package com.decoration.entity;
/**
 * @author zhenghan
 * 2017年3月17日 
 * 下午11:04:06
 *
 */
public class Project {
	private int projectId;
	private String projectName;
	private String customer;
	private String telephone;
	private String address;
	public Project() {
	}
	public Project(String projectName, String customer, String telephone, String address) {
		super();
		this.projectName = projectName;
		this.customer = customer;
		this.telephone = telephone;
		this.address = address;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", customer=" + customer
				+ ", telephone=" + telephone + ", address=" + address + "]";
	}
	
	
}
