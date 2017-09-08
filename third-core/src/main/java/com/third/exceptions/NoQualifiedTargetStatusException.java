package com.third.exceptions;

public class NoQualifiedTargetStatusException extends SystemException {
	private String currentStatus;

	public String getCurrentStatus()
	{
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus)
	{
		this.currentStatus = currentStatus;
	}

}
