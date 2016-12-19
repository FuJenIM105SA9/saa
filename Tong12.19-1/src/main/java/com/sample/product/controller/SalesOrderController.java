package com.sample.product.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sample.product.dao.SalesOrderDAO;
import com.sample.product.dao.PurchaseOrderDAO;
import com.sample.product.dao.ReturnOrderDAO;
import com.sample.product.dao.AllowanceOrderDAO;
import com.sample.product.dao.ChangeOrderDAO;
import com.sample.product.dao.ManagerDAO;
import com.sample.product.dao.ProductDAO;
import com.sample.product.entity.AllowanceOrder;
import com.sample.product.entity.ChangeOrder;
import com.sample.product.entity.Product;
import com.sample.product.entity.ReturnOrder;
import com.sample.product.entity.SalesOrder;
import com.sample.product.entity.ShoppingCart;


@Controller
public class SalesOrderController {
	ApplicationContext context =  new ClassPathXmlApplicationContext("beans.xml");
	//private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	//configuration for session, please refer to: http://tuhrig.de/making-a-spring-bean-session-scoped/
	
	@RequestMapping(value = "/addShoppingCart", method = RequestMethod.GET)
	public ModelAndView addShoppingCart(@ModelAttribute Product product){
		ModelAndView model = new ModelAndView("redirect:/productCust");
		//only id is passed
		long pid = product.getId();
		System.out.println("pid="+pid);
		ProductDAO dao = (ProductDAO)context.getBean("productDAO");
		product = dao.get(product);//retrieve all information with id
		ShoppingCart shoppingCart = (ShoppingCart)context.getBean("shoppingCart"); 
		int i =0;
		shoppingCart.add(product);
		/*do{
			shoppingCart.add(product);
			i++;
		}while(pid != (shoppingCart.getCart()).get(i).getId());*/
		
		
		/*if(shoppingCart.count()== 0){
			shoppingCart.add(product);
		}
		else{
			for(int i=0;i<shoppingCart.count();i++){
				System.out.println("pid: " + pid + " test: "+ (shoppingCart.getCart()).get(i).getId()+ " count: " + shoppingCart.count());
				if(pid == (shoppingCart.getCart()).get(i).getId()){
					System.out.println("FAIL");
					
					break;
				}else{
					shoppingCart.add(product);
					break;
				}
			}
		}*/
		//System.out.println(shoppingCart.count());
		return model;
	}
	
	@RequestMapping(value = "/deleteShopping", method = RequestMethod.GET)
	public ModelAndView deleteShopping(@ModelAttribute Product product, @ModelAttribute ("id") int id){
		ModelAndView model = new ModelAndView("shoppingcart");	
		ShoppingCart dao = (ShoppingCart) context.getBean("shoppingCart");
		List<Product> content =  dao.getCart();
		System.out.println("id"+id);
		dao.delete(id);
		System.out.println("products in cart:"+content.size());
		List<Product> content2 =  dao.getCart();
		model.addObject("shoppingCart",content2);
		return model;
	}
	
	@RequestMapping(value = "/showCart", method = RequestMethod.GET)
	public ModelAndView showShoppingCart(){
		ModelAndView model = new ModelAndView("shoppingcart");
		ShoppingCart shoppingCart = (ShoppingCart)context.getBean("shoppingCart");
		List<Product> content =  shoppingCart.getCart();
		System.out.println("products in cart:"+content.size());
		model.addObject("shoppingCart",content);
		return model;
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	 public ModelAndView checkout(HttpServletRequest request){
		HttpSession session = request.getSession();

		String user=(String) session.getAttribute("username");
		ManagerDAO managerDAO = (ManagerDAO)context.getBean("managerDAO");
		long mid = managerDAO.get(user).getId();
	  ModelAndView model = new ModelAndView("shoppingcart");
	  ShoppingCart shoppingCart = (ShoppingCart)context.getBean("shoppingCart");
	  SalesOrderDAO salesOrderDAO = (SalesOrderDAO)context.getBean("SalesOrderDAO");
	  List<Product> pList =  shoppingCart.getCart();
	  System.out.println("plist:"+pList.size());
	  List<Long> pList2 = new ArrayList<Long>();
	
	  for (int i=0; i<pList.size();i++){
	   pList2.add(pList.get(i).getId());
	   System.out.println("id:"+pList.get(i).getId());
	   System.out.println("mid:"+mid);
	   model.addObject("salesOrder",pList);
	 
	   
	  }
	  int result = 0;
	  try {
	   result = salesOrderDAO.sellProduct(pList2,mid);
	  } catch (SQLException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	  System.out.println("result="+result);
	  if (result != 0){ //successfully updated, clean up shopping cart
	   shoppingCart.cleanup();
	  }
	  return model;
	 }
	
	@RequestMapping(value = "/arrive", method = RequestMethod.GET)
	public ModelAndView arrive(@ModelAttribute("id") int id,HttpServletRequest request){
         ModelAndView model = new ModelAndView("SalesOrder");
         HttpSession session = request.getSession();

 		String user=(String) session.getAttribute("username");
 		ManagerDAO managerDAO = (ManagerDAO)context.getBean("managerDAO");
 		long mid = managerDAO.get(user).getId();
		  SalesOrderDAO dao = (SalesOrderDAO)context.getBean("SalesOrderDAO");
		  List<SalesOrder> SalesOrderList = dao.getList2(mid);
		  long pid= SalesOrderList.get(id).getProductId();
		  long soid= SalesOrderList.get(id).getSoid();
		  dao.arrive(pid,soid);
		  List<SalesOrder> SalesOrderList2 = dao.getList2(mid);
			System.out.println("111pid="+pid+"soid="+soid);
			model.addObject("SalesOrderList",SalesOrderList2);
		 
		  return model;
		 }
		
	

	@RequestMapping(value = "/bought", method = RequestMethod.GET)
	public ModelAndView getSalesOrderList(HttpServletRequest request){
		ModelAndView model = new ModelAndView("SalesOrder");
		HttpSession session = request.getSession();

		String user=(String) session.getAttribute("username");
		ManagerDAO managerDAO = (ManagerDAO)context.getBean("managerDAO");
		long mid = managerDAO.get(user).getId();
		SalesOrderDAO dao = (SalesOrderDAO) context.getBean("SalesOrderDAO");
		
		List<SalesOrder> SalesOrderList = dao.getList2(mid);
		//System.out.println("category:"+SalesOrderList.get(0).getCategory());
		model.addObject("SalesOrderList",SalesOrderList);
		
		return model;
	}
	@RequestMapping(value = "/returnProduct", method = RequestMethod.GET)
	public ModelAndView returnList(@ModelAttribute ("id") int id, HttpServletRequest request){
		ModelAndView model = new ModelAndView("SalesOrder");
         HttpSession session = request.getSession();
 		String user=(String) session.getAttribute("username");
 		ManagerDAO managerDAO = (ManagerDAO)context.getBean("managerDAO");
 		long mid = managerDAO.get(user).getId();
		  ReturnOrderDAO dao = (ReturnOrderDAO)context.getBean("ReturnOrderDAO");
		  SalesOrderDAO sdao = (SalesOrderDAO)context.getBean("SalesOrderDAO");
		  List<SalesOrder> SalesOrderList = sdao.getList2(mid);
		  long pid= SalesOrderList.get(id).getProductId();
		  long soid= SalesOrderList.get(id).getSoid();
		  dao.insert(mid, pid, soid);
		  List<SalesOrder> SalesOrderList2 = sdao.getList2(mid);
			//model.addObject("ReturnOrderList",ReturnOrderList);
			model.addObject("SalesOrderList",SalesOrderList2);
		  return model;
	}
	
	@RequestMapping(value = "/changeProduct", method = RequestMethod.GET)
	public ModelAndView changeProduct(@ModelAttribute ("id") int id, HttpServletRequest request){
		ModelAndView model = new ModelAndView("SalesOrder");
         HttpSession session = request.getSession();
 		String user=(String) session.getAttribute("username");
 		ManagerDAO managerDAO = (ManagerDAO)context.getBean("managerDAO");
 		long mid = managerDAO.get(user).getId();
 		 ChangeOrderDAO dao = (ChangeOrderDAO)context.getBean("ChangeOrderDAO");
		  SalesOrderDAO sdao = (SalesOrderDAO)context.getBean("SalesOrderDAO");
		  List<SalesOrder> SalesOrderList = sdao.getList2(mid);
		  long pid= SalesOrderList.get(id).getProductId();
		  long soid= SalesOrderList.get(id).getSoid();
		  dao.insert(mid, pid, soid);;
			//model.addObject("ReturnOrderList",ReturnOrderList);
			model.addObject("SalesOrderList",SalesOrderList);
		  return model;
	}
	
	/*@RequestMapping(value = "/requestReturn", method = RequestMethod.GET)
	public ModelAndView requestReturn(@ModelAttribute ("id") int id, HttpServletRequest request){
		ModelAndView model = new ModelAndView("returnProduct");
         HttpSession session = request.getSession();
 		String user=(String) session.getAttribute("username");
 		ManagerDAO managerDAO = (ManagerDAO)context.getBean("managerDAO");
 		long mid = managerDAO.get(user).getId();
		  ReturnOrderDAO dao = (ReturnOrderDAO)context.getBean("ReturnOrderDAO");
		  SalesOrderDAO sdao = (SalesOrderDAO)context.getBean("SalesOrderDAO");
		  List<SalesOrder> SalesOrderList = sdao.getList2(mid);
		  long pid= SalesOrderList.get(id).getProductId();
		  long soid= SalesOrderList.get(id).getSoid();
		  SalesOrderList = sdao.getList3(mid, soid, pid);
			System.out.println("111pid="+pid+"soid="+soid);
			//model.addObject("ReturnOrderList",ReturnOrderList);
			model.addObject("SalesOrderList",SalesOrderList);
		  return model;
	}*/
	
	@RequestMapping(value = "/sale", method = RequestMethod.GET)
	public ModelAndView getSalesOrderListCon(){
		ModelAndView model = new ModelAndView("SalesCon");
		SalesOrderDAO dao = (SalesOrderDAO) context.getBean("SalesOrderDAO");
		
		List<SalesOrder> SalesOrderList = dao.getList4();

		model.addObject("SalesOrderList",SalesOrderList);
		return model;
	}
	@RequestMapping(value = "/sas", method = RequestMethod.GET)
	public ModelAndView beforedelivery(){
         ModelAndView model = new ModelAndView("SalesOrderCon");
         
		  SalesOrderDAO dao = (SalesOrderDAO)context.getBean("SalesOrderDAO");
		  List<SalesOrder> SalesOrderList = dao.getList5();
		 
	
			model.addObject("SalesOrderList",SalesOrderList);
		 
		  return model;
		 }
	@RequestMapping(value = "/sa", method = RequestMethod.GET)
	public ModelAndView delivery(@ModelAttribute ("id") int id){
         ModelAndView model = new ModelAndView("SalesOrderCon");
         
		  SalesOrderDAO dao = (SalesOrderDAO)context.getBean("SalesOrderDAO");
		  List<SalesOrder> SalesOrderList = dao.getList5();
		  long pid= SalesOrderList.get(id).getProductId();
		  long soid= SalesOrderList.get(id).getSoid();
		  dao.delivery(pid,soid);
			System.out.println("id="+id);
			model.addObject("SalesOrderList",SalesOrderList);
		 
		  return model;
		 }
	@RequestMapping(value = "/returnList", method = RequestMethod.GET)
	public ModelAndView beforereturnconfirm(){
         ModelAndView model = new ModelAndView("returnList");
         
		  SalesOrderDAO dao = (SalesOrderDAO)context.getBean("SalesOrderDAO");
		  List<SalesOrder> SalesOrderList = dao.getList6();
		 
	
			model.addObject("SalesOrderList",SalesOrderList);
		 
		  return model;
		 }
	
	/*@RequestMapping(value = "/allowance", method = RequestMethod.GET)
	public ModelAndView allowance(@ModelAttribute ("id") int id, HttpServletRequest request){
		ModelAndView model = new ModelAndView("SalesOrder");
         HttpSession session = request.getSession();
 		String user=(String) session.getAttribute("username");
 		ManagerDAO managerDAO = (ManagerDAO)context.getBean("managerDAO");
 		long mid = managerDAO.get(user).getId();
 		AllowanceOrderDAO dao = (AllowanceOrderDAO)context.getBean("AllowanceOrderDAO");
		
		  SalesOrderDAO sdao = (SalesOrderDAO)context.getBean("SalesOrderDAO");
		  List<SalesOrder> SalesOrderList = sdao.getList2(mid);
		  long pid= SalesOrderList.get(id).getProductId();
		  long soid= SalesOrderList.get(id).getSoid();
		  dao.insert(mid, pid, soid);
			//model.addObject("ReturnOrderList",ReturnOrderList);
			model.addObject("SalesOrderList",SalesOrderList);
		  return model;
	}*/
	@RequestMapping(value = "/uploadAllowanceFile", method = RequestMethod.GET)
	public ModelAndView allowance(@ModelAttribute AllowanceOrder allowance,@ModelAttribute SalesOrder SalesOrder,HttpServletRequest request){
		ModelAndView model = new ModelAndView("custallowance");
		HttpSession session = request.getSession();
 		String user=(String) session.getAttribute("username");
 		ManagerDAO managerDAO = (ManagerDAO)context.getBean("managerDAO");
        SalesOrderDAO dao = (SalesOrderDAO) context.getBean("SalesOrderDAO");
 		long mid = managerDAO.get(user).getId();
 		List<SalesOrder> SalesOrderList = dao.getList2(mid);
 		long pid=SalesOrderList.get((int)SalesOrder.getAutoid()-1).getProductId();
 		SalesOrder.setProductId(pid);
		System.out.println("autoid1:"+SalesOrder.getAutoid());
		System.out.println("pid:"+pid);
		System.out.println("mid:"+mid);
		System.out.println("soid:"+SalesOrder.getSoid());
        allowance.setsoid(SalesOrder.getSoid());
        //allowance.setProductId(pid); 
        allowance.setManagerId(mid);
       //model.addObject("allowance",allowance);
        model.addObject("SalesOrder",SalesOrder);
	    return model;
	  
	  
	}
	@RequestMapping(value = "/aConfirm", method = RequestMethod.GET)
	public ModelAndView allowanceConfirm(@ModelAttribute SalesOrder SalesOrder,@ModelAttribute ("detail")String detail, @ModelAttribute ("autoid") long autoid,HttpServletRequest request){
		ModelAndView model = new ModelAndView("SalesOrder");
		HttpSession session = request.getSession();
 		String user=(String) session.getAttribute("username");
 		AllowanceOrderDAO dao = (AllowanceOrderDAO)context.getBean("AllowanceOrderDAO");
 		ManagerDAO managerDAO = (ManagerDAO)context.getBean("managerDAO");
 		  SalesOrderDAO sdao = (SalesOrderDAO) context.getBean("SalesOrderDAO");
 	 		long mid = managerDAO.get(user).getId();
 	 		List<SalesOrder> SalesOrderList = sdao.getList2(mid);
 	 		long pid=SalesOrderList.get((int)SalesOrder.getAutoid()-1).getProductId();
 	 		SalesOrder.setProductId(pid);
 		
 		pid = SalesOrder.getProductId();
 		
		System.out.println("autoid2:"+autoid);
		System.out.println("soid2:"+SalesOrder.getSoid());
		System.out.println("pid2:"+pid);
		System.out.println("mid2:"+mid);
		System.out.println("detail:"+detail);
	
		dao.insert(mid, pid, SalesOrder.getSoid() ,detail);
		
		
		  model.addObject("SalesOrderList",SalesOrderList);
	    return model;
	  
	  
	}
	/*@RequestMapping(value = "/uploadAllowanceFile", method = RequestMethod.GET)
	public ModelAndView uploadAllowanceFile(@ModelAttribute SalesOrder SalesOrder) {
		ModelAndView model = new ModelAndView("custallowance");
		System.out.println("autoid2:"+SalesOrder.getAutoid());
		
		model.addObject("SalesOrder",SalesOrder);
		return model;
	}*/
	 @RequestMapping(value = "/uploadAllowanceFile", method = RequestMethod.POST)
	    public ModelAndView uploadFileHandler(@ModelAttribute SalesOrder SalesOrder,@ModelAttribute("file") MultipartFile file, HttpServletRequest request) {
	    	ModelAndView model = new ModelAndView("custallowance");
	    	
	    	//save it as the file name submitted 
	    	//String name = file.getOriginalFilename();
	    	System.out.println("autoid:"+SalesOrder.getAutoid());
	    	String name =SalesOrder.getAutoid()+".jpg";
	        String filePath = request.getSession().getServletContext().getRealPath("/") + "resources\\allowanceFileUpload\\";  
	    	//
	        File dir = new File(filePath);
	        if (!dir.exists()){
	            dir.mkdirs();
	        }
	        try {
	            file.transferTo(new File(filePath+name));
	            System.out.println("Server File Location="+ filePath+name);
	            model.addObject("message","Your file is uploaded:" + file.getOriginalFilename());
	        } catch (IOException e) {
	        	model.addObject("message","You failed to upload:" + file.getOriginalFilename() + " => " + e.getMessage());
	            e.printStackTrace();
	        }
	        model.addObject("SalesOrder",SalesOrder);
	        return model;
	    }
	
	@RequestMapping(value = "/allowanceList", method = RequestMethod.GET)
	public ModelAndView allowanceList(){
         ModelAndView model = new ModelAndView("allowanceList");
         
		  SalesOrderDAO dao = (SalesOrderDAO)context.getBean("SalesOrderDAO");
		  List<SalesOrder> SalesOrderList = dao.getList6();
		 
	
			model.addObject("SalesOrderList",SalesOrderList);
		 
		  return model;
		 }
	
	@RequestMapping(value = "/allowanceListfinal", method = RequestMethod.GET)
	public ModelAndView allowanceListfinal(){
         ModelAndView model = new ModelAndView("allowanceListfinal");
         
         AllowanceOrderDAO dao = (AllowanceOrderDAO)context.getBean("AllowanceOrderDAO");
		  List<AllowanceOrder> AllowanceOrderList = dao.getList();
		 
	
			model.addObject("AllowanceOrderList",AllowanceOrderList);
		 
		  return model;
		 }
	
	@RequestMapping(value = "/returnconfirm", method = RequestMethod.GET)
	public ModelAndView returnconfirm(@ModelAttribute ("id") int id){
         ModelAndView model = new ModelAndView("returnList");
         ReturnOrderDAO rdao = (ReturnOrderDAO)context.getBean("ReturnOrderDAO");
		  SalesOrderDAO dao = (SalesOrderDAO)context.getBean("SalesOrderDAO");
		  List<SalesOrder> SalesOrderList = dao.getList6();
		  long pid= SalesOrderList.get(id).getProductId();
		  long soid= SalesOrderList.get(id).getSoid();
		 rdao.confirmreturn(pid, soid);
			System.out.println("id="+id);
			model.addObject("SalesOrderList",SalesOrderList);
		 
		  return model;
		 }
	@RequestMapping(value = "/changeList", method = RequestMethod.GET)
	public ModelAndView beforechangeconfirm(){
         ModelAndView model = new ModelAndView("changeList");
         
		  SalesOrderDAO dao = (SalesOrderDAO)context.getBean("SalesOrderDAO");
		  List<SalesOrder> SalesOrderList = dao.getList7();
		 
	
			model.addObject("SalesOrderList",SalesOrderList);
		 
		  return model;
		 }
	@RequestMapping(value = "/changeconfirm", method = RequestMethod.GET)
	public ModelAndView changeconfirm(@ModelAttribute ("id") int id){
         ModelAndView model = new ModelAndView("changeList");
         ChangeOrderDAO cdao = (ChangeOrderDAO)context.getBean("ChangeOrderDAO");
		  SalesOrderDAO dao = (SalesOrderDAO)context.getBean("SalesOrderDAO");
		  List<SalesOrder> SalesOrderList = dao.getList7();
		  long pid= SalesOrderList.get(id).getProductId();
		  long soid= SalesOrderList.get(id).getSoid();
		 cdao.confirmchange(pid, soid);
			System.out.println("id="+id);
			model.addObject("SalesOrderList",SalesOrderList);
		 
		  return model;
		 }
	@RequestMapping(value = "/returnListfinal", method = RequestMethod.GET)
	public ModelAndView returnListfinal(){
         ModelAndView model = new ModelAndView("returnListfinal");
         
		  ReturnOrderDAO dao = (ReturnOrderDAO)context.getBean("ReturnOrderDAO");
		  List<ReturnOrder> ReturnOrderList = dao.getList();
		 
	
			model.addObject("ReturnOrderList",ReturnOrderList);
		 
		  return model;
		 }
	@RequestMapping(value = "/changeListfinal", method = RequestMethod.GET)
	public ModelAndView changeListfinal(){
         ModelAndView model = new ModelAndView("changeListfinal");
         
         ChangeOrderDAO dao = (ChangeOrderDAO)context.getBean("ChangeOrderDAO");
		  List<ChangeOrder> ChangeOrderList = dao.getList();
		 
	
			model.addObject("ChangeOrderList",ChangeOrderList);
		 
		  return model;
		 }
}
