/**
 * 
 */
package com.decoration.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.decoration.service.CostService;
import com.decoration.service.UtilService;

import util.Page;

/**
 * @author zhenghan
 * 2017年5月10日 
 * 下午4:38:41
 *
 */
@RequestMapping("/cost")
@Scope("prototype")
@Controller
public class CostController {
	@Autowired
	private CostService costService;
	@Autowired
	private HttpSession session;
	@Autowired
	private UtilService utilService;
	
	
	//==========材料成本===========================================================================
	@RequestMapping(value="/mat",method = RequestMethod.GET)
	public ModelAndView showMatCostChoose(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("page", "matCost");
		mv.setViewName("/home");
		return mv;
	}
	@RequestMapping(value="/mat2",method = RequestMethod.POST)
	public ModelAndView showMatCost(String projectName,String flowName,Page page){
		page.setCurrentPageCode(1);
		session.setAttribute("matProjectSelected", projectName);
		session.setAttribute("matFlowSelected", flowName);
		ModelAndView mv = costService.findMatCostByPage(page);
		mv.addObject("page", "matCost");
		mv.setViewName("/home");
		return mv;
	}
	@RequestMapping(value="/",method = RequestMethod.GET)
	public ModelAndView showMatCostByPage(Page page){
		page = (Page)session.getAttribute("matCostPage");
		utilService.choosePage(page,"matCostFrom");
		ModelAndView mv = costService.findMatCostByPage(page);
		mv.addObject("page", "matCost");
		mv.setViewName("/home");
		return mv;
	}
	
	@RequestMapping(value="/matCostPageNumber",method = RequestMethod.POST)
	public ModelAndView showMatCostByPageNumBer(Integer currentPageCode){
		Page page = (Page)session.getAttribute("matCostPage");
		page.setCurrentPageCode(currentPageCode);
		ModelAndView mv = costService.findMatCostByPage(page);
		mv.addObject("page", "matCost");
		mv.setViewName("/home");
		return mv;
	}
	//===========薪水成本==========================================================================
	@RequestMapping(value="/wage",method = RequestMethod.GET)
	public ModelAndView showWageCost(String projectName,String userName){
		ModelAndView mv = costService.findWageCostByCondition(projectName, userName);
		mv.addObject("page", "wageCost");
		mv.setViewName("/home");
		return mv;
	}
	@RequestMapping(value="/wageByPage",method = RequestMethod.POST)
	public ModelAndView showWageCostByPage(String projectName,String userName,Page page){
		page.setCurrentPageCode(1);
		session.setAttribute("wageProjectSelected", projectName);
		session.setAttribute("wageUserSelected", userName);
		ModelAndView mv = costService.findWageCostByPage(page);
		mv.addObject("page", "wageCost");
		mv.setViewName("/home");
		return mv;
	}
	@RequestMapping(value="/wage/",method = RequestMethod.GET)
	public ModelAndView showWageByPage(Page page){
		page = (Page)session.getAttribute("wageCostPage");
		utilService.choosePage(page,"wageCostFrom");
		ModelAndView mv = costService.findWageCostByPage(page);
		mv.addObject("page", "wageCost");
		mv.setViewName("/home");
		return mv;
	}
	
	@RequestMapping(value="/wageCostPageNumber",method = RequestMethod.POST)
	public ModelAndView showWageByPageNumBer(Integer currentPageCode){
		Page page = (Page)session.getAttribute("wageCostPage");
		page.setCurrentPageCode(currentPageCode);
		ModelAndView mv = costService.findWageCostByPage(page);
		mv.addObject("page", "wageCost");
		mv.setViewName("/home");
		return mv;
	}
	//============总成本=========================================================================
	@RequestMapping(value="/total",method = RequestMethod.GET)
	public ModelAndView showTotalCost(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("page", "totalCost");
		mv.setViewName("/home");
		return mv;
	}
	
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
