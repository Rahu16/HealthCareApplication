package com.health.boot.entities;

public enum ApprovalStatus {

	approved("approved"),
	pending("pending"),
	cancelled("cancelled");
	
	ApprovalStatus(String status ) {
		this.setStatus(status);
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String status;
	
}
