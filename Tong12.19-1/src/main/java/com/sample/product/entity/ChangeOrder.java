package com.sample.product.entity;

import java.sql.Date;

public class ChangeOrder {
    private long cId;
    private long productId;
    private long managerId;
    private long soid;
    private Date changeOrderTime;
    private Date changeConfirmTime;
    private String Category;
    private String Desc;
    
	public long getcId() {
		return cId;
	}
	public void setcId(long cId) {
		this.cId = cId;
	}
	public long getSoid() {
		return soid;
	}
	public void setSoid(long soid) {
		this.soid = soid;
	}
	public long getManagerId() {
		return managerId;
	}
	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public Date getChangeOrderTime() {
		return changeOrderTime;
	}
	public void setChangeOrderTime(Date changeOrderTime) {
		this.changeOrderTime = changeOrderTime;
	}
	public Date getChangeConfirmTime() {
		return changeConfirmTime;
	}
	public void setChangeConfirmTime(Date changeConfirmTime) {
		this.changeConfirmTime = changeConfirmTime;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}


    /* getters and setters */
    
}
