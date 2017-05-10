/**
 * 
 */
package com.decoration.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.decoration.dao.FlowDao;
import com.decoration.dao.ProjectDao;
import com.decoration.dao.ScheduleDao;
import com.decoration.entity.Flow;
import com.decoration.entity.Project;
import com.decoration.entity.Schedule;
import com.decoration.entity.User;
import com.decoration.service.ScheduleService;

/**
 * @author zhenghan
 * 2017年5月10日 
 * 上午11:02:12
 *
 */
@Service(value = "scheduleService")
@Scope("prototype")
public class ScheduleServiceImpl implements ScheduleService{
	@Autowired
	private ScheduleDao scheduleDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private FlowDao flowDao;
	@Override
	public ModelAndView saveSchedule(Schedule schedule,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String projectName = schedule.getScheduleProject().getProjectName();
		String flowName = schedule.getScheduleFlow().getFlowName();
		Date recordDate = new Date();
		Project project = projectDao.findProByName(projectName);
		Flow flow = flowDao.findFlowByName(flowName);
		User user = (User)session.getAttribute("loginUser");
		if(project != null && flow != null && user != null){
			schedule.setFinishedDays(0);
			schedule.setRecordDate(recordDate);
			schedule.setScheduleProject(project);
			schedule.setScheduleFlow(flow);
			schedule.setScheduleUser(user);
			scheduleDao.saveSchedule(schedule);
			mv.addObject("page","schedule");
		}else{
			mv.addObject("result",false);
			mv.addObject("page","scheduleAddInfo");
		}
		return mv;
	}


	@Override
	public ModelAndView deleteScheduleById(int scheduleId) {
		scheduleDao.deleleScheduleById(scheduleId);
		return null;
	}


	@Override
	public ModelAndView findAllSchedule() {
		ModelAndView mv = new ModelAndView();
		List<Schedule> scheduleList = scheduleDao.findAllSchedule();	
		mv.addObject("scheduleData",scheduleList);
		mv.addObject("page","schedule");
		return mv;
		
	}

}
