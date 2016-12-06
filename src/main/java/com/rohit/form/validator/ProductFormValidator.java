package com.rohit.form.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rohit.form.model.Product;
import com.rohit.form.service.ProductService;
@Component
public class ProductFormValidator implements Validator {
	@Autowired
	ProductService productService;

	@Override
	public void validate(Object target, Errors errors) {

		Product product = (Product) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "NotEmpty.productForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productPrice", "NotEmpty.productForm.price");

		

	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.equals(clazz);
	}

}
