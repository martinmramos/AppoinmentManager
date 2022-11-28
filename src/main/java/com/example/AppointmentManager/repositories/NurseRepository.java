package com.example.AppointmentManager.repositories;

import com.example.AppointmentManager.domain.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse, String> {
    boolean existsByCollegiateNumber(int collegiateNumber);

    Nurse findByDni(String dni);

}