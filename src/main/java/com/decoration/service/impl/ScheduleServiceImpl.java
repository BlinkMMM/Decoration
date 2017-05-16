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

import com.decoration.bean.MaterialBean;
import com.decoration.dao.FlowDao;
import com.decoration.dao.ProjectDao;
import com.decoration.dao.ScheduleDao;
import com.decoration.entity.Flow;
import com.decoration.entity.MaterialUse;
import com.decoration.entity.Project;
import com.decoration.entity.Schedule;
import com.decoration.entity.User;
import com.decoration.service.ScheduleService;

import util.Page;

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
	@Autowired
	private HttpSession session;
	@Override
	public ModelAndView saveSchedule(Schedule schedule) {
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
			mv = this.findScheduleByPageAfterOperation(mv);
			mv.addObject("page","schedule");
		}else{
			mv.addObject("result",false);
			mv.addObject("page","scheduleAddInfo");
		}
		return mv;
	}


	@Override
	public ModelAndView deleteScheduleById(int scheduleId) {
		ModelAndView mv = new ModelAndView();
		scheduleDao.deleleScheduleById(scheduleId);
		mv = this.findScheduleByPageAfterOperation(mv);
		return mv;
	}


	@Override
	public ModelAndView findAllSchedule() {
		ModelAndView mv = new ModelAndView();
		List<Schedule> scheduleList = scheduleDao.findAllSchedule();	
		mv.addObject("scheduleData",scheduleList);
		mv.addObject("page","schedule");
		return mv;
		
	}
	
	@Override
	public ModelAndView findAllScheduleByPage(Page page) {
		ModelAndView mv = new ModelAndView();
		List<Schedule> list = scheduleDao.findAllSchedule();
		page = new Page(list.size(),page.getCurrentPageCode());
		session.setAttribute("schedulePage", page);
		
		List<Schedule> pageList = scheduleDao.findAllScheduleByPage(page);
		mv.addObject("schedulePageData",pageList);
		mv.addObject("page","schedule");
		return mv;
	}
	
	/**
	 * 在材料使用模块执行操作后分页查出数据
	 * @param mv
	 */
	public ModelAndView findScheduleByPageAfterOperation(ModelAndView mv){
		List<Schedule> list = scheduleDao.findAllSchedule();
		Page page = (Page)session.getAttribute("schedulePage");
		Page page2 = new Page(list.size(), 1);
		int currentPage = 0;
		if(page.getTotalPages() == page2.getTotalPages()){
			currentPage = page.getTotalPages();
		}else{
			currentPage = page2.getTotalPages();
		}
		page.setCurrentPageCode(currentPage);
		mv = this.findAllScheduleByPage(page);
		return mv;
	}
}
