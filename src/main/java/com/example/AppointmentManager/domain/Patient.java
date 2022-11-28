package com.example.AppointmentManager.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "patients")
public class Patient extends Person{

    @NotNull(message = "Address is null.")
    @NotBlank(message = "Address is empty.")
    private String address;

    public Patient(String name, String dni, String address) {
        super(name, dni);
        this.address = address;
    }

    public Patient(){

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
