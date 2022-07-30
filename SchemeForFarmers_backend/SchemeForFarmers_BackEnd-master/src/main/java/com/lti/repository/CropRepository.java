package com.lti.repository;

import java.util.List;

import com.lti.entity.Crop;

public interface CropRepository {

	void save(Crop crop);

	List<Crop> findCrops();

	Crop findbyId(int id);

	double getBasePrice(int id);

	List<Crop> findExpiredCrops();

	List<Crop> findSoldCropsbyFarmerId(int id);

	Crop getcropbydetails(int farmerid, int quantity, double price);

	boolean getcropcountbydetails(int farmerid, int quantity, double price, String name);

}
