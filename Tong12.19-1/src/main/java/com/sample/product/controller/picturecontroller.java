/**
 * Reference:
 * http://www.journaldev.com/2573/spring-mvc-file-upload-example-tutorial-single-and-multiple-files
 */
package com.sample.product.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sample.product.dao.ProductDAO;
import com.sample.product.entity.Product;

@Controller
public class picturecontroller {

	@RequestMapping(value = "/pic", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute Product product) {
		ModelAndView model = new ModelAndView("picture");
		model.addObject(product);
		return model;
	}
	@RequestMapping(value = "/viewpic", method = RequestMethod.GET)
	public ModelAndView viewpic(@ModelAttribute Product product) {
		ModelAndView model = new ModelAndView("picture2");
		model.addObject(product);
		return model;
	}
	@RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
	public ModelAndView home2(@ModelAttribute Product product) {
		ModelAndView model = new ModelAndView("picture");
		model.addObject(product);
		return model;
	}
	@RequestMapping(value = "/return", method = RequestMethod.GET)
	public ModelAndView home3(@ModelAttribute Product product) {
		ModelAndView model = new ModelAndView("redirect:/productcon");
		
		return model;
	}
	@RequestMapping(value = "/return2", method = RequestMethod.GET)
	public ModelAndView home4(@ModelAttribute Product product) {
		ModelAndView model = new ModelAndView("redirect:/product2");
		
		return model;
	}
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ModelAndView uploadFileHandler(@ModelAttribute("file") MultipartFile file, HttpServletRequest request,@ModelAttribute Product product) {
    	ModelAndView model = new ModelAndView("picture");
    	
    	//save it as the file name submitted 
    	//String name = file.getOriginalFilename();
    	System.out.println("id:"+product.getId());
    	String name =product.getId()+".jpg";
        String filePath = request.getSession().getServletContext().getRealPath("/") + "resources\\fileUpload\\";  
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
                
        return model;
    }
}