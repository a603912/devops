package com.rohit.form.service;

import java.util.List;

import com.rohit.form.model.Product;

public interface ProductService {

	Product findById(Integer id);

	List<Product> findAll();

	void saveOrUpdate(Product product);

	void delete(int id);
}
