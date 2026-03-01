-- Base de datos para popstar
-- Author: Referuz
-- Fecha: 25-02-2026
-- Version: 1.0
 
-- DDL
DROP DATABASE IF EXISTS db_popstar;
CREATE DATABASE db_popstar;
USE db_popstar;
 
CREATE TABLE empleado(
	numero_empleado INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50),
    apellidos VARCHAR(100),
    telefono VARCHAR(12),
	id_sucursal INT
);
 
CREATE TABLE sucursal(
	id_sucursal INT PRIMARY KEY AUTO_INCREMENT,
    domicilio VARCHAR(200),
    plaza_comercial VARCHAR(50),
    latitud DOUBLE,
    longitud DOUBLE
);
 
ALTER  TABLE empleado 
ADD CONSTRAINT fk_empleado_sucursal
FOREIGN KEY(id_sucursal) REFERENCES sucursal(id_sucursal);
 
-- describe table empleado;
-- select * from empleado;
 
INSERT INTO 
sucursal(domicilio, plaza_comercial, latitud, longitud) 
VALUES("Blvd. Adolfo López Mateos 2013, Los Saldos, 37170 León","Sucursal Insurgentes", 21.1495, -101.7088),
	("Calle Francisco I. Madero 802, Centro, 37000 León","Plaza de La Calzada", 21.1215, -101.6742),
	("Zona Recreativa y Cultural (Instalaciones de la Feria), 37500 León de los Aldama, Gto., México","Feria", 21.1138405, -101.6565569),
	("Calle ficticia 000, Fueras, 12345 León","Utl", 21.065138844935014, -101.5814386511005);
 
INSERT INTO 
empleado(nombre, apellidos, telefono, id_sucursal) 
VALUES("Sebastian", "Torres Araujo","4771221231", 1),
	("Estrella", "Yebra Sanches", "4777894562", 2),
	("Romina", "Ramires Hernandez", "4778951596", 2),
	("Said", "Rodriguez Gonzales", "4779637415", 1),
	("Oscar", "Villanueva Gimenes", "4776549632", 3); -- 2
 
CREATE VIEW v_sucursales AS(
	SELECT * FROM sucursal
);
 
CREATE VIEW v_empleados AS(
	SELECT e.numero_empleado, e.nombre, e.apellidos, e.telefono, 
		s.id_sucursal, s.domicilio, s.plaza_comercial, s.latitud, s.longitud
    FROM empleado e
	INNER JOIN sucursal s
    ON e.id_sucursal = s.id_sucursal
);
 
-- select * from v_empleados;

INSERT INTO 
empleado(nombre, apellidos, telefono, id_sucursal) 
VALUES("Diego", "Torres Infante","4771111111", 3);

-- use db_popstar;
-- select * from empleado;
-- delete from empleado ;
 
 