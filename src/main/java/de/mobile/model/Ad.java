package de.mobile.model;

import java.math.BigDecimal;

import de.mobile.util.Category;

public class Ad {

	private Long id;

	private String make;

	private String model;

	private String description;

	private Category category;

	private BigDecimal price;

	private Customer customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the mobileCustomer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param mobileCustomer the mobileCustomer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
