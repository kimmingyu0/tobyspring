package com.kimmingyu0.springframe.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class NewConnectionDao extends UserDao{
	@Override
	public Connection createConnection() throws ClassNotFoundException, SQLException {
		return this.connectionMaker.makeConnection();
	}
	
	public NewConnectionDao () {
		this.connectionMaker = new NewConnectionMaker();
	}
}