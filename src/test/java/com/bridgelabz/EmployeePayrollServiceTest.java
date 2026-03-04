package com.bridgelabz;

import org.junit.jupiter.api.*;

import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeePayrollServiceTest {

    private static final String FILE_PATH = "EmployeePayroll.txt";
    private EmployeePayrollFileIOService fileService;
    private EmployeePayrollAnalysisService analysisService;

    @BeforeEach
    void setUp() {
        fileService = new EmployeePayrollFileIOService();
        analysisService = new EmployeePayrollAnalysisService();
    }

    @AfterEach
    void cleanUp() throws Exception {
        Files.deleteIfExists(Paths.get(FILE_PATH));
    }

    @Test
    void givenEmployeeData_whenWrittenToFile_shouldMatchEntryCount() {

        List<EmployeePayrollData> employees = Arrays.asList(
                new EmployeePayrollData(1, "Brahmika", 50000),
                new EmployeePayrollData(2, "Rahul", 60000)
        );

        fileService.writeData(employees);

        long count = fileService.countEntries();

        assertEquals(2, count);
    }

    @Test
    void givenPayrollFile_whenRead_shouldReturnCorrectListSize() {

        List<EmployeePayrollData> employees = Arrays.asList(
                new EmployeePayrollData(1, "Asha", 40000),
                new EmployeePayrollData(2, "Kiran", 70000),
                new EmployeePayrollData(3, "Meena", 45000)
        );

        fileService.writeData(employees);

        List<EmployeePayrollData> readEmployees =
                analysisService.readData();

        assertEquals(3, readEmployees.size());
    }

    @Test
    void givenPayrollFile_whenAnalyzed_shouldReturnCorrectAverageSalary() {

        List<EmployeePayrollData> employees = Arrays.asList(
                new EmployeePayrollData(1, "A", 10000),
                new EmployeePayrollData(2, "B", 20000),
                new EmployeePayrollData(3, "C", 30000)
        );

        fileService.writeData(employees);

        List<EmployeePayrollData> readEmployees =
                analysisService.readData();

        double averageSalary = readEmployees.stream()
                .mapToDouble(EmployeePayrollData::getSalary)
                .average()
                .orElse(0);

        assertEquals(20000, averageSalary);
    }
}