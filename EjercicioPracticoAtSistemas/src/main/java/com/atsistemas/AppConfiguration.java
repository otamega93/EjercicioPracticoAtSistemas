package com.atsistemas;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories("com.atsistemas.repositories")
public class AppConfiguration {

	@Bean
	public DataSource datasource() {
			
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl("jdbc:derby://localhost:1527/clientes;create=true");
		ds.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
		ds.setUsername("user");
		ds.setPassword("user");
		return ds;
	}
		
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
			
		return new JpaTransactionManager(emf);
	}
		
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource datasource) {
			
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(datasource);
		factoryBean.setPackagesToScan("com.atsistemas.entities");
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		Map<String, String> map = new HashMap<>();
		map.put("hibernate.hbm2ddl.auto", "update");
		factoryBean.setJpaPropertyMap(map);
		return factoryBean;
			
	}
}
