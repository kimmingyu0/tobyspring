package com.kimmingyu0.springframe.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

	@Bean
	public UserDao userDao() {
		 UserDao dao = new UserDao();
		 dao.setConnection(connectionMaker());
		 return dao;
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		DConnectionMaker connection = new DConnectionMaker();
		connection.setClassName("com.mysql.cj.jdbc.Driver");
		connection.setUrl("jdbc:mysql://localhost:3306/sbdt_db?characterEncoding=UTF-8");
		connection.setUserName("root");
		connection.setPassword("1234");
		return connection;
	}
}
