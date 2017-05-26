/**
 * 
 */
package com.decoration.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.decoration.bean.TotalCostBean;
import com.decoration.bean.WageCostBean;

import util.Page;

/**
 * @author zhenghan
 * 2017年5月11日 
 * 下午3:32:57
 *
 */
public interface CostDao {
	public List<WageCostBean> findWageCost();
	public List<WageCostBean> findWageCostByCondition(@Param("projectName")String projectName,
			@Param("userName")String userName);
	public List<WageCostBean> findWageCostByPage(@Param("projectName")String projectName,
			@Param("userName")String userName,@Param("page")Page page);
	
	public List<TotalCostBean> findTotalCostByCondition(@Param("projectName")String projectName,
			@Param("customer")String customer);
	public List<TotalCostBean> findTotalCostByPage(@Param("projectName")String projectName,
			@Param("customer")String customer);
}
