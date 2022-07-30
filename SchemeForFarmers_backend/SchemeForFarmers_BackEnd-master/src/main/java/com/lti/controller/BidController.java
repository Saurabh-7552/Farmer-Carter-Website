package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.PlaceBidDto;
import com.lti.dto.ShowBidsByCropId;
import com.lti.dto.bidsDto;
import com.lti.dto.maxbid;
import com.lti.exception.CropServiceException;
import com.lti.service.BidService;
import com.lti.status.Status;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class BidController {

	@Autowired
	private BidService bidservice;

	@GetMapping("/bids")
	public ShowBidsByCropId getBidsbycropid(@RequestParam("cropid") Integer cropid) {
		try {
			List<bidsDto> b = bidservice.getBids(cropid);
			Status status = new Status();
			status.setStatus(com.lti.status.Status.StatusType.SUCCESS);
			status.setMessage("Bids Available");
			ShowBidsByCropId bids = new ShowBidsByCropId();
			bids.setBids(b);
			bids.setStatus(status);
			return bids;

		} catch (CropServiceException e) {

			ShowBidsByCropId bids = new ShowBidsByCropId();
			Status status = new Status();
			status.setStatus(com.lti.status.Status.StatusType.FAILURE);
			status.setMessage(e.getMessage());
			bids.setStatus(status);
			return bids;

		}
	}

	@GetMapping("/bid")
	public maxbid getmaxBidbycropid(@RequestParam("cropid") Integer cropid) {
		try {
			double amount = bidservice.getBid(cropid);
			Status status = new Status();
			status.setStatus(com.lti.status.Status.StatusType.SUCCESS);
			status.setMessage("Bids Available");
			maxbid bid = new maxbid();
			bid.setAmount(amount);
			bid.setStatus(status);
			return bid;

		} catch (CropServiceException e) {

			maxbid bid = new maxbid();
			Status status = new Status();
			status.setStatus(com.lti.status.Status.StatusType.FAILURE);
			status.setMessage(e.getMessage());
			bid.setStatus(status);
			return bid;

		}
	}

	@PostMapping("/placebids")
	public Status placebid(@RequestBody PlaceBidDto placebiddto) {
		try {
			bidservice.savebid(placebiddto);
			Status status = new Status();
			status.setStatus(com.lti.status.Status.StatusType.SUCCESS);
			status.setMessage("Bids Available");

			return status;

		} catch (CropServiceException e) {

			Status status = new Status();
			status.setStatus(com.lti.status.Status.StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;

		}
	}
}
