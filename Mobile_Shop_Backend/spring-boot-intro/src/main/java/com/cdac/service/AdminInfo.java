package com.cdac.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdac.entity.Contact;
import com.cdac.entity.Order;
import com.cdac.entity.Product;
import com.cdac.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Component
public class AdminInfo implements Serializable{
	
	//private List<User> users;
	
	@Autowired
	@JsonIgnore
	private OrderService orderService;
	
	private List<Product> prodcuts;
	private List<OrderAdminInfo> orders;
	private List<User> users;
	private List<Contact> contacts;
	private double revenue;
	
	
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public OrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public List<OrderAdminInfo> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderAdminInfo> orders) {
		this.orders = orders;
	}
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	public List<Product> getProdcuts() {
		return prodcuts;
	}
	public void setProdcuts(List<Product> prodcuts) {
		this.prodcuts = prodcuts;
	}
	
	
	
}
