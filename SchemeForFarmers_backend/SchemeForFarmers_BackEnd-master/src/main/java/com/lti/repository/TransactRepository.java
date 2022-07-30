package com.lti.repository;

import java.util.List;

import com.lti.entity.TransactBids;

public interface TransactRepository {

	void save(TransactBids bids);

	TransactBids getTransactionByCropId(int id);

	List<TransactBids> CropsPurchasedbyBidder(int id);

}