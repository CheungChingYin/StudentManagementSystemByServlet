package com.management.service;

import java.util.List;

import com.management.entities.College;

public interface CollegeService {

	public List<College> searchAllCollege();
	
	public College searchCollegeById(Integer id);
	
	public College searchCollegeByName(String name);
	
	public void RegistCollege(College college);
	
	public void AlertCollege(College college);
	
	public void deleteCollege(Integer id);
}
