package com.example.AppointmentManager.domain.personalExceptions;

public class DoctorNotFoundException extends Exception{
    public DoctorNotFoundException(String message) {
        super(message);
    }
}
