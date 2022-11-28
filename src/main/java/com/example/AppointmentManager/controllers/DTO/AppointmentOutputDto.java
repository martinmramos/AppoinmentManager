package com.example.AppointmentManager.controllers.DTO;


public class AppointmentOutputDto implements Comparable<AppointmentOutputDto>{

    private String name;
    private String hour;

    public AppointmentOutputDto(String name, String hour) {
        this.name = name;
        this.hour = hour;
    }

    public AppointmentOutputDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    @Override
    public int compareTo(AppointmentOutputDto other) {
        return this.hour.compareTo(other.getHour());
    }
}
