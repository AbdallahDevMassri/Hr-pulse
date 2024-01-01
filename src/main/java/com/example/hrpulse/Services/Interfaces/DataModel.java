package com.example.hrpulse.Services.Interfaces;

public interface DataModel {
    String[] getDataAsArray();

    void initializeFromCsvData(String[] data);
}