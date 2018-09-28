package com.management.entities;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Student {

	private String id;
	private String name;
	private Integer sex;
	private Date birth;
	private Date schoolDay;
	private Integer major_id;
	private Integer college_id;
	private String majorName;
	private String collegeName;
	private Map<String, String> errors = new HashMap<String, String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}
	

	public Date getSchoolDay() {
		return schoolDay;
	}

	public void setSchoolDay(Date schoolDay) {
		this.schoolDay = schoolDay;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Integer getMajor_id() {
		return major_id;
	}

	public void setMajor_id(Integer major_id) {
		this.major_id = major_id;
	}

	public Integer getCollege_id() {
		return college_id;
	}

	public void setCollege_id(Integer college_id) {
		this.college_id = college_id;
	}
	
	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	

}
