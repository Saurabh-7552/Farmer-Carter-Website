package com.lti.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.dto.PlaceBidDto;
import com.lti.dto.bidsDto;
import com.lti.entity.Bid;
import com.lti.entity.Crop;
import com.lti.entity.User;
import com.lti.exception.BidServiceException;
import com.lti.exception.CropServiceException;
import com.lti.repository.BidRepository;
import com.lti.repository.CropRepository;
import com.lti.repository.UserRepository;

@Service
public class BidServiceImpl implements BidService {
	@Autowired
	private BidRepository bidrepo;

	@Autowired
	private CropRepository cropRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<bidsDto> getBids(int id) {
		try {

			List<Bid> bids = bidrepo.findBidsByCropId(id);
			List<bidsDto> sbids = new ArrayList<bidsDto>();

			for (Bid bid : bids) {
				bidsDto bdto = new bidsDto();
				bdto.setBidderName(bid.getUser().getFullname());
				bdto.setAmount(bid.getAmount());
				sbids.add(bdto);
			}
			return sbids;
		} catch (EmptyResultDataAccessException e) {
			throw new CropServiceException("No crops available");
		}
	}

	@Override
	public double getBid(int id) {
		try {
			double amount = bidrepo.maxbid(id);
			System.out.println(amount);
			if (amount == 0) {
				amount = cropRepo.getBasePrice(id);
			}
			if (amount == 0) {

				throw new CropServiceException("Crop not found");
			}
			return amount;

		} catch (EmptyResultDataAccessException e) {
			throw new CropServiceException("No Bid available");
		}
	}

	@Override
	public boolean savebid(PlaceBidDto placebiddto) {
		try {
			Bid bid = new Bid();

			Crop crop = cropRepo.findbyId(placebiddto.getCropid());

			User bidder = userRepo.findbyId(placebiddto.getUserid());

			bid.setCrop(crop);
			bid.setUser(bidder);

			double currentbid = bidrepo.maxbid(placebiddto.getCropid());

			if (currentbid == 0.0) {
				currentbid = crop.getBasePrice();
			}

			if (currentbid + 99.9 > placebiddto.getAmount()) {

				throw new BidServiceException("Bid amount should be atleast 100 greater than current bid amount");
			}
			bid.setAmount(placebiddto.getAmount());

			bidrepo.save(bid);
			return true;
		} catch (EmptyResultDataAccessException e) {
			throw new BidServiceException("Failed to add bid");
		}
	}

}
