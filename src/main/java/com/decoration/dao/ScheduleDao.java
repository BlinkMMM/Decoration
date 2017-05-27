package com.decoration.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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
	
	
	public List<Schedule> findAllSchedule(Map<String,Object> map);
	public List<Schedule> findAllScheduleByPage(@Param("page") Page page,@Param("map")Map<String,Object> map);
}
