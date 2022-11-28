package com.example.AppointmentManager.repositories;

import com.example.AppointmentManager.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    List<Appointment> findByPatientDni(String dni);

    List<Appointment> findBySanitaryDni(String dni);

    List<Appointment> findBySanitaryDniAndDayBetween(String dni, LocalDate firstDay, LocalDate lastDay);

    List<Appointment> findBySanitaryDniAndDay(String dni, LocalDate day);

    List<Appointment> findByPatientDniAndDay(String dni, LocalDate day);

    boolean existsBySanitaryDniAndDay(String dni, LocalDate day);

    @Query(value = "SELECT appointment_manager.patients.name FROM appointment_manager.patients INNER JOIN appointment_manager.appointments ON patients.dni=appointments.patient_dni WHERE appointments.patient_dni= ?1", nativeQuery = true)
    HashSet<String> getNamePatient(String dni);
}