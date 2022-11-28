create table appointments (
    patient_dni varchar(9) NOT NULL,
    sanitary_dni varchar(9) NOT NULL,
    day date NOT NULL,
    hour time NOT NULL,
    CONSTRAINT PK_appointment PRIMARY KEY (patient_dni, sanitary_dni, day, hour)
);