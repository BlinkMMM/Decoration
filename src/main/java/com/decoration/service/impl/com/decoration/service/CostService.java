/**
 * 
 */
package com.decoration.service;

import org.springframework.web.servlet.ModelAndView;
import util.Page;

/**
 * @author zhenghan
 * 2017年5月10日 
 * 下午4:15:52
 *
 */
public interface CostService {
	/*
	 * 材料成本
	 */
	public ModelAndView findMatCostByCondition(String projectName,String flowName);
	
	public ModelAndView findMatCostByPage(Page page);
	
	
	/*
	 * 薪水成本
	 */
	public ModelAndView findWageCost();
	public ModelAndView findWageCostByCondition(String projectName,String userName);
	public ModelAndView findWageCostByPage(Page page);
	
	
	/*
	 * 总成本
	 */
	public ModelAndView findTotalCost(String searchName);
	

}
