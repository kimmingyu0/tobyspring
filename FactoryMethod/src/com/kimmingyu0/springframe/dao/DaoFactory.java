package com.kimmingyu0.springframe.dao;

import java.sql.SQLException;

public interface DaoFactory {
	
	ConnectionMaker createConnection ();
	
}
