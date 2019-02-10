package com.trix.tcp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trix.tcp.entity.User;
import com.trix.tcp.repo.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TcpApplicationTests {

	@Autowired
	UserRepository repo;
	
	@Test
	public void contextLoads() {
		User user = new User();
		user.setEmail("abc@gmail.com");
		user.setName("abc");
		repo.save(user);
	}

}

