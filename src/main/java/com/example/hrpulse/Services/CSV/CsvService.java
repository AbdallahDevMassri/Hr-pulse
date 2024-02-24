package com.example.hrpulse.Services.CSV;


<<<<<<< HEAD
import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading and writing CSV files using OpenCSV library.
 */
public class CsvService {

    /**
     * Reads CSV data from the specified file path.
     *
     * @param csvFilePath The path of the CSV file to read.
     * @return A list of String arrays representing the CSV data.
     * @throws IOException If an I/O error occurs.
     */
    public static List<String[]> readCsv(String csvFilePath) throws IOException {
        List<String[]> csvData = new ArrayList<>();
        if (csvFilePath.isEmpty()) {
            // Handle the case where the file path is empty.
            return csvData;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (int i = 0; i < values.length; i++) {
                    // Remove leading and trailing whitespaces and double quotes from each value
                    values[i] = values[i].trim().replaceAll("^\"|\"$", "");
                }
                csvData.add(values);
            }
=======
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
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159
        }

        return csvData;
    }

<<<<<<< HEAD
    /**
     * Writes CSV data to the specified file.
     *
     * @param filePath The path of the CSV file to write.
     * @param csvData  The list of String arrays representing the CSV data.
     */
    public static void writeCsv(String filePath, List<String[]> csvData) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
=======
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
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159
            writer.writeAll(csvData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



<<<<<<< HEAD

=======
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159
