package com.hexaware.PayXpert.dao;

import java.math.BigDecimal;

import java.sql.*;
import java.time.LocalDate;

import com.hexaware.PayXpert.Util.DBConnUtil;
import com.hexaware.PayXpert.Util.DBPropertyUtil;
import com.hexaware.PayXpert.Validation.ValidationService;
import com.hexaware.PayXpert.entity.FinancialRecord;
import com.hexaware.PayXpert.entity.FinancialRecord.RecordType;
import com.hexaware.PayXpert.exceptions.DatabaseConnectionException;
import com.hexaware.PayXpert.exceptions.InvalidInputException;

public class FinancialRecordService implements IFinancialRecordService {

    private Connection conn;

    public FinancialRecordService() throws DatabaseConnectionException {
    	String connectionString = DBPropertyUtil.getConnectionString("src/main/db.properties");
        this.conn = DBConnUtil.getConnection(connectionString);
    }

    
    
    
    //---------------------------------------------------adding financial record-----------------------------------------------
    
    @Override
    public void addFinancialRecord(int employeeId, String description, double amt, RecordType recordType)throws InvalidInputException, SQLException  {
    	employeeId=ValidationService.validateId(employeeId);
    	description=ValidationService.validateDescription(description);
    	BigDecimal amount=ValidationService.validateAmount(BigDecimal.valueOf(amt));
    	recordType=ValidationService.validateRecordType(recordType);
        try {
            FinancialRecord record = new FinancialRecord(employeeId, LocalDate.now(), description, amount, recordType);

            String query = "INSERT INTO FinancialRecord (RecordID, EmployeeID, fDescription, Amount, RecordType, RecordDate) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, record.getRecordId());
                ps.setInt(2, record.getEmployeeId());
                ps.setString(3, record.getDescription());
                ps.setBigDecimal(4, record.getAmount());
                ps.setString(5, getRecordTypeDisplayName(record.getRecordType()));
                ps.setDate(6, Date.valueOf(record.getRecordDate()));
                ps.executeUpdate();
                System.out.println("Financial record added successfully with ID: " + record.getRecordId());
            }
        } catch (InvalidInputException e) {
            throw new InvalidInputException("Invalid input while creating financial record: " + e.getMessage());
        } catch (SQLException e) {
        	throw new SQLException("Error adding financial record to database: " + e.getMessage());
        }
    }

    
    
    
    //-----------------------------get financial record by id-----------------------------------------------------------------
    
    @Override
    public void getFinancialRecordById(int recordId) throws SQLException {
    	recordId=ValidationService.validateId(recordId);
        String query = "SELECT * FROM FinancialRecord WHERE RecordID = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, recordId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                printRecord(rs);
            } else {
                System.out.println("Financial record with ID " + recordId + " not found.");
            }
        } catch (SQLException e) {
        	throw new SQLException("Error retrieving financial record: " + e.getMessage());
        }
    }

    
    
    
  
    
    
    //-----------------------------------get financial records for employee----------------------------------------------
    
    
    @Override
    public void getFinancialRecordsForEmployee(int employeeId)throws SQLException {
    	employeeId=ValidationService.validateId(employeeId);
        String query = "SELECT * FROM FinancialRecord WHERE EmployeeID = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                printRecord(rs);
            }
        } catch (SQLException e) {
        	throw new SQLException("Error retrieving records for employee: " + e.getMessage());
        }
    }

    
    
    
    //-----------------------------get financial records for date----------------------------------------------------------
    
    @Override
    public void getFinancialRecordsForDate(LocalDate recordDate)throws SQLException, InvalidInputException {
    	recordDate=ValidationService.validateDate(recordDate);
        String query = "SELECT * FROM FinancialRecord WHERE RecordDate = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDate(1, Date.valueOf(recordDate));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                printRecord(rs);
            }
        } catch (SQLException e) {
        	throw new SQLException("Error retrieving records for date: " + e.getMessage());
        }
    }

    
    
    
    
    
    
    
    //---------------------------------get all financial records---------------------------------------------------------
    
    
    @Override
    public void getAllFinancialRecords()throws SQLException {
        String query = "SELECT * FROM FinancialRecord";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                printRecord(rs);
            }
        } catch (SQLException e) {
        	throw new SQLException("Error retrieving all financial records: " + e.getMessage());
        }
    }

    
    
    
    
    //-------------------------------------update financial record-------------------------------------------------------------
    
    @Override
    public void updateFinancialRecord(int frID, int employeeId, String description, LocalDate recordDate, double amt, RecordType recordType)throws SQLException, InvalidInputException {
    	frID=ValidationService.validateId(frID);
    	employeeId=ValidationService.validateId(employeeId);
    	description=ValidationService.validateDescription(description);
    	recordDate=ValidationService.validateDate(recordDate);
    	BigDecimal amount=ValidationService.validateAmount(BigDecimal.valueOf(amt));
    	recordType =ValidationService.validateRecordType(recordType);
        String query = "UPDATE FinancialRecord SET EmployeeID = ?, fDescription = ?, RecordDate = ?, Amount = ?, RecordType = ? WHERE RecordID = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, employeeId);
            ps.setString(2, description);
            ps.setDate(3, Date.valueOf(recordDate));
            ps.setBigDecimal(4, amount);
            ps.setString(5, getRecordTypeDisplayName(recordType));
            ps.setInt(6, frID);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Financial record updated successfully.");
            } else {
                System.out.println("Financial record with ID " + frID + " not found.");
            }
        } catch (SQLException e) {
        	throw new SQLException("Error updating financial record: " + e.getMessage());
        }
    }
    
    
    
    
    //------------------------------delete financial record-------------------------------------------------------------
   

    @Override
    public void deleteFinancialRecord(int ID) throws SQLException{
    	ID=ValidationService.validateId(ID);
        String query = "DELETE FROM FinancialRecord WHERE RecordID = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, ID);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Financial record deleted successfully.");
            } else {
                System.out.println("Financial record with ID " + ID + " not found.");
            }
        } catch (SQLException e) {
        	throw new SQLException("Error deleting financial record: " + e.getMessage());
        }
    }

    
    
    
    
    
    //---------------------------------printing result set--------------------------------------------------------------
    private void printRecord(ResultSet rs) throws SQLException {
        System.out.println("Record ID: " + rs.getInt("RecordID"));
        System.out.println("Employee ID: " + rs.getInt("EmployeeID"));
        System.out.println("Description: " + rs.getString("fDescription"));
        System.out.println("Date: " + rs.getDate("RecordDate"));
        System.out.println("Amount: " + rs.getBigDecimal("Amount"));
        System.out.println("Record Type: " + rs.getString("RecordType"));
        System.out.println("--------------------------------------------------");
    }
    
    
    
    //----------------------converting record type to string as per DB----------------------------------------------------------
    private String getRecordTypeDisplayName(RecordType recordType) {
        return switch (recordType) {
            case INCOME -> "Income";
            case EXPENSE -> "Expense";
            case TAX_PAYMENT -> "Tax payment";
        };
    }
}
