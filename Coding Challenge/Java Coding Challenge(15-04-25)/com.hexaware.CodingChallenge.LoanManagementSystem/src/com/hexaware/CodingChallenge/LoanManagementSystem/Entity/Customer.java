package com.hexaware.CodingChallenge.LoanManagementSystem.Entity;

import com.hexaware.CodingChallenge.LoanManagementSystem.exceptions.InvalidInputException;

public class Customer {
	private int CustomerID;
	private String Name;
	private String Email;
	private String Address;
	private int creditScore;
	
	//-----------------------------------constructor---------------------------------
	
	public Customer(int customerID, String name, String email, String address, int creditScore) throws InvalidInputException {
        setCustomerID(customerID);
        setName(name);
        setEmail(email);
        setAddress(address);
        setCreditScore(creditScore);
    }





	
	
	//----------------------------setters and getters-------------------------------------------
	
	
	public int getCustomerID() {
		return CustomerID;
	}
	public String getName() {
		return Name;
	}
	public String getEmail() {
		return Email;
	}
	public String getAddress() {
		return Address;
	}
	public int getCreditScore() {
		return creditScore;
	}
	
	
	
	
	
	
	public void setCustomerID(int customerID) throws InvalidInputException {
		if(customerID<=0) {
			throw new InvalidInputException("Customer ID should not be negative or 0");	
		}
		this.CustomerID = customerID;
	}
	
	
	
	public void setName(String name)throws InvalidInputException {
		
		if(name==null|| name.trim().isEmpty()) {
			throw new InvalidInputException("Customer name should not be empty");
		}
		if (!name.matches("^[A-Za-z ]+$")) {
            throw new InvalidInputException("Name should only contain letters and spaces");
        }
		this.Name = name;
	}
	
	
	
	
	public void setEmail(String email)throws InvalidInputException {
        if (email == null || !(email.contains("@") && (email.endsWith(".com") || email.endsWith(".in")))) {
            throw new InvalidInputException("Invalid email format.");
        }
        this.Email = email;
    }
	
	
public void setAddress(String address)throws InvalidInputException {
		
		if(address==null|| address.trim().isEmpty()) {
			throw new InvalidInputException("Customer address should not be empty");
		}
		this.Name = address;
	}



	public void setCreditScore(int cs)throws InvalidInputException {
		if(cs<300||cs>850) {
			throw new InvalidInputException("Invalid Credit Score ,should be between 300 and 850");	
		}
		this.creditScore = cs;
	}

}
