package com.example.AppointmentManager.domain.personalExceptions;

public class PatientExistsException extends Exception {
    public PatientExistsException(String message) {
        super(message);
    }
}
