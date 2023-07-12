package com.intheeast.springframe.service;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.intheeast.springframe.dao.UserDaoJdbc;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class TestServiceFactory {
	@Bean
	public DataSource dataSource() {
		
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost:3306/testdb?characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");

		return dataSource;
	}

	@Bean
	public UserDaoJdbc userDao() {
		UserDaoJdbc userDaoJdbc = new UserDaoJdbc();
		userDaoJdbc.setDataSource(dataSource());
		return userDaoJdbc;
	}

	@Bean
	public UserServiceTx userServiceTx(){
		UserServiceTx userServiceTx = new UserServiceTx();
		userServiceTx.setUserService(userService());
		userServiceTx.setTransactionManager(transactionManager());

		return userServiceTx;
	}


	@Bean
	public UserServiceImpl userService() {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setUserDao(userDao());
//		userServiceImpl.setTransactionManager(transactionManager());
		userServiceImpl.setMailSender(javaMailSender());
		userServiceImpl.setAnyMailRequest(requestRepo());
		return userServiceImpl;
	}

	@Bean
	public DummyMailSender mailSender() {
		DummyMailSender dummyMailSender = new DummyMailSender();
		return dummyMailSender;
	}

	@Bean
	public RequestRepo requestRepo(){
		RequestRepo requestRepo = new RequestRepo();

		requestRepo.setJavaMailProperties(properties());
		return requestRepo;
	}

	@Bean
	public JavaMailSenderImpl javaMailSender() {
		JavaMailSenderImpl smailSender = new JavaMailSenderImpl();
		smailSender.setHost("smtp.gmail.com");
		smailSender.setPort(587);
		smailSender.setUsername("minku4820@gmail.com");
		smailSender.setPassword("kiqlmupofwubhrsq");

		smailSender.setJavaMailProperties(properties());

		return smailSender;
	}

	@Bean
	public Properties properties (){
		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		return props;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource());
		return dataSourceTransactionManager;
	}
}
