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

import com.decoration.entity.CheckSchedule;
import com.decoration.entity.Schedule;
import com.decoration.service.MaterialService;
import com.decoration.service.ScheduleService;
import com.decoration.service.UserService;
import com.decoration.service.UtilService;

import util.Page;

/**
 * @author zhenghan
 * 2017年5月9日 
 * 下午6:31:05
 *
 */
@RequestMapping("/schedule")
@Scope("prototype")
@Controller
public class ScheduleController {
		@Autowired
		private UserService userService;
		@Autowired
		private MaterialService matService;
		@Autowired
		private ScheduleService scheduleService;
		@Autowired
		private HttpSession session;
		@Autowired
		private UtilService utilService;
		
		/**
		 * 跳转到工程进度页面
		 * @return
		 */
		@RequestMapping(value="/schedule",method = RequestMethod.GET)
		public ModelAndView showSchedule(Page page){
			page.setCurrentPageCode(1);
			ModelAndView mv = scheduleService.findAllScheduleByPage(page,"");
			mv.setViewName("/home");
			return mv;
		}
		@RequestMapping(value="/",method = RequestMethod.GET)
		public ModelAndView showScheduleByPage(Page page){
			page = (Page)session.getAttribute("schedulePage");
			utilService.choosePage(page,"scheduleFrom");
			ModelAndView mv = scheduleService.findAllScheduleByPage(page,"");
			mv.addObject("page", "schedule");
			mv.setViewName("/home");
			return mv;
		}
		
		@RequestMapping(value="/pageNumber",method = RequestMethod.POST)
		public ModelAndView showBuyByPageNumBer(Integer currentPageCode){
			Page page = (Page)session.getAttribute("schedulePage");
			page.setCurrentPageCode(currentPageCode);
			ModelAndView mv = scheduleService.findAllScheduleByPage(page,"");
			mv.addObject("page", "schedule");
			mv.setViewName("/home");
			return mv;
		}
		
		@RequestMapping(value="/scheduleSearch",method = RequestMethod.POST)
		public ModelAndView showMatBySearch(String searchName){
			Page page = (Page)session.getAttribute("schedulePage");
			page.setCurrentPageCode(1);
			ModelAndView mv = scheduleService.findAllScheduleByPage(page,searchName);
			mv.addObject("page", "schedule");
			mv.setViewName("/home");
			return mv;
		}
		/**
		 * 跳转到添加工程进度页面
		 * @return
		 */
		@RequestMapping(value = "/addScheduleInfo", method=RequestMethod.GET)
		public ModelAndView showScheduleInfo(){
			ModelAndView mv = new ModelAndView();
			mv.addObject("page","scheduleAddInfo");
			mv.setViewName("/home");
			return mv;
		}
		/**
		 * 执行添加工程进度操作
		 * @return
		 */
		@RequestMapping(value = "/saveSchedule", method=RequestMethod.POST)
		public ModelAndView saveSchedule(Schedule schedule){
			ModelAndView mv = scheduleService.saveSchedule(schedule);
			mv.setViewName("/home");
			return mv;
		}
		/**
		 * 执行删除操作
		 * @param 
		 * @return
		 */
		@RequestMapping(value = "/delete/{scheduleId}", method = RequestMethod.GET)
		public ModelAndView deleteMatPurById(@PathVariable int scheduleId) {
			ModelAndView mv = scheduleService.deleteScheduleById(scheduleId);
			mv.addObject("page", "schedule");
			mv.setViewName("/home");
			return mv;
		}
		/**
		 * 跳转到更新进度页面
		 * @param binder
		 */
		@RequestMapping(value = "/updateInfo/{scheduleId} ", method = RequestMethod.GET)
		public ModelAndView updateScheduleInfo(@PathVariable int scheduleId) {
			ModelAndView mv = new ModelAndView();
			mv = scheduleService.checkScheduleRateById(scheduleId);
			mv.addObject("scheduleUpdateId",scheduleId);
			mv.setViewName("/home");
			return mv;
		}
		/**
		 * 执行更新项目进度操作并跳转到展示项目进度
		 * @param 
		 * @return
		 */
		@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
		public ModelAndView updateSchedule(Schedule schedule,@PathVariable int id) {
			ModelAndView mv = new ModelAndView();
			schedule.setScheduleId(id);
			mv = scheduleService.updateSchedule(schedule);
			mv.setViewName("/home");
			return mv;
		}

		/**
		 * 跳转到审核进度页面
		 * @param binder
		 */
		@RequestMapping(value = "/checkInfo/{scheduleId} ", method = RequestMethod.GET)
		public ModelAndView checkScheduleInfo(@PathVariable int scheduleId) {
			ModelAndView mv = new ModelAndView();
			mv = scheduleService.checkLevel(scheduleId);
			mv.setViewName("/home");
			return mv;
		}
		
		/**
		 * 执行审核项目进度操作并跳转到展示项目进度
		 * @param 
		 * @return
		 */
		@RequestMapping(value = "/check/{id}", method = RequestMethod.POST)
		public ModelAndView checkSchedule(CheckSchedule checkSchedule,@PathVariable int id) {
			ModelAndView mv = new ModelAndView();
			checkSchedule.setScheduleId(id);//点击审核按钮，传入该该条记录的schedulleId
			mv = scheduleService.saveCheckSchedule(checkSchedule);
			mv.addObject("page", "schedule");
			mv.setViewName("/home");
			return mv;
		}
		
		@InitBinder
		protected void initBinder(WebDataBinder binder) {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		}
	}
