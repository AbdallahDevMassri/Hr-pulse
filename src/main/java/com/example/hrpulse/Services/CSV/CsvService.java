package com.example.hrpulse.Services.CSV;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvService {

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


    public static void writeCsv(String filePath, List<String[]> csvData) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(csvData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}



