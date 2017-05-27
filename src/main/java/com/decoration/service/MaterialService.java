/**
 * 
 */
package com.decoration.service;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.decoration.bean.MaterialBean;
import com.decoration.entity.MaterialEnter;
import com.decoration.entity.MaterialUse;

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
	 * @return
	 */
	public ModelAndView findAllMatBean();
	/**
	 * 添加材料
	 * @param material
	 * @return
	 */
	public ModelAndView saveMaterialBean(MaterialBean matBean);
	/**
	 * 根据id删除材料
	 * @param matId
	 * @return
	 */
	public ModelAndView deleteMatById(int matId);
	/**
	 * 修改材料
	 * @param matBean
	 * @return
	 */
	public ModelAndView updateMatBean(MaterialBean matBean);
	
	public ModelAndView findMatBeanByPage(Page page,String searchName);
	/*
	 * ===========材料进场========================================================================
	 */
	/**
	 * 查找出所有进场材料用于显示
	 * @return
	 */
	public ModelAndView findAllMatEnter();
	
	public ModelAndView findAllMatEnterByPage(Page page,String searchName);
	/**
	 * 添加进场材料
	 * @return
	 */
	public ModelAndView saveMatEnter(MaterialEnter matEnter);
	/**
	 * 根据id删除材料
	 * @param matId
	 * @return
	 */
	public ModelAndView deleteEnterMatById(int enterId);
	/**
	 * 修改进场材料
	 * @param matEnter
	 * @return
	 */
	public ModelAndView updateMatEnter(MaterialEnter matEnter);
	
	/*
	 * ===========材料使用========================================================================
	 */
	public ModelAndView findAllMatUse();
	public ModelAndView findAllMatUseByPage(Page page,String searchName);
	public ModelAndView saveMatUse(MaterialUse matUse);
	public ModelAndView deleteUseMatById(int useId);
	public ModelAndView updateMatUse(MaterialUse matUse);
	
}
