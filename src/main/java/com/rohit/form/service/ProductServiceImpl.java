package com.rohit.form.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rohit.form.dao.ProductDao;
import com.rohit.form.model.Product;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	ProductDao productDao;

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}



	public ProductDao getProductDao() {
		return productDao;
	}
	
	

	@Override
	public Product findById(Integer id) {
		return productDao.findById(id);
	}

	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public void saveOrUpdate(Product product) {
		if (findById(product.getId()) == null) {
			productDao.save(product);
		} else {
			productDao.update(product);
		}

	}

	@Override
	public void delete(int id) {
		productDao.delete(id);

	}

}
