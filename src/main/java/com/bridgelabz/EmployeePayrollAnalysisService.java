package com.bridgelabz;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.OptionalDouble;

public class EmployeePayrollAnalysisService {

    private static final String FILE_PATH = "EmployeePayroll.txt";

    public List<EmployeePayrollData> readData() {

        try {
            return Files.lines(Paths.get(FILE_PATH))
                    .map(EmployeePayrollData::fromString)
                    .toList();

        } catch (IOException e) {
            System.out.println("Error reading file.");
            return List.of();
        }
    }

    public void performAnalysis(List<EmployeePayrollData> employees) {

        System.out.println("\n--- Payroll Analysis ---");

        long count = employees.size();
        System.out.println("Total Employees: " + count);

        OptionalDouble totalSalary =
                employees.stream()
                        .mapToDouble(EmployeePayrollData::getSalary)
                        .reduce(Double::sum);

        System.out.println("Total Salary: " +
                (totalSalary.isPresent() ? totalSalary.getAsDouble() : 0));

        double averageSalary =
                employees.stream()
                        .mapToDouble(EmployeePayrollData::getSalary)
                        .average()
                        .orElse(0);

        System.out.println("Average Salary: " + averageSalary);

        employees.stream()
                .max((e1, e2) ->
                        Double.compare(e1.getSalary(), e2.getSalary()))
                .ifPresent(emp ->
                        System.out.println("Highest Paid Employee: "
                                + emp.getName()));
    }

    public static void main(String[] args) {

        EmployeePayrollAnalysisService service =
                new EmployeePayrollAnalysisService();

        List<EmployeePayrollData> employees = service.readData();

        service.performAnalysis(employees);
    }
}