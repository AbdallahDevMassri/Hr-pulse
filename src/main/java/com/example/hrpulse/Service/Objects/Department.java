package com.example.hrpulse.Service.Objects;

public class Department {
    private String name;
    private String description;
    private Boolean Status;

    public Department(String name, Boolean status) {
        this.name = name;
        Status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }
}
