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
	private Project schProId;
	private Flow schFlowId;
	private User schUserId;
	public Schedule() {
	}
	public Schedule(int expectedDays, int finishedDays, double scheduleRate, Date recordDate,
			Project schProId, Flow schFlowId, User schUserId) {
		super();
		this.expectedDays = expectedDays;
		this.finishedDays = finishedDays;
		this.scheduleRate = scheduleRate;
		this.recordDate = recordDate;
		this.schProId = schProId;
		this.schFlowId = schFlowId;
		this.schUserId = schUserId;
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
	public Project getSchProId() {
		return schProId;
	}
	public void setSchProId(Project schProId) {
		this.schProId = schProId;
	}
	public Flow getSchFlowId() {
		return schFlowId;
	}
	public void setSchFlowId(Flow schFlowId) {
		this.schFlowId = schFlowId;
	}
	public User getSchUserId() {
		return schUserId;
	}
	public void setSchUserId(User schUserId) {
		this.schUserId = schUserId;
	}
	@Override
	public String toString() {
		return "Schedule [scheduleId=" + scheduleId + ", expectedDays=" + expectedDays + ", finishedDays="
				+ finishedDays + ", scheduleRate=" + scheduleRate + ", recordDate=" + recordDate + ", schProId="
				+ schProId + ", schFlowId=" + schFlowId + ", schUserId=" + schUserId + "]";
	}
	
	
}
