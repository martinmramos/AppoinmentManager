package com.example.AppointmentManager.domain.personalExceptions;

public class PatientBusyException extends Exception{
    public PatientBusyException(String message) {
        super(message);
    }
}
