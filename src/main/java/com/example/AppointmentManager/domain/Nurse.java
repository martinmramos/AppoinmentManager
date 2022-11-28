package com.example.AppointmentManager.domain;

import com.example.AppointmentManager.domain.personalExceptions.TimetableIncorrectException;

import javax.persistence.Entity;
import javax.validation.constraints.Positive;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

@Entity(name = "nurses")
public class Nurse extends Person {

    @Positive(message = "The college number is negative.")
    private int collegiateNumber;
    private LocalTime startTime;
    private LocalTime endTime;

    public Nurse(String name, String dni, int collegiateNumber) {
        super(name, dni);
        this.collegiateNumber = collegiateNumber;
    }

    public Nurse() {
    }

    public int getCollegiateNumber() {
        return collegiateNumber;
    }

    public void setCollegiateNumber(int collegiateNumber) {
        this.collegiateNumber = collegiateNumber;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setTimetable(String start, String end) throws TimetableIncorrectException {
        setStartTime(LocalTime.parse(start));
        setEndTime(LocalTime.parse(end));
        if (startTime.isAfter(endTime)) throw new TimetableIncorrectException("Timetable is incorrect.");
    }

    public LocalTime getLastHourAvailable() {
        LocalTime lastHour = LocalTime.of(this.endTime.getHour() - 1, 00);
        return lastHour;
    }

    public ArrayList<LocalDate> getDaysWindows(LocalDate today) {
        LocalDate firstDay = null;
        ArrayList<LocalDate> dayWindows = new ArrayList<>();
        int index = 0;
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            if (dayOfWeek.equals(today.getDayOfWeek())) {
                firstDay = today.plusDays(DayOfWeek.values().length - index);
                break;
            }
            index += 1;
        }
        for (int j = 0; j < 5; j++) {
            if (firstDay != null) {
                dayWindows.add(firstDay.plusDays(j));
            }
        }
        Collections.sort(dayWindows);
        return dayWindows;
    }

    public ArrayList<LocalTime> getHoursAvailable(){
        ArrayList<LocalTime> hoursAvailable = new ArrayList<>();
        int hours = getLastHourAvailable().getHour() - getStartTime().getHour();
        for (int i = 0; i <= hours; i++){
            hoursAvailable.add(getStartTime().plus(i, ChronoUnit.HOURS));
        }
        Collections.sort(hoursAvailable);
        return hoursAvailable;
    }
}
