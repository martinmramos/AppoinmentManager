package com.example.AppointmentManager.domain.personalExceptions;

public class HourIncorrectException extends Exception{
    public HourIncorrectException(String message) {
        super(message);
    }
}
