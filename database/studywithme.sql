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
    descripcion_tarea VARCHAR(300) NOT NULL,
    calificacion_tarea DECIMAL(5, 3)
    );









-- TABLAS DE ENLACE ------------------------------------
-- Tabla de enlace tarea_curso
CREATE TABLE IF NOT EXISTS tarea_curso (
                                           id_tarea_curso INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                                           id_tarea INT NOT NULL,
                                           id_curso INT NOT NULL,
                                           FOREIGN KEY (id_tarea) REFERENCES tareas(id_tarea),
    FOREIGN KEY (id_curso) REFERENCES cursos(id_curso)
    );

-- Tabla de enlace tarea_asignatura
CREATE TABLE IF NOT EXISTS tarea_asignatura (
                                                id_tarea_asignatura INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                                                id_tarea INT NOT NULL,
                                                id_asignatura INT NOT NULL,
                                                FOREIGN KEY (id_tarea) REFERENCES tareas(id_tarea),
    FOREIGN KEY (id_asignatura) REFERENCES asignaturas(id_asignatura)
    );

-- Tabla de enlace asignatura_curso
CREATE TABLE IF NOT EXISTS asignatura_curso (
                                                id_asignatura_curso INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                                                id_asignatura INT NOT NULL,
                                                id_curso INT NOT NULL,
                                                FOREIGN KEY (id_asignatura) REFERENCES asignaturas(id_asignatura),
    FOREIGN KEY (id_curso) REFERENCES cursos(id_curso)
    );

-- Tabla de enlace alumnos_tarea
CREATE TABLE IF NOT EXISTS alumnos_tarea (
                                             id_alumnos_tarea INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                                             id_alumno INT NOT NULL,
                                             id_tarea INT NOT NULL,
                                             FOREIGN KEY (id_alumno) REFERENCES alumnos(id_alumno),
    FOREIGN KEY (id_tarea) REFERENCES tareas(id_tarea)
    );

-- Tabla de enlace profesor_tarea
CREATE TABLE IF NOT EXISTS profesor_tarea (
                                              id_profesor_tarea INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                                              id_profesor INT NOT NULL,
                                              id_tarea INT NOT NULL,
                                              FOREIGN KEY (id_profesor) REFERENCES profesores(id_profesor),
    FOREIGN KEY (id_tarea) REFERENCES tareas(id_tarea)
    );

-- Tabla de enlace alumno_curso
CREATE TABLE IF NOT EXISTS alumno_curso (
                                            id_alumno_curso INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                                            id_alumno INT NOT NULL,
                                            id_curso INT NOT NULL,
                                            FOREIGN KEY (id_alumno) REFERENCES alumnos(id_alumno),
    FOREIGN KEY (id_curso) REFERENCES cursos(id_curso)
    );

-- Tabla de enlace profesor_curso
CREATE TABLE IF NOT EXISTS profesor_curso (
                                              id_profesor_curso INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                                              id_profesor INT NOT NULL,
                                              id_curso INT NOT NULL,
                                              FOREIGN KEY (id_profesor) REFERENCES profesores(id_profesor),
    FOREIGN KEY (id_curso) REFERENCES cursos(id_curso)
    );

-- Tabla de enlace alumno_asignatura
CREATE TABLE IF NOT EXISTS alumno_asignatura (
                                                 id_alumno_asignatura INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                                                 id_alumno INT NOT NULL,
                                                 id_asignatura INT NOT NULL,
                                                 FOREIGN KEY (id_alumno) REFERENCES alumnos(id_alumno),
    FOREIGN KEY (id_asignatura) REFERENCES asignaturas(id_asignatura)
    );

-- Tabla de enlace profesor_asignatura
CREATE TABLE IF NOT EXISTS profesor_asignatura (
                                                   id_profesor_asignatura INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                                                   id_profesor INT NOT NULL,
                                                   id_asignatura INT NOT NULL,
                                                   FOREIGN KEY (id_profesor) REFERENCES profesores(id_profesor),
    FOREIGN KEY (id_asignatura) REFERENCES asignaturas(id_asignatura)
    );










-- INSERT INTO --------------------------------------

-- Datos para la tabla administradores
INSERT INTO administradores (nif_admin, nombre_admin, email_admin, password_admin) VALUES
                                                                                       ('76587609C', 'María García López', 'maria.garcia@a.stdywithme.com', '$2a$10$BaInhP2.v5Dm4IYuPfJqHO4kr4OEb/2TJEqrj3AYlsSdEEiMef59O'), -- password123
                                                                                       ('35891234X', 'Juan Martínez Sánchez', 'juan.martinez@a.stdywithme.com', '$2a$10$29SmIZBAh/P3GZIDw6tUo.8gz2GkMbX350wUowxWYzpHZxMoERhKG'), -- admin456pass
                                                                                       ('44781265A', 'Laura Pérez Martín', 'laura.perez@a.stdywithme.com', '$2a$10$sNIr2tlDJ.Zz0HSR2KkDnO1InYOZrFOgxRCzrtJZaIh0JppYel8oe'); -- admin789pass

select * from tareas;

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
                                                                                                                             ('87654321P', 'Luis Ramírez Soto', 'luis.ramirez@studywithme.com', '$2a$10$FyW9U0qO6Z3DX.tGy5poje8pyJSV5C6lX3xMj8VpPkh0VwUJspvhy', 'Pedro Ramírez', 'Ana Soto'),-- pass1234
                                                                                                                             ('23456789A', 'Julia Fernández Ruiz', 'julia.fernandez@studywithme.com', '$2a$10$6pQXbU0Wkgz6lpKn9ICBCO7uNUQ/2QWjj.tX49DbGPvM3yE3CxiOa', 'Miguel Fernández', 'Lucía Ruiz'),-- password567
                                                                                                                             ('34567891R', 'Miguel Santos Torres', 'miguel.santos@studywithme.com', '$2a$10$.6w4Ry4x5Xe23o3Lhz1GOO9RJ7p.y3OCW34.L5jY7QhZsV7hdH41W', 'Carlos Santos', 'Rosa Torres'),-- pass890
                                                                                                                             ('45678902S', 'Natalia Jiménez Ortega', 'natalia.jimenez@studywithme.com', '$2a$10$X.O8c62L0u9Bntg0P.Vv6uLnqOltTTf9CZ7z2/CrEzDWJXYX7b3h6', 'José Jiménez', 'Carmen Ortega'),-- password123
                                                                                                                             ('56789012T', 'Pablo Castro Blanco', 'pablo.castro@studywithme.com', '$2a$10$D9mFV13XNOXzvlRXHc3q5uLrR/9F6UcfDrtMQZ1Z4JmDkxJGdPVgO', 'Fernando Castro', 'Marta Blanco'),-- pass456
                                                                                                                             ('67890123U', 'Laura Vázquez Delgado', 'laura.vazquez@studywithme.com', '$2a$10$Y6O0G1quby90Owv5a.IHBuQ2fW6g/kPDAvZ0ZPrx6wwTiL.1y/eS.', 'Raúl Vázquez', 'Beatriz Delgado'),-- pass789
                                                                                                                             ('78901234V', 'David Morales Peña', 'david.morales@studywithme.com', '$2a$10$X1t/ObPQhUhB2uL8gJz/Wu3kB47PI6Z7Z.BOk/X9n9YQq5yX2Bu9G', 'Alberto Morales', 'Eva Peña'),-- pass111
                                                                                                                             ('89012345W', 'Sofía Herrera Cabrera', 'sofia.herrera@studywithme.com', '$2a$10$U.U1jtBl1KrOQ9OQkm1wRuDfQ5D/JUXD.kA8Jk9/2v14Y/1G7bwPG', 'Diego Herrera', 'Elena Cabrera'),-- pass222
                                                                                                                             ('90123456X', 'Lucas Alonso Rivas', 'lucas.alonso@studywithme.com', '$2a$10$epFDxdTrBTVHDb7vwOgtuO9aO/w7/OYXcz1eKJTwfH7gB.sv6pZ5K', 'Hugo Alonso', 'Irene Rivas'),-- pass333
                                                                                                                             ('01234567Y', 'María Flores Méndez', 'maria.flores@studywithme.com', '$2a$10$7QciQ6vFB0/E2NgGe0Dz8OgQ5ZONOk3ws13Yg.KymyoC7pFdJ/4GS', 'Luis Flores', 'Sonia Méndez'),-- pass444
                                                                                                                             ('12345678Z', 'Daniela Gil Vega', 'daniela.gil@studywithme.com', '$2a$10$h4Zw8Dcz5Rk3l9CBuUqHYOKfKD2VDPbnH9DOuP9J66rEXz5a3IZQm', 'Ricardo Gil', 'Clara Vega'),-- pass555
                                                                                                                             ('23456789J', 'Jorge Ortiz Lara', 'jorge.ortiz@studywithme.com', '$2a$10$wO5d2KPq/W3k1.S7XCRGfOVgy92PTruMphuoh0VYt7l30kRGXh1DS', 'Sergio Ortiz', 'Natalia Lara'),-- pass666
                                                                                                                             ('34567890B', 'Elena Romero Rubio', 'elena.romero@studywithme.com', '$2a$10$OZpPVvVrF7a4Q6S3TOBo2OK.q76N5jFR03lOWBmyfsSTk5trRlkay', 'Jorge Romero', 'Marta Rubio'),-- pass777
                                                                                                                             ('45678901C', 'Miguel Delgado Hernández', 'miguel.delgado@studywithme.com', '$2a$10$aT0DN6mJUI5osGJp7vnNe.CcP8bcm5g7CM93P9ZSK/p0BtFCTFdV2', 'Manuel Delgado', 'Elisa Hernández'),-- pass888
                                                                                                                             ('56789012D', 'Raúl Cruz Fuentes', 'raul.cruz@studywithme.com', '$2a$10$LqhvzVx43X0U1K76kT5Wn.9gJ93DEAL6TO7yZBGph1MnvNxVIC0zi', 'Pedro Cruz', 'Paula Fuentes'),-- pass999

-- 2 DE ESO
                                                                                                                             ('67890123E', 'Lucía Sánchez Martín', 'lucia.sanchez.martin@studywithme.com', '$2a$10$rIjQTTFLNTP2WNiug/10nuwtFO.ZSPmA5..KfgOyg0LmapdgvnevK', 'Juan Sánchez', 'Ana Martín'),-- alum789pass
                                                                                                                             ('89012345F', 'David González Gómez', 'david.gonzalez.gomez@studywithme.com', '$2a$10$iCLUL2NNfECDuo3tXf2TX.xtaVyLAI5JCPyeXyWR63Qf9Con1BPyi', 'Manuel González', 'María Gómez'),-- studentpass789
                                                                                                                             ('78901234G', 'Ana Martínez Rodríguez', 'ana.martinez.rodriguez@studywithme.com', '$2a$10$PnbH2U/OAonBJbwWzD194egIFnP0FrIoB78yq/V/oY2E9ZTML2EgW', 'Carlos Martínez', 'Sara Rodríguez'),-- alum666pass
                                                                                                                             ('90123456H', 'Pablo Ruiz García', 'pablo.ruiz.garcia@studywithme.com', '$2a$10$FX4/cO9T.58Dah99PkjKs.sQLC7v2EUx6gI86v7HD5k8JycnUMfSq', 'Francisco Ruiz', 'Elena García'),-- studentpass777
                                                                                                                             ('23456789I', 'Elena Fernández López', 'elena.fernandez.lopez@studywithme.com', '$2a$10$cVU0CnT1cRtXo5BppwFXQOFOrQJgteUbv3oDIln1k8QZYdtfOLaeW', 'Luis Fernández', 'Lucía López'),-- alum888pass
                                                                                                                             ('12345678J', 'Carlos Ramírez Soto', 'carlos.ramirez@studywithme.com', '$2a$10$3nPzGJwOHxZnd8A8N9/JeOB56zpoXQRlhy6wXK9pF9Br6ImGJx.gO', 'Pedro Ramírez', 'Ana Soto'),-- pass1234
                                                                                                                             ('87654321K', 'María Torres Ruiz', 'maria.torres@studywithme.com', '$2a$10$4y6BrBhiycC/NBqW5T.SZ.wRoVxRYYI11R7hQoPOQn6zN5PlS6FeO', 'Miguel Torres', 'Lucía Ruiz'),-- password567
                                                                                                                             ('34567890L', 'Javier Santos Gómez', 'javier.santos@studywithme.com', '$2a$10$zn7v4dp0frLJkSTI19mKJe/N2ck8YsNkJtROfC9gANkWZy0NxsPl6', 'Carlos Santos', 'Rosa Gómez'),-- pass890
                                                                                                                             ('23456789M', 'Natalia Ortega Ramírez', 'natalia.ortega@studywithme.com', '$2a$10$O5sc0UO5Gl1GRCdAPOZcUuOVC90q.P9X5.bWQLh8imuv6h4hV6oeq', 'José Ortega', 'Carmen Ramírez'),-- password123
                                                                                                                             ('98765432N', 'Fernando Castro Vega', 'fernando.castro@studywithme.com', '$2a$10$w.cFihnToUye/Yc3BSJ9EO6KlB.7D7X0sYJWyB9/X6jcvuxQFGZsW', 'Raúl Castro', 'Beatriz Vega'),-- pass456
                                                                                                                             ('45678901O', 'Laura Delgado Blanco', 'laura.delgado@studywithme.com', '$2a$10$J.dRPEfCMXrJ9N6KxQRCWuq9FkUqBFmhIXhINo6fR3oPzYGmP5Bmi', 'Miguel Delgado', 'Sonia Blanco'),-- pass789
                                                                                                                             ('67890123P', 'Daniel Pareja Herrera', 'daniel.pareja@studywithme.com', '$2a$10$tYb2gVv5guahTlrSBt7H.eOEiYf8/b2keDt90DJhFeU29ocFe4.0K', 'Alberto Peña', 'Eva Herrera'),-- pass111
                                                                                                                             ('78901234Q', 'Elena Cabrera Alonso', 'elena.cabrera@studywithme.com', '$2a$10$4Z.JCNhUSzR7i53nR1.8sOOyQ3k0RxEm7vd7jyzBO3F5MiR/E9smW', 'Diego Cabrera', 'Elena Alonso'),-- pass222
                                                                                                                             ('89012345R', 'Hugo Rivas Herrera', 'hugo.rivas@studywithme.com', '$2a$10$ExDlGqCuxeruv6Lk88U85OkJ7OqMsmk/G7LSmIDaBaQEK1MsWmChO', 'Hugo Herrera', 'Irene Rivas'),-- pass333
                                                                                                                             ('90123456S', 'Sonia Méndez Flores', 'sonia.mendez@studywithme.com', '$2a$10$Hn8Ty9N6A5NFiDJuv4TcCOmyTTrAPAmZr7XnxM0UFlmKpEkU5T/h6', 'Luis Méndez', 'Sonia Flores'),-- pass444
                                                                                                                             ('01234567T', 'Clara Vega Gil', 'clara.vega@studywithme.com', '$2a$10$e6aPHVG3wN5m0nbY3YFDOupNfiS2Q5TME2uRqOiwcHLH51v9iLtHW', 'Ricardo Vega', 'Clara Gil'),-- pass555
                                                                                                                             ('12345678U', 'Sergio Lara Ortiz', 'sergio.lara@studywithme.com', '$2a$10$G4M.A7eF3QQ3WFM24RI8s.yvV1aF1FL2ZHVfPmMiKTGkWad4NO3m6', 'Sergio Ortiz', 'Natalia Lara'),-- pass666
                                                                                                                             ('23456789V', 'Marta Rubio Romero', 'marta.rubio@studywithme.com', '$2a$10$S6lL9IXcl.wTtUuwiAY5UeFtZ6jXrr3.mr5l9T9ZcoZj9/.IDrQyK', 'Jorge Rubio', 'Marta Romero'),-- pass777
                                                                                                                             ('34567890W', 'Elisa Hernández Delgado', 'elisa.hernandez@studywithme.com', '$2a$10$myZfzS4b0H0DtxB6EJh2W.pX/.clz51Qg2q1k13ESyElEmwljx0Fm', 'Manuel Hernández', 'Elisa Delgado'),-- pass888
                                                                                                                             ('45678901X', 'Paula Fuentes Cruz', 'paula.fuentes@studywithme.com', '$2a$10$4bVFpzdd2ka1jR.vIu4NReh71D8u3.GgX.6/Usz7N/oPSosJtv2H2', 'Pedro Fuentes', 'Paula Cruz'),-- pass999

-- 3 DE ESO
                                                                                                                             ('56789012Y', 'Laura García Martínez', 'laura.garcia@studywithme.com', '$2a$10$lUetrKUizQEFHT0hBLAt3eQnIqmmaCNlQ/My5JAquGQk9NZ5vHDFm', 'Antonio García', 'María Martínez'),-- studentpass123
                                                                                                                             ('12345678Q', 'Javier Martínez Rodríguez', 'javier.martinez@studywithme.com', '$2a$10$UDxfRB8x.hn0v18BVa./G.yZHwpqfh.lCFzaxtt2k3Zb2wH6vD1rm', 'José Martínez', 'Ana Rodríguez'),-- alum123pass
                                                                                                                             ('98765432A', 'Sofía Pérez López', 'sofia.perez@studywithme.com', '$2a$10$UUPD1dLteTurLphO4MIwHuY7FI5A2iuqsOeEgJhq1kclpzDARTqIy', 'Francisco Pérez', 'Sara López'),-- student456pass
                                                                                                                             ('34567890T', 'Diego Rodríguez Sánchez', 'diego.rodriguez@studywithme.com', '$2a$10$8Djft9apoqzJZltmzwiYnuMv8xc7Q1fMJ.oKwPo8QFbnj1cPQv9ei', 'Javier Rodríguez', 'María Sánchez'),-- alumpass789
                                                                                                                             ('54321678C', 'Marta López Martínez', 'marta.lopez@studywithme.com', '$2a$10$hm4K1j8g/7dp0Us/tp..p.iqviaBY66/b/C1Gh9LnM8daosTPFife', 'David López', 'Lucía Martínez'),-- studentpass456
                                                                                                                             ('34567890D', 'Raúl Ruiz Fuentes', 'raul.ruiz@studywithme.com', '$2a$10$gUMJ5RLfh.vGz5F0F4vL3OY5ZQzH2HK/W9p04NQKhmxYF0v1N8c0q', 'Pedro Ruiz', 'Paula Fuentes'),-- pass999
                                                                                                                             ('56789012E', 'Elena García Fernández', 'elena.garcia@studywithme.com', '$2a$10$cP3FG8a5JltEN2/IjX3V6OzN8w5MJkTQGnFJ/JHdY4s5aXABhNopW', 'Antonio García', 'María Fernández'),-- studentpass123
                                                                                                                             ('12345678F', 'Luis Martínez López', 'luis.martinez@studywithme.com', '$2a$10$ZJ6W8MEZ29q1/KZ7b4Nmn.MIifGzJNTtG8GQK.B6Bc/tFZQg1ES2u', 'José Martínez', 'Ana López'),-- alum123pass
                                                                                                                             ('98765432G', 'Ana Pérez García', 'ana.perez@studywithme.com', '$2a$10$kTL3P8W6cVItUVfXfQzAmOd6YO3U6kEDDJv6FMseIuU6B1WhNJ/Ei', 'Francisco Pérez', 'Sara García'),-- student456pass
                                                                                                                             ('34567890H', 'Javier Rodríguez Ruiz', 'javier.rodriguez@studywithme.com', '$2a$10$bgZQ1V9e/9Q5WzNZ/hZn/eWnTf2nL5lP.jn9zJrCl.IQ5D9nJbLqK', 'Javier Rodríguez', 'María Ruiz'),-- alumpass789
                                                                                                                             ('54321678I', 'Sara López Fernández', 'sara.lopez@studywithme.com', '$2a$10$aOB9e.3GwlxGAI.vhdfGf.KeBgfI/HRd8V9e0JdYxJ2x6UlB.2JQy', 'David López', 'Lucía Fernández'),-- studentpass456
                                                                                                                             ('34567890J', 'Daniel Santos Gómez', 'daniel.santos@studywithme.com', '$2a$10$Hb8yY5o7HH/YbUj/G5t6YOT2fX/o8/3PtpO7Yd5tE5Xp6TRdFoeDq', 'Carlos Santos', 'Rosa Gómez'),-- pass890
                                                                                                                             ('45678901A', 'Paula Ramírez Soto', 'paula.ramirez@studywithme.com', '$2a$10$0jS/vLobZV27/5CEc8g3BOkR8cJ8dQKX8/DgS1jX7fD3wrEw1EmXK', 'Pedro Ramírez', 'Ana Soto'),-- pass1234
                                                                                                                             ('23456789L', 'Javier Torres Ruiz', 'javier.torres@studywithme.com', '$2a$10$IrZndx5Rlg3WJ1VkGh98VuZNm7gX8Mi0JkPjWoNqg3Y4blLyz.kxW', 'Miguel Torres', 'Lucía Ruiz'),-- password567
                                                                                                                             ('01234567M', 'Laura Santos Gómez', 'laura.santos@studywithme.com', '$2a$10$1Zm1kLfQFdmtA2G71G6B7u2V8rKQe1D9WZPl6vulz8UIMjIxgEDB2', 'Carlos Santos', 'Rosa Gómez'),-- pass890
                                                                                                                             ('34567890P', 'Elena Ortega Ramírez', 'elena.ortega@studywithme.com', '$2a$10$OE1ZkseEnulJh6ysOjWtM.nHCM8BdhoYH8h4hSKaBexNkQYX/tK4S', 'José Ortega', 'Carmen Ramírez'),-- password123
                                                                                                                             ('56789012O', 'Natalia Castro Vega', 'natalia.castro@studywithme.com', '$2a$10$gQK7j9j8X5LdpUTr5IHR7ebMzCfrsL3REJS1W8J9QhP0BvjZ9xPO2', 'Raúl Castro', 'Beatriz Vega'),-- pass456
                                                                                                                             ('12345678P', 'Fernando Delgado Blanco', 'fernando.delgado@studywithme.com', '$2a$10$Yt7gOQFzUj4E7z/8WjHCO.H/OtWUE53k5hH2wG9k8w8EBOf3Z5jyK', 'Miguel Delgado', 'Sonia Blanco'),-- pass789
                                                                                                                             ('23456789Q', 'Daniel Peña Herrera', 'daniel.pena@studywithme.com', '$2a$10$wv3BYPZcHgP9KtY59KkC8ebYaC4Bcv6GgAK5mjmyhC1G1t/VyZl0a', 'Alberto Peña', 'Eva Herrera'),-- pass111
                                                                                                                             ('34567890R', 'Elena Sanchez Alonso', 'elena.sanchez@studywithme.com', '$2a$10$OZPHN5cEpxi7bzi/E50/Zex.EDK7KZ3/B/w6/3LW2t8I/yF42XhuO', 'Diego Cabrera', 'Elena Alonso'),-- pass222

-- 4 DE ESO
                                                                                                                             ('12345678Y', 'Lucía Sánchez Rodríguez', 'lucia.sanchez@studywithme.com', '$2a$10$u81YO8VWqWvoHmNXr2xqKOj5O7y.yqmvaBkM65ftADuLYtk71hhSK', 'Juan Sánchez', 'Sofía Rodríguez'),-- alum789pass
                                                                                                                             ('23456789W', 'David González García', 'david.gonzalez@studywithme.com', '$2a$10$wgSBn/.Jv8IH4LUrc51ERutmWQ6SYF8L6/q.ERlskJKwuSpthZDA6', 'Manuel González', 'Laura García'),-- studentpass789
                                                                                                                             ('34567890K', 'Ana Martínez Gómez', 'ana.martinez@studywithme.com', '$2a$10$TXFEjRPo/S2HVHwnZcEUgu23oIC8k1iMwoKcMDScsBRFYZxKlrHUK', 'Carlos Martínez', 'Marta Gómez'),-- alum666pass
                                                                                                                             ('45678901J', 'Pablo Ruiz Rodríguez', 'pablo.ruiz@studywithme.com', '$2a$10$NXg5IPPFOibqhRLpSDndE.fFX4az9ibCR55NnbqkoBStBIaSKXT/u', 'Francisco Ruiz', 'Elena Rodríguez'),-- studentpass777
                                                                                                                             ('56789012Z', 'Elena Fernández Martínez', 'elena.fernandez@studywithme.com', '$2a$10$LbLYzcPcUyreOrL3pWf34eV5rCNfrzzF58DHh5oReRajUN/PufSue', 'Luis Fernández', 'Sara Martínez'),-- alum888pass
                                                                                                                             ('67890123J', 'Carlos Pedrerol López', 'carlos.pedrerol@studywithme.com', '$2a$10$5KEjTvKSL0oG/vYXLG/FyOSM.pQ0GEMV2LHY5FQ12iz5tpHUBgTW.', 'Pedro Ramírez', 'Ana López'),-- pass1234
                                                                                                                             ('78901234L', 'María Cifuentes Martínez', 'maria.cifuentes@studywithme.com', '$2a$10$1Un6Ut6G3y2TML5evM0j0u.aFujmPRoS2kiNRJYCCt86k61.TW5nS', 'Miguel Torres', 'Sara Martínez'),-- password567
                                                                                                                             ('89012345T', 'Javier Martin Rodríguez', 'javier.martin@studywithme.com', '$2a$10$KJfgNqyyXwO0FnT/LsF/suDePxZLO5Gi6QKBnKD8jLkW3RRNhR3dm', 'Carlos Santos', 'Elena Rodríguez'),-- pass890
                                                                                                                             ('90123456E', 'Natalia Ramirez Fernández', 'natalia.ramirez@studywithme.com', '$2a$10$52LKhGADEJ8LL0Fx8xd5FuS6vw6H0fjLTZygzphE5QfZ0vphHG4Pa', 'José Ortega', 'María Fernández'),-- password123
                                                                                                                             ('01234567A', 'Fernando Sanchez López', 'fernando.sanchez@studywithme.com', '$2a$10$Zfb.1i2rQXRMHyjW1Ko8puV3EXEDCDchdcIvkjkGO4Ix2TY/sy8eW', 'Raúl Castro', 'Lucía López'),-- pass456
                                                                                                                             ('12345678S', 'Laura Gonzalez Rodríguez', 'laura.gonzalez@studywithme.com', '$2a$10$2rxkR.N9iJd3nbxrFPchXObHBnYDJ87.IXKX2/Ev0wdUBghoIrDgu', 'Miguel Delgado', 'Sara Rodríguez'),-- pass789
                                                                                                                             ('23456789D', 'Daniel Martin Martínez', 'daniel.martin@studywithme.com', '$2a$10$J9ndu4Yp5FdxYkciFTFf0u.tCWEs2F4lyL9ecsvdfXlgNDnPIpxyS', 'Alberto Peña', 'Marta Martínez'),-- pass111
                                                                                                                             ('34567890V', 'Elena Martin Fernández', 'elena.martin@studywithme.com', '$2a$10$uwz.ZijgLWXRo8I07B6aUudPv3I53gYXDCt8w/nC.5G1XtV1y8RVi', 'Diego Cabrera', 'Lucía Fernández'),-- pass222
                                                                                                                             ('45678901N', 'Hugo Martin Pérez', 'hugo.martin@studywithme.com', '$2a$10$SOE6zZRlNDvF8Odv1oQIaepj.p8.3dBdfOkAhPjOJl3NdbBFKFC3K', 'Hugo Pérez', 'Elena Rivas'),-- pass333
                                                                                                                             ('56789012V', 'Sonia Martin López', 'sonia.martin@studywithme.com', '$2a$10$cPldf3Dq59v8GdbJbfiRRuBkxh0b2AvuohQ8lCk5QDaZhbyPoJVoW', 'Luis Méndez', 'Lucía López'),-- pass444
                                                                                                                             ('67890123V', 'Clara Martin Martínez', 'clara.martin@studywithme.com', '$2a$10$kTfT1PoNEvEKHDeu2RDtKO6CKgTgDte68jDFtL6l1oe1CQ.9ZxQyO', 'Ricardo Vega', 'Sara Martínez'),-- pass555
                                                                                                                             ('78901234I', 'Sergio Martin Fernández', 'sergio.martin@studywithme.com', '$2a$10$uqWHiMds3/C8H7.VAYmgnOF0ZT9oL64VfU8LGXvc6HCz3R.I6hJke', 'Sergio Fernández', 'Marta Lara'),-- pass666
                                                                                                                             ('89012345Y', 'Marta Martin López', 'marta.martin@studywithme.com', '$2a$10$13nEMTv6W0r2asW/w4rR5OTfFchLttfDTPBbTXJ9H1l5XMW/9K4A2', 'Jorge Rubio', 'Sara López'), -- pass777
                                                                                                                             ('90123456A', 'Elisa Waves Delgado', 'elisa.waves@studywithme.com', '$2a$10$upKkPOHkZf8N/Y5stB6Jdu2pl34Gl9TFUL51UMPKDEkE/rz/MjqWm', 'Manuel Hernández', 'Elisa Delgado'),-- pass888
                                                                                                                             ('01234567X', 'Paula Rodriguez Cruz', 'paula.rodriguez@studywithme.com', '$2a$10$RO3zNUsfVCBc7VC2S1idDul7Be0IXiTdzTEIcTJKUJ6k54bGJjB2i', 'Pedro Fuentes', 'Paula Cruz');-- pass999


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
('Tarea', 'Tarea 1', 'Resolver problemas de suma y resta de números enteros', 1.000),
('Tarea', 'Tarea 2', 'Identificar y clasificar figuras geométricas básicas', 1.000),
('Tarea', 'Tarea 3', 'Realizar un mapa conceptual sobre la célula y sus partes', 1.000),
('Tarea', 'Tarea 4', 'Escribir una redacción sobre las vacaciones de verano', 1.000),
('Tarea', 'Tarea 5', 'Resolver ejercicios de fracciones y decimales', 1.000),
('Tarea', 'Tarea 6', 'Investigar sobre los planetas del sistema solar', 1.000),
('Tarea', 'Tarea 7', 'Crear una presentación sobre la Edad Media', 1.000),
('Tarea', 'Tarea 8', 'Realizar una entrevista a un familiar sobre su infancia', 1.000),
('Tarea', 'Tarea 9', 'Desarrollar un proyecto de reciclaje en casa', 1.000),
('Tarea', 'Tarea 10', 'Leer y resumir un capítulo de un libro de aventuras', 1.000),
('Tarea', 'Tarea 11', 'Resolver problemas de multiplicación y división', 1.000),
('Tarea', 'Tarea 12', 'Realizar un experimento sobre la flotabilidad de objetos', 1.000),
('Tarea', 'Tarea 13', 'Escribir un cuento sobre un héroe imaginario', 1.000),
('Tarea', 'Tarea 14', 'Elaborar un mural sobre los animales en peligro de extinción', 1.000),
('Tarea', 'Tarea 15', 'Resolver ejercicios de medida de longitud, masa y capacidad', 1.000),
('Tarea', 'Tarea 16', 'Investigar sobre los hábitos alimenticios saludables', 1.000),
('Tarea', 'Tarea 17', 'Crear una línea del tiempo de la historia de la tecnología', 1.000),
('Tarea', 'Tarea 18', 'Hacer una maqueta de un volcán en erupción', 1.000),
('Tarea', 'Tarea 19', 'Escribir un poema sobre la naturaleza', 1.000),
('Tarea', 'Tarea 20', 'Resolver problemas de ecuaciones simples', 1.000),
('Tarea', 'Tarea 21', 'Investigar sobre la importancia del agua en el planeta', 1.000),
('Tarea', 'Tarea 22', 'Desarrollar un proyecto de ahorro de energía en casa', 1.000),
('Tarea', 'Tarea 23', 'Leer y comentar un artículo sobre el cambio climático', 1.000),
('Tarea', 'Tarea 24', 'Realizar una presentación sobre la vida en el antiguo Egipto', 1.000),
('Tarea', 'Tarea 25', 'Escribir una carta a un amigo imaginario', 1.000),
('Tarea', 'Tarea 26', 'Resolver problemas de porcentajes y proporciones', 1.000),
('Tarea', 'Tarea 27', 'Investigar sobre los diferentes tipos de ecosistemas', 1.000),
('Tarea', 'Tarea 28', 'Crear una obra de arte utilizando materiales reciclados', 1.000),
('Tarea', 'Tarea 29', 'Realizar un informe sobre la biodiversidad en el jardín de la escuela', 1.000),
('Tarea', 'Tarea 30', 'Escribir una reflexión sobre la importancia de la amistad', 1.000),
('Examen', 'Examen de Matemáticas', 'Evaluación de operaciones básicas, fracciones y decimales', 1.000),
('Examen', 'Examen de Ciencias Naturales', 'Evaluación sobre la célula y los seres vivos', 1.000),
('Examen', 'Examen de Lengua', 'Comprensión lectora y redacción de textos narrativos', 1.000),
('Examen', 'Examen de Historia', 'Evaluación sobre la Prehistoria y las primeras civilizaciones', 1.000),
('Examen', 'Examen de Geografía', 'Evaluación sobre el sistema solar y la Tierra', 1.000),
('Examen', 'Examen de Inglés', 'Evaluación de vocabulario y gramática básica', 1.000),

-- 2 DE ESO
('Tarea', 'Tarea 1', 'Realizar un análisis de un poema de un autor clásico', 2.000),
('Tarea', 'Tarea 2', 'Resolver problemas de álgebra básica', 2.000),
('Tarea', 'Tarea 3', 'Investigar sobre las causas y efectos del calentamiento global', 2.000),
('Tarea', 'Tarea 4', 'Escribir una biografía de un personaje histórico importante', 2.000),
('Tarea', 'Tarea 5', 'Crear una presentación sobre las capas de la Tierra', 2.000),
('Tarea', 'Tarea 6', 'Resolver ejercicios de ecuaciones de primer grado', 2.000),
('Tarea', 'Tarea 7', 'Elaborar un mural sobre los inventos más importantes del siglo XX', 2.000),
('Tarea', 'Tarea 8', 'Escribir un ensayo sobre los derechos humanos', 2.000),
('Tarea', 'Tarea 9', 'Realizar un experimento sobre la fotosíntesis en plantas', 2.000),
('Tarea', 'Tarea 10', 'Leer y resumir una novela de ciencia ficción', 2.000),
('Tarea', 'Tarea 11', 'Resolver problemas de geometría y trigonometría básica', 2.000),
('Tarea', 'Tarea 12', 'Investigar sobre los diferentes tipos de energía renovable', 2.000),
('Tarea', 'Tarea 13', 'Crear una línea del tiempo de la Revolución Industrial', 2.000),
('Tarea', 'Tarea 14', 'Escribir un relato corto sobre un futuro distópico', 2.000),
('Tarea', 'Tarea 15', 'Elaborar un proyecto sobre el ciclo del agua', 2.000),
('Tarea', 'Tarea 16', 'Resolver ejercicios de estadística y probabilidad', 2.000),
('Tarea', 'Tarea 17', 'Investigar sobre la evolución de las especies según Darwin', 2.000),
('Tarea', 'Tarea 18', 'Crear un vídeo documental sobre la biodiversidad marina', 2.000),
('Tarea', 'Tarea 19', 'Escribir una crítica literaria de un libro leído en clase', 2.000),
('Tarea', 'Tarea 20', 'Resolver problemas de funciones y gráficas', 2.000),
('Tarea', 'Tarea 21', 'Investigar sobre los efectos de la contaminación del aire', 2.000),
('Tarea', 'Tarea 22', 'Desarrollar un proyecto de mejora del entorno escolar', 2.000),
('Tarea', 'Tarea 23', 'Leer y comentar un artículo científico sobre la salud', 2.000),
('Tarea', 'Tarea 24', 'Realizar una presentación sobre la historia de la música', 2.000),
('Tarea', 'Tarea 25', 'Escribir una carta formal a una autoridad pública', 2.000),
('Tarea', 'Tarea 26', 'Resolver problemas de interés simple y compuesto', 2.000),
('Tarea', 'Tarea 27', 'Investigar sobre los ecosistemas marinos y su conservación', 2.000),
('Tarea', 'Tarea 28', 'Crear una obra de teatro sobre la convivencia y el respeto', 2.000),
('Tarea', 'Tarea 29', 'Realizar un informe sobre la flora y fauna local', 2.000),
('Tarea', 'Tarea 30', 'Escribir una reflexión sobre la importancia de la educación', 2.000),
('Examen', 'Examen de Matemáticas', 'Evaluación de álgebra básica y ecuaciones de primer grado', 2.000),
('Examen', 'Examen de Ciencias Naturales', 'Evaluación sobre el cuerpo humano y la salud', 2.000),
('Examen', 'Examen de Lengua', 'Análisis de textos y redacción de ensayos', 2.000),
('Examen', 'Examen de Historia', 'Evaluación sobre la Edad Media y el Renacimiento', 2.000),
('Examen', 'Examen de Geografía', 'Evaluación sobre los climas y los paisajes de la Tierra', 2.000),
('Examen', 'Examen de Inglés', 'Evaluación de comprensión lectora y expresión escrita', 2.000),

-- 3 DE ESO
('Tarea', 'Tarea 1', 'Analizar una obra de teatro de Shakespeare', 3.000),
('Tarea', 'Tarea 2', 'Resolver problemas de álgebra avanzada', 3.000),
('Tarea', 'Tarea 3', 'Investigar sobre las causas y consecuencias de la Segunda Guerra Mundial', 3.000),
('Tarea', 'Tarea 4', 'Escribir una monografía sobre un descubrimiento científico', 3.000),
('Tarea', 'Tarea 5', 'Crear una presentación sobre la estructura del ADN', 3.000),
('Tarea', 'Tarea 6', 'Resolver ejercicios de ecuaciones de segundo grado', 3.000),
('Tarea', 'Tarea 7', 'Elaborar un mural sobre los avances tecnológicos del siglo XXI', 3.000),
('Tarea', 'Tarea 8', 'Escribir un ensayo sobre la justicia social', 3.000),
('Tarea', 'Tarea 9', 'Realizar un experimento sobre la ley de la conservación de la masa', 3.000),
('Tarea', 'Tarea 10', 'Leer y resumir una novela histórica', 3.000),
('Tarea', 'Tarea 11', 'Resolver problemas de geometría analítica', 3.000),
('Tarea', 'Tarea 12', 'Investigar sobre los diferentes tipos de energía nuclear', 3.000),
('Tarea', 'Tarea 13', 'Crear una línea del tiempo de la Guerra Fría', 3.000),
('Tarea', 'Tarea 14', 'Escribir un relato corto sobre un viaje en el tiempo', 3.000),
('Tarea', 'Tarea 15', 'Elaborar un proyecto sobre el ciclo del carbono', 3.000),
('Tarea', 'Tarea 16', 'Resolver ejercicios de cálculo diferencial e integral', 3.000),
('Tarea', 'Tarea 17', 'Investigar sobre la teoría de la relatividad de Einstein', 3.000),
('Tarea', 'Tarea 18', 'Crear un vídeo documental sobre la evolución del ser humano', 3.000),
('Tarea', 'Tarea 19', 'Escribir una crítica literaria de un libro clásico', 3.000),
('Tarea', 'Tarea 20', 'Resolver problemas de funciones exponenciales y logarítmicas', 3.000),
('Tarea', 'Tarea 21', 'Investigar sobre los efectos del cambio climático en los océanos', 3.000),
('Tarea', 'Tarea 22', 'Desarrollar un proyecto de sostenibilidad en la comunidad', 3.000),
('Tarea', 'Tarea 23', 'Leer y comentar un artículo científico sobre la biotecnología', 3.000),
('Tarea', 'Tarea 24', 'Realizar una presentación sobre la historia del arte renacentista', 3.000),
('Tarea', 'Tarea 25', 'Escribir una carta formal a una organización internacional', 3.000),
('Tarea', 'Tarea 26', 'Resolver problemas de finanzas personales y presupuestos', 3.000),
('Tarea', 'Tarea 27', 'Investigar sobre los ecosistemas terrestres y su conservación', 3.000),
('Tarea', 'Tarea 28', 'Crear una obra de teatro sobre los derechos humanos', 3.000),
('Tarea', 'Tarea 29', 'Realizar un informe sobre los recursos naturales locales', 3.000),
('Tarea', 'Tarea 30', 'Escribir una reflexión sobre la importancia del voluntariado', 3.000),
('Examen', 'Examen de Matemáticas', 'Evaluación de ecuaciones de segundo grado y funciones', 3.000),
('Examen', 'Examen de Ciencias Naturales', 'Evaluación sobre la evolución de las especies', 3.000),
('Examen', 'Examen de Lengua', 'Análisis de obras literarias y redacción de críticas', 3.000),
('Examen', 'Examen de Historia', 'Evaluación sobre la Revolución Industrial y sus consecuencias', 3.000),
('Examen', 'Examen de Geografía', 'Evaluación sobre la población y la urbanización', 3.000),
('Examen', 'Examen de Inglés', 'Evaluación de análisis de textos y redacción de informes', 3.000),

-- 4 DE ESO
('Tarea', 'Tarea 1', 'Analizar un poema de Pablo Neruda', 4.000),
('Tarea', 'Tarea 2', 'Resolver problemas de álgebra lineal', 4.000),
('Tarea', 'Tarea 3', 'Investigar sobre las causas y efectos de la Primera Guerra Mundial', 4.000),
('Tarea', 'Tarea 4', 'Escribir una tesis sobre un avance médico reciente', 4.000),
('Tarea', 'Tarea 5', 'Crear una presentación sobre las leyes de Newton', 4.000),
('Tarea', 'Tarea 6', 'Resolver ejercicios de sistemas de ecuaciones', 4.000),
('Tarea', 'Tarea 7', 'Elaborar un mural sobre las revoluciones científicas y tecnológicas', 4.000),
('Tarea', 'Tarea 8', 'Escribir un ensayo sobre la igualdad de género', 4.000),
('Tarea', 'Tarea 9', 'Realizar un experimento sobre la ley de la gravitación universal', 4.000),
('Tarea', 'Tarea 10', 'Leer y resumir una novela de realismo mágico', 4.000),
('Tarea', 'Tarea 11', 'Resolver problemas de geometría del espacio', 4.000),
('Tarea', 'Tarea 12', 'Investigar sobre los diferentes tipos de radiación', 4.000),
('Tarea', 'Tarea 13', 'Crear una línea del tiempo de la historia de la computación', 4.000),
('Tarea', 'Tarea 14', 'Escribir un relato corto sobre una utopía futura', 4.000),
('Tarea', 'Tarea 15', 'Elaborar un proyecto sobre el ciclo del nitrógeno', 4.000),
('Tarea', 'Tarea 16', 'Resolver ejercicios de cálculo vectorial', 4.000),
('Tarea', 'Tarea 17', 'Investigar sobre la física cuántica y sus aplicaciones', 4.000),
('Tarea', 'Tarea 18', 'Crear un vídeo documental sobre la historia de la vida en la Tierra', 4.000),
('Tarea', 'Tarea 19', 'Escribir una crítica literaria de una obra contemporánea', 4.000),
('Tarea', 'Tarea 20', 'Resolver problemas de funciones trigonométricas', 4.000),
('Tarea', 'Tarea 21', 'Investigar sobre los impactos ambientales de la deforestación', 4.000),
('Tarea', 'Tarea 22', 'Desarrollar un proyecto de mejora ambiental en la escuela', 4.000),
('Tarea', 'Tarea 23', 'Leer y comentar un artículo científico sobre la genética', 4.000),
('Tarea', 'Tarea 24', 'Realizar una presentación sobre la historia de la filosofía', 4.000),
('Tarea', 'Tarea 25', 'Escribir una carta formal a una organización benéfica', 4.000),
('Tarea', 'Tarea 26', 'Resolver problemas de economía y microeconomía', 4.000),
('Tarea', 'Tarea 27', 'Investigar sobre los ecosistemas urbanos y su sostenibilidad', 4.000),
('Tarea', 'Tarea 28', 'Crear una obra de teatro sobre la paz y la justicia', 4.000),
('Tarea', 'Tarea 29', 'Realizar un informe sobre los desafíos medioambientales globales', 4.000),
('Tarea', 'Tarea 30', 'Escribir una reflexión sobre la importancia del respeto y la tolerancia', 4.000),
('Examen', 'Examen de Matemáticas', 'Evaluación de álgebra avanzada y geometría analítica', 4.000),
('Examen', 'Examen de Ciencias Naturales', 'Evaluación sobre la física y la química básica', 4.000),
('Examen', 'Examen de Lengua', 'Análisis de literatura contemporánea y redacción de ensayos', 4.000),
('Examen', 'Examen de Historia', 'Evaluación sobre las guerras mundiales y la historia contemporánea', 4.000),
('Examen', 'Examen de Geografía', 'Evaluación sobre los problemas medioambientales y su impacto', 4.000),
('Examen', 'Examen de Inglés', 'Evaluación de comprensión y producción de textos argumentativos', 4.000);



select * from tareas;
select * from alumnos;


-- Datos para la tabla alumno_curso
INSERT INTO alumno_curso (id_alumno, id_curso) VALUES
                                                   -- 1 DE ESO
                                                   (1, 1), -- Sara García López en 1 de ESO
                                                   (2, 1), -- Carlos Martínez Sánchez en 1 de ESO
                                                   (3, 1), -- Laura Pérez Martín en 1 de ESO
                                                   (4, 1), -- Sergio Rodríguez Gómez en 1 de ESO
                                                   (5, 1), -- Marta López García en 1 de ESO
                                                   (6, 1),
                                                   (7, 1),
                                                   (8, 1),
                                                   (9, 1),
                                                   (10, 1),
                                                   (11, 1),
                                                   (12, 1),
                                                   (13, 1),
                                                   (14, 1),
                                                   (15, 1),
                                                   (16, 1),
                                                   (17, 1),
                                                   (18, 1),
                                                   (19, 1),
                                                   (20, 1),

                                                   -- 2 DE ESO
                                                   (21, 2), -- Lucía Sánchez Martín en 2 de ESO
                                                   (22, 2), -- David González Gómez en 2 de ESO
                                                   (23, 2), -- Ana Martínez Rodríguez en 2 de ESO
                                                   (24, 2), -- Pablo Ruiz García en 2 de ESO
                                                   (25, 2), -- Elena Fernández López en 2 de ESO
                                                   (26, 2),
                                                   (27, 2),
                                                   (28, 2),
                                                   (29, 2),
                                                   (30, 2),
                                                   (31, 2),
                                                   (32, 2),
                                                   (33, 2),
                                                   (34, 2),
                                                   (35, 2),
                                                   (36, 2),
                                                   (37, 2),
                                                   (38, 2),
                                                   (39, 2),
                                                   (40, 2),


                                                   -- 3 DE ESO
                                                   (41, 3), -- Laura García Martínez en 3 de ESO
                                                   (42, 3), -- Javier Martínez Rodríguez en 3 de ESO
                                                   (43, 3), -- Sofía Pérez López en 3 de ESO
                                                   (44, 3), -- Diego Rodríguez Sánchez en 3 de ESO
                                                   (45, 3), -- Marta López Martínez en 3 de ESO
                                                   (46, 3),
                                                   (47, 3),
                                                   (48, 3),
                                                   (49, 3),
                                                   (50, 3),
                                                   (51, 3),
                                                   (52, 3),
                                                   (53, 3),
                                                   (54, 3),
                                                   (55, 3),
                                                   (56, 3),
                                                   (57, 3),
                                                   (58, 3),
                                                   (59, 3),
                                                   (60, 3),

                                                   -- 4 DE ESO
                                                   (61, 4), -- Lucía Sánchez Rodríguez en 4 de ESO
                                                   (62, 4), -- David González García en 4 de ESO
                                                   (63, 4), -- Ana Martínez Gómez en 4 de ESO
                                                   (64, 4), -- Pablo Ruiz Rodríguez en 4 de ESO
                                                   (65, 4),
                                                   (66, 4),
                                                   (67, 4),
                                                   (68, 4),
                                                   (69, 4),
                                                   (70, 4),
                                                   (71, 4),
                                                   (72, 4),
                                                   (73, 4),
                                                   (74, 4),
                                                   (75, 4),
                                                   (76, 4),
                                                   (77, 4),
                                                   (78, 4),
                                                   (79, 4),
                                                   (80, 4);

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



select * from alumno_asignatura;
-- Insertar datos en tabla alumno_asignatura
INSERT INTO alumno_asignatura (id_alumno, id_asignatura) VALUES
                                                             -- 1 DE ESO
                                                             (1, 1), -- Sara García López en 1 de ESO
                                                             (2, 2), -- Carlos Martínez Sánchez en 1 de ESO
                                                             (3, 3), -- Laura Pérez Martín en 1 de ESO
                                                             (4, 4), -- Sergio Rodríguez Gómez en 1 de ESO
                                                             (5, 5), -- Marta López García en 1 de ESO
                                                             (6, 6),
                                                             (7, 1),
                                                             (8, 2),
                                                             (9, 3),
                                                             (10, 4),
                                                             (11, 5),
                                                             (12, 6),
                                                             (13, 1),
                                                             (14, 2),
                                                             (15, 3),
                                                             (16, 4),
                                                             (17, 5),
                                                             (18, 6),
                                                             (19, 1),
                                                             (20, 2),

                                                             -- 2 DE ESO
                                                             (21, 3), -- Lucía Sánchez Martín en 2 de ESO
                                                             (22, 4), -- David González Gómez en 2 de ESO
                                                             (23, 5), -- Ana Martínez Rodríguez en 2 de ESO
                                                             (24, 6), -- Pablo Ruiz García en 2 de ESO
                                                             (25, 1), -- Elena Fernández López en 2 de ESO
                                                             (26, 2),
                                                             (27, 3),
                                                             (28, 4),
                                                             (29, 5),
                                                             (30, 6),
                                                             (31, 1),
                                                             (32, 2),
                                                             (33, 3),
                                                             (34, 4),
                                                             (35, 5),
                                                             (36, 6),
                                                             (37, 1),
                                                             (38, 2),
                                                             (39, 3),
                                                             (40, 4),


                                                             -- 3 DE ESO
                                                             (41, 5), -- Laura García Martínez en 3 de ESO
                                                             (42, 6), -- Javier Martínez Rodríguez en 3 de ESO
                                                             (43, 1), -- Sofía Pérez López en 3 de ESO
                                                             (44, 2), -- Diego Rodríguez Sánchez en 3 de ESO
                                                             (45, 3), -- Marta López Martínez en 3 de ESO
                                                             (46, 4),
                                                             (47, 5),
                                                             (48, 6),
                                                             (49, 1),
                                                             (50, 2),
                                                             (51, 3),
                                                             (52, 4),
                                                             (53, 5),
                                                             (54, 6),
                                                             (55, 1),
                                                             (56, 2),
                                                             (57, 3),
                                                             (58, 4),
                                                             (59, 5),
                                                             (60, 6),

                                                             -- 4 DE ESO
                                                             (61, 1), -- Lucía Sánchez Rodríguez en 4 de ESO
                                                             (62, 2), -- David González García en 4 de ESO
                                                             (63, 3), -- Ana Martínez Gómez en 4 de ESO
                                                             (64, 4), -- Pablo Ruiz Rodríguez en 4 de ESO
                                                             (65, 5),
                                                             (66, 6),
                                                             (67, 1),
                                                             (68, 2),
                                                             (69, 3),
                                                             (70, 4),
                                                             (71, 5),
                                                             (72, 6),
                                                             (73, 1),
                                                             (74, 2),
                                                             (75, 3),
                                                             (76, 4),
                                                             (77, 5),
                                                             (78, 6),
                                                             (79, 1),
                                                             (80, 2);

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

select * from tareas;

INSERT INTO alumnos_tarea (id_alumno, id_tarea) VALUES
-- Alumno 1
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 32),
(1, 33),
(1, 34),
(1, 35),
(1, 36),

-- Alumno 2
(2, 7),
(2, 8),
(2, 9),
(2, 10),
(2, 11),
(2, 12),
(2, 13),
(2, 37),
(2, 38),
(2, 39),
(2, 40),
(2, 41),
-- Alumno 3
(3, 14),
(3, 15),
(3, 16),
(3, 17),
(3, 18),
(3, 19),
(3, 20),
-- Alumno 4
(4, 21),
(4, 22),
(4, 23),
(4, 24),
(4, 25),
(4, 26),
-- Alumno 5
(5, 27),
(5, 28),
(5, 29),
(5, 30);




-- Tabla de enlace entre profesor - tarea
INSERT INTO profesor_tarea (id_profesor, id_tarea) VALUES
-- Profesor Matemáticas
(1, 1),
(1, 7),
(1, 13),
(1, 25),
(1, 26),
(1, 27),
(1, 28),
(1, 29),
(1, 30),

-- Profesor Lengua
(2, 2),
(2, 8),
(2, 14),
(2, 20),
(2, 31),
(2, 32),
(2, 33),
(2, 34),
(2, 35),
(2, 36),
(2, 37),

-- Profesor Ciencias Naturales
(3, 3),
(3, 9),
(3, 15),
(3, 21),
(3, 38),
(3, 39),
(3, 40),
(3, 41),
(3, 42),
(3, 43),

-- Profesor Historia
(4, 4),
(4, 10),
(4, 16),
(4, 22),
(4, 44),
(4, 45),
(4, 46),
(4, 47),
(4, 48),
(4, 49),

-- Profesor Inglés
(5, 5),
(5, 11),
(5, 17),
(5, 23),
(5, 50),
(5, 51),
(5, 52),
(5, 53),
(5, 54),
(5, 55),

-- Profesor Educación Física
(6, 6),
(6, 12),
(6, 18),
(6, 24),
(6, 56),
(6, 57),
(6, 58),
(6, 59),
(6, 60),
(6, 61);



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
                                                (25, 1),
                                                (26, 1),
                                                (27, 1),
                                                (28, 1),
                                                (29, 1),
                                                (30, 1),
                                                (31, 1),
                                                (32, 1),
                                                (33, 1),
                                                (34, 1),

                                                (7,2),
                                                (8,2),
                                                (9,2),
                                                (10,2),
                                                (11,2),
                                                (12,2),
                                                (35,2),
                                                (36,2),
                                                (37,2),
                                                (38,2),
                                                (39,2),
                                                (40,2),
                                                (41,2),
                                                (42,2),

                                                (13,3),
                                                (14,3),
                                                (43,3),
                                                (44,3),
                                                (45,3),
                                                (46,3),
                                                (47,3),
                                                (48,3),
                                                (49,3),
                                                (50,3),
                                                (51,3),


                                                (20,4),
                                                (21,4),
                                                (22,4),
                                                (23,4),
                                                (24,4),
                                                (52,4),
                                                (53,4),
                                                (54,4),
                                                (55,4),
                                                (56,4),
                                                (57,4),
                                                (58,4),
                                                (59,4);



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
                                                           (24, 24),
                                                           (25, 1),
                                                           (26, 2),
                                                           (27, 3),
                                                           (28, 4),
                                                           (29, 5),
                                                           (30, 6),
                                                           (31, 7),
                                                           (32, 8),
                                                           (33, 9),
                                                           (34, 10),
                                                           (35, 11),
                                                           (36, 12),
                                                           (37, 13),
                                                           (38, 14),
                                                           (39, 15),
                                                           (40, 16),
                                                           (41, 17),
                                                           (42, 18),
                                                           (43, 19),
                                                           (44, 20),
                                                           (45, 21),
                                                           (46, 22),
                                                           (47, 23),
                                                           (48, 24),
                                                           (49, 1),
                                                           (50, 2),
                                                           (51, 3),
                                                           (52, 4),
                                                           (53, 5),
                                                           (54, 6),
                                                           (55, 7),
                                                           (56, 8),
                                                           (57, 9),
                                                           (58, 10),
                                                           (59, 11),
                                                           (60, 12),
                                                           (61, 13),
                                                           (62, 14),
                                                           (63, 15),
                                                           (64, 16),
                                                           (65, 17),
                                                           (66, 18),
                                                           (67, 19),
                                                           (68, 20),
                                                           (69, 21),
                                                           (70, 22),
                                                           (71, 23),
                                                           (72, 24),
                                                           (73, 1),
                                                           (74, 2),
                                                           (75, 3),
                                                           (76, 4),
                                                           (77, 5),
                                                           (78, 6),
                                                           (79, 7),
                                                           (80, 8),
                                                           (81, 9),
                                                           (82, 10),
                                                           (83, 11),
                                                           (84, 12),
                                                           (85, 13),
                                                           (86, 14),
                                                           (87, 15),
                                                           (88, 16),
                                                           (89, 17),
                                                           (90, 18),
                                                           (91, 19),
                                                           (92, 20),
                                                           (93, 21),
                                                           (94, 22),
                                                           (95, 23),
                                                           (96, 24),
                                                           (97, 1),
                                                           (98, 2),
                                                           (99, 3),
                                                           (100, 1),
                                                           (101, 2),
                                                           (102, 3),
                                                           (103, 1),
                                                           (104, 2),
                                                           (105, 3),
                                                           (106, 1),
                                                           (107, 2),
                                                           (108, 3),
                                                           (109, 1),
                                                           (110, 2),
                                                           (111, 3),
                                                           (112, 1),
                                                           (113, 2),
                                                           (114, 3);





-- SELECT * FROM administradores;
-- SELECT * FROM profesores;
-- SELECT * FROM alumnos;
-- SELECT * FROM cursos;
-- SELECT * FROM asignaturas;
-- SELECT * FROM tareas;
-- SELECT * FROM asignatura_curso;
-- SELECT * FROM tarea_curso;
-- SELECT * FROM tarea_asignatura;
-- SELECT * FROM alumnos_tarea;
-- SELECT * FROM profesor_tarea;
-- SELECT * FROM alumno_curso;
-- SELECT * FROM profesor_curso;
-- SELECT * FROM alumno_asignatura;
-- SELECT * FROM profesor_asignatura;


-- SENTENCIA SQL PARA VER TAREAS ASOCIADAS A UN ALUMNO
-- SELECT t.*
-- FROM tareas t
-- JOIN alumnos_tarea at ON t.id_tarea = at.id_tarea
-- JOIN alumnos a ON at.id_alumno = a.id_alumno
-- WHERE a.nif_alumno = '45678901K';


-- SENTENCIA SQL PARA VER ASIGNATURAS ASOCIADAS A UN ALUMNO
-- SELECT a.*
-- FROM asignaturas a
-- JOIN alumno_asignatura aa ON a.id_asignatura = aa.id_asignatura
-- JOIN alumnos al ON aa.id_alumno = al.id_alumno
-- WHERE al.nif_alumno = '45678901K';