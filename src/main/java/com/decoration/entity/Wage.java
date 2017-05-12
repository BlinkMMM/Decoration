/**
 * 
 */
package com.decoration.entity;

/**
 * @author zhenghan
 * 2017年5月11日 
 * 下午2:32:07
 *
 */
public class Wage {
	private int wageId;
	private double dailyWage;
	private Project wageProject;
	private User wageUser;
	public Wage() {
	}
	public Wage(double dailyWage, Project wageProject, User wageUser) {
		super();
		this.dailyWage = dailyWage;
		this.wageProject = wageProject;
		this.wageUser = wageUser;
	}
	public int getWageId() {
		return wageId;
	}
	public void setWageId(int wageId) {
		this.wageId = wageId;
	}
	public double getDailyWage() {
		return dailyWage;
	}
	public void setDailyWage(double dailyWage) {
		this.dailyWage = dailyWage;
	}
	public Project getWageProject() {
		return wageProject;
	}
	public void setWageProject(Project wageProject) {
		this.wageProject = wageProject;
	}
	public User getWageUser() {
		return wageUser;
	}
	public void setWageUser(User wageUser) {
		this.wageUser = wageUser;
	}
	@Override
	public String toString() {
		return "Wage [wageId=" + wageId + ", dailyWage=" + dailyWage + ", wageProject=" + wageProject + ", wageUser="
				+ wageUser + "]";
	}
	
}
