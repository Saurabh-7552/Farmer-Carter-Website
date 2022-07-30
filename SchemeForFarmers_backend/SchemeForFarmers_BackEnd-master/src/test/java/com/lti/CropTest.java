package com.lti;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.lti.dto.CropDto;
import com.lti.entity.Crop;
import com.lti.repository.CropRepository;
import com.lti.service.CropService;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CropTest {

	@Autowired
	private CropService cropServ;

	@Autowired
	private CropRepository cropRepo;

	@Test
	void CropAdd() {

		Crop c = new Crop();
		c.setName("Rice");
		c.setCropType("Rabi");
		c.setFertilizerType("Urea");
		c.setQuantity(123);

		c.setSoilPh(7.19);
		c.setBasePrice(208);
		c.setStatus("Sold");
		c.setStartDate(LocalDate.of(2020, 3, 3));
		c.setEndDate(LocalDate.of(2020, 3, 31));

		CropDto dto = new CropDto();
		dto.setCrop(c);
		dto.setFarmerid(1161);

		cropServ.register(dto);
		Crop crop = cropRepo.getcropbydetails(1161, 123, 208);
		assertThat(crop).isNotNull();
	}
}
