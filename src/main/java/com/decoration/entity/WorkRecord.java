package com.decoration.entity;

import java.util.Date;

/**
 * @author zhenghan
 * 2017年3月17日 
 * 下午11:04:06
 *
 */
public class WorkRecord {
	private int workRecordId;
	private Date checkDate;
	private int isFullWork;
	private String remark;
	private Project recordProject;
	private User recordUser;
	public WorkRecord() {
	}
	public WorkRecord(Date checkDate, int isFullWork, String remark, Project recordProject, User recordUser) {
		super();
		this.checkDate = checkDate;
		this.isFullWork = isFullWork;
		this.remark = remark;
		this.recordProject = recordProject;
		this.recordUser = recordUser;
	}
	public int getWorkRecordId() {
		return workRecordId;
	}
	public void setWorkRecordId(int workRecordId) {
		this.workRecordId = workRecordId;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public int getIsFullWork() {
		return isFullWork;
	}
	public void setIsFullWork(int isFullWork) {
		this.isFullWork = isFullWork;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Project getRecordProject() {
		return recordProject;
	}
	public void setRecordProject(Project recordProject) {
		this.recordProject = recordProject;
	}
	public User getRecordUser() {
		return recordUser;
	}
	public void setRecordUser(User recordUser) {
		this.recordUser = recordUser;
	}
	@Override
	public String toString() {
		return "WorkRecord [workRecordId=" + workRecordId + ", checkDate=" + checkDate + ", isFullWork=" + isFullWork
				+ ", remark=" + remark + ", recordProject=" + recordProject + ", recordUser=" + recordUser + "]";
	}
	
}
