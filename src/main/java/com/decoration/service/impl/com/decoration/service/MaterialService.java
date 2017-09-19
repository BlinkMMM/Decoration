package com.decoration.service;

import com.decoration.bean.MaterialBean;
import com.decoration.entity.MaterialEnter;
import com.decoration.entity.MaterialUse;
import org.springframework.web.servlet.ModelAndView;
import util.Page;

/**
 * @author zhenghan
 * 2017年4月14日 
 * 下午11:00:55
 *
 */
public interface MaterialService {
	
	/**
	 * 查找所有MaterialBean用于显示
	 * @return ModelAndView
	 */
	ModelAndView findAllMatBean();
	/**
	 * 添加材料
	 * @param matBean sth
	 * @return ModelAndView
	 */
	ModelAndView saveMaterialBean(MaterialBean matBean);
	/**
	 * 根据id删除材料
	 * @param matId sth
	 * @return ModelAndView
	 */
	ModelAndView deleteMatById(int matId);
	/**
	 * 修改材料
	 * @param matBean sth
	 * @return ModelAndView
	 */
	ModelAndView updateMatBean(MaterialBean matBean);
	
	ModelAndView findMatBeanByPage(Page page,String searchName);
	
	ModelAndView findMatBeanById(int matId);
	
	/*
	 * ===========材料进场========================================================================
	 */
	/**
	 * 查找出所有进场材料用于显示
	 * @return ModelAndView
	 */
	ModelAndView findAllMatEnter();
	
	ModelAndView findAllMatEnterByPage(Page page,String searchName);
	/**
	 * 添加进场材料
	 * @return ModelAndView
	 */
	ModelAndView saveMatEnter(MaterialEnter matEnter);
	/**
	 * 根据id删除材料
	 * @param enterId sth
	 * @return ModelAndView
	 */
	ModelAndView deleteEnterMatById(int enterId);
	/**
	 * 修改进场材料
	 * @param matEnter sth
	 * @return ModelAndView
	 */
	ModelAndView updateMatEnter(MaterialEnter matEnter);
	
	ModelAndView findMatEnterById(int enterId);
	
	/*
	 * ===========材料使用========================================================================
	 */
	ModelAndView findAllMatUse();
	ModelAndView findAllMatUseByPage(Page page,String searchName);
	ModelAndView saveMatUse(MaterialUse matUse);
	ModelAndView deleteUseMatById(int useId);
	ModelAndView updateMatUse(MaterialUse matUse);
	ModelAndView findMatUseById(int useId);
}
