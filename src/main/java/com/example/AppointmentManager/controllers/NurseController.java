package com.example.AppointmentManager.controllers;

import com.example.AppointmentManager.controllers.DTO.NurseInputDto;
import com.example.AppointmentManager.domain.personalExceptions.*;
import com.example.AppointmentManager.services.NurseService;
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
public class NurseController {

    @Autowired
    private NurseService nurseService;

    @PostMapping("/nurses")
    public ResponseEntity<String> addNurse(@Valid @RequestBody NurseInputDto nurseInputDto){
        try {
            nurseService.addNurse(nurseInputDto);
        } catch (NurseExistsException | CollegiateNumberExistsException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/nurses/{dni}/timetable/{startHour}/{endHour}")
    public ResponseEntity<String> setTimetable(@PathVariable String dni, @PathVariable String startHour, @PathVariable String endHour){
        try {
            nurseService.setTimetable(dni, startHour, endHour);
        } catch (TimetableIncorrectException | NurseNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/nurses/{dni}")
    public ResponseEntity<TreeMap<LocalDate, ArrayList<LocalTime>>> getAvailability(@PathVariable String dni){
        try {
            return ResponseEntity.ok(nurseService.getAvailableSpace(dni));
        } catch (NurseNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
