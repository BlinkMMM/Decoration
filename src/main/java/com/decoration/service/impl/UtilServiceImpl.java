/**
 * 
 */
package com.decoration.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.decoration.bean.MaterialBean;
import com.decoration.dao.FlowDao;
import com.decoration.dao.MaterialDao;
import com.decoration.dao.ProjectDao;
import com.decoration.entity.Flow;
import com.decoration.entity.MaterialEnter;
import com.decoration.entity.MaterialUse;
import com.decoration.entity.Project;
import com.decoration.service.UtilService;

import util.DictionaryItems;
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
	@Autowired
	private MaterialDao materialDao;
	@Autowired
	private HttpSession session;

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
	
	public ModelAndView choosePage(Page page , String parameter){
		ModelAndView mv = new ModelAndView();
		String from = request.getParameter(parameter);
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
	
	public void initChooseProjectAndFlow(){
		ModelAndView mv = new ModelAndView();
		List<Project> projectList = projectDao.findAllProject();
		session.setAttribute("chooseProject",projectList);
		List<Flow> flowList = flowDao.findAllFlow();
		session.setAttribute("chooseFlow",flowList);
	}
	
	
	
	/**
	 * 初始化分页查询的page
	 */
	public Page initPage(String type){
		Page page = new Page();
		Map<String,Object> map = new HashMap<String,Object>();
		switch(type) {
		case DictionaryItems.MATERIAL_BUY_PAGE_TYPE:
			List<MaterialBean> matList = materialDao.findMatBean(map);
			page = new Page(matList.size(),DictionaryItems.MATERIAL_BUY_INIT_PAGECODE);
			session.setAttribute("matPage", page);
			break;
		case DictionaryItems.MATERIAL_ENTER_PAGE_TYPE:
			List<MaterialEnter> enterList = materialDao.findAllMatEnter(map);
			page = new Page(enterList.size(),DictionaryItems.MATERIAL_ENTER_INIT_PAGECODE);
			session.setAttribute("enterPage", page);
			break;
		case DictionaryItems.MATERIAL_USE_PAGE_TYPE:
			List<MaterialUse> useList = materialDao.findAllMatUse(map);
			page = new Page(useList.size(),DictionaryItems.MATERIAL_USE_INIT_PAGECODE);
			session.setAttribute("usePage", page);
			break;
		}
		return page;
	}
}
