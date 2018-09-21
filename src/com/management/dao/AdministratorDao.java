package com.management.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.management.entities.Administrator;

public interface AdministratorDao {

	public boolean existAdministrator(Administrator admin) throws SQLException, IOException;
	
	public boolean loginAdministrator(String userName,String password);
	
	public void registerAdministrator(Administrator admin);
	
	public ResultSet queryAllAdministrator();
	
	public boolean alertAdministrator(Administrator admin);
	
}
