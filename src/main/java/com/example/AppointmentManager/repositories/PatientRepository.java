package com.example.AppointmentManager.repositories;

import com.example.AppointmentManager.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
}