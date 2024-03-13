-- Crear la base de datos 'studywithme'
CREATE DATABASE IF NOT EXISTS studywithme;

-- Seleccionar la base de datos
USE studywithme;

-- Eliminar tablas si existen
DROP TABLE IF EXISTS tareas;
DROP TABLE IF EXISTS asignaturas;
DROP TABLE IF EXISTS clases;
DROP TABLE IF EXISTS alumnos;
DROP TABLE IF EXISTS profesores;
DROP TABLE IF EXISTS administradores;

-- Crear la tabla para Administradores
CREATE TABLE IF NOT EXISTS administradores (
    nif_admin CHAR(9) NOT NULL UNIQUE PRIMARY KEY,
    nombre_admin VARCHAR(50) NOT NULL,
    email_admin VARCHAR(100) UNIQUE NOT NULL,
    password_admin VARCHAR(100) NOT NULL
);

-- Crear la tabla para Profesores
CREATE TABLE IF NOT EXISTS profesores (
    nif_profesor CHAR(9) NOT NULL UNIQUE PRIMARY KEY,
    nombre_profesor VARCHAR(50) NOT NULL,
    email_profesor VARCHAR(100) UNIQUE NOT NULL,
    password_profesor VARCHAR(100) NOT NULL
);

-- Crear la tabla para Alumnos
CREATE TABLE IF NOT EXISTS alumnos (
    nif_alumno CHAR(9) NOT NULL UNIQUE PRIMARY KEY,
    nombre_alumno VARCHAR(50) NOT NULL,
    email_alumno VARCHAR(100) UNIQUE NOT NULL,
    password_alumno VARCHAR(100) NOT NULL,
    nombre_padre_alumno VARCHAR(100),
	nombre_madre_alumno VARCHAR(100)
);

-- Crear la tabla para clases
CREATE TABLE IF NOT EXISTS clases (
    id_clase INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
    nombre_clase VARCHAR(100) NOT NULL
);

-- Crear la tabla para asignaturas
CREATE TABLE IF NOT EXISTS asignaturas (
    id_asignatura INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
    nombre_asignatura VARCHAR(100) NOT NULL,
    id_clase_asignatura INT NOT NULL,
    nif_profesor CHAR(9) NOT NULL,
    FOREIGN KEY (id_clase_asignatura) REFERENCES clases(id_clase),
    FOREIGN KEY (nif_profesor) REFERENCES profesores(nif_profesor)
);

-- Crear la tabla para tareas
CREATE TABLE IF NOT EXISTS tareas (
    id_tarea INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
    tipo_tarea VARCHAR(50) NOT NULL,
    titulo_tarea VARCHAR(100) NOT NULL,
    descripcion_tarea VARCHAR(100) NOT NULL,
    calificacion_tarea DECIMAL,
    nif_profesor_tarea CHAR(9) NOT NULL,
    nif_alumno_calificacion CHAR(9) NOT NULL,
    id_asignatura INT NOT NULL,
    FOREIGN KEY (nif_profesor_tarea) REFERENCES profesores(nif_profesor),
    FOREIGN KEY (nif_alumno_calificacion) REFERENCES alumnos(nif_alumno),
    FOREIGN KEY (id_asignatura) REFERENCES asignaturas(id_asignatura)
);

-- Datos para la tabla administradores
INSERT INTO administradores (nif_admin, nombre_admin, email_admin, password_admin) VALUES
('76587609C', 'María García López', 'maria.garcia@a.stdywithme.com', '{noop}password123'),
('35891234X', 'Juan Martínez Sánchez', 'juan.martinez@a.stdywithme.com', '{noop}admin456pass'),
('44781265A', 'Laura Pérez Martín', 'laura.perez@a.stdywithme.com', '{noop}admin789pass');

-- Datos para la tabla profesores
INSERT INTO profesores (nif_profesor, nombre_profesor, email_profesor, password_profesor) VALUES
('87651234A', 'Javier Ruiz García', 'javier.ruiz@p.studywithme.com', '{noop}prof123pass'),
('76451235B', 'María Sánchez López', 'maria.sanchez@p.studywithme.com', '{noop}profpass456'),
('65482367C', 'Daniel Martínez Gómez', 'daniel.martinez@p.studywithme.com', '{noop}passwordprof789'),
('34578901D', 'Carmen García Martín', 'carmen.garcia@p.studywithme.com', '{noop}professorpass123'),
('56789012E', 'Sergio Pérez Rodríguez', 'sergio.perez@p.studywithme.com', '{noop}profpass789'),
('67890123F', 'Marta López Gómez', 'marta.lopez@p.studywithme.com', '{noop}passwordprof123'),
('78901234G', 'Alejandro Ruiz Sánchez', 'alejandro.ruiz@p.studywithme.com', '{noop}professorpass456'),
('89012345H', 'Laura Martínez Pérez', 'laura.martinez@p.studywithme.com', '{noop}profpass123'),
('90123456I', 'Diego Sánchez Gómez', 'diego.sanchez@p.studywithme.com', '{noop}passwordprof456'),
('12345678J', 'Elena González Rodríguez', 'elena.gonzalez@p.studywithme.com', '{noop}professor789pass');

-- Datos actualizados para la tabla alumnos
INSERT INTO alumnos (nif_alumno, nombre_alumno, email_alumno, password_alumno, nombre_padre_alumno, nombre_madre_alumno) VALUES
('45678901K', 'Sara García López', 'sara.garcia@studywithme.com', '{noop}studentpass123', 'Antonio García', 'María López'),
('12345678L', 'Carlos Martínez Sánchez', 'carlos.martinez@studywithme.com', '{noop}alum123pass', 'José Martínez', 'Ana Sánchez'),
('98765432M', 'Laura Pérez Martín', 'laura.perez@studywithme.com', '{noop}student456pass', 'Francisco Pérez', 'Sofía Martín'),
('34567890N', 'Sergio Rodríguez Gómez', 'sergio.rodriguez@studywithme.com', '{noop}alumpass789', 'Javier Rodríguez', 'Elena Gómez'),
('54321678O', 'Marta López García', 'marta.lopez.garcia@studywithme.com', '{noop}studentpass456', 'David López', 'Laura García'),
('89012345P', 'Lucía Sánchez Martín', 'lucia.sanchez.martinez@studywithme.com', '{noop}alum789pass', 'Juan Sánchez', 'Ana Martín'),
('67890123Q', 'David González Gómez', 'david.gonzalez.gomez@studywithme.com', '{noop}studentpass789', 'Manuel González', 'María Gómez'),
('78901234R', 'Ana Martínez Rodríguez', 'ana.martinez.rodriguez@studywithme.com', '{noop}alum666pass', 'Carlos Martínez', 'Sara Rodríguez'),
('90123456S', 'Pablo Ruiz García', 'pablo.ruiz.garcia@studywithme.com', '{noop}studentpass777', 'Francisco Ruiz', 'Elena García'),
('23456789T', 'Elena Fernández López', 'elena.fernandez.lopez@studywithme.com', '{noop}alum888pass', 'Luis Fernández', 'Lucía López'),
('45678901U', 'Laura García Martínez', 'laura.garcia@studywithme.com', '{noop}studentpass123', 'Antonio García', 'María Martínez'),
('12345678V', 'Javier Martínez Rodríguez', 'javier.martinez@studywithme.com', '{noop}alum123pass', 'José Martínez', 'Ana Rodríguez'),
('98765432W', 'Sofía Pérez López', 'sofia.perez@studywithme.com', '{noop}student456pass', 'Francisco Pérez', 'Sara López'),
('34567890X', 'Diego Rodríguez Sánchez', 'diego.rodriguez@studywithme.com', '{noop}alumpass789', 'Javier Rodríguez', 'María Sánchez'),
('54321678Y', 'Marta López Martínez', 'marta.lopez@studywithme.com', '{noop}studentpass456', 'David López', 'Lucía Martínez'),
('89012345Z', 'Lucía Sánchez Rodríguez', 'lucia.sanchez@studywithme.com', '{noop}alum789pass', 'Juan Sánchez', 'Sofía Rodríguez'),
('67890123A', 'David González García', 'david.gonzalez@studywithme.com', '{noop}studentpass789', 'Manuel González', 'Laura García'),
('78901234B', 'Ana Martínez Gómez', 'ana.martinez@studywithme.com', '{noop}alum666pass', 'Carlos Martínez', 'Marta Gómez'),
('90123456C', 'Pablo Ruiz Rodríguez', 'pablo.ruiz@studywithme.com', '{noop}studentpass777', 'Francisco Ruiz', 'Elena Rodríguez'),
('23456789D', 'Elena Fernández Martínez', 'elena.fernandez@studywithme.com', '{noop}alum888pass', 'Luis Fernández', 'Sara Martínez'),
('65432123E', 'María García Sánchez', 'maria.garcia@studywithme.com', '{noop}studentpass123', 'Antonio García', 'Laura Sánchez'),
('76543212F', 'José Martínez Pérez', 'jose.martinez@studywithme.com', '{noop}alum123pass', 'José Martínez', 'María Pérez'),
('87654321G', 'Daniel Pérez Rodríguez', 'daniel.perez@studywithme.com', '{noop}student456pass', 'Francisco Pérez', 'Ana Rodríguez'),
('98765432H', 'Sara Rodríguez López', 'sara.rodriguez@studywithme.com', '{noop}alumpass789', 'Javier Rodríguez', 'Sara López'),
('12345678I', 'Laura López Martínez', 'laura.lopez@studywithme.com', '{noop}studentpass456', 'David López', 'Laura Martínez'),
('23456789J', 'Marcos Sánchez Rodríguez', 'marcos.sanchez@studywithme.com', '{noop}alum789pass', 'Juan Sánchez', 'Sofía Rodríguez'),
('34567890K', 'Sofía González García', 'sofia.gonzalez@studywithme.com', '{noop}studentpass789', 'Manuel González', 'María García'),
('45678901L', 'Diego Martínez Gómez', 'diego.martinez@studywithme.com', '{noop}alum666pass', 'Carlos Martínez', 'Laura Gómez');

-- Insertar asignaturas para 1º de ESO
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Matemáticas', id_clase, '87651234A' FROM clases WHERE nombre_clase = '1 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Lengua', id_clase, '76451235B' FROM clases WHERE nombre_clase = '1 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Ciencias Naturales', id_clase, '65482367C' FROM clases WHERE nombre_clase = '1 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Historia', id_clase, '34578901D' FROM clases WHERE nombre_clase = '1 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Inglés', id_clase, '56789012E' FROM clases WHERE nombre_clase = '1 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Educación Física', id_clase, '67890123F' FROM clases WHERE nombre_clase = '1 de ESO';

-- Insertar asignaturas para 2º de ESO
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Matemáticas', id_clase, '87651234A' FROM clases WHERE nombre_clase = '2 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Lengua', id_clase, '76451235B' FROM clases WHERE nombre_clase = '2 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Física y Química', id_clase, '65482367C' FROM clases WHERE nombre_clase = '2 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Geografía e Historia', id_clase, '34578901D' FROM clases WHERE nombre_clase = '2 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Inglés', id_clase, '56789012E' FROM clases WHERE nombre_clase = '2 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Educación Física', id_clase, '67890123F' FROM clases WHERE nombre_clase = '2 de ESO';

-- Insertar asignaturas para 3º de ESO
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Matemáticas', id_clase, '87651234A' FROM clases WHERE nombre_clase = '3 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Lengua', id_clase, '76451235B' FROM clases WHERE nombre_clase = '3 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Biología y Geología', id_clase, '65482367C' FROM clases WHERE nombre_clase = '3 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Geografía e Historia', id_clase, '34578901D' FROM clases WHERE nombre_clase = '3 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Inglés', id_clase, '56789012E' FROM clases WHERE nombre_clase = '3 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Educación Física', id_clase, '67890123F' FROM clases WHERE nombre_clase = '3 de ESO';

-- Insertar asignaturas para 4º de ESO
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Matemáticas', id_clase, '87651234A' FROM clases WHERE nombre_clase = '4 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Lengua', id_clase, '76451235B' FROM clases WHERE nombre_clase = '4 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Física y Química', id_clase, '65482367C' FROM clases WHERE nombre_clase = '4 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Historia', id_clase, '34578901D' FROM clases WHERE nombre_clase = '4 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Inglés', id_clase, '56789012E' FROM clases WHERE nombre_clase = '4 de ESO';
INSERT INTO asignaturas (nombre_asignatura, id_clase_asignatura, nif_profesor) 
SELECT 'Educación Física', id_clase, '67890123F' FROM clases WHERE nombre_clase = '4 de ESO';