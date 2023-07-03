package com.kitec.springframe.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kitec.springframe.domain.User;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {DaoFactory.class})
public class UserDaoTest {	
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {				
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();	
		assertEquals(dao.getCount(), 0);
		
		User user = new User();
		user.setId("gyumee");
		user.setName("¹Ú¼ºÃ¶");
		user.setPassword("springno1");

		dao.add(user);
		assertEquals(dao.getCount(), 1);
		
		User user2 = dao.get(user.getId());		
		
		assertEquals(user2.getName(), user.getName());
		assertEquals(user2.getPassword(), user.getPassword());		
	}   

}
