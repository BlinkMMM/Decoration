package com.decoration.dao;

import java.util.List;

import com.decoration.entity.Flow;

/**
 * @author zhenghan
 * 2017年3月25日 
 * 下午10:12:34
 *
 */
public interface FlowDao {
	public Flow findFlowByName(String flowName);
	
	public List<Flow> findAllFlow();
}
