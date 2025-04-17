package com.hexaware.PayXpert.Validation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;

import com.hexaware.PayXpert.entity.FinancialRecord.RecordType;
import com.hexaware.PayXpert.exceptions.InvalidInputException;

public class ValidationService {
	
	//-------------------------validating all id's-----------------------------------------------
	public static int validateId(int Id) {
        if (Id <= 0) {
            throw new IllegalArgumentException("ID must be positive.");
        }
        return Id;
    }
	
	
	//---------------------------validating names--------------------------------------------
	public static String validateName(String name) {
        if (name == null || name.trim().isEmpty() || !name.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("Invalid name. Only letters and spaces allowed.");
        }
        return name.trim();
    }
	
	
	//------------------------------validating numbers------------------------------------------
	public static String validateContactNumber(String phoneNumber) throws InvalidInputException {
		if (phoneNumber == null || !phoneNumber.matches("\\d{10}")) {
            throw new InvalidInputException("Phone number must be exactly 10 digits.");
        }
        return phoneNumber;
    }
	
	
	
	//-------------------------------validating date----------------------------------------------
	public static LocalDate validateDate(LocalDate date)throws InvalidInputException {
        if (date == null) {
            throw new InvalidInputException("Enter the date correctly.");
        }
        if (date.isAfter(LocalDate.now())) {
	        throw new InvalidInputException("Date should not be in future");
	    }
        return date;
    }
	
	
	
	
	//-------------------------validating gender--------------------------------------------------------------------
	public static String validateGender(String gender)throws InvalidInputException {
        if (gender == null || !(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("T"))) {
            throw new InvalidInputException("Gender must be 'M', 'F', or 'T'.");
        }
        return gender.toUpperCase();
    }
	
	
	
	
	//--------------------------------validating email----------------------------------------------------------------
	public static String validateEmail(String email)throws InvalidInputException {
        if (email == null || !(email.contains("@") && (email.endsWith(".com") || email.endsWith(".in")))) {
            throw new InvalidInputException("Invalid email format.");
        }
        return email;
    }
	
	
	
	//---------------------------validating address---------------------------------------------------------
	
	public static String validateAddress(String address)throws InvalidInputException {
        if (address == null || address.trim().isEmpty()) {
            throw new InvalidInputException("Address cannot be null or empty.");
        }
        return address;
    }
	
	
	
	//---------------------------validating joining date--------------------------------------------------------
	public static LocalDate validateJoiningDate(LocalDate joiningDate) throws InvalidInputException {
	    if (joiningDate == null) {
	        throw new InvalidInputException("Joining date cannot be null.");
	    }
	    if (joiningDate.isAfter(LocalDate.now())) {
	        throw new InvalidInputException("Joining date cannot be in the future.");
	    }
	    return joiningDate;
	}
	
	
	
	
	
	//-----------------------------------validating end date--------------------------------------------------------
	public static LocalDate validateEndDate(LocalDate startDate, LocalDate terminationDate) throws InvalidInputException {
        if (terminationDate != null  && terminationDate.isBefore(startDate)) {
            throw new InvalidInputException("end date cannot be before start date.");
        }
        return terminationDate;
    }
	
	
	
	
	
	//-----------------------------------validating employee position----------------------------------
	
	public static String validatePosition(String position)throws InvalidInputException {
        if (position == null || position.trim().isEmpty()) {
            throw new InvalidInputException("Position cannot be null or empty.");
        }
        return position;
    }
	
	
	
	//-------------------------------validating amount------------------------------------------------
	public static BigDecimal validateAmount(BigDecimal amount) throws InvalidInputException {
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidInputException("Amount cannot be negative.");
        return amount;
    }
	
	
	
	//-------------------------------------validating year-------------------------------------------
	
	public static int validateYear(int year) throws InvalidInputException {
        int currentYear = Year.now().getValue();
        if (year > currentYear) {
            throw new InvalidInputException("Tax year must be lesser than or equal to current year.");
        }
        return year;
    }
	
	
	
	//-------------------------------validatng description-------------------------------------------
	public static String validateDescription(String description) throws InvalidInputException {
        if (description == null || description.trim().isEmpty()) {
            throw new InvalidInputException("Description cannot be empty.");
        }
        return description;
    }
	
	
	
	
	//--------------------validating record type--------------------------------------------------------
	public static RecordType validateRecordType(RecordType recordType) throws InvalidInputException { 
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

        return recordType;
    }
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	

	
	

}
