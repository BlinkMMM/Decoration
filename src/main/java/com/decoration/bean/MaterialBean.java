/**
 * 
 */
package com.decoration.bean;

import java.util.Date;

import com.decoration.entity.Flow;
import com.decoration.entity.Material;
import com.decoration.entity.Project;
import com.decoration.entity.User;

/**
 * @author zhenghan
 * 2017年4月17日 
 * 下午9:39:34
 *
 */
public class MaterialBean {
	private int matId;
	private String matName;
	private double matNum;
	private String matUnit;
	private double matPrice;
	private String matBrand;
	private Project matProject;
	private Flow matFlow;
	private User matUser;
	private Date matBuyDate;
	public MaterialBean() {
	}
	public MaterialBean(String matName, double matNum, String matUnit, double matPrice, String matBrand,
			Project matProject, Flow matFlow, User matUser, Date matBuyDate) {
		super();
		this.matName = matName;
		this.matNum = matNum;
		this.matUnit = matUnit;
		this.matPrice = matPrice;
		this.matBrand = matBrand;
		this.matProject = matProject;
		this.matFlow = matFlow;
		this.matUser = matUser;
		this.matBuyDate = matBuyDate;
	}
	public int getMatId() {
		return matId;
	}
	public void setMatId(int matId) {
		this.matId = matId;
	}
	public String getMatName() {
		return matName;
	}
	public void setMatName(String matName) {
		this.matName = matName;
	}
	public double getMatNum() {
		return matNum;
	}
	public void setMatNum(double matNum) {
		this.matNum = matNum;
	}
	public String getMatUnit() {
		return matUnit;
	}
	public void setMatUnit(String matUnit) {
		this.matUnit = matUnit;
	}
	public double getMatPrice() {
		return matPrice;
	}
	public void setMatPrice(double matPrice) {
		this.matPrice = matPrice;
	}
	public String getMatBrand() {
		return matBrand;
	}
	public void setMatBrand(String matBrand) {
		this.matBrand = matBrand;
	}
	public Project getMatProject() {
		return matProject;
	}
	public void setMatProject(Project matProject) {
		this.matProject = matProject;
	}
	public Flow getMatFlow() {
		return matFlow;
	}
	public void setMatFlow(Flow matFlow) {
		this.matFlow = matFlow;
	}
	public User getMatUser() {
		return matUser;
	}
	public void setMatUser(User matUser) {
		this.matUser = matUser;
	}
	public Date getMatBuyDate() {
		return matBuyDate;
	}
	public void setMatBuyDate(Date matBuyDate) {
		this.matBuyDate = matBuyDate;
	}
	@Override
	public String toString() {
		return "MaterialBean [matId=" + matId + ", matName=" + matName + ", matNum=" + matNum + ", matUnit=" + matUnit
				+ ", matPrice=" + matPrice + ", matBrand=" + matBrand + ", matProject=" + matProject + ", matFlow="
				+ matFlow + ", matUser=" + matUser + ", matBuyDate=" + matBuyDate + "]";
	}
	
	
	
}
