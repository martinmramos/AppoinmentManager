package com.example.AppointmentManager.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity(name = "appointments")
@IdClass(AppointmentID.class)
public class Appointment {

    @Id
    private String patientDni;
    @Id
    private String sanitaryDni;
    @Id
    private LocalDate day;
    @Id
    private LocalTime hour;

    public Appointment(String patientDni, String sanitaryDni, LocalDate day, LocalTime hour) {
        this.patientDni = patientDni;
        this.sanitaryDni = sanitaryDni;
        this.day = day;
        this.hour = hour;
    }

    public Appointment() {
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
}
