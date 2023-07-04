package com.kimmingyu0.springframe.dao;

public class NewConnectionDao extends UserDao{
	@Override
	public ConnectionMaker createConnection() {
		return new NewConnectionMaker();
	}
	
	public NewConnectionDao(){
		this.connectionMaker = this.createConnection();
	}
}