package com.cdac.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.entity.Address;
import com.cdac.entity.Contact;
import com.cdac.entity.GroupOrder;
import com.cdac.entity.Order;
import com.cdac.entity.Product;
import com.cdac.entity.User;
import com.cdac.repository.ContactUsRepo;
import com.cdac.repository.ProductRepository;
import com.cdac.service.AddressService;
import com.cdac.service.ProductService;
import com.cdac.service.UserServiceImp;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {
	
	@Autowired
	private UserServiceImp userServiceImp;
	@Autowired
	private ProductService productRepo;
	@Autowired
	private AddressService addService;
	
	@Autowired
	private ContactUsRepo contactUsRepo;
	
	@RequestMapping("/hello")
	public String hello() {
		return "Welcome To Spring Boot";
	}
	
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		if(userServiceImp.findByEmail(user.getEmail()) == null) {
			Address address = user.getAddress();
		
		user.setAddress(addService.saveAdd(address));
		userServiceImp.saveUser(user);
		return "Succesful";
		}else return "User Already Exists";
	}
	
	@PostMapping("/login")
	public User login(@RequestBody User user, HttpSession session) {
		
		User vUser =  userServiceImp.validateUser(user);
		session.setAttribute("user",vUser);
		return vUser;
	}
	
	@PostMapping("/listAll")
	public List<Product> showAllProducts() {
		return productRepo.showAll();
	}
	
	
	@PostMapping("/contactUs")
	public String contact(@RequestBody Contact contact) {
		if  (contactUsRepo.save(contact)!= null) {
			return "Your Querry has been registered";
		} else {
			return " Internal Server Error";
		}
	}
}
