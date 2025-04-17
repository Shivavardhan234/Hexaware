package dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import Entity.Loan;
import Util.DBUtil;
import exceptions.*;


public class ILoanRepositoryImpl implements ILoanRepository  {
	

	private final Connection connection;

    public ILoanRepositoryImpl() throws SQLException {
        this.connection = DBUtil.getDBConn(); 
    }

    
 //-------------------------applying loan(adding)---------------------------------------
    @Override
    public void applyLoan(Loan loan) throws InvalidInputException,SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to apply for the loan? (Yes/No): ");
        String input = scanner.nextLine();
        if (!input.equalsIgnoreCase("Yes")) {
            System.out.println("Loan application cancelled.");
            return ;
        }

        String query = "INSERT INTO Loan (LoanId, customer, principalAmount, intrestRate, loanTerm, loanType, loanStatus) VALUES (?, ?, ?, ?, ?, ?, 'Pending')";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, loan.getLoanId());
            ps.setInt(2, loan.getCustomer());
            ps.setBigDecimal(3, loan.getPrincipalAmount());
            ps.setBigDecimal(4, loan.getInterestRate());
            ps.setInt(5, loan.getLoanTerm());
            ps.setString(6, loan.getLoanType());
            int rows = ps.executeUpdate();
            if(rows>0) {
            	
            	System.out.println("Loan applied successfully");
            }
            return;
        }
        catch (SQLException e) {
        	throw new SQLException("Error occured while applying loan",e);
        }
    }
    
    
    
    //-----------------------------calculating intrest-----------------------------------
    @Override
    public BigDecimal calculateInterest(int loanId) throws InvalidLoanException, InvalidInputException {
        Loan loan = getLoanById(loanId);
        System.out.println("------ Loan Details ------");
        System.out.println("Loan ID: " + loan.getLoanId());
        System.out.println("Customer ID: " + loan.getCustomer());
        System.out.println("Loan Type: " + loan.getLoanType());
        System.out.println("Principal Amount: Rs" + loan.getPrincipalAmount());
        System.out.println("Interest Rate: " + loan.getInterestRate() + "% per annum");
        System.out.println("Loan Term: " + loan.getLoanTerm() + " months");

        BigDecimal interest = calculateInterest(loan.getPrincipalAmount(), loan.getInterestRate(), loan.getLoanTerm());


        System.out.println("Calculated Total Interest: ₹" + interest);

        return interest;

    }
    
    private BigDecimal calculateInterest(BigDecimal principal, BigDecimal rate, int term) {
        return principal.multiply(rate).multiply(BigDecimal.valueOf(term)).divide(BigDecimal.valueOf(12*100), 2, BigDecimal.ROUND_HALF_EVEN);
    }
    
    
    
    //---------------------loan status------------------------------------
    
    @Override
    public void loanStatus(int loanId)  throws InvalidLoanException, InvalidInputException, SQLException {
        Loan loan = getLoanById(loanId);

        String customerQuery = "SELECT creditScore FROM Customer WHERE CustomerID=?";
        try (PreparedStatement ps = connection.prepareStatement(customerQuery)) {
            ps.setInt(1, loan.getCustomer());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int score = rs.getInt("creditScore");
                String status = score > 650 ? "Approved" : "Rejected";

                String updateQuery = "UPDATE Loan SET loanStatus=? WHERE LoanId=?";
                try (PreparedStatement ups = connection.prepareStatement(updateQuery)) {
                    ups.setString(1, status);
                    ups.setInt(2, loanId);
                    ups.executeUpdate();
                }
                
                System.out.println("------ Loan Status Update ------");
                System.out.println("Loan ID      : " + loanId);
                System.out.println("Customer ID  : " + loan.getCustomer());
                System.out.println("Credit Score : " + score);
                System.out.println("Loan Status  : " + status);

                return;
            } else {
                throw new InvalidLoanException("Customer not found for loan ID: " + loanId);
            }
        }
    }
    
    
    //------------------------calculate emi-------------------------------------------------
    @Override
    public BigDecimal calculateEMI(int loanId) throws InvalidLoanException, InvalidInputException {
        Loan loan = getLoanById(loanId);
        System.out.println("---------- Emi Details ------");
        System.out.println("Loan ID: " + loan.getLoanId());
        System.out.println("Customer ID: " + loan.getCustomer());
        System.out.println("Loan Type: " + loan.getLoanType());
        System.out.println("Principal Amount: ₹" + loan.getPrincipalAmount());
        System.out.println("Interest Rate: " + loan.getInterestRate() + "% per annum");
        System.out.println("Loan Term: " + loan.getLoanTerm() + " months");
        System.out.println("Loan Status: " + loan.getLoanStatus());
        BigDecimal emi= calculateEMI(loan.getPrincipalAmount(), loan.getInterestRate(), loan.getLoanTerm());
        System.out.println("Calculated Monthly EMI: ₹" + emi);
        System.out.println("-------------------- ------");
        return emi;
    }

    
    public BigDecimal calculateEMI(BigDecimal principal, BigDecimal rate, int tenure) {
        BigDecimal monthlyRate = rate.divide(BigDecimal.valueOf(12 * 100), 10, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal onePlusRPowerN = monthlyRate.add(BigDecimal.ONE).pow(tenure);
        BigDecimal numerator = principal.multiply(monthlyRate).multiply(onePlusRPowerN);
        BigDecimal denominator = onePlusRPowerN.subtract(BigDecimal.ONE);
        return numerator.divide(denominator, 2, BigDecimal.ROUND_HALF_EVEN);
    }
    
    
    //-------------------loan repayment------------------------------------
    
    @Override
    public void loanRepayment(int loanId, BigDecimal amount) throws InvalidLoanException, InvalidInputException {
        BigDecimal emi = calculateEMI(loanId);
        if (amount.compareTo(emi) < 0) {
            System.out.println("Amount is less than single EMI. Payment rejected.");
            return ;
        }

        int numEmis = amount.divide(emi, 0, BigDecimal.ROUND_DOWN).intValue();
        System.out.println("Number of EMIs paid: " + numEmis);
        
        return ;
    }

    @Override
    public List<Loan> getAllLoan() throws SQLException, InvalidInputException {
        List<Loan> loans = new ArrayList<>();
        String query = "SELECT * FROM Loan";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Loan loan = new Loan(
                    rs.getInt("LoanId"),
                    rs.getInt("customer"),
                    rs.getBigDecimal("principalAmount"),
                    rs.getBigDecimal("interestRate"),
                    rs.getInt("loanTerm"),
                    rs.getString("loanType"),
                    rs.getString("loanStatus")
                );
                loans.add(loan);
                
                System.out.println("--------------------------------------");
                System.out.println("Loan ID      : " + loan.getLoanId());
                System.out.println("Customer ID  : " + loan.getCustomer());
                System.out.println("Principal    : Rs" + loan.getPrincipalAmount());
                System.out.println("Interest Rate: " + loan.getInterestRate() + "%");
                System.out.println("Loan Term    : " + loan.getLoanTerm() + " months");
                System.out.println("Loan Type    : " + loan.getLoanType());
                System.out.println("Loan Status  : " + loan.getLoanStatus());
            
            }
            if (loans.isEmpty()) {
                System.out.println("No loans till now.");
            }
        }
        return loans;
    }

    
    //----------------------getting loan by id--------------------
    @Override
    public Loan getLoanById(int loanId) throws InvalidLoanException, InvalidInputException {
        String query = "SELECT * FROM Loan WHERE LoanId=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, loanId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Loan loan= new Loan(
                    rs.getInt("LoanId"),
                    rs.getInt("customer"),
                    rs.getBigDecimal("principalAmount"),
                    rs.getBigDecimal("interestRate"),
                    rs.getInt("loanTerm"),
                    rs.getString("loanType"),
                    rs.getString("loanStatus")
                );
                System.out.println("------ Loan Details ------");
                System.out.println("Loan ID      : " + loan.getLoanId());
                System.out.println("Customer ID  : " + loan.getCustomer());
                System.out.println("Principal    : Rs." + loan.getPrincipalAmount());
                System.out.println("Interest Rate: " + loan.getInterestRate() + "%");
                System.out.println("Loan Term    : " + loan.getLoanTerm() + " months");
                System.out.println("Loan Type    : " + loan.getLoanType());
                System.out.println("Loan Status  : " + loan.getLoanStatus());
                System.out.println("--------------------------");
                return loan;
            } else {
                throw new InvalidLoanException("Loan not found with ID: " + loanId);
            }
        } catch (SQLException e) {
            throw new InvalidLoanException("Error retrieving loan with ID: " + loanId);
        }
    }
}
