package com.example.AppointmentManager.controllers.DTO;

import com.example.AppointmentManager.domain.Patient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PatientInputDto {

    @NotNull (message = "Name is null.")
    @NotBlank (message = "Name is empty.")
    private String name;
    @NotNull(message = "DNI is null.")
    @NotBlank(message = "DNI is empty.")
    private String dni;
    @NotNull (message = "Address is null.")
    @NotBlank (message = "Address is empty.")
    private String address;

    public PatientInputDto(String name, String dni, String address) {
        this.name = name;
        this.dni = dni;
        this.address = address;
    }

    public PatientInputDto() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Patient toDomain(){
        return new Patient(this.name, this.dni, this.address);
    }
}
