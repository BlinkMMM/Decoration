/**
 * 
 */
package com.decoration.entity;

import java.util.Date;

/**
 * @author zhenghan
 * 2017年6月1日 
 * 下午4:38:10
 *
 */
public class CheckSchedule {
	private int checkId;
	private int checkState;
	private String reason;
	private String responsibleParty;
	private Date checkDate;
	private User checkUser;
	private int scheduleId;
	public CheckSchedule() {
	
	}
	public CheckSchedule(int checkState, String reason, String responsibleParty, Date checkDate, User checkUser,
			int scheduleId) {
		super();
		this.checkState = checkState;
		this.reason = reason;
		this.responsibleParty = responsibleParty;
		this.checkDate = checkDate;
		this.checkUser = checkUser;
		this.scheduleId = scheduleId;
	}
	public int getCheckId() {
		return checkId;
	}
	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}
	public int getCheckState() {
		return checkState;
	}
	public void setCheckState(int checkState) {
		this.checkState = checkState;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getResponsibleParty() {
		return responsibleParty;
	}
	public void setResponsibleParty(String responsibleParty) {
		this.responsibleParty = responsibleParty;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public User getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	@Override
	public String toString() {
		return "CheckSchedule [checkId=" + checkId + ", checkState=" + checkState + ", reason=" + reason
				+ ", responsibleParty=" + responsibleParty + ", checkDate=" + checkDate + ", checkUser=" + checkUser
				+ ", scheduleId=" + scheduleId + "]";
	}
	
	
}
