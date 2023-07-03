package com.kimmingyu0.springframe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kimmingyu0.springframe.domain.User;

public class UserDao implements DaoFactory {

	private ConnectionMaker connectionMaker;

//	public UserDao(ConnectionMaker simpleConnectionMaker) {
//		this.connectionMaker = simpleConnectionMaker;
//	}

	/**
	 * ConnectionMaker 인스턴스화 후 UserDao내 참조변수 저장
	 * @param DConnectionMarker and NewConnectionMaker
	 * @return ConnectionMaker 인터페이스의 구체화 class 인스턴스의 참조변수
	 * */
	@Override
	public ConnectionMaker create(String connectionType) {
		if (connectionType == null) {
			return null;
		} else if (connectionType == "DConnectionMarker") {
			this.connectionMaker = new DConnectionMaker();
		} else if (connectionType == "NewConnectionMaker") {
			this.connectionMaker = new NewConnectionMaker();
		}
		return null;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();

		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();

		ps.close();
		c.close();
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();
		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));

		rs.close();
		ps.close();
		c.close();

		return user;
	}

}
