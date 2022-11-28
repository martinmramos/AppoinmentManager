package com.example.AppointmentManager;

import com.example.AppointmentManager.domain.Nurse;
import com.example.AppointmentManager.domain.personalExceptions.TimetableIncorrectException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

@SpringBootTest
class AppointmentManagerApplicationTests {

    private Nurse nurse = new Nurse("Martin", "1234", 1);

    @Test
    public void setTimetable() throws TimetableIncorrectException {
        nurse.setTimetable("17:00", "18:00");
        LocalTime expectedStart = LocalTime.parse("17:00");
        LocalTime expectedEnd = LocalTime.parse("18:00");
        Assertions.assertEquals(expectedStart, nurse.getStartTime());
        Assertions.assertEquals(expectedEnd, nurse.getEndTime());
    }

    @Test
    public void getLastHourAvailable() throws TimetableIncorrectException {
        nurse.setTimetable("07:00", "09:00");
        LocalTime expected = LocalTime.parse("08:00");
        Assertions.assertEquals(expected, nurse.getLastHourAvailable());
    }

    @Test
    public void getDayWindows() {
        ArrayList<LocalDate> result = nurse.getDaysWindows(LocalDate.parse("2022-11-23"));
        ArrayList<LocalDate> expected = new ArrayList<>();
        expected.add(LocalDate.parse("2022-11-28"));
        expected.add(LocalDate.parse("2022-11-29"));
        expected.add(LocalDate.parse("2022-11-30"));
        expected.add(LocalDate.parse("2022-12-01"));
        expected.add(LocalDate.parse("2022-12-02"));
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getHoursAvailable() throws TimetableIncorrectException {
        nurse.setTimetable("10:00", "13:00");
        ArrayList<LocalTime> result = nurse.getHoursAvailable();
        ArrayList<LocalTime> expected = new ArrayList<>();
        expected.add(LocalTime.parse("10:00"));
        expected.add(LocalTime.parse("11:00"));
        expected.add(LocalTime.parse("12:00"));
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void comprobarDias(){
        ArrayList<LocalDate> daysWindows = new ArrayList<>(nurse.getDaysWindows(LocalDate.parse("2022-11-23")));
        Collections.sort(daysWindows);
        LocalDate firstDay = daysWindows.get(0);
        LocalDate lastDay = daysWindows.get(daysWindows.size()-1);
        LocalDate expectedFirst = LocalDate.parse("2022-11-28");
        LocalDate expectedLast = LocalDate.parse("2022-12-02");
        Assertions.assertEquals(expectedFirst, firstDay);
        Assertions.assertEquals(expectedLast, lastDay);
    }
}
