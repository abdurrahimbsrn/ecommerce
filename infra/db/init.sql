
CREATE SCHEMA IF NOT EXISTS siparis_schema;
CREATE SCHEMA IF NOT EXISTS kullanici_schema;
CREATE SCHEMA IF NOT EXISTS urun_schema;
CREATE SCHEMA IF NOT EXISTS keycloak_schema;

GRANT ALL ON SCHEMA siparis_schema TO siparis_user;
GRANT ALL ON SCHEMA kullanici_schema TO kullanici_user;
GRANT ALL ON SCHEMA urun_schema TO urun_user;


