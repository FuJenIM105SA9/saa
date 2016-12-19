package com.sample.product.entity;

import java.sql.Date;

public class AllowanceOrder {
    private long AId;
    private long productId;
    private long managerId;
    private long soid;
    private Date allowanceOrderTime;
    private Date allowanceConfirmTime;
    private double aPrice;


    /* getters and setters */

	public long getProductId(){
		return productId;
	}
    public void setProductId(long productId){
    	this.productId = productId;
    }
    
    public long getManagerId(){
		return managerId;
	}
    public void setManagerId(long managerId){
    	this.managerId = managerId;
    }
    
    public long getsoid(){
		return soid;
	}
    public void setsoid(long soid){
    	this.soid = soid;
    }
	public long getAId() {
		return AId;
	}
	public void setAId(long aId) {
		AId = aId;
	}
	public Date getAllowanceOrderTime() {
		return allowanceOrderTime;
	}
	public void setAllowanceOrderTime(Date allowanceOrderTime) {
		this.allowanceOrderTime = allowanceOrderTime;
	}
	public Date getAllowanceConfirmTime() {
		return allowanceConfirmTime;
	}
	public void setAllowanceConfirmTime(Date allowanceConfirmTime) {
		this.allowanceConfirmTime = allowanceConfirmTime;
	}
	public double getaPrice() {
		return aPrice;
	}
	public void setaPrice(double aPrice) {
		this.aPrice = aPrice;
	}

}
