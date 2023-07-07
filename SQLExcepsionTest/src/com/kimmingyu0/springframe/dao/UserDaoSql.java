package com.kimmingyu0.springframe.dao;

import com.kimmingyu0.springframe.domain.User;
import com.mysql.cj.exceptions.MysqlErrorNumbers;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoSql {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public void add(User user) throws DuplicateUserIdExceotion, ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/sbdt_db1?characterEncoding=UTF-8",
					"root",
					"1234");

			PreparedStatement ps = c.prepareStatement(
					"insert into users(id, name, password) values(?,?,?)");
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());

			ps.executeUpdate();
			ps.close();
			c.close();
		} catch (SQLException e){
			if(e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY){
				throw new DuplicateUserIdExceotion(e);
			} else {
				throw new RuntimeException(e);
			}
		}
	}
}
