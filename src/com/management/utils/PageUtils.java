package com.management.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * 分页工具
 * @author CheungChingYin
 *
 */
public class PageUtils {

	/**
	 * 上一页逻辑
	 * @param page
	 * @return
	 */
	public static Integer prePageHandler(Integer page) {

		Integer prePage;
		if (page - 1 == 0) {//如果当前页-1为0，则表示当前页为第一页，
			prePage = 1;//前一页只能为第一页
		} else {
			prePage = page - 1;
		}
		return prePage;
	}

	/**
	 * 下一页逻辑
	 * @param page
	 * @param listCount
	 * @return
	 */
	public static Integer nextPageHandler(Integer page, Integer listCount) {

		Integer PAGESIZE = ConstantUtils.Page.PAGESIZE;
		Integer pages = (listCount % PAGESIZE == 0) ? (listCount / PAGESIZE) : (listCount / PAGESIZE + 1);
		Integer nextPage;
		if (page == pages) {//如果当前页等于最大页数
			nextPage = pages;//下一页只能等于最大页数
		} else {
			nextPage = page + 1;
		}
		return nextPage;
	}

	/**
	 * 列出页码列表
	 * @param page
	 * @param listCount
	 * @return
	 */
	public static List<Integer> pageHandler(Integer page,Integer listCount) {
		
		List<Integer> list = new LinkedList<Integer>();
		Integer PAGESIZE = ConstantUtils.Page.PAGESIZE;
		Integer PAGENUM = ConstantUtils.Page.PAGENUM;
		
		Integer pages = (listCount % PAGESIZE == 0) ? (listCount / PAGESIZE) : (listCount / PAGESIZE + 1);
		Integer minPages = (page - PAGENUM > 0) ? (page - PAGENUM) : (1);//和上一页同理
		Integer maxPages = (page + PAGENUM >= pages)?(pages):(page + PAGENUM);//与下一页同理
		for(int i = minPages;i<= maxPages;i++){
			list.add(i);//添加最小页到最大页之间的页码
		}
		return list;
	}
	
	/**
	 * 总共页数逻辑
	 * @param listCount
	 * @return
	 */
	public static Integer pagesHandler(Integer listCount){
		
		Integer PAGESIZE = ConstantUtils.Page.PAGESIZE;
		Integer pages = (listCount % PAGESIZE == 0) ? (listCount / PAGESIZE) : (listCount / PAGESIZE + 1);
		return pages;
	}

}
