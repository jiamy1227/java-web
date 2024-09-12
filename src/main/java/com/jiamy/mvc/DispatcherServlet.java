package com.jiamy.mvc;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/")
public class DispatcherServlet extends HttpServlet {

    // 实现个体请求映射
    private Map<String, GetDispatcher> getDispatcherMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (getDispatcherMap.containsKey(req.getRequestURI())){
            try {
                getDispatcherMap.get(req.getRequestURI()).invoke(req ,resp);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // 扫描包
        String packageName = "com.jiamy.mvc.controller";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Enumeration<URL> resources = classLoader.getResources(packageName.replace('.', '/'));
            while(resources.hasMoreElements()) {
                File file = new File(resources.nextElement().getFile());
                if(file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    for(File subFile: listFiles) {
                        if(subFile.getName().endsWith(".class")){
                            String className = packageName + "."+ subFile.getName().replace(".class","");
                            Class<?> clazz = classLoader.loadClass(className);
                            Method[] methods = clazz.getMethods();
                            for (Method method : methods) {
                                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                                if (getMapping != null) {
                                    GetDispatcher getDispatcher = new GetDispatcher();
                                    getDispatcher.setMethod(method);
                                    getDispatcher.setInstance(clazz.getDeclaredConstructor().newInstance());
                                    // fixme: 参数没有传到controller
                                    Parameter[] parameters = method.getParameters();
                                    String[] paramNames = new String[parameters.length];
                                    for(int i=0;i<parameters.length;i++) {
                                        paramNames[i] = parameters[i].getName();
                                    }
                                    getDispatcher.setParameterNames(paramNames);
                                    getDispatcher.setParameterClasses(method.getParameterTypes());
                                    String path = getMapping.value();
                                    getDispatcherMap.put(path,getDispatcher);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }
}
