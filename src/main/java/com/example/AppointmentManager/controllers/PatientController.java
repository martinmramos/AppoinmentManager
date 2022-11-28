package com.example.AppointmentManager.controllers;

import com.example.AppointmentManager.controllers.DTO.PatientInputDto;
import com.example.AppointmentManager.domain.personalExceptions.PatientExistsException;
import com.example.AppointmentManager.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/patients")
    public ResponseEntity<String> addPatient(@Valid @RequestBody PatientInputDto patientInputDto) {
        try {
            patientService.addPatient(patientInputDto);
        } catch (PatientExistsException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
