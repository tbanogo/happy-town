package com.example.happytownclone.core.use_cases;

public class NotificationException extends RuntimeException{
    public NotificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
