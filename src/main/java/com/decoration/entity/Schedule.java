package com.decoration.entity;

import java.util.Date;

/**
 * @author zhenghan
 * 2017年3月17日 
 * 下午11:04:06
 *
 */
public class Schedule {
	private int scheduleId;
	private int expectedDays;
	private int finishedDays;
	private double scheduleRate;
	private Date recordDate;
	private Project scheduleProject;
	private Flow scheduleFlow;
	private User scheduleUser;
	public Schedule() {
		
	}
	public Schedule(int expectedDays, int finishedDays, double scheduleRate, Date recordDate,
			Project scheduleProject, Flow scheduleFlow, User scheduleUser) {
		super();
		this.expectedDays = expectedDays;
		this.finishedDays = finishedDays;
		this.scheduleRate = scheduleRate;
		this.recordDate = recordDate;
		this.scheduleProject = scheduleProject;
		this.scheduleFlow = scheduleFlow;
		this.scheduleUser = scheduleUser;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public int getExpectedDays() {
		return expectedDays;
	}
	public void setExpectedDays(int expectedDays) {
		this.expectedDays = expectedDays;
	}
	public int getFinishedDays() {
		return finishedDays;
	}
	public void setFinishedDays(int finishedDays) {
		this.finishedDays = finishedDays;
	}
	public double getScheduleRate() {
		return scheduleRate;
	}
	public void setScheduleRate(double scheduleRate) {
		this.scheduleRate = scheduleRate;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public Project getScheduleProject() {
		return scheduleProject;
	}
	public void setScheduleProject(Project scheduleProject) {
		this.scheduleProject = scheduleProject;
	}
	public Flow getScheduleFlow() {
		return scheduleFlow;
	}
	public void setScheduleFlow(Flow scheduleFlow) {
		this.scheduleFlow = scheduleFlow;
	}
	public User getScheduleUser() {
		return scheduleUser;
	}
	public void setScheduleUser(User scheduleUser) {
		this.scheduleUser = scheduleUser;
	}
	@Override
	public String toString() {
		return "Schedule [scheduleId=" + scheduleId + ", expectedDays=" + expectedDays + ", finishedDays="
				+ finishedDays + ", scheduleRate=" + scheduleRate + ", recordDate=" + recordDate + ", scheduleProject="
				+ scheduleProject + ", scheduleFlow=" + scheduleFlow + ", scheduleUser=" + scheduleUser + "]";
	}
	
}
