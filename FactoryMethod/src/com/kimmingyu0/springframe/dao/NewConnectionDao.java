package com.kimmingyu0.springframe.dao;

public class NewConnectionDao extends UserDao{
	@Override
	public void createConnection() {
		this.connectionMaker = new NewConnectionMaker();
	}
}