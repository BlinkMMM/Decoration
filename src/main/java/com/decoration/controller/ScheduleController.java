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
import com.decoration.entity.Schedule;
import com.decoration.service.MaterialService;
import com.decoration.service.ScheduleService;
import com.decoration.service.UserService;

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
		
		/**
		 * 跳转到工程进度页面
		 * @return
		 */
		@RequestMapping(value="/schedule",method = RequestMethod.GET)
		public ModelAndView showSchedule(){
			ModelAndView mv = scheduleService.findAllSchedule();
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
		public ModelAndView saveSchedule(Schedule schedule,HttpSession session){
			ModelAndView mv = scheduleService.saveSchedule(schedule,session);
			mv.setViewName("/home");
			return mv;
		}
		/**
		 * 跳转到更新工程进度表单
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
			mv = matService.findAllMatUse();
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
			mv = matService.findAllMatUse();
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
