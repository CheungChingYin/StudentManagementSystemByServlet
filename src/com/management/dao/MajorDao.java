package com.management.dao;

import java.sql.SQLException;
import java.util.List;

import com.management.entities.Major;

public interface MajorDao {
	
	public List<Major> queryAllMajor() throws SQLException;
	
	public Major queryMajorById(Integer id) throws SQLException;
	
	public List<Major> queryMajorByCollege(Integer id) throws SQLException;
	
	public Major queryMajorByName(String name) throws SQLException;
	
	public void addMajor(Major major) throws SQLException;
	
	public void alertMajor(Major major) throws SQLException;
	
	public void deleteMajor(Integer id) throws SQLException;
	
	public boolean existMajor(String majorName) throws SQLException;

}
