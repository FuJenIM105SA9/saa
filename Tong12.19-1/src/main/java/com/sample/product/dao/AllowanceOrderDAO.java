package com.sample.product.dao;

import java.sql.SQLException;
import java.util.List;

import com.sample.product.entity.Product;
import com.sample.product.entity.AllowanceOrder;
import com.sample.product.entity.Manager;
import com.sample.product.entity.ReturnOrder;
import com.sample.product.entity.SalesOrder;


public interface AllowanceOrderDAO {	
	
	public void insert(long mid, long pid, long soid , String detail);
	//public void update(long mid, long pid, long soid, String reason);

	public void confirmallowance(long pid,long soid) ;
	public List<AllowanceOrder> getList();
	public List<AllowanceOrder> getList(String sql);
	

}
