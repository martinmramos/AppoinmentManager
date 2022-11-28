package com.example.AppointmentManager.domain.personalExceptions;

public class SanitaryNotFoundException extends Exception{
    public SanitaryNotFoundException(String message) {
        super(message);
    }
}
