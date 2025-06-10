CREATE TABLE usuarios (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          login VARCHAR(255) UNIQUE NOT NULL,
                          senha VARCHAR(255) NOT NULL
);

INSERT INTO usuarios (login, senha) VALUES (
                                               'admin',
                                               '$2a$10$E/sCRAHSHy/AOWZBmBlVXuHQIB59r0eOOrIMWss1bQXrsl1cdun1i'
                                           );