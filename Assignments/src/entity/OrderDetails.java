package entity;

import exception.InvalidDataException;

public class OrderDetails {
	private int orderDetailID;
	private Orders order;
	private Products product;
	private int quantity;
	private float discount; 
	
	
//-------------------constructorrrrrrr----------------------------
	
	public OrderDetails(int orderDetailID, Orders order, Products product, int quantity,float discount)throws InvalidDataException {
        this.setOrderDetailID ( orderDetailID);
        this.setOrder ( order);
        this.setProduct ( product);
        this.setQuantity ( quantity);
        this.AddDiscount ( discount);
        order.addOrderDetail(this);
    }

	
//---------------------(subtotal =total amount-discount)--------------
	public double CalculateSubtotal() {
        double subtotal = product.getPrice() * quantity;
        double discountAmount = subtotal * (discount / 100.0);
        return subtotal - discountAmount;
    }

	
//-----------------------Getting order detail info----------------------------
	public void GetOrderDetailInfo() {
        System.out.println("Order Detail ID: " + orderDetailID);
        System.out.println("Product: " + product.getProductName());
        System.out.println("Quantity: " + quantity);
        System.out.println("Price per unit: Rs." + product.getPrice());
        System.out.println("Discount: " + discount + "%");
        System.out.println("Subtotal: Rs." + CalculateSubtotal());
        
 
    }
	
	
	
//-----------------------changing or updating the quantity---------------------
    public void UpdateQuantity(int Quantity) throws InvalidDataException{
    	if (quantity <= 0) {
            throw new InvalidDataException("Quantity must be greater than zero.");
        }
        this.quantity = Quantity;
        System.out.println("Quantity updated to: " + Quantity);
    }
    
    
    
//-------------------------Adding the discount----------------------------------
    public void AddDiscount(float discountPercent) throws InvalidDataException{
        if (discountPercent >= 0 && discountPercent <= 100) {
            this.discount = discountPercent;
            System.out.println("Discount of " + discountPercent + "% applied.");
        } else {
        	throw new InvalidDataException("Enter appropriate discount.");
        }
    }
    
 //~~~~~~~~~~~~~~~~~~~~~~~~~~~~{[(Getters and setters)]}~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    public int getOrderDetailID() {
		return orderDetailID;
	}


	public void setOrderDetailID(int orderDetailID) throws InvalidDataException{
		if (orderDetailID <= 0) {
	        throw new InvalidDataException("Order Detail ID must be a positive number.");
	    }
	    this.orderDetailID = orderDetailID;
	}


	public Orders getOrder() {
		return order;
	}


	public void setOrder(Orders order) throws InvalidDataException{
		if (order == null) {
	        throw new InvalidDataException("Order must not be null.");
	    }
	    this.order = order;
	}


	public Products getProduct() {
		return product;
	}


	public void setProduct(Products product) throws InvalidDataException{
		if (product == null) {
	        throw new InvalidDataException("Product must not be null.");
	    }
	    this.product = product;
	}


	public int getQuantity() {
		return quantity;
	}
	
	 private void setQuantity(int Quantity) throws InvalidDataException{
	    	if (quantity <= 0) {
	            throw new InvalidDataException("Quantity must be positive.");
	        }
	        this.quantity = Quantity;
	        
	    }



	public float getDiscount() {
		return discount;
	}


	


}
