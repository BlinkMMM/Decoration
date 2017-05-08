package com.decoration.entity;

import java.util.Date;

import com.decoration.bean.MaterialBean;

/**
 * @author zhenghan
 * 2017年3月17日 
 * 下午11:04:06
 *
 */
public class MaterialEnter {
	private int enterId;//材料进场编号
	private MaterialBean enterMat;//进场材料
	private double enterNum;//材料进场数量
	private Date enterDate;//材料进场日期
	private User enterUser;//登记人
	private Project enterProject;
	
	public MaterialEnter() {
	}


	public MaterialEnter(MaterialBean enterMat, double enterNum, Date enterDate, User enterUser, Project enterProject) {
		super();
		this.enterMat = enterMat;
		this.enterNum = enterNum;
		this.enterDate = enterDate;
		this.enterUser = enterUser;
		this.enterProject = enterProject;
	}


	public Project getEnterProject() {
		return enterProject;
	}

	public void setEnterProject(Project enterProject) {
		this.enterProject = enterProject;
	}

	

	public int getEnterId() {
		return enterId;
	}

	public void setEnterId(int enterId) {
		this.enterId = enterId;
	}

	public MaterialBean getEnterMat() {
		return enterMat;
	}

	public void setEnterMat(MaterialBean enterMat) {
		this.enterMat = enterMat;
	}

	public double getEnterNum() {
		return enterNum;
	}

	public void setEnterNum(double enterNum) {
		this.enterNum = enterNum;
	}

	public Date getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	public User getEnterUser() {
		return enterUser;
	}

	public void setEnterUser(User enterUser) {
		this.enterUser = enterUser;
	}

	@Override
	public String toString() {
		return "MaterialEnter [enterId=" + enterId + ", enterMat=" + enterMat + ", enterNum=" + enterNum
				+ ", enterDate=" + enterDate + ", enterUser=" + enterUser + "]";
	}

	
	
}
