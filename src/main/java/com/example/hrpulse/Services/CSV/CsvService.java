package com.example.hrpulse.Services.CSV;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvService {

    /**
     * Reads a CSV file and returns its contents as a List of String arrays.
     *
     * @param filePath the file path of the CSV file to read
     * @return a List of String arrays representing the CSV data
     * @throws IOException if an I/O error occurs while reading the file
     */
    public static List<String[]> readCsv(String filePath) throws IOException {

        // Create a new CSVReader object using the given file path
        CSVReader reader = new CSVReader(new FileReader(filePath));

        List<String[]> csvData = null;
        try {
            // Read all the data from the CSV file into a List of String arrays
            csvData = reader.readAll();
        } finally {
            // Close the CSVReader to release system resources
            reader.close();
        }

        return csvData;
    }

    /*
     * Prints the contents of a CSV file to the console.
     *
     * @param csvData a List of String arrays representing the CSV data to print
     */
    public static void printCsv(List<String[]> csvData) {
        for (String[] row : csvData) {
            for (String cell : row) {
                // Print each cell followed by a tab character
                System.out.print(cell + "\t");
            }
            // Print a new line character to separate rows
            System.out.println();
        }
    }

    public static void writeCsv(String filePath, List<String[]> csvData) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Write all the data to the CSV file
            writer.writeAll(csvData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



