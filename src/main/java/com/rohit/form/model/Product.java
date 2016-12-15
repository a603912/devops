package com.rohit.form.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Product.
 *http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=1605088
 * @author a603912
 */
public class Product { 

	/** The id.test */
	private Integer id;
	
	/** The product name. */
	private String productName;
	
	/** The product price. */
	private Integer productPrice;
	
	/** The product price 2. */
	private Integer productPrice2;


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the product name.
	 *
	 * @return the product name
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the product name.
	 *
	 * @param productName the new product name
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * Gets the product price.
	 *
	 * @return the product price
	 */
	public Integer getProductPrice() {
		return productPrice;
	}

	/**
	 * Sets the product price.
	 *
	 * @param productPrice the new product price
	 */
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	
	/**
	 * Checks if is new.
	 *
	 * @return true, if is new
	 */
	public boolean isNew() {
		return (this.id == null);
	}

}
