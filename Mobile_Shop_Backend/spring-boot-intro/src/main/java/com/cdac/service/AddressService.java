package com.cdac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.Address;
import com.cdac.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addRepo;
	
	public Address saveAdd(Address address) {
		return addRepo.save(address);
	}
	
}
