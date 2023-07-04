package com.kimmingyu0.springframe.dao;

import java.sql.SQLException;

public class NewConnectionDao extends UserDao{
	@Override
	public void createConnection() throws ClassNotFoundException, SQLException {
		this.connectionMaker.makeConnection();
	}
	
	public NewConnectionDao () {
		this.connectionMaker = new NewConnectionMaker();
	}
}