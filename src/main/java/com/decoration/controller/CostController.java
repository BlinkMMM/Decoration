/**
 * 
 */
package com.decoration.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.decoration.dao.FlowDao;
import com.decoration.dao.ProjectDao;
import com.decoration.entity.Flow;
import com.decoration.entity.Project;
import com.decoration.service.CostService;

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
	private ProjectDao projectDao;
	
	@RequestMapping(value="/mat",method = RequestMethod.GET)
	public ModelAndView showMatCostChoose(){
		ModelAndView mv = new ModelAndView();
		costService.chooseProjectAndFlow("costProData", "costFlowData");
		mv.addObject("page", "matCost");
		mv.setViewName("/home");
		return mv;
	}
	@RequestMapping(value="/mat2",method = RequestMethod.POST)
	public ModelAndView showMatCost(String projectName,String flowName){
		ModelAndView mv = costService.findMatCostByCondition(projectName, flowName);
		mv.addObject("page", "matCost");
		mv.setViewName("/home");
		return mv;
	}
	@RequestMapping(value="/wage",method = RequestMethod.GET)
	public ModelAndView showWageCost(){
		ModelAndView mv = costService.findWageCost();
		mv.addObject("page", "wageCost");
		mv.setViewName("/home");
		return mv;
	}
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
