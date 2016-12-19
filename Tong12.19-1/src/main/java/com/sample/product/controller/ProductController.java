package com.sample.product.controller;

//import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sample.product.entity.Account;
import com.sample.product.entity.Manager;
import com.sample.product.entity.Product;
import com.sample.product.entity.PurchaseOrder;
import com.sample.product.entity.ShoppingCart;
import com.sample.product.dao.ManagerDAO;
import com.sample.product.dao.ProductDAO;
import com.sample.product.dao.PurchaseOrderDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	
	@RequestMapping(value = "pOrder", method = RequestMethod.GET)
	public ModelAndView showPurchase(@ModelAttribute Product a){
		PurchaseOrderDAO p = (PurchaseOrderDAO) context.getBean("purchaseOrderDAO");
		ModelAndView model = new ModelAndView("purchaseOrder");
		return model;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView model = new ModelAndView("product");
		return model;
	}//getProductList
	
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ModelAndView getProductList(){
		ModelAndView model = new ModelAndView("product");
		ProductDAO dao = (ProductDAO) context.getBean("productDAO");
		List<Product> list = dao.getList();
		model.addObject(list);
		return model;
	}//getProductList
	
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView getMemberList(){
		ModelAndView model = new ModelAndView("manager");
		ManagerDAO dao = (ManagerDAO) context.getBean("managerDAO");
		List<Manager> list = dao.getList();
		model.addObject(list);
		return model;
	}
	
	@RequestMapping(value = "/managerSalesmen", method = RequestMethod.GET)
	public ModelAndView memberSales(){
		ModelAndView model = new ModelAndView("managerSalesmen");
		ManagerDAO dao = (ManagerDAO) context.getBean("managerDAO");
		List<Manager> list = dao.getList();
		model.addObject(list);
		return model;
	}
	
	@RequestMapping(value = "/managerWarehouse", method = RequestMethod.GET)
	public ModelAndView memberWarehouse(){
		ModelAndView model = new ModelAndView("managerWarehouse");
		ManagerDAO dao = (ManagerDAO) context.getBean("managerDAO");
		List<Manager> list = dao.getList();
		model.addObject(list);
		return model;
	}
	
	@RequestMapping(value = "/managerAfterSales", method = RequestMethod.GET)
	public ModelAndView memberAftersales(){
		ModelAndView model = new ModelAndView("managerAfterSales");
		ManagerDAO dao = (ManagerDAO) context.getBean("managerDAO");
		List<Manager> list = dao.getList();
		model.addObject(list);
		return model;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		ModelAndView model = new ModelAndView("product");
		ProductDAO dao = (ProductDAO) context.getBean("productDAO");
		List<Product> list = dao.getList();
		model.addObject(list);
		return model;
	}//getProductList

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(){
		ModelAndView model = new ModelAndView("redirect:/product");
		
		return model;
	}//getProductList
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchpage(@ModelAttribute("search") String ser,@ModelAttribute("stype") String stype){
		ModelAndView model = new ModelAndView("product");
		ProductDAO dao = (ProductDAO) context.getBean("productDAO");
		List<Product> list = dao.getList3(ser,stype);
	
		model.addObject(list);
		return model;
	}
	
	@RequestMapping(value = "/search1", method = RequestMethod.POST)
	public ModelAndView search1(){
		ModelAndView model = new ModelAndView("redirect:/productcon");
		
		return model;
	}//getProductList
	@RequestMapping(value = "/search1", method = RequestMethod.GET)
	public ModelAndView search1page(@ModelAttribute("search") String ser,@ModelAttribute("stype") String stype){
		ModelAndView model = new ModelAndView("productcon");
		ProductDAO dao = (ProductDAO) context.getBean("productDAO");
		List<Product> list = dao.getList3(ser,stype);
	
		model.addObject(list);
		return model;
	}
	
	@RequestMapping(value = "/search2", method = RequestMethod.POST)
	public ModelAndView search2(){
		ModelAndView model = new ModelAndView("redirect:/productCust");
		
		return model;
	}//getProductList
	@RequestMapping(value = "/search2", method = RequestMethod.GET)
	public ModelAndView search2page(@ModelAttribute("search") String ser,@ModelAttribute("stype") String stype){
		ModelAndView model = new ModelAndView("productCust");
		ProductDAO dao = (ProductDAO) context.getBean("productDAO");
		List<Product> list = dao.getList3(ser,stype);
	
		model.addObject(list);
		return model;
	}
	@RequestMapping(value = "/searchCat", method = RequestMethod.POST)
	public ModelAndView searchCat(){
		ModelAndView model = new ModelAndView("redirect:/product");
		
		return model;
	}
	@RequestMapping(value = "/searchCat", method = RequestMethod.GET)
	public ModelAndView searchCat(@ModelAttribute("c") String c){
		ModelAndView model = new ModelAndView("product");
		ProductDAO dao = (ProductDAO) context.getBean("productDAO");
		List<Product> list = dao.getList4(c);
		System.out.println(c);
		model.addObject(list);
		return model;
	}
	
	@RequestMapping(value = "/searchCat1", method = RequestMethod.POST)
	public ModelAndView searchCat1(){
		ModelAndView model = new ModelAndView("redirect:/productCust");
		
		return model;
	}
	@RequestMapping(value = "/searchCat1", method = RequestMethod.GET)
	public ModelAndView searchCat1(@ModelAttribute("c") String c){
		ModelAndView model = new ModelAndView("productCust");
		ProductDAO dao = (ProductDAO) context.getBean("productDAO");
		List<Product> list = dao.getList4(c);
		System.out.println(c);
		model.addObject(list);
		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(){
		return getProductList();
	}//getProductList
	//list all products	
	
	@RequestMapping(value = "/productCust", method = RequestMethod.POST)
	public ModelAndView productCust(){
		ModelAndView model = new ModelAndView("productCust");
		ProductDAO dao = (ProductDAO) context.getBean("productDAO");
		List<Product> list = dao.getList();
		model.addObject(list);
		return model;
	}
	@RequestMapping(value = "/productCust", method = RequestMethod.GET)
	public ModelAndView productCustomer(){
		ModelAndView model = new ModelAndView("productCust");
		ProductDAO dao = (ProductDAO) context.getBean("productDAO");
		List<Product> list = dao.getList();
		model.addObject(list);
		return model;
	}
	
	@RequestMapping(value = "/productSalesmen", method = RequestMethod.POST)
	public ModelAndView productcon(){
		ModelAndView model = new ModelAndView("productcon");
		ProductDAO dao = (ProductDAO) context.getBean("productDAO");
		List<Product> list = dao.getList();
		model.addObject(list);
		return model;
	}//getProductList
	
	@RequestMapping(value = "/productSalesmen", method = RequestMethod.GET)
	public ModelAndView productconUser(){
		ModelAndView model = new ModelAndView("productcon");
		ProductDAO dao = (ProductDAO) context.getBean("productDAO");
		List<Product> list = dao.getList();
		model.addObject(list);
		return model;
	}//getProductList
	
	@RequestMapping(value = "/insertProduct", method = RequestMethod.GET)
	public ModelAndView insertProductPage(){
		ModelAndView model = new ModelAndView("insertProduct");
		return model;
	}//insertProductPage
	
	@RequestMapping(value = "/insertProduct", method = RequestMethod.POST)
	public ModelAndView insertProduct(@ModelAttribute Product product){
		ModelAndView model = new ModelAndView("redirect:/productcon");	
		ProductDAO dao = (ProductDAO) context.getBean("productDAO");
		product.setId(dao.count());
		dao.insert(product);
		return model;
	}//insertProduct
	
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	public ModelAndView deleteProduct(@ModelAttribute Product product, long id){
		ModelAndView model = new ModelAndView("redirect:/productcon");	
		ProductDAO dao = (ProductDAO) context.getBean("productDAO");
		product.setId(dao.count());
		dao.delete(id);
		return model;
	}
	
	@RequestMapping(value = "/updateProduct", method = RequestMethod.GET)
	public ModelAndView updateproductpage(@ModelAttribute Product product){
		ModelAndView model = new ModelAndView("updateproduct");
		ProductDAO dao = (ProductDAO) context.getBean("productDAO");
		product = dao.get(product.getId());
		model.addObject("product", product);
		return model;
	}//insertProductPage

	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public ModelAndView modify(@ModelAttribute Product product){
		ModelAndView model = new ModelAndView("redirect:/productcon");	
		ProductDAO dao = (ProductDAO) context.getBean("productDAO");
		//product.setId(dao.count());
		dao.update(product);
		
		return model;
	}//insertProduct
		
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView checkLogin(@ModelAttribute Account account,HttpServletRequest request, Manager man) {
		ModelAndView model = new ModelAndView("redirect:/product");	
		Account account_session = (Account)context.getBean("account");
		ManagerDAO dao = (ManagerDAO)context.getBean("managerDAO");
		
		if(account.getName().equals(dao.get(account.getName()).getName()) && account.getPassword().equals(dao.get(account.getName()).getPassword())){
			account_session.setName(account.getName());
			System.out.println("Success");
			model = new ModelAndView("redirect:/productCust");
			HttpSession session = request.getSession();
			session.setAttribute("username",account.getName());
			String user=(String) session.getAttribute("username");
			System.out.println(user);
		}
		else if(("sales".equals(account.getName()))&&"sales".equals(account.getPassword())){
			System.out.println("Sales Success");
			model = new ModelAndView("productSalesmen");
			HttpSession session = request.getSession();
			session.setAttribute("username",account.getName());
			String user=(String) session.getAttribute("username");
			System.out.println(user);
		}
		else if(("warehouse".equals(account.getName()))&&"warehouse".equals(account.getPassword())){
			System.out.println("Warehouse Success");
			model = new ModelAndView("productWarehouse");
			HttpSession session = request.getSession();
			session.setAttribute("username",account.getName());
			String user=(String) session.getAttribute("username");
			System.out.println(user);
		}
		else if(("aftersales".equals(account.getName()))&&"aftersales".equals(account.getPassword())){
			System.out.println("aftersales Success");
			model = new ModelAndView("productAfterSales");
			HttpSession session = request.getSession();
			session.setAttribute("username",account.getName());
			String user=(String) session.getAttribute("username");
			System.out.println(user);
		}
		else{
			account_session.setName("");
			System.out.println("failed");
			model = new ModelAndView("redirect:/product");
			
		}	
		//System.out.println("model:"+account.getUsername());
		//System.out.println("session:"+account_session.getName());
		
		return model;
	}
	
	
	
    @RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public ModelAndView newuser(){
		ModelAndView model = new ModelAndView("newuser");
		return model;
	}
    
    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public ModelAndView newuser(@ModelAttribute Manager manager){
		ModelAndView model = new ModelAndView("redirect:product");	
		ManagerDAO dao = (ManagerDAO) context.getBean("managerDAO");
		manager.setId(dao.count());
		dao.insert(manager);
		return model;
	}
}//ProductController
