package com.decoration.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.decoration.entity.WorkRecord;

import util.Page;

/**
 * @author zhenghan
 * 2017年3月25日 
 * 下午10:12:34
 *
 */
public interface WorkRecordDao {

	public boolean saveWorkRecord(WorkRecord workRecord);

	public boolean deleteWorkRecordById(int recordId);
	
	public WorkRecord findRecordById(int recordId);

	public WorkRecord findRecordByName(String recordName);

	public List<WorkRecord> findAllWorkRecord();
	
	public List<WorkRecord> findUserAllRecordByUseId(int userId);
	
	public List<WorkRecord> findUserAllRecordByUseIdAndByPage(@Param("userId")int userId,@Param("startCode")int startCode,@Param("pageSize")int pageSize);
	
	public WorkRecord findRecordByUserIdAndDate(WorkRecord workRecord);
}
