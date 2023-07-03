package com.kimmingyu0.springframe.dao;

public class DConnectionDao implements DaoFactory {

	@Override
	public ConnectionMaker createConnection() {
		return new DConnectionMaker();
	}

}
