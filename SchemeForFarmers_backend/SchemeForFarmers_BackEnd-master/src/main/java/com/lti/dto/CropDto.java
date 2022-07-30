package com.lti.dto;

import com.lti.entity.Crop;

public class CropDto {

	private int farmerid;
	private Crop crop;

	public Crop getCrop() {
		return crop;
	}

	public int getFarmerid() {
		return farmerid;
	}

	public void setFarmerid(int farmerid) {
		this.farmerid = farmerid;
	}

	public void setCrop(Crop crop) {
		this.crop = crop;
	}
}
