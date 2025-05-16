CREATE DATABASE gestion_criptomonedas;
USE gestion_criptomonedas;


CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE wallet (
    id_wallet INT PRIMARY KEY AUTO_INCREMENT,
    alias VARCHAR(50),
    seed_phrase VARCHAR(255) NOT NULL,
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE criptomoneda (
    id_cripto INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50),
    simbolo VARCHAR(10),
    cotizacion_usd DECIMAL(18,6)
);

CREATE TABLE wallet_cripto (
    id_wallet INT,
    id_cripto INT,
    saldo DECIMAL(18,6) NOT NULL DEFAULT 0,
    PRIMARY KEY (id_wallet, id_cripto),
    FOREIGN KEY (id_wallet) REFERENCES wallet(id_wallet),
    FOREIGN KEY (id_cripto) REFERENCES criptomoneda(id_cripto)
);

CREATE TABLE transaccion (
    id_transaccion INT PRIMARY KEY AUTO_INCREMENT,
    tipo ENUM('envio', 'recepcion') NOT NULL,
    monto DECIMAL(18,6) NOT NULL,
    fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('pendiente', 'completada', 'fallida') DEFAULT 'pendiente',
    id_wallet_origen INT,
    id_wallet_destino INT,
    id_cripto INT,
    FOREIGN KEY (id_wallet_origen) REFERENCES wallet(id_wallet),
    FOREIGN KEY (id_wallet_destino) REFERENCES wallet(id_wallet),
    FOREIGN KEY (id_cripto) REFERENCES criptomoneda(id_cripto)
);


INSERT INTO usuario (nombre_usuario, contraseña)
VALUES 
('juan123', 'pass123'),
('maria456', 'pass456');


INSERT INTO wallet (alias, seed_phrase, id_usuario)
VALUES 
('Billetera Juan', 'semilla123', 1),
('Billetera Maria', 'semilla456', 2);

INSERT INTO criptomoneda (nombre, simbolo, cotizacion_usd)
VALUES 
('Bitcoin', 'BTC', 66000.50),
('Ethereum', 'ETH', 3200.75);

INSERT INTO wallet_cripto (id_wallet, id_cripto, saldo)
VALUES 
(1, 1, 0.5),  -- Juan tiene 0.5 BTC
(2, 2, 10);   -- Maria tiene 10 ETH

INSERT INTO transaccion (tipo, monto, estado, id_wallet_origen, id_wallet_destino, id_cripto)
VALUES 
('envio', 0.1, 'completada', 1, 2, 1); -- Juan le envió 0.1 BTC a María

SELECT id_usuario, nombre_usuario, fecha_registro
FROM usuario;

SELECT w.id_wallet, w.alias, u.nombre_usuario
FROM wallet w
JOIN usuario u ON w.id_usuario = u.id_usuario
WHERE u.nombre_usuario = 'juan123';