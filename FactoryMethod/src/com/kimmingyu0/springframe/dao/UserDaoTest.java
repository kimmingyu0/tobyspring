package com.kimmingyu0.springframe.dao;

import java.sql.SQLException;

import com.kimmingyu0.springframe.domain.User;



public class UserDaoTest {
	public static class DConnectionDao extends UserDao {
		@Override
		public ConnectionMaker createConnection() {
			return new DConnectionMaker();
		}
		
		public DConnectionDao(){
			this.connectionMaker = this.createConnection();
		}
	}
	
	public static class NewConnectionDao extends UserDao{
		@Override
		public ConnectionMaker createConnection() {
			return new NewConnectionMaker();
		}
		
		public NewConnectionDao(){
			this.connectionMaker = this.createConnection();
		}
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		UserDao dao = new NewConnectionDao();

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
