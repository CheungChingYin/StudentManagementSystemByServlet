package com.management.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.management.dao.MajorDao;
import com.management.dao.impl.MajorDaoImpl;
import com.management.entities.Major;
import com.management.exception.MajorExistException;
import com.management.service.MajorService;
import com.management.utils.CheckDataValidity;

public class MajorServiceImpl implements MajorService {

	MajorDao dao = new MajorDaoImpl();

	@Override
	public List<Major> searchAllMajor() {

		List<Major> list = null;

		try {
			list = dao.queryAllMajor();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Major> searchMajorByCollegeId(Integer id) {

		List<Major> list = null;
		try {
			list = dao.queryMajorByCollege(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Major searchMajorById(Integer id) {
		Major major = null;
		try {
			major = dao.queryMajorById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return major;
	}

	@Override
	public Major searchMajorByName(String name) {
		Major major = null;
		try {
			major = dao.queryMajorByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return major;
	}

	@Override
	public void registMajor(Major major) {

		try {
			if (dao.existMajor(major.getName())) {
				major.getErrors().put("name", "专业已存在！");
				throw new MajorExistException();
			}
			if(CheckDataValidity.checkMajorValidate(major)){
				dao.addMajor(major);
			}
		} catch (MajorExistException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void alertMajor(Major major) {

		try {
			if (dao.existMajor(major.getName())) {
				major.getErrors().put("name", "专业已存在！");
				throw new MajorExistException();
			}
			if(CheckDataValidity.checkMajorValidate(major)){
				dao.alertMajor(major);
			}
		} catch (SQLException | MajorExistException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteMajor(Integer id) {
		try {
			dao.deleteMajor(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
