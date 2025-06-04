//package com.myteam.chat.global.config;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@EnableJpaRepositories(
//	basePackages = "com.myteam.chat.chat",
//	entityManagerFactoryRef = "primaryEntityManager",
//	transactionManagerRef = "primaryTransactionManager"
//)
//public class PrimaryConfig {
//
//	@Bean
//	@Primary
//	@ConfigurationProperties(prefix = "spring.datasource")
//	public DataSource primaryDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Bean
//	@Primary
//	public LocalContainerEntityManagerFactoryBean primaryEntityManager() {
//		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//		em.setDataSource(primaryDataSource());
//		em.setPackagesToScan("com.myteam.chat.chat");
//
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setShowSql(true);
//		vendorAdapter.setGenerateDdl(true);
//		em.setJpaVendorAdapter(vendorAdapter);
//
//		Map<String, Object> prop = new HashMap<>();
//		prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//		prop.put("hibernate.hbm2ddl.auto", "update");
//		prop.put("hibernate.format_sql", true);
//		em.setJpaPropertyMap(prop);
//
//		return em;
//	}
//
//	@Bean
//	@Primary
//	public PlatformTransactionManager primaryTransactionManager() {
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(primaryEntityManager().getObject());
//		return transactionManager;
//	}
//}
