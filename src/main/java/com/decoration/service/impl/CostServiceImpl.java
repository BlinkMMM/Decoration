/**
 * 
 */
package com.decoration.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.decoration.bean.MaterialBean;
import com.decoration.bean.MaterialCostBean;
import com.decoration.bean.WageCostBean;
import com.decoration.dao.CostDao;
import com.decoration.dao.FlowDao;
import com.decoration.dao.MaterialDao;
import com.decoration.dao.ProjectDao;
import com.decoration.entity.Flow;
import com.decoration.entity.Project;
import com.decoration.service.CostService;

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
	private HttpSession session;
	@Autowired
	private HttpServletRequest request;
	@Override
	public ModelAndView findMatCostByCondition(String projectName,String flowName) {
		ModelAndView mv = new ModelAndView();
		MaterialBean matBean = new MaterialBean();
		Project project = projectDao.findProByName(projectName);
		Flow flow = flowDao.findFlowByName(flowName);
		matBean.setMatProject(project);
		matBean.setMatFlow(flow);
		List<MaterialCostBean> matCostList= matDao.findMatCostByCondition(matBean);
		mv.addObject("matCostData",matCostList);
		return mv;
	}

	@Override
	public void chooseProjectAndFlow(String proData, String flowData) {
		List<Project> projectList = projectDao.findAllProject();
		request.setAttribute(proData,projectList);
		List<Flow> flowList = flowDao.findAllFlow();
		request.setAttribute(flowData,flowList);
	}

	@Override
	public ModelAndView findWageCost() {
		ModelAndView mv = new ModelAndView();
		List<WageCostBean> wageCostList = costDao.findWageCost();
		mv.addObject("wageCostData",wageCostList);
		return mv;
	}

	@Override
	public ModelAndView findTotalCost() {
		return null;
	}

}
