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
                                               password_admin VARCHAR(100) NOT NULL,
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
                                      nombre_curso VARCHAR(100) NOT NULL UNIQUE
);

-- Crear la tabla para asignaturas
CREATE TABLE IF NOT EXISTS asignaturas (
                                           id_asignatura INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                                           nombre_asignatura VARCHAR(100) NOT NULL UNIQUE,
                                           nombre_curso_asignatura VARCHAR(100) NOT NULL,
                                           nif_profesor_asignatura CHAR(9) NOT NULL
);


-- Crear la tabla para tareas
CREATE TABLE IF NOT EXISTS tareas (
                                      id_tarea INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                                      tipo_tarea VARCHAR(50) NOT NULL,
                                      titulo_tarea VARCHAR(100) NOT NULL,
                                      descripcion_tarea VARCHAR(100) NOT NULL,
                                      calificacion_tarea DECIMAL(5, 3),
                                      nif_profesor_tarea CHAR(9) NOT NULL,
                                      nif_alumno_tarea CHAR(9) NOT NULL,
                                      nombre_asignatura_tarea VARCHAR(100) NOT NULL
);

-- Datos para la tabla administradores
INSERT INTO administradores (nif_admin, nombre_admin, email_admin, password_admin) VALUES
                                                                                       ('76587609C', 'María García López', 'maria.garcia@a.stdywithme.com', '$2a$10$BaInhP2.v5Dm4IYuPfJqHO4kr4OEb/2TJEqrj3AYlsSdEEiMef59O'), -- password123
                                                                                       ('35891234X', 'Juan Martínez Sánchez', 'juan.martinez@a.stdywithme.com', '$2a$10$29SmIZBAh/P3GZIDw6tUo.8gz2GkMbX350wUowxWYzpHZxMoERhKG'), -- admin456pass
                                                                                       ('44781265A', 'Laura Pérez Martín', 'laura.perez@a.stdywithme.com', '$2a$10$sNIr2tlDJ.Zz0HSR2KkDnO1InYOZrFOgxRCzrtJZaIh0JppYel8oe'); -- admin789pass

-- Datos para la tabla profesores
INSERT INTO profesores (nif_profesor, nombre_profesor, email_profesor, password_profesor) VALUES
                                                                                              -- MATEMATICAS
                                                                                              ('87651234A', 'Javier Ruiz García', 'javier.ruiz@p.studywithme.com', '$2a$10$OHRYb2trGntu0v9G0TUr/.wjlAWS8/EFRESsy3quKafq64FKGpAmy'), -- prof123pass
                                                                                              -- LENGUA
                                                                                              ('76451235B', 'María Sánchez López', 'maria.sanchez@p.studywithme.com', '$2a$10$WErz18pvf0bmZSlyZQBzb.MaFGvTIXF0zdRp6a3HmjnoshzMp7bty'), -- profpass456
                                                                                              -- CIENCIAS NATURALES
                                                                                              ('65482367C', 'Daniel Martínez Gómez', 'daniel.martinez@p.studywithme.com', '$2a$10$Kfp.bCt7jWTXzpu2TG9/ZuUBvgr8A9BZBcPNt2DNDikybL8MzLevW'), -- passwordprof789
                                                                                              -- HISTORIA
                                                                                              ('34578901D', 'Carmen García Martín', 'carmen.garcia@p.studywithme.com', '$2a$10$8jO3qMaqd3GSpAvy/d479.qE9jNwcYXy3v4melEWyCAjRU6Q2WQ8O'), -- professorpass123
                                                                                              -- INGLES
                                                                                              ('56789012E', 'Sergio Pérez Rodríguez', 'sergio.perez@p.studywithme.com', '$2a$10$ERejNG1FwMOMUFvchxx/XeOGLlO6QrIdYr8sjhMTmNySZqWLn9lsW'), -- profpass789
                                                                                              -- EDUCACION FISICA
                                                                                              ('67890123F', 'Marta López Gómez', 'marta.lopez@p.studywithme.com', '$2a$10$.t5yWwwmwX4oVI6OBsoQ1.NzF5MhvPYIXNRWPl1538scW83Gr6t0a'); -- passwordprof123


-- Datos actualizados para la tabla alumnos
INSERT INTO alumnos (nif_alumno, nombre_alumno, email_alumno, password_alumno, nombre_padre_alumno, nombre_madre_alumno) VALUES
                                                                                                                             -- 1 DE ESO
                                                                                                                             ('45678901K', 'Sara García López', 'sara.garcia@studywithme.com', '$2a$10$uG/68Ph4Nt18Ey8UbLYf5e2E.uhp30oxmJGUBfpjbaF3EBCSlVY5C', 'Antonio García', 'María López'), -- studentpass123
                                                                                                                             ('12345678L', 'Carlos Martínez Sánchez', 'carlos.martinez@studywithme.com', '$2a$10$cFCvG06yCs3Fln/wH73E..XdY7RVawyxNQPfulSqhAKkaq10/bw4G', 'José Martínez', 'Ana Sánchez'),-- alum123pass
                                                                                                                             ('98765432M', 'Laura Pérez Martín', 'laura.perez@studywithme.com', '$2a$10$k0xsNfeyC6.Spz6b7xt/OOlfsa6FLa4tVF7u3GboKVuznm/SAQOlK', 'Francisco Pérez', 'Sofía Martín'),-- student456pass
                                                                                                                             ('34567890N', 'Sergio Rodríguez Gómez', 'sergio.rodriguez@studywithme.com', '$2a$10$H7sPiivc9rMuYpwIVyeYVu62UsvqvRmGTrAAXfsRJhrKXJN3ubuKK', 'Javier Rodríguez', 'Elena Gómez'),-- alumpass789
                                                                                                                             ('54321678O', 'Marta López García', 'marta.lopez.garcia@studywithme.com', '$2a$10$KBy0EauvhKWmMlWRfxgJQOQ9l68MDY/cRZLHodrsmtRwV5fHP83KG', 'David López', 'Laura García'),-- studentpass456
                                                                                                                             -- 2 DE ESO
                                                                                                                             ('89012345P', 'Lucía Sánchez Martín', 'lucia.sanchez.martinez@studywithme.com', '$2a$10$rIjQTTFLNTP2WNiug/10nuwtFO.ZSPmA5..KfgOyg0LmapdgvnevK', 'Juan Sánchez', 'Ana Martín'),-- alum789pass
                                                                                                                             ('67890123Q', 'David González Gómez', 'david.gonzalez.gomez@studywithme.com', '$2a$10$iCLUL2NNfECDuo3tXf2TX.xtaVyLAI5JCPyeXyWR63Qf9Con1BPyi', 'Manuel González', 'María Gómez'),-- studentpass789
                                                                                                                             ('78901234R', 'Ana Martínez Rodríguez', 'ana.martinez.rodriguez@studywithme.com', '$2a$10$PnbH2U/OAonBJbwWzD194egIFnP0FrIoB78yq/V/oY2E9ZTML2EgW', 'Carlos Martínez', 'Sara Rodríguez'),-- alum666pass
                                                                                                                             ('90123456S', 'Pablo Ruiz García', 'pablo.ruiz.garcia@studywithme.com', '$2a$10$FX4/cO9T.58Dah99PkjKs.sQLC7v2EUx6gI86v7HD5k8JycnUMfSq', 'Francisco Ruiz', 'Elena García'),-- studentpass777
                                                                                                                             ('23456789T', 'Elena Fernández López', 'elena.fernandez.lopez@studywithme.com', '$2a$10$cVU0CnT1cRtXo5BppwFXQOFOrQJgteUbv3oDIln1k8QZYdtfOLaeW', 'Luis Fernández', 'Lucía López'),-- alum888pass
                                                                                                                             -- 3 DE ESO
                                                                                                                             ('45678901U', 'Laura García Martínez', 'laura.garcia@studywithme.com', '$2a$10$lUetrKUizQEFHT0hBLAt3eQnIqmmaCNlQ/My5JAquGQk9NZ5vHDFm', 'Antonio García', 'María Martínez'),-- studentpass123
                                                                                                                             ('12345678V', 'Javier Martínez Rodríguez', 'javier.martinez@studywithme.com', '$2a$10$UDxfRB8x.hn0v18BVa./G.yZHwpqfh.lCFzaxtt2k3Zb2wH6vD1rm', 'José Martínez', 'Ana Rodríguez'),-- alum123pass
                                                                                                                             ('98765432W', 'Sofía Pérez López', 'sofia.perez@studywithme.com', '$2a$10$UUPD1dLteTurLphO4MIwHuY7FI5A2iuqsOeEgJhq1kclpzDARTqIy', 'Francisco Pérez', 'Sara López'),-- student456pass
                                                                                                                             ('34567890X', 'Diego Rodríguez Sánchez', 'diego.rodriguez@studywithme.com', '$2a$10$8Djft9apoqzJZltmzwiYnuMv8xc7Q1fMJ.oKwPo8QFbnj1cPQv9ei', 'Javier Rodríguez', 'María Sánchez'),-- alumpass789
                                                                                                                             ('54321678Y', 'Marta López Martínez', 'marta.lopez@studywithme.com', '$2a$10$hm4K1j8g/7dp0Us/tp..p.iqviaBY66/b/C1Gh9LnM8daosTPFife', 'David López', 'Lucía Martínez'),-- studentpass456
                                                                                                                             -- 4 DE ESO
                                                                                                                             ('89012345Z', 'Lucía Sánchez Rodríguez', 'lucia.sanchez@studywithme.com', '$2a$10$u81YO8VWqWvoHmNXr2xqKOj5O7y.yqmvaBkM65ftADuLYtk71hhSK', 'Juan Sánchez', 'Sofía Rodríguez'),-- alum789pass
                                                                                                                             ('67890123A', 'David González García', 'david.gonzalez@studywithme.com', '$2a$10$wgSBn/.Jv8IH4LUrc51ERutmWQ6SYF8L6/q.ERlskJKwuSpthZDA6', 'Manuel González', 'Laura García'),-- studentpass789
                                                                                                                             ('78901234B', 'Ana Martínez Gómez', 'ana.martinez@studywithme.com', '$2a$10$TXFEjRPo/S2HVHwnZcEUgu23oIC8k1iMwoKcMDScsBRFYZxKlrHUK', 'Carlos Martínez', 'Marta Gómez'),-- alum666pass
                                                                                                                             ('90123456C', 'Pablo Ruiz Rodríguez', 'pablo.ruiz@studywithme.com', '$2a$10$NXg5IPPFOibqhRLpSDndE.fFX4az9ibCR55NnbqkoBStBIaSKXT/u', 'Francisco Ruiz', 'Elena Rodríguez'),-- studentpass777
                                                                                                                             ('23456789D', 'Elena Fernández Martínez', 'elena.fernandez@studywithme.com', '$2a$10$LbLYzcPcUyreOrL3pWf34eV5rCNfrzzF58DHh5oReRajUN/PufSue', 'Luis Fernández', 'Sara Martínez');-- alum888pass


-- Datos curso
INSERT INTO cursos (nombre_curso) VALUES ('1 de ESO'), ('2 de ESO'), ('3 de ESO'), ('4 de ESO');

-- Insertar asignaturas para 1º de ESO
INSERT INTO ASIGNATURAS (nombre_asignatura, nombre_curso_asignatura, nif_profesor_asignatura) VALUES
                                                                                                  -- 1 DE ESO
                                                                                                  ('Matematicas - 1 de ESO', '1 de ESO', '87651234A'),
                                                                                                  ('Lengua - 1 de ESO', '1 de ESO', '76451235B'),
                                                                                                  ('Ciencias Naturales - 1 de ESO', '1 de ESO', '65482367C'),
                                                                                                  ('Historia - 1 de ESO', '1 de ESO', '34578901D'),
                                                                                                  ('Ingles - 1 de ESO', '1 de ESO', '56789012E'),
                                                                                                  ('Educacion Fisica - 1 de ESO', '1 de ESO', '67890123F'),
                                                                                                  -- 2 DE ESO
                                                                                                  ('Matematicas - 2 de ESO', '2 de ESO', '87651234A'),
                                                                                                  ('Lengua - 2 de ESO', '2 de ESO', '76451235B'),
                                                                                                  ('Ciencias Naturales - 2 de ESO', '2 de ESO', '65482367C'),
                                                                                                  ('Historia - 2 de ESO', '2 de ESO', '34578901D'),
                                                                                                  ('Ingles - 2 de ESO', '2 de ESO', '56789012E'),
                                                                                                  ('Educacion Fisica - 2 de ESO', '2 de ESO', '67890123F'),
                                                                                                  -- 3 DE ESO
                                                                                                  ('Matematicas - 3 de ESO', '3 de ESO', '87651234A'),
                                                                                                  ('Lengua - 3 de ESO', '3 de ESO', '76451235B'),
                                                                                                  ('Ciencias Naturales - 3 de ESO', '3 de ESO', '65482367C'),
                                                                                                  ('Historia - 3 de ESO', '3 de ESO', '34578901D'),
                                                                                                  ('Ingles - 3 de ESO', '3 de ESO', '56789012E'),
                                                                                                  ('Educacion Fisica - 3 de ESO', '3 de ESO', '67890123F'),
                                                                                                  -- 4 DE ESO
                                                                                                  ('Matematicas - 4 de ESO', ' ', '87651234A'),
                                                                                                  ('Lengua - 4 de ESO', '4 de ESO', '76451235B'),
                                                                                                  ('Ciencias Naturales - 4 de ESO', '4 de ESO', '65482367C'),
                                                                                                  ('Historia - 4 de ESO', '4 de ESO', '34578901D'),
                                                                                                  ('Ingles - 4 de ESO', '4 de ESO', '56789012E'),
                                                                                                  ('Educacion Fisica - 4 de ESO', '4 de ESO', '67890123F');




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
                    nif_profesor_tarea, nif_alumno_tarea, nombre_asignatura_tarea) VALUES
-- 1 DE ESO
('Tarea', 'Tarea 1', 'Primera tarea', 1.000, '45678901K', '87651234A', 'Matematicas - 1 de ESO'),
('Tarea', 'Tarea 2', 'Segunda tarea', 4.000, '12345678L', '76451235B', 'Lengua - 1 de ESO'),
('Tarea', 'Tarea 3', 'Tercera tarea', 2.000, '98765432M	', '65482367C', 'Ciencias Naturales - 1 de ESO'),
('Tarea', 'Tarea 4', 'Cuarta tarea', 9.000, '34567890N', '34578901D', 'Historia - 1 de ESO'),
('Tarea', 'Tarea 5', 'Quinta tarea', 3.000, '54321678O', '56789012E', 'Ingles - 1 de ESO'),
('Tarea', 'Tarea 6', 'Sexta tarea', 5.000, '54321678O', '67890123F', 'Educacion Fisica - 1 de ESO'),
-- 2 DE ESO
('Tarea', 'Tarea 1', 'Primera tarea', 3.000, '89012345P', '87651234A', 'Matematicas - 2 de ESO'),
('Tarea', 'Tarea 2', 'Segunda tarea', 1.000, '67890123Q', '76451235B', 'Lengua - 2 de ESO'),
('Tarea', 'Tarea 3', 'Tercera tarea', 8.000, '78901234R', '65482367C', 'Ciencias Naturales - 2 de ESO'),
('Tarea', 'Tarea 4', 'Cuarta tarea', 5.000, '90123456S', '34578901D', 'Historia - 2 de ESO'),
('Tarea', 'Tarea 5', 'Quinta tarea', 0.000, '23456789T', '56789012E', 'Ingles - 2 de ESO'),
('Tarea', 'Tarea 6', 'Sexta tarea', 8.000, '23456789T', '67890123F', 'Educacion Fisica - 2 de ESO'),
-- 3 DE ESO
('Tarea', 'Tarea 1', 'Primera tarea', 2.000, '45678901U', '87651234A', 'Matematicas - 3 de ESO'),
('Tarea', 'Tarea 2', 'Segunda tarea', 3.000, '12345678V', '76451235B', 'Lengua - 3 de ESO'),
('Tarea', 'Tarea 3', 'Tercera tarea', 4.000, '98765432W', '65482367C', 'Ciencias Naturales - 3 de ESO'),
('Tarea', 'Tarea 4', 'Cuarta tarea', 5.000, '34567890X', '34578901D', 'Historia - 3 de ESO'),
('Tarea', 'Tarea 5', 'Quinta tarea', 6.000, '54321678Y', '56789012E', 'Ingles - 3 de ESO'),
('Tarea', 'Tarea 6', 'Sexta tarea', 7.000, '54321678Y', '67890123F', 'Educacion Fisica - 1 de ESO'),
-- 4 DE ESO
('Tarea', 'Tarea 1', 'Primera tarea', 9.000, '89012345Z', '87651234A', 'Matematicas - 4 de ESO'),
('Tarea', 'Tarea 2', 'Segunda tarea', 1.000, '67890123A', '76451235B', 'Lengua - 4 de ESO'),
('Tarea', 'Tarea 3', 'Tercera tarea', 2.000, '78901234B', '65482367C', 'Ciencias Naturales - 4 de ESO'),
('Tarea', 'Tarea 4', 'Cuarta tarea', 2.000, '90123456C', '34578901D', 'Historia - 4 de ESO'),
('Tarea', 'Tarea 5', 'Quinta tarea', 6.000, '23456789D', '56789012E', 'Ingles - 4 de ESO'),
('Tarea', 'Tarea 6', 'Sexta tarea', 10.000, '23456789D', '67890123F', 'Educacion Fisica - 4 de ESO');



SELECT * FROM administradores;
SELECT * FROM profesores;
SELECT * FROM alumnos;
SELECT * FROM cursos;
SELECT * FROM asignaturas;
SELECT * FROM cursos_asignaturas;
SELECT * FROM tareas;