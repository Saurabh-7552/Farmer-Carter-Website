package com.lti.repository;

import java.util.List;

import com.lti.entity.Bid;
import com.lti.entity.User;

public interface BidRepository {

	List<Bid> findBidsByCropId(int id);

	void save(Bid bid);

	double maxbid(int id);

	User findBidderbyBidid(int id, double bidamount);

}