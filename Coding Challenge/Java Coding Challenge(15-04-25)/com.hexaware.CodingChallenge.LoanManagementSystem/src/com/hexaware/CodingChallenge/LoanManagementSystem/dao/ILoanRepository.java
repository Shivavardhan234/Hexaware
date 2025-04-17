package com.hexaware.CodingChallenge.LoanManagementSystem.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

import com.hexaware.CodingChallenge.LoanManagementSystem.Entity.Loan;
import com.hexaware.CodingChallenge.LoanManagementSystem.exceptions.*;

public interface ILoanRepository {
	void applyLoan(Loan loan) throws InvalidInputException,SQLException;
	BigDecimal calculateInterest( int loanId)throws InvalidLoanException, InvalidInputException;
	void loanStatus(int loanId)  throws InvalidLoanException, InvalidInputException, SQLException;
	 BigDecimal calculateEMI(int loanId) throws InvalidLoanException, InvalidInputException;
	void loanRepayment(int loanId, BigDecimal amount) throws InvalidLoanException, InvalidInputException;
	List<Loan> getAllLoan() throws InvalidLoanException, InvalidInputException, SQLException;
    Loan getLoanById(int loanId) throws InvalidLoanException, InvalidInputException;
}
