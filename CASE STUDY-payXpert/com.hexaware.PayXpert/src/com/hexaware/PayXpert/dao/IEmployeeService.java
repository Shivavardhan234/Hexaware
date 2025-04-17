package com.hexaware.PayXpert.dao;

import java.sql.SQLException;

import com.hexaware.PayXpert.entity.Employee;
import com.hexaware.PayXpert.exceptions.EmployeeNotFoundException;

public interface IEmployeeService {
	 	void getEmployeeById(int employeeId)throws EmployeeNotFoundException, SQLException;
	    void getAllEmployees()throws SQLException;
	    void addEmployee(Employee employeeData)throws SQLException;
	    void updateEmployee(Employee employeeData)throws SQLException,EmployeeNotFoundException;
	    void removeEmployee(int employeeId)throws SQLException,EmployeeNotFoundException;

}
