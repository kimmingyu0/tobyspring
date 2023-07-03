package com.kimmingyu0.springframe.dao;

import java.sql.SQLException;

import com.kimmingyu0.springframe.domain.User;



public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = DaoFactory.createDao();

		// UserDao 인스턴스 메서드 create 호출
		dao.create("NewConnectionMaker");
		/* arguments에 따라  
		* UserDao 내부에서 
		* NewConnectionMaker
		* or
		* DConnectionMaker
		* 두개중 하나를 인스턴스화 후
		* UserDao 내부 ConnectionMarker 참조변수에 대입한다.
		* 위 인스턴스들은 oracle 또는 mysql과의 커넥터를 생성한다.
		*/ 

		User user = new User();
		user.setId("whiteship");
		user.setName("백기선");
		user.setPassword("married");

		dao.add(user);
			
		System.out.println(user.getId() + "\n회원 등록 완료");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
			
		System.out.println(user2.getId() + "\n회원 조회 완료");
	}

}
