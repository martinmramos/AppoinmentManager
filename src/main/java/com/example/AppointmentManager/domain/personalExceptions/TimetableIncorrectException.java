package com.example.AppointmentManager.domain.personalExceptions;

public class TimetableIncorrectException extends Exception{
    public TimetableIncorrectException(String message) {
        super(message);
    }
}
