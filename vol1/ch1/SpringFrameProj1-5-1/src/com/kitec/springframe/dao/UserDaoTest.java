package com.kitec.springframe.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kitec.springframe.domain.User;


public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(DaoFactory.class);
		
		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("whiteship");
		user.setName("백기선");
		user.setPassword("married");

		dao.add(user);
			
		System.out.println(user.getId() + "\n등록 완료");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
			
		System.out.println(user2.getId() + "\n조회 완료");
	}

}
