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

-- Crear la tabla para clases
CREATE TABLE IF NOT EXISTS clases (
                                      id_clase INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY auto_increment,
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
    calificacion_tarea DECIMAL(5, 3),
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


-- Insertar clases
INSERT INTO clases (nombre_clase) VALUES
                                      ('1 de ESO'),
                                      ('2 de ESO'),
                                      ('3 de ESO'),
                                      ('4 de ESO');

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

-- TAREAS
-- 1 DE ESO
INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'tarea1', 'Tarea 1', 'Descripción de la tarea 1', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '45678901K' OR alumnos.nif_alumno = '12345678L' OR alumnos.nif_alumno = '98765432M'
    OR alumnos.nif_alumno = '34567890N' OR alumnos.nif_alumno = '54321678O')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '1 de ESO';

INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'tarea2', 'Tarea 2', 'Descripción de la tarea 2', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '45678901K' OR alumnos.nif_alumno = '12345678L' OR alumnos.nif_alumno = '98765432M'
    OR alumnos.nif_alumno = '34567890N' OR alumnos.nif_alumno = '54321678O')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '1 de ESO';

INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'tarea3', 'Tarea 3', 'Descripción de la tarea 3', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '45678901K' OR alumnos.nif_alumno = '12345678L' OR alumnos.nif_alumno = '98765432M'
    OR alumnos.nif_alumno = '34567890N' OR alumnos.nif_alumno = '54321678O')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '1 de ESO';

-- Exámenes
INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'examen1', 'Examen 1', 'Descripción del examen 1', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '45678901K' OR alumnos.nif_alumno = '12345678L' OR alumnos.nif_alumno = '98765432M'
    OR alumnos.nif_alumno = '34567890N' OR alumnos.nif_alumno = '54321678O')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '1 de ESO';

INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'examen2', 'Examen 2', 'Descripción del examen 2', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '45678901K' OR alumnos.nif_alumno = '12345678L' OR alumnos.nif_alumno = '98765432M'
    OR alumnos.nif_alumno = '34567890N' OR alumnos.nif_alumno = '54321678O')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '1 de ESO';

INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'examen3', 'Examen 3', 'Descripción del examen 3', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '45678901K' OR alumnos.nif_alumno = '12345678L' OR alumnos.nif_alumno = '98765432M'
    OR alumnos.nif_alumno = '34567890N' OR alumnos.nif_alumno = '54321678O')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '1 de ESO';


-- 2 DE ESO
INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'tarea1', 'Tarea 1', 'Descripción de la tarea 1', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '89012345P' OR alumnos.nif_alumno = '67890123Q' OR alumnos.nif_alumno = '78901234R'
    OR alumnos.nif_alumno = '90123456S' OR alumnos.nif_alumno = '23456789T')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '2 de ESO';

INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'tarea2', 'Tarea 2', 'Descripción de la tarea 2', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '89012345P' OR alumnos.nif_alumno = '67890123Q' OR alumnos.nif_alumno = '78901234R'
    OR alumnos.nif_alumno = '90123456S' OR alumnos.nif_alumno = '23456789T')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '2 de ESO';

INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'tarea3', 'Tarea 3', 'Descripción de la tarea 3', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '89012345P' OR alumnos.nif_alumno = '67890123Q' OR alumnos.nif_alumno = '78901234R'
    OR alumnos.nif_alumno = '90123456S' OR alumnos.nif_alumno = '23456789T')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '2 de ESO';

-- Exámenes
INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'examen1', 'Examen 1', 'Descripción del examen 1', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '89012345P' OR alumnos.nif_alumno = '67890123Q' OR alumnos.nif_alumno = '78901234R'
    OR alumnos.nif_alumno = '90123456S' OR alumnos.nif_alumno = '23456789T')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '2 de ESO';

INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'examen2', 'Examen 2', 'Descripción del examen 2', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '89012345P' OR alumnos.nif_alumno = '67890123Q' OR alumnos.nif_alumno = '78901234R'
    OR alumnos.nif_alumno = '90123456S' OR alumnos.nif_alumno = '23456789T')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '2 de ESO';

INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'examen3', 'Examen 3', 'Descripción del examen 3', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '89012345P' OR alumnos.nif_alumno = '67890123Q' OR alumnos.nif_alumno = '78901234R'
    OR alumnos.nif_alumno = '90123456S' OR alumnos.nif_alumno = '23456789T')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '2 de ESO';

-- 3 DE ESO
INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'tarea1', 'Tarea 1', 'Descripción de la tarea 1', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '45678901U' OR alumnos.nif_alumno = '12345678V' OR alumnos.nif_alumno = '98765432W'
    OR alumnos.nif_alumno = '34567890X' OR alumnos.nif_alumno = '54321678Y')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '3 de ESO';

INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'tarea2', 'Tarea 2', 'Descripción de la tarea 2', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '45678901U' OR alumnos.nif_alumno = '12345678V' OR alumnos.nif_alumno = '98765432W'
    OR alumnos.nif_alumno = '34567890X' OR alumnos.nif_alumno = '54321678Y')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '3 de ESO';

INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'tarea3', 'Tarea 3', 'Descripción de la tarea 3', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '45678901U' OR alumnos.nif_alumno = '12345678V' OR alumnos.nif_alumno = '98765432W'
    OR alumnos.nif_alumno = '34567890X' OR alumnos.nif_alumno = '54321678Y')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '3 de ESO';

-- Exámenes
INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'examen1', 'Examen 1', 'Descripción del examen 1', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '45678901U' OR alumnos.nif_alumno = '12345678V' OR alumnos.nif_alumno = '98765432W'
    OR alumnos.nif_alumno = '34567890X' OR alumnos.nif_alumno = '54321678Y')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '3 de ESO';

INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'examen2', 'Examen 2', 'Descripción del examen 2', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '45678901U' OR alumnos.nif_alumno = '12345678V' OR alumnos.nif_alumno = '98765432W'
    OR alumnos.nif_alumno = '34567890X' OR alumnos.nif_alumno = '54321678Y')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '3 de ESO';

INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'examen3', 'Examen 3', 'Descripción del examen 3', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '45678901U' OR alumnos.nif_alumno = '12345678V' OR alumnos.nif_alumno = '98765432W'
    OR alumnos.nif_alumno = '34567890X' OR alumnos.nif_alumno = '54321678Y')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '3 de ESO';

-- 4 DE ESO
INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'tarea1', 'Tarea 1', 'Descripción de la tarea 1', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '89012345Z' OR alumnos.nif_alumno = '67890123A' OR alumnos.nif_alumno = '78901234B'
    OR alumnos.nif_alumno = '90123456C' OR alumnos.nif_alumno = '23456789D')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '4 de ESO';

INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'tarea2', 'Tarea 2', 'Descripción de la tarea 2', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '89012345Z' OR alumnos.nif_alumno = '67890123A' OR alumnos.nif_alumno = '78901234B'
    OR alumnos.nif_alumno = '90123456C' OR alumnos.nif_alumno = '23456789D')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '4 de ESO';

INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'tarea3', 'Tarea 3', 'Descripción de la tarea 3', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '89012345Z' OR alumnos.nif_alumno = '67890123A' OR alumnos.nif_alumno = '78901234B'
    OR alumnos.nif_alumno = '90123456C' OR alumnos.nif_alumno = '23456789D')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '4 de ESO';

-- Exámenes
INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'examen1', 'Examen 1', 'Descripción del examen 1', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '89012345Z' OR alumnos.nif_alumno = '67890123A' OR alumnos.nif_alumno = '78901234B'
    OR alumnos.nif_alumno = '90123456C' OR alumnos.nif_alumno = '23456789D')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '4 de ESO';

INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'examen2', 'Examen 2', 'Descripción del examen 2', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '89012345Z' OR alumnos.nif_alumno = '67890123A' OR alumnos.nif_alumno = '78901234B'
    OR alumnos.nif_alumno = '90123456C' OR alumnos.nif_alumno = '23456789D')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '4 de ESO';

INSERT INTO tareas (tipo_tarea, titulo_tarea, descripcion_tarea, nif_profesor_tarea, nif_alumno_calificacion, id_asignatura)
SELECT 'examen3', 'Examen 3', 'Descripción del examen 3', '87651234A', alumnos.nif_alumno, asignaturas.id_asignatura
FROM alumnos, asignaturas, clases
WHERE (alumnos.nif_alumno = '89012345Z' OR alumnos.nif_alumno = '67890123A' OR alumnos.nif_alumno = '78901234B'
    OR alumnos.nif_alumno = '90123456C' OR alumnos.nif_alumno = '23456789D')
  AND asignaturas.id_clase_asignatura = clases.id_clase
  AND clases.nombre_clase = '4 de ESO';




SELECT * FROM administradores;
SELECT * FROM profesores;
SELECT * FROM alumnos;
SELECT * FROM clases;
SELECT * FROM asignaturas;
SELECT * FROM tareas;