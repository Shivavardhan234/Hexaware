package entity;

import java.util.*;

import exception.InvalidDataException;

import java.time.LocalDateTime;


public class Orders {
	
	
	
	
	private int OrderID;
	private Customers customer;
	private LocalDateTime OrderDate;
	private double TotalAmount;
	private String orderStatus;
	private ArrayList<OrderDetails> orderDetailsList;
    
	private static List<Orders> ordersList = new ArrayList<>();



	//--------------------------constructor----------------------------------------
    public Orders(int orderID, Customers customer, LocalDateTime orderDate)throws InvalidDataException
    {
    	this.setOrderID(orderID);
        this.setCustomer ( customer);
        this.setOrderDate ( orderDate);
        this.orderStatus = "Pending";
        this.orderDetailsList = new ArrayList<OrderDetails>();
        this.TotalAmount=CalculateTotalAmount();
        
        ordersList.add(this);
        customer.AddOrder(this);
        sortOrdersByDate();
    }
    

//--------------------------Calculating the total amount------------------------   
    public double CalculateTotalAmount() {
        double Amount = 0;
        for (OrderDetails detail : orderDetailsList) {
            Amount += detail.CalculateSubtotal();
        }
        return Amount;
        
    }
    
//------------------------getting the order details------------------------------    
    public void GetOrderDetails() {
        System.out.println("Order ID: " + OrderID);
        System.out.println("Order Date & time: " + OrderDate);
        System.out.println("Customer Full Name: " + customer.getFirstName() + " " +customer.getLastName()); // Assuming getName() exists
        System.out.println("Order Status: " + orderStatus);
        System.out.println("Orders list:");

        for (OrderDetails ods : orderDetailsList) {
            System.out.println("Product" + ods.getProduct().getProductName() + " | Quantity:" + ods.getQuantity());
        }

        System.out.println("Total Amount: Rs." + TotalAmount);
    }
    
    
    
    
//------------------------updating the order status--------------------------------  
    
    public void UpdateOrderStatus(String Status) {
        this.orderStatus = Status;
        System.out.println("Order status updated to: " + Status);
    }
    
    
    
    
//-------------------------------------getters and setters-------------------------
    public int getOrderID() {
		return OrderID;
	}
    public void setOrderID(int orderID) throws InvalidDataException {
        if (orderID <= 0) {
            throw new InvalidDataException("Order ID must be a positive integer.");
        }
        this.OrderID = orderID;
    }


	public Customers getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customers customer) throws InvalidDataException {
	    if (customer == null) {
	        throw new InvalidDataException("Customer cannot be null.");
	    }
	    this.customer = customer;
	}

	public void setOrderDate(LocalDateTime orderDate) throws InvalidDataException {
	    if (orderDate == null) {
	        throw new InvalidDataException("Order date cannot be null.");
	    }
	    this.OrderDate = orderDate;
	}


	public LocalDateTime getOrderDate() {
		return OrderDate;
	}


	public double getTotalAmount() {
		return TotalAmount;
	}


	
	public ArrayList<OrderDetails> getOrderDetailsList() {
		return orderDetailsList;
	}


	public void setOrderDetailsList(ArrayList<OrderDetails> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	 public static List<Orders> getOrdersList() {
	        return ordersList;
	    }
	 
	 
//-------------------------cancel the order---------------------------------------
	public void CancelOrder() throws InvalidDataException{
	    if (orderStatus.equalsIgnoreCase("Cancelled")) {
	        System.out.println("Order is already cancelled.");
	        return;
	    }

	    for (OrderDetails od : orderDetailsList) {
	        Products prod = od.getProduct();
	        int qty = od.getQuantity();

	 
	        for (Inventory inv : Inventory.inventoryList) {
	            if (inv.GetProduct().equals(prod)) {
	                inv.AddToInventory(qty);
	                break;
	            }
	        }
	    }

	    this.orderStatus = "Cancelled";
	    Orders.ordersList.remove(this);
	    System.out.println("Order ID " + this.OrderID + " has been cancelled and stock levels adjusted.");
	}

	
//------------------adding order details into list-------------------------
	public void addOrderDetail(OrderDetails detail) throws InvalidDataException{
	    if (detail == null) {
	        throw new InvalidDataException("Order detail cannot be null.");
	    }
	    orderDetailsList.add(detail);
	    
	}

//-------------------------methods for collections---------------------------
						//--Adding order--
	public static void addOrder(Orders order) throws InvalidDataException {
	    if (order != null) {
	        ordersList.add(order);
	        System.out.println("Order added successfully.");
	    } else {
	    	throw new InvalidDataException("Order is null. Cannot add.");
	    }
	}
	
//---------------------to sort orders in orderlist by date--------------------
	public static void sortOrdersByDate() {
	    ordersList.sort(Comparator.comparing(Orders::getOrderDate).reversed());
	    System.out.println("Orders sorted by descending date.the most previous one will be on first");
	}


	

}
