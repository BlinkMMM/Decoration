/**
 * 
 */
package com.decoration.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.decoration.entity.Schedule;

/**
 * @author zhenghan
 * 2017年5月9日 
 * 下午6:34:40
 *
 */
public interface ScheduleService {
	public ModelAndView saveSchedule(Schedule schedule,HttpSession session);
	public ModelAndView deleteScheduleById(int scheduleId);
	public ModelAndView findAllSchedule();
}
