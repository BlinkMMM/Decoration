/**
 * 
 */
package com.decoration.service;

import org.springframework.web.servlet.ModelAndView;
import util.Page;

import java.util.Date;

/**
 * @author zhenghan
 * 2017年5月13日 
 * 下午1:54:23
 *
 */
public interface UtilService {
	void chooseProjectAndFlow(String proData, String flowData);
	
	boolean checkDateIsValid(Date InputDate);
	
	ModelAndView choosePage(Page page ,String parameter);
	
	void initChooseProjectAndFlow();
	
	Page initPage(String type);
}
