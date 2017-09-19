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

	ModelAndView saveWorkRecord(WorkRecord workRecord);
	
	ModelAndView deleteWorkRecord(int recordId);
	
	ModelAndView findAllWorkRecrd();
	
	ModelAndView findUserAllRecordByUserId();
	
	ModelAndView findUserAllRecordByUserIdByPage(Page page);
}
