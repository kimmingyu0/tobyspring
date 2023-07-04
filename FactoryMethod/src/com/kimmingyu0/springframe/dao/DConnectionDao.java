package com.kimmingyu0.springframe.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DConnectionDao extends UserDao {
	@Override
	public Connection createConnection() throws ClassNotFoundException, SQLException {
		return this.connectionMaker.makeConnection();
	}	
	
	public DConnectionDao () {
		this.connectionMaker = new DConnectionMaker();
	}
}

