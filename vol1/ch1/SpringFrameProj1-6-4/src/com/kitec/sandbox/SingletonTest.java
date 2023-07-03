package com.kitec.sandbox;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kitec.springframe.dao.DaoFactory;
import com.kitec.springframe.dao.UserDao;

public class SingletonTest {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		
		UserDao user1 = context.getBean(UserDao.class);
		UserDao user2 = context.getBean(UserDao.class);
		System.out.println(user1);
		System.out.println(user2);

	}

}
