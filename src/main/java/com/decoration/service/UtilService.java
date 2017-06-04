/**
 * 
 */
package com.decoration.service;

import java.util.Date;

import org.springframework.web.servlet.ModelAndView;

import util.Page;

/**
 * @author zhenghan
 * 2017年5月13日 
 * 下午1:54:23
 *
 */
public interface UtilService {
	public void chooseProjectAndFlow(String proData, String flowData);
	
	public boolean checkDateIsValid(Date InputDate);
	
	public ModelAndView choosePage(Page page ,String parameter);
	
	public void initChooseProjectAndFlow();
	
	public Page initPage(String type);
}
