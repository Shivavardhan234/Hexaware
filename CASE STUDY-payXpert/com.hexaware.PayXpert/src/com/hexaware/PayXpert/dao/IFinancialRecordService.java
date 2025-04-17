package com.hexaware.PayXpert.dao;

import java.sql.SQLException;
import java.time.LocalDate;

import com.hexaware.PayXpert.entity.FinancialRecord.RecordType;
import com.hexaware.PayXpert.exceptions.InvalidInputException;

public interface IFinancialRecordService {
	void addFinancialRecord(int employeeId, String description, double amount, RecordType recordType)throws InvalidInputException, SQLException;
    void getFinancialRecordById(int recordId)throws SQLException, InvalidInputException;
    void getFinancialRecordsForEmployee(int employeeId)throws SQLException, InvalidInputException;
    void getFinancialRecordsForDate(LocalDate recordDate)throws SQLException, InvalidInputException;
    void getAllFinancialRecords()throws SQLException;
    void updateFinancialRecord(int frID,int employeeId, String description,LocalDate recordDate, double amount, RecordType recordType)throws SQLException, InvalidInputException;
    void deleteFinancialRecord(int ID)throws SQLException, InvalidInputException;
}
