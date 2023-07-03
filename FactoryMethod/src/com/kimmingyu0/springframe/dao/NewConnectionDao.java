package com.kimmingyu0.springframe.dao;

public class NewConnectionDao implements DaoFactory {

	@Override
	public ConnectionMaker createConnection() {
		return new NewConnectionMaker();
	}

}
