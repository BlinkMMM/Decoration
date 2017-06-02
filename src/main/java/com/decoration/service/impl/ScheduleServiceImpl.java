/**
 * 
 */
package com.decoration.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.decoration.dao.FlowDao;
import com.decoration.dao.ProjectDao;
import com.decoration.dao.ScheduleDao;
import com.decoration.entity.CheckSchedule;
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
	public ModelAndView updateSchedule(Schedule schedule) {
		ModelAndView mv = new ModelAndView();
		User user = (User)session.getAttribute("loginUser");
		String workContent = schedule.getWorkContent();
		int todayFinishedDays = schedule.getFinishedDays();
		System.out.println("workContent = " + workContent);
		System.out.println("todayFinishedDays = " + todayFinishedDays);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("scheduleId", schedule.getScheduleId());
		List<Schedule> scheduleList = scheduleDao.findAllSchedule(map);
		
	    schedule = scheduleList.get(0);
	    
		int finishedDays = todayFinishedDays + schedule.getFinishedDays();	
		int expectedDays = schedule.getExpectedDays();
		double scheduleRate = (double)finishedDays/(double)expectedDays;
		Date recordDate = new Date();
		
		schedule.setWorkContent(workContent);
		schedule.setFinishedDays(finishedDays);
		schedule.setRecordDate(recordDate);
		schedule.setScheduleRate(scheduleRate);
		schedule.setScheduleUser(user);
		System.out.println("schedule = " + schedule);
		scheduleDao.saveSchedule(schedule);
		mv = this.findScheduleByPageAfterOperation(mv);
		mv.addObject("page","schedule");
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
		Map<String,Object> map = new HashMap<String,Object>();
		List<Schedule> scheduleList = scheduleDao.findAllSchedule(map);	
		mv.addObject("scheduleData",scheduleList);
		mv.addObject("page","schedule");
		return mv;
		
	}
	
	@Override
	public ModelAndView findAllScheduleByPage(Page page,String searchName) {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> map = this.checkSearchNameForSchedule(searchName);
		List<Schedule> list = scheduleDao.findAllSchedule(map);
		page = new Page(list.size(),page.getCurrentPageCode());
		session.setAttribute("schedulePage", page);
		
		List<Schedule> pageList = scheduleDao.findAllScheduleByPage(page,map);
		mv.addObject("schedulePageData",pageList);
		mv.addObject("page","schedule");
		return mv;
	}
	
	/**
	 * 添加审核
	 */
	@Override
	public ModelAndView saveCheckSchedule(CheckSchedule checkSchedule) {
		ModelAndView mv = new ModelAndView();
		User checkUser = (User)session.getAttribute("loginUser");
		int scheduleId = checkSchedule.getScheduleId();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("scheduleId", scheduleId);

		List<CheckSchedule> checkScheduleList = scheduleDao.findCheckSchedule(map);
		
		if(checkScheduleList.size()==0){//说明未审核,则执行添加审核操作
			checkSchedule.setCheckDate(new Date());
			checkSchedule.setCheckUser(checkUser);	
			scheduleDao.saveCheckSchedule(checkSchedule);		
			mv = this.findScheduleByPageAfterOperation(mv);
		}else{//说明已审核，则执行修改操作
			CheckSchedule newCheckSchedule = checkScheduleList.get(0);
			
			if (checkSchedule.getCheckState() == newCheckSchedule.getCheckState()
					&& checkSchedule.getReason().equals(newCheckSchedule.getReason())
					&& checkSchedule.getResponsibleParty().equals(newCheckSchedule.getResponsibleParty())){
			}else{
				newCheckSchedule.setCheckDate(new Date());
				newCheckSchedule.setCheckUser(checkUser);
				newCheckSchedule.setCheckState(checkSchedule.getCheckState());
				newCheckSchedule.setReason(checkSchedule.getReason());
				newCheckSchedule.setResponsibleParty(checkSchedule.getResponsibleParty());
			}
			scheduleDao.updateCheckSchedule(newCheckSchedule);
			mv = this.findScheduleByPageAfterOperation(mv);
		}		
		return mv;
	}

	@Override
	public ModelAndView findCheckSchedule() {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> map = new HashMap<String,Object>();
		scheduleDao.findAllSchedule(map);
		return mv;
	}
	/**
	 * 在材料使用模块执行操作后分页查出数据
	 * @param mv
	 */
	public ModelAndView findScheduleByPageAfterOperation(ModelAndView mv){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Schedule> list = scheduleDao.findAllSchedule(map);
		Page page = (Page)session.getAttribute("schedulePage");
		Page page2 = new Page(list.size(), 1);
		int currentPage = 0;
		if(page.getTotalPages() == page2.getTotalPages()){
			currentPage = page.getTotalPages();
		}else{
			currentPage = page2.getTotalPages();
		}
		page.setCurrentPageCode(currentPage);
		mv = this.findAllScheduleByPage(page,"");
		return mv;
	}
	
	public Map<String,Object> checkSearchNameForSchedule(String searchName){
		Map<String,Object> map = new HashMap<String,Object>();
		
		Map<String,Object> projectMap = new HashMap<String,Object>();
		projectMap.put("projectName", searchName);
		List<Schedule> scheduleList = scheduleDao.findAllSchedule(projectMap);
		
		Map<String,Object> flowMap = new HashMap<String,Object>();
		flowMap.put("flowName", searchName);
		List<Schedule> scheduleList2 = scheduleDao.findAllSchedule(flowMap);
		
		if(scheduleList.size()!=0 && scheduleList2.size()==0){
			map.put("projectName", searchName);
		}else if(scheduleList.size()==0 && scheduleList2.size()!=0){
			map.put("flowName", searchName);
		}else{
			map.put("projectName",searchName);
			map.put("flowName",searchName);
		}
		return map;
	}
	
	public ModelAndView checkLevel(int scheduleId){
		ModelAndView mv = new ModelAndView();
		User user = (User)session.getAttribute("loginUser");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("scheduleId", scheduleId);
		List<Schedule> scheduleList = scheduleDao.findAllSchedule(map);
		Schedule schedule = scheduleList.get(0);
		if(user.getJobType().equals("项目经理")){
			if(schedule.getScheduleRate()<1){
				mv.addObject("result",false);
				mv.addObject("reason","进度尚未完成，无法审核！");
				mv.addObject("page","errorInfo");
			}else{
				List<CheckSchedule> checkScheduleList = scheduleDao.findCheckSchedule(map);
				CheckSchedule checkSchedule = checkScheduleList.get(0);
				mv.addObject("checkSchedule",checkSchedule);
				mv.addObject("page", "checkScheduleInfo");
				mv.addObject("checkScheduleId",scheduleId);
			}
		}else{
			mv.addObject("result",false);
			mv.addObject("reason","权限不足，无法审核！");
			mv.addObject("page","errorInfo");
		}
		return mv;
		
	}
}
