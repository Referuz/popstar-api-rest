-- Base de datos para popstar
-- Author: Diego Torres
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
	("Oscar", "Villanueva Gimenes", "4776549632", 3);

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

-- MODICACIONES A LA BASE DE DATOS EN EL DDL
ALTER TABLE empleado DROP COLUMN nombre; 
ALTER TABLE empleado DROP COLUMN apellidos;
ALTER TABLE empleado DROP COLUMN telefono;

-- SE GENERALIZA INFORMACION COMUN ENTRE EMPLEADO Y CLIENTE 
-- 	MEDIANTE LA TABLA NDIVIDO
CREATE TABLE individuo(
	id_individuo INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(45),
    apellidos VARCHAR(80),
    telefono VARCHAR(15)
);

ALTER TABLE empleado ADD id_individuo INT;

ALTER TABLE empleado ADD CONSTRAINT fk_empleado_individuo 
FOREIGN KEY(id_individuo) REFERENCES individuo (id_individuo);

-- SE CREA LA TABLA CLIENTE
CREATE TABLE cliente(
	cuenta VARCHAR(15) PRIMARY KEY UNIQUE,
    domicilio VARCHAR(100),
    id_sucursal INT,
    id_individuo INT
);

ALTER TABLE cliente ADD CONSTRAINT fk_cliente_sucursal
FOREIGN KEY(id_sucursal) REFERENCES sucursal(id_sucursal);

ALTER TABLE cliente ADD CONSTRAINT fk_cliente_individuo
FOREIGN KEY(id_individuo) REFERENCES individuo(id_individuo);

ALTER TABLE empleado ADD usuario VARCHAR(45) UNIQUE,
ADD contrasenia VARCHAR(45) DEFAULT "123", 
ADD email VARCHAR(100);

DELIMITER $$
CREATE FUNCTION crear_cuenta(nombre varchar(50), apellidos varchar(80), id_individuo int)
RETURNS VARCHAR(45)
DETERMINISTIC
BEGIN
	DECLARE v_cuenta VARCHAR(45);
    DECLARE v_fecha_registro DATE;
    SET v_fecha_registro = CURDATE();
    SET v_cuenta = UPPER(
		CONCAT(LEFT(nombre, 1), 
        RIGHT(apellidos, 2), 
        "000", id_individuo,
        MONTH(v_fecha_registro),
        DAY(v_fecha_registro)));
	RETURN v_cuenta;
END $$
DELIMITER ;

-- DROP FUNCTION crear_cuenta;

DELIMITER $$
CREATE PROCEDURE sp_insertCliente(
	IN p_nombre VARCHAR(50), -- 1
    IN p_apellido VARCHAR(100), -- 2
    IN p_telefono VARCHAR(12), -- 3
    IN p_domicilio VARCHAR(100), -- 4 
    IN p_id_sucursal INT, -- 5
    OUT p_cuenta VARCHAR(45),
    OUT p_id_individuo INT
)
BEGIN
	INSERT INTO individuo(nombre, apellidos, telefono)
    VALUES(p_nombre, p_apellido, p_telefono);
    
    SET p_id_individuo = LAST_INSERT_ID();
    SET p_cuenta = crear_cuenta(p_nombre, p_apellido, p_id_individuo);
    
    INSERT INTO cliente(cuenta, domicilio, id_sucursal, id_individuo) 
    VALUES(p_cuenta, p_domicilio, p_id_sucursal, p_id_individuo);

END $$
DELIMITER ;

-- DROP PROCEDURE sp_insertCliente;

CALL sp_insertCliente("Diego", "Torres Infante", "4774561230", "Calle Atargea #205 Arboledas", 2, @cuenta, @id_ind);

select @cuenta cuenta_cliente, @id_ind id_individuo_cliente;


-- ############# INSERCIÓN PARA EMPLEADO ############# 

DELIMITER $$
CREATE PROCEDURE sp_insertEmpleado(
	IN p_nombre VARCHAR(50), -- 1
    IN p_apellido VARCHAR(100), -- 2
    IN p_telefono VARCHAR(12), -- 3 los 3 son de individuo
    
    IN p_id_sucursal INT, -- 4
    IN p_usuario VARCHAR(45), -- 5
    IN p_contrasenia VARCHAR(45), -- 6
    IN p_email VARCHAR(45), -- 7
    
    OUT p_id_individuo INT, -- Antes VARCHAR(45)
    OUT p_numero_empleado INT
)
BEGIN
	INSERT INTO individuo(nombre, apellidos, telefono)
    VALUES(p_nombre, p_apellido, p_telefono);
    
    SET p_id_individuo = LAST_INSERT_ID();
    
    INSERT INTO empleado(id_sucursal, id_individuo, usuario, contrasenia, email) 
    VALUES(p_id_sucursal, p_id_individuo, p_usuario, p_contrasenia, p_email);
    
    SET p_numero_empleado = LAST_INSERT_ID();

END $$
DELIMITER ;

CALL sp_insertEmpleado
	("Said", "Almaraz Ramires", "4772345671", 2, "SaidAlRa35", "ContraseniaPrueba123", "p@mail.com", @id_ind, @numero_empleado);

select @id_ind id_individuo_empleado, @numero_empleado numero_empleado;

ALTER TABLE cliente ADD estado BIT DEFAULT 1;
ALTER TABLE empleado ADD estado BIT DEFAULT 1;

CREATE OR REPLACE VIEW v_empleados AS
    SELECT i.id_individuo, i.nombre, i.apellidos, i.telefono,
    e.numero_empleado, e.usuario, e.email, e.contrasenia,
    s.id_sucursal, s.plaza_comercial, s.domicilio, s.latitud, s.longitud
	FROM empleado e
    INNER JOIN individuo i
    ON e.id_individuo = i.id_individuo
    INNER JOIN sucursal s
    ON s.id_sucursal = e.id_sucursal
    WHERE estado = 1
;

CREATE VIEW v_clientes AS 
	SELECT i.id_individuo, i.nombre, i.apellidos, i.telefono,
    c.domicilio direccion, c.cuenta,
    s.id_sucursal, s.plaza_comercial, s.domicilio, s.latitud, s.longitud
    FROM individuo i
    INNER JOIN cliente c 
    ON i.id_individuo = c.id_individuo
    INNER JOIN sucursal s
    ON s.id_sucursal = c.id_sucursal
    WHERE estado = 1
;

DELIMITER $$
CREATE PROCEDURE sp_deleteCliente(IN v_cuenta VARCHAR(45))
BEGIN
	UPDATE cliente SET estado = 0 WHERE cuenta = v_cuenta;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_deleteEmpleado(IN v_usuario VARCHAR(45))
BEGIN
	UPDATE empleado SET estado = 0 WHERE usuario = v_usuario;
END $$
DELIMITER ;






