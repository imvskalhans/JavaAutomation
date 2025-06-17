package com.demo.payloads;

public class Payloads {
    public String name;
    public String job;

    // Default constructor (required for Jackson)
    public Payloads() {}

    public Payloads(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
