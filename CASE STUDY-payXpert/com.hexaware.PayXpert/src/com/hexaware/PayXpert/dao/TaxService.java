package com.hexaware.PayXpert.dao;

import java.math.BigDecimal;
import java.sql.*;

import com.hexaware.PayXpert.Util.DBConnUtil;
import com.hexaware.PayXpert.Util.DBPropertyUtil;
import com.hexaware.PayXpert.Validation.ValidationService;
import com.hexaware.PayXpert.entity.Tax;
import com.hexaware.PayXpert.exceptions.*;


public class TaxService implements ITaxService {

    private Connection conn;

    public TaxService() throws DatabaseConnectionException {
    	String connectionString = DBPropertyUtil.getConnectionString("src/main/db.properties");
        this.conn = DBConnUtil.getConnection(connectionString);
    }

    
    
    
    
 //-----------------------------------------calculate tax---------------------------------------------------------------  
    
    @Override
    public BigDecimal calculateTax(int employeeId, int taxYear) throws SQLException, InvalidInputException, TaxCalculationException {
    	employeeId=ValidationService.validateId(employeeId);
    	taxYear=ValidationService.validateYear(taxYear);
        BigDecimal totalIncome = BigDecimal.ZERO;

        String query = "SELECT SUM(Amount) AS TotalIncome FROM FinancialRecord " +
                       "WHERE EmployeeID = ? AND RecordType = ? AND YEAR(RecordDate) = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, employeeId);
            ps.setString(2, "Income"); 
            ps.setInt(3, taxYear);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalIncome = rs.getBigDecimal("TotalIncome");
            }
        }
        catch (Exception e) {
        	throw new TaxCalculationException("Error occured while calculating income ");
        	
        }

        if (totalIncome.compareTo(BigDecimal.ZERO) > 0) {
        	BigDecimal taxAmount = calculatingYearlyTax(totalIncome,taxYear);

            
            Tax taxRecord = new Tax(employeeId, taxYear, totalIncome, taxAmount); 

            String insertQuery = "INSERT INTO Tax (TaxID, EmployeeID, TaxYear, TaxableIncome, TaxAmount) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {
                ps.setInt(1, taxRecord.getTaxId());            
                ps.setInt(2, taxRecord.getEmployeeId());
                ps.setInt(3, taxRecord.getTaxYear());
                ps.setBigDecimal(4, taxRecord.getTaxableIncome());
                ps.setBigDecimal(5, taxRecord.getTaxAmount());

                ps.executeUpdate();
                System.out.println("Tax record added successfully for employee ID " + employeeId + " for year " + taxYear);
            }
            catch (Exception e) {
            	throw new TaxCalculationException("Error occured while calculating income ");
            	
            }return taxAmount;
            
        } else {
            System.out.println("No income records found for employee ID " + employeeId + " in year " + taxYear);
            return BigDecimal.ZERO;
        }
        
        
    }

    
    
    
    
    
    
    
    
    
 //-----------------------------------------------get tax by id----------------------------------------------------------   
    
    @Override
    public void getTaxById(int taxId) throws SQLException {
    	taxId=ValidationService.validateId(taxId);
        String query = "SELECT * FROM Tax WHERE TaxID = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, taxId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                printTaxRecord(rs);
            } else {
                System.out.println("No tax record found with ID: " + taxId);
            }
        }
        catch (SQLException e) {
        	throw new SQLException("Error occured while getting tax by tax_id",e);
        	
        }
    }
    
    
    
 //--------------------------------get taxes for employee------------------------------------------------------------------   

    @Override
    public void getTaxesForEmployee(int employeeId) throws SQLException {
    	employeeId=ValidationService.validateId(employeeId);
        String query = "SELECT * FROM Tax WHERE EmployeeID = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            boolean found = false;
            while (rs.next()) {
                printTaxRecord(rs);
                found = true;
            }
            if (!found) {
                System.out.println("No tax records found for Employee ID: " + employeeId);
            }
        }
        catch (SQLException e) {
        	throw new SQLException("Error occured while getting tax by employee_id",e);
        	
        }
    }

    
    
    
    
 //------------------------------- get taxes for year-------------------------------------------------------------------   
    @Override
    public void getTaxesForYear(int taxYear) throws SQLException, InvalidInputException {
    	taxYear=ValidationService.validateYear(taxYear);
        String query = "SELECT * FROM Tax WHERE TaxYear = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, taxYear);
            ResultSet rs = ps.executeQuery();
            boolean found = false;
            while (rs.next()) {
                printTaxRecord(rs);
                found = true;
            }
            if (!found) {
                System.out.println("No tax records found for Year: " + taxYear);
            }
        }
        catch (SQLException e) {
        	throw new SQLException("Error occured while getting taxes by tax_year",e);
        	
        }
    }

    
    
//-------------------------printing result set------------------------------------------------------------    
    
    private void printTaxRecord(ResultSet rs) throws SQLException {
        System.out.println("Tax ID: " + rs.getInt("TaxID"));
        System.out.println("Employee ID: " + rs.getInt("EmployeeID"));
        System.out.println("Tax Year: " + rs.getInt("TaxYear"));
        System.out.println("Taxable Income: " + rs.getBigDecimal("TaxableIncome"));
        System.out.println("Tax Amount: " + rs.getDouble("TaxAmount"));
        System.out.println("----------------------------------------");
    }
    
    
    
  //--------------------calculating yearly tax-----------------------------------------------------------------  
    
    private BigDecimal calculatingYearlyTax(BigDecimal income, int taxYear) {
    	BigDecimal taxAmount = BigDecimal.ZERO;

    	if (taxYear <= 2024) {
            if (income.compareTo(BigDecimal.valueOf(300000)) <= 0) {
                taxAmount = BigDecimal.ZERO;
            } else if (income.compareTo(BigDecimal.valueOf(700000)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.05));
            } else if (income.compareTo(BigDecimal.valueOf(1000000)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.1));
            } else if (income.compareTo(BigDecimal.valueOf(1200000)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.15));
            } else if (income.compareTo(BigDecimal.valueOf(1500000)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.2));
            } else {
                taxAmount = income.multiply(BigDecimal.valueOf(0.3));
            }
        } else {
            if (income.compareTo(BigDecimal.valueOf(400000)) <= 0) {
                taxAmount = BigDecimal.ZERO;
            } else if (income.compareTo(BigDecimal.valueOf(800000)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.05));
            } else if (income.compareTo(BigDecimal.valueOf(1200000)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.1));
            } else if (income.compareTo(BigDecimal.valueOf(1600000)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.15));
            } else if (income.compareTo(BigDecimal.valueOf(2000000)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.2));
            } else if (income.compareTo(BigDecimal.valueOf(2400000)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.25));
            } else {
                taxAmount = income.multiply(BigDecimal.valueOf(0.3));
            }
        }

        return taxAmount.setScale(2, BigDecimal.ROUND_HALF_UP); 
    	
        
        
        
        
 //----------------------------again calculating tax for pay roll----------------------------------------------------------
    	
    	}
    public static BigDecimal calculatingTax(BigDecimal income,BigDecimal dayBasic, int taxYear) {
    	
    	BigDecimal taxAmount = BigDecimal.ZERO;

        if (taxYear <= 2024) {
            if (dayBasic.compareTo(BigDecimal.valueOf(834)) <= 0) {
                taxAmount = BigDecimal.ZERO;
            } else if (dayBasic.compareTo(BigDecimal.valueOf(1945)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.05));
            } else if (dayBasic.compareTo(BigDecimal.valueOf(2778)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.1));
            } else if (dayBasic.compareTo(BigDecimal.valueOf(3334)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.15));
            } else if (dayBasic.compareTo(BigDecimal.valueOf(4167)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.2));
            } else {
                taxAmount = income.multiply(BigDecimal.valueOf(0.3));
            }
        } else {
            if (dayBasic.compareTo(BigDecimal.valueOf(1111.11)) <= 0) {
                taxAmount = BigDecimal.ZERO;
            } else if (dayBasic.compareTo(BigDecimal.valueOf(2222.22)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.05));
            } else if (dayBasic.compareTo(BigDecimal.valueOf(3333.33)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.1));
            } else if (dayBasic.compareTo(BigDecimal.valueOf(4444.44)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.15));
            } else if (dayBasic.compareTo(BigDecimal.valueOf(5555.55)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.2));
            } else if (dayBasic.compareTo(BigDecimal.valueOf(6666.66)) < 0) {
                taxAmount = income.multiply(BigDecimal.valueOf(0.25));
            } else {
                taxAmount = income.multiply(BigDecimal.valueOf(0.3));
            }
        }

        return taxAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
			
	
	}
    
    
    
    //-------------------------------------------for testing------------------------------------------------------
    public BigDecimal calculatesTax(int employeeId, int taxYear) throws SQLException, InvalidInputException, TaxCalculationException {
    	employeeId=ValidationService.validateId(employeeId);
    	taxYear=ValidationService.validateYear(taxYear);
        BigDecimal totalIncome = BigDecimal.ZERO;

        String query = "SELECT SUM(Amount) AS TotalIncome FROM FinancialRecord " +
                       "WHERE EmployeeID = ? AND RecordType = ? AND YEAR(RecordDate) = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, employeeId);
            ps.setString(2, "Income"); 
            ps.setInt(3, taxYear);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalIncome = rs.getBigDecimal("TotalIncome");
            }
        }
        catch (Exception e) {
        	throw new TaxCalculationException("Error occured while calculating income ");
        	
        }

        if (totalIncome.compareTo(BigDecimal.ZERO) > 0) {
        	BigDecimal taxAmount = calculatingYearlyTax(totalIncome,taxYear); 
            Tax taxRecord = new Tax(employeeId, taxYear, totalIncome, taxAmount); 
            return taxAmount;
            
        } else {
            System.out.println("No income records found for employee ID " + employeeId + " in year " + taxYear);
            return BigDecimal.ZERO;
        }  
        
    }

        
}







