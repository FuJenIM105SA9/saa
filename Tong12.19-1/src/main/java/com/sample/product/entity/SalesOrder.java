package com.sample.product.entity;

import java.sql.Date;

public class SalesOrder {
    private long id;
    private long autoid;
    private long productId;
    private long soid;
    private long manid;
    private int quantity;
    private Date OrderTime;
    private Date CustArrivalTime;
    private Date DeliveryTime;
    private double Price;
    private String Category;
    private String Desc;

    private String State;

    /* getters and setters */
    public long getId(){
            return id;
    }
    public void setId(long id){
    	this.id = id;
    }

	public long getProductId(){
		return productId;
	}
    public void setProductId(long productId){
    	this.productId = productId;
    }

    public long getSoid(){
        return soid;
}
public void setSoid(long soid){
	this.soid = soid;
}

	public int getQuantity(){
		return quantity;
	}
    public void setQuantity(int quantity){
    	this.quantity = quantity;
    }

	public Date getOrderTime(){
		return OrderTime;
	}
    public void setOrderTime(Date OrderTime){
    	this.OrderTime = OrderTime;
    }

    public Date getCustArrivalTime(){
		return CustArrivalTime;
	}
    public void setCustArrivalTime(Date CustArrivalTime){
    	this.CustArrivalTime = CustArrivalTime;
    }
    
    public double getPrice(){
		return Price;
}
public void setPrice(double Price){
	this.Price = Price;
}
public String getCategory(){
    return Category;
}
public void setCategory(String Category){
this.Category = Category;
}

public String getDesc(){
return Desc;
}
public void setDesc(String Desc){
this.Desc = Desc;
}


public String getState() {
	return State;
}
public void setState(String state) {
	State = state;
}
public long getManid() {
	return manid;
}
public void setManid(long manid) {
	this.manid = manid;
}
public Date getDeliveryTime() {
	return DeliveryTime;
}
public void setDeliveryTime(Date deliveryTime) {
	DeliveryTime = deliveryTime;
}
public long getAutoid() {
	return autoid;
}
public void setAutoid(long autoid) {
	this.autoid = autoid;
}

}
