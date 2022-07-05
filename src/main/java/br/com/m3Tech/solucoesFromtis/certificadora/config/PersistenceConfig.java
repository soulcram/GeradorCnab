//package br.com.m3Tech.solucoesFromtis.certificadora.config;
//
//import org.hibernate.cfg.AvailableSettings;
//import org.hibernate.dialect.H2Dialect;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.support.SharedEntityManagerBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.inject.Inject;
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@EnableJpaRepositories(basePackages = { "br.com.fromtis.certificadora.repository" })
//@EnableTransactionManagement
//public class PersistenceConfig {
//
//	@Bean
//	@Inject
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory(final @MemDataSource DataSource dataSource) {
//
//		final Properties properties = new Properties();
//		properties.setProperty(AvailableSettings.DIALECT, H2Dialect.class.getName());
//		properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
////		properties.put(AvailableSettings.SHOW_SQL, true);
//
//		final LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//		entityManagerFactory.setPersistenceUnitName("fidcPersistence");
//		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//		entityManagerFactory.setJpaProperties(properties);
//		entityManagerFactory.setDataSource(dataSource);
//		entityManagerFactory.setPackagesToScan("br.com.fromtis");
//
//		return entityManagerFactory;
//	}
//
//	@Bean
//	@Inject
//	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory,	@MemDataSource DataSource appDataSource) {
//		JpaTransactionManager txManager = new JpaTransactionManager();
//		txManager.setEntityManagerFactory(entityManagerFactory);
//		txManager.setDataSource(appDataSource);
//		txManager.setJpaDialect(new HibernateJpaDialect());
//		return txManager;
//	}
//
//	@Bean
//	public SharedEntityManagerBean jpaEntityManager(final EntityManagerFactory entityManagerFactory) {
//		final SharedEntityManagerBean sharedEntityManagerBean = new SharedEntityManagerBean();
//		sharedEntityManagerBean.setEntityManagerFactory(entityManagerFactory);
//		sharedEntityManagerBean.afterPropertiesSet();
//		return sharedEntityManagerBean;
//	}
//
//}
