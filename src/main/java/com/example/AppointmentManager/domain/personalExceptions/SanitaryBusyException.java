package com.example.AppointmentManager.domain.personalExceptions;

public class SanitaryBusyException extends Exception{
    public SanitaryBusyException(String message) {
        super(message);
    }
}
