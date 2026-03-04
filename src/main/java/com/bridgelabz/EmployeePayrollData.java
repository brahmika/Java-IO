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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id +
                ", Name: " + name +
                ", Salary: " + salary;
    }
}