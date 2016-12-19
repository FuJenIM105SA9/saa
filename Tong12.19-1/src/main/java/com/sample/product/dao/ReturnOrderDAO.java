package com.sample.product.dao;

import java.sql.SQLException;
import java.util.List;

import com.sample.product.entity.Product;
import com.sample.product.entity.Manager;
import com.sample.product.entity.ReturnOrder;
import com.sample.product.entity.SalesOrder;


public interface ReturnOrderDAO {	
	
	public void insert(long mid, long pid, long soid);
	//public void update(long mid, long pid, long soid, String reason);

	public void confirmreturn(long pid,long soid) ;
	public List<ReturnOrder> getList();
	public List<ReturnOrder> getList(String sql);
	

}
