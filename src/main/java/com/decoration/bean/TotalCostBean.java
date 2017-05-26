/**
 * 
 */
package com.decoration.bean;

import java.util.Date;

import com.decoration.entity.Project;
import com.decoration.entity.User;

/**
 * @author zhenghan
 * 2017年5月9日 
 * 下午1:37:14
 *
 */
public class TotalCostBean {
	private double allMatCost;//所有材料成本
	private double allWageCost;//所有工资成本
	private double totalCost;//总成本
	private Project project;//项目
	
	public TotalCostBean() {

	}
	
	public TotalCostBean(double allMatCost, double allWageCost, double totalCost, Project project) {
		super();
		this.allMatCost = allMatCost;
		this.allWageCost = allWageCost;
		this.totalCost = totalCost;
		this.project = project;
	}
	public double getAllMatCost() {
		return allMatCost;
	}
	public void setAllMatCost(double allMatCost) {
		this.allMatCost = allMatCost;
	}
	public double getAllWageCost() {
		return allWageCost;
	}
	public void setAllWageCost(double allWageCost) {
		this.allWageCost = allWageCost;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	@Override
	public String toString() {
		return "TotalCostBean [allMatCost=" + allMatCost + ", allWageCost=" + allWageCost + ", totalCost=" + totalCost
				+ ", project=" + project + "]";
	}
	
	
}
