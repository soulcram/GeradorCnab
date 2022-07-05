package br.com.m3Tech.solucoesFromtis.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorRequisicoesCertificadora;
import br.com.m3Tech.solucoesFromtis.certificadora.service.impl.GuardadorRequisicaoCertificadoraImpl;

@Configuration
@EnableWs
//@ImportResource("classpath:/applicationContext.xml")
public class WebServiceConfiguration extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(servlet, "/soap/*");
	}

//	@Bean(name = "countries")
//	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
//		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
//		wsdl11Definition.setPortTypeName("CountriesPort");
//		wsdl11Definition.setLocationUri("/ws");
//		wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
//		wsdl11Definition.setSchema(countriesSchema);
//		return wsdl11Definition;
//	}
//
//	@Bean
//	public XsdSchema countriesSchema() {
//		return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
//	}
	


}
