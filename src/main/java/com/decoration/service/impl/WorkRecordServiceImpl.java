/**
 * 
 */
package com.decoration.service.impl;

import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.decoration.dao.ProjectDao;
import com.decoration.dao.UserDao;
import com.decoration.dao.WorkRecordDao;
import com.decoration.entity.Project;
import com.decoration.entity.User;
import com.decoration.entity.WorkRecord;
import com.decoration.service.WorkRecordService;

/**
 * @author zhenghan
 * 2017年4月2日 
 * 下午12:00:38
 *
 */
@Service(value = "workRecordService")
@Scope("prototype")
public class WorkRecordServiceImpl implements WorkRecordService{
	@Autowired
	private WorkRecordDao recordDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProjectDao projectDao;
	@Override
	public ModelAndView saveWorkRecord(WorkRecord workRecord,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String proName = workRecord.getRecordProject().getProjectName();
		User user = (User)session.getAttribute("loginUser");
		Project project = projectDao.findProByName(proName);
		String remark = workRecord.getRemark();
		if(user != null && project != null){
			workRecord.setRecordUser(user);
			workRecord.setRecordProject(project);
			if(remark == null || remark.equals("")) {
				workRecord.setRemark("无");
			}
			this.checkCheckDateIsValid(workRecord);
			recordDao.saveWorkRecord(workRecord);
			mv.addObject("page","record");
		}else{
			mv.addObject("result",false);
			mv.addObject("page","recordAddInfo");
		}
		return mv;
	}


	@Override
	public ModelAndView deleteWorkRecord(int recordId) {
		ModelAndView mv = new ModelAndView();
		//TODO
		return mv;
	}
	
	@Override
	public ModelAndView findAllWorkRecrd() {
		ModelAndView mv = new ModelAndView();
		//TODO
		return mv;
	}
	
	@Override
	public ModelAndView findUserAllRecordByUserId(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User user = (User)session.getAttribute("loginUser");
		if(user != null){
			int userId = user.getUserId();
			List<WorkRecord> recordList = recordDao.findUserAllRecordByUseId(userId);
			mv.addObject("recordData",recordList);
			mv.addObject("page","record");
		}else{
			mv.addObject("result",false);
			mv.addObject("page","errorLogin");
		}
		return mv;
	}
	
	private ModelAndView checkCheckDateIsValid(WorkRecord workRecord) {
		ModelAndView mv = new ModelAndView();
		Date currentDate = new Date();
		Date checkDate = workRecord.getCheckDate();
		
		if(checkDate.after(currentDate)){
			System.out.println("签到日期不能迟于当前日期");
			throw new RuntimeException();
		}
		
		WorkRecord record = recordDao.findRecordByUserIdAndDate(workRecord);
		if(record != null) {
			System.out.println("不能重复签到");
			throw new RuntimeException();
		}
		return mv;

	}

}
