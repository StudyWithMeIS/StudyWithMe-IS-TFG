-- Borrar la base de datos si existe
DROP DATABASE IF EXISTS studywithme;

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
                                               id_admin INT NOT NULL UNIQUE PRIMARY KEY auto_increment,
                                               nif_admin CHAR(9) NOT NULL UNIQUE,
    nombre_admin VARCHAR(50) NOT NULL,
    email_admin VARCHAR(100) UNIQUE NOT NULL,
    password_admin VARCHAR(100) NOT NULL
    );

-- Crear la tabla para Profesores
CREATE TABLE IF NOT EXISTS profesores (
                                          id_profesor INT NOT NULL UNIQUE PRIMARY KEY auto_increment,
                                          nif_profesor CHAR(9) NOT NULL UNIQUE,
    nombre_profesor VARCHAR(50) NOT NULL,
    email_profesor VARCHAR(100) UNIQUE NOT NULL,
    password_profesor VARCHAR(100) NOT NULL
    );

-- Crear la tabla para Alumnos
CREATE TABLE IF NOT EXISTS alumnos (
                                       id_alumno INT NOT NULL UNIQUE PRIMARY KEY auto_increment,
                                       nif_alumno CHAR(9) NOT NULL UNIQUE,
    nombre_alumno VARCHAR(50) NOT NULL,
    email_alumno VARCHAR(100) UNIQUE NOT NULL,
    password_alumno VARCHAR(100) NOT NULL,
    nombre_padre_alumno VARCHAR(100),
    nombre_madre_alumno VARCHAR(100)
    );

-- Crear tabla curso
CREATE TABLE IF NOT EXISTS cursos (
                                      id_curso INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                                      nombre_curso VARCHAR(100) NOT NULL
    );

-- Crear la tabla para asignaturas
CREATE TABLE IF NOT EXISTS asignaturas (
                                           id_asignatura INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                                           nombre_asignatura VARCHAR(100) NOT NULL,
    id_curso_asignatura INT NOT NULL,
    id_profesor INT NOT NULL,
    FOREIGN KEY (id_curso_asignatura) REFERENCES cursos(id_curso),
    FOREIGN KEY (id_profesor) REFERENCES profesores(id_profesor)
    );


-- Crear la tabla para tareas
CREATE TABLE IF NOT EXISTS tareas (
    id_tarea INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
    tipo_tarea VARCHAR(50) NOT NULL,
    titulo_tarea VARCHAR(100) NOT NULL,
    descripcion_tarea VARCHAR(100) NOT NULL,
    calificacion_tarea DECIMAL(5, 3),
    id_profesor_tarea INT NOT NULL,
    id_alumno_tarea INT NOT NULL,
    id_asignatura_tarea INT NOT NULL,
    FOREIGN KEY (id_profesor_tarea) REFERENCES profesores(id_profesor),
    FOREIGN KEY (id_alumno_tarea) REFERENCES alumnos(id_alumno),
    FOREIGN KEY (id_asignatura_tarea) REFERENCES asignaturas(id_asignatura)
    );

-- Datos para la tabla administradores
INSERT INTO administradores (nif_admin, nombre_admin, email_admin, password_admin) VALUES
                                                                                       ('76587609C', 'María García López', 'maria.garcia@a.stdywithme.com', '{noop}password123'),
                                                                                       ('35891234X', 'Juan Martínez Sánchez', 'juan.martinez@a.stdywithme.com', '{noop}admin456pass'),
                                                                                       ('44781265A', 'Laura Pérez Martín', 'laura.perez@a.stdywithme.com', '{noop}admin789pass');

-- Datos para la tabla profesores
INSERT INTO profesores (nif_profesor, nombre_profesor, email_profesor, password_profesor) VALUES
                                                                                              -- MATEMATICAS
                                                                                              ('87651234A', 'Javier Ruiz García', 'javier.ruiz@p.studywithme.com', '{noop}prof123pass'),
                                                                                              -- LENGUA
                                                                                              ('76451235B', 'María Sánchez López', 'maria.sanchez@p.studywithme.com', '{noop}profpass456'),
                                                                                              -- CIENCIAS NATURALES
                                                                                              ('65482367C', 'Daniel Martínez Gómez', 'daniel.martinez@p.studywithme.com', '{noop}passwordprof789'),
                                                                                              -- HISTORIA
                                                                                              ('34578901D', 'Carmen García Martín', 'carmen.garcia@p.studywithme.com', '{noop}professorpass123'),
                                                                                              -- INGLES
                                                                                              ('56789012E', 'Sergio Pérez Rodríguez', 'sergio.perez@p.studywithme.com', '{noop}profpass789'),
                                                                                              -- EDUCACION FISICA
                                                                                              ('67890123F', 'Marta López Gómez', 'marta.lopez@p.studywithme.com', '{noop}passwordprof123');


-- Datos actualizados para la tabla alumnos
INSERT INTO alumnos (nif_alumno, nombre_alumno, email_alumno, password_alumno, nombre_padre_alumno, nombre_madre_alumno) VALUES
                                                                                                                             -- 1 DE ESO
                                                                                                                             ('45678901K', 'Sara García López', 'sara.garcia@studywithme.com', '{noop}studentpass123', 'Antonio García', 'María López'),
                                                                                                                             ('12345678L', 'Carlos Martínez Sánchez', 'carlos.martinez@studywithme.com', '{noop}alum123pass', 'José Martínez', 'Ana Sánchez'),
                                                                                                                             ('98765432M', 'Laura Pérez Martín', 'laura.perez@studywithme.com', '{noop}student456pass', 'Francisco Pérez', 'Sofía Martín'),
                                                                                                                             ('34567890N', 'Sergio Rodríguez Gómez', 'sergio.rodriguez@studywithme.com', '{noop}alumpass789', 'Javier Rodríguez', 'Elena Gómez'),
                                                                                                                             ('54321678O', 'Marta López García', 'marta.lopez.garcia@studywithme.com', '{noop}studentpass456', 'David López', 'Laura García'),
                                                                                                                             -- 2 DE ESO
                                                                                                                             ('89012345P', 'Lucía Sánchez Martín', 'lucia.sanchez.martinez@studywithme.com', '{noop}alum789pass', 'Juan Sánchez', 'Ana Martín'),
                                                                                                                             ('67890123Q', 'David González Gómez', 'david.gonzalez.gomez@studywithme.com', '{noop}studentpass789', 'Manuel González', 'María Gómez'),
                                                                                                                             ('78901234R', 'Ana Martínez Rodríguez', 'ana.martinez.rodriguez@studywithme.com', '{noop}alum666pass', 'Carlos Martínez', 'Sara Rodríguez'),
                                                                                                                             ('90123456S', 'Pablo Ruiz García', 'pablo.ruiz.garcia@studywithme.com', '{noop}studentpass777', 'Francisco Ruiz', 'Elena García'),
                                                                                                                             ('23456789T', 'Elena Fernández López', 'elena.fernandez.lopez@studywithme.com', '{noop}alum888pass', 'Luis Fernández', 'Lucía López'),
                                                                                                                             -- 3 DE ESO
                                                                                                                             ('45678901U', 'Laura García Martínez', 'laura.garcia@studywithme.com', '{noop}studentpass123', 'Antonio García', 'María Martínez'),
                                                                                                                             ('12345678V', 'Javier Martínez Rodríguez', 'javier.martinez@studywithme.com', '{noop}alum123pass', 'José Martínez', 'Ana Rodríguez'),
                                                                                                                             ('98765432W', 'Sofía Pérez López', 'sofia.perez@studywithme.com', '{noop}student456pass', 'Francisco Pérez', 'Sara López'),
                                                                                                                             ('34567890X', 'Diego Rodríguez Sánchez', 'diego.rodriguez@studywithme.com', '{noop}alumpass789', 'Javier Rodríguez', 'María Sánchez'),
                                                                                                                             ('54321678Y', 'Marta López Martínez', 'marta.lopez@studywithme.com', '{noop}studentpass456', 'David López', 'Lucía Martínez'),
                                                                                                                             -- 4 DE ESO
                                                                                                                             ('89012345Z', 'Lucía Sánchez Rodríguez', 'lucia.sanchez@studywithme.com', '{noop}alum789pass', 'Juan Sánchez', 'Sofía Rodríguez'),
                                                                                                                             ('67890123A', 'David González García', 'david.gonzalez@studywithme.com', '{noop}studentpass789', 'Manuel González', 'Laura García'),
                                                                                                                             ('78901234B', 'Ana Martínez Gómez', 'ana.martinez@studywithme.com', '{noop}alum666pass', 'Carlos Martínez', 'Marta Gómez'),
                                                                                                                             ('90123456C', 'Pablo Ruiz Rodríguez', 'pablo.ruiz@studywithme.com', '{noop}studentpass777', 'Francisco Ruiz', 'Elena Rodríguez'),
                                                                                                                             ('23456789D', 'Elena Fernández Martínez', 'elena.fernandez@studywithme.com', '{noop}alum888pass', 'Luis Fernández', 'Sara Martínez');


-- Datos curso
INSERT INTO cursos (nombre_curso) VALUES ('1 de ESO'), ('2 de ESO'), ('3 de ESO'), ('4 de ESO');

-- Insertar asignaturas para 1º de ESO
INSERT INTO ASIGNATURAS (nombre_asignatura, id_curso_asignatura, id_profesor) VALUES
                                                                                  -- 1 DE ESO
                                                                                  ('Matematicas', 1, 1),
                                                                                  ('Lengua', 1, 2),
                                                                                  ('Ciencias Naturales', 1, 3),
                                                                                  ('Historia', 1, 4),
                                                                                  ('Ingles', 1, 5),
                                                                                  ('Educacion Fisica', 1, 6),
                                                                                  -- 2 DE ESO
                                                                                  ('Matematicas', 2, 1),
                                                                                  ('Lengua', 2, 2),
                                                                                  ('Ciencias Naturales', 2, 3),
                                                                                  ('Historia', 2, 4),
                                                                                  ('Ingles', 2, 5),
                                                                                  ('Educacion Fisica', 2, 6),
                                                                                  -- 3 DE ESO
                                                                                  ('Matematicas', 3, 1),
                                                                                  ('Lengua', 3, 2),
                                                                                  ('Ciencias Naturales', 3, 3),
                                                                                  ('Historia', 3, 4),
                                                                                  ('Ingles', 3, 5),
                                                                                  ('Educacion Fisica', 3, 6),
                                                                                  -- 4 DE ESO
                                                                                  ('Matematicas', 4, 1),
                                                                                  ('Lengua', 4, 2),
                                                                                  ('Ciencias Naturales', 4, 3),
                                                                                  ('Historia', 4, 4),
                                                                                  ('Ingles', 4, 5),
                                                                                  ('Educacion Fisica', 4, 6);

SELECT * FROM ASIGNATURAS;



CREATE TABLE IF NOT EXISTS cursos_asignaturas (
                                                  id_curso INT NOT NULL,
                                                  id_asignatura INT NOT NULL,
                                                  PRIMARY KEY (id_curso, id_asignatura),
    FOREIGN KEY (id_curso) REFERENCES cursos(id_curso),
    FOREIGN KEY (id_asignatura) REFERENCES asignaturas(id_asignatura)
    );

-- ASIGNATURAS
INSERT INTO cursos_asignaturas (id_curso, id_asignatura)
SELECT c.id_curso, a.id_asignatura
FROM cursos c
         CROSS JOIN asignaturas a;

-- TAREAS
INSERT INTO TAREAS (tipo_tarea, titulo_tarea, descripcion_tarea, calificacion_tarea,
                    id_profesor_tarea, id_alumno_tarea, id_asignatura_tarea) VALUES
-- 1 DE ESO
('Tarea', 'Tarea 1', 'Primera tarea', 1.000, 1, 1, 1),
('Tarea', 'Tarea 2', 'Segunda tarea', 2.000, 1, 2, 1),
('Tarea', 'Tarea 3', 'Tercera tarea', 3.000, 1, 3, 1),
('Tarea', 'Tarea 4', 'Cuarta tarea', 4.000, 1, 4, 1),
('Tarea', 'Tarea 5', 'Quinta tarea', 5.000, 1, 5, 1),
-- 2 DE ESO
('Tarea', 'Tarea 1', 'Primera tarea', 1.000, 1, 6, 2),
('Tarea', 'Tarea 2', 'Segunda tarea', 2.000, 1, 7, 2),
('Tarea', 'Tarea 3', 'Tercera tarea', 3.000, 1, 8, 2),
('Tarea', 'Tarea 4', 'Cuarta tarea', 4.000, 1, 9, 2),
('Tarea', 'Tarea 5', 'Quinta tarea', 5.000, 1, 10, 2),
-- 3 DE ESO
('Tarea', 'Tarea 1', 'Primera tarea', 1.000, 1, 11, 3),
('Tarea', 'Tarea 2', 'Segunda tarea', 2.000, 1, 12, 3),
('Tarea', 'Tarea 3', 'Tercera tarea', 3.000, 1, 13, 3),
('Tarea', 'Tarea 4', 'Cuarta tarea', 4.000, 1, 14, 3),
('Tarea', 'Tarea 5', 'Quinta tarea', 5.000, 1, 15, 3),
-- 4 DE ESO
('Tarea', 'Tarea 1', 'Primera tarea', 1.000, 1, 16, 4),
('Tarea', 'Tarea 2', 'Segunda tarea', 2.000, 1, 17, 4),
('Tarea', 'Tarea 3', 'Tercera tarea', 3.000, 1, 18, 4),
('Tarea', 'Tarea 4', 'Cuarta tarea', 4.000, 1, 19, 4),
('Tarea', 'Tarea 5', 'Quinta tarea', 5.000, 1, 20, 4);




SELECT * FROM administradores;
SELECT * FROM profesores;
SELECT * FROM alumnos;
SELECT * FROM cursos;
SELECT * FROM asignaturas;
SELECT * FROM cursos_asignaturas;
SELECT * FROM tareas;