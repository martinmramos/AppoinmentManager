package com.example.AppointmentManager.domain.personalExceptions;

public class DoctorExistsException extends Exception{
    public DoctorExistsException(String message) {
        super(message);
    }
}
