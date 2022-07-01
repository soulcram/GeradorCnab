package br.com.m3Tech.solucoesFromtis;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;

import com.sun.faces.config.ConfigureListener;
import com.sun.faces.config.FacesInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;


@SpringBootApplication
public class FromtisSolucoesApplication implements ServletContextInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FromtisSolucoesApplication.class, args);
    }

    @Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<>(new JsfApplicationObjectConfigureListener());
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
        servletContext.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");

        servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
        servletContext.setInitParameter("facelets.DEVELOPMENT", "true");
        servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
        servletContext.setInitParameter("primefaces.THEME", "blitzer");

        Set<Class<?>> clazz = new HashSet<Class<?>>();

        clazz.add(FromtisSolucoesApplication.class); // dummy, enables InitFacesContext

        FacesInitializer facesInitializer = new FacesInitializer();
        facesInitializer.onStartup(clazz, servletContext);
    }

    static class JsfApplicationObjectConfigureListener extends ConfigureListener {

        @Override
        public void contextInitialized(ServletContextEvent sce) {
            super.contextInitialized(sce);

            ApplicationFactory factory = (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
            Application app = factory.getApplication();

            app.addELResolver(new SpringBeanFacesELResolver());
        }
    }

}
