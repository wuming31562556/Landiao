package com.landiao.rntegral.entity;

public enum ProjectStatus {
	NotStart(0), InProgress(1), Done(2);
	
	private int value;

	private ProjectStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
