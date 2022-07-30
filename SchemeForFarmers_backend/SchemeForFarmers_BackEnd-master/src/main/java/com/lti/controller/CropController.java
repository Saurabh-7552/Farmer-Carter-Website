package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.CropDto;
import com.lti.dto.CropsDto;
import com.lti.dto.ShowAllCropsDto;
import com.lti.exception.CropServiceException;
import com.lti.service.CropService;
import com.lti.status.Status;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class CropController {

	@Autowired
	private CropService cropService;

	@PostMapping(path = "/cropregister")
	public Status register(@RequestBody CropDto cropdto) {
		try {
			cropService.register(cropdto);
			Status status = new Status();
			status.setStatus(com.lti.status.Status.StatusType.SUCCESS);
			status.setMessage("Crop added Successfully");
			return status;
		} catch (CropServiceException e) {

			Status status = new Status();
			status.setStatus(com.lti.status.Status.StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;

		}
	}

	@GetMapping(path = "/crops")
	public CropsDto getCrops() {
		try {
			List<ShowAllCropsDto> crops = cropService.getCrops();
			CropsDto cropdto = new CropsDto();
			cropdto.setCrops(crops);
			Status status = new Status();
			status.setStatus(com.lti.status.Status.StatusType.SUCCESS);
			status.setMessage("Crops Available");
			cropdto.setStatus(status);
			return cropdto;
		} catch (CropServiceException e) {

			Status status = new Status();
			status.setStatus(com.lti.status.Status.StatusType.FAILURE);
			status.setMessage(e.getMessage());
			CropsDto cropdto = new CropsDto();
			cropdto.setStatus(status);
			return cropdto;
		}
	}

}
