-- Crear la base de datos 'studywithme'
CREATE DATABASE IF NOT EXISTS studywithme;

-- Seleccionar la base de datos
USE studywithme;


-- Crear la tabla para Administradores
CREATE TABLE IF NOT EXISTS administradores
(
    nif_admin      VARCHAR(15) PRIMARY KEY,
    nombre         VARCHAR(50)         NOT NULL,
    apellidos      VARCHAR(50)         NOT NULL,
    email_admin    VARCHAR(100) UNIQUE NOT NULL,
    password_admin VARCHAR(100)        NOT NULL
);

-- Crear la tabla para Profesores
CREATE TABLE IF NOT EXISTS profesores
(
    nif_profesor      VARCHAR(15) PRIMARY KEY,
    nombre            VARCHAR(50)         NOT NULL,
    apellidos         VARCHAR(50)         NOT NULL,
    email_profesor    VARCHAR(100) UNIQUE NOT NULL,
    password_profesor VARCHAR(100)        NOT NULL,
    clases_profesor   VARCHAR(255) -- Puedes ajustar la longitud según tus necesidades
);

-- Crear la tabla para Alumnos
CREATE TABLE IF NOT EXISTS alumnos
(
    nif_alumno       VARCHAR(15) PRIMARY KEY,
    nombre_alumno    VARCHAR(50)         NOT NULL,
    apellidos_alumno VARCHAR(50)         NOT NULL,
    email_alumno     VARCHAR(100) UNIQUE NOT NULL,
    password_alumno  VARCHAR(100)        NOT NULL,
    curso            VARCHAR(50)         NOT NULL,
    PRIMARY KEY (nif_alumno, curso) -- La combinación de nif_alumno y curso es única
);

-- Crear la tabla para Padres
CREATE TABLE IF NOT EXISTS padres
(
    nif_padre       VARCHAR(15) PRIMARY KEY,
    nombre_padre    VARCHAR(50)         NOT NULL,
    apellidos_padre VARCHAR(50)         NOT NULL,
    email_padre     VARCHAR(100) UNIQUE NOT NULL,
    password_padre  VARCHAR(100)        NOT NULL
);

-- Crear la tabla para Relación entre Padres y Alumnos
CREATE TABLE IF NOT EXISTS padres_alumnos
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    padre_nif  VARCHAR(15) NOT NULL,
    alumno_nif VARCHAR(15) NOT NULL,
    FOREIGN KEY (padre_nif) REFERENCES padres (nif_padre),
    FOREIGN KEY (alumno_nif) REFERENCES alumnos (nif_alumno)
);

-- Crear la tabla para Clases/Asignaturas
CREATE TABLE IF NOT EXISTS clases
(
    id_clase     INT AUTO_INCREMENT PRIMARY KEY,
    asignatura   VARCHAR(100) NOT NULL,
    profesor_nif VARCHAR(15)  NOT NULL,
    FOREIGN KEY (profesor_nif) REFERENCES profesores (nif_profesor)
);

-- Crear la tabla para Alumnos en Clases
CREATE TABLE IF NOT EXISTS alumnos_clases
(
    id_alumno_clase INT AUTO_INCREMENT PRIMARY KEY,
    alumno_nif      VARCHAR(15) NOT NULL,
    clase_id        INT         NOT NULL,
    FOREIGN KEY (alumno_nif) REFERENCES alumnos (nif_alumno),
    FOREIGN KEY (clase_id) REFERENCES clases (id_clase)
);

-- Crear la tabla para Tareas/Examenes
CREATE TABLE IF NOT EXISTS tareas_examenes
(
    id_tarea_examen INT AUTO_INCREMENT PRIMARY KEY,
    clase_id        INT  NOT NULL,
    fecha           DATE NOT NULL,
    descripcion     TEXT,
    FOREIGN KEY (clase_id) REFERENCES clases (id_clase)
);