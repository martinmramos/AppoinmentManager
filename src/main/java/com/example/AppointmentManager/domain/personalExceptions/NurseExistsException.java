package com.example.AppointmentManager.domain.personalExceptions;

public class NurseExistsException extends Exception{
    public NurseExistsException(String message) {
        super(message);
    }
}
