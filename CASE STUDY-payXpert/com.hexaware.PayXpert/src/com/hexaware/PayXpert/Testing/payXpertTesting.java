package com.hexaware.PayXpert.Testing;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import com.hexaware.PayXpert.dao.PayrollService;
import com.hexaware.PayXpert.dao.TaxService;
import com.hexaware.PayXpert.entity.Employee;
import com.hexaware.PayXpert.exceptions.DatabaseConnectionException;
import com.hexaware.PayXpert.exceptions.InvalidInputException;

class payXpertTesting {

	
	
	
	@Test
	 public void testCalculateGrossSalaryForEmployee() throws SQLException, DatabaseConnectionException {
        PayrollService payrollService = new PayrollService();
        int employeeId = 2;
        BigDecimal expectedGrossSalary = new BigDecimal("82000.00");
        BigDecimal actualGrossSalary = payrollService.calculateGrossSalary(employeeId);
        assertEquals(expectedGrossSalary, actualGrossSalary);
    }
	
	
	
	@Test
	public void testCalculateNetSalaryAfterDeductions() throws SQLException, DatabaseConnectionException {
	    PayrollService payrollService = new PayrollService();

	    int employeeId = 2;
	    LocalDate startDate = LocalDate.of(2024, 02, 01);
	    LocalDate endDate = LocalDate.of(2024, 02, 29);
	    BigDecimal actualNetSalary = payrollService.calculateNetSalary(employeeId, startDate, endDate);
	    BigDecimal expectedNetSalary = new BigDecimal("77500.00"); 
	    assertEquals(expectedNetSalary, actualNetSalary);
	}
	
	
	
	
	@Test
    public void testCalculateTaxForHighIncomeEmployee() throws Exception {
        int employeeId = 10;
        int taxYear = 2024;
        BigDecimal expectedTax = new BigDecimal("30000000.00"); 
        TaxService ts=new TaxService();
        
        assertEquals(expectedTax,ts.calculatesTax(employeeId, taxYear) );
        
    }
	
	
	
	
	
	@Test
	public void testInvalidEmployeeDataHandling() {
	    try {
	        Employee invalidEmployee = new Employee(-1, "", "",null, "X", "invalid-email", "12345", "", "", LocalDate.now().plusDays(1), LocalDate.of(2020, 1, 1) );

	       
	    } catch (InvalidInputException e) {
	        assertNotNull(e.getMessage());
	    }
	}

	
	
	@Test
	public void testProcessPayrollForMultipleEmployees() throws DatabaseConnectionException {
	    PayrollService payrollService = new PayrollService();

	    int[] employeeIds = {1,2,3,4,5}; 

	    for (int employeeId : employeeIds) {
	        try {
	            System.out.println("Fetching payroll for Employee ID: " + employeeId);
	            payrollService.getPayrollsForEmployee(employeeId);
	        } catch (SQLException e) {
	            fail("SQL error for Employee ID: " + employeeId + " - " + e.getMessage());
	        }
	    }
	}

}
