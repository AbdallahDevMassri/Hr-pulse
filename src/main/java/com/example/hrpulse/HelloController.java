package com.example.hrpulse;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class HelloController {

    @FXML
    void btnOkClicked(ActionEvent event) {

        try {
            CSVWriter writer = new CSVWriter(new FileWriter("example.csv"));
            String[] header = {"Name", "Age", "City"};
            String[] data1 = {"John", "25", "New York"};
            String[] data2 = {"Jane", "30", "Los Angeles"};
            writer.writeNext(header);
            writer.writeNext(data1);
            writer.writeNext(data2);
            writer.close();
            System.out.println("CSV file written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Example of reading from a CSV file
        try {
            CSVReader reader = new CSVReader(new FileReader("example.csv"));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                System.out.println(nextLine[0] + " is " + nextLine[1] + " years old and lives in " + nextLine[2]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
