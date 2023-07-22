
CREATE SCHEMA IF NOT EXISTS `jugueriasemnurvercio2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `jugueriasemnurvercio2`;

CREATE TABLE IF NOT EXISTS `usuarios` (
  `idusuario` INT NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `tipousuario` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `trabajadores` (
  `idtrabajador` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idtrabajador`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `categorias` (
  `idcategoria` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `unidadMedida` CHAR(10) NOT NULL,
  `duracionDias` INT,
  PRIMARY KEY (`idcategoria`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARACTER SET latin1;

/*productos que se compran en el mercado*/
CREATE TABLE IF NOT EXISTS `productos` (
  `idproducto` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` TEXT NOT NULL,
  `idcategoria` INT NOT NULL,
  `precio` DECIMAL(10,2) NOT NULL,
  `stock` FLOAT NOT NULL,
  PRIMARY KEY (`idproducto`),
  FOREIGN KEY (`idcategoria`) REFERENCES `categorias`(`idcategoria`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARACTER SET latin1;

/*productos que se venden*/
CREATE TABLE IF NOT EXISTS `productos_Ventas` (
  `idproducto` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` TEXT NOT NULL,
  `precio` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`idproducto`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARACTER SET latin1;

CREATE TABLE IF NOT EXISTS `compras` (
  `idcompra` INT NOT NULL AUTO_INCREMENT,
  `idproducto` INT NOT NULL,
  `cantidad` FLOAT NOT NULL,
  `precio_unitario` DECIMAL(10,2) NOT NULL,
  `total` DECIMAL(10,2) NOT NULL,
  `fecha` DATE NOT NULL,
  `descripcion` VARCHAR(255),
  PRIMARY KEY (`idcompra`),
  FOREIGN KEY (`idproducto`) REFERENCES `productos`(`idproducto`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARACTER SET latin1;

CREATE TABLE IF NOT EXISTS `productos_comprados` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idcompra` INT NOT NULL,
  `idproducto` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`idcompra`) REFERENCES `compras`(`idcompra`),
  FOREIGN KEY (`idproducto`) REFERENCES `productos`(`idproducto`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARACTER SET latin1;

CREATE TABLE IF NOT EXISTS `receta` (
  `idreceta` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` TEXT NOT NULL,
  `idproductoventa` INT NOT NULL,
  PRIMARY KEY (`idreceta`),
  FOREIGN KEY (`idproductoventa`) REFERENCES `productos_Ventas`(`idproducto`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARACTER SET latin1;

CREATE TABLE IF NOT EXISTS `receta_ingredientes` (
  `idreceta` INT NOT NULL,
  `idproducto` INT NOT NULL,
  `cantidad` FLOAT NOT NULL,
  FOREIGN KEY (`idreceta`) REFERENCES `receta`(`idreceta`),
  FOREIGN KEY (`idproducto`) REFERENCES `productos`(`idproducto`)
) ENGINE = InnoDB DEFAULT CHARACTER SET latin1;

CREATE TABLE IF NOT EXISTS `clientes` (
  `idcliente` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `telefono` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idcliente`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARACTER SET latin1;

CREATE TABLE IF NOT EXISTS `ventas` (
  `idventa` INT NOT NULL AUTO_INCREMENT,
  `idusuario` INT NOT NULL,
  `total` DECIMAL(10,2) NOT NULL,
  `fecha` DATE NOT NULL,
  PRIMARY KEY (`idventa`),
  FOREIGN KEY (`idusuario`) REFERENCES `usuarios`(`idusuario`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARACTER SET latin1;

CREATE TABLE IF NOT EXISTS `detalle_venta`(
  `idventa` INT NOT NULL,
  `idproducto` INT NOT NULL,
  `cantidad` FLOAT NOT NULL,
  FOREIGN KEY (`idventa`) REFERENCES `ventas`(`idventa`),
  FOREIGN KEY (`idproducto`) REFERENCES `productos_Ventas`(`idproducto`)
) ENGINE = InnoDB DEFAULT CHARACTER SET latin1;

CREATE TABLE IF NOT EXISTS `facturas` (
  `idfactura` INT NOT NULL AUTO_INCREMENT,
  `idventa` INT NOT NULL,
  `idcliente` INT NOT NULL,
  `fecha` DATE NOT NULL,
  PRIMARY KEY (`idfactura`),
  FOREIGN KEY (`idventa`) REFERENCES `ventas`(`idventa`),
  FOREIGN KEY (`idcliente`) REFERENCES `clientes`(`idcliente`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARACTER SET latin1;

CREATE TABLE IF NOT EXISTS `corte_caja` (
  `idcorte` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `total_ventas` DECIMAL(10,2) NOT NULL,
  `total_compras` DECIMAL(10,2) NOT NULL,
  `total_efectivo` DECIMAL(10,2) NOT NULL,
  `diferencia` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`idcorte`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARACTER SET latin1;

CREATE TABLE IF NOT EXISTS `informe_mensual` (
  `idinforme` INT NOT NULL AUTO_INCREMENT,
  `mes` INT NOT NULL,
  `anio` INT NOT NULL,
  `total_ventas` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  `total_compras` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  `ganancia` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  `perdida` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`idinforme`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARACTER SET latin1;

CREATE TABLE IF NOT EXISTS `informe_semanal` (
  `idinforme` INT NOT NULL AUTO_INCREMENT,
  `fecha_inicio` DATE NOT NULL,
  `fecha_fin` DATE NOT NULL,
  `total_ventas` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  `total_compras` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  `ganancia` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  `perdida` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`idinforme`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARACTER SET latin1;


CREATE TABLE `perdidas` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `fecha` DATE NOT NULL,
    `producto` INT NOT NULL,
    `cantidad` INT NOT NULL,
    `motivo` VARCHAR(255), 
	`total` DECIMAL(10,2) NOT NULL,
	FOREIGN KEY (`producto`) REFERENCES `productos`(`idproducto`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARACTER SET latin1;


/* */
CREATE TABLE IF NOT EXISTS `gestion` (
  `idgestion` INT NOT NULL AUTO_INCREMENT,
  `idtrabajador` INT,
  `idcorte` INT,
  `idinforme_mensual` INT,
  `idinforme_semanal` INT,
  `nombre_jugueria` VARCHAR(100),
  `ruc` VARCHAR(20),
  `nombre_dueno` VARCHAR(100),
  `descripcion` TEXT,
  PRIMARY KEY (`idgestion`),
  FOREIGN KEY (`idtrabajador`) REFERENCES `trabajadores`(`idtrabajador`),
  FOREIGN KEY (`idcorte`) REFERENCES `corte_caja`(`idcorte`),
  FOREIGN KEY (`idinforme_mensual`) REFERENCES `informe_mensual`(`idinforme`),
  FOREIGN KEY (`idinforme_semanal`) REFERENCES `informe_semanal`(`idinforme`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARACTER SET latin1;


-- Crear un disparador después de insertar una nueva compra en la tabla "compras"
DELIMITER $$
CREATE TRIGGER actualizacion_stock
AFTER INSERT ON compras
FOR EACH ROW
BEGIN
  UPDATE productos
  SET stock = stock + NEW.cantidad
  WHERE idproducto = NEW.idproducto;
END$$
DELIMITER ;

-- Crear un disparador después de insertar una nueva compra en la tabla "compras"
DELIMITER $$
CREATE TRIGGER actualizacion_precio
AFTER INSERT ON compras
FOR EACH ROW
BEGIN
  UPDATE productos
  SET precio = NEW.precio_unitario
  WHERE idproducto = NEW.idproducto;
END$$
DELIMITER ;


-- Crear un disparador para hacer el descuento de productos cada vez que se vende algo
DELIMITER $$
CREATE TRIGGER update_stock AFTER INSERT ON detalle_venta
FOR EACH ROW
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE producto_id INT;
  DECLARE cantidad FLOAT;

  DECLARE ingredientes_cursor CURSOR FOR
    SELECT ri.idproducto, ri.cantidad
    FROM receta_ingredientes ri
    WHERE ri.idreceta = (
      SELECT idproductoventa
      FROM receta
      WHERE idreceta = (
        SELECT idproducto
        FROM productos_Ventas
        WHERE idproducto = NEW.idproducto
      )
    );

  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  OPEN ingredientes_cursor;

  read_loop: LOOP
    FETCH ingredientes_cursor INTO producto_id, cantidad;
    IF done THEN
      LEAVE read_loop;
    END IF;

    -- Realizar el descuento solo si hay suficiente stock disponible
    UPDATE productos
    SET stock = CASE
      WHEN stock >= (cantidad * NEW.cantidad) THEN stock - (cantidad * NEW.cantidad)
      ELSE stock
    END
    WHERE idproducto = producto_id;
  END LOOP;

  CLOSE ingredientes_cursor;
END$$
DELIMITER ;

-- Crear el trigger para insertar los registros en la tabla de productos comprados
DELIMITER //
CREATE TRIGGER `trg_after_compra_insert`
AFTER INSERT ON `compras`
FOR EACH ROW
BEGIN
  DECLARE i INT DEFAULT 1;
  DECLARE total INT DEFAULT NEW.cantidad;

  WHILE i <= total DO
    INSERT INTO `productos_comprados` (`idcompra`, `idproducto`) VALUES (NEW.idcompra, NEW.idproducto);
    SET i = i + 1;
  END WHILE;
END //
DELIMITER ;

-- triger para registrar el total de costo de las compras
DELIMITER //
CREATE TRIGGER calcular_total_compras BEFORE INSERT ON `compras`
FOR EACH ROW
BEGIN
  SET NEW.total = NEW.precio_unitario * NEW.cantidad;
END//
DELIMITER ;

-- triger para registrar el total de costo de las perdidas
DELIMITER //
CREATE TRIGGER calcular_total_perdidas BEFORE INSERT ON `perdidas`
FOR EACH ROW
BEGIN
  SET NEW.total = NEW.cantidad * (SELECT `precio` FROM `productos` WHERE `idproducto` = NEW.producto);
END//
DELIMITER ;

-- -------------------------------------------------------------------------

DELIMITER //
CREATE TRIGGER tr_update_informe_semanal_ventas
AFTER INSERT ON ventas
FOR EACH ROW
BEGIN
    DECLARE semana_inicio DATE;
    DECLARE semana_fin DATE;
    DECLARE informe_id INT;

    SET semana_inicio = DATE_SUB(NEW.fecha, INTERVAL WEEKDAY(NEW.fecha) DAY);
    SET semana_fin = NEW.fecha;

    SELECT idinforme INTO informe_id
    FROM informe_semanal
    WHERE fecha_inicio = semana_inicio AND fecha_fin = semana_fin;

    IF informe_id IS NULL THEN
        INSERT INTO informe_semanal (fecha_inicio, fecha_fin, total_ventas, total_compras, ganancia, perdida)
        VALUES (semana_inicio, semana_fin, 0, 0, 0, 0);
        SET informe_id = LAST_INSERT_ID();
    END IF;

    UPDATE informe_semanal
    SET total_ventas = (SELECT COALESCE(SUM(total), 0) FROM ventas WHERE fecha BETWEEN semana_inicio AND semana_fin),
        total_compras = (SELECT COALESCE(SUM(total), 0) FROM compras WHERE fecha BETWEEN semana_inicio AND semana_fin),
        perdida = (SELECT COALESCE(SUM(total), 0) FROM perdidas WHERE fecha BETWEEN semana_inicio AND semana_fin),
        ganancia = (SELECT COALESCE(SUM(total), 0) FROM ventas WHERE fecha BETWEEN semana_inicio AND semana_fin) - 
                   (SELECT COALESCE(SUM(total), 0) FROM compras WHERE fecha BETWEEN semana_inicio AND semana_fin) -
                   (SELECT COALESCE(SUM(total), 0) FROM perdidas WHERE fecha BETWEEN semana_inicio AND semana_fin)
    WHERE idinforme = informe_id;
END//
DELIMITER ;


DELIMITER //
CREATE TRIGGER tr_update_informe_mensual_ventas
AFTER INSERT ON ventas
FOR EACH ROW
BEGIN
    DECLARE mes_actual INT;
    DECLARE anio_actual INT;
    DECLARE informe_id INT;

    SET mes_actual = MONTH(NEW.fecha);
    SET anio_actual = YEAR(NEW.fecha);

    SELECT idinforme INTO informe_id
    FROM informe_mensual
    WHERE mes = mes_actual AND anio = anio_actual;

    IF informe_id IS NULL THEN
        INSERT INTO informe_mensual (mes, anio, total_ventas, total_compras, ganancia, perdida)
        VALUES (mes_actual, anio_actual, 0, 0, 0, 0);
        SET informe_id = LAST_INSERT_ID();
    END IF;

    UPDATE informe_mensual
    SET total_ventas = (SELECT COALESCE(SUM(total), 0) FROM ventas WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual),
        total_compras = (SELECT COALESCE(SUM(total), 0) FROM compras WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual),
        perdida = (SELECT COALESCE(SUM(total), 0) FROM perdidas WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual),
        ganancia = (SELECT COALESCE(SUM(total), 0) FROM ventas WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual) - 
                   (SELECT COALESCE(SUM(total), 0) FROM compras WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual) -
                   (SELECT COALESCE(SUM(total), 0) FROM perdidas WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual)
    WHERE idinforme = informe_id;
END//
DELIMITER ;


-- Compras

DELIMITER //
CREATE TRIGGER tr_update_informe_semanal_compras
AFTER INSERT ON compras
FOR EACH ROW
BEGIN
    DECLARE semana_inicio DATE;
    DECLARE semana_fin DATE;
    DECLARE informe_id INT;

    SET semana_inicio = DATE_SUB(NEW.fecha, INTERVAL WEEKDAY(NEW.fecha) DAY);
    SET semana_fin = NEW.fecha;

    SELECT idinforme INTO informe_id
    FROM informe_semanal
    WHERE fecha_inicio = semana_inicio AND fecha_fin = semana_fin;

    IF informe_id IS NULL THEN
        INSERT INTO informe_semanal (fecha_inicio, fecha_fin, total_ventas, total_compras, ganancia, perdida)
        VALUES (semana_inicio, semana_fin, 0, 0, 0, 0);
        SET informe_id = LAST_INSERT_ID();
    END IF;

    UPDATE informe_semanal
    SET total_ventas = (SELECT COALESCE(SUM(total), 0) FROM ventas WHERE fecha BETWEEN semana_inicio AND semana_fin),
        total_compras = (SELECT COALESCE(SUM(total), 0) FROM compras WHERE fecha BETWEEN semana_inicio AND semana_fin),
        perdida = (SELECT COALESCE(SUM(total), 0) FROM perdidas WHERE fecha BETWEEN semana_inicio AND semana_fin),
        ganancia = (SELECT COALESCE(SUM(total), 0) FROM ventas WHERE fecha BETWEEN semana_inicio AND semana_fin) - 
                   (SELECT COALESCE(SUM(total), 0) FROM compras WHERE fecha BETWEEN semana_inicio AND semana_fin) -
                   (SELECT COALESCE(SUM(total), 0) FROM perdidas WHERE fecha BETWEEN semana_inicio AND semana_fin)
    WHERE idinforme = informe_id;
END//
DELIMITER ;


DELIMITER //
CREATE TRIGGER tr_update_informe_mensual_compras
AFTER INSERT ON compras
FOR EACH ROW
BEGIN
    DECLARE mes_actual INT;
    DECLARE anio_actual INT;
    DECLARE informe_id INT;

    SET mes_actual = MONTH(NEW.fecha);
    SET anio_actual = YEAR(NEW.fecha);

    SELECT idinforme INTO informe_id
    FROM informe_mensual
    WHERE mes = mes_actual AND anio = anio_actual;

    IF informe_id IS NULL THEN
        INSERT INTO informe_mensual (mes, anio, total_ventas, total_compras, ganancia, perdida)
        VALUES (mes_actual, anio_actual, 0, 0, 0, 0);
        SET informe_id = LAST_INSERT_ID();
    END IF;

    UPDATE informe_mensual
    SET total_ventas = (SELECT COALESCE(SUM(total), 0) FROM ventas WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual),
        total_compras = (SELECT COALESCE(SUM(total), 0) FROM compras WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual),
        perdida = (SELECT COALESCE(SUM(total), 0) FROM perdidas WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual),
        ganancia = (SELECT COALESCE(SUM(total), 0) FROM ventas WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual) - 
                   (SELECT COALESCE(SUM(total), 0) FROM compras WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual) -
                   (SELECT COALESCE(SUM(total), 0) FROM perdidas WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual)
    WHERE idinforme = informe_id;
END//
DELIMITER ;

-- Perdidas

DELIMITER //
CREATE TRIGGER tr_update_informe_semanal_perdidas
AFTER INSERT ON perdidas
FOR EACH ROW
BEGIN
    DECLARE semana_inicio DATE;
    DECLARE semana_fin DATE;
    DECLARE informe_id INT;

    SET semana_inicio = DATE_SUB(NEW.fecha, INTERVAL WEEKDAY(NEW.fecha) DAY);
    SET semana_fin = NEW.fecha;

    SELECT idinforme INTO informe_id
    FROM informe_semanal
    WHERE fecha_inicio = semana_inicio AND fecha_fin = semana_fin;

    IF informe_id IS NULL THEN
        INSERT INTO informe_semanal (fecha_inicio, fecha_fin, total_ventas, total_compras, ganancia, perdida)
        VALUES (semana_inicio, semana_fin, 0, 0, 0, 0);
        SET informe_id = LAST_INSERT_ID();
    END IF;

    UPDATE informe_semanal
    SET total_ventas = (SELECT COALESCE(SUM(total), 0) FROM ventas WHERE fecha BETWEEN semana_inicio AND semana_fin),
        total_compras = (SELECT COALESCE(SUM(total), 0) FROM compras WHERE fecha BETWEEN semana_inicio AND semana_fin),
        perdida = (SELECT COALESCE(SUM(total), 0) FROM perdidas WHERE fecha BETWEEN semana_inicio AND semana_fin),
        ganancia = (SELECT COALESCE(SUM(total), 0) FROM ventas WHERE fecha BETWEEN semana_inicio AND semana_fin) - 
                   (SELECT COALESCE(SUM(total), 0) FROM compras WHERE fecha BETWEEN semana_inicio AND semana_fin) -
                   (SELECT COALESCE(SUM(total), 0) FROM perdidas WHERE fecha BETWEEN semana_inicio AND semana_fin)
    WHERE idinforme = informe_id;
END//
DELIMITER ;


DELIMITER //
CREATE TRIGGER tr_update_informe_mensual_perdidas
AFTER INSERT ON perdidas
FOR EACH ROW
BEGIN
    DECLARE mes_actual INT;
    DECLARE anio_actual INT;
    DECLARE informe_id INT;

    SET mes_actual = MONTH(NEW.fecha);
    SET anio_actual = YEAR(NEW.fecha);

    SELECT idinforme INTO informe_id
    FROM informe_mensual
    WHERE mes = mes_actual AND anio = anio_actual;

    IF informe_id IS NULL THEN
        INSERT INTO informe_mensual (mes, anio, total_ventas, total_compras, ganancia, perdida)
        VALUES (mes_actual, anio_actual, 0, 0, 0, 0);
        SET informe_id = LAST_INSERT_ID();
    END IF;

    UPDATE informe_mensual
    SET total_ventas = (SELECT COALESCE(SUM(total), 0) FROM ventas WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual),
        total_compras = (SELECT COALESCE(SUM(total), 0) FROM compras WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual),
        perdida = (SELECT COALESCE(SUM(total), 0) FROM perdidas WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual),
        ganancia = (SELECT COALESCE(SUM(total), 0) FROM ventas WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual) - 
                   (SELECT COALESCE(SUM(total), 0) FROM compras WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual) -
                   (SELECT COALESCE(SUM(total), 0) FROM perdidas WHERE MONTH(fecha) = mes_actual AND YEAR(fecha) = anio_actual)
    WHERE idinforme = informe_id;
END//
DELIMITER ;

