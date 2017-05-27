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

import com.decoration.entity.MaterialUse;
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
@RequestMapping("/use")
@Scope("prototype")
@Controller
public class MaterialUseController {

	@Autowired
	private UserService userService;
	@Autowired
	private MaterialService matService;
	@Autowired
	private UtilService utilService;
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public ModelAndView showUseByPage(Page page){
		page = (Page)session.getAttribute("usePage");
		utilService.choosePage(page,"useFrom");
		ModelAndView mv = matService.findAllMatUseByPage(page,"");
		mv.addObject("page", "use");
		mv.setViewName("/home");
		return mv;
	}
	
	@RequestMapping(value="/pageNumber",method = RequestMethod.POST)
	public ModelAndView showUseByPageNumBer(Integer currentPageCode){
		Page page = (Page)session.getAttribute("usePage");
		page.setCurrentPageCode(currentPageCode);
		ModelAndView mv = matService.findAllMatUseByPage(page,"");
		mv.addObject("page", "use");
		mv.setViewName("/home");
		return mv;
	}
	
	@RequestMapping(value="/matSearch",method = RequestMethod.POST)
	public ModelAndView showMatBySearch(String searchName){
		Page page = (Page)session.getAttribute("usePage");
		page.setCurrentPageCode(1);
		ModelAndView mv = matService.findAllMatUseByPage(page,searchName);
		mv.addObject("page", "use");
		mv.setViewName("/home");
		return mv;
	}
	/**
	 * 跳转到添加材料使用表单
	 * @return
	 */
	@RequestMapping(value = "/addInfo", method=RequestMethod.GET)
	public ModelAndView showUseInfo(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("page","useAddInfo");
		mv.setViewName("/home");
		return mv;
	}
	/**
	 * 执行添加材料使用操作
	 * @return
	 */
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public ModelAndView addUseMat(MaterialUse matUse){
		ModelAndView mv = matService.saveMatUse(matUse);
		mv.setViewName("/home");
		return mv;
	}
	/**
	 * 跳转到修改使用材料表单
	 * @return
	 */
	@RequestMapping(value = "/updateInfo/{useId}", method=RequestMethod.GET)
	public ModelAndView showUpdateInfo(@PathVariable int useId){
		ModelAndView mv = new ModelAndView();
		mv.addObject("page","useUpdateInfo");
		mv.addObject("useUpdateId",useId);
		mv.setViewName("/home");
		return mv;
	}
	/**
	 * 执行修改进场材料
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method=RequestMethod.POST)
	public ModelAndView updateUseMat(MaterialUse matUse,@PathVariable int id){
		ModelAndView mv = new ModelAndView();
		matUse.setUseId(id);
		mv = matService.updateMatUse(matUse);
		//mv = matService.findAllMatUse();
		mv.addObject("page","use");
		mv.setViewName("/home");
		return mv;
	}
	/**
	 * 执行删除进场材料
	 * @return
	 */
	@RequestMapping(value = "/delete/{useId}", method=RequestMethod.GET)
	public ModelAndView deleteUseMat(@PathVariable int useId){
		ModelAndView mv = matService.deleteUseMatById(useId);
		//mv = matService.findAllMatUse();
		mv.addObject("page","use");
		mv.setViewName("/home");
		return mv;
	}
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
