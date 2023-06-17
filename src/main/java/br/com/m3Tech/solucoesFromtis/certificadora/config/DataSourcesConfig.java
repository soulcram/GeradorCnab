//package br.com.m3Tech.solucoesFromtis.certificadora.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.dbcp.BasicDataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.io.File;
//
//
//@Configuration
//@Slf4j
//public class DataSourcesConfig {
//
//
//    @Configuration
//    public class MemoryDataSource {
//    	
//    	@MemDataSource
//        @Bean
//        public DataSource h2DataSource() {
//        	log.info("Carregando Data Source do Banco de Dados Embarcado H2");
//        	String urlDb = new StringBuilder()
//        			.append("jdbc:h2:file:")
//        			.append(System.getProperty("catalina.home"))
//        			.append(File.separator)
//        			.append("conf")
//        			.append(File.separator)
//        			.append("fromtis")
//        			.append(File.separator)
//        			.append("db")
//        			.append(File.separator)
//        			.append("certificadora_app")
//        			.toString();
//        	
//        	
//    		final BasicDataSource dataSource = new BasicDataSource();
//            dataSource.setDriverClassName("org.h2.Driver");
////            dataSource.setUrl("jdbc:h2:mem:datasource;DB_CLOSE_ON_EXIT=true");
//            dataSource.setUrl(urlDb);
//            dataSource.setValidationQuery("SELECT 1");
//            dataSource.setUsername("sa");
//            dataSource.setMaxOpenPreparedStatements(100);
//            dataSource.setInitialSize(50);
//            dataSource.setMaxActive(1);
//            dataSource.setMaxIdle(5);
//            dataSource.setPassword("");
//            return dataSource;
//        }
//    }
//
//}