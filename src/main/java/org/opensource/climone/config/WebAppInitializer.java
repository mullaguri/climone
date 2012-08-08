package org.opensource.climone.config;

import com.sun.faces.config.WebConfiguration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.*;
import java.util.EnumSet;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));

        // encoding filter
        org.springframework.web.filter.CharacterEncodingFilter encodingFilter = new org.springframework.web.filter.CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(false);
        FilterRegistration.Dynamic encodingFilterDynamic = servletContext.addFilter("charEncodingFilter", encodingFilter);
        encodingFilterDynamic.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // security filter
        org.springframework.web.filter.DelegatingFilterProxy delegatingFilterProxy = new org.springframework.web.filter.DelegatingFilterProxy(
                "springSecurityFilterChain");
        FilterRegistration.Dynamic securityFilterDynamic = servletContext.addFilter("securityFilter", delegatingFilterProxy);
        securityFilterDynamic.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // webflow
        //AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        //mvcContext.register(WebMvcConfig.class);
        org.springframework.web.servlet.DispatcherServlet dispatcherServlet = new org.springframework.web.servlet.DispatcherServlet();
        dispatcherServlet.setContextConfigLocation("");
        ServletRegistration.Dynamic dispatcherServletDynamic = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
        dispatcherServletDynamic.setLoadOnStartup(1);
        dispatcherServletDynamic.addMapping("/view/*");


        /*
        //opensessioninview filter
        OpenSessionInViewFilter openSessionInViewFilter = new OpenSessionInViewFilter();
        FilterRegistration.Dynamic openSessionInViewFilterDynamic = servletContext.addFilter("openSessionInViewFilter", openSessionInViewFilter);
        openSessionInViewFilterDynamic.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/view/*");
        */
        // faces
        servletContext.setInitParameter(WebConfiguration.WebContextInitParameter.JavaxFacesProjectStage.getQualifiedName(), "Development");
        servletContext.setInitParameter(WebConfiguration.BooleanWebContextInitParameter.FaceletsSkipComments.getQualifiedName(), "true");
        servletContext.setInitParameter(WebConfiguration.WebContextInitParameter.FaceletsLibraries.getQualifiedName(),
                "/WEB-INF/springsecurity.taglib.xml");

        servletContext.setInitParameter("primefaces.skin", "bluesky");
        
    }
}