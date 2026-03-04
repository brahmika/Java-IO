package com.bridgelabz;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class FileOperationsDemo {

    public static void main(String[] args) {

        String directoryName = "PayrollDirectory";
        String fileName = "employee.txt";

        Path directoryPath = Paths.get(directoryName);
        Path filePath = Paths.get(directoryName, fileName);

        try {

            // Check File Exists
            System.out.println("Checking if file exists...");
            if (Files.exists(filePath)) {
                System.out.println("File exists.");
            } else {
                System.out.println("File does not exist.");
            }

            // Create Directory
            System.out.println("\nCreating directory...");
            if (!Files.exists(directoryPath)) {
                Files.createDirectory(directoryPath);
                System.out.println("Directory created successfully.");
            } else {
                System.out.println("Directory already exists.");
            }

            // Create Empty File
            System.out.println("\nCreating empty file...");
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }

            // List All Files & Directories
            System.out.println("\nListing all files and directories:");
            try (Stream<Path> paths = Files.list(directoryPath)) {
                paths.forEach(path ->
                        System.out.println(path.getFileName()));
            }

            // List Files with Specific Extension (.txt)
            System.out.println("\nListing only .txt files:");
            try (Stream<Path> paths = Files.list(directoryPath)) {
                paths
                        .filter(path -> path.toString().endsWith(".txt"))
                        .forEach(path ->
                                System.out.println(path.getFileName()));
            }

            // Delete File and Check Again
            System.out.println("\nDeleting file...");
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                System.out.println("File deleted successfully.");
            }

            System.out.println("Checking file after deletion...");
            if (!Files.exists(filePath)) {
                System.out.println("File does not exist now.");
            }

        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}