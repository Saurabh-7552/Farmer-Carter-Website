package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CropDetails")
@NamedQuery(name = "getCrops", query = "Select c from Crop c where c.status='Available' and c.startDate < sysdate and c.endDate > sysdate")

public class Crop {
	@Id
	@GeneratedValue
	@Column(name = "Crop_id")
	private int id;

	@Column(name = "Crop_name")
	private String name;

	@Column(name = "Crop_type")
	private String cropType;

	@Column(name = "Fertilizer_Type")
	private String fertilizerType;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "soilPh")
	private double soilPh;

	@Column(name = "Base_Price")
	private double basePrice;

	@Column(name = "status")
	private String status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "start_Date")
	private LocalDate startDate;


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "end_Date")

	private LocalDate endDate;
	@OneToOne
	@JoinColumn(name= "User_id") //FK

	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCropType() {
		return cropType;
	}

	public void setCropType(String cropType) {
		this.cropType = cropType;
	}

	public String getFertilizerType() {
		return fertilizerType;
	}

	public void setFertilizerType(String fertilizerType) {
		this.fertilizerType = fertilizerType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSoilPh() {
		return soilPh;
	}

	public void setSoilPh(double soilPh) {
		this.soilPh = soilPh;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
