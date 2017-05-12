/**
 * 
 */
package com.decoration.bean;

import com.decoration.entity.WorkRecord;

/**
 * @author zhenghan
 * 2017年5月11日 
 * 下午2:37:02
 *
 */
public class WageCostBean {
	private WorkRecord workRecord;
	private double dailyWage;
	private int fullWorkDays;
	private int notFullWorkDays;
	private double singleWage;
	public WageCostBean() {
	}
	public WageCostBean(WorkRecord workRecord, double dailyWage, int fullWorkDays, int notFullWorkDays,
			double singleWage) {
		super();
		this.workRecord = workRecord;
		this.dailyWage = dailyWage;
		this.fullWorkDays = fullWorkDays;
		this.notFullWorkDays = notFullWorkDays;
		this.singleWage = singleWage;
	}
	public WorkRecord getWorkRecord() {
		return workRecord;
	}
	public void setWorkRecord(WorkRecord workRecord) {
		this.workRecord = workRecord;
	}
	public double getDailyWage() {
		return dailyWage;
	}
	public void setDailyWage(double dailyWage) {
		this.dailyWage = dailyWage;
	}
	public int getFullWorkDays() {
		return fullWorkDays;
	}
	public void setFullWorkDays(int fullWorkDays) {
		this.fullWorkDays = fullWorkDays;
	}
	public int getNotFullWorkDays() {
		return notFullWorkDays;
	}
	public void setNotFullWorkDays(int notFullWorkDays) {
		this.notFullWorkDays = notFullWorkDays;
	}
	public double getSingleWage() {
		return singleWage;
	}
	public void setSingleWage(double singleWage) {
		this.singleWage = singleWage;
	}
	@Override
	public String toString() {
		return "WageCostBean [workRecord=" + workRecord + ", dailyWage=" + dailyWage + ", fullWorkDays=" + fullWorkDays
				+ ", notFullWorkDays=" + notFullWorkDays + ", singleWage=" + singleWage + "]";
	}
	
}
