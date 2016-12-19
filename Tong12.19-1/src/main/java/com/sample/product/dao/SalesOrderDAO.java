package com.sample.product.dao;

import java.sql.SQLException;
import java.util.List;

import com.sample.product.entity.Product;
import com.sample.product.entity.PurchaseOrder;
import com.sample.product.entity.SalesOrder;


public interface SalesOrderDAO {	
	
	public int sellProduct(List<Long> pList,long mid) throws SQLException;

	public List<SalesOrder> getList(String sql);
	public List<SalesOrder> getList1(long mid);
	public List<SalesOrder> getList2(long mid);
	public List<SalesOrder> getList3(long mid, long soid, long pid);
	public List<SalesOrder> getList4();
	public List<SalesOrder> getList5();
	public List<SalesOrder> getList6();
	public List<SalesOrder> getList7();
	public void arrive(long pid,long soid);
	public void delivery(long pid,long soid) ;
	


}
