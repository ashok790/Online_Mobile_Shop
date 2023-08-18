package com.cdac.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.GroupOrder;
import com.cdac.entity.Order;
import com.cdac.entity.Product;
import com.cdac.entity.User;
import com.cdac.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private ProductService productService;
	
	public Order placeOrder(List<GroupOrder> orders, User user) {
		Order mainOrder =  new Order();
		float totalValue = 0;
//		String date = "13-07-2023";
//		LocalDate deliveryDate = LocalDate.parse(date);
		List<Product> products = new ArrayList<Product>();
		for (GroupOrder order : orders) {
			int id = order.getProduct().getProductId();
			Product product = productService.findProduct(id);
			if (product.getQuantity() >= order.getOrderQuantity() ) {
				order.setProduct(product);
				order.setOrder(mainOrder);
				order.setPrice(product.getSellingPrice());
				product.setQuantity(product.getQuantity() - order.getOrderQuantity());
				products.add(product);
				totalValue += product.getSellingPrice()*order.getOrderQuantity();
			}else {
				throw new IllegalArgumentException(product.getBrand());
			}
		}
		for(Product product : products) {
//			Product updateProduct = productService.findProduct(product.getProductId());
//			updateProduct.setQuantity(updateProduct.getQuantity() - product.getQuantity());
			productService.updateQty(product);
		}
		mainOrder.setUser(user);
		mainOrder.setOrderDate(LocalDate.now());
		mainOrder.setGroupOrder(orders);
		mainOrder.setDeliveryCharge(100);
		mainOrder.setOrderValue(totalValue);
		Order placed = orderRepo.save(mainOrder);
		
		
		return placed;
	}
	
	public List<Order> listAll(){
		return orderRepo.findAll();
	}
	
	public double totalOrderValue() {
		double sum = 0 ;
		List<Order> orders = orderRepo.findAll();
		for (Order order : orders ) {
			sum += order.getOrderValue();
		}
		return sum;
	}
}
