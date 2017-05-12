/**
 * 
 */
package com.decoration.bean;

import com.decoration.entity.Material;

/**
 * @author zhenghan
 * 2017年5月9日 
 * 下午2:57:53
 *
 */
public class MaterialCostBean {
	private MaterialBean matBean;
	private double singleMatCost;
	
	public MaterialCostBean() {
		
	}

	public MaterialCostBean(MaterialBean matBean, double singleMatCost) {
		super();
		this.matBean = matBean;
		this.singleMatCost = singleMatCost;
	}

	public MaterialBean getMatBean() {
		return matBean;
	}

	public void setMatBean(MaterialBean matBean) {
		this.matBean = matBean;
	}

	public double getSingleMatCost() {
		return singleMatCost;
	}

	public void setSingleMatCost(double singleMatCost) {
		this.singleMatCost = singleMatCost;
	}

	@Override
	public String toString() {
		return "MaterialCostBean [matBean=" + matBean + ", singleMatCost=" + singleMatCost + "]";
	}
}
