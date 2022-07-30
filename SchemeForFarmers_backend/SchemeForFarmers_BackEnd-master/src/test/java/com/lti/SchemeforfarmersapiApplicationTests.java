package com.lti;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.lti.dto.RegisterDto;
import com.lti.entity.Address;
import com.lti.entity.LandDetails;
import com.lti.entity.User;
import com.lti.repository.UserRepository;
import com.lti.service.UserService;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)

class SchemeforfarmersapiApplicationTests {

	@Autowired
	private UserService userServ;
	@Autowired
	private UserRepository userRepo;

	@Test
	void addFarmer() {

		RegisterDto regdto = new RegisterDto();

		User u = new User();
		u.setFullname("Ayush");
		u.setRole("Farmer");
		u.setEmail("ayush@lti.com");
		u.setPhoneno(9650500511L);
		u.setAadharcard(180040002000L);
		u.setAccountNumber(123456789012L);
		u.setPassword("Farmer");
		u.setIfscCode("SB000001");
		u.setPancard("PSKPC1234");

		Address addr = new Address();
		addr.setAddressLine1("456/21 Streetno.2");
		addr.setAddressLine2("Hinjewadi");
		addr.setCity("Gurgaon");
		addr.setPincode(12201);
		addr.setState("Haryana");

		LandDetails landdetails = new LandDetails();
		landdetails.setAddress("123 plot no.2");
		landdetails.setArea(123.05);
		landdetails.setPincode(122001);

		regdto.setUser(u);
		regdto.setLanddetails(landdetails);
		regdto.setAddress(addr);

		userServ.register(regdto);

		assertThat(userRepo.isUserAvailable("ayush@lti.com")).isTrue();
	}

	@Test
	void addBidder() {

		RegisterDto regdto = new RegisterDto();

		User u = new User();
		u.setFullname("Sanjana");
		u.setRole("Bidder");
		u.setEmail("sanjana@lti.com");
		u.setPhoneno(9650500511L);
		u.setAadharcard(180040003000L);
		u.setAccountNumber(123456789123L);
		u.setPassword("Bidder");
		u.setIfscCode("SB000001");
		u.setPancard("PSKC1234");

		Address addr = new Address();
		addr.setAddressLine1("456/21 Streetno.2");
		addr.setAddressLine2("Hinjewadi");
		addr.setCity("Gurgaon");
		addr.setPincode(12201);
		addr.setState("Haryana");

		regdto.setUser(u);
		regdto.setAddress(addr);

		userServ.register(regdto);

		assertThat(userRepo.isUserAvailable("sanjana@lti.com")).isTrue();
		RegisterDto dto = new RegisterDto();

	}

	@Test
	void loginUser() {

		User SavedUser = userServ.login("ayush@lti.com", "Farmer");
		assertThat(SavedUser).isNotNull();
	}
}
