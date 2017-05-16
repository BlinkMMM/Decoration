package com.decoration.dao;

import java.util.List;

import com.decoration.entity.Schedule;

import util.Page;

/**
 * @author zhenghan
 * 2017年3月25日 
 * 下午10:12:34
 *
 */
public interface ScheduleDao {
	public boolean saveSchedule(Schedule schedule);
	public boolean deleleScheduleById(int scheduleId);
	public boolean updateSchedule(Schedule schedule);
	public List<Schedule> findAllSchedule();
	public List<Schedule> findAllScheduleByPage(Page page);
}
