package com.hexaware.CodingChallenge.LoanManagementSystem.main;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

import com.hexaware.CodingChallenge.LoanManagementSystem.Entity.*;
import com.hexaware.CodingChallenge.LoanManagementSystem.dao.ILoanRepositoryImpl;

public class LoanManagementApp {
	public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        ILoanRepositoryImpl loanRepo = new ILoanRepositoryImpl();

        while (true) {
            System.out.println("======== Loan Repository Menu ========");
            System.out.println("1. Apply for Loan");
            System.out.println("2. Calculate Interest");
            System.out.println("3. Check Loan Status");
            System.out.println("4. Calculate EMI");
            System.out.println("5. Repay Loan");
            System.out.println("6. View All Loans");
            System.out.println("7. View Loan by ID");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            try {
                switch (choice) {
                    case 1:
                        System.out.println("\n--- Apply for Loan ---");
                        System.out.print("Enter Loan ID: ");
                        int loanId = sc.nextInt();
                        System.out.print("Enter Customer ID: ");
                        int custId = sc.nextInt();
                        System.out.print("Enter Principal Amount: ");
                        BigDecimal principal = sc.nextBigDecimal();
                        System.out.print("Enter Interest Rate: ");
                        BigDecimal rate = sc.nextBigDecimal();
                        System.out.print("Enter Loan Term (months): ");
                        int term = sc.nextInt();
                        sc.nextLine(); 
                        System.out.print("Enter Loan Type (HomeLoan/CarLoan): ");
                        String type = sc.nextLine();

                        Loan loan = new Loan(loanId, custId, principal, rate, term, type, "Pending");
                        

                        loanRepo.applyLoan(loan);
                        break;

                    case 2:
                        System.out.println("------- Calculate Interest-------");
                        System.out.print("Enter Loan ID: ");
                        int id = sc.nextInt();
                        BigDecimal interest = loanRepo.calculateInterest(id);
                        System.out.println("Interest for Loan ID " + id + " is: ₹" + interest);
                        break;

                    case 3:
                        System.out.println("--------- Checking Loan Status ----------");
                        System.out.print("Enter Loan ID: ");
                        int statId = sc.nextInt();
                        loanRepo.loanStatus(statId);
                        break;

                    case 4:
                        System.out.println("---------- Calculate EMI -------");
                        System.out.print("Enter Loan ID: ");
                        int emiId = sc.nextInt();
                        BigDecimal emi = loanRepo.calculateEMI(emiId);
                        
                        break;
                        
                        
    //-------------------------------repaying loan-------------------------------------------

                    case 5:
                        System.out.println("-----Repaying Loan ---");
                        System.out.print("Enter Loan ID: ");
                        int repayId = sc.nextInt();
                        System.out.print("Enter Repayment Amount: ");
                        BigDecimal amount = sc.nextBigDecimal();
                        loanRepo.loanRepayment(repayId, amount);
                        break;

                        
                        
                //----------------------get and display all loans--------------------------------------        
                    case 6:
                        System.out.println("------- All Loans ---");
                        List<Loan> allLoans = loanRepo.getAllLoan();
                        break;

                        
                        
                        
                        
                        
                        
                        
                        
                        
        //------------------------getting and printing loan by id--------------------------------------
                    case 7:
                        System.out.println("----Loan by ID ---");
                        System.out.print("Enter Loan ID: ");
                        int searchId = sc.nextInt();
                        Loan foundLoan = loanRepo.getLoanById(searchId);
                        
                        break;
                        
                        
                        
                        
        //-------------------exit case----------------------------------
                    case 8:
                        System.out.println("Exiting. Thank you!");
                        sc.close();
                        return;
                        
                        
                        
                        
                        
                        
//--------------------------------default case---------------------------------------
                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
	
	
	

}
