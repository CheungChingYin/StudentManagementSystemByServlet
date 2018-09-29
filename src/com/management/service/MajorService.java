package com.management.service;

import java.util.List;

import com.management.entities.Major;

public interface MajorService {
	
	public List<Major> searchAllMajor();
	
	public List<Major> searchMajorByCollegeId(Integer id);
	
	public Major searchMajorById(Integer id);
	
	public Major searchMajorByName(String name);
	
	public void registMajor(Major major);
	
	public void alertMajor(Major major);
	
	public void deleteMajor(Integer id);
	

}
