/**
 * 
 */
package com.decoration.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.decoration.entity.WorkRecord;

/**
 * @author zhenghan
 * 2017年4月2日 
 * 上午11:00:11
 *
 */
public interface WorkRecordService {

	public ModelAndView saveWorkRecord(WorkRecord workRecord,HttpSession session);
	
	public ModelAndView deleteWorkRecord(int recordId);
	
	public ModelAndView findAllWorkRecrd();
	
	public ModelAndView findUserAllRecordByUserId(HttpSession session);
}
