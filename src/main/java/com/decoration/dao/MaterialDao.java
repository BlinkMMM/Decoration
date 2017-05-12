/**
 * 
 */
package com.decoration.dao;

import java.util.List;

import com.decoration.bean.MaterialBean;
import com.decoration.bean.MaterialCostBean;
import com.decoration.entity.Material;
import com.decoration.entity.MaterialEnter;
import com.decoration.entity.MaterialUse;

/**
 * @author zhenghan
 * 2017年4月14日 
 * 下午10:27:53
 *
 */
public interface MaterialDao {
	/*
	 * 材料CRUD
	 */
	public boolean saveMaterial(Material material);
	public boolean deleteMatById(int materialId);
	public boolean updateMaterial(Material material);
	public Material findMatById(int matId);
	public Material findMatByName(String matName);
	public List<Material> findAllMat();
	public Material findMatByNameAndProjectId(Material material);
	/*
	 * 材料进场CRUD
	 */
	public boolean saveMatEnter(MaterialEnter matEnter);
	public boolean deleteMatEnterById(int matEnterId);
	public boolean updateMatEnter(MaterialEnter matEnter);
	public MaterialEnter findMatEnterById(int matEnterId);
	public List<MaterialEnter> findAllMatEnter();
	
	/*
	 * 材料使用CRUD
	 */
	public boolean saveMatUse(MaterialUse matUse);
	public boolean deleteMatUseById(int matUseId);
	public boolean updateMatUse(MaterialUse matUse);
	public MaterialUse findMatUseById(int matUseId);
	public List<MaterialUse> findAllMatUse();
	
	/*
	 * MaterialBean CRUD
	 */
	public List<MaterialBean> findMatBean();
	public boolean saveMatBean(MaterialBean matBean);
	public boolean updateMatBean(MaterialBean matBean);
	public MaterialBean findMatBeanById(int matBeanId);
	public List<MaterialBean> findMatBeanByCondition(MaterialBean matBean);
	
	public List<MaterialCostBean> findMatCostByProjectId(int projectId);
}
