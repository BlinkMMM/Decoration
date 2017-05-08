package com.decoration.entity;

import java.util.Date;

/**
 * @author zhenghan
 * 2017年3月17日 
 * 下午11:04:06
 *	材料实体类
 */
public class Material {
	private int materialId;
	private String materialName;
	private double materialNum;
	private String materialUnit;
	private double materialPrice;
	private String materialBrand;
	private int matProjectId;
	private int matFlowId;
	private int matUserId;
	private Date matBuyDate;
	public Material() {
	}
	public Material(String materialName, double materialNum, String materialUnit, double materialPrice,
			String materialBrand, int matProjectId, int matFlowId, int matUserId, Date matBuyDate) {
		super();
		this.materialName = materialName;
		this.materialNum = materialNum;
		this.materialUnit = materialUnit;
		this.materialPrice = materialPrice;
		this.materialBrand = materialBrand;
		this.matProjectId = matProjectId;
		this.matFlowId = matFlowId;
		this.matUserId = matUserId;
		this.matBuyDate = matBuyDate;
	}
	public int getMaterialId() {
		return materialId;
	}
	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public double getMaterialNum() {
		return materialNum;
	}
	public void setMaterialNum(double materialNum) {
		this.materialNum = materialNum;
	}
	public String getMaterialUnit() {
		return materialUnit;
	}
	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}
	public double getMaterialPrice() {
		return materialPrice;
	}
	public void setMaterialPrice(double materialPrice) {
		this.materialPrice = materialPrice;
	}
	public String getMaterialBrand() {
		return materialBrand;
	}
	public void setMaterialBrand(String materialBrand) {
		this.materialBrand = materialBrand;
	}
	public int getMatProjectId() {
		return matProjectId;
	}
	public void setMatProjectId(int matProjectId) {
		this.matProjectId = matProjectId;
	}
	public int getMatFlowId() {
		return matFlowId;
	}
	public void setMatFlowId(int matFlowId) {
		this.matFlowId = matFlowId;
	}
	public int getMatUserId() {
		return matUserId;
	}
	public void setMatUserId(int matUserId) {
		this.matUserId = matUserId;
	}
	public Date getMatBuyDate() {
		return matBuyDate;
	}
	public void setMatBuyDate(Date matBuyDate) {
		this.matBuyDate = matBuyDate;
	}
	@Override
	public String toString() {
		return "Material [materialId=" + materialId + ", materialName=" + materialName + ", materialNum=" + materialNum
				+ ", materialUnit=" + materialUnit + ", materialPrice=" + materialPrice + ", materialBrand="
				+ materialBrand + ", matProjectId=" + matProjectId + ", matFlowId=" + matFlowId + ", matUserId="
				+ matUserId + ", matBuyDate=" + matBuyDate + "]";
	}
	
	
}
