package com.management.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertor {

	public static java.sql.Date stringDate2SqlDate(String utilDate) {
		java.sql.Date sqlDate = null;
		
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(utilDate);
			sqlDate = new java.sql.Date(date.getTime());

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return sqlDate;
	}

}
