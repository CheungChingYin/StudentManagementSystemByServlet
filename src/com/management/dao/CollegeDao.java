package com.management.dao;

import java.sql.SQLException;
import java.util.List;

import com.management.entities.College;

public interface CollegeDao {
	
	public List<College> queryAllCollege() throws SQLException;
	
	public College queryCollegeById(Integer id) throws SQLException;
	
	public College queryCollegeByName(String name) throws SQLException;
	
	public void addCollege(College college) throws SQLException;
	
	public void alertCollege(College college) throws SQLException;
	
	public void deleteCollege(Integer id) throws SQLException;
	
	public boolean existCollege(String collegeName) throws SQLException;
	

}
