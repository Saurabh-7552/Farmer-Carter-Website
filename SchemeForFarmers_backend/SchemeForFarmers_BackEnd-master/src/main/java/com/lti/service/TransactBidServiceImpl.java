package com.lti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.dto.NotificationDto;
import com.lti.entity.Crop;
import com.lti.entity.TransactBids;
import com.lti.entity.User;
import com.lti.exception.CropServiceException;
import com.lti.exception.TransactServiceException;
import com.lti.repository.BidRepository;
import com.lti.repository.CropRepository;
import com.lti.repository.TransactRepository;
import com.lti.repository.UserRepository;

@Service
public class TransactBidServiceImpl implements TransactBidsService {

	@Autowired
	private CropRepository cropRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BidRepository bidrepo;
	@Autowired
	private TransactRepository transactrepo;

	@Override
	public void getwinnerbids() {
		try {
			List<Crop> expiredcrops = cropRepo.findExpiredCrops();
			if (expiredcrops.isEmpty()) {
				throw new TransactServiceException("Bidders are still bidding");
			}
			for (Crop crop : expiredcrops) {
				double bidamount = bidrepo.maxbid(crop.getId());
				if (bidamount == 0) {
					crop.setStatus("Unsold");
					cropRepo.save(crop);
				} else {
					User winnerbidder = bidrepo.findBidderbyBidid(crop.getId(), bidamount);
					crop.setStatus("Sold");
					cropRepo.save(crop);
					TransactBids bids = new TransactBids();
					bids.setCrop(crop);
					bids.setUser(winnerbidder);
					bids.setAmount(bidamount);
					transactrepo.save(bids);
				}
			}
		} catch (EmptyResultDataAccessException e) {
			throw new TransactServiceException("You are not allowed to add Crop");
		}

	}

	@Override
	public List<NotificationDto> getNotification(Integer userid) {

		try {
			String role = userRepo.getUserRole(userid);
			List<NotificationDto> notifications = new ArrayList<NotificationDto>();

			if (role.equals("Farmer")) {

				List<Crop> soldcrops = cropRepo.findSoldCropsbyFarmerId(userid);

				for (Crop crop : soldcrops) {

					TransactBids bids = transactrepo.getTransactionByCropId(crop.getId());

					NotificationDto dto = new NotificationDto();

					dto.setAmount(bids.getAmount());
					dto.setBidderName(bids.getUser().getFullname());
					dto.setBaseprice(crop.getBasePrice());
					dto.setFarmerName(crop.getUser().getFullname());
					dto.setCropid(crop.getId());
					dto.setQuantity(crop.getQuantity());
					dto.setCropName(crop.getName());

					notifications.add(dto);
				}
				return notifications;

			}
			if (role.equals("Bidder")) {

				List<TransactBids> winbids = transactrepo.CropsPurchasedbyBidder(userid);
				for (TransactBids transactBid : winbids) {

					NotificationDto dto = new NotificationDto();

					dto.setAmount(transactBid.getAmount());
					dto.setBidderName(transactBid.getUser().getFullname());
					dto.setBaseprice(transactBid.getCrop().getBasePrice());
					dto.setFarmerName(transactBid.getCrop().getUser().getFullname());
					dto.setCropid(transactBid.getCrop().getId());
					dto.setQuantity(transactBid.getCrop().getQuantity());
					dto.setCropName(transactBid.getCrop().getName());

					notifications.add(dto);

				}

			} else {
				throw new TransactServiceException("No such User");

			}
			return notifications;
		} catch (EmptyResultDataAccessException e) {
			throw new CropServiceException("No Data available");
		}

	}
}
