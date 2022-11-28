package com.example.AppointmentManager.controllers.DTO;

import com.example.AppointmentManager.domain.Nurse;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NurseInputDto {

    @NotNull(message = "Name is null.")
    @NotBlank(message = "Name is empty.")
    private String name;
    @NotNull(message = "DNI is null.")
    @NotBlank(message = "DNI is empty.")
    private String dni;
    @Positive(message = "The college number is negative.")
    private int collegiateNumber;

    public NurseInputDto(String name, String dni, int collegiateNumber) {
        this.name = name;
        this.dni = dni;
        this.collegiateNumber = collegiateNumber;
    }

    public NurseInputDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getCollegiateNumber() {
        return collegiateNumber;
    }

    public void setCollegiateNumber(int collegiateNumber) {
        this.collegiateNumber = collegiateNumber;
    }

    public Nurse toDomain() {
        return new Nurse(this.name, this.dni, this.collegiateNumber);
    }
}
