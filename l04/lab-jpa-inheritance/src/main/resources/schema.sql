-- Muss auf dem Klassenpfad liegen, wird ausgeführt falls
-- spring.datasource.initialization-mode=always oder embedded
-- spring.jpa.hibernate.ddl-auto=none (sonst überschreibt generiertes Schema das hier definierte)

CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE topic (
	ID BIGINT NOT NULL PRIMARY KEY,
	DTYPE VARCHAR(31) NOT NULL,
	OWNER VARCHAR(255) NOT NULL,
	TITLE VARCHAR(255) NOT NULL,
	CONTENT VARCHAR(255),
	VALID_UNTIL DATE,
);
