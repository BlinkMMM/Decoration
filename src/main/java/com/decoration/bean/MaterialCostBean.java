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
	private double allMatCost;
	
	public MaterialCostBean() {
		
	}

	public MaterialCostBean(MaterialBean matBean, double singleMatCost, double allMatCost) {
		super();
		this.matBean = matBean;
		this.singleMatCost = singleMatCost;
		this.allMatCost = allMatCost;
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

	public double getAllMatCost() {
		return allMatCost;
	}

	public void setAllMatCost(double allMatCost) {
		this.allMatCost = allMatCost;
	}

	@Override
	public String toString() {
		return "MaterialCostBean [matBean=" + matBean + ", singleMatCost=" + singleMatCost + ", allMatCost="
				+ allMatCost + "]";
	}
	
	
	
}
