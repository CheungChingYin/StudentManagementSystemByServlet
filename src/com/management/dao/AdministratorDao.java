package com.management.dao;

import java.sql.SQLException;
import java.util.List;

import com.management.entities.Administrator;
import com.management.exception.AdministratorExistException;

public interface AdministratorDao {

	public boolean existAdministrator(Administrator admin) throws AdministratorExistException,SQLException;
	
	public boolean loginAdministrator(String userName,String password) throws SQLException;
	
	public void addAdministrator(Administrator admin) throws SQLException;
	
	public List<Administrator> queryAllAdministrator() throws SQLException;
	
	public Administrator queryAdministratorById(Integer id) throws SQLException;
	
	public void alertAdministrator(Administrator admin) throws SQLException;
	
	public void deleteAdministrator(Integer id) throws SQLException;
	
	public Administrator queryAdministratorByName(String name) throws SQLException;
	
	public void alertAdministratorPassword(Integer id,String password) throws SQLException;
	
}
