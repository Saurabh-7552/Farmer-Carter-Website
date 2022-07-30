package com.lti.service;

import java.util.List;

import com.lti.dto.PlaceBidDto;
import com.lti.dto.bidsDto;

public interface BidService {

	List<bidsDto> getBids(int id);

	boolean savebid(PlaceBidDto placebiddto);

	double getBid(int id);

}

