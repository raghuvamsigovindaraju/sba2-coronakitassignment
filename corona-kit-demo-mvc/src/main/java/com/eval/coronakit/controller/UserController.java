package com.eval.coronakit.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.eval.coronakit.entity.CoronaKit;
import com.eval.coronakit.entity.KitDetail;
import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.exception.CoronaKitException;
import com.eval.coronakit.exception.KitDetailsException;
import com.eval.coronakit.exception.ProductException;
import com.eval.coronakit.service.CoronaKitService;
import com.eval.coronakit.service.KitDetailService;
import com.eval.coronakit.service.ProductService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CoronaKitService coronaKitService;
	
	@Autowired
	KitDetailService kitDetailService;
	
	
	@RequestMapping("/home")
	public ModelAndView home() throws ProductException {
		return new ModelAndView("user-home","products",productService.getAllProducts());
	}
	
	@RequestMapping("/show-kit")
	public ModelAndView showKit(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws ProductException {
		

		List<String> ids = (List<String>) session.getAttribute("productids");
		Set<String> uniqueids=new LinkedHashSet<>(ids);
		
			List<ProductMaster> productmasterkititems=new ArrayList<ProductMaster>();
		for(String x:uniqueids)
		  {
			int id=Integer.parseInt(x);
			
				ProductMaster productmasteritemkit = productService.getProductById(id);
				productmasterkititems.add(productmasteritemkit);
			
		
				session.setAttribute("kitproducts", productmasterkititems);
		  }
		
		return new ModelAndView("show-cart","kitproducts",productmasterkititems);
	
	}

	@RequestMapping("/show-list")
	public ModelAndView showList() throws ProductException {
		return new ModelAndView("show-all-item-user","products",productService.getAllProducts());
		}
	
	@RequestMapping("/add-to-cart/{productId}")
	public ModelAndView addKit(@PathVariable("productId") int productId,Model model,HttpSession session) throws ProductException, ServletException, IOException, KitDetailsException 
	{

     
		List<String> ids = (List<String>) session.getAttribute("productids");
	
	   if(ids==null) 
	   {
		 ids = new ArrayList<>();
	   }
	    String id=String.valueOf(productId);
	    if(id!=null)
	    {
	    	ids.add(id);
	    }
	    session.setAttribute("productids", ids);
        model.addAttribute("productids", ids);
		/*
		 * httpSession.setAttribute("productids", ids); //add to table ProductMaster
		 * pmaster=productService.getProductById(productId); KitDetail kd=new
		 * KitDetail(); kd.setProductId(pmaster.getId());
		 * kd.setAmount(pmaster.getCost()); kd.setQuantity(1);
		 * kitDetailService.addKitItem(kd);
		 */
	   
	
	return new ModelAndView("show-all-item-user","products",productService.getAllProducts());
	
	
	}
	
	
	@RequestMapping("/checkout")
	public String checkout(Model model) {
		return null;
	}
	
	@GetMapping("/address-entry")
	public ModelAndView addAddress(String address,Model model) {
		CoronaKit coronakit=new CoronaKit();
		ModelAndView mv = new ModelAndView("checkout-address","coronakit",coronakit);
		mv.addObject("address",true);
		mv.addObject("id",coronakit.getId());
		return mv;
		
	}
	@GetMapping("/finalize")
	public ModelAndView finalizeOrder(@RequestParam("deliveryAddress") String deliveryAddress,Model model,HttpSession session) throws KitDetailsException, CoronaKitException {

		List<KitDetail> kitdetaillist =new ArrayList<KitDetail>();
		int totalAmount=0;
		List<ProductMaster> pmlist=(List<ProductMaster>)session.getAttribute("kitproducts");
		CoronaKit coronaKitItem=new CoronaKit();
		for(ProductMaster m:pmlist)
		{
			KitDetail kitItem=new KitDetail();
			kitItem.setProductId(m.getId());
			kitItem.setQuantity(1);
			kitItem.setCoronaKitId(coronaKitItem.getId());
			kitItem.setAmount(m.getCost());			
			totalAmount=totalAmount+m.getCost();
			
			kitdetaillist.add(kitItem);
			kitDetailService.addKitItem(kitItem);
		}
		coronaKitItem.setTotalAmount(totalAmount);
		coronaKitItem.setDeliveryAddress(deliveryAddress);
		coronaKitItem.setOrderDate(LocalDate.now().toString());
		
		coronaKitService.saveKit(coronaKitItem);
		ModelAndView mv = new ModelAndView("show-summary","kitdetaillist",kitdetaillist);
	 
	   mv.addObject("coronaKitItem",coronaKitItem);
		return mv;
		
	}
	
	@RequestMapping("/delete/{itemId}")
	public ModelAndView deleteItem(@PathVariable("itemId") int itemId,HttpSession session) throws ProductException {
		List<String> ids = (List<String>) session.getAttribute("productids");
		
		   if(ids==null) 
		   {
			 ids = new ArrayList<>();
		   }
		    String id=String.valueOf(itemId);
		    if(id!=null)
		    {
		    	ids.remove(id);
		    }
		    session.setAttribute("productids", ids);
		
	//	session.removeAttribute(String.valueOf(itemId));
		return new ModelAndView("show-all-item-user","products",productService.getAllProducts());
	}
}
