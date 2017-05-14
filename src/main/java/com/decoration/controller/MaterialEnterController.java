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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.decoration.entity.MaterialEnter;
import com.decoration.service.MaterialService;
import com.decoration.service.UserService;
import com.decoration.service.UtilService;

import util.Page;

/**
 * @author zhenghan
 * 2017年4月23日 
 * 上午9:01:30
 *
 */
@RequestMapping("/enter")
@Scope("prototype")
@Controller
public class MaterialEnterController {
	@Autowired
	private MaterialService matService;
	@Autowired
	private HttpSession session;
	@Autowired
	private UtilService utilService;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public ModelAndView showEnterByPage(Page page){
		page = (Page)session.getAttribute("enterPage");
		utilService.choosePage(page);
		ModelAndView mv = matService.findAllMatEnterByPage(page);
		mv.addObject("page", "enter");
		mv.setViewName("/home");
		return mv;
	}
	
	@RequestMapping(value="/pageNumber",method = RequestMethod.POST)
	public ModelAndView showEnterByPageNumBer(Integer currentPageCode){
		Page page = (Page)session.getAttribute("enterPage");
		page.setCurrentPageCode(currentPageCode);
		ModelAndView mv = matService.findAllMatEnterByPage(page);
		mv.addObject("page", "enter");
		mv.setViewName("/home");
		return mv;
	}
	/**
	 * 跳转到添加进场材料表单
	 * @return
	 */
	@RequestMapping(value = "/addInfo", method=RequestMethod.GET)
	public ModelAndView showAddInfo(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("page","enterAddInfo");
		mv.setViewName("/home");
		return mv;
	}
	/**
	 * 执行添加进场材料
	 * @return
	 */
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public ModelAndView addEnterMat(MaterialEnter matEnter){
		ModelAndView mv = matService.saveMatEnter(matEnter);
		mv.setViewName("/home");
		return mv;
	}
	/**
	 * 跳转到修改进场材料表单
	 * @return
	 */
	@RequestMapping(value = "/updateInfo/{enterId}", method=RequestMethod.GET)
	public ModelAndView showUpdateInfo(@PathVariable int enterId){
		ModelAndView mv = new ModelAndView();
		mv.addObject("page","enterUpdateInfo");
		mv.addObject("enterUpdateId",enterId);
		mv.setViewName("/home");
		return mv;
	}
	/**
	 * 执行修改进场材料
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method=RequestMethod.POST)
	public ModelAndView updateEnterMat(MaterialEnter matEnter,@PathVariable int id){
		ModelAndView mv = new ModelAndView();
		matEnter.setEnterId(id);
		mv = matService.updateMatEnter(matEnter);
		//mv = matService.findAllMatEnter();
		mv.addObject("page","enter");
		mv.setViewName("/home");
		return mv;
	}
	/**
	 * 执行删除进场材料
	 * @return
	 */
	@RequestMapping(value = "/delete/{enterId}", method=RequestMethod.GET)
	public ModelAndView deleteEnterMat(@PathVariable int enterId){
		ModelAndView mv = matService.deleteEnterMatById(enterId);
		//mv = matService.findAllMatEnter();
		mv.addObject("page","enter");
		mv.setViewName("/home");
		return mv;
	}
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
