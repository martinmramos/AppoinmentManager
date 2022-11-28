package com.example.AppointmentManager.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class AppointmentID implements Serializable {

    private String patientDni;
    private String sanitaryDni;
    private LocalDate day;
    private LocalTime hour;

    public AppointmentID(String patientDni, String sanitaryDni, String day, String hour) {
        this.patientDni = patientDni;
        this.sanitaryDni = sanitaryDni;
        this.day = LocalDate.parse(day);
        this.hour = LocalTime.parse(hour);
    }

    public AppointmentID() {
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
