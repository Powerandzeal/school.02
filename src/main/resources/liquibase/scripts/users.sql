-- liquibase formatted sql

-- changeset sergeyPanin:3
CREATE INDEX searchByName ON student (name);
--
-- changeset sergeyPanin:4
CREATE INDEX searchByFaculty ON faculty (color);
