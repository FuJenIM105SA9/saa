package com.sample.product.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;







//import com.mysql.jdbc.Statement;
import com.sample.product.entity.Product;
import com.sample.product.entity.SalesOrder;
import com.sample.product.entity.ShoppingCart;
import com.sample.product.dao.SalesOrderDAO;

public class SalesOrderDAODB implements SalesOrderDAO{
	private DataSource dataSource;
	private Connection conn = null ;
	private ResultSet rs = null ;
	private PreparedStatement smt = null ;
	private PreparedStatement smt2 = null ;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int sellProduct(List<Long> pList,long mid) throws SQLException {
		    
		if (pList.size() == 0){
			return 0; // if nothing in the shopping cart
		}
		long orderID = 0;
		int result = 0;
		int count = 0; // count how many sales order items were processed successfully
		
	    PreparedStatement stCreateOrder = null;
		PreparedStatement stUpdateProduct = null;
		PreparedStatement stInsertOrderItem = null;


		try {
		      
			//Connect to a database
			conn = dataSource.getConnection();
			conn.setAutoCommit(false); //make it a transaction
			/*
			String sql = "INSERT INTO salesOrder (OrderTime) VALUES(Now())";
			st1 = conn.prepareStatement(sql);
			st1.executeUpdate();
			st1.close();
		    */
			
			// get order id for MS Access and SQL Server
			/*
			st2 = conn.prepareStatement("SELECT @@IDENTITY");
			ResultSet rs = st2.executeQuery();
		      if (rs != null && rs.next()) {
		    	  orderID = rs.getInt(1);
		      }
		      st2.close();
		      //System.out.println(orderID+"is created");
			*/
		      
			// get order id for MySQL
			
			String sqlCreateOrder = "INSERT INTO salesOrder (OrderTime) VALUES(Now())";
		    stCreateOrder  = conn.prepareStatement(sqlCreateOrder, PreparedStatement.RETURN_GENERATED_KEYS);

		    result = stCreateOrder.executeUpdate();
		    ResultSet rs = stCreateOrder.getGeneratedKeys();
		    if (rs != null && rs.next()) {
		    	orderID = rs.getLong(1);
		    }
		   
		    System.out.println("order id:"+orderID);
		    stCreateOrder.close();		    
		      
		    for (long productID:pList){
		    	//the following two SQL have to be done in the same transaction
		    	//Issue a query and get a result
		    	stUpdateProduct = conn.prepareStatement("UPDATE product SET Inventory = Inventory - 1 WHERE ProductId = ?");
		    	stUpdateProduct.setLong(1,productID);
		    	result = stUpdateProduct.executeUpdate();
		    	stUpdateProduct.close();
		    	//System.out.println(productID+"is updated");
		    	String sqlInsertOrderItem = "INSERT INTO salesOrderitem (SOID, ProductID, Quantity, ManagerID, State ) VALUES(?, ?, 1, ? , 'Paid' )";
		    	stInsertOrderItem = conn.prepareStatement(sqlInsertOrderItem);
		    	stInsertOrderItem.setLong(1,orderID);
		    	stInsertOrderItem.setLong(2,productID);
		    	stInsertOrderItem.setLong(3,mid);
		    	result = stInsertOrderItem.executeUpdate();
		    	stInsertOrderItem.close();
		    	System.out.println(productID+"is processed");
		    	count ++;
		      }// for all products on the cart
		      
		      conn.commit();
		      conn.close();
		      
		} // try
    	catch (Exception e) {
    		count = 0;
    		e.printStackTrace();
	  		if (conn!= null) { 
	  			try { 
	  				System.err.print("Transaction is being rolled back"); 
	  				conn.rollback(); } 
	  			catch(SQLException excep){
	  				e.printStackTrace(); 
	  			}
	  		}
	    } finally { 
		  	  if (stCreateOrder != null) {
		  	   stCreateOrder.close(); }
		  	  if (stUpdateProduct != null) { 
		  	   stUpdateProduct.close(); }
		  	  if (stInsertOrderItem != null) { 
			  	   stInsertOrderItem.close(); }
		  	  conn.close();
		} 
	    return count;
	} //sellProduct

public void arrive(long pid,long soid) {
		//String sql = "SELECT SOID FROM SalesOrder "
		String sql = "UPDATE salesorderitem SET CustArrivalTime = Now() , State = 'Arrived' "
				+ "WHERE SOID = ? AND ProductID = ?";
		System.out.println("pid="+pid+"soid="+soid);
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setLong(1, soid);
			smt.setLong(2, pid);
			
			smt.executeUpdate();			
			smt.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
	}
public void delivery(long pid,long soid) {
	//String sql = "SELECT SOID FROM SalesOrder "
	String sql = "UPDATE salesorderitem SET DeliveryTime = Now() , State = 'Deliveried' "
			+ "WHERE SOID = ? AND ProductID = ?";
	System.out.println("pid="+pid+"soid="+soid);
	try {
		conn = dataSource.getConnection();
		smt = conn.prepareStatement(sql);
		smt.setLong(1, soid);
		smt.setLong(2, pid);
		
		smt.executeUpdate();			
		smt.close();

	} catch (SQLException e) {
		throw new RuntimeException(e);

	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
	
}
public List<SalesOrder> getList1(long mid){
	
	String sql = "SELECT ProductID FROM SalesOrderItem";
	return getList(sql);
}
public List<SalesOrder> getList2(long mid){
	String sql = "SELECT product.ProductID,product.Category, product.Description, product.Price, salesorder.SOID,salesorder.OrderTime, salesorderitem.id, salesorderitem.DeliveryTime, salesorderitem.CustArrivalTime,salesorderitem.State, salesorderitem.ManagerID " 
+ "FROM product join salesorderitem ON product.ProductID = salesorderitem.ProductID "
+ "join salesorder ON salesorderitem.SOID = salesorder.SOID "
			+ "WHERE salesorderitem.ManagerID = "+mid+"";
	return getList(sql);
	
}

public List<SalesOrder> getList3(long mid, long soid, long pid){
	String sql = "SELECT product.ProductID,product.Category, product.Description, product.Price, salesorder.SOID,salesorder.OrderTime, salesorderitem.CustArrivalTime,salesorderitem.State " 
+ "FROM product join salesorderitem ON product.ProductID = salesorderitem.ProductID "
+ "join salesorder ON salesorderitem.SOID = salesorder.SOID "
			+ "WHERE salesorderitem.ManagerID = "+mid+" AND salesorderitem.SOID = " + soid + " AND product.ProductID = " + pid + "";
	return getList(sql);
	
}

public List<SalesOrder> getList4(){
	String sql = "SELECT product.ProductID,product.Category, product.Description, product.Price, salesorder.SOID,salesorder.OrderTime, salesorderitem.id,salesorderitem.DeliveryTime,salesorderitem.CustArrivalTime,salesorderitem.State,salesorderitem.ManagerID " 
+ "FROM product join salesorderitem ON product.ProductID = salesorderitem.ProductID "
+ "join salesorder ON salesorderitem.SOID = salesorder.SOID ";
	return getList(sql);
}
public List<SalesOrder> getList5(){
	String sql = "SELECT product.ProductID,product.Category, product.Description, product.Price, salesorder.SOID,salesorder.OrderTime, salesorderitem.id, salesorderitem.DeliveryTime, salesorderitem.CustArrivalTime,salesorderitem.State,salesorderitem.ManagerID " 
+ "FROM product join salesorderitem ON product.ProductID = salesorderitem.ProductID "
+ "join salesorder ON salesorderitem.SOID = salesorder.SOID "
+ "WHERE salesorderitem.State = 'Paid'";
	return getList(sql);
}
public List<SalesOrder> getList6(){
	String sql = "SELECT product.ProductID,product.Category, product.Description, product.Price, salesorder.SOID,salesorder.OrderTime, salesorderitem.id, salesorderitem.DeliveryTime, salesorderitem.CustArrivalTime,salesorderitem.State,salesorderitem.ManagerID " 
+ "FROM product join salesorderitem ON product.ProductID = salesorderitem.ProductID "
+ "join salesorder ON salesorderitem.SOID = salesorder.SOID "
+ "WHERE salesorderitem.State = 'Return Requested'";
	return getList(sql);
}

public List<SalesOrder> getList7(){
	String sql = "SELECT product.ProductID,product.Category, product.Description, product.Price, salesorder.SOID,salesorder.OrderTime, salesorderitem.id, salesorderitem.DeliveryTime, salesorderitem.CustArrivalTime,salesorderitem.State,salesorderitem.ManagerID " 
+ "FROM product join salesorderitem ON product.ProductID = salesorderitem.ProductID "
+ "join salesorder ON salesorderitem.SOID = salesorder.SOID "
+ "WHERE salesorderitem.State = 'Change Requested'";
	return getList(sql);
}


public List<SalesOrder> getList(String sql) {
	
	List<SalesOrder> SalesOrderList = new ArrayList<SalesOrder>();
	try {
		conn = dataSource.getConnection();
		smt = conn.prepareStatement(sql);
		rs = smt.executeQuery();
		int i=0;
		while(rs.next()){
			SalesOrder asale = new SalesOrder();
			asale.setId(i);
			i++;
			asale.setAutoid(rs.getLong("id"));
			asale.setProductId(rs.getLong("ProductID"));	
			asale.setSoid(rs.getLong("SOID"));	
			asale.setCategory(rs.getString("Category"));			
			asale.setDesc(rs.getString("Description"));	
			asale.setPrice(rs.getDouble("Price"));	
			asale.setOrderTime(rs.getDate("OrderTime"));
			asale.setCustArrivalTime(rs.getDate("CustArrivalTime"));
			asale.setState(rs.getString("State"));
			asale.setManid(rs.getLong("ManagerID"));
			asale.setDeliveryTime(rs.getDate("DeliveryTime"));
			SalesOrderList.add(asale);
		}
		rs.close();
		smt.close();

	} catch (SQLException e) {
		throw new RuntimeException(e);

	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
	return SalesOrderList;
}

// TODO Auto-generated method stub

}
	



