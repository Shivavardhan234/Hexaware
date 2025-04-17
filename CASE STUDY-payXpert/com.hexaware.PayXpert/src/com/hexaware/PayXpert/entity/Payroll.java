package com.hexaware.PayXpert.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.hexaware.PayXpert.dao.TaxService;
import com.hexaware.PayXpert.exceptions.InvalidInputException;

public class Payroll {
	

    private int payrollId;
    private int employeeId;
    private LocalDate payPeriodStartDate;
    private LocalDate payPeriodEndDate;
    private BigDecimal basicSalary;
    private BigDecimal overtimePay;
    private BigDecimal deductions;
    private BigDecimal netSalary;
    private static int id = 11;

    public Payroll(int employeeId, LocalDate payPeriodStartDate, LocalDate payPeriodEndDate,
                   BigDecimal basicSalary, BigDecimal overtimePay) throws InvalidInputException {

        setPayrollId(id);
        setEmployeeId(employeeId);
        setPayPeriodStartDate(payPeriodStartDate);
        setPayPeriodEndDate(payPeriodEndDate);
        setBasicSalary(basicSalary);
        setOvertimePay(overtimePay);
        calculateDeductions();
        calculateNetSalary();
        id++;
    }

    public void setPayrollId(int payrollId) throws InvalidInputException {
        if (payrollId <= 0) throw new InvalidInputException("Payroll ID must be positive.");
        this.payrollId = payrollId;
    }

    public void setEmployeeId(int employeeId) throws InvalidInputException {
        if (employeeId <= 0) throw new InvalidInputException("Employee ID must be positive.");
        this.employeeId = employeeId;
    }

    public void setPayPeriodStartDate(LocalDate startDate) throws InvalidInputException {
        if (startDate == null) throw new InvalidInputException("Start date cannot be null.");
        this.payPeriodStartDate = startDate;
    }

    public void setPayPeriodEndDate(LocalDate endDate) throws InvalidInputException {
        if (endDate == null || (endDate.isBefore(payPeriodStartDate)))
            throw new InvalidInputException("End date cannot be null or before start date.");
        this.payPeriodEndDate = endDate;
    }

    public void setBasicSalary(BigDecimal basicSalary) throws InvalidInputException {
        if (basicSalary.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidInputException("Basic salary cannot be negative.");
        this.basicSalary = basicSalary;
    }

    public void setOvertimePay(BigDecimal overtimePay) throws InvalidInputException {
        if (overtimePay.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidInputException("Overtime pay cannot be negative.");
        this.overtimePay = overtimePay;
    }

    public void setDeductions(BigDecimal deductions) throws InvalidInputException {
        if (deductions.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidInputException("Deductions cannot be negative.");
        this.deductions = deductions;
    }

    public int getPayrollId() {
        return payrollId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public LocalDate getPayPeriodStartDate() {
        return payPeriodStartDate;
    }

    public LocalDate getPayPeriodEndDate() {
        return payPeriodEndDate;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public BigDecimal getOvertimePay() {
        return overtimePay;
    }

    public BigDecimal getDeductions() {
        return deductions;
    }

    public BigDecimal getNetSalary() {
        return netSalary;
    }

    public void calculateNetSalary() {
        this.netSalary = basicSalary.add(overtimePay).subtract(deductions);
    }

    public void calculateDeductions() {
        long daysInPeriod = ChronoUnit.DAYS.between(this.payPeriodStartDate, this.payPeriodEndDate) + 1;
        BigDecimal days = new BigDecimal(daysInPeriod);
        BigDecimal basicPerDay = basicSalary.divide(days, 2, BigDecimal.ROUND_HALF_UP);
        
        this.deductions = TaxService.calculatingTax(
                basicSalary.add(overtimePay),
                basicPerDay,
                payPeriodEndDate.getYear()
        );
    }
}
