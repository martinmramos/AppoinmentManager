package com.example.AppointmentManager.services;

import com.example.AppointmentManager.controllers.DTO.AppointmentInputDto;
import com.example.AppointmentManager.controllers.DTO.AppointmentOutputDto;
import com.example.AppointmentManager.domain.Appointment;
import com.example.AppointmentManager.domain.Nurse;
import com.example.AppointmentManager.domain.personalExceptions.*;
import com.example.AppointmentManager.repositories.AppointmentRepository;
import com.example.AppointmentManager.repositories.DoctorRepository;
import com.example.AppointmentManager.repositories.NurseRepository;
import com.example.AppointmentManager.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    public boolean existsSanitary(String dni) {
        if (nurseRepository.existsById(dni) | doctorRepository.existsById(dni))
            return true;
        return false;
    }

    public boolean duplicateAppointment(AppointmentInputDto appointmentInputDto) {
        List<Appointment> listPatientAppointments = appointmentRepository.findByPatientDni(appointmentInputDto.getPatientDni());
        if (!listPatientAppointments.isEmpty()) {
            for (Appointment patientAppointment : listPatientAppointments) {
                if (patientAppointment.getDay().equals(appointmentInputDto.getDay()) & patientAppointment.getHour().equals(appointmentInputDto.getHour())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean busySanitary(AppointmentInputDto appointmentInputDto) {
        List<Appointment> listSanitaryAppointments = appointmentRepository.findBySanitaryDni(appointmentInputDto.getSanitaryDni());
        if (!listSanitaryAppointments.isEmpty()) {
            for (Appointment sanitaryAppointment : listSanitaryAppointments) {
                if (sanitaryAppointment.getDay().equals(appointmentInputDto.getDay()) & sanitaryAppointment.getHour().equals(appointmentInputDto.getHour())) {
                    return true;
                }
            }
        }
        return false;
    }

    public Optional<? extends Nurse> createSanitaryClass(String dni) {
        if (nurseRepository.existsById(dni)) {
            return nurseRepository.findById(dni);
        } else {
            return doctorRepository.findById(dni);
        }
    }

    public void addAppointment(AppointmentInputDto appointmentInputDto) throws SanitaryNotFoundException, PatientNotFoundException, PatientBusyException,
            SanitaryBusyException, DayIncorrectException, HourIncorrectException {
        if (!existsSanitary(appointmentInputDto.getSanitaryDni()))
            throw new SanitaryNotFoundException("Sanitary doesn't exists.");
        Optional<? extends Nurse> sanitary = createSanitaryClass(appointmentInputDto.getSanitaryDni());
        if (!patientRepository.existsById(appointmentInputDto.getPatientDni()))
            throw new PatientNotFoundException("Patient doesn't exists.");
        if (duplicateAppointment(appointmentInputDto))
            throw new PatientBusyException("The patient already has another appointment with the same data.");
        if (busySanitary(appointmentInputDto))
            throw new SanitaryBusyException("The sanitary already has another appointment with the same data.");
        if (!sanitary.get().getDaysWindows(LocalDate.now()).contains(appointmentInputDto.getDay()))
            throw new DayIncorrectException("Wrong day.");
        if (sanitary.get().getStartTime().isAfter(appointmentInputDto.getHour()) | sanitary.get().getLastHourAvailable().isBefore(appointmentInputDto.getHour()))
            throw new HourIncorrectException("Wrong hour.");
        appointmentRepository.save(appointmentInputDto.toDomain());
    }

    public TreeMap<LocalDate, ArrayList<AppointmentOutputDto>> getAppointmentsBySanitary(String dni) throws SanitaryNotFoundException {
        if (!existsSanitary(dni))
            throw new SanitaryNotFoundException("Sanitary doesn't exists.");
        Optional<? extends Nurse> sanitary = createSanitaryClass(dni);
        LocalDate today = LocalDate.now();
        ArrayList<LocalDate> dayWindows = new ArrayList<>(sanitary.get().getDaysWindows(today));
        LocalDate firstDay = dayWindows.get(0);
        LocalDate lastDay = dayWindows.get(dayWindows.size() - 1);
        List<Appointment> appointmentsList = appointmentRepository.findBySanitaryDniAndDayBetween(dni, firstDay, lastDay);
        TreeMap<LocalDate, ArrayList<AppointmentOutputDto>> appointmentsSanitary = new TreeMap<>();
        for (LocalDate day : dayWindows) {
            ArrayList<AppointmentOutputDto> appointmentOutputDtoList = new ArrayList<>();
            for (Appointment appointment : appointmentsList) {
                if (appointment.getDay().equals(day)) {
                    String name = patientRepository.findById(appointment.getPatientDni()).get().getName();
                    String hour = appointment.getHour().toString();
                    AppointmentOutputDto appointmentOutputDto = new AppointmentOutputDto(name, hour);
                    appointmentOutputDtoList.add(appointmentOutputDto);
                }
            }
            Collections.sort(appointmentOutputDtoList);
            appointmentsSanitary.put(day, appointmentOutputDtoList);
        }
        return appointmentsSanitary;
    }

    public ArrayList<AppointmentOutputDto> getAppointmentsByPatientAndDay(String dni, String day) throws PatientNotFoundException {
        if (!patientRepository.existsById(dni))
            throw new PatientNotFoundException("Patient doesn't exists.");
        List<Appointment> appointmentsList = appointmentRepository.findByPatientDniAndDay(dni, LocalDate.parse(day));
        ArrayList<AppointmentOutputDto> appointmentsOutputList = new ArrayList<>();
        for (Appointment appointment : appointmentsList) {
            Optional<? extends Nurse> sanitary = createSanitaryClass(appointment.getSanitaryDni());
            String name = sanitary.get().getName();
            AppointmentOutputDto appointmentOutputDto = new AppointmentOutputDto(name, appointment.getHour().toString());
            appointmentsOutputList.add(appointmentOutputDto);
        }
        Collections.sort(appointmentsOutputList);
        return appointmentsOutputList;
    }
}
