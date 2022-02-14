BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Atraccion" (
	"id"	INTEGER,
	"nombre"	TEXT NOT NULL UNIQUE,
	"costo"	INTEGER NOT NULL CHECK("costo" > 0),
	"tiempo"	REAL NOT NULL CHECK("tiempo" > 0),
	"cupo"	INTEGER NOT NULL CHECK("cupo" >= 0),
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Usuario" (
	"id"	INTEGER,
	"nombre"	TEXT NOT NULL UNIQUE,
	"dinero"	INTEGER NOT NULL CHECK("dinero" >= 0),
	"tiempo"	REAL NOT NULL CHECK("tiempo" >= 0),
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Promocion" (
	"id"	INTEGER,
	"nombre"	TEXT NOT NULL UNIQUE,
	"tipo"	TEXT NOT NULL,
	"descuento"	REAL CHECK("descuento" > 0),
	"costo"	INTEGER CHECK("costo" > 0),
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "promocion_atraccion" (
	"id"	INTEGER,
	"atraccion_id"	INTEGER NOT NULL,
	"promocion_id"	INTEGER NOT NULL,
	FOREIGN KEY("atraccion_id") REFERENCES "Atraccion"("id"),
	FOREIGN KEY("promocion_id") REFERENCES "Promocion"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "promoGratuita_axb" (
	"id"	INTEGER,
	"atraccion_id"	INTEGER,
	"promocion_id"	INTEGER,
	FOREIGN KEY("promocion_id") REFERENCES "Promocion"("id"),
	FOREIGN KEY("atraccion_id") REFERENCES "Atraccion"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Itinerario" (
	"id"	INTEGER,
	"usuario_id"	INTEGER NOT NULL UNIQUE,
	"promocion_id"	TEXT,
	"atraccion_id"	TEXT,
	FOREIGN KEY("usuario_id") REFERENCES "Usuario"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
INSERT INTO "Atraccion" VALUES (1,'Moria',10,2.0,6);
INSERT INTO "Atraccion" VALUES (2,'Minas Tirith',5,2.5,25);
INSERT INTO "Atraccion" VALUES (3,'La Comarca',3,6.5,150);
INSERT INTO "Atraccion" VALUES (4,'Mordor',25,3.0,4);
INSERT INTO "Atraccion" VALUES (5,'Abismo de Helm',5,2.0,15);
INSERT INTO "Atraccion" VALUES (6,'Lothlorien',35,1.0,30);
INSERT INTO "Atraccion" VALUES (7,'Erebor',12,3.0,32);
INSERT INTO "Atraccion" VALUES (8,'Bosque Negro',3,4.0,12);
INSERT INTO "Usuario" VALUES (1,'Eowyn',10,8.0);
INSERT INTO "Usuario" VALUES (2,'Gandalf',100,5.0);
INSERT INTO "Usuario" VALUES (3,'Sam',36,8.0);
INSERT INTO "Usuario" VALUES (4,'Galadriel',120,6.0);
INSERT INTO "Promocion" VALUES (1,'Pack Sindarin','Porcentual',20.0,NULL);
INSERT INTO "Promocion" VALUES (2,'Pack Sindarin plus','Porcentual',10.0,NULL);
INSERT INTO "Promocion" VALUES (3,'Pack Quenya gold','Absoluta',NULL,46.0);
INSERT INTO "Promocion" VALUES (4,'Pack Quenya','Absoluta',NULL,36.0);
INSERT INTO "Promocion" VALUES (5,'Pack Oestron','AxB',NULL,NULL);
INSERT INTO "Promocion" VALUES (6,'Pack Oestron extrema','AxB',NULL,NULL);
INSERT INTO "promocion_atraccion" VALUES (1,2,5);
INSERT INTO "promocion_atraccion" VALUES (2,2,6);
INSERT INTO "promocion_atraccion" VALUES (3,3,3);
INSERT INTO "promocion_atraccion" VALUES (4,3,4);
INSERT INTO "promocion_atraccion" VALUES (5,4,1);
INSERT INTO "promocion_atraccion" VALUES (6,4,2);
INSERT INTO "promocion_atraccion" VALUES (7,5,5);
INSERT INTO "promocion_atraccion" VALUES (8,6,2);
INSERT INTO "promocion_atraccion" VALUES (9,6,3);
INSERT INTO "promocion_atraccion" VALUES (10,6,4);
INSERT INTO "promocion_atraccion" VALUES (11,6,6);
INSERT INTO "promocion_atraccion" VALUES (12,7,3);
INSERT INTO "promocion_atraccion" VALUES (13,8,1);
INSERT INTO "promocion_atraccion" VALUES (14,8,2);
INSERT INTO "promoGratuita_axb" VALUES (1,7,5);
INSERT INTO "promoGratuita_axb" VALUES (2,7,6);
INSERT INTO "promoGratuita_axb" VALUES (3,8,6);
COMMIT;
