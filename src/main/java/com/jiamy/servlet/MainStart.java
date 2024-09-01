package com.jiamy.servlet;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class MainStart {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();
        Context context = tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());
        WebResourceRoot root = new StandardRoot(context);
        root.addPreResources(new DirResourceSet(root, "/WEB-INF/classes", new File("target/classes").getAbsolutePath(),"/"));
        context.setResources(root);
        tomcat.start();
        tomcat.getServer().await();
    }
}
