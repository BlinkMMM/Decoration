package com.decoration.dao;

import java.util.Date;
import java.util.List;

import com.decoration.entity.WorkRecord;

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
	
	public WorkRecord findRecordByUserIdAndDate(WorkRecord workRecord);
}
