create table doctors (
    name varchar(25) NOT NULL,
    dni varchar(9) PRIMARY KEY,
    collegiate_number int NOT NULL,
    years_of_experience int NOT NULL,
    start_time time NULL,
    end_time time NULL
);