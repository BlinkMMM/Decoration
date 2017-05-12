/**
 * 
 */
package com.decoration.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhenghan
 * 2017年5月10日 
 * 下午4:15:52
 *
 */
public interface CostService {
	public ModelAndView findMatCostByProjectId(int projctId);
	public void chooseProjectAndFlow(String proData,String flowData);
	
	public ModelAndView findWageCost();
}
