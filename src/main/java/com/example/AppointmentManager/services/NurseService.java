package com.example.AppointmentManager.services;

import com.example.AppointmentManager.controllers.DTO.NurseInputDto;
import com.example.AppointmentManager.domain.Appointment;
import com.example.AppointmentManager.domain.Nurse;
import com.example.AppointmentManager.domain.personalExceptions.CollegiateNumberExistsException;
import com.example.AppointmentManager.domain.personalExceptions.NurseExistsException;
import com.example.AppointmentManager.domain.personalExceptions.NurseNotFoundException;
import com.example.AppointmentManager.domain.personalExceptions.TimetableIncorrectException;
import com.example.AppointmentManager.repositories.AppointmentRepository;
import com.example.AppointmentManager.repositories.DoctorRepository;
import com.example.AppointmentManager.repositories.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class NurseService {

    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void addNurse(NurseInputDto nurseInputDto) throws NurseExistsException, CollegiateNumberExistsException {
        if (nurseRepository.existsById(nurseInputDto.getDni()))
            throw new NurseExistsException("Nurse already exists.");
        if (doctorRepository.existsByCollegiateNumber(nurseInputDto.getCollegiateNumber()) | nurseRepository.existsByCollegiateNumber(nurseInputDto.getCollegiateNumber()))
            throw new CollegiateNumberExistsException("Collegiate number already exists.");
        nurseRepository.save(nurseInputDto.toDomain());
    }

    public void setTimetable(String dni, String start, String end) throws TimetableIncorrectException, NurseNotFoundException {
        Optional<Nurse> nurse = nurseRepository.findById(dni);
        if (nurse.isEmpty()) throw new NurseNotFoundException("Nurse doesn't exists.");
        nurse.get().setTimetable(start, end);
        nurseRepository.save(nurse.get());
    }

    public TreeMap<LocalDate, ArrayList<LocalTime>> getAvailableSpace(String dni) throws NurseNotFoundException {
        LocalDate today = LocalDate.now();
        if (!nurseRepository.existsById(dni))
            throw new NurseNotFoundException("Nurse doesn't exists.");
        Nurse nurse = nurseRepository.findByDni(dni);
        ArrayList<LocalDate> daysWindows = nurse.getDaysWindows(today);
        ArrayList<LocalTime> hoursAvailable = nurse.getHoursAvailable();
        TreeMap<LocalDate, ArrayList<LocalTime>> availableSpace = new TreeMap<>();
        for (LocalDate day : daysWindows) {
            ArrayList<LocalTime> copyHoursAvailable = new ArrayList<>(hoursAvailable);
            List<Appointment> appointments = appointmentRepository.findBySanitaryDniAndDay(dni, day);
            if (appointmentRepository.existsBySanitaryDniAndDay(nurse.getDni(), day)) {
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
