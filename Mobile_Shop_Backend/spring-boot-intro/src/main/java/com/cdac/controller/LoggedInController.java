package com.cdac.controller;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.entity.Contact;
import com.cdac.entity.GroupOrder;
import com.cdac.entity.Order;
import com.cdac.entity.Product;
import com.cdac.entity.User;
import com.cdac.repository.ContactUsRepo;
import com.cdac.service.AdminInfo;
import com.cdac.service.OrderAdminInfo;
import com.cdac.service.OrderService;
import com.cdac.service.ProductService;
import com.cdac.service.UserService;
import com.cdac.service.UserServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class LoggedInController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private AdminInfo admin;

	@Autowired
	private UserServiceImp userService;

	@Autowired
	private ContactUsRepo contactUsRepo;
	
	
	@PostMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "Logged Out From Server";
	}

	@RequestMapping("/placeOrder")
	public String placeOrder(@RequestBody List<GroupOrder> orders, HttpSession session) {
		// Check working
		if (session.getAttribute("user") != null) {
			try {
				Order placed = orderService.placeOrder(orders, (User) session.getAttribute("user"));
				return "Order olaced. Your Order id : " + placed.getOrderId();
			} catch (IllegalArgumentException e) {
				return "Insuffcicet Quantity  "+e.getMessage();
			}
		}
		return "False";
	}

	@PostMapping("/dashboard")
	public AdminInfo getSalesDetails(HttpSession session) {
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			System.out.println(user.getAdmin());
			if (user.getAdmin()) {
				AdminInfo admin = new AdminInfo();
				System.out.println(orderService.totalOrderValue());
				//admin.setOrders(orderService.listAll());
				List<Order> orders1 = orderService.listAll();
				List<OrderAdminInfo> orders = new ArrayList<OrderAdminInfo>();
				for (Order order : orders1) {
					OrderAdminInfo orderAdminInfo = new OrderAdminInfo();
					orderAdminInfo.setOrderId(order.getOrderId());
					orderAdminInfo.setOrderDate(order.getOrderDate());
					orderAdminInfo.setOrderValue(order.getOrderValue());
					orderAdminInfo.setGroupOrder(order.getGroupOrder());
					orderAdminInfo.setAddress(order.getUser().getAddress());
					orderAdminInfo.setUserId(order.getUser().getUserId());
					orderAdminInfo.setUserName(order.getUser().getFname() + " " + order.getUser().getLname());
					orders.add(orderAdminInfo);
				}
				admin.setOrders(orders);
				admin.setRevenue(orderService.totalOrderValue());
				admin.setProdcuts(productService.showAll());
				admin.setUsers(userService.findAll());
				admin.setContacts(contactUsRepo.findAll());
				return admin;
			}

		}
		return null;
	}
	
	@PostMapping("/usersAdmin")
	public AdminInfo getProductsAdmin(HttpSession session) {
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			System.out.println(user.getAdmin());
			if (user.getAdmin()) {
				AdminInfo admin = new AdminInfo();
				System.out.println(orderService.totalOrderValue());
//				admin.setOrders(orderService.listAll());
//				admin.setRevenue(orderService.totalOrderValue());
//				admin.setProdcuts(productService.showAll());
				admin.setUsers(userService.findAll());
//				admin.setContacts(contactUsRepo.findAll());
		
				return admin;
			}

		}
		return null;
	}

	@PostMapping("/deleteProduct")
	public String deleteProduct(@RequestBody Product product, HttpSession session) {
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			System.out.println(user.getAdmin());
			if (user.getAdmin()) {
				try {
					productService.deleteProduct(product);
					return "Product with ID : " + product.getProductId() + " Deleted";
				} catch (Exception e) {
					return "Internal Server Error Contact Admin";
				}
			}
		}
		return "Admin Login Failed";
	}

	@PostMapping("/updateProduct")
	public String updateProduct(@RequestBody Product product, HttpSession session) {
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			System.out.println(user.getAdmin());
			if (user.getAdmin()) {
				try {
					productService.updateProduct(product);
					return "Product with ID : " + product.getProductId() + " Updated";
				} catch (Exception e) {
					return "Internal Server Error Contact Admin";
				}
			}
		}
		return "Admin Login Failed";
	}

	@PostMapping("/addProduct")
	public String addProduct(@RequestBody Product product, HttpSession session) {
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			System.out.println(user.getAdmin());
			if (user.getAdmin()) {
				try {
					productService.updateProduct(product);
					return "Product with ID : " + product.getProductId() + " Added";
				} catch (Exception e) {
					return "Internal Server Error Contact Admin";
				}
			}
		}
		return "Admin Login Failed";
	}

	@RequestMapping(value = "/addProductTest", consumes = {
			org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE })
	public String addProduct(@RequestParam("file") MultipartFile file, @RequestParam("productJson") String productJson,
			HttpSession session) {
		System.out.println("Launch");
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			System.out.println(user.getAdmin());
			if (user.getAdmin()) {
				try {
					Product product = new ObjectMapper().readValue(productJson, Product.class);

					String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
				//	String directoryPath = "C:\\Users\\Dell\\Desktop\\CDAC\\Training\\Module 7 - WPJ\\Project\\Tushar\\RoadSide\\RoadSide\\RoadSide\\roadside\\public\\product_images";
					String directoryPath = "C:\\Users\\ashok\\Desktop\\ADV_JAVA_Project\\roadside\\roadside\\public\\product_images";

					createDirectoryIfNotExists(directoryPath); // Create directory if it doesn't exist
					String filePath = directoryPath + "/" + fileName;
					file.transferTo(new File(filePath));
					product.setImg(fileName);

					productService.updateProduct(product);

					return "Product with ID : " + product.getProductId() + " Added";
				} catch (IOException e) {
					e.printStackTrace();
					return "Error processing the product data";
				} catch (Exception e) {
					e.printStackTrace();
					return "Internal Server Error Contact Admin";
				}
			}
		}
		return "Admin Login Failed";
	}

	@PostMapping("/deleteContact")
	public String deleteContact(@RequestBody Contact contact, HttpSession session) {
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			if (user.getAdmin()) {
				contactUsRepo.delete(contact);
				return "Entry Deleted";
			}
		}
		return "Admin Login Failed";
	}

	private void createDirectoryIfNotExists(String directoryPath) throws IOException {
		Path path = Paths.get(directoryPath);
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}
	}

}
