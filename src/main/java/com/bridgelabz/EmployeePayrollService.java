package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {

    private List<EmployeePayrollData> employeeList = new ArrayList<>();

    public void readEmployeeData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // consume newline

        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();

        EmployeePayrollData employee = new EmployeePayrollData(id, name, salary);
        employeeList.add(employee);
    }

    public void writeEmployeeData() {
        System.out.println("\n---- Employee Payroll Data ----");
        employeeList.forEach(System.out::println);
    }
}