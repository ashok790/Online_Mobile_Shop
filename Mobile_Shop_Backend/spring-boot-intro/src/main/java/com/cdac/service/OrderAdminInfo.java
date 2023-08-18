package com.cdac.service;

import java.time.LocalDate;
import java.util.List;

import com.cdac.entity.Address;
import com.cdac.entity.GroupOrder;
import com.cdac.entity.Order;
import com.cdac.entity.User;

public class OrderAdminInfo {
	private int orderId;
	private float orderValue;
	private LocalDate orderDate;
	private int userId;
	private String userName;
	
	private Address address;
	private List<GroupOrder> groupOrder;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public float getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(float orderValue) {
		this.orderValue = orderValue;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<GroupOrder> getGroupOrder() {
		return groupOrder;
	}
	public void setGroupOrder(List<GroupOrder> groupOrder) {
		this.groupOrder = groupOrder;
	}
	
	
}
