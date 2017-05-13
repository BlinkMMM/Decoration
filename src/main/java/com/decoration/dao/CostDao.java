/**
 * 
 */
package com.decoration.dao;

import java.util.List;

import com.decoration.bean.MaterialCostBean;
import com.decoration.bean.WageCostBean;

/**
 * @author zhenghan
 * 2017年5月11日 
 * 下午3:32:57
 *
 */
public interface CostDao {
	public List<WageCostBean> findWageCost();
	
	
}
