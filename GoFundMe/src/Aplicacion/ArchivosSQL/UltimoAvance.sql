-- Trigger for Donación_Pago table: Set the date automatically on INSERT
DELIMITER //
CREATE TRIGGER tr_set_fecha_donacion
BEFORE INSERT ON Donación_Pago
FOR EACH ROW
BEGIN
    SET NEW.fecha = NOW();
END;
//

-- Trigger for Comentario table: Set the date automatically on INSERT
DELIMITER //
CREATE TRIGGER tr_set_fecha_comentario
BEFORE INSERT ON Comentario
FOR EACH ROW
BEGIN
    SET NEW.fecha = NOW();
END;
//
DELIMITER ;


-- Reportes (Vistas):
-- 1. Vista de Usuarios y sus Campañas:
CREATE VIEW Usuarios_Campañas AS
SELECT 
    Usuario.ID AS ID_Usuario,
    Usuario.nombre AS Nombre_Usuario,
    Campaña.ID AS ID_Campaña,
    Campaña.nombre AS Nombre_Campaña
FROM 
    Usuario JOIN Campaña ON Usuario.ID_campaña = Campaña.ID;


-- 2. Vista de Comentarios por Campaña:
CREATE VIEW Comentarios_Por_Campaña AS
SELECT
    Comentario.ID AS ID_Comentario,
    Comentario.contenido AS Contenido_Comentario,
    Campaña.ID AS ID_Campaña,
    Campaña.nombre AS Nombre_Campaña
FROM 
    Comentario JOIN Campaña ON Comentario.ID_campaña = Campaña.ID;


-- 3. Vista de Donaciones por Campaña:

CREATE VIEW Donaciones_Por_Campaña AS
SELECT 
    Donación_Pago.ID AS ID_Donación,
    Donación_Pago.monto AS Monto_Donación,
    Campaña.ID AS ID_Campaña,
    Campaña.nombre AS Nombre_Campaña
FROM 
    Donación_Pago JOIN Campaña ON Donación_Pago.ID_campaña = Campaña.ID;
    
-- 4. Vista de Usuarios y sus Donaciones:
CREATE VIEW Usuarios_Donaciones AS
SELECT 
    Usuario.ID AS ID_Usuario,
    Usuario.nombre AS Nombre_Usuario,
    Donación_Pago.ID AS ID_Donación,
    Donación_Pago.monto AS Monto_Donación
FROM 
    Usuario JOIN Donación_Pago ON Usuario.ID = Donación_Pago.ID_usuario;
   
   
-- SP
-- 1. SP para insertar datos en la tabla Usuario:
DELIMITER //
CREATE PROCEDURE InsertUsuario(IN nombre_param VARCHAR(100), IN email_param VARCHAR(100), IN contraseña_param VARCHAR(100), 
                               IN tarjetaDebito_param VARCHAR(100), IN ID_tipo_usuario_param INT, IN ID_campaña_param INT)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en Usuario';
    END;
    
    START TRANSACTION;
    IF LOCATE('@', email_param) = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Email no válido';
    ELSE
        INSERT INTO Usuario(nombre, email, contraseña, tarjetaDebito, ID_tipo_usuario, ID_campaña) 
        VALUES (nombre_param, email_param, contraseña_param, tarjetaDebito_param, ID_tipo_usuario_param, ID_campaña_param);
        COMMIT;
    END IF;
END;
//DELIMITER;


-- 2. SP para actualizar datos en la tabla Usuario:
DELIMITER //
CREATE PROCEDURE UpdateUsuario(IN ID_param INT, IN nombre_param VARCHAR(100), IN email_param VARCHAR(100), IN contraseña_param VARCHAR(100), 
                               IN tarjetaDebito_param VARCHAR(100), IN ID_tipo_usuario_param INT, IN ID_campaña_param INT)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al actualizar Usuario';
    END;
    START TRANSACTION;
    IF LOCATE('@', email_param) = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Email no válido';
    ELSE
        UPDATE Usuario
        SET nombre = nombre_param, email = email_param, contraseña = contraseña_param, tarjetaDebito = tarjetaDebito_param,
            ID_tipo_usuario = ID_tipo_usuario_param, ID_campaña = ID_campaña_param
        WHERE ID = ID_param;
        COMMIT;
    END IF;
END;
DELIMITER;

-- 3. SP para eliminar datos de la tabla Usuario:
DELIMITER //
CREATE PROCEDURE DeleteUsuario(IN ID_param INT)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al eliminar de Usuario';
    END;
    
    START TRANSACTION;
    DELETE FROM Usuario WHERE ID = ID_param;
    COMMIT;
END;
DELIMITER;

-- Indexes
CREATE INDEX idx_usuario_email ON Usuario(email);
CREATE INDEX idx_campaña_id ON Campaña(ID);
CREATE INDEX idx_comentario_id_campaña ON Comentario(ID_campaña);
CREATE INDEX idx_donacion_id_campaña ON Donación_Pago(ID_campaña);
CREATE INDEX idx_donacion_id_usuario ON Donación_Pago(ID_usuario);

-- Users and Permissions
-- User admin
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin123';
GRANT ALL PRIVILEGES ON Crowfounding.* TO 'admin'@'localhost';

-- User gestor_campañas
CREATE USER 'gestor_campañas'@'localhost' IDENTIFIED BY 'gestor123';
GRANT INSERT, UPDATE, DELETE ON Crowfounding.Campaña TO 'gestor_campañas'@'localhost';
GRANT SELECT ON Crowfounding.Usuarios_Campañas TO 'gestor_campañas'@'localhost';

-- User lector
CREATE USER 'lector'@'localhost' IDENTIFIED BY 'lector123';
GRANT SELECT ON Crowfounding.* TO 'lector'@'localhost';

-- User gestor_donaciones
CREATE USER 'gestor_donaciones'@'localhost' IDENTIFIED BY 'donaciones123';
GRANT INSERT, UPDATE ON Crowfounding.Donación_Pago TO 'gestor_donaciones'@'localhost';
GRANT EXECUTE ON PROCEDURE Crowfounding.InsertUsuario TO 'gestor_donaciones'@'localhost';

-- User gestor_comentarios
CREATE USER 'gestor_comentarios'@'localhost' IDENTIFIED BY 'comentarios123';
GRANT INSERT ON Crowfounding.Comentario TO 'gestor_comentarios'@'localhost';
GRANT SELECT ON Crowfounding.Comentarios_Por_Campaña TO 'gestor_comentarios'@'localhost';
