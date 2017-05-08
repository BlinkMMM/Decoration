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
		Date checkDate = workRecord.getCheckDate();
		if(user != null && project != null){
			workRecord.setRecordUser(user);
			workRecord.setRecordProject(project);
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
			
		}
		
		WorkRecord record = recordDao.findRecordByUserIdAndDate(workRecord);
		
		if(record != null) {
			
		}
		return mv;

	}

}
