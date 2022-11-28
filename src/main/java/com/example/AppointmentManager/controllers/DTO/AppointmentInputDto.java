package com.example.AppointmentManager.controllers.DTO;

import com.example.AppointmentManager.domain.Appointment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentInputDto {

    @NotNull(message = "DNI is null.")
    @NotBlank(message = "DNI is empty.")
    private String patientDni;
    @NotNull(message = "DNI is null.")
    @NotBlank(message = "DNI is empty.")
    private String sanitaryDni;

    private LocalDate day;

    private LocalTime hour;

    public AppointmentInputDto(String patientDni, String sanitaryDni, String day, String hour) {
        this.patientDni = patientDni;
        this.sanitaryDni = sanitaryDni;
        this.day = LocalDate.parse(day);
        this.hour = LocalTime.parse(hour);
    }

    public AppointmentInputDto() {
    }

    public String getPatientDni() {
        return patientDni;
    }

    public void setPatientDni(String patientDni) {
        this.patientDni = patientDni;
    }

    public String getSanitaryDni() {
        return sanitaryDni;
    }

    public void setSanitaryDni(String sanitaryDni) {
        this.sanitaryDni = sanitaryDni;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public Appointment toDomain(){
        return new Appointment(this.patientDni, this.sanitaryDni, this.day, this.hour);
    }
}
