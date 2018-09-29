package com.management.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.management.dao.CollegeDao;
import com.management.dao.impl.CollegeDaoImpl;
import com.management.entities.College;
import com.management.exception.CollegeIsExistException;
import com.management.service.CollegeService;
import com.management.utils.CheckDataValidity;

public class CollegeServiceImpl implements CollegeService {

	private CollegeDao dao = new CollegeDaoImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.management.service.CollegeService#searchAllCollege()
	 */
	@Override
	public List<College> searchAllCollege() {

		List<College> list = null;
		try {
			list = dao.queryAllCollege();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.management.service.CollegeService#searchCollegeById(java.lang.
	 * Integer)
	 */
	@Override
	public College searchCollegeById(Integer id) {

		College college = null;
		try {
			college = dao.queryCollegeById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return college;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.management.service.CollegeService#searchCollegeByName(java.lang.
	 * String)
	 */
	@Override
	public College searchCollegeByName(String name) {

		College college = null;
		try {
			college = dao.queryCollegeByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return college;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.management.service.CollegeService#RegistCollege(com.management.
	 * entities.College)
	 */
	@Override
	public void RegistCollege(College college) {
		try {
			if (dao.existCollege(college.getName())) {
				college.getErrors().put("college", "学院名已存在");
				throw new CollegeIsExistException();
			}
			if(CheckDataValidity.checkCollegeValidate(college)){
				dao.addCollege(college);
			}
			
		} catch (CollegeIsExistException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.management.service.CollegeService#AlertCollege(com.management.
	 * entities.College)
	 */
	@Override
	public void AlertCollege(College college) {

		try {
			if (dao.existCollege(college.getName())) {
				college.getErrors().put("college", "学院名已存在");
				throw new CollegeIsExistException();
			}
			if(CheckDataValidity.checkCollegeValidate(college)){
				dao.alertCollege(college);;
			}
		} catch (SQLException | CollegeIsExistException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.management.service.CollegeService#deleteCollege(java.lang.Integer)
	 */
	@Override
	public void deleteCollege(Integer id) {
		try {
			dao.deleteCollege(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
