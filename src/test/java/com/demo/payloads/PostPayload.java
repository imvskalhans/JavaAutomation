package com.demo.payloads;

public class PostPayload {
    public String name;
    public String job;

    // Default constructor (required for Jackson)
    public PostPayload() {}

    public PostPayload(String name, String job) {
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
