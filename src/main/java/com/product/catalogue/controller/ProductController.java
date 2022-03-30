package com.product.catalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.product.catalogue.model.Product;
import com.product.catalogue.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	//Display list of products
	@GetMapping("/")
	public String viewHomePage(Model model){
		model.addAttribute("listProducts",productService.getAllProducts());
		return "index";
	}
	@GetMapping("/showNewProductForm")
	public String showNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("product",product);
		return "new_product";
	}
	
	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute("product") Product product) {
		//save product to db
		productService.saveProduct(product);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id")long id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product",product);
		return "update_product";
	}
	
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable (value = "id")long id) {
		this.productService.deleteProductById(id);
		return "redirect:/";
	}
}
