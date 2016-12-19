package com.sample.product.dao;

import java.util.List;

import com.sample.product.entity.Product;

public interface ProductDAO {
	
	public List<Product> getList();
	public void insert(Product aProduct);

	public Product get(long id);
	
	public void update(Product aProduct);
	
	public void delete(long id);

	public int count();

	public List<Product> getReorderList();
	public List<Product> getAvailableList();
	public Product get(Product aProduct);
	
	public List<Product> getList3(String ser,String stype);
	public List<Product> getList4(String c);

}//ProductDAO

