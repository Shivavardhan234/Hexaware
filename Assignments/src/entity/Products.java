package entity;

import java.util.*;

import exception.InvalidDataException;





public class Products {
	private int ProductID;
	private String ProductName;
	private String Description;
	private double Price;
	
	 private static List<Products> productsList = new ArrayList<>();
	
	private Scanner sc=new Scanner(System.in);
	
	
//-------------------------constructor-----------------------------------------------------
	
	public Products(int productID, String productName, String description, double price)throws InvalidDataException
    {
		for (Products p : productsList) {
            if (p.getProductID() == productID ||
                p.getProductName().equalsIgnoreCase(productName)) {
                throw new IllegalArgumentException("Product with same ID or name already exists.");
            }
        }
        this.setProductID ( productID);
        this.setProductName (productName);
        this.setDescription (description);
        this.setPrice ( price);
        productsList.add(this);
    }
	
	
//---------------------------to get product details----------------------------------------
	public void getProductDetails() {
		System.out.println("ProductID: "+ this.ProductID);
		System.out.println("Product Name: "+ this.ProductName);
		System.out.println("Product Description: "+ this.Description);
		System.out.println("Price : "+ this.Price);

	}
	
//----------------------------getters and setters-----------------------------------------
	
	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) throws InvalidDataException {
		if (productID <= 0) {
	        throw new InvalidDataException("Product ID must be greater than 0.");
	    }
	    this.ProductID = productID;
	}
	
	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName)throws InvalidDataException {
		if (productName == null || productName.trim().isEmpty()) {
	        throw new InvalidDataException("Product name cannot be empty.");
	    }
	    this.ProductName = productName;
	}
	
	
	public String getDescription() {
		return Description;
	}

	public void setDescription(String description)throws InvalidDataException {
		if (description == null || description.trim().isEmpty()) {
	        throw new InvalidDataException("Product description cannot be empty.");
	    }
	    this.Description = description;
	}
	
	public double getPrice() {
		return Price;
	}

	public void setPrice(double price)throws InvalidDataException {
		if (price < 0) {
	        throw new InvalidDataException("Price cannot be negative.");
	    }
	    this.Price = price;
	}
	
	
	
//-------------------------to update product info-----------------------------------------
	public void UpdateProductInfo() {
		System.out.println(" Enter 1 to update product price \n Enter 2 to update Product description ");
		int c =sc.nextInt();
		sc.nextLine();
		switch (c) {
		case 1:
			System.out.println(" Enter updated Price  ");
			float newprice=sc.nextFloat();
			System.out.println("-----Price successfully updated-----");
			System.out.println("Old Price: "+ this.Price);
			this.Price=newprice;
			System.out.println("New Price: "+ this.Price);
			break;
		case 2:
			System.out.println(" Enter updated Product Description.  ");
			
			String newdesc=sc.next();
			System.out.println("-----Product Description successfully updated-----");
			System.out.println("Old Description: "+ this.Description);
			this.Description=newdesc;
			System.out.println("New Description: "+ this.Description);
			break;
		
			
		default:
			System.out.println("Choose appropriate choice");
			
		}
	}

	
	//-----------------------is in stock------------------------------
	public void IsProductInStock() {
	    boolean found = false;

	    for (Inventory inv : Inventory.inventoryList) {
	        if (inv.GetProduct().equals(this) && inv.GetQuantityInStock() > 0) {
	            System.out.println("Product '" + this.getProductName() + "' is in stock. Quantity: " + inv.GetQuantityInStock());
	            found = true;
	            break;
	        }
	    }

	    if (!found) {
	        System.out.println("Product '" + this.getProductName() + "' is currently out of stock.");
	    }
	}
	
	
//----------------collections(product list methods)-----------------------------
	public static List<Products> getAllProducts() {
        return productsList;
    }
	
	
	//------------------adding into list----------------------------------------
	public static void addProduct(Products product) {
	    for (Products p : productsList) {
	        if (p.getProductID() == product.getProductID() ||
	            p.getProductName().equalsIgnoreCase(product.getProductName())) {
	            throw new IllegalArgumentException("Product with same ID or name already exists.");
	        }
	    }
	    productsList.add(product);
	    System.out.println("Product added successfully: " + product.getProductName());
	}
	
	//-------------------------------remove product---------------------------------
	public static void removeProduct(Products product) {
	    if (product == null || !productsList.contains(product)) {
	        System.out.println("Product not found in the list.");
	        return;
	    }

	    // Optional: Add logic here to check for existing orders before removing
	    productsList.remove(product);
	    System.out.println("Product removed successfully: " + product.getProductName());
	}

//--------------------------------update product---------------------------------------
	public static void updateProduct(Products product)throws InvalidDataException {
	    if (product == null) {
	        System.out.println("Product is null. Cannot update.");
	        return;
	    }

	    for (Products p : productsList) {
	        if (p.getProductID() == product.getProductID()) {
	            p.setProductName(product.getProductName());
	            p.setDescription(product.getDescription());
	            p.setPrice(product.getPrice());
	            System.out.println("Product updated successfully: " + product.getProductName());
	            return;
	        }
	    }

	    System.out.println("Product not found in the list.");
	}
	
//-----------------searching for a product-------------------------------------
	public static void searchByName(String name) {
	    boolean found = false;

	    for (Products product : productsList) {
	        if (product.getProductName().equalsIgnoreCase(name)) {
	            System.out.println("Product found:");
	            product.getProductDetails();  // Custom method to print details
	            found = true;
	        }
	    }

	    if (!found) {
	        System.out.println("No product found with the name: " + name);
	    }
	}



}
