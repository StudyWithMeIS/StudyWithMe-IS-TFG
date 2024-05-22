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
                                      nombre_curso VARCHAR(100) NOT NULL UNIQUE
    );


-- Crear la tabla para asignaturas
CREATE TABLE IF NOT EXISTS asignaturas (
                                           id_asignatura INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                                           nombre_asignatura VARCHAR(100) NOT NULL UNIQUE,
    imagen_asignatura VARCHAR(500),
    descripcion_asignatura VARCHAR(150),
    detalle_asignatura VARCHAR(100)
    );


-- Crear la tabla para tareas
CREATE TABLE IF NOT EXISTS tareas (
                                      id_tarea INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                                      tipo_tarea VARCHAR(50) NOT NULL,
    titulo_tarea VARCHAR(100) NOT NULL,
    descripcion_tarea VARCHAR(100) NOT NULL,
    calificacion_tarea DECIMAL(5, 3)
    );









-- TABLAS DE ENLACE ------------------------------------
-- Tabla de enlace tarea_curso
CREATE TABLE IF NOT EXISTS tarea_curso (
                                           id_tarea INT NOT NULL,
                                           id_curso INT NOT NULL,
                                           FOREIGN KEY (id_tarea) REFERENCES tareas(id_tarea),
    FOREIGN KEY (id_curso) REFERENCES cursos(id_curso),
    PRIMARY KEY (id_tarea, id_curso)
    );

-- Tabla de enlace tarea_asignatura
CREATE TABLE IF NOT EXISTS tarea_asignatura (
                                                id_tarea INT NOT NULL,
                                                id_asignatura INT NOT NULL,
                                                FOREIGN KEY (id_tarea) REFERENCES tareas(id_tarea),
    FOREIGN KEY (id_asignatura) REFERENCES asignaturas(id_asignatura),
    PRIMARY KEY (id_tarea, id_asignatura)
    );

-- Tabla de enlace asignatura_curso
CREATE TABLE IF NOT EXISTS asignatura_curso (
                                                id_asignatura INT NOT NULL,
                                                id_curso INT NOT NULL,
                                                FOREIGN KEY (id_asignatura) REFERENCES asignaturas(id_asignatura),
    FOREIGN KEY (id_curso) REFERENCES cursos(id_curso),
    PRIMARY KEY (id_asignatura, id_curso)
    );

-- Tabla de enlace alumnos_tarea
CREATE TABLE IF NOT EXISTS alumnos_tarea (
                                             id_alumno INT NOT NULL,
                                             id_tarea INT NOT NULL,
                                             FOREIGN KEY (id_alumno) REFERENCES alumnos(id_alumno),
    FOREIGN KEY (id_tarea) REFERENCES tareas(id_tarea),
    PRIMARY KEY (id_alumno, id_tarea)
    );

-- Tabla de enlace profesor_tarea
CREATE TABLE IF NOT EXISTS profesor_tarea (
                                              id_profesor INT NOT NULL,
                                              id_tarea INT NOT NULL,
                                              FOREIGN KEY (id_profesor) REFERENCES profesores(id_profesor),
    FOREIGN KEY (id_tarea) REFERENCES tareas(id_tarea),
    PRIMARY KEY (id_profesor, id_tarea)
    );

-- Tabla de enlace alumno_curso
CREATE TABLE IF NOT EXISTS alumno_curso (
                                            id_alumno INT NOT NULL,
                                            id_curso INT NOT NULL,
                                            FOREIGN KEY (id_alumno) REFERENCES alumnos(id_alumno),
    FOREIGN KEY (id_curso) REFERENCES cursos(id_curso),
    PRIMARY KEY (id_alumno, id_curso)
    );

-- Tabla de enlace profesor_curso
CREATE TABLE IF NOT EXISTS profesor_curso (
                                              id_profesor INT NOT NULL,
                                              id_curso INT NOT NULL,
                                              FOREIGN KEY (id_profesor) REFERENCES profesores(id_profesor),
    FOREIGN KEY (id_curso) REFERENCES cursos(id_curso),
    PRIMARY KEY (id_profesor, id_curso)
    );

-- Tabla de enlace alumno_asignatura
CREATE TABLE IF NOT EXISTS alumno_asignatura (
                                                 id_alumno INT NOT NULL,
                                                 id_asignatura INT NOT NULL,
                                                 FOREIGN KEY (id_alumno) REFERENCES alumnos(id_alumno),
    FOREIGN KEY (id_asignatura) REFERENCES asignaturas(id_asignatura),
    PRIMARY KEY (id_alumno, id_asignatura)
    );

-- Tabla de enlace profesor_asignatura
CREATE TABLE IF NOT EXISTS profesor_asignatura (
                                                   id_profesor INT NOT NULL,
                                                   id_asignatura INT NOT NULL,
                                                   FOREIGN KEY (id_profesor) REFERENCES profesores(id_profesor),
    FOREIGN KEY (id_asignatura) REFERENCES asignaturas(id_asignatura),
    PRIMARY KEY (id_profesor, id_asignatura)
    );










-- INSERT INTO --------------------------------------

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



-- Insertar asignaturas
INSERT INTO asignaturas (nombre_asignatura, imagen_asignatura, descripcion_asignatura, detalle_asignatura) VALUES
                                                                                                               -- 1 DE ESO
                                                                                                               ('Matematicas - 1 de ESO', '', 'Matemáticas básicas', 'Álgebra y geometría'),
                                                                                                               ('Lengua - 1 de ESO', '', 'Lengua española', 'Gramática y literatura'),
                                                                                                               ('Ciencias Naturales - 1 de ESO', '', 'Ciencias naturales', 'Biología y geología'),
                                                                                                               ('Historia - 1 de ESO', '', 'Historia general', 'Historia antigua y medieval'),
                                                                                                               ('Ingles - 1 de ESO', '', 'Inglés básico', 'Gramática y vocabulario'),
                                                                                                               ('Educacion Fisica - 1 de ESO', '', 'Educación física', 'Deportes y actividades físicas'),
                                                                                                               -- 2 DE ESO
                                                                                                               ('Matematicas - 2 de ESO', '', 'Matemáticas avanzadas', 'Ecuaciones y funciones'),
                                                                                                               ('Lengua - 2 de ESO', '', 'Lengua española', 'Análisis sintáctico y literatura'),
                                                                                                               ('Ciencias Naturales - 2 de ESO', '', 'Ciencias naturales', 'Física y química'),
                                                                                                               ('Historia - 2 de ESO', '', 'Historia mundial', 'Historia moderna'),
                                                                                                               ('Ingles - 2 de ESO', '', 'Inglés intermedio', 'Conversación y redacción'),
                                                                                                               ('Educacion Fisica - 2 de ESO', '', 'Educación física', 'Deportes y actividades físicas'),
                                                                                                               -- 3 DE ESO
                                                                                                               ('Matematicas - 3 de ESO', '', 'Matemáticas avanzadas', 'Trigonometría y estadística'),
                                                                                                               ('Lengua - 3 de ESO', '', 'Lengua española', 'Literatura y redacción avanzada'),
                                                                                                               ('Ciencias Naturales - 3 de ESO', '', 'Ciencias naturales', 'Química y biología avanzada'),
                                                                                                               ('Historia - 3 de ESO', '', 'Historia moderna', 'Historia contemporánea'),
                                                                                                               ('Ingles - 3 de ESO', '', 'Inglés avanzado', 'Literatura y redacción avanzada'),
                                                                                                               ('Educacion Fisica - 3 de ESO', '', 'Educación física', 'Deportes y actividades físicas'),
                                                                                                               -- 4 DE ESO
                                                                                                               ('Matematicas - 4 de ESO', '', 'Matemáticas avanzadas', 'Cálculo y geometría analítica'),
                                                                                                               ('Lengua - 4 de ESO', '', 'Lengua española', 'Crítica literaria y redacción avanzada'),
                                                                                                               ('Ciencias Naturales - 4 de ESO', '', 'Ciencias naturales', 'Física y biología avanzada'),
                                                                                                               ('Historia - 4 de ESO', '', 'Historia contemporánea', 'Historia del siglo XX'),
                                                                                                               ('Ingles - 4 de ESO', '', 'Inglés avanzado', 'Literatura y análisis crítico'),
                                                                                                               ('Educacion Fisica - 4 de ESO', '', 'Educación física', 'Deportes y actividades físicas');


-- TAREAS
INSERT INTO TAREAS (tipo_tarea, titulo_tarea, descripcion_tarea, calificacion_tarea) VALUES
-- 1 DE ESO
('Tarea', 'Tarea 1', 'Primera tarea', 1.000),
('Tarea', 'Tarea 2', 'Segunda tarea', 4.000),
('Tarea', 'Tarea 3', 'Tercera tarea', 2.000),
('Tarea', 'Tarea 4', 'Cuarta tarea', 9.000),
('Tarea', 'Tarea 5', 'Quinta tarea', 3.000),
('Tarea', 'Tarea 6', 'Sexta tarea', 5.000),
-- 2 DE ESO
('Tarea', 'Tarea 1', 'Primera tarea', 3.000),
('Tarea', 'Tarea 2', 'Segunda tarea', 1.000),
('Tarea', 'Tarea 3', 'Tercera tarea', 8.000),
('Tarea', 'Tarea 4', 'Cuarta tarea', 5.000),
('Tarea', 'Tarea 5', 'Quinta tarea', 0.000),
('Tarea', 'Tarea 6', 'Sexta tarea', 8.000),
-- 3 DE ESO
('Tarea', 'Tarea 1', 'Primera tarea', 2.000),
('Tarea', 'Tarea 2', 'Segunda tarea', 3.000),
('Tarea', 'Tarea 3', 'Tercera tarea', 4.000),
('Tarea', 'Tarea 4', 'Cuarta tarea', 5.000),
('Tarea', 'Tarea 5', 'Quinta tarea', 6.000),
('Tarea', 'Tarea 6', 'Sexta tarea', 7.000),
-- 4 DE ESO
('Tarea', 'Tarea 1', 'Primera tarea', 9.000),
('Tarea', 'Tarea 2', 'Segunda tarea', 1.000),
('Tarea', 'Tarea 3', 'Tercera tarea', 2.000),
('Tarea', 'Tarea 4', 'Cuarta tarea', 2.000),
('Tarea', 'Tarea 5', 'Quinta tarea', 6.000),
('Tarea', 'Tarea 6', 'Sexta tarea', 10.000);




-- Datos para la tabla alumno_curso
INSERT INTO alumno_curso (id_alumno, id_curso) VALUES
                                                   -- 1 DE ESO
                                                   (1, 1), -- Sara García López en 1 de ESO
                                                   (2, 1), -- Carlos Martínez Sánchez en 1 de ESO
                                                   (3, 1), -- Laura Pérez Martín en 1 de ESO
                                                   (4, 1), -- Sergio Rodríguez Gómez en 1 de ESO
                                                   (5, 1), -- Marta López García en 1 de ESO
                                                   -- 2 DE ESO
                                                   (6, 2), -- Lucía Sánchez Martín en 2 de ESO
                                                   (7, 2), -- David González Gómez en 2 de ESO
                                                   (8, 2), -- Ana Martínez Rodríguez en 2 de ESO
                                                   (9, 2), -- Pablo Ruiz García en 2 de ESO
                                                   (10, 2), -- Elena Fernández López en 2 de ESO
                                                   -- 3 DE ESO
                                                   (11, 3), -- Laura García Martínez en 3 de ESO
                                                   (12, 3), -- Javier Martínez Rodríguez en 3 de ESO
                                                   (13, 3), -- Sofía Pérez López en 3 de ESO
                                                   (14, 3), -- Diego Rodríguez Sánchez en 3 de ESO
                                                   (15, 3), -- Marta López Martínez en 3 de ESO
                                                   -- 4 DE ESO
                                                   (16, 4), -- Lucía Sánchez Rodríguez en 4 de ESO
                                                   (17, 4), -- David González García en 4 de ESO
                                                   (18, 4), -- Ana Martínez Gómez en 4 de ESO
                                                   (19, 4), -- Pablo Ruiz Rodríguez en 4 de ESO
                                                   (20, 4); -- Elena Fernández Martínez en 4 de ESO

-- Datos para la tabla profesor_curso
INSERT INTO profesor_curso (id_profesor, id_curso) VALUES
                                                       (1, 1), -- Javier Ruiz García en 1 de ESO (Matemáticas)
                                                       (2, 1), -- María Sánchez López en 1 de ESO (Lengua)
                                                       (3, 1), -- Daniel Martínez Gómez en 1 de ESO (Ciencias Naturales)
                                                       (4, 1), -- Carmen García Martín en 1 de ESO (Historia)
                                                       (5, 1), -- Sergio Pérez Rodríguez en 1 de ESO (Inglés)
                                                       (6, 1), -- Marta López Gómez en 1 de ESO (Educación Física)

                                                       (1, 2), -- Javier Ruiz García en 2 de ESO (Matemáticas)
                                                       (2, 2), -- María Sánchez López en 2 de ESO (Lengua)
                                                       (3, 2), -- Daniel Martínez Gómez en 2 de ESO (Ciencias Naturales)
                                                       (4, 2), -- Carmen García Martín en 2 de ESO (Historia)
                                                       (5, 2), -- Sergio Pérez Rodríguez en 2 de ESO (Inglés)
                                                       (6, 2), -- Marta López Gómez en 2 de ESO (Educación Física)

                                                       (1, 3), -- Javier Ruiz García en 3 de ESO (Matemáticas)
                                                       (2, 3), -- María Sánchez López en 3 de ESO (Lengua)
                                                       (3, 3), -- Daniel Martínez Gómez en 3 de ESO (Ciencias Naturales)
                                                       (4, 3), -- Carmen García Martín en 3 de ESO (Historia)
                                                       (5, 3), -- Sergio Pérez Rodríguez en 3 de ESO (Inglés)
                                                       (6, 3), -- Marta López Gómez en 3 de ESO (Educación Física)

                                                       (1, 4), -- Javier Ruiz García en 4 de ESO (Matemáticas)
                                                       (2, 4), -- María Sánchez López en 4 de ESO (Lengua)
                                                       (3, 4), -- Daniel Martínez Gómez en 4 de ESO (Ciencias Naturales)
                                                       (4, 4), -- Carmen García Martín en 4 de ESO (Historia)
                                                       (5, 4), -- Sergio Pérez Rodríguez en 4 de ESO (Inglés)
                                                       (6, 4); -- Marta López Gómez en 4 de ESO (Educación Física)




-- Insertar datos en tabla alumno_asignatura
INSERT INTO alumno_asignatura (id_alumno, id_asignatura) VALUES
                                                             -- 1 DE ESO
                                                             (1, 1), -- Matemáticas 1 de ESO
                                                             (2, 2), -- Lengua 1 de ESO
                                                             (3, 3), -- Ciencias Naturales 1 de ESO
                                                             (4, 4), -- Historia 1 de ESO
                                                             (5, 5), -- Inglés 1 de ESO
                                                             (6, 6), -- Educación Física 1 de ESO
                                                             -- 2 DE ESO
                                                             (7, 7), -- Matemáticas 2 de ESO
                                                             (8, 8), -- Lengua 2 de ESO
                                                             (9, 9), -- Ciencias Naturales 2 de ESO
                                                             (10, 10), -- Historia 2 de ESO
                                                             (11, 11), -- Inglés 2 de ESO
                                                             (12, 12), -- Educación Física 2 de ESO
                                                             -- 3 DE ESO
                                                             (13, 13), -- Matemáticas 3 de ESO
                                                             (14, 14), -- Lengua 3 de ESO
                                                             (15, 15), -- Ciencias Naturales 3 de ESO
                                                             (16, 16), -- Historia 3 de ESO
                                                             (17, 17), -- Inglés 3 de ESO
                                                             (18, 18), -- Educación Física 3 de ESO
                                                             -- 4 DE ESO
                                                             (19, 19), -- Matemáticas 4 de ESO
                                                             (20, 20), -- Lengua 4 de ESO
                                                             (1, 21), -- Ciencias Naturales 4 de ESO
                                                             (2, 22), -- Historia 4 de ESO
                                                             (3, 23), -- Inglés 4 de ESO
                                                             (4, 24); -- Educación Física 4 de ESO

-- Insertar datos en tabla profesor_asignatura
INSERT INTO profesor_asignatura (id_profesor, id_asignatura) VALUES
                                                                 -- 1 DE ESO
                                                                 (1, 1), -- Matemáticas 1 de ESO
                                                                 (2, 2), -- Lengua 1 de ESO
                                                                 (3, 3), -- Ciencias Naturales 1 de ESO
                                                                 (4, 4), -- Historia 1 de ESO
                                                                 (5, 5), -- Inglés 1 de ESO
                                                                 (6, 6), -- Educación Física 1 de ESO
                                                                 -- 2 DE ESO
                                                                 (1, 7), -- Matemáticas 2 de ESO
                                                                 (2, 8), -- Lengua 2 de ESO
                                                                 (3, 9), -- Ciencias Naturales 2 de ESO
                                                                 (4, 10), -- Historia 2 de ESO
                                                                 (5, 11), -- Inglés 2 de ESO
                                                                 (6, 12), -- Educación Física 2 de ESO
                                                                 -- 3 DE ESO
                                                                 (1, 13), -- Matemáticas 3 de ESO
                                                                 (2, 14), -- Lengua 3 de ESO
                                                                 (3, 15), -- Ciencias Naturales 3 de ESO
                                                                 (4, 16), -- Historia 3 de ESO
                                                                 (5, 17), -- Inglés 3 de ESO
                                                                 (6, 18), -- Educación Física 3 de ESO
                                                                 -- 4 DE ESO
                                                                 (1, 19), -- Matemáticas 4 de ESO
                                                                 (2, 20), -- Lengua 4 de ESO
                                                                 (3, 21), -- Ciencias Naturales 4 de ESO
                                                                 (4, 22), -- Historia 4 de ESO
                                                                 (5, 23), -- Inglés 4 de ESO
                                                                 (6, 24); -- Educación Física 4 de ESO



INSERT INTO alumnos_tarea (id_alumno, id_tarea) VALUES
-- Alumno 1
(1, 1),
(1, 21),
-- Alumno 2
(2, 2),
(2, 22),
-- Alumno 3
(3, 3),
(3, 23),
-- Alumno 4
(4, 4),
(4, 23),
-- Alumno 5
(5, 5),
-- Alumno 6
(6, 6),
-- Alumno 7
(7, 7),
-- Alumno 8
(8, 8),
-- Alumno 9
(9, 9),
-- Alumno 10
(10, 10),
-- Alumno 11
(11, 11),
-- Alumno 12
(12, 12),
-- Alumno 13
(13, 13),
-- Alumno 14
(14, 14),
-- Alumno 15
(15, 15),
-- Alumno 16
(16, 16),
-- Alumno 17
(17, 17),
-- Alumno 18
(18, 18);



-- Tabla de enlace entre profesor - tarea
INSERT INTO profesor_tarea (id_profesor, id_tarea) VALUES
-- Profesor Matemáticas
(1, 1),
(1, 7),
(1, 13),
(1, 19),
-- Profesor Lengua
(2, 2),
(2, 8),
(2, 14),
(2, 20),
-- Profesor Ciencias Naturales
(3, 3),
(3, 9),
(3, 15),
(3, 21),
-- Profesor Historia
(4, 4),
(4, 10),
(4, 16),
(4, 22),
-- Profesor Inglés
(5, 5),
(5, 11),
(5, 17),
(5, 23),
-- Profesor Educación Física
(6, 6),
(6, 12),
(6, 18),
(6, 24);



select * from asignatura_curso;
-- Insertar asignaturas para 1º de ESO
INSERT INTO asignatura_curso (id_asignatura, id_curso) VALUES
                                                           -- 1 DE ESO
                                                           (1, 1), -- Matematicas - 1 de ESO
                                                           (2, 1), -- Lengua - 1 de ESO
                                                           (3, 1), -- Ciencias Naturales - 1 de ESO
                                                           (4, 1), -- Historia - 1 de ESO
                                                           (5, 1), -- Ingles - 1 de ESO
                                                           (6, 1), -- Educacion Fisica - 1 de ESO
                                                           -- 2 DE ESO
                                                           (7, 2), -- Matematicas - 2 de ESO
                                                           (8, 2), -- Lengua - 2 de ESO
                                                           (9, 2), -- Ciencias Naturales - 2 de ESO
                                                           (10, 2), -- Historia - 2 de ESO
                                                           (11, 2), -- Ingles - 2 de ESO
                                                           (12, 2), -- Educacion Fisica - 2 de ESO
                                                           -- 3 DE ESO
                                                           (13, 3), -- Matematicas - 3 de ESO
                                                           (14, 3), -- Lengua - 3 de ESO
                                                           (15, 3), -- Ciencias Naturales - 3 de ESO
                                                           (16, 3), -- Historia - 3 de ESO
                                                           (17, 3), -- Ingles - 3 de ESO
                                                           (18, 3), -- Educacion Fisica - 3 de ESO
                                                           -- 4 DE ESO
                                                           (19, 4), -- Matematicas - 4 de ESO
                                                           (20, 4), -- Lengua - 4 de ESO
                                                           (21, 4), -- Ciencias Naturales - 4 de ESO
                                                           (22, 4), -- Historia - 4 de ESO
                                                           (23, 4), -- Ingles - 4 de ESO
                                                           (24, 4); -- Educacion Fisica - 4 de ESO



INSERT INTO tarea_curso(id_tarea, id_curso) VALUES
                                                (1,1),
                                                (2,1),
                                                (3,1),
                                                (4,1),
                                                (5,1),
                                                (6,1),

                                                (7,2),
                                                (8,2),
                                                (9,2),
                                                (10,2),
                                                (11,2),
                                                (12,2),

                                                (13,3),
                                                (14,3),
                                                (20,4),
                                                (21,4),
                                                (22,4),
                                                (23,4),
                                                (24,4);


INSERT INTO tarea_asignatura (id_tarea, id_asignatura) VALUES
                                                           (1, 1),
                                                           (2, 2),
                                                           (3, 3),
                                                           (4, 4),
                                                           (5, 5),
                                                           (6, 6),
                                                           (7, 7),
                                                           (8, 8),
                                                           (9, 9),
                                                           (10, 10),
                                                           (11, 11),
                                                           (12, 12),
                                                           (13, 13),
                                                           (14, 14),
                                                           (15, 15),
                                                           (16, 16),
                                                           (17, 17),
                                                           (18, 18),
                                                           (19, 19),
                                                           (20, 20),
                                                           (21, 21),
                                                           (22, 22),
                                                           (23, 23),
                                                           (24, 24);





SELECT * FROM administradores;
SELECT * FROM profesores;
SELECT * FROM alumnos;
SELECT * FROM cursos;
SELECT * FROM asignaturas;
SELECT * FROM tareas;
SELECT * FROM asignatura_curso;
SELECT * FROM tarea_curso;
SELECT * FROM tarea_asignatura;
SELECT * FROM alumnos_tarea;
SELECT * FROM profesor_tarea;
SELECT * FROM alumno_curso;
SELECT * FROM profesor_curso;
SELECT * FROM alumno_asignatura;
SELECT * FROM profesor_asignatura;

