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
//	basePackages = "com.myteam.chat.match",
//	entityManagerFactoryRef = "secondEntityManager",
//	transactionManagerRef = "secondTransactionManager"
//)
//public class SecondDBConfig {
//	@Bean
//	@ConfigurationProperties(prefix = "spring.second-datasource")
//	public DataSource secondDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Bean
//	public LocalContainerEntityManagerFactoryBean secondEntityManager() {
//		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//		em.setDataSource(secondDataSource());
//		em.setPackagesToScan("com.myteam.chat.match");
//
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setShowSql(true);
//		vendorAdapter.setGenerateDdl(true);
//		em.setJpaVendorAdapter(vendorAdapter);
//
//		HashMap<String, Object> prop = new HashMap<>();
//		prop.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//		prop.put("hibernate.hbm2ddl.auto", "none");
//		prop.put("hibernate.format_sql", true);
//		em.setJpaPropertyMap(prop);
//
//		return em;
//	}
//
//	@Bean
//	public PlatformTransactionManager secondTransactionManager() {
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(secondEntityManager().getObject());
//		return transactionManager;
//	}
//}
