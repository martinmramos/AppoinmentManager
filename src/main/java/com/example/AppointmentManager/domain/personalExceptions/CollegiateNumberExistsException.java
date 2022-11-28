package com.example.AppointmentManager.domain.personalExceptions;

public class CollegiateNumberExistsException extends Exception{
    public CollegiateNumberExistsException(String message) {
        super(message);
    }
}
