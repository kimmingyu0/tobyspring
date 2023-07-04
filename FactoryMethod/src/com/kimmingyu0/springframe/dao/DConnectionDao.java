package com.kimmingyu0.springframe.dao;

public class DConnectionDao extends UserDao {
	@Override
	public void createConnection() {
		this.connectionMaker = new DConnectionMaker();
	}	
}

