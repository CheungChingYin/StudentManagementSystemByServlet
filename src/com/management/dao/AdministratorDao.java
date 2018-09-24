package com.management.dao;

import java.sql.SQLException;
import java.util.List;

import com.management.entities.Administrator;

public interface AdministratorDao {

	public boolean existAdministrator(Administrator admin) throws SQLException;
	
	public boolean loginAdministrator(String userName,String password) throws SQLException;
	
	public void addAdministrator(Administrator admin) throws SQLException;
	
	public List<Administrator> queryAllAdministrator() throws SQLException;
	
	public void alertAdministrator(Administrator admin) throws SQLException;
	
	public void deleteAdministrator(Integer id) throws SQLException;
	
}
