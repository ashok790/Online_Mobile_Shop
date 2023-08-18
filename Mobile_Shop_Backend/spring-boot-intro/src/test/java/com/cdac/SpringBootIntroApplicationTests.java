package com.cdac;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cdac.entity.User;
import com.cdac.repository.UserRepository;

@SpringBootTest
class SpringBootIntroApplicationTests {
	
	
	@Autowired
	private UserRepository userRepo;

	@Test
	void addUser() {
		User user = new User();
		user.setEmail("spring@gmail.com");
		user.setPassword("1234");
		user.setFname("Raj");
		
		userRepo.save(user);
		
	}

}
