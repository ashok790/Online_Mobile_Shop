package com.cdac.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="mobile_product")
public class Product implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private int productId;
	
	private String brand;
	private String model;
	private float sellingPrice;
	private float costPrice;
	private int ram;
	private int storage;
	private String color;
	private int quantity;
	@JsonIgnore
	private byte[] image;
	private String img;
	private boolean resell;
	
	
	@OneToMany(mappedBy = "product")
	@JsonBackReference
	private List<GroupOrder> groupOrder;


	public int getProductId() {
		return productId;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public float getSellingPrice() {
		return sellingPrice;
	}


	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}


	public float getCostPrice() {
		return costPrice;
	}


	public void setCostPrice(float costPrice) {
		this.costPrice = costPrice;
	}


	public int getRam() {
		return ram;
	}


	public void setRam(int ram) {
		this.ram = ram;
	}


	public int getStorage() {
		return storage;
	}


	public void setStorage(int storage) {
		this.storage = storage;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public boolean isResell() {
		return resell;
	}


	public void setResell(boolean resell) {
		this.resell = resell;
	}


	public List<GroupOrder> getGroupOrder() {
		return groupOrder;
	}


	public void setGroupOrder(List<GroupOrder> groupOrder) {
		this.groupOrder = groupOrder;
	}
	
	
	
	
}
