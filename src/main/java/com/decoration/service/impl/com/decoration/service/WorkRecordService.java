/**
 * 
 */
package com.decoration.service;

import org.springframework.web.servlet.ModelAndView;

import com.decoration.entity.WorkRecord;

import util.Page;

/**
 * @author zhenghan
 * 2017年4月2日 
 * 上午11:00:11
 *
 */
public interface WorkRecordService {

	public ModelAndView saveWorkRecord(WorkRecord workRecord);
	
	public ModelAndView deleteWorkRecord(int recordId);
	
	public ModelAndView findAllWorkRecrd();
	
	public ModelAndView findUserAllRecordByUserId();
	
	public ModelAndView findUserAllRecordByUserIdByPage(Page page);
}
