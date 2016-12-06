package com.rohit.form.model;

public class Product {

	private Integer id;
	private String productName;
	private Integer productPrice;
	private Integer productPrice2;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	
	public boolean isNew() {
		return (this.id == null);
	}

}
