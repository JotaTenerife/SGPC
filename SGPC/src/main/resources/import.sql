-- This script initializes the database by creating tables and inserting data
-- Use only for a proof of concept

-- You can use this file to load seed data into the database using SQL statements

-- Roles
INSERT INTO ROLES (rol_id, codigo, descripcion) VALUES (1, 'ADMINISTRACION', 'Administradores del sistema');
INSERT INTO ROLES (rol_id, codigo, descripcion) VALUES (2, 'DOCENTE', 'Personal docente');
INSERT INTO ROLES (rol_id, codigo, descripcion) VALUES (3, 'USUARIO', 'Usuarios del sistema');


-- Permisos
INSERT INTO PERMISOS (permiso_id, codigo, descripcion) VALUES (1, 'MODULO_USUARIOS', 'Permite gestionar los usuarios del sistema');
INSERT INTO PERMISOS (permiso_id, codigo, descripcion) VALUES (2, 'MODULO_ROLES', 'Permite gestionar los roles del sistema y asignarlos');
INSERT INTO PERMISOS (permiso_id, codigo, descripcion) VALUES (3, 'MODULO_PERMISOS', 'Permite visualizar los permisos del sistema y asignarlos');
INSERT INTO PERMISOS (permiso_id, codigo, descripcion) VALUES (4, 'MODULO_TESTS', 'Permite visualizar los test del sistema');
INSERT INTO PERMISOS (permiso_id, codigo, descripcion) VALUES (5, 'MODULO_PREGUNTAS', 'Permite visualizar los test del sistema');

-- Permisos de roles
INSERT INTO ROLES_PERMISOS (rol_id, permiso_id) VALUES (1, 1);
INSERT INTO ROLES_PERMISOS (rol_id, permiso_id) VALUES (1, 2);
INSERT INTO ROLES_PERMISOS (rol_id, permiso_id) VALUES (1, 3);
INSERT INTO ROLES_PERMISOS (rol_id, permiso_id) VALUES (1, 4);
INSERT INTO ROLES_PERMISOS (rol_id, permiso_id) VALUES (1, 5);
INSERT INTO ROLES_PERMISOS (rol_id, permiso_id) VALUES (2, 4);
INSERT INTO ROLES_PERMISOS (rol_id, permiso_id) VALUES (2, 5);

-- Usuarios
INSERT INTO USUARIOS (usuario_id, rol_id, codigo, password, nif_nie, nombre, apellido1, email, fecha_alta) VALUES (1, 1, 'A000000','$2a$10$v2GP5vgdGn5a6TmLchPuvuBPgxfz/T2/gS1TEL0qTsA1l0.hm.dNC', '11111111H', 'Administrador','Smith','a000000@llamalox.com', TO_DATE('2003/05/03', 'yyyy/mm/dd'));
INSERT INTO USUARIOS (usuario_id, rol_id, codigo, password, nif_nie, nombre, apellido1, email, fecha_alta) VALUES (2, 2, 'D000000','$2a$10$v2GP5vgdGn5a6TmLchPuvuBPgxfz/T2/gS1TEL0qTsA1l0.hm.dNC', '22222222J', 'Docente','Smith','d000000@llamalox.com', TO_DATE('2016/10/03', 'yyyy/mm/dd'));
INSERT INTO USUARIOS (usuario_id, rol_id, codigo, password, nif_nie, nombre, apellido1, email, fecha_alta) VALUES (3, 3, 'U000001','$2a$10$v2GP5vgdGn5a6TmLchPuvuBPgxfz/T2/gS1TEL0qTsA1l0.hm.dNC', '33333333P', 'Usuario1','Smith','u000001@llamalox.com', TO_DATE('2016/10/03', 'yyyy/mm/dd'));
INSERT INTO USUARIOS (usuario_id, rol_id, codigo, password, nif_nie, nombre, apellido1, email, fecha_alta) VALUES (4, 3, 'U000002','$2a$10$v2GP5vgdGn5a6TmLchPuvuBPgxfz/T2/gS1TEL0qTsA1l0.hm.dNC', '44444444A', 'Usuario2','Smith','u000002@llamalox.com', TO_DATE('2016/10/03', 'yyyy/mm/dd'));

-- Test
INSERT INTO TESTS (test_id, codigo, nombre, descripcion, oportunidades, fecha_inicio, fecha_fin) VALUES (1, 'TEST001', 'Test 1', 'Test 1', 2, TO_DATE('2016/10/30 00:00:00', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2016/11/06 23:59:59', 'yyyy/mm/dd hh24:mi:ss'));
INSERT INTO TESTS (test_id, codigo, nombre, descripcion, oportunidades, fecha_inicio, fecha_fin) VALUES (2, 'TEST002', 'Test 2', 'Test 2', 2, TO_DATE('2016/11/01 00:00:00', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2016/11/01 23:59:59', 'yyyy/mm/dd hh24:mi:ss'));
INSERT INTO TESTS (test_id, codigo, nombre, descripcion, oportunidades, fecha_inicio, fecha_fin) VALUES (3, 'TEST003', 'Test 3', 'Test 3', 2, TO_DATE('2016/10/20 00:00:00', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2016/10/21 23:59:59', 'yyyy/mm/dd hh24:mi:ss'));

-- Pregunta
INSERT INTO PREGUNTAS (pregunta_id, codigo, texto, imagen) VALUES (1, 'PREG001', 'Pregunta 1', 'https://upload.wikimedia.org/wikipedia/en/thumb/0/01/Atos.svg/800px-Atos.svg.png');
INSERT INTO PREGUNTAS (pregunta_id, codigo, texto, imagen) VALUES (2, 'PREG002', 'Pregunta 2', 'https://upload.wikimedia.org/wikipedia/en/thumb/0/01/Atos.svg/800px-Atos.svg.png');
INSERT INTO PREGUNTAS (pregunta_id, codigo, texto, imagen) VALUES (3, 'PREG003', 'Pregunta 3', 'https://upload.wikimedia.org/wikipedia/en/thumb/0/01/Atos.svg/800px-Atos.svg.png');
INSERT INTO PREGUNTAS (pregunta_id, codigo, texto, imagen) VALUES (4, 'PREG004', 'Pregunta 4', 'https://upload.wikimedia.org/wikipedia/en/thumb/0/01/Atos.svg/800px-Atos.svg.png');
INSERT INTO PREGUNTAS (pregunta_id, codigo, texto, imagen) VALUES (5, 'PREG005', 'Pregunta 5', 'https://upload.wikimedia.org/wikipedia/en/thumb/0/01/Atos.svg/800px-Atos.svg.png');
INSERT INTO PREGUNTAS (pregunta_id, codigo, texto, imagen) VALUES (6, 'PREG006', 'Pregunta 6', 'https://upload.wikimedia.org/wikipedia/en/thumb/0/01/Atos.svg/800px-Atos.svg.png');
INSERT INTO PREGUNTAS (pregunta_id, codigo, texto, imagen) VALUES (7, 'PREG007', 'Pregunta 7', 'https://upload.wikimedia.org/wikipedia/en/thumb/0/01/Atos.svg/800px-Atos.svg.png');

-- Pregunta Test
INSERT INTO TESTS_PREGUNTAS (test_id, pregunta_id) VALUES (1, 1);
INSERT INTO TESTS_PREGUNTAS (test_id, pregunta_id) VALUES (1, 2);
INSERT INTO TESTS_PREGUNTAS (test_id, pregunta_id) VALUES (1, 3);
INSERT INTO TESTS_PREGUNTAS (test_id, pregunta_id) VALUES (2, 3);
INSERT INTO TESTS_PREGUNTAS (test_id, pregunta_id) VALUES (2, 4);
INSERT INTO TESTS_PREGUNTAS (test_id, pregunta_id) VALUES (2, 5);
INSERT INTO TESTS_PREGUNTAS (test_id, pregunta_id) VALUES (3, 5);
INSERT INTO TESTS_PREGUNTAS (test_id, pregunta_id) VALUES (3, 6);
INSERT INTO TESTS_PREGUNTAS (test_id, pregunta_id) VALUES (3, 7);

-- Respuesta
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (1, 1, 'P001R001', 'Respuesta 1', 0);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (2, 1, 'P001R002', 'Respuesta 2', 0);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (3, 1, 'P001R003', 'Respuesta 3', 1);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (4, 2, 'P002R001', 'Respuesta 1', 0);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (5, 2, 'P002R002', 'Respuesta 2', 0);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (6, 2, 'P002R003', 'Respuesta 3', 1);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (7, 3, 'P003R001', 'Respuesta 1', 0);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (8, 3, 'P003R002', 'Respuesta 2', 0);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (9, 3, 'P003R003', 'Respuesta 3', 1);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (10, 4, 'P004R001', 'Respuesta 1', 0);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (11, 4, 'P004R002', 'Respuesta 2', 0);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (12, 4, 'P004R003', 'Respuesta 3', 1);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (13, 5, 'P005R001', 'Respuesta 1', 0);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (14, 5, 'P005R002', 'Respuesta 2', 0);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (15, 5, 'P005R003', 'Respuesta 3', 1);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (16, 6, 'P006R001', 'Respuesta 1', 0);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (17, 6, 'P006R002', 'Respuesta 2', 0);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (18, 6, 'P006R003', 'Respuesta 3', 1);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (19, 7, 'P007R001', 'Respuesta 1', 0);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (20, 7, 'P007R002', 'Respuesta 2', 0);
INSERT INTO RESPUESTAS (respuesta_id, pregunta_id, codigo, texto, correcta) VALUES (21, 7, 'P007R003', 'Respuesta 3', 1);
