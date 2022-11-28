package com.example.AppointmentManager.controllers;

import com.example.AppointmentManager.controllers.DTO.DoctorInputDtoDto;
import com.example.AppointmentManager.domain.personalExceptions.*;
import com.example.AppointmentManager.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.TreeMap;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/doctors")
    public ResponseEntity<String> addDoctor(@Valid @RequestBody DoctorInputDtoDto doctorInputDto){
        try {
            doctorService.addDoctor(doctorInputDto);
        } catch (DoctorExistsException | CollegiateNumberExistsException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/doctors/{dni}/timetable/{startHour}/{endHour}")
    public ResponseEntity<String> setTimetable(@PathVariable String dni, @PathVariable String startHour, @PathVariable String endHour){
        try {
            doctorService.setTimetable(dni, startHour, endHour);
        } catch (TimetableIncorrectException | DoctorNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/doctors/{dni}")
    public ResponseEntity<TreeMap<LocalDate, ArrayList<LocalTime>>> getAvailability(@PathVariable String dni){
        try {
            return ResponseEntity.ok(doctorService.getAvailableSpace(dni));
        } catch (DoctorNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
