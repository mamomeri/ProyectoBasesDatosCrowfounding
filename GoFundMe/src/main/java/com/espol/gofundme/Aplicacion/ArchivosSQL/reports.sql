-- Vista 1: Información de Campaña con Comentarios
CREATE VIEW Vista_CampañaComentarios AS
SELECT
    C.ID AS Campaña_ID,
    C.nombre AS Campaña_Nombre,
    C.historia AS Campaña_Historia,
    C.montoObjetivo AS Campaña_MontoObjetivo,
    U.País AS Campaña_País,
    U.Ciudad AS Campaña_Ciudad,
    COALESCE(COUNT(COM.ID), 0) AS Total_Comentarios
FROM
    Campaña C
JOIN
    Ubicación_Campaña U ON C.ID_ubicacion = U.ID
LEFT JOIN
    Comentario COM ON C.ID = COM.ID_campaña
GROUP BY
    C.ID;
    
SELECT * from Vista_CampañaComentarios;
    
-- Vista 2: Información de Donaciones por Usuario
CREATE VIEW Vista_DonacionesUsuario AS
SELECT
    U.nombre AS Usuario_Nombre,
    U.email AS Usuario_Email,
    D.monto AS Monto_Donación,
    D.fecha_donacion AS Fecha_Donación,
    D.metodo_pago AS Método_Pago,
    C.nombre AS Campaña_Nombre
FROM
    Donación_Pago D
JOIN
    Usuario U ON D.ID_usuario = U.ID
JOIN
    Campaña C ON D.ID_campaña = C.ID;

-- Vista 3: Campañas con Asesores y Estados Legales
CREATE VIEW Vista_CampañasAsesores AS
SELECT
    C.nombre AS Campaña_Nombre,
    A.nombre AS Asesor_Nombre,
    EL.nombre AS Estado_Legal
FROM
    Campaña C
JOIN
    Asesor_Campaña AC ON C.ID = AC.ID_campaña
JOIN
    Asesor A ON AC.ID_asesor = A.ID
JOIN
    Estado_Legal EL ON AC.ID_Estado_legal = EL.ID;


-- Vista 4: Usuarios y Comentarios de una Campaña Específica
CREATE VIEW Vista_UsuariosComentariosCampaña AS
SELECT
    U.nombre AS Usuario_Nombre,
    U.email AS Usuario_Email,
    CO.contenido AS Comentario_Contenido
FROM
    Usuario U
JOIN
    Comentario CO ON U.ID = CO.ID_usuario
WHERE
    CO.ID_campaña = [ID de la Campaña];

