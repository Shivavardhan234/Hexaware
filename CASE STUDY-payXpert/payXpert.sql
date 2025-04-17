CREATE DATABASE payXpert;
USE payXpert;

CREATE TABLE Employee (
    EmployeeID INT PRIMARY KEY ,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender VARCHAR(1) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(15) UNIQUE NOT NULL,
    Address VARCHAR(200) NOT NULL,
    Position VARCHAR(50) NOT NULL,
    JoiningDate DATE NOT NULL,
    TerminationDate DATE 
);


CREATE TABLE Payroll (
    PayrollID INT PRIMARY KEY ,
    EmployeeID INT,
    PayPeriodStartDate DATE NOT NULL,
    PayPeriodEndDate DATE NOT NULL,
    BasicSalary DECIMAL(15,2) NOT NULL,
    OvertimePay DECIMAL(15,2) NOT NULL,
    Deductions DECIMAL(15,2) NOT NULL,
    NetSalary DECIMAL(15,2) NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Tax (
    TaxID INT PRIMARY KEY ,
    EmployeeID INT,
    TaxYear INT NOT NULL,
    TaxableIncome DECIMAL(15,2) NOT NULL,
    TaxAmount DECIMAL(15,2) NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE FinancialRecord (
    RecordID INT PRIMARY KEY ,
    EmployeeID INT,
    RecordDate DATE NOT NULL,
    fDescription VARCHAR(500) NOT NULL,
    Amount DECIMAL(15,2) NOT NULL,
    RecordType ENUM('Income', 'Expense', 'Tax Payment') NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Employee VALUES
(1, 'Vardaniya', 'Dasgupta', '1997-06-06', 'M', 'dhanuk84@sha.com', '07075228349', '49/883, Bera Road, Tezpur-266880', 'Software Engineer', '2023-12-08', NULL),
(2, 'Aayush', 'Salvi', '1983-06-29', 'M', 'kjhaveri@hotmail.com', '+911952810186', '32, Joshi Chowk, Amritsar 095405', 'HR Manager', '2022-11-10', '2024-03-15'),
(3, 'Priya', 'Sharma', '1992-03-15', 'F', 'priya.sharma@gmail.com', '09876543210', '101, Anand Vihar, Delhi-110092', 'Marketing Analyst', '2024-01-20', NULL),
(4, 'Rahul', 'Verma', '1988-11-22', 'M', 'rahul.v@yahoo.co.in', '08899776655', '56, Nehru Street, Chennai-600001', 'Project Manager', '2023-05-15', '2025-01-31'),
(5, 'Sneha', 'Patel', '1995-09-01', 'F', 'sneha.patel12@rediffmail.com', '09988776655', '7/B, Sai Nagar, Mumbai-400068', 'Data Scientist', '2024-03-01', NULL),
(6, 'Vikram', 'Singh', '1985-04-10', 'M', 'vikramsingh.info@gmail.com', '07766554433', '12, Gandhi Colony, Kolkata-700029', 'Business Analyst', '2022-09-25', NULL),
(7, 'Deepika', 'Menon', '1990-07-18', 'F', 'deepika.m@outlook.com', '+919000112233', '45, MG Road, Bangalore-560001', 'Finance Manager', '2023-07-01', '2024-12-31'),
(8, 'Suresh', 'Kumar', '1982-12-28', 'M', 'suresh.k@email.com', '08012345678', '23, Patel Nagar, Hyderabad-500082', 'Team Lead', '2022-06-10', NULL),
(9, 'Anjali', 'Gupta', '1996-02-05', 'F', 'anjali.gupta@hotmail.com', '09765432109', '8, Shastri Nagar, Pune-411007', 'UX Designer', '2024-02-15', NULL),
(10, 'Rohan', 'Joshi', '1989-08-20', 'M', 'rohan.joshi@yahoo.com', '08123456789', '30, Tilak Road, Nagpur-440001', 'System Administrator', '2023-11-01', '2025-05-10');




INSERT INTO Payroll (PayrollID, EmployeeID, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OvertimePay, Deductions, NetSalary) VALUES
(1, 1, '2024-01-01', '2024-01-31', 50000.00, 5000.00, 1500.00, 53500.00), 
(2, 2, '2024-02-01', '2024-02-29', 80000.00, 2000.00, 4500.00, 75500.00),
(3, 3, '2025-01-01', '2025-01-31', 60000.00, 1000.00, 1500.00, 59500.00), 
(4, 4, '2025-02-01', '2025-02-28', 100000.00, 0.00, 7500.00, 92500.00), 
(5, 5, '2024-03-01', '2024-03-31', 40000.00, 8000.00, 1500.00, 46500.00), 
(6, 6, '2024-04-01', '2024-04-30', 70000.00, 3000.00, 3000.00, 70000.00), 
(7, 7, '2025-03-01', '2025-03-31', 90000.00, 10000.00, 9000.00, 91000.00), 
(8, 8, '2025-04-01', '2025-04-30', 55000.00, 2000.00, 1500.00, 55500.00),  
(9, 9, '2024-05-01', '2024-05-31', 120000.00, 0.00, 16500.00, 103500.00),  
(10, 10, '2024-06-01', '2024-06-30', 90000000.00, 10000000.00, 30000000.00, 70000000.00);



INSERT INTO Tax (TaxID, EmployeeID, TaxYear, TaxableIncome, TaxAmount) VALUES
(1, 1, 2024, 60000.00, 1500.00),  
(2, 2, 2024, 82000.00, 4500.00),  
(3, 3, 2025, 61000.00, 1500.00), 
(4, 4, 2025, 100000.00, 7500.00), 
(5, 5, 2024, 48000.00, 1500.00),  
(6, 6, 2024, 73000.00, 3000.00),  
(7, 7, 2025, 100000.00, 9000.00),
(8, 8, 2025, 57000.00, 1500.00),  
(9, 9, 2024, 120000.00, 16500.00), 
(10, 10, 2024, 100000000.00, 30000000.00); 


INSERT INTO FinancialRecord (RecordID, EmployeeID, RecordDate, fDescription, Amount, RecordType) VALUES
(1, 1, '2024-01-15', 'Salary payment for January 2024', 55000.00, 'Income'),
(2, 1, '2024-01-20', 'Tax payment for January 2024', 1500.00, 'Tax Payment'),
(3, 2, '2024-02-15', 'Salary payment for February 2024', 77500.00, 'Income'),
(4, 2, '2024-02-20', 'Tax payment for February 2024', 4500.00, 'Tax Payment'),
(5, 3, '2025-01-15', 'Salary payment for January 2025', 59500.00, 'Income'),
(6, 3, '2025-01-20', 'Tax payment for January 2025', 1500.00, 'Tax Payment'),
(7, 4, '2025-02-15', 'Salary payment for February 2025', 92500.00, 'Income'),
(8, 4, '2025-02-20', 'Tax payment for February 2025', 7500.00, 'Tax Payment'),
(9, 5, '2024-03-15', 'Office Supplies Expense', 1000.00, 'Expense'),
(10, 6, '2024-04-10', 'Bonus payment', 5000.00, 'Income'),
(11, 10, '2024-06-30', 'salary payment', 100000000.00, 'Income');
