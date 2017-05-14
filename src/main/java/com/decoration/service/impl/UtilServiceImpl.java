/**
 * 
 */
package com.decoration.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.decoration.dao.FlowDao;
import com.decoration.dao.ProjectDao;
import com.decoration.entity.Flow;
import com.decoration.entity.Project;
import com.decoration.service.UtilService;

import util.Page;

/**
 * @author zhenghan
 * 2017年5月13日 
 * 下午1:54:36
 *
 */
@Service(value = "utilService")
@Scope("prototype")
public class UtilServiceImpl implements UtilService{
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private FlowDao flowDao;

	@Override
	public void chooseProjectAndFlow(String proData, String flowData) {
		List<Project> projectList = projectDao.findAllProject();
		request.setAttribute(proData,projectList);
		List<Flow> flowList = flowDao.findAllFlow();
		request.setAttribute(flowData,flowList);
	}

	@Override
	public ModelAndView checkDateIsValid(Date InputDate) {
		ModelAndView mv = new ModelAndView();
		Date currentDate = new Date();
		if(InputDate.after(currentDate)){
			System.out.println("日期不能迟于当前日期");
			throw new RuntimeException();
		}
		return mv;
	}
	
	public ModelAndView choosePage(Page page){
		ModelAndView mv = new ModelAndView();
		String from = request.getParameter("from");
		int total = page.getTotalPages();//总页数
		int currentPageCode = page.getCurrentPageCode();
		if (from == null || from.equals("")) {
			currentPageCode = 1;
		} else if (from.equals("firstPage")) {
			currentPageCode = 1;
		} else if (from.equals("previousPage")) {
			if (currentPageCode > 1) {
				currentPageCode = currentPageCode - 1;
			} else {
				currentPageCode = 1;
			}
		} else if (from.equals("nextPage")) {
			if (currentPageCode < total) {
				currentPageCode = currentPageCode + 1;
			} else {
				currentPageCode = total;
			}
		} else if (from.equals("finalPage")) {
			currentPageCode = total;
		} 
		page.setCurrentPageCode(currentPageCode);
		return mv;
	}
}
