package com.lti.service;

import java.util.List;

import com.lti.dto.CropDto;
import com.lti.dto.ShowAllCropsDto;

public interface CropService {

	void register(CropDto cropdto);

	List<ShowAllCropsDto> getCrops();

}
