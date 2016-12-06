package com.rohit.form.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rohit.form.model.Product;
import com.rohit.form.service.ProductService;
import com.rohit.form.validator.ProductFormValidator;

@Controller
public class ProductController {
	
	private final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductFormValidator productFormValidator;
	
	private ProductService productService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(productFormValidator);
	}

	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("index()");
		return "redirect:/products";
	}

	// list page
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String showAllproducts(Model model) {

		
		model.addAttribute("products", productService.findAll());
		return "products/list";

	}

	// save or update product
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String saveOrUpdateProduct(@ModelAttribute("productForm") Product product,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

		logger.debug("saveOrUpdateProduct() : {}", product);

		if (result.hasErrors()) {
			
			return "products/productform";
		} else {

			redirectAttributes.addFlashAttribute("css", "success");
			if(product.isNew()){
				redirectAttributes.addFlashAttribute("msg", "Product added successfully!");
			}else{
				redirectAttributes.addFlashAttribute("msg", "Product updated successfully!");
			}
			
			productService.saveOrUpdate(product);
			
			
			return "redirect:/products/" + product.getId();

			

		}

	}

	
	@RequestMapping(value = "/products/add", method = RequestMethod.GET)
	public String showAddProductForm(Model model) {

		logger.debug("showAddProductForm()");

		Product product = new Product();

		// set default value
		product.setProductName("default name");
		product.setProductPrice(123);
		

		model.addAttribute("productForm", product);

		

		return "products/productform";

	}

	// show update form
	@RequestMapping(value = "/products/{id}/update", method = RequestMethod.GET)
	public String showUpdateProductForm(@PathVariable("id") int id, Model model) {

		

		Product product = productService.findById(id);
		model.addAttribute("productForm", product);
		
		
		
		return "products/productform";

	}

	
	@RequestMapping(value = "/products/{id}/delete", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable("id")@ModelAttribute() int id, final RedirectAttributes redirectAttributes) {

		logger.debug("deleteProduct() : {}", id);

		productService.delete(id);
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Product is deleted!");
		
		return "redirect:/products";

	}

	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public String showProduct(@PathVariable("id") int id, Model model) {

		logger.debug("showProduct() id: {}", id);

		Product product = productService.findById(id);
		if (product == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Product not found");
		}
		model.addAttribute("product", product);

		return "products/show";

	}

	


}
