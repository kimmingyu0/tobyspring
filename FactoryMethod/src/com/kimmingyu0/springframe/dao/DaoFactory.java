package com.kimmingyu0.springframe.dao;

import java.sql.SQLException;

public interface DaoFactory {
	
	ConnectionMaker create (String connectionType);
	
	/**
	 * DaoFactory 인터페이스 내 static 메서드
	 * UserDao 인스턴스 생성후 참조변수 반환
	 * @return UserDao
	 * */
	static UserDao createDao () {
		return new UserDao();
	}
	
}
