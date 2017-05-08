package com.decoration.entity;

import java.util.Date;

import com.decoration.bean.MaterialBean;

/**
 * @author zhenghan
 * 2017年3月17日 
 * 下午11:04:06
 *
 */
public class MaterialUse {
	private int useId;
	private MaterialBean useMat;
	private double useNum;
	private double restRate;
	private Date useDate;
	private Project useProject;
	private User useUser;
	
	public MaterialUse() {
	}

	public MaterialUse(MaterialBean useMat, double useNum, double restRate, Date useDate, Project useProject,
			User useUser) {
		super();
		this.useMat = useMat;
		this.useNum = useNum;
		this.restRate = restRate;
		this.useDate = useDate;
		this.useProject = useProject;
		this.useUser = useUser;
	}

	public int getUseId() {
		return useId;
	}

	public void setUseId(int useId) {
		this.useId = useId;
	}

	public MaterialBean getUseMat() {
		return useMat;
	}

	public void setUseMat(MaterialBean useMat) {
		this.useMat = useMat;
	}

	public double getUseNum() {
		return useNum;
	}

	public void setUseNum(double useNum) {
		this.useNum = useNum;
	}

	public double getRestRate() {
		return restRate;
	}

	public void setRestRate(double restRate) {
		this.restRate = restRate;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public Project getUseProject() {
		return useProject;
	}

	public void setUseProject(Project useProject) {
		this.useProject = useProject;
	}

	public User getUseUser() {
		return useUser;
	}

	public void setUseUser(User useUser) {
		this.useUser = useUser;
	}

	@Override
	public String toString() {
		return "MaterialUse [useId=" + useId + ", useMat=" + useMat + ", useNum=" + useNum + ", restRate=" + restRate
				+ ", useDate=" + useDate + ", useProject=" + useProject + ", useUser=" + useUser + "]";
	}
	
}
