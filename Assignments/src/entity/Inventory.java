package entity;

import java.util.*;
import exception.InsufficientStockException;
import exception.InvalidDataException;
import java.time.LocalDateTime;


public class Inventory {
	private int InventoryID;
	private Products product;
	private int QuantityInStock;
	private LocalDateTime LastStockUpdate;
	public static ArrayList<Inventory> inventoryList = new ArrayList<>();
	
//-------------------------constructor-------------------------------------
	public Inventory(int inventoryID, Products product, int quantityInStock) throws InvalidDataException {
        this.setInventoryID ( inventoryID);
        this.setProduct ( product);
        this.setQuantityInStock (quantityInStock);
        this.LastStockUpdate = LocalDateTime.now();
        
        inventoryList.add(this);
    }
	
	
	
//----------------------------get product-----------------------------------
	 public Products GetProduct() {
	        return product;
	    }
//-----------------------------get quantity--------------------------------
	
	    public int GetQuantityInStock() {
	        return QuantityInStock;
	    }
	
//-----------------------------Add items to inventory-----------------------------
	public void AddToInventory(int quantity) throws InvalidDataException{
        if (quantity > 0) {
            QuantityInStock += quantity;
            LastStockUpdate = LocalDateTime.now();
            System.out.println(quantity + " quantity added to inventory.");
        } else {
        	throw new InvalidDataException("Quantity must be positive.");
        }
    }
	

//================================remove items=============================
	public void RemoveFromInventory(int quantity) throws InsufficientStockException {
        if (quantity > 0 && quantity <= QuantityInStock) {
            QuantityInStock -= quantity;
            LastStockUpdate = LocalDateTime.now();
            System.out.println(quantity + " units removed from inventory.");
        } else {
        	throw new InsufficientStockException("Invalid or Not enough quantity to remove.");
        }
    }
	
	
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^Updating the stock value to new value^^^^^^^^^^^^^^^^^^^
	public void UpdateStockQuantity(int newQuantity) throws InvalidDataException {
        if (newQuantity >= 0) {
            QuantityInStock = newQuantity;
            LastStockUpdate = LocalDateTime.now();
            System.out.println("Stock updated to: " + newQuantity);
        } else {
        	throw new InvalidDataException("Stock quantity cannot be negative.");
        }
    }
	
//---------------------------------is product available--------------------
	 public boolean IsProductAvailable(int quantityToCheck) {
		 
	        return QuantityInStock >= quantityToCheck;
	    }

//---------------------------------get inventory value-----------------------\
	
	    public double GetInventoryValue() {
	        return product.getPrice() * QuantityInStock;
	    }

	    
//-----------------------------Last stock updated on-------------------------
	 
	    public LocalDateTime getLastStockUpdate() {
	        return LastStockUpdate;
	    }
	    
//-------------------------------products that are low in stock---------------
	    
	    public static void ListLowStockProducts(int threshold) {
	        System.out.println(" Products in low stock:");
	        for (Inventory inv : inventoryList) {
	            if (inv.QuantityInStock > 0 && inv.QuantityInStock < threshold) {
	                System.out.println("Product: " + inv.product.getProductName() + " | Quantity: " + inv.QuantityInStock);
	            }
	        }
	    }
	    
	    
//-----------------------------Out of stock products----------------------------
	    public static void ListOutOfStockProducts() {
	        System.out.println("Out of Stock Products:");
	        for (Inventory inv : inventoryList) {
	            if (inv.QuantityInStock == 0) {
	                System.out.println( inv.product.getProductName());
	            }
	        }
	        
	        
	    } 
	    
	    
	    
//-------------------------------to list all products---------------------------
	    public static void ListAllProducts() {
	        System.out.println("All Products in Inventory:");
	        for (Inventory inv : inventoryList) {
	            System.out.println("Product: " + inv.product.getProductName() + " | Quantity: " + inv.QuantityInStock);
	        }
	    }
	    
	    
//--------------------------getters and setters---------------------------------
	    public void setInventoryID(int inventoryID) throws InvalidDataException{
	        if (inventoryID <= 0) {
	            throw new InvalidDataException("Inventory ID must be a positive number.");
	        }
	        this.InventoryID = inventoryID;
	    }

	    public void setProduct(Products product) throws InvalidDataException{
	        if (product == null) {
	            throw new InvalidDataException("Product must not be null.");
	        }
	        this.product = product;
	    }

	    public void setQuantityInStock(int quantityInStock) throws InvalidDataException{
	        if (quantityInStock < 0) {
	            throw new InvalidDataException("Quantity in stock cannot be negative.");
	        }
	        this.QuantityInStock = quantityInStock;
	    }
	    
	    
	    public int getInventoryID() {
	    	return InventoryID;
	    }

	    public Products getProduct() {
	    	return product;
	    }


}
