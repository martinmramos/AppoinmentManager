package com.example.AppointmentManager.domain.personalExceptions;

public class PatientNotFoundException extends Exception{
    public PatientNotFoundException(String message) {
        super(message);
    }
}
