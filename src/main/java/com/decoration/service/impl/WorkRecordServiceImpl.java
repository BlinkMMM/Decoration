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
import com.decoration.dao.ProjectDao;
import com.decoration.dao.UserDao;
import com.decoration.dao.WorkRecordDao;
import com.decoration.entity.MaterialUse;
import com.decoration.entity.Project;
import com.decoration.entity.User;
import com.decoration.entity.WorkRecord;
import com.decoration.service.WorkRecordService;

import util.Page;

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
	@Autowired
	private HttpSession session;

	@Override
	public ModelAndView saveWorkRecord(WorkRecord workRecord) {
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
			mv = this.findWorkRecordByPageAfterOperation(mv);
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
	public ModelAndView findUserAllRecordByUserId() {
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
	
	@Override
	public ModelAndView findUserAllRecordByUserIdByPage(Page page) {
		ModelAndView mv = new ModelAndView();
		User user = (User)session.getAttribute("loginUser");
		int userId = user.getUserId();
		List<WorkRecord> list = recordDao.findUserAllRecordByUseId(userId);
		page = new Page(list.size(),page.getCurrentPageCode());
		session.setAttribute("recordPage", page);
		
		List<WorkRecord> pageList = 
				recordDao.findUserAllRecordByUseIdAndByPage(userId, page.getStartCode(), page.getPageSize());
		mv.addObject("recordPageData",pageList);
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
	
	/**
	 * 在签到模块执行操作后分页查出数据
	 * @param mv
	 */
	public ModelAndView findWorkRecordByPageAfterOperation(ModelAndView mv){
		User user = (User)session.getAttribute("loginUser");
		int userId = user.getUserId();
		List<WorkRecord> list = recordDao.findUserAllRecordByUseId(userId);
		Page page = (Page)session.getAttribute("recordPage");
		Page page2 = new Page(list.size(), 1);
		int currentPage = 0;
		if(page.getTotalPages() == page2.getTotalPages()){
			currentPage = page.getTotalPages();
		}else{
			currentPage = page2.getTotalPages();
		}
		page.setCurrentPageCode(currentPage);
		mv = this.findUserAllRecordByUserIdByPage(page);
		return mv;
	}
}
