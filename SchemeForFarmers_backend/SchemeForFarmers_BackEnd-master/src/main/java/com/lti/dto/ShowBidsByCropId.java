package com.lti.dto;

import java.util.List;

import com.lti.status.Status;

public class ShowBidsByCropId {

	private List<bidsDto> bids;
	private Status status;

	public List<bidsDto> getBids() {
		return bids;
	}

	public void setBids(List<bidsDto> bids) {
		this.bids = bids;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
