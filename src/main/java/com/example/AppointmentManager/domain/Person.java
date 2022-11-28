package com.example.AppointmentManager.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {

    @NotNull (message = "Name is null.")
    @NotBlank (message = "Name is empty.")
    private String name;
    @Id
    @NotNull(message = "DNI is null.")
    @NotBlank(message = "DNI is empty.")
    private String dni;

    public Person(String name, String dni) {
        this.name = name;
        this.dni = dni;
    }

    public Person(){

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
}
