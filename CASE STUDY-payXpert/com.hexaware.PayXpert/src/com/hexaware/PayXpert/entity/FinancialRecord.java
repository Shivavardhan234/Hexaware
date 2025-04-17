package com.hexaware.PayXpert.entity;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.hexaware.PayXpert.exceptions.InvalidInputException;

public class FinancialRecord {
	
	
	    private int recordId;
	    private int employeeId;
	    private LocalDate recordDate;
	    private String description;
	    private BigDecimal amount;

	    private RecordType recordType;

	    public enum RecordType {
	        INCOME,
	        EXPENSE,
	        TAX_PAYMENT
	    }
	    private static int id=11;

//--------------------------- Constructor-----------------------------------------
	    public FinancialRecord( int employeeId, LocalDate recordDate, String description, BigDecimal amount, RecordType recordType) throws InvalidInputException {
	        setRecordId(id);
	        setEmployeeId(employeeId);
	        setRecordDate(recordDate);
	        setDescription(description);
	        setAmount(amount);
	        setRecordType(recordType);
	        id++;
	    }

//---------------------- Getters--------------------------------------------
	    public int getRecordId() {
	        return recordId;
	    }

	    public int getEmployeeId() {
	        return employeeId;
	    }

	    public LocalDate getRecordDate() {
	        return recordDate;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public BigDecimal getAmount() {
	        return amount;
	    }

	    public RecordType getRecordType() {
	        return recordType;
	    }

	    //--------------------- Setters with validation--------------------------------------------
	    public void setRecordId(int recordId) throws InvalidInputException {
	        if (recordId <= 0) {
	            throw new InvalidInputException("Record ID must be positive.");
	        }
	        this.recordId = recordId;
	    }

	    public void setEmployeeId(int employeeId) throws InvalidInputException {
	        if (employeeId <= 0) {
	            throw new InvalidInputException("Employee ID must be positive.");
	        }
	        this.employeeId = employeeId;
	    }

	    public void setRecordDate(LocalDate recordDate) throws InvalidInputException {
	        if (recordDate == null || recordDate.isAfter(LocalDate.now())) {
	            throw new InvalidInputException("Record date cannot be null or after current date.");
	        }
	        this.recordDate = recordDate;
	    }

	    public void setDescription(String description) throws InvalidInputException {
	        if (description == null || description.trim().isEmpty()) {
	            throw new InvalidInputException("Description cannot be empty.");
	        }
	        this.description = description;
	    }

	    public void setAmount(BigDecimal amount) throws InvalidInputException {
	        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
	            throw new InvalidInputException("Amount cannot be negative.");
	        }
	        this.amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
	    }

	    public void setRecordType(RecordType recordType) throws InvalidInputException { 
	        if (recordType == null) {
	            throw new InvalidInputException("Record type must be specified.");
	        }

	        boolean isValid = false;
	        for (RecordType type : RecordType.values()) {
	            if (type == recordType) {
	                isValid = true;
	                break;
	            }
	        }

	        if (!isValid) {
	            throw new InvalidInputException("Invalid record type: " + recordType);
	        }

	        this.recordType = recordType;
	    }


	    
	


}
