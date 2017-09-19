package com.decoration.service;

import org.springframework.web.servlet.ModelAndView;

import com.decoration.entity.CheckSchedule;
import com.decoration.entity.Schedule;

import util.Page;

/**
 * @author zhenghan
 * 2017年5月9日 
 * 下午6:34:40
 *
 */
public interface ScheduleService {
	ModelAndView saveSchedule(Schedule schedule);

	ModelAndView deleteScheduleById(int scheduleId);
	
	ModelAndView updateSchedule(Schedule schedule);
	
	ModelAndView findAllSchedule();
	
	ModelAndView findAllScheduleByPage(Page page,String searchName);
	
	ModelAndView saveCheckSchedule(CheckSchedule checkSchedule);
	
	ModelAndView findCheckSchedule();
	
	ModelAndView checkLevel(int scheduleId);
	
	ModelAndView checkScheduleRateById(int scheduleId);
}
