package com.lti.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.dto.CropDto;
import com.lti.dto.ShowAllCropsDto;
import com.lti.entity.Crop;
import com.lti.entity.User;
import com.lti.exception.CropServiceException;
import com.lti.repository.CropRepository;
import com.lti.repository.UserRepository;

@Service
public class CropServiceImpl implements CropService {

	@Autowired
	private CropRepository cropRepo;
	@Autowired
	private UserRepository userRepo;

	@Override
	public void register(CropDto cropdto) {

		try {
			User farmer = userRepo.findbyId(cropdto.getFarmerid());

			if (farmer.getRole().equals("Bidder")) {
				throw new CropServiceException("Bidder not allowed to add Crop");
			}
			Crop crop = cropdto.getCrop();

			long daysBetween = ChronoUnit.DAYS.between(crop.getStartDate(), crop.getEndDate());
			if (daysBetween > 30) {
				throw new CropServiceException("The bid period cannot be greater than 30 days");
			}
			if (cropRepo.getcropcountbydetails(cropdto.getFarmerid(), crop.getQuantity(), crop.getBasePrice(),
					crop.getName())) {
				throw new CropServiceException("Similar request has been made already");
			}
			crop.setUser(farmer);

			cropRepo.save(crop);

		} catch (EmptyResultDataAccessException e) {
			System.out.println(e.getMessage());
			throw new CropServiceException("Only a registered farmer can add crop");
		}

	}

	@Override
	public List<ShowAllCropsDto> getCrops() {
		try {
			List<ShowAllCropsDto> AvailableCrops = new ArrayList<ShowAllCropsDto>();
			List<Crop> crops = cropRepo.findCrops();
			for (Crop crop : crops) {
				ShowAllCropsDto scrop = new ShowAllCropsDto();
				scrop.setId(crop.getId());
				scrop.setName(crop.getName());
				scrop.setSoilPh(crop.getSoilPh());
				scrop.setCropType(crop.getCropType());
				scrop.setFertilizerType(crop.getFertilizerType());
				scrop.setBasePrice(crop.getBasePrice());
				scrop.setQuantity(crop.getQuantity());
				scrop.setFullname(crop.getUser().getFullname());
				scrop.setEndDate(crop.getEndDate());
				AvailableCrops.add(scrop);
			}
			return AvailableCrops;
		} catch (EmptyResultDataAccessException e) {
			throw new CropServiceException("No crops available");
		}
	}
}
