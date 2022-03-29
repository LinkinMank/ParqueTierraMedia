CREATE TABLE "Usuario" (
	"id"	INTEGER,
	"nombre"	TEXT NOT NULL UNIQUE,
	"contraseña"	TEXT NOT NULL UNIQUE,
	"dinero"	INTEGER NOT NULL CHECK("dinero" >= 0),
	"tiempo"	REAL NOT NULL CHECK("tiempo" >= 0),
	PRIMARY KEY("id" AUTOINCREMENT)
);

INSERT INTO "Usuario" VALUES (1,'Eowyn','Eowyn',10,8.0);
INSERT INTO "Usuario" VALUES (2,'Gandalf','Gandalf',100,5.0);
INSERT INTO "Usuario" VALUES (3,'Sam','Sam',36,8.0);
INSERT INTO "Usuario" VALUES (4,'Galadriel','Galadriel',120,6.0);

SELECT * FROM Usuario WHERE Usuario.nombre = 'Sam' AND Usuario.password = 'Sam';