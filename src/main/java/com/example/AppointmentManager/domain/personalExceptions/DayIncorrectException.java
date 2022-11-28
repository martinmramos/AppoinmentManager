package com.example.AppointmentManager.domain.personalExceptions;

public class DayIncorrectException extends Exception{
    public DayIncorrectException(String message) {
        super(message);
    }
}
