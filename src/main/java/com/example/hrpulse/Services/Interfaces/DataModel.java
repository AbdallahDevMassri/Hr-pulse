package com.example.hrpulse.Services.Interfaces;

/**
 * The `DataModel` interface defines methods for handling data in a model.
 */
public interface DataModel {

    /**
     * Gets the data as an array.
     *
     * @return The data as an array.
     */
    String[] getDataAsArray();

    /**
     * Initializes the data from CSV (Comma-Separated Values) data.
     *
     * @param data The CSV data to initialize from.
     */
    void initializeFromCsvData(String[] data);
}
