package main;
import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;

import dao.*;
import entity.*;
import exception.InvalidDataException;

public class TechShopAPP {
		
		public static void main(String[] args) throws InvalidDataException ,SQLException {
			Scanner scanner = new Scanner(System.in);   

	        while (true) {
	            System.out.println("\n==== Welcome to TechShop Management System ====");
	            System.out.println("Select a category to manage:");
	            System.out.println("1. Customers");
	            System.out.println("2. Products");
	            System.out.println("3. Orders");
	            System.out.println("4. Order Details");
	            System.out.println("5. Inventory");
	            System.out.println("6. Exit");
	            System.out.print("Enter your choice: ");
	            int category = scanner.nextInt();
	            scanner.nextLine();

	            try {
	                switch (category) {
	                    case 1:
	                        manageCustomers();
	                        break;
	                    case 2:
	                        manageProducts();
	                        break;
	                    case 3:
	                        manageOrders();
	                        break;
	                    case 4:
	                        manageOrderDetails();
	                        break;
	                    case 5:
	                        manageInventory();
	                        break;
	                    case 6:
	                        System.out.println("Exiting TechShop System. Goodbye!");
	                        return;
	                    default:
	                        System.out.println("Invalid choice. Please try again.");
	                }
	            } catch (SQLException e) {
	                System.out.println("Database error: " + e.getMessage());
	            }
	        }
	    }

		
//----------------------------------------Customers-------------------------------------------------------------------------

	    private static void manageCustomers() throws SQLException, InvalidDataException {
	    	Scanner scanner = new Scanner(System.in);
	        Customer_dao dao = new Customer_dao();

	        System.out.println("\n-- Customer Management --");
	        System.out.println("1. Add Customer");
	        System.out.println("2. View All Customers");
	        System.out.println("3. View Customer by ID");
	        System.out.println("4. Update Customer");
	        System.out.println("5. Delete Customer");
	        System.out.print("Enter your choice: ");
	        int choice = scanner.nextInt();
	        scanner.nextLine();

	        try {
	            switch (choice) {
	                case 1 -> {
	                    System.out.print("Enter Customer ID: ");
	                    int id = scanner.nextInt();
	                    scanner.nextLine();
	                    System.out.print("Enter First Name: ");
	                    String fname = scanner.nextLine();
	                    System.out.print("Enter Last Name: ");
	                    String lname = scanner.nextLine();
	                    System.out.print("Enter Email: ");
	                    String email = scanner.nextLine();
	                    System.out.print("Enter Phone: ");
	                    String phone = scanner.nextLine();
	                    System.out.print("Enter Address: ");
	                    String address = scanner.nextLine();

	                    Customers customer = new Customers(id, fname, lname, email, phone, address);
	                    dao.addCustomer(customer);
	                    System.out.println("Customer added successfully.");
	                }

	                case 2 -> dao.getAllCustomers();

	                case 3 -> {
	                    System.out.print("Enter Customer ID: ");
	                    int id = scanner.nextInt();
	                    dao.getCustomerById(id);
	                }

	                case 4 -> {
	                    System.out.print("Enter Customer ID to update: ");
	                    int id = scanner.nextInt();
	                    scanner.nextLine();
	                    System.out.print("Enter new First Name: ");
	                    String fname = scanner.nextLine();
	                    System.out.print("Enter new Last Name: ");
	                    String lname = scanner.nextLine();
	                    System.out.print("Enter new Email: ");
	                    String email = scanner.nextLine();
	                    System.out.print("Enter new Phone: ");
	                    String phone = scanner.nextLine();
	                    System.out.print("Enter new Address: ");
	                    String address = scanner.nextLine();

	                    Customers customer = new Customers(id, fname, lname, email, phone, address);
	                    dao.updateCustomer(customer);
	                    System.out.println("Customer updated successfully.");
	                }

	                case 5 -> {
	                    System.out.print("Enter Customer ID to delete: ");
	                    int id = scanner.nextInt();
	                    dao.deleteCustomer(id);
	                    System.out.println("Customer deleted successfully.");
	                }

	                default -> System.out.println("Invalid choice.");
	            }
	        } catch (SQLException e) {
	            throw new SQLException(" An error occurred while performing the operation: " + e.getMessage());
	            
	        }
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
//-------------------------------------------(^_^)  products  (^_^)  -----------------------------------------------------------------------

	    private static void manageProducts() throws SQLException, InvalidDataException {
	    	Scanner scanner = new Scanner(System.in);
	        product_dao dao = new product_dao();
	        System.out.println("\n-- Product Management --");
	        System.out.println("1. Add Product");
	        System.out.println("2. View All Products");
	        System.out.println("3. View Product by ID");
	        System.out.println("4. Update Product");
	        System.out.println("5. Delete Product");
	        System.out.print("Enter your choice: ");
	        int choice = scanner.nextInt();
	        scanner.nextLine();

	        try {
	            switch (choice) {
	                case 1 -> {
	                    System.out.print("Enter Product ID: ");
	                    int id = scanner.nextInt();
	                    scanner.nextLine();
	                    System.out.print("Enter Product Name: ");
	                    String name = scanner.nextLine();
	                    System.out.print("Enter Product Description: ");
	                    String desc = scanner.nextLine();
	                    System.out.print("Enter Product Price: ");
	                    double price = scanner.nextDouble();

	                    Products product = new Products(id, name, desc, price);
	                    dao.addProduct(product);
	                    System.out.println("✅ Product added successfully.");
	                }

	                case 2 -> dao.getAllProducts();

	                case 3 -> {
	                    System.out.print("Enter Product ID: ");
	                    int id = scanner.nextInt();
	                    dao.getProductById(id);
	                }

	                case 4 -> {
	                    System.out.print("Enter Product ID to update: ");
	                    int id = scanner.nextInt();
	                    scanner.nextLine();
	                    System.out.print("Enter new Product Name: ");
	                    String name = scanner.nextLine();
	                    System.out.print("Enter new Description: ");
	                    String desc = scanner.nextLine();
	                    System.out.print("Enter new Price: ");
	                    double price = scanner.nextDouble();

	                    Products updatedProduct = new Products(id, name, desc, price);
	                    dao.updateProduct(updatedProduct);
	                    System.out.println("Product updated successfully.");
	                }

	                case 5 -> {
	                    System.out.print("Enter Product ID to delete: ");
	                    int id = scanner.nextInt();
	                    dao.deleteProduct(id);
	                    System.out.println("Product deleted successfully.");
	                }

	                default -> System.out.println("Invalid choice.");
	            }
	        } catch (SQLException e) {
	            throw new SQLException(" An error occurred while accessing products: " + e.getMessage());
	            
	        }
	    }
	    
	    
	    

	    
	    
	    
	   
	    
	    
	    
//-------------------------------------------------------orders-----------------------------------------------------------

	    private static void manageOrders() throws SQLException {
	    	Scanner scanner = new Scanner(System.in);
	        orders_dao dao = new orders_dao();
	        System.out.println("\n-- Order Management --");
	        System.out.println("1. Place Order");
	        System.out.println("2. View All Orders");
	        System.out.println("3. View Order by ID");
	        System.out.println("4. Update Order Status");
	        System.out.println("5. Delete Order");
	        System.out.print("Enter your choice: ");
	        int choice = scanner.nextInt();
	        scanner.nextLine();

	        try {
	        	switch (choice) {
	            case 1 -> {
	            	System.out.print("Enter Order ID: ");
	                int orderId = scanner.nextInt();
	                System.out.print("Enter Customer ID: ");
	                int customerId = scanner.nextInt();
	                scanner.nextLine(); 
	                System.out.print("Enter Order Status: ");
	                String status = scanner.nextLine();
	                System.out.print("Enter Total Amount: ");
	                double totalAmount = scanner.nextDouble();
	                scanner.nextLine();

	               
	                dao.addOrder(orderId, customerId, LocalDateTime.now(), status, totalAmount);
	                System.out.println("Order placed successfully!");
	            }
	            case 2 -> dao.getAllOrders();
	            
	            case 3 -> {
	                System.out.print("Enter Order ID: ");
	                int orderId = scanner.nextInt();
	                dao.getOrderById(orderId);
	            }
	            case 4 -> {
	                System.out.print("Enter Order ID: ");
	                int orderId = scanner.nextInt();
	                scanner.nextLine(); 
	                System.out.print("Enter new status: ");
	                String newStatus = scanner.nextLine();
	                dao.updateOrderStatus(orderId, newStatus);
	                System.out.println("Order status updated.");
	            }
	            case 5 -> {
	                System.out.print("Enter Order ID to delete: ");
	                int orderId = scanner.nextInt();
	                dao.deleteOrder(orderId);
	                System.out.println("Order deleted successfully.");
	            }
	            default -> System.out.println("Invalid choice. Please try again.");
	        }
	        }
	        catch (SQLException e) {
	            throw new SQLException(" An error occurred while accessing orders: " + e.getMessage());
	            
	        }
	        
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
//------------------------------------order details---------------------------------------------------------------------------------------------

	    private static void manageOrderDetails() throws SQLException {
	    	Scanner scanner = new Scanner(System.in);
	        OrderDetails_dao dao = new OrderDetails_dao();
	        System.out.println("\n-- Order Details Management --");
	        System.out.println("1. Add Order Details");
	        System.out.println("2. View All Order Details");
	        System.out.println("3. View Order Detail by ID");
	        System.out.println("4. Update Order Details");
	        System.out.println("5. Delete Order Detail");
	        System.out.print("Enter your choice: ");
	        int choice = scanner.nextInt();
	        scanner.nextLine();

	        try {
	            switch (choice) {
	                case 1 -> {
	                    System.out.print("Enter Order Detail ID: ");
	                    int detailId = scanner.nextInt();
	                    System.out.print("Enter Order ID: ");
	                    int orderId = scanner.nextInt();
	                    System.out.print("Enter Product ID: ");
	                    int productId = scanner.nextInt();
	                    System.out.print("Enter Quantity: ");
	                    int quantity = scanner.nextInt();
	                    System.out.print("Enter Discount: ");
	                    float discount = scanner.nextFloat();
	                    scanner.nextLine();

	                    
	                    dao.addOrderDetails(detailId, orderId, productId, quantity, discount);
	                    System.out.println("Order detail added successfully!");
	                }

	                case 2 -> dao.getAllOrderDetails();

	                case 3 -> {
	                    System.out.print("Enter Order Detail ID: ");
	                    int id = scanner.nextInt();
	                    dao.getOrderDetailsById(id);
	                }

	                case 4 -> {
	                    System.out.print("Enter Order Detail ID to update: ");
	                    int detailId = scanner.nextInt();
	                    System.out.print("Enter New Order ID: ");
	                    int orderId = scanner.nextInt();
	                    System.out.print("Enter New Product ID: ");
	                    int productId = scanner.nextInt();
	                    System.out.print("Enter New Quantity: ");
	                    int quantity = scanner.nextInt();
	                    System.out.print("Enter New Discount: ");
	                    float discount = scanner.nextFloat();
	                    scanner.nextLine();

	                
	                    dao.updateOrderDetails(detailId, orderId, productId, quantity, discount);
	                    System.out.println("Order detail updated successfully!");
	                }

	                case 5 -> {
	                    System.out.print("Enter Order Detail ID to delete: ");
	                    int id = scanner.nextInt();
	                    dao.removeOrderDetails(id);
	                    System.out.println("Order detail deleted successfully!");
	                }

	                default -> System.out.println("Invalid choice. Please try again.");
	            }
	        } catch (SQLException e) {
	            throw new SQLException("Error performing order detail operation: " + e.getMessage(), e);
	        }
	    }

	    
	    
	    
	    
	    
	    
//-------------------------------------inventory--------------------------------------------------------------------------------------------------
	    private static void manageInventory() throws SQLException {
	    	Scanner scanner = new Scanner(System.in);
	        inventory_dao dao = new inventory_dao();
	        System.out.println("\n-- Inventory Management --");
	        System.out.println("1. Add Inventory");
	        System.out.println("2. View All Inventories");
	        System.out.println("3. View Inventory by ID");
	        System.out.println("4. Update Inventory");
	        System.out.println("5. Delete Inventory");
	        System.out.print("Enter your choice: ");
	        int choice = scanner.nextInt();
	        scanner.nextLine();

	        try {
	            switch (choice) {
	                case 1 -> {
	                    System.out.print("Enter Inventory ID: ");
	                    int inventoryId = scanner.nextInt();
	                    System.out.print("Enter Product ID: ");
	                    int productId = scanner.nextInt();
	                    System.out.print("Enter Quantity in Stock: ");
	                    int quantity = scanner.nextInt();
	                    scanner.nextLine(); // consume newline
	                    LocalDateTime lastStockUpdate = LocalDateTime.now();

	                    dao.addInventory(inventoryId, productId, quantity, lastStockUpdate);
	                    System.out.println("Inventory added successfully!");
	                }

	                case 2 -> dao.getAllInventories();

	                case 3 -> {
	                    System.out.print("Enter Inventory ID: ");
	                    int id = scanner.nextInt();
	                    dao.getInventoryByInventoryId(id);
	                }

	                case 4 -> {
	                    System.out.print("Enter Inventory ID to update: ");
	                    int inventoryId = scanner.nextInt();
	                    System.out.print("Enter new Product ID: ");
	                    int productId = scanner.nextInt();
	                    System.out.print("Enter new Quantity in Stock: ");
	                    int quantity = scanner.nextInt();
	                    scanner.nextLine(); // consume newline
	                    LocalDateTime lastStockUpdate = LocalDateTime.now();

	                    dao.updateInventory(inventoryId, productId, quantity, lastStockUpdate);
	                    System.out.println("Inventory updated successfully!");
	                }

	                case 5 -> {
	                    System.out.print("Enter Inventory ID to delete: ");
	                    int id = scanner.nextInt();
	                    dao.deleteInventory(id);
	                    System.out.println("Inventory deleted successfully!");
	                }

	                default -> System.out.println("Invalid choice. Please try again.");
	            }
	        } catch (SQLException e) {
	            throw new SQLException("Error performing inventory operation: " + e.getMessage(), e);
	        }
	    }
		
	

}
