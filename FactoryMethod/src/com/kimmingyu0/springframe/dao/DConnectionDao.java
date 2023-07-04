package com.kimmingyu0.springframe.dao;

public class DConnectionDao extends UserDao {
	@Override
	public ConnectionMaker createConnection() {
		return new DConnectionMaker();
	}
	
	public DConnectionDao(){
		this.connectionMaker = this.createConnection();
	}
}

