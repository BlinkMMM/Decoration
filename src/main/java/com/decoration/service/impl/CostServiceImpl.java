/**
 * 
 */
package com.decoration.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.decoration.bean.MaterialCostBean;
import com.decoration.bean.TotalCostBean;
import com.decoration.bean.WageCostBean;
import com.decoration.dao.CostDao;
import com.decoration.dao.FlowDao;
import com.decoration.dao.MaterialDao;
import com.decoration.dao.ProjectDao;
import com.decoration.entity.Project;
import com.decoration.service.CostService;

import util.Page;

/**
 * @author zhenghan
 * 2017年5月10日 
 * 下午4:16:40
 *
 */
@Service(value = "costService")
@Scope("prototype")
public class CostServiceImpl implements CostService{
	@Autowired
	private MaterialDao matDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private FlowDao flowDao;
	@Autowired
	private CostDao costDao;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession session;
	
	@Override
	public ModelAndView findMatCostByCondition(String projectName,String flowName) {
		ModelAndView mv = new ModelAndView();
		List<MaterialCostBean> matCostList= matDao.findMatCostByCondition(projectName,flowName);
		mv.addObject("matCostData",matCostList);
		return mv;
	}
	
	@Override
	public ModelAndView findMatCostByPage(Page page) {
		ModelAndView mv = new ModelAndView();
		
		String projectName = (String)session.getAttribute("matProjectSelected");
		String flowName = (String)session.getAttribute("matFlowSelected");

		List<MaterialCostBean> list= matDao.findMatCostByCondition(projectName,flowName);
		page = new Page(list.size(),page.getCurrentPageCode());
		session.setAttribute("matCostPage", page);
		
		List<MaterialCostBean> pageList = matDao.findMatCostByPage(projectName,flowName,page);
		mv.addObject("matCostPageData",pageList);
		return mv;
	}
	
	@Override
	public ModelAndView findWageCost() {
		ModelAndView mv = new ModelAndView();
		List<WageCostBean> wageCostList = costDao.findWageCost();
		mv.addObject("wageCostData",wageCostList);
		return mv;
	}
	
	@Override
	public ModelAndView findWageCostByCondition(String projectName, String userName) {
		ModelAndView mv = new ModelAndView();
		List<WageCostBean> wageCostList = costDao.findWageCostByCondition(projectName, userName);
		mv.addObject("wageCostData",wageCostList);
		return mv;
	}

	@Override
	public ModelAndView findWageCostByPage(Page page) {
		ModelAndView mv = new ModelAndView();
		
		String projectName = (String)session.getAttribute("wageProjectSelected");
		String userName = (String)session.getAttribute("wageUserSelected");

		List<WageCostBean> list= costDao.findWageCostByCondition(projectName,userName);
		page = new Page(list.size(),page.getCurrentPageCode());
		session.setAttribute("wageCostPage", page);
		
		List<WageCostBean> pageList = costDao.findWageCostByPage(projectName, userName, page);
		mv.addObject("wageCostPageData",pageList);
		return mv;
	}
	
	@Override
	public ModelAndView findTotalCost(String searchName) {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("projectName", searchName);
//		map.put("customer", searchName);
		map = this.checkSearchName(searchName);
		List<Project> projectList = projectDao.findAllProjectByCondition(map);
		List<TotalCostBean> totalCostList = new ArrayList<TotalCostBean>();
		for(Project p:projectList){
			TotalCostBean totalCostBean = this.getTotalCost(p);
			totalCostList.add(totalCostBean);
		}
		mv.addObject("totalCostData",totalCostList);
		return mv;
	}
	
	public double getAllWageCOst(String projectName){
		double allWageCost = 0;
		List<WageCostBean> wageList = costDao.findWageCostByCondition(projectName, "");
		for(WageCostBean w:wageList){
			allWageCost = allWageCost + w.getSingleWage();
		}
		return allWageCost;
	}
	
	public TotalCostBean getTotalCost(Project project){
		TotalCostBean totalCostBean = new TotalCostBean();
		double totalCost = 0;
		String projectName = project.getProjectName();
		MaterialCostBean matCostBean = matDao.findAllMatCostByProjectName(projectName);
		double allMatCost;
		if(matCostBean == null){
			allMatCost = 0;
		}else{
			allMatCost = matCostBean.getAllMatCost();
		}
		double allWageCost = this.getAllWageCOst(projectName);
		
		totalCost = allWageCost + allMatCost;
		totalCostBean = new TotalCostBean(allMatCost, allWageCost, totalCost, project);
		return totalCostBean;
		
	}
	
	public Map<String,Object> checkSearchName(String searchName){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> projectMap = new HashMap<String,Object>();
		projectMap.put("projectName", searchName);
		List<Project> projectList = projectDao.findAllProjectByCondition(projectMap);
		
		Map<String,Object> customerMap = new HashMap<String,Object>();
		customerMap.put("customer", searchName);
		List<Project> projectList2 = projectDao.findAllProjectByCondition(customerMap);
		
		if(projectList.size()!=0 && projectList2.size()==0){
			map.put("projectName", searchName);
		}else if(projectList.size()==0 && projectList2.size()!=0){
			map.put("customer", searchName);
		}else{
			map.put("projectName",searchName);
			map.put("customer",searchName);
		}
		return map;
	}

}
