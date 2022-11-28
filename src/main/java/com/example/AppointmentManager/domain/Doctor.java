package com.example.AppointmentManager.domain;

import javax.persistence.Entity;
import javax.validation.constraints.PositiveOrZero;

@Entity(name = "doctors")
public class Doctor extends Nurse{

    @PositiveOrZero(message = "The years of experience is negative.")
    private int yearsOfExperience;

    public Doctor(String name, String dni, int collegeNumber, int yearsOfExperience) {
        super(name, dni, collegeNumber);
        this.yearsOfExperience = yearsOfExperience;
    }

    public Doctor(){

    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
