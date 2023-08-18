package com.cdac.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "mobile_group_orders")
public class GroupOrder implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_order_id")
	private int groupOrderId;
	
	@ManyToOne(optional = false , cascade = CascadeType.ALL)
	@JsonBackReference(value = "order-groupOrder")
	@JoinColumn(name = "order_id")
	private Order order; 
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id")
	
	private Product product;
	
	private int orderQuantity;
	private float price;
	
	

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getGroupOrderId() {
		return groupOrderId;
	}

	public void setGroupOrderId(int groupOrderId) {
		this.groupOrderId = groupOrderId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	
}
