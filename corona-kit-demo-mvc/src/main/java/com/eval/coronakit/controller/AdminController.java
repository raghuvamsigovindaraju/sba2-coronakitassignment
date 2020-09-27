package com.eval.coronakit.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.exception.ProductException;
import com.eval.coronakit.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/home")
	public ModelAndView home() throws ProductException {
		return new ModelAndView("admin-home","products",productService.getAllProducts());
	}
	
	@GetMapping("/product-list")
	public ModelAndView showContactsList() throws ProductException {
		return new ModelAndView("show-all-item-admin","products",productService.getAllProducts());
	}
	
	@GetMapping("/product-entry")
	public ModelAndView showContactFormForAdd() {
		ProductMaster productmaster=new ProductMaster();
		ModelAndView mv = new ModelAndView("add-new-item","product",productmaster);
		mv.addObject("isNew",true);
		mv.addObject("id",productmaster.getId());
		return mv;
	}
	
	@GetMapping("/product-edit")
	public ModelAndView showContactFormForEdit(@RequestParam("cid") int contactId) throws ProductException {
		ProductMaster productmaster = productService.getProductById(contactId);
		ModelAndView mv = new ModelAndView("add-new-item","product",productmaster);
		mv.addObject("isNew",false);
		return mv;
	}
	
	@PostMapping("/product-add")
	public ModelAndView doAddContact(@ModelAttribute("product") @Valid ProductMaster product,BindingResult result) throws ProductException {
		ModelAndView mv =null;
		
		if(result.hasErrors()) {
			mv = new ModelAndView("add-new-item","product",product);
			mv.addObject("isNew",true);
		}else {
			productService.addNewProduct(product);
			mv = new ModelAndView("index","msg","Contact Saved Succesfully!");
		}
		
		return mv;
	}
	
	@PostMapping("/product-save")
	public ModelAndView doSaveContact(@ModelAttribute("product") @Valid ProductMaster product,BindingResult result) throws ProductException {
		ModelAndView mv =null;
		
		if(result.hasErrors()) {
			mv = new ModelAndView("add-new-item","product",product);
			mv.addObject("isNew",false);
		}else {
			productService.saveNewProduct(product);
			mv = new ModelAndView("index","msg","Contact Saved Succesfully!");
		}
		
		return mv;
	}
	
	@GetMapping("/product-delete/{productId}")
	public ModelAndView doDeleteContact(@PathVariable("productId") int productId) throws ProductException {
		System.out.println("product id is:"+productId);
		productService.deleteProduct(productId);
		return new ModelAndView("index","msg","Product Deleted Succesfully!");
	}
		
}
