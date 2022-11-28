package com.example.AppointmentManager.controllers.DTO;

import com.example.AppointmentManager.domain.Doctor;

import javax.validation.constraints.PositiveOrZero;

public class DoctorInputDtoDto extends NurseInputDto {

    @PositiveOrZero (message = "The years of experience is negative.")
    private int yearsOfExperience;

    public DoctorInputDtoDto(String name, String dni, int collegeNumber, int yearsOfExperience) {
        super(name, dni, collegeNumber);
        this.yearsOfExperience = yearsOfExperience;
    }

    public DoctorInputDtoDto() {
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Doctor toDomain(){
        return new Doctor(this.getName(), this.getDni(), this.getCollegiateNumber(), this.yearsOfExperience);
    }
}
