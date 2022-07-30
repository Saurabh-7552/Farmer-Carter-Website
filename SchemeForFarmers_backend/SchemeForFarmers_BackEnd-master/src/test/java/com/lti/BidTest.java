package com.lti;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.lti.dto.PlaceBidDto;
import com.lti.repository.UserRepository;
import com.lti.service.BidService;
import com.lti.service.UserService;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)

public class BidTest {
	@Autowired
	private UserService userServ;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BidService bidServ;

	@Test
	void addbid() {

		PlaceBidDto dto = new PlaceBidDto();

		dto.setAmount(1023.0);
		dto.setCropid(1003);
		dto.setUserid(1141);

		assertThat(bidServ.savebid(dto)).isTrue();

	}
}
