package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.User;
import com.cdac.repository.UserRepository;


@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	
	public User saveUser(User user) {
		
		return userRepo.save(user);
	}


	@Override
	public User validateUser(User user) {
		User validatedUser =  userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if (validatedUser ==  null)
			return null;
		return validatedUser;

	}
	
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public List<User> findAll(){
		return userRepo.findAll();
	}
	
	
}
