package ru.dsoccer1980.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer {//} implements WebApplicationInitializer {

//  private AnnotationConfigWebApplicationContext context =
//      new AnnotationConfigWebApplicationContext();
//
//  @Override
//  public void onStartup(ServletContext servletContext) throws ServletException {
//    context.register(WebConfig.class);
//    context.setServletContext(servletContext);
//    DispatcherServlet ds = new DispatcherServlet(context);
//    ds.setThrowExceptionIfNoHandlerFound(true);
//    ServletRegistration.Dynamic restDynamic = servletContext.addServlet("dispatcher", ds);
//    restDynamic.addMapping("/");
//    restDynamic.setLoadOnStartup(1);
//
//  }
}
