package com.sample.product.dao;

import java.sql.SQLException;
import java.util.List;

import com.sample.product.entity.Product;
import com.sample.product.entity.ChangeOrder;
import com.sample.product.entity.Manager;
import com.sample.product.entity.ReturnOrder;


public interface ChangeOrderDAO {	
	
	public void insert(long mid, long pid, long soid);
	//public void update(long mid, long pid, long soid, String reason);
	public void confirmchange(long pid,long soid) ;
	public List<ChangeOrder> getList();
	public List<ChangeOrder> getList(String sql);

}
