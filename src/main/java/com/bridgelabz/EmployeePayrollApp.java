package com.bridgelabz;

public class EmployeePayrollApp {

    public static void main(String[] args) {

        EmployeePayrollService payrollService = new EmployeePayrollService();

        payrollService.readEmployeeData();
        payrollService.writeEmployeeData();
    }
}