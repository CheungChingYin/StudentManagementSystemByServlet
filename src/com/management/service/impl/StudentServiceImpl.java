package com.management.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.management.dao.StudentDao;
import com.management.dao.impl.StudentDaoImpl;
import com.management.entities.Student;
import com.management.exception.StudentExistException;
import com.management.service.StudentService;
import com.management.utils.CheckDataValidity;

public class StudentServiceImpl implements StudentService {

	private StudentDao dao = new StudentDaoImpl();

	@Override
	public void addStudent(Student student) {

		try {
			if (dao.studentIsExist(student.getId())) {
				throw new StudentExistException();
			}
			if(CheckDataValidity.checkStudentValidate(student)){
				dao.addStudent(student);
			}

		} catch (SQLException | StudentExistException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void alertStudent(Student student) {

		try {
			if (dao.studentIsExist(student.getId())) {
				if(CheckDataValidity.checkStudentValidate(student)){
					dao.alertStudent(student);
				}
			} else {
				throw new StudentExistException();
			}
		} catch (StudentExistException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteStudent(String id) {

		try {
			dao.deleteStudent(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Student> queryAllStudent() {

		List<Student> list = null;
		try {
			list = dao.queryAllStudents();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Student searchStudentById(String id) {

		Student student = null;
		try {
			student = dao.queryStudentById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public List<Student> searchStudentByName(String name) {

		List<Student>list = null;
		try {
			list = dao.queryStudentByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Integer countStudents() {
		List<Student> list = null;
		try {
			list = dao.queryAllStudents();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int count = list.size();
		return count;
	}

}
