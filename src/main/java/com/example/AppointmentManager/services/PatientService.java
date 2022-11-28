package com.example.AppointmentManager.services;

import com.example.AppointmentManager.controllers.DTO.PatientInputDto;
import com.example.AppointmentManager.domain.personalExceptions.PatientExistsException;
import com.example.AppointmentManager.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public void addPatient(PatientInputDto patientInputDto) throws PatientExistsException {
        if (patientRepository.existsById(patientInputDto.getDni()))
            throw new PatientExistsException("Patient already exists.");
        patientRepository.save(patientInputDto.toDomain());
    }
}
