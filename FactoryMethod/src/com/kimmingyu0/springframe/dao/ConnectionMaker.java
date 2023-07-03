package com.kimmingyu0.springframe.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {

	/**
	 * Database Connection 생성 추상 메서드
	 * */
	public abstract Connection makeConnection() throws ClassNotFoundException, SQLException;

}
