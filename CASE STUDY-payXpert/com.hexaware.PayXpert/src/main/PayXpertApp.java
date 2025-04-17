package main;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import com.hexaware.PayXpert.dao.*;
import com.hexaware.PayXpert.entity.Employee;
import com.hexaware.PayXpert.entity.FinancialRecord.RecordType;
import com.hexaware.PayXpert.exceptions.DatabaseConnectionException;
import com.hexaware.PayXpert.exceptions.FinancialRecordException;

public class PayXpertApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {


            while (true) {
                System.out.println("\n===== PayXpert Main Menu =====");
                System.out.println("1. Employee Service");
                System.out.println("2. Payroll Service");
                System.out.println("3. Tax Service");
                System.out.println("4. Financial Record Service");
                System.out.println("0. Exit");
                System.out.print("Choose a service: ");
                int serviceChoice = scanner.nextInt();

                switch (serviceChoice) {
                    case 1:
                        EmployeeServiceMenu(scanner);
                        break;
                    case 2:
                        PayrollServiceMenu( scanner);
                        break;
                    case 3:
                        TaxServiceMenu( scanner);
                        break;
                    case 4:
                        FinancialRecordServiceMenu( scanner);
                        break;
                    case 0:
                        System.out.println("Exiting PayXpert. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }

        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            }

        }


        
        
        
//-----------------------------employee service---------------------------------------------------------------------------------------------
        
        
    private static void EmployeeServiceMenu( Scanner scanner) throws Exception {
    	System.out.println("\n--- Employee Service ---");
        System.out.println("1. Get Employee by ID");
        System.out.println("2. Get All Employees");
        System.out.println("3. Add New Employee");
        System.out.println("4. Update Employee");
        System.out.println("5. Remove Employee");
        System.out.print("Choose an operation: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        EmployeeService employeeService = new EmployeeService();

        try {
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    employeeService.getEmployeeById(id);
                }
                case 2 -> {
                    employeeService.getAllEmployees();
                }
                case 3 -> {
                	System.out.println("Enter Employee Details:");

                    System.out.print("Employee ID: ");
                    int employeeId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Date of Birth (yyyy-MM-dd): ");
                    LocalDate dob = LocalDate.parse(scanner.nextLine());

                    System.out.print("Gender (M/F): ");
                    String gender = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Phone Number: ");
                    String phone = scanner.nextLine();

                    System.out.print("Address: ");
                    String address = scanner.nextLine();

                    System.out.print("Position: ");
                    String position = scanner.nextLine();

                    System.out.print("Joining Date (YYYY-MM-dd): ");
                    LocalDate joiningDate = LocalDate.parse(scanner.nextLine());
                    
                    System.out.print("Termination Date (YYYY-MM-dd) if not available press enter: ");
                    LocalDate terminationDate = LocalDate.parse(scanner.nextLine());

                    Employee newEmp = new Employee(employeeId, firstName, lastName, dob, gender, email, phone, address, position, joiningDate,terminationDate);
                    employeeService.addEmployee(newEmp);
                    System.out.println("Employee added successfully.");
                }
                case 4 -> {
                	System.out.println("Enter Employee Details:");

                    System.out.print("Employee ID: ");
                    int employeeId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Date of Birth (yyyy-MM-dd): ");
                    LocalDate dob = LocalDate.parse(scanner.nextLine());

                    System.out.print("Gender (M/F): ");
                    String gender = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Phone Number: ");
                    String phone = scanner.nextLine();

                    System.out.print("Address: ");
                    String address = scanner.nextLine();

                    System.out.print("Position: ");
                    String position = scanner.nextLine();

                    System.out.print("Joining Date (YYYY-MM-dd): ");
                    LocalDate joiningDate = LocalDate.parse(scanner.nextLine());
                    
                    System.out.print("Termination Date (YYYY-MM-dd) if not available press enter: ");
                    LocalDate terminationDate = LocalDate.parse(scanner.nextLine());

                    Employee emp = new Employee(employeeId, firstName, lastName, dob, gender, email, phone, address, position, joiningDate,terminationDate);
                    employeeService.updateEmployee(emp);
                    System.out.println("Employee updated successfully.");
                }
                case 5 -> {
                    System.out.print("Enter Employee ID to remove: ");
                    int id = scanner.nextInt();
                    employeeService.removeEmployee(id);
                    System.out.println("Employee removed successfully.");
                }
                default -> System.out.println("Invalid option selected.");
            }
            } catch (Exception e) {
            throw new Exception("Operation failed: " + e.getMessage());
        }
    }

    
   
    
    
    
    
    
    
    
    
//----------------------------------------pay roll service-----------------------------------------------------------------------
    
    private static void PayrollServiceMenu( Scanner scanner) throws Exception {
        System.out.println("\n--- Payroll Service ---");
        System.out.println("1. Generate Payroll");
        System.out.println("2. View Payroll By ID");
        System.out.println("3. View Payrolls for Employee");
        System.out.println("4. View Payrolls for Period");
        System.out.println("5. Calculate Gross Salary");
        System.out.print("Choose an operation: ");
        int choice = scanner.nextInt();
        PayrollService service= new PayrollService ();

        try {
            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int empId = scanner.nextInt();
                    System.out.print("Enter Start Date (YYYY-MM-DD): ");
                    LocalDate start = LocalDate.parse(scanner.next());
                    System.out.print("Enter End Date (YYYY-MM-DD): ");
                    LocalDate end = LocalDate.parse(scanner.next());
                    service.generatePayroll(empId, start, end);
                    break;
                case 2:
                    System.out.print("Enter Payroll ID: ");
                    int payrollId = scanner.nextInt();
                    service.getPayrollById(payrollId);
                    break;
                case 3:
                    System.out.print("Enter Employee ID: ");
                    int empID = scanner.nextInt();
                    service.getPayrollsForEmployee(empID);
                    break;
                case 4:
                    System.out.print("Enter Start Date (YYYY-MM-DD): ");
                    LocalDate s = LocalDate.parse(scanner.next());
                    System.out.print("Enter End Date (YYYY-MM-DD): ");
                    LocalDate e = LocalDate.parse(scanner.next());
                    service.getPayrollsForPeriod(s, e);
                    break;
                case 5:
                    System.out.print("Enter Employee ID to calculate gross salary: ");
                    int employeeId = scanner.nextInt();
                    BigDecimal grossSalary = service.calculateGrossSalary(employeeId);
                    System.out.println("The Gross Salary for Employee ID " + employeeId + " is: " + grossSalary);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } catch (Exception e) {
            throw new Exception("Operation failed: " + e.getMessage());
        }
    }

    
    
    
    
    
    
    
 //--------------------------------tax service--------------------------------------------------------------------------------
    private static void TaxServiceMenu( Scanner scanner) throws Exception {
    	System.out.println("\n--- Tax Service ---");
        System.out.println("1. Calculate Tax");
        System.out.println("2. Get Tax by ID");
        System.out.println("3. Get Taxes for Employee");
        System.out.println("4. Get Taxes for Year");
        System.out.print("Choose an operation: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        TaxService taxService= new TaxService ();

        try {
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Employee ID: ");
                    int empId = scanner.nextInt();
                    System.out.print("Enter Tax Year: ");
                    int year = scanner.nextInt();
                    BigDecimal taxAmount=taxService.calculateTax(empId, year);
                }
                case 2 -> {
                    System.out.print("Enter Tax ID: ");
                    int taxId = scanner.nextInt();
                    taxService.getTaxById(taxId);
                }
                case 3 -> {
                    System.out.print("Enter Employee ID: ");
                    int empId = scanner.nextInt();
                    taxService.getTaxesForEmployee(empId);
                }
                case 4 -> {
                    System.out.print("Enter Tax Year: ");
                    int year = scanner.nextInt();
                    taxService.getTaxesForYear(year);
                }
                default -> System.out.println("Invalid option selected.");
            }
        } catch (Exception e) {
            throw new Exception("Tax Service Operation failed: " + e.getMessage());
        }
    }

    
    
    
    
    
    
    
    
//--------------------------------------financial record service-------------------------------------------------------------------------
    private static void FinancialRecordServiceMenu( Scanner scanner) throws FinancialRecordException, DatabaseConnectionException {
    	System.out.println("\n--- Financial Record Service ---");
        System.out.println("1. Add Financial Record");
        System.out.println("2. Get Financial Record by ID");
        System.out.println("3. Get Financial Records for Employee");
        System.out.println("4. Get Financial Records for Date");
        System.out.println("5. Get All Financial Records");
        System.out.println("6. Update Financial Record");
        System.out.println("7. Delete Financial Record");
        System.out.print("Choose an operation: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        FinancialRecordService service = new FinancialRecordService();

        try {
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Employee ID: ");
                    int empId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Record Type (Income, Expense, Tax Payment): ");
                    RecordType type = RecordType.valueOf(scanner.nextLine().toUpperCase().replace(" ", "_"));
                    service.addFinancialRecord(empId, description, amount, type);
                }
                case 2 -> {
                    System.out.print("Enter Record ID: ");
                    int recordId = scanner.nextInt();
                    service.getFinancialRecordById(recordId);
                }
                case 3 -> {
                    System.out.print("Enter Employee ID: ");
                    int empId = scanner.nextInt();
                    service.getFinancialRecordsForEmployee(empId);
                }
                case 4 -> {
                    System.out.print("Enter Record Date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.next());
                    service.getFinancialRecordsForDate(date);
                }
                case 5 -> service.getAllFinancialRecords();
                case 6 -> {
                    System.out.print("Enter Financial Record ID: ");
                    int frId = scanner.nextInt();
                    System.out.print("Enter Employee ID: ");
                    int empId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Record Date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Record Type (Income, Expense, Tax Payment): ");
                    RecordType type = RecordType.valueOf(scanner.nextLine().toUpperCase().replace(" ", "_"));
                    service.updateFinancialRecord(frId, empId, description, date, amount, type);
                }
                case 7 -> {
                    System.out.print("Enter Record ID to delete: ");
                    int deleteId = scanner.nextInt();
                    service.deleteFinancialRecord(deleteId);
                }
                default -> System.out.println("Invalid option.");
            }
        } catch (Exception e) {
            throw new FinancialRecordException("Financial Record Operation failed: " + e.getMessage());
        }
    }
}

