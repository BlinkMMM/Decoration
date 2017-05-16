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

import com.decoration.entity.WorkRecord;
import com.decoration.service.UtilService;
import com.decoration.service.WorkRecordService;

import util.Page;

/**
 * @author zhenghan
 * 2017年4月2日 
 * 上午11:58:14
 *
 */
@RequestMapping("/work")
@Scope("prototype")
@Controller
public class WorkRecordController {
	@Autowired
	private WorkRecordService recordService;
	@Autowired
	private HttpSession session;
	@Autowired
	private UtilService utilService;
	/**
	 * 跳转到员工考勤页面
	 * @return
	 */
	@RequestMapping(value="/record",method = RequestMethod.GET)
	public ModelAndView showRecord(Page page){
		page.setCurrentPageCode(1);
		ModelAndView mv = recordService.findUserAllRecordByUserIdByPage(page);
		mv.addObject("page","record");
		mv.setViewName("/home");
		return mv;
	}
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public ModelAndView showRecordByPage(Page page){
		page = (Page)session.getAttribute("recordPage");
		utilService.choosePage(page,"recordFrom");
		ModelAndView mv = recordService.findUserAllRecordByUserIdByPage(page);
		mv.addObject("page", "record");
		mv.setViewName("/home");
		return mv;
	}
	
	@RequestMapping(value="/pageNumber",method = RequestMethod.POST)
	public ModelAndView showRecordByPageNumBer(Integer currentPageCode){
		Page page = (Page)session.getAttribute("recordPage");
		page.setCurrentPageCode(currentPageCode);
		ModelAndView mv = recordService.findUserAllRecordByUserIdByPage(page);
		mv.addObject("page", "record");
		mv.setViewName("/home");
		return mv;
	}
	/**
	 * 跳转到添加考勤页面
	 * @return
	 */
	@RequestMapping(value="/recordInfo",method = RequestMethod.GET)
	public ModelAndView showRecordInfo(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("page","recordAddInfo");
		mv.setViewName("/home");
		return mv;
	}
	/**
	 * 执行添加考勤操作
	 * @param workRecord
	 * @return
	 */
	@RequestMapping(value="/saveRecord",method = RequestMethod.POST)
	public ModelAndView saveRecord(WorkRecord workRecord){
		ModelAndView mv = recordService.saveWorkRecord(workRecord);
		mv.setViewName("/home");
		return mv;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
