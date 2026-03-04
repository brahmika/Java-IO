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

    public String toFileFormat() {
        return id + "," + name + "," + salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}