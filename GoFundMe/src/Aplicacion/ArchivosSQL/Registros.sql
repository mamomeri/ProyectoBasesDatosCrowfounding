use crowfounding;

-- Usuario (ID, nombre, email, contraseña, tarjetaDebito, ID_tipo_usuario, ID_campaña)
-- (1, 'Jorge Escobar', 'JorgeEs@gmail.com', 'MAgno125', '4541-2393-4817-1242', 1, null)

-- Tipo_Usuario (ID, nombre)
-- (1, 'Donante')

-- Comentario (ID, fecha, contenido, ID_usuario, ID_campaña)
-- (1, '2023-05-14', '¡Buena campaña!', 1, 102)

-- Campaña (ID, nombre, historia, montoObjetivo, linkImagen, ID_ubicacion)
-- (101, 'Protección animal', 'Rescatemos a los animales en peligro', 2000.00, 'https://crowdfounding.com/imagen1.jpg', 1)

-- Ubicación_Campaña (ID, País, Ciudad)
-- (1, 'Italia', 'Milan'),

--Asesor (ID, nombre, email, contraseña, teléfono)
-- (1, 'Allan', 'Allan@founding.com', 'asesor1pass', '123-456-7890'),

--Asesor_Campaña (ID, ID_Estado_legal, ID_asesor, ID_campaña)
-- (1, 1, 1, 101),


-- Estado_Legal (ID, nombre)
-- (1, 'En Proceso'),

-- Donación_Pago (ID_donacion, monto, fecha_donacion, metodo_pago, fecha_pago, ID_usuario, ID_campaña)
-- (1, 5.00, '2023-05-14', 'Tarjeta', '2023-05-30', 1, 102),


INSERT INTO Usuario (ID, nombre, email, contraseña, tarjetaDebito, ID_tipo_usuario, ID_campaña)
VALUES
(1, 'Jorge Escobar', 'JorgeEs@gmail.com', 'MAgno125', '4541-2393-4817-1242', 1, null),
(2, 'Alberto Mississippi', 'Alber_missi@outlook.com', 'ILYDIMP', '4101-2050-8302-0045', 2, 101),
(3, 'Jonathan Schules', 'Jona.Hex@outlook.com', 'RNGPG', '4829-8366-4864-4985', 1, null),
(4, 'Aaron Maldini', 'Aaron.123Maldi@gmail.com', 'BlackSlather', '4738-4393-0339-8433', 1, null),
(5, 'Andrea Escobar', 'GG.OP@outlook.com', 'Flutter12021', '4042-4951-4115-3464', 1, null),
(6, 'Ericka Biller', 'Mymail@gmail.com', 'PeanutT55', '1804-6507-4135-4163', 1, null),
(7, 'Calvin Candy', 'Calvin_Cand@gmail.com', 'Muggy78*', '4747-8063-1775-9017', 1, null),
(8, 'Django Hudes', 'Hude_am@outlook.com', 'TIJFTG456', '4441-9713-1415-7315', 2, 102),
(9, 'John Peñafiel', 'John1.now@outlook.com', 'UWFMME4', '4701-2677-1291-6690', 1, null),
(10, 'Elena Tarantino', 'Tarani_He@gmail.com', 'UWMMIG789', '6495-4087-8149-1562', 1, null);


INSERT INTO Tipo_Usuario (ID, nombre)
VALUES
(1, 'Donante'),
(2, 'Organizador');


INSERT INTO Comentario (ID, fecha, contenido, ID_usuario, ID_campaña)
VALUES
(1, '2023-05-14', '¡Buena campaña!', 1, 102),
(2, '2023-06-11', 'Ánimo, apoyando desde el inicio', 5, 101),
(3, '2023-04-21', '¡Excelente iniciativa!', 6, 101),
(4, '2023-04-05', 'Les deseo mucho éxito', 7, 101),
(5, '2023-05-19', '¡Sigan Adelante!', 9, 102),
(6, '2023-07-17', 'Espero que logren su meta', 10, 101),
(7, '2023-06-22', 'A Darle', 1, 101),
(8, '2023-06-10', 'Apoyando desde el corazón', 4, 102),
(9, '2023-04-29', '¡Vamos por el objetivo!', 6, 102),
(10, '2023-04-17', 'Cuenten con mi apoyo', 10, 101);


INSERT INTO Campaña (ID, nombre, historia, montoObjetivo, linkImagen, ID_ubicacion)
VALUES
(101, 'Protección animal', 'Rescatemos a los animales en peligro', 2000.00, 'https://crowdfounding.com/imagen1.jpg', 1),
(102, 'Becas universitarias', 'Oportunidades para jóvenes talentos', 3500.00, 'https://crowdfounding.com/imagen2.jpg', 2);



INSERT INTO Ubicación_Campaña (ID, País, Ciudad)
VALUES
(1, 'Italia', 'Milan'),
(2, 'China', 'PEkín');


INSERT INTO Asesor (ID, nombre, email, contraseña, teléfono)
VALUES
(1, 'Allan', 'Allan@founding.com', 'asesor1pass', '123-456-7890'),
(2, 'Miguel', 'Miguel@founding.com', 'asesor2pass', '987-654-3210'),
(3, 'Samuel', 'Samuel@founding.com', 'asesor3pass', '111-222-3333');


INSERT INTO Asesor_Campaña (ID, ID_Estado_legal, ID_asesor, ID_campaña)
VALUES
(1, 1, 1, 101),
(2, 2, 2, 102);


INSERT INTO Estado_Legal (ID, nombre)
VALUES
(1, 'En Proceso'),
(2, 'Aprobada'),
(3, 'Rechazada');


INSERT INTO Donación_Pago (ID_donacion, monto, fecha_donacion, metodo_pago, fecha_pago, ID_usuario, ID_campaña)
VALUES
(1, 5.00, '2023-05-14', 'Tarjeta', '2023-05-30', 1, 102),
(2, 100.00, '2023-06-11', 'PayPal', '2023-06-30', 5, 101),
(3, 15.00, '2023-04-21', 'Tarjeta', '2023-05-03', 6, 101),
(4, 35.00, '2023-04-05', 'Paypal', '2023-04-30', 7, 101),
(5, 50.00, '2023-05-19', 'Tarjeta', '2023-06-03', 9, 102),
(6, 25.00, '2023-07-17', 'Tarjeta', '2023-08-02', 10, 101),
(7, 10.00, '2023-06-22', 'Paypal', '2023-07-05', 1, 101),
(8, 5.00, '2023-06-10', 'Tarjeta', '2023-06-30', 4, 102),
(9, 60.00, '2023-04-29', 'Paypal', '2023-05-5', 6, 102),
(10, 45.00, '2023-04-17', 'efectivo', '2023-04-17', 10, 101);


