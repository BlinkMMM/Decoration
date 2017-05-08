package com.decoration.entity;
/**
 * @author zhenghan
 * 2017年3月17日 
 * 下午11:04:06
 * 用户实体类
 */
public class User {
	private int userId;//用户编号
	private String userName;//用户名
	private String password;//密码
	private String jobType;//工作类型
	private String telephone;//手机号码
	private String email;//邮件
	
	public User() {
		
	}

	public User(String userName, String password, String jobType, String telephone, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.jobType = jobType;
		this.telephone = telephone;
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", jobType=" + jobType
				+ ", telephone=" + telephone + ", email=" + email + "]";
	}

	
	
}
