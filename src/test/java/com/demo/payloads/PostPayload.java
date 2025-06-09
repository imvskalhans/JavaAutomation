package com.demo.payloads;

public class PostPayload {
    public String title;
    public String body;
    public int userId;

    public PostPayload(String title, String body, int userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }


}
