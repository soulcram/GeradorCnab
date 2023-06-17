//package br.com.m3Tech.solucoesFromtis.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.data.domain.Pageable;
//
//import br.com.m3Tech.solucoesFromtis.dto.PageableModel;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//@Profile("!prod")
//public class SwaggerConfig {          
//	
//    @Bean
//    public Docket api() { 
//        return new Docket(DocumentationType.SWAGGER_2)  
//          .select()                                  
//          .apis(RequestHandlerSelectors.basePackage("br.com.m3Tech.solucoesFromtis"))              
//          .paths(PathSelectors.any())   
//          .build()
//          .directModelSubstitute(Pageable.class, PageableModel.class)
//          .apiInfo(apiInfo());                                           
//    }
//    
//	private ApiInfo apiInfo() {
//
//		return new ApiInfoBuilder().title("Mock Service Registradora API")
//				.description("Mock Cerc para integração das Registradoras")
//				.version("1.0").build();
//	}    
//    
//}
//
//
