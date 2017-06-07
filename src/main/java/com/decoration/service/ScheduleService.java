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
	public ModelAndView saveSchedule(Schedule schedule);
	public ModelAndView deleteScheduleById(int scheduleId);
	
	public ModelAndView updateSchedule(Schedule schedule);
	
	public ModelAndView findAllSchedule();
	
	public ModelAndView findAllScheduleByPage(Page page,String searchName);
	
	public ModelAndView saveCheckSchedule(CheckSchedule checkSchedule);
	
	public ModelAndView findCheckSchedule();
	
	public ModelAndView checkLevel(int scheduleId);
	
	public ModelAndView checkScheduleRateById(int scheduleId);
}
