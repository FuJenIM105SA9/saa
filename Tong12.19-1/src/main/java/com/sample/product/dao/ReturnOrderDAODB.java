package com.sample.product.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.sample.product.entity.Product;
import com.sample.product.entity.Manager;
import com.sample.product.entity.ReturnOrder;
import com.sample.product.entity.SalesOrder;

public class ReturnOrderDAODB implements ReturnOrderDAO{
	private DataSource dataSource;
	private Connection conn = null ;
	private ResultSet rs = null ;
	private PreparedStatement smt = null ;
	private PreparedStatement smt2 = null ;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/*public void update(long mid, long pid, long soid, String reason) {
		System.out.println("mid: " + mid);
		System.out.println("pid: " + pid);
		System.out.println("soid: " + soid);
		// remove first parameter when Id is auto-increment
	    String sql = "UPDATE returnOrder SET Reason = ? WHERE SOID = ? AND ProductID = ? AND ManagerID = ?" ;
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setString(1, reason);
			smt.setLong(2, soid);
			smt.setLong(3, pid);
			smt.setLong(4, mid);
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
		
	}*/
	
	public void insert(long mid, long pid, long soid) {
		System.out.println("mid: " + mid);
		System.out.println("pid: " + pid);
		System.out.println("soid: " + soid);
		// remove first parameter when Id is auto-increment
	    String sql = "INSERT INTO returnOrder (ProductID, ManagerID, SOID, returnOrderTime) VALUES(?, ?, ?, NOW())";
	    String sql2 = "UPDATE salesorderitem SET State = 'Return Requested' "
				+ "WHERE SOID = ? AND ProductID = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setLong(1, pid);
			smt.setLong(2, mid);
			smt.setLong(3, soid);
			smt.executeUpdate();			
			smt.close();
			smt2 = conn.prepareStatement(sql2);
			smt2.setLong(1, soid);
			smt2.setLong(2, pid);
			smt2.executeUpdate();			
			smt2.close();
           
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

	
	public void confirmreturn(long pid,long soid) {
		//String sql = "SELECT SOID FROM SalesOrder "
		String sql = "UPDATE returnOrder SET returnConfirmTime = Now() "
				+ "WHERE SOID = ? AND ProductID = ?";
		String sql2 = "UPDATE salesorderitem SET State = 'Return Confirmed' "
				+ "WHERE SOID = ? AND ProductID = ?";
		System.out.println("pid="+pid+"soid="+soid);
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setLong(1, soid);
			smt.setLong(2, pid);
			smt.executeUpdate();			
			smt.close();
			smt2 = conn.prepareStatement(sql2);
			smt2.setLong(1, soid);
			smt2.setLong(2, pid);
			smt2.executeUpdate();			
			smt2.close();

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
	public List<ReturnOrder> getList(){
		String sql = "SELECT * FROM returnorder";
		return getList(sql);
	}
	
	public List<ReturnOrder> getList(String sql) {
		
		List<ReturnOrder> ReturnOrderList = new ArrayList<ReturnOrder>();
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			rs = smt.executeQuery();
			while(rs.next()){
				ReturnOrder aReturn = new ReturnOrder();
				aReturn.setRId(rs.getLong("RID"));			
				aReturn.setProductId(rs.getLong("ProductID"));
				aReturn.setManagerId(rs.getLong("ManagerID"));
				aReturn.setsoid(rs.getLong("SOID"));
				aReturn.setReturnOrderTime(rs.getDate("returnOrderTime"));
				aReturn.setReturnConfirmTime(rs.getDate("returnConfirmTime"));
			
				ReturnOrderList.add(aReturn);
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
		return ReturnOrderList;
	}
}
