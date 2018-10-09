package com.management.utils;

import java.util.LinkedList;
import java.util.List;

public class PageUtils {

	public static Integer prePageHandler(Integer page) {

		Integer prePage;
		if (page - 1 == 0) {
			prePage = 1;
		} else {
			prePage = page - 1;
		}
		return prePage;
	}

	public static Integer nextPageHandler(Integer page, Integer listCount) {

		Integer PAGESIZE = ConstantUtils.Page.PAGESIZE;
		Integer pages = (listCount % PAGESIZE == 0) ? (listCount / PAGESIZE) : (listCount / PAGESIZE + 1);
		Integer nextPage;
		if (page == pages) {
			nextPage = pages;
		} else {
			nextPage = page + 1;
		}
		return nextPage;
	}

	public static List<Integer> pageHandler(Integer page,Integer listCount) {
		
		List<Integer> list = new LinkedList<Integer>();
		Integer PAGESIZE = ConstantUtils.Page.PAGESIZE;
		Integer PAGENUM = ConstantUtils.Page.PAGENUM;
		
		Integer pages = (listCount % PAGESIZE == 0) ? (listCount / PAGESIZE) : (listCount / PAGESIZE + 1);
		Integer minPages = (page - PAGENUM > 0) ? (page - PAGENUM) : (1);
		Integer maxPages = (page + PAGENUM >= pages)?(pages):(page + PAGENUM);
		for(int i = minPages;i<= maxPages;i++){
			list.add(i);
		}
		return list;
	}
	
	public static Integer pagesHandler(Integer listCount){
		
		Integer PAGESIZE = ConstantUtils.Page.PAGESIZE;
		Integer pages = (listCount % PAGESIZE == 0) ? (listCount / PAGESIZE) : (listCount / PAGESIZE + 1);
		return pages;
	}

}
