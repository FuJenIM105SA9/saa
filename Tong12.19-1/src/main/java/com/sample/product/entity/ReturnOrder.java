package com.sample.product.entity;

import java.sql.Date;

public class ReturnOrder {
    private long rId;
    private long productId;
    private long managerId;
    private long soid;
    private Date returnOrderTime;
    private Date returnConfirmTime;
    private String reason;


    /* getters and setters */
    public long getRId(){
            return rId;
    }
    public void setRId(long rId){
    	this.rId = rId;
    }

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

	public Date getReturnOrderTime(){
		return returnOrderTime;
	}
    public void setReturnOrderTime(Date returnOrderTime){
    	this.returnOrderTime = returnOrderTime;
    }

    public Date getReturnConfirmTime(){
		return returnConfirmTime;
	}
    public void setReturnConfirmTime(Date returnConfirmTime){
    	this.returnConfirmTime = returnConfirmTime;
    }
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

}
