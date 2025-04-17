package com.hexaware.PayXpert.dao;

import java.sql.*;

import com.hexaware.PayXpert.Util.*;
import com.hexaware.PayXpert.Validation.ValidationService;
import com.hexaware.PayXpert.entity.Employee;
import com.hexaware.PayXpert.exceptions.DatabaseConnectionException;
import com.hexaware.PayXpert.exceptions.EmployeeNotFoundException;



public class EmployeeService implements IEmployeeService {
	
	private Connection conn;
	public EmployeeService() throws DatabaseConnectionException {
		String connectionString = DBPropertyUtil.getConnectionString("src/main/db.properties");
        this.conn = DBConnUtil.getConnection(connectionString);
	}

    @Override
    public void getEmployeeById(int employeeId) throws EmployeeNotFoundException, SQLException {
    		employeeId=ValidationService.validateId(employeeId);
        
            String query = "SELECT * FROM Employee WHERE EmployeeID = ?";
            try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	System.out.println("Employee Details:");
                System.out.println("ID: " + rs.getInt("EmployeeID"));
                System.out.println("First Name: " + rs.getString("FirstName"));
                System.out.println("Last Name: " + rs.getString("LastName"));
                System.out.println("Date of Birth: " + rs.getDate("DateOfBirth"));
                System.out.println("Gender: " + rs.getString("Gender"));
                System.out.println("Email: " + rs.getString("Email"));
                System.out.println("Phone Number: " + rs.getString("PhoneNumber"));
                System.out.println("Address: " + rs.getString("Address"));
                System.out.println("Position: " + rs.getString("Position"));
                System.out.println("Joining Date: " + rs.getDate("JoiningDate"));
                System.out.println("Termination Date: " + rs.getDate("TerminationDate"));
                System.out.println("Employee Found: " + rs.getString("FirstName") + " " + rs.getString("LastName"));
            } else {
                throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
            }
        } catch (SQLException e) {
            throw new SQLException("error occured while retrieving employee details by id",e) ;
        }
    }

    @Override
    public void getAllEmployees() throws SQLException {
        String query = "SELECT * FROM Employee";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("Employee Details:");
                System.out.println("ID: " + rs.getInt("EmployeeID"));
                System.out.println("First Name: " + rs.getString("FirstName"));
                System.out.println("Last Name: " + rs.getString("LastName"));
                System.out.println("Date of Birth: " + rs.getDate("DateOfBirth"));
                System.out.println("Gender: " + rs.getString("Gender"));
                System.out.println("Email: " + rs.getString("Email"));
                System.out.println("Phone Number: " + rs.getString("PhoneNumber"));
                System.out.println("Address: " + rs.getString("Address"));
                System.out.println("Position: " + rs.getString("Position"));
                System.out.println("Joining Date: " + rs.getDate("JoiningDate"));
                System.out.println("Termination Date: " + rs.getDate("TerminationDate"));
                System.out.println("-------------------------------------------------");
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving all employees: " + e.getMessage());
            
        }
    }

    @Override
    public void addEmployee(Employee employeeData) throws SQLException {
        
            String query = "INSERT INTO Employee (FirstName, LastName, DateOfBirth, Gender, Email, PhoneNumber, Address, Position, JoiningDate, TerminationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, employeeData.getFirstName());
            ps.setString(2, employeeData.getLastName());
            ps.setDate(3, Date.valueOf(employeeData.getDateOfBirth()));
            ps.setString(4, employeeData.getGender());
            ps.setString(5, employeeData.getEmail());
            ps.setString(6, employeeData.getPhoneNumber());
            ps.setString(7, employeeData.getAddress());
            ps.setString(8, employeeData.getPosition());
            ps.setDate(9, Date.valueOf(employeeData.getJoiningDate()));
            ps.setDate(10, employeeData.getTerminationDate() != null ? Date.valueOf(employeeData.getTerminationDate()) : null);
            ps.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (SQLException e) {
        	throw new SQLException("Error While adding employee: " + e.getMessage());
        }
    }

    @Override
    public void updateEmployee(Employee employeeData) throws EmployeeNotFoundException, SQLException {
        
            String query = "UPDATE Employee SET FirstName = ?, LastName = ?, DateOfBirth = ?, Gender = ?, Email = ?, PhoneNumber = ?, Address = ?, Position = ?, JoiningDate = ?, TerminationDate = ? WHERE EmployeeID = ?";
            try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, employeeData.getFirstName());
            ps.setString(2, employeeData.getLastName());
            ps.setDate(3, Date.valueOf(employeeData.getDateOfBirth()));
            ps.setString(4, employeeData.getGender());
            ps.setString(5, employeeData.getEmail());
            ps.setString(6, employeeData.getPhoneNumber());
            ps.setString(7, employeeData.getAddress());
            ps.setString(8, employeeData.getPosition());
            ps.setDate(9, Date.valueOf(employeeData.getJoiningDate()));
            ps.setDate(10, employeeData.getTerminationDate() != null ? Date.valueOf(employeeData.getTerminationDate()) : null);
            ps.setInt(11, employeeData.getEmployeeId());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee updated successfully.");
            } else {
                throw new EmployeeNotFoundException("Employee ID not found.");
            }
        } catch (SQLException e) {
        	throw new SQLException("Error While updating employee details: " + e.getMessage());
        }
    }
    
    

    @Override
    public void removeEmployee(int employeeId) throws SQLException,EmployeeNotFoundException {
    		employeeId=ValidationService.validateId(employeeId);
       
            String query = "DELETE FROM Employee WHERE EmployeeID = ?";
            try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, employeeId);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted == 0) {
                throw new EmployeeNotFoundException("Cannot delete. Employee with ID " + employeeId + " not found.");
            } else {
                System.out.println("Employee deleted successfully.");
            }
        } catch (SQLException e) {
        	throw new SQLException("Error While deleting employee details: " + e.getMessage());
        }
    }
}