package com.jiamy.mvc;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class ViewEngine {

    private final ServletContext servletContext;

    private final TemplateEngine templateEngine;


    //
    public ViewEngine(ServletContext context) {
        this.servletContext = context;
        // 初始化模板引擎
        templateEngine = new TemplateEngine();
        //
        JakartaServletWebApplication jakartaServletWebApplication = JakartaServletWebApplication.buildApplication(context);
        // 初始化resolver
        WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(jakartaServletWebApplication);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // This will convert "home" to "/WEB-INF/templates/home.html"
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        // thymeleaf页面中文乱码 == 通过EncodingFilter解决
        templateResolver.setCharacterEncoding("UTF-8");
        templateEngine.setTemplateResolver(templateResolver);

    }

    public void render(ModelAndView mv, Writer writer, HttpServletRequest request, HttpServletResponse response){
        // 渲染页面
        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(servletContext).buildExchange(request, response);
        WebContext context = new WebContext(iWebExchange);
        context.setVariables(mv.getModel());
        templateEngine.process(mv.getView(), context, writer);

    }
}
