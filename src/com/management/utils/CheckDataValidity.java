package com.management.utils;

import java.sql.Date;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.management.entities.Administrator;
import com.management.entities.College;
import com.management.entities.Major;
import com.management.entities.Student;

/**
 * 提交信息合法性检查
 * @author CheungChingYin
 *
 */
public class CheckDataValidity {

	/**
	 * 检查管理员数据合法性
	 * @param admin
	 * @return
	 */
	public static boolean administratorValidate(Administrator admin) {
		boolean isOk = true;
		// 1.用户名不能为空
		if (admin.getUser() == null || admin.getUser().trim().equals("")) {
			isOk = false;
			admin.getError().put("user", "用户名不能为空");
		}
		// 2.密码需要为字母数字混合,限制为6-14位
		if (admin.getPassword() == null || admin.getPassword().trim().equals("")) {
			isOk = false;
			admin.getError().put("password", "密码不能为空");
		} else if (!admin.getPassword().matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,14}$")) {

			/*
			 * (?![0-9]+$) 预测该位置后面不全是数字 (?![a-zA-Z]+$) 预测该位置后面不全是字母 [0-9A-Za-z]
			 * {8,16} 由8-16位数字或这字母组成
			 *
			 * 参考地址：https://blog.csdn.net/u011974797/article/details/71479456
			 */
			isOk = false;
			admin.getError().put("password", "密码需要为字母数字混合,限制为6-14位");
		}
		// 管理员权限必须为数字且不能为空
		if (admin.getPermission() == null) {
			isOk = false;
			admin.getError().put("permission", "管理员权限不能为空");
		} else if (admin.getPermission().toString().matches("[0-1]{1,1}"))
			;
		return isOk;
	}

	/**
	 * 检查学生数据合法性
	 * @param stu
	 * @return
	 */
	public static boolean checkStudentValidate(Student stu) {

		String id = stu.getId();
		String name = stu.getName();
		Integer sex = stu.getSex();
		Date birth = stu.getBirth();
		Date schoolDay = stu.getSchoolDay();
		Integer major_id = stu.getMajor_id();
		Integer college_id = stu.getCollege_id();

		boolean isOk = true;
		// 1.用户id不能为空且id位数需要为15位
		if (id == null || id.trim().equals("")) {
			isOk = false;
			stu.getErrors().put("id", "学生学号不能为空");
		} else if (!id.matches("\\d{15,15}")) {
			isOk = false;
			stu.getErrors().put("id", "学生学号必须为15位");
		}
		// 2.姓名不能为空，必须是汉字或者是英文
		if (name == null || name.trim().equals("")) {
			isOk = false;
			stu.getErrors().put("name", "学生姓名不能为空");
		} else if (!name.matches("[\u4e00-\u9fa5_a-zA-Z_]+")) {
			isOk = false;
			stu.getErrors().put("name", "学生姓名必须为中文或者是英文");
		}
		// 3.性别不能为空，必须为0-1
		if (sex == null) {
			isOk = false;
			stu.getErrors().put("sex", "学生性别不能为空");
		} else if (!sex.toString().matches("[0-1]{1,1}")) {
			isOk = false;
			stu.getErrors().put("sex", "学生性别格式错误");
		}
		// 4.出生日期不能为空
		if (birth == null) {
			isOk = false;
			stu.getErrors().put("birth", "学生出生日期不能为空！");
		}
		try {
			DateLocaleConverter dlc = new DateLocaleConverter();
			dlc.convert(birth, "yyyy-MM-dd");
		} catch (Exception e) {
			isOk = false;
			stu.getErrors().put("birth", "日期格式错误");
		}
		// 5.入学日期不能为空
		if (schoolDay == null) {
			isOk = false;
			stu.getErrors().put("schoolDay", "学生入学日期不能为空！");
		}
		try {
			DateLocaleConverter dlc = new DateLocaleConverter();
			dlc.convert(birth, "yyyy-MM-dd");
		} catch (Exception e) {
			isOk = false;
			stu.getErrors().put("schoolDay", "日期格式错误");
		}
		// 6.专业不能为空
		if (major_id == null) {
			stu.getErrors().put("major_id", "专业id不能为空");
		}
		// 7.学院名称不能为空
		if (college_id == null) {
			stu.getErrors().put("college_id", "学院id不能为空");
		}
		return isOk;
	}

	/**
	 * 检查专业数据合法性
	 * @param major
	 * @return
	 */
	public static boolean checkMajorValidate(Major major) {

		boolean isOk = true;

		if (major.getName() == null || major.getName().trim().equals("")) {
			isOk = false;
		} else if (!major.getName().matches("^[\u4e00-\u9fa5_a-zA-Z0-9]+$")) {
			isOk = false;
		}
		if (major.getCollege_id() == null || major.getCollege_id().toString().equals("")) {
			isOk = false;
		} else if (!major.getCollege_id().toString().matches("[0-9]+")) {
			isOk = false;
		}

		return isOk;
	}

	/**
	 * 检查学院数据合法性
	 * @param college
	 * @return
	 */
	public static boolean checkCollegeValidate(College college) {

		boolean isOk = true;

		if (college.getName() == null || college.getName().trim().equals("")) {
			college.getErrors().put("name", "用户名不能为空！");
			isOk = false;
		} else if (!college.getName().matches("^[\u4e00-\u9fa5_a-zA-Z]+$")){
			college.getErrors().put("name", "用户名必须为中文或者是英文");
			isOk = false;
		}
		return isOk;
	}
}
