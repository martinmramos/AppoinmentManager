package com.example.AppointmentManager.domain.personalExceptions;

public class NurseNotFoundException extends Exception {
    public NurseNotFoundException(String message) {
        super(message);
    }
}
