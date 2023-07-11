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
	public UserService userService() {
		UserService userService = new UserService();
		userService.setUserDao(userDao());
		userService.setTransactionManager(transactionManager());
		//<property name="mailSender" ref="mailSender" />
//		userService.setMailSender(mailSender());
		userService.setMailSender(javaMailSender());
		userService.setAnyMailRequest(requestRepo());
		return userService;
	}

	@Bean
	public DummyMailSender mailSender() {
		DummyMailSender dummyMailSender = new DummyMailSender();
		return dummyMailSender;
	}

	@Bean
	public RequestRepo requestRepo(){
		RequestRepo requestRepo = new RequestRepo();
		return requestRepo;
	}

	@Bean
	public JavaMailSenderImpl javaMailSender() {
		// JaveMailSenderImpl 인스턴스 생성 후
		// 발신자 정보만 입력해야됨.
		// 다른 기능은 분리해서 의존성 주입
		JavaMailSenderImpl smailSender = new JavaMailSenderImpl();
		smailSender.setHost("smtp.gmail.com");
		smailSender.setPort(587);
		smailSender.setUsername("minku4820@gmail.com");
		smailSender.setPassword("kiqlmupofwubhrsq");

		smailSender.setJavaMailProperties(properties());

// 		bean 객체 분리후 의존성 주입

//		java.util.Properties props = smailSender.getJavaMailProperties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");

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
