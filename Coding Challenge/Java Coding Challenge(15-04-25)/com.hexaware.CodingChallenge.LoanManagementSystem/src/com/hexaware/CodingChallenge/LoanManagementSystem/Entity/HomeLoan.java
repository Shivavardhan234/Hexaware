package com.hexaware.CodingChallenge.LoanManagementSystem.Entity;

import java.math.BigDecimal;

import com.hexaware.CodingChallenge.LoanManagementSystem.exceptions.InvalidInputException;



public class HomeLoan extends Loan {
	private String propertyAddress;
	private BigDecimal propertyValue;

    public HomeLoan(int loanId, int customer, BigDecimal principalAmount, BigDecimal interestRate,int loanTerm, String loanStatus, String propAdd,BigDecimal propVal) throws InvalidInputException {
        super(loanId, customer, principalAmount, interestRate, loanTerm, "HomeLoan", loanStatus);
        setPropertyAddress(propAdd);
        setPropertyValue(propVal);
    }
    
    
    
    //--------------------------------getters and setters-------------------------
    public String getPropertyAddress() {
        return propertyAddress;
    }

    public BigDecimal getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyAddress(String propertyAddress) throws InvalidInputException {
        if (propertyAddress==null || propertyAddress.trim().isEmpty()) {
            throw new InvalidInputException("Property address should not be empty.");
        }
        this.propertyAddress = propertyAddress;
    }

    public void setPropertyValue(BigDecimal propertyValue) throws InvalidInputException {
        if (propertyValue==null || propertyValue.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInputException("Property value should not be negative or zero.");
        }
        this.propertyValue = propertyValue;
    }

   
}