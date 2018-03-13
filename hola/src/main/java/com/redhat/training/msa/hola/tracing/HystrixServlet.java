package com.redhat.training.msa.hola.tracing;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@WebListener
public class HystrixServlet implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        System.out.println(servletContext);
		servletContext
            .addServlet("HystrixMetricsStreamServlet", HystrixMetricsStreamServlet.class)
            .addMapping("/hystrix.stream");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
