package com.hexaware.PayXpert.dao;

import java.sql.SQLException;
import java.time.LocalDate;

import com.hexaware.PayXpert.exceptions.InvalidInputException;
import com.hexaware.PayXpert.exceptions.PayrollGenerationException;

public interface IPayrollService {
	void generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate)throws InvalidInputException, SQLException, PayrollGenerationException;
    void getPayrollById(int payrollId)throws InvalidInputException, SQLException;
    void getPayrollsForEmployee(int employeeId)throws InvalidInputException, SQLException;
    void getPayrollsForPeriod(LocalDate startDate, LocalDate endDate)throws InvalidInputException, SQLException;

}
