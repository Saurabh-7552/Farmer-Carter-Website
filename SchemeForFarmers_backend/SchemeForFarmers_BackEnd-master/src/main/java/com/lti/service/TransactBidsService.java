package com.lti.service;

import java.util.List;

import com.lti.dto.NotificationDto;

public interface TransactBidsService {

	void getwinnerbids();

	List<NotificationDto> getNotification(Integer userid);

}