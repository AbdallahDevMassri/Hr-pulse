package com.example.hrpulse.Services.CSV;


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
        }

        return csvData;
    }

    /**
     * Writes CSV data to the specified file.
     *
     * @param filePath The path of the CSV file to write.
     * @param csvData  The list of String arrays representing the CSV data.
     */
    public static void writeCsv(String filePath, List<String[]> csvData) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(csvData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}




