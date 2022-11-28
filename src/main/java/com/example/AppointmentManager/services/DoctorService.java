package com.example.AppointmentManager.services;

import com.example.AppointmentManager.controllers.DTO.DoctorInputDtoDto;
import com.example.AppointmentManager.domain.Appointment;
import com.example.AppointmentManager.domain.Doctor;
import com.example.AppointmentManager.domain.personalExceptions.*;
import com.example.AppointmentManager.repositories.AppointmentRepository;
import com.example.AppointmentManager.repositories.DoctorRepository;
import com.example.AppointmentManager.repositories.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void addDoctor(DoctorInputDtoDto doctorInputDto) throws DoctorExistsException, CollegiateNumberExistsException {
        if (doctorRepository.existsById(doctorInputDto.getDni()))
            throw new DoctorExistsException("Doctor already exists.");
        if (doctorRepository.existsByCollegiateNumber(doctorInputDto.getCollegiateNumber()) | nurseRepository.existsByCollegiateNumber(doctorInputDto.getCollegiateNumber()))
            throw new CollegiateNumberExistsException("Collegiate number already exists.");
        doctorRepository.save(doctorInputDto.toDomain());
    }

    public void setTimetable(String dni, String start, String end) throws TimetableIncorrectException, DoctorNotFoundException {
        Optional<Doctor> doctor = doctorRepository.findById(dni);
        if (doctor.isEmpty()) throw new DoctorNotFoundException("Doctor doesn't exists.");
        doctor.get().setTimetable(start, end);
        doctorRepository.save(doctor.get());
    }

    public TreeMap<LocalDate, ArrayList<LocalTime>> getAvailableSpace(String dni) throws DoctorNotFoundException {
        LocalDate today = LocalDate.now();
        if (!doctorRepository.existsById(dni))
            throw new DoctorNotFoundException("Doctor doesn't exists.");
        Doctor doctor = doctorRepository.findByDni(dni);
        ArrayList<LocalDate> daysWindows = doctor.getDaysWindows(today);
        ArrayList<LocalTime> hoursAvailable = doctor.getHoursAvailable();
        TreeMap<LocalDate, ArrayList<LocalTime>> availableSpace = new TreeMap<>();
        for (LocalDate day : daysWindows) {
            ArrayList<LocalTime> copyHoursAvailable = new ArrayList<>(hoursAvailable);
            List<Appointment> appointments = appointmentRepository.findBySanitaryDniAndDay(dni, day);
            if (appointmentRepository.existsBySanitaryDniAndDay(doctor.getDni(), day)) {
                for (LocalTime hour : hoursAvailable) {
                    for (Appointment appointment : appointments) {
                        if (appointment.getHour().equals(hour)) {
                            copyHoursAvailable.remove(hour);
                        }
                    }
                }
            }
            availableSpace.put(day, copyHoursAvailable);
        }
        return availableSpace;
    }
}
