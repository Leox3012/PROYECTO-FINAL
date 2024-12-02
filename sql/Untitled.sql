CREATE TABLE eventos (
    id_evento SERIAL PRIMARY KEY,
    nombre_evento VARCHAR(100),
    descripcion TEXT,
    fecha_evento DATE,
    activo BOOLEAN DEFAULT TRUE
);


CREATE TABLE reservas (
    id_reserva SERIAL PRIMARY KEY,
    id_evento INT REFERENCES eventos(id_evento),
    nombre_cliente VARCHAR(100),
    cedula_cliente VARCHAR(20),
    fecha_reserva TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO eventos (nombre_evento, descripcion, fecha_evento, activo)
VALUES 
    ('Noche de Techno', 'Un evento con los mejores Dj´s del pais', '2024-12-30', TRUE),
    ('Charla de Tecnología', 'Evento educativo sobre innovación', '2024-12-1', TRUE),
    ('Concierto de Rock', 'Un concierto con las mejores bandas', '2024-11-30', FALSE);


    INSERT INTO reservas (nombre_cliente, cedula_cliente, id_evento) 
VALUES ('Leonardo Valencia', '1192807115', 1);

INSERT INTO reservas (nombre_cliente, cedula_cliente, id_evento) 
VALUES ('Jose Carludo', '1189374907', 2);

INSERT INTO reservas (nombre_cliente, cedula_cliente, id_evento) 
VALUES ('Juan Pérez', '1097623840', 3);

INSERT INTO reservas (nombre_cliente, cedula_cliente, id_evento) 
VALUES ('María López', '8574689001', 4);

INSERT INTO reservas (nombre_cliente, cedula_cliente, id_evento) 
VALUES ('Nicol Villa', '9920193648', 5);

INSERT INTO reservas (nombre_cliente, cedula_cliente, id_evento) 
VALUES ('Maria Jose Gallego', '1736458960', 6);

INSERT INTO reservas (nombre_cliente, cedula_cliente, id_evento) 
VALUES ('Alejandra Valencia', '11029384756', 7);

INSERT INTO reservas (nombre_cliente, cedula_cliente, id_evento) 
VALUES ('Rafael Osorio', '1102938478', 8);


SELECT r.id_reserva, r.nombre_cliente, r.cedula_cliente, e.nombre AS evento
FROM reservas r
JOIN eventos e ON r.id_evento = e.id_evento
WHERE r.cedula_cliente = '123456789';
