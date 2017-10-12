package com.landiao.rntegral.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmployeeWorkContentEvaluation {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	private Integer employeeWorkContentId; //员工工作内容ID
	
	private Integer evaluationOptionId; //评价项ID
	
	private String evaluationValue; //评价值(星级)
	
	private Date createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmployeeWorkContentId() {
		return employeeWorkContentId;
	}

	public void setEmployeeWorkContentId(Integer employeeWorkContentId) {
		this.employeeWorkContentId = employeeWorkContentId;
	}

	public Integer getEvaluationOptionId() {
		return evaluationOptionId;
	}

	public void setEvaluationOptionId(Integer evaluationOptionId) {
		this.evaluationOptionId = evaluationOptionId;
	}

	public String getEvaluationValue() {
		return evaluationValue;
	}

	public void setEvaluationValue(String evaluationValue) {
		this.evaluationValue = evaluationValue;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
