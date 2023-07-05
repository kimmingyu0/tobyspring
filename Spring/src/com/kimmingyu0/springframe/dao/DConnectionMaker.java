package com.kimmingyu0.springframe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker {
	private String className;
	private String url;
	private String userName;
	private String password;

	public void setClassName(String className) {
		this.className = className;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		Class.forName(this.className);
		Connection c = DriverManager.getConnection(this.url, this.userName, this.password);
		return c;
	}

}
