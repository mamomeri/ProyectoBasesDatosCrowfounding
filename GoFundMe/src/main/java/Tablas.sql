CREATE DATABASE Crowfounding;
USE Crowfounding;

CREATE TABLE Usuario(
  ID INT PRIMARY KEY,
  nombre VARCHAR(100),
  email VARCHAR(100),
  contraseña VARCHAR(100),
  tarjetaDebito VARCHAR(100),
  ID_tipo_usuario INT,
  ID_campaña INT
);

CREATE TABLE Tipo_Usuario(
  ID INT PRIMARY KEY,
  nombre VARCHAR(100)
);

CREATE TABLE Comentario(
  ID INT PRIMARY KEY,
  fecha DATE,
  contenido VARCHAR(100),
  ID_usuario INT,
  ID_campaña INT
);

CREATE TABLE Campaña(
  ID INT PRIMARY KEY,
  nombre VARCHAR(100),
  historia VARCHAR(100),
  montoObjetivo DOUBLE,
  linkImagen VARCHAR(100),
  ID_ubicacion INT
);

CREATE TABLE Ubicación_Campaña(
  ID INT PRIMARY KEY,
  País VARCHAR(100),
  Ciudad VARCHAR(100)
);

CREATE TABLE Asesor(
  ID INT PRIMARY KEY,
  nombre VARCHAR(100),
  email VARCHAR(100),
  contraseña VARCHAR(100),
  teléfono VARCHAR(100)
);

CREATE TABLE Asesor_Campaña(
  ID INT PRIMARY KEY,
  ID_Estado_legal INT,
  ID_asesor INT,
  ID_campaña INT
);

CREATE TABLE Estado_Legal(
  ID INT PRIMARY KEY,
  nombre VARCHAR(100)
);

CREATE TABLE Donación_Pago(
  ID_donacion INT PRIMARY KEY,
  monto DOUBLE,
  fecha_donacion DATE,
  metodo_pago VARCHAR(100),
  fecha_pago DATE,
  ID_usuario INT,
  ID_campaña INT
);


