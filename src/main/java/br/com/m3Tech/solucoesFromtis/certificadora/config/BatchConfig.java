//package br.com.m3Tech.solucoesFromtis.certificadora.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
//
//@Configuration
//@EnableScheduling
//@Slf4j
//public class BatchConfig {
//
//	@Bean(destroyMethod = "shutdown")
//	public ThreadPoolTaskScheduler taskScheduler() throws Exception {
//		log.info("Carregando BatchConfig.");
//		ThreadPoolTaskScheduler taskPool = new ThreadPoolTaskScheduler();
//		taskPool.setPoolSize(2);
//		taskPool.setThreadNamePrefix("Scheduler - ");
//		taskPool.setWaitForTasksToCompleteOnShutdown(true);
//		taskPool.afterPropertiesSet();
//		return taskPool;
//	}
//
//}