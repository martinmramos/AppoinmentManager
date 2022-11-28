package com.example.AppointmentManager.controllers;

import com.example.AppointmentManager.controllers.DTO.AppointmentInputDto;
import com.example.AppointmentManager.controllers.DTO.AppointmentOutputDto;
import com.example.AppointmentManager.domain.personalExceptions.PatientNotFoundException;
import com.example.AppointmentManager.domain.personalExceptions.SanitaryNotFoundException;
import com.example.AppointmentManager.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/appointments")
    public ResponseEntity<String> addAppointment(@Valid @RequestBody AppointmentInputDto appointmentInputDto) {
        try {
            appointmentService.addAppointment(appointmentInputDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/appointments/sanitary/{sanitaryDni}")
    public ResponseEntity<TreeMap<LocalDate, ArrayList<AppointmentOutputDto>>> getAppointmentsBySanitary(@PathVariable String sanitaryDni){
        try {
            return ResponseEntity.ok(appointmentService.getAppointmentsBySanitary(sanitaryDni));
        } catch (SanitaryNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/appointments/patient/{patientDni}/{day}")
    public ResponseEntity<ArrayList<AppointmentOutputDto>> getAppointmentsByPatientAndDay(@PathVariable String patientDni, @PathVariable String day){
        try {
            return ResponseEntity.ok(appointmentService.getAppointmentsByPatientAndDay(patientDni, day));
        } catch (PatientNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
