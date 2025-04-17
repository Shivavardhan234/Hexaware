package com.hexaware.PayXpert.dao;

import java.math.BigDecimal;

import java.sql.*;
import java.time.LocalDate;
import java.sql.Date;
import java.util.*;

import com.hexaware.PayXpert.Util.DBConnUtil;
import com.hexaware.PayXpert.Util.DBPropertyUtil;
import com.hexaware.PayXpert.Validation.ValidationService;
import com.hexaware.PayXpert.entity.Payroll;
import com.hexaware.PayXpert.exceptions.DatabaseConnectionException;
import com.hexaware.PayXpert.exceptions.InvalidInputException;
import com.hexaware.PayXpert.exceptions.PayrollGenerationException;

public class PayrollService implements IPayrollService {

    private Connection conn;
    

    public PayrollService() throws DatabaseConnectionException {
    	String connectionString = DBPropertyUtil.getConnectionString("src/main/db.properties");
        this.conn = DBConnUtil.getConnection(connectionString);
    }

    private Scanner scanner = new Scanner(System.in);
    
  
    
    
    
    //--------------------------------------generating payroll-----------------------------------------------------------
    @Override
    public void generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate) throws InvalidInputException, SQLException, PayrollGenerationException{
    	employeeId=ValidationService.validateId(employeeId);
    	startDate=ValidationService.validateDate(startDate);
    	endDate=ValidationService.validateEndDate(startDate, endDate);
    	
    	System.out.print("Enter basic salary for the period: ");
        double basicsalary = scanner.nextDouble();
        BigDecimal basicSalary=ValidationService.validateAmount(BigDecimal.valueOf(basicsalary));

        System.out.print("Enter overtime pay: ");
        double overtimepay = scanner.nextDouble();
        BigDecimal overtimePay=ValidationService.validateAmount(BigDecimal.valueOf(overtimepay));
        Payroll payroll = new Payroll(employeeId, startDate, endDate, basicSalary, overtimePay);
        
        
        String insertQuery = "INSERT INTO Payroll (PayrollID, EmployeeID, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OvertimePay, Deductions, NetSalary) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {
			ps.setInt(1, payroll.getPayrollId());
			ps.setInt(2, payroll.getEmployeeId());
			ps.setDate(3, java.sql.Date.valueOf(payroll.getPayPeriodStartDate()));
			ps.setDate(4, java.sql.Date.valueOf(payroll.getPayPeriodEndDate()));
			ps.setBigDecimal(5, payroll.getBasicSalary());
			ps.setBigDecimal(6, payroll.getOvertimePay());
			ps.setBigDecimal(7, payroll.getDeductions());
			ps.setBigDecimal(8, payroll.getNetSalary());
			
			ps.executeUpdate();
			
			//---------------------------------------------------------
			System.out.println("\n--- Payroll Slip ---");
			System.out.println("Payroll ID: " + payroll.getPayrollId());
			System.out.println("Employee ID: " + payroll.getEmployeeId());
			System.out.println("Start Date: " + payroll.getPayPeriodStartDate());
			System.out.println("End Date: " + payroll.getPayPeriodEndDate());
			System.out.println("Basic Salary: ₹" + payroll.getBasicSalary());
			System.out.println("Overtime Pay: ₹" + payroll.getOvertimePay());
			System.out.println("Deductions (Tax): ₹" + payroll.getDeductions());
			System.out.println("Net Pay: ₹" + payroll.getNetSalary());
			
		} 
		catch (SQLException e) {
			throw new PayrollGenerationException("Error inserting payroll record: " + e.getMessage());
		}
	
	        
}
	
	    
    
    
    //------------------------get pay roll by id----------------------------------------------------------------------
    
    @Override
    public void getPayrollById(int payrollId) throws SQLException {
    	payrollId=ValidationService.validateId(payrollId);
        String query = "SELECT * FROM Payroll WHERE PayrollID = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, payrollId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Payroll Details:");
                System.out.println("Payroll ID: " + rs.getInt("PayrollID"));
                System.out.println("Employee ID: " + rs.getInt("EmployeeID"));
                System.out.println("Start Date: " + rs.getDate("PayPeriodStartDate"));
                System.out.println("End Date: " + rs.getDate("PayPeriodEndDate"));
                System.out.println("Basic Salary: ₹" + rs.getBigDecimal("BasicSalary"));
    			System.out.println("Overtime Pay: ₹" + rs.getBigDecimal("OvertimePay"));
    			System.out.println("Deductions (Tax): ₹" + rs.getBigDecimal("Deductions"));
                System.out.println("NetSalary: " + rs.getDouble("NetSalary"));
            } else {
                System.out.println("Payroll with ID " + payrollId + " not found.");
            }
        } catch (SQLException e) {
        	throw new SQLException("Error retrieving payroll by ID: " + e.getMessage());
            
        }
    }

    
    
    
    
    
    //------------------------------------get payrolls for employee--------------------------------------------------------
    @Override
    public void getPayrollsForEmployee(int employeeId) throws SQLException {
    	employeeId=ValidationService.validateId(employeeId);
        String query = "SELECT * FROM Payroll WHERE EmployeeID = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Payroll ID: " + rs.getInt("PayrollID"));
                System.out.println("Employee ID: " + rs.getInt("EmployeeID")); 
                System.out.println("Start Date: " + rs.getDate("PayPeriodStartDate"));
                System.out.println("End Date: " + rs.getDate("PayPeriodEndDate"));
                System.out.println("Basic Salary: ₹" + rs.getBigDecimal("BasicSalary"));
    			System.out.println("Overtime Pay: ₹" + rs.getBigDecimal("OvertimePay"));
    			System.out.println("Deductions (Tax): ₹" + rs.getBigDecimal("Deductions"));
                System.out.println("Amount: " + rs.getDouble("NetSalary"));
                System.out.println("-------------------------------------------------");
            }
        } catch (SQLException e) {
        	throw new SQLException("Error retrieving payrolls for employee: " + e.getMessage());
            
        }
    }
    
    
    
    //---------------------------------get payrolls for period-----------------------------------------------------------

    @Override
    public void getPayrollsForPeriod(LocalDate startDate, LocalDate endDate)throws SQLException, InvalidInputException {
    	startDate=ValidationService.validateDate(startDate);
    	endDate=ValidationService.validateEndDate(startDate, endDate);
        String query = "SELECT * FROM Payroll WHERE PayPeriodStartDate >= ? AND PayPeriodEndDate <= ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDate(1, Date.valueOf(startDate));
            ps.setDate(2, Date.valueOf(endDate));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Payroll ID: " + rs.getInt("PayrollID"));
                System.out.println("Employee ID: " + rs.getInt("EmployeeID"));
                System.out.println("Start Date: " + rs.getDate("PayPeriodStartDate"));
                System.out.println("End Date: " + rs.getDate("PayPeriodEndDate"));
                System.out.println("Basic Salary: ₹" + rs.getBigDecimal("BasicSalary"));
    			System.out.println("Overtime Pay: ₹" + rs.getBigDecimal("OvertimePay"));
    			System.out.println("Deductions (Tax): ₹" + rs.getBigDecimal("Deductions"));
                System.out.println("Amount: " + rs.getDouble("NetSalary"));
                System.out.println("-------------------------------------------------");
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving payrolls for period: " + e.getMessage());
        }
    }
    
    
    //-------------------------------gross salary------------------------------
    public BigDecimal calculateGrossSalary(int employeeId) throws SQLException {
        
        String query = "SELECT basicSalary, overtimePay FROM Payroll WHERE employeeId = ?";
        BigDecimal totalBasicSalary = BigDecimal.ZERO;
        BigDecimal totalOvertimePay = BigDecimal.ZERO;
        int recordCount = 0;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                totalBasicSalary = totalBasicSalary.add(rs.getBigDecimal("BasicSalary"));
                totalOvertimePay = totalOvertimePay.add(rs.getBigDecimal("OvertimePay"));
                recordCount++;
            }
            if (recordCount == 0) {
                throw new SQLException("No payroll records found for employee with ID: " + employeeId);
            }
            BigDecimal grossSalary = totalBasicSalary.add(totalOvertimePay).divide(BigDecimal.valueOf(recordCount), 2, BigDecimal.ROUND_HALF_UP);

            return grossSalary;
        } catch (SQLException e) {
            throw new SQLException("Error calculating gross salary: " + e.getMessage());
        }
    }
    
//--------------------------------------------------net salary calculation-----------------------------------------------------
    public BigDecimal calculateNetSalary(int employeeId, LocalDate startDate, LocalDate endDate) throws SQLException {
        String query = "SELECT BasicSalary, OvertimePay, Deductions FROM Payroll WHERE EmployeeId = ? AND PayPeriodStartDate = ? AND PayPeriodEndDate = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, employeeId);
            ps.setDate(2, Date.valueOf(startDate));
            ps.setDate(3, Date.valueOf(endDate));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BigDecimal basicSalary = rs.getBigDecimal("basicSalary");
                BigDecimal overtimePay = rs.getBigDecimal("overtimePay");
                BigDecimal deductions = rs.getBigDecimal("deductions");
                BigDecimal grossSalary = basicSalary.add(overtimePay);
                BigDecimal netSalary = grossSalary.subtract(deductions);
                
                return netSalary;
            } else {
                throw new SQLException("No payroll record found for the given employee ID and date range.");
            }
        }
    }
}

