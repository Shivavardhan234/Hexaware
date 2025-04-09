package entity;

import java.util.*;

import exception.InvalidDataException;

public class Customers {
	private int CustomerID;
	private String FirstName;
	private String LastName;
	private String Email;
	private String Phone;
	private String Address;
	private Scanner sc=new Scanner(System.in);
	private ArrayList<Orders> customerOrders;
	
//----------------------------constructor----------------------------------
	
    public Customers(int customerID, String firstName, String lastName, String email, String phone, String address)throws InvalidDataException {
        this.setCustomerID(customerID);
        this.setFirstName (firstName);
        this.setLastName ( lastName);
        this.setEmail ( email);
        this.setPhone ( phone);
        this.setAddress (address);
        customerOrders = new ArrayList<Orders>();
    }

    
  //-----------------total orders------------------------------------------ 
	
	public int calculateTotalOrders() {
		return customerOrders.size();
		
	}
	
	
//------------------getting customer details--------------------------------
	public void getCustomerDetails() {
		System.out.println("CustomerID: "+ this.CustomerID);
		System.out.println("Customer First Name: "+ this.FirstName);
		System.out.println("Customer Last Name: "+ this.LastName);
		System.out.println("Email : "+ this.Email);
		System.out.println("Mobile NO.: "+ this.Phone);
		System.out.println("Customer Address: "+ this.Address);
	}
	
//------------updating customer info------------------------------------------
	
	public void UpdateCustomerInfo() throws InvalidDataException{
		System.out.println(" Enter 1 to update Email \n Enter 2 to update Mobile No. \n Enter 3 to update Address ");
		int c =sc.nextInt();
		sc.nextLine();
		switch (c) {
		case 1:
			System.out.println(" Enter updated Email  ");
			String newEmail=sc.next();
			
			System.out.println("Old Email: "+ this.Email);
			this.setEmail(newEmail);
			System.out.println("-----Email successfully updated-----");
			System.out.println("New Email: "+ this.Email);
			break;
		case 2:
			System.out.println(" Enter updated Phone No.  ");
			String newphone=sc.next();
			
			System.out.println("Old Phone No.: "+ this.Phone);
			this.setPhone(newphone);
			System.out.println("-----Phone No. successfully updated-----");
			System.out.println("New Phone No.: "+ this.Phone);
			break;
		case 3:
			System.out.println(" Enter updated Address  ");
			String newAdd=sc.nextLine();
			
			System.out.println("Old Address: "+ this.Address);
			this.setAddress(newAdd);
			System.out.println("-----Address successfully updated-----");
			System.out.println("New Address: "+ this.Address);
			break;
			
		default:
			System.out.println("Choose appropriate choice");
			
		}
		
	}
//-----------------------------------getters and setters-----------------------------
	
	public int getCustomerID() { 
		return CustomerID; 
		}
	
    public void setCustomerID(int customerID) throws InvalidDataException {
    	if (customerID <= 0) {
            throw new InvalidDataException("Customer ID must be a positive integer.");
        }
        this.CustomerID = customerID;
    	}
    
    public String getFirstName() { 
    	return FirstName; 
    	}
    
    public void setFirstName(String firstName) throws InvalidDataException {
    	if (firstName == null || firstName.trim().isEmpty()) {
            throw new InvalidDataException("First name cannot be null or empty.");
        }
        this.FirstName = firstName;
    	}
    
    public String getLastName() { 
    	return LastName;
    	}
    
    public void setLastName(String lastName) throws InvalidDataException{ 
    	if (lastName == null || lastName.trim().isEmpty()) {
            throw new InvalidDataException("Last name cannot be null or empty.");
        }
        this.LastName = lastName;
    	}
    
    public String getEmail() {
    	return Email; 
    	}
    
    public void setEmail(String email)throws InvalidDataException {
    	if (email == null || !email.contains("@") || !(email.endsWith(".com") || email.endsWith(".in"))) {
    	        throw new InvalidDataException("Invalid email address. Must contain '@' and end with '.com' or '.in'.");
    	    }
    	    this.Email = email;
    	}
    
    public String getPhone() {
    	return Phone; 
    	}
    
    public void setPhone(String phone) throws InvalidDataException{
    	if (phone == null || phone.length() != 10 || !phone.matches("\\d+")) {
            throw new InvalidDataException("Phone number must be exactly 10 digits and contain only numbers.");
        }
        this.Phone = phone;
    	}
    
    public String getAddress() { 
    	return Address;
    	}
    
    public void setAddress(String address) throws InvalidDataException{
    	if (address == null || address.trim().isEmpty()) {
            throw new InvalidDataException("Address cannot be null or empty.");
        }
        this.Address = address;
    	}
    
 //-----------------------if order is added on this customer this will add into list-----------------------------
    
    public void AddOrder(Orders order)
    {
        customerOrders.add(order);
    }


}
