package com.decoration.entity;
/**
 * @author zhenghan
 * 2017年3月17日 
 * 下午11:04:06
 *	装修流程实体类
 */
public class Flow {
	private int flowId;//流程编号
	private String flowName;//流程名称
	public Flow() {
	}
	public Flow(String flowName) {
		super();
		this.flowName = flowName;
	}
	public int getFlowId() {
		return flowId;
	}
	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}
	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	@Override
	public String toString() {
		return "Flow [flowId=" + flowId + ", flowName=" + flowName + "]";
	}
	
	
}
