package com.bridgelabz;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class EmployeePayrollFileIOService {

    private static final String FILE_PATH = "EmployeePayroll.txt";

    // Write data (UC4)
    public void writeData(List<EmployeePayrollData> employeeList) {

        List<String> lines = employeeList.stream()
                .map(EmployeePayrollData::toFileFormat)
                .toList();

        try {
            Files.write(Paths.get(FILE_PATH),
                    lines,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("Data written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // UC5 - Print Employee Payrolls
    public void printPayrollData() {

        System.out.println("\n--- Employee Payroll Data ---");

        try {
            Files.lines(Paths.get(FILE_PATH))
                    .forEach(System.out::println);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Count entries (lines)
    public long countEntries() {
        try {
            return Files.lines(Paths.get(FILE_PATH)).count();
        } catch (IOException e) {
            System.out.println("Error counting entries.");
            return 0;
        }
    }

    public static void main(String[] args) {

        EmployeePayrollFileIOService service =
                new EmployeePayrollFileIOService();

        // Print data from file
        service.printPayrollData();

        // Count entries
        long count = service.countEntries();
        System.out.println("\nNumber of entries in file: " + count);
    }
}