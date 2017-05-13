/**
 * 
 */
package com.decoration.service;

import org.springframework.web.servlet.ModelAndView;

import com.decoration.bean.MaterialBean;

/**
 * @author zhenghan
 * 2017年5月10日 
 * 下午4:15:52
 *
 */
public interface CostService {
	public ModelAndView findMatCostByCondition(String projectName,String flowName);
	public void chooseProjectAndFlow(String proData,String flowData);
	
	public ModelAndView findWageCost();
	
	public ModelAndView findTotalCost();
}
