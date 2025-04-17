package com.hexaware.PayXpert.entity;

import java.math.BigDecimal;
import java.time.Year;

import com.hexaware.PayXpert.exceptions.InvalidInputException;

public class Tax {
	private int taxId;
    private int employeeId;
    private int taxYear;
    private BigDecimal taxableIncome;
    private BigDecimal taxAmount;
    private static int id = 11;

    // Constructor
    public Tax(int employeeId, int taxYear, BigDecimal taxableIncome, BigDecimal taxAmount) throws InvalidInputException {
        setTaxId(id);
        setEmployeeId(employeeId);
        setTaxYear(taxYear);
        setTaxableIncome(taxableIncome);
        setTaxAmount(taxAmount);
        id++;
    }

    // Getters
    public int getTaxId() {
        return taxId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getTaxYear() {
        return taxYear;
    }

    public BigDecimal getTaxableIncome() {
        return taxableIncome;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    // Setters
    public void setTaxId(int taxId) throws InvalidInputException {
        if (taxId <= 0) {
            throw new InvalidInputException("Tax ID must be greater than 0.");
        }
        this.taxId = taxId;
    }

    public void setEmployeeId(int employeeId) throws InvalidInputException {
        if (employeeId <= 0) {
            throw new InvalidInputException("Employee ID must be greater than 0.");
        }
        this.employeeId = employeeId;
    }

    public void setTaxYear(int taxYear) throws InvalidInputException {
        int currentYear = Year.now().getValue();
        if (taxYear > currentYear) {
            throw new InvalidInputException("Tax year must be lesser than or equal to current year.");
        }
        this.taxYear = taxYear;
    }

    public void setTaxableIncome(BigDecimal taxableIncome) throws InvalidInputException {
        if (taxableIncome == null || taxableIncome.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidInputException("Taxable income cannot be null or negative.");
        }
        this.taxableIncome = taxableIncome.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setTaxAmount(BigDecimal taxAmount) throws InvalidInputException {
        if (taxAmount == null || taxAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidInputException("Tax amount cannot be null or negative.");
        }
        this.taxAmount = taxAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
