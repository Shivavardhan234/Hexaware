package com.hexaware.PayXpert.dao;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.hexaware.PayXpert.exceptions.InvalidInputException;
import com.hexaware.PayXpert.exceptions.TaxCalculationException;

public interface ITaxService {
	    BigDecimal calculateTax(int employeeId, int taxYear) throws SQLException, InvalidInputException, TaxCalculationException ;
	    void getTaxById(int taxId)throws InvalidInputException, SQLException;
	    void getTaxesForEmployee(int employeeId)throws InvalidInputException, SQLException;
	    void getTaxesForYear(int taxYear)throws InvalidInputException, SQLException;

}
