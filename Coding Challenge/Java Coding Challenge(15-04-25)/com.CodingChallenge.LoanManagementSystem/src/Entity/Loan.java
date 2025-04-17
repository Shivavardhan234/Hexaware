package Entity;

import java.math.BigDecimal;

import exceptions.InvalidInputException;

public class Loan {
    private int loanId;
    private int customer;  
    private BigDecimal principalAmount;
    private BigDecimal interestRate;
    private int loanTerm;
    private String loanType;     
    private String loanStatus;   
    
    
 // ---------------------------- Constructor -------------------------------------

    public Loan(int loanId, int customer, BigDecimal principalAmount, BigDecimal interestRate,
                int loanTerm, String loanType, String loanStatus) throws InvalidInputException {
        setLoanId(loanId);
        setCustomer(customer);
        setPrincipalAmount(principalAmount);
        setInterestRate(interestRate);
        setLoanTerm(loanTerm);
        setLoanType(loanType);
        setLoanStatus(loanStatus);
    }
    
    
    
    
    // ---------------------------- Getters -------------------------------------------

    public int getLoanId() {
        return loanId;
    }

    public int getCustomer() {
        return customer;
    }

    public BigDecimal getPrincipalAmount() {
        return principalAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public String getLoanType() {
        return loanType;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    // -------------------------------- Setters with validations ------------------------

    public void setLoanId(int loanId) throws InvalidInputException {
        if (loanId <= 0) {
            throw new InvalidInputException("Loan ID should be positive.");
        }
        this.loanId = loanId;
    }

    public void setCustomer(int customer) throws InvalidInputException {
        if (customer <= 0) {
            throw new InvalidInputException("Customer ID must be positive.");
        }
        this.customer = customer;
    }

    public void setPrincipalAmount(BigDecimal principalAmount) throws InvalidInputException {
        if (principalAmount == null || principalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInputException("Principal amount must be positive.");
        }
        this.principalAmount = principalAmount;
    }

    public void setInterestRate(BigDecimal interestRate) throws InvalidInputException {
        if (interestRate == null || interestRate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInputException("Interest rate must be positive.");
        }
        this.interestRate = interestRate;
    }

    public void setLoanTerm(int loanTerm) throws InvalidInputException {
        if (loanTerm <= 0) {
            throw new InvalidInputException("Loan term must be positive.");
        }
        this.loanTerm = loanTerm;
    }

    public void setLoanType(String loanType) throws InvalidInputException {
        if (loanType == null || 
            !(loanType.equalsIgnoreCase("CarLoan") || loanType.equalsIgnoreCase("HomeLoan"))) {
            throw new InvalidInputException("Loan type must be either 'CarLoan' or 'HomeLoan'.");
        }
        this.loanType = loanType;
    }

    public void setLoanStatus(String loanStatus) throws InvalidInputException {
        if (loanStatus == null || !(loanStatus.equalsIgnoreCase("Pending") || loanStatus.equalsIgnoreCase("Approved"))) {
            throw new InvalidInputException("Loan status must be either 'Pending' or 'Approved'.");
        }
        this.loanStatus = loanStatus;
    }

    
}

