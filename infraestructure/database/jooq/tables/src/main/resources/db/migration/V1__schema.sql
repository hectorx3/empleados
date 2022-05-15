DROP TABLE IF EXISTS employess_worked;
DROP TABLE IF EXISTS employess;
DROP TABLE IF EXISTS jobs;
DROP TABLE IF EXISTS genders;

create table jobs(
     id serial primary key,
     name text not null,
     salary decimal(9,2) not null
);

INSERT INTO public.jobs
("name", salary)
VALUES('Programador', 123.50);

INSERT INTO public.jobs
("name", salary)
VALUES('Taxista', 50.60);

INSERT INTO public.jobs
("name", salary)
VALUES('Mesero', 60.99);

INSERT INTO public.jobs
("name", salary)
VALUES('Profesor', 99.0);

create table genders(
    id serial primary key,
    name text not null
);

INSERT INTO public.genders
("name")
VALUES('M');

INSERT INTO public.genders
("name")
VALUES('F');

CREATE TABLE employess(
    id serial primary key,
    gender_id int not null,
    job_id int NOT NULL,
    name TEXT NOT NULL,
    last_name text not null,
    birthdate date,
    constraint fk_emp_job foreign key(job_id) references jobs(id),
    constraint fk_emp_gend foreign key(gender_id) references genders(id)
);

CREATE TABLE employess_worked(
     id serial primary key,
     employee_id int not null,
     worked_hours int not null,
     worked_date date not null,
     constraint fk_worker_emp foreign key(employee_id) references employess(id)
);