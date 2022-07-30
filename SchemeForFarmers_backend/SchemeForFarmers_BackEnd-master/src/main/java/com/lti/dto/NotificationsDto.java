package com.lti.dto;

import java.util.List;

import com.lti.status.Status;

public class NotificationsDto {

	private List<NotificationDto> notification;
	private Status status;

	public List<NotificationDto> getNotification() {
		return notification;
	}

	public void setNotification(List<NotificationDto> notification) {
		this.notification = notification;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
