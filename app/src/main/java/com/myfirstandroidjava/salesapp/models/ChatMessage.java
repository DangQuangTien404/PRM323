package com.myfirstandroidjava.salesapp.models;

import java.util.Date;

public class ChatMessage {
    private String user;
    private String message;
    private Date timestamp;

    public ChatMessage(String user, String message, Date timestamp) {
        this.user = user;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
