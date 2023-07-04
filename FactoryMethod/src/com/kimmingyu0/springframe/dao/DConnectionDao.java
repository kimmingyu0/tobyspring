package com.kimmingyu0.springframe.dao;

import java.sql.SQLException;

public class DConnectionDao extends UserDao {
	@Override
	public void createConnection() throws ClassNotFoundException, SQLException {
		this.connectionMaker.makeConnection();
	}	
	
	public DConnectionDao () {
		this.connectionMaker = new DConnectionMaker();
	}
}

