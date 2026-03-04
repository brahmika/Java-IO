package com.bridgelabz;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

public class EmployeePayrollService {

    private static final String FILE_PATH = "EmployeePayroll.txt";

    // Write employees to file
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

    // Count number of entries (lines)
    public long countEntries() {
        try {
            return Files.lines(Paths.get(FILE_PATH)).count();
        } catch (IOException e) {
            System.out.println("Error reading file.");
            return 0;
        }
    }

    public static void main(String[] args) {

        EmployeePayrollData emp1 =
                new EmployeePayrollData(101, "Brahmika", 50000);

        EmployeePayrollData emp2 =
                new EmployeePayrollData(102, "Rahul", 60000);

        List<EmployeePayrollData> employees =
                Arrays.asList(emp1, emp2);

        EmployeePayrollService service =
                new EmployeePayrollService();

        service.writeData(employees);

        long count = service.countEntries();
        System.out.println("Number of entries in file: " + count);
    }
}