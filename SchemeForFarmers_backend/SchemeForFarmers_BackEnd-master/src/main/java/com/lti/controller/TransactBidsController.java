package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.NotificationDto;
import com.lti.dto.NotificationsDto;
import com.lti.exception.TransactServiceException;
import com.lti.service.TransactBidsService;
import com.lti.status.Status;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class TransactBidsController {

	@Autowired
	private TransactBidsService tbservice;

	@GetMapping("/winbid")
	public Status placebid() {
		try {
			tbservice.getwinnerbids();
			Status status = new Status();
			status.setStatus(com.lti.status.Status.StatusType.SUCCESS);
			status.setMessage("Winner bidders announced");
			return status;

		} catch (TransactServiceException e) {

			Status status = new Status();
			status.setStatus(com.lti.status.Status.StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@GetMapping("/winbids")
	public NotificationsDto farmernotification(@RequestParam("userid") Integer userid) {
		try {

			List<NotificationDto> notification = tbservice.getNotification(userid);
			NotificationsDto dto = new NotificationsDto();
			Status status = new Status();
			status.setStatus(com.lti.status.Status.StatusType.SUCCESS);
			status.setMessage("Winner bidders announced");
			dto.setNotification(notification);
			dto.setStatus(status);
			return dto;

		} catch (TransactServiceException e) {

			NotificationsDto dto = new NotificationsDto();
			Status status = new Status();
			status.setStatus(com.lti.status.Status.StatusType.FAILURE);
			status.setMessage(e.getMessage());
			dto.setStatus(status);
			return dto;
		}
	}
}
