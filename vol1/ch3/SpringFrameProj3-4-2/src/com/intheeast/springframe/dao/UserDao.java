package com.intheeast.springframe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import com.intheeast.springframe.domain.User;

public class UserDao {
	private DataSource dataSource;

	private JdbcContext jdbcContext;

	public void setDataSource(DataSource dataSource) {
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setDataSource(dataSource);

		this.dataSource = dataSource;
	}
	
	// functional Interface = StatementStrategy
	// =>
	// PreparedStatement makePreparedStatement(Connection c) throws SQLException;
	public void add(final User user) throws SQLException {

		this.jdbcContext.workWithStatementStrategy(c -> {
			PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());

			return ps;
		});
	}

//				new StatementStrategy() {
//					public PreparedStatement makePreparedStatement(Connection c)
//					throws SQLException {
//						PreparedStatement ps =
//							c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
//						ps.setString(1, user.getId());
//						ps.setString(2, user.getName());
//						ps.setString(3, user.getPassword());
//
//						return ps;
//					}
//				}
//		);
	}

	public User get(String id) throws ClassNotFoundException, SQLException {

		Connection c = this.dataSource.getConnection();

		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();

		User user = null;
		if (rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}

		rs.close();
		ps.close();
		c.close();

		if (user == null)
			throw new EmptyResultDataAccessException(1);

		return user;
	}

	// functional Interface = StatementStrategy
	// =>
	// PreparedStatement makePreparedStatement(Connection c) throws SQLException;
	public void deleteAll() throws SQLException {
		this.jdbcContext.workWithStatementStrategy(
			c -> {
				return c.prepareStatement("delete from users");
			}
//				new StatementStrategy() {
//					public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//						return c.prepareStatement("delete from users");
//					}
//				}
		);
	}

	public int getCount() throws SQLException, ClassNotFoundException {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = this.dataSource.getConnection();

			ps = c.prepareStatement("select count(*) from users");
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
		}
	}

}
