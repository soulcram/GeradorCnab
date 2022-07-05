package br.com.m3Tech.solucoesFromtis;

import java.util.Arrays;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages = {"br.com.m3Tech.solucoesFromtis"})
public class FromtisSolucoesApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FromtisSolucoesApplication.class, args);
	}
	
	  @Bean
	  ServletRegistrationBean<FacesServlet> jsfServletRegistration (ServletContext servletContext) {
	      //spring boot only works if this is set
	      servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
	      //registration
	      ServletRegistrationBean<FacesServlet> srb = new ServletRegistrationBean<FacesServlet>();
	      srb.setServlet(new FacesServlet());
	      srb.setUrlMappings(Arrays.asList("*.xhtml"));
//	      srb.setLoadOnStartup(10);
	      return srb;
	  }
	  


}
