-- Crear la base de datos 'studywithme'
CREATE DATABASE IF NOT EXISTS studywithme;

-- Seleccionar la base de datos
USE studywithme;


-- Crear la tabla 'administradores'
CREATE TABLE IF NOT EXISTS administradores
(
    nif_administradores      varchar(9)  NOT NULL PRIMARY KEY,
    nombre_administradores   varchar(50) NOT NULL,
    edad_administradores     int         NOT NULL,
    email_administradores    varchar(50) NOT NULL,
    password_administradores varchar(50) NOT NULL,
    clases_administradores   varchar(50) NOT NULL,
    UNIQUE (email_administradores),
    UNIQUE (password_administradores)
) DEFAULT CHARSET = utf8mb4;


-- Crear la tabla 'profesores'
CREATE TABLE IF NOT EXISTS profesores
(
    nif_profesores    varchar(9)  NOT NULL PRIMARY KEY,
    nombre_profesores varchar(50) NOT NULL,
    edad_profesores   int         NOT NULL,
    email_profesores  varchar(50) NOT NULL,
    password_profesores varchar(50) NOT NULL,
    clases_profesores   varchar(50) NOT NULL,
    UNIQUE (email_profesores),
    UNIQUE (password_profesores)
) DEFAULT CHARSET = utf8mb4;


-- Crear la tabla 'alumnos'
CREATE TABLE IF NOT EXISTS alumnos
(
    nif_alumno    varchar(9)  NOT NULL PRIMARY KEY,
    nombre_alumno varchar(50) NOT NULL,
    edad_alumno   int         NOT NULL,
    email_alumno  varchar(50) NOT NULL,
    password_alumno varchar(50) NOT NULL,
    clases_alumno   varchar(50) NOT NULL,
    UNIQUE (email_alumno),
    UNIQUE (password_alumno)
) DEFAULT CHARSET = utf8mb4;


-- Crear la tabla 'padres'
CREATE TABLE IF NOT EXISTS padres
(
    nif_padres    varchar(9)  NOT NULL PRIMARY KEY,
    nombre_padres varchar(50) NOT NULL,
    edad_padres   int         NOT NULL,
    email_padres  varchar(50) NOT NULL,
    password_padres varchar(50) NOT NULL,
    gestion_hijos_padres   varchar(50) NOT NULL,
    UNIQUE (email_padres),
    UNIQUE (password_padres)
) DEFAULT CHARSET = utf8mb4;


