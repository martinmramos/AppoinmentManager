package com.example.AppointmentManager.repositories;

import com.example.AppointmentManager.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
    boolean existsByCollegiateNumber(int collegiateNumber);

    Doctor findByDni(String dni);
}