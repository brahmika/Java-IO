package com.bridgelabz;

public class EmployeePayrollData {

    private int id;
    private String name;
    private double salary;

    public EmployeePayrollData(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public static EmployeePayrollData fromString(String line) {
        String[] parts = line.split(",");
        return new EmployeePayrollData(
                Integer.parseInt(parts[0]),
                parts[1],
                Double.parseDouble(parts[2])
        );
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}