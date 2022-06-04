//package br.com.m3Tech.solucoesFromtis.config;
//
//import java.time.Duration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import io.github.bucket4j.Bandwidth;
//import io.github.bucket4j.Bucket;
//import io.github.bucket4j.Bucket4j;
//import io.github.bucket4j.Refill;
//
//@SuppressWarnings("deprecation")
//@Configuration
//public class ConfigBean {
//	
//	  @Bean
//	  Bucket bucket() {
//		  Bandwidth limit = Bandwidth.classic(1, Refill.greedy(1, Duration.ofMinutes(1)));
//		  return Bucket4j.builder()
//	            .addLimit(limit)
//	            .build();
//		  
//	  }
//
//}
