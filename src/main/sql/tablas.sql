
-- -----------------------------------------------------
-- Table `Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Usuario(
  idUsuario INTEGER PRIMARY KEY ,
  nombre VARCHAR(40)  NOT NULL,
  correo VARCHAR(50)  NOT NULL,
  contraseña VARCHAR(80)  NOT NULL,
  estado INTEGER  NOT NULL,
  rol VARCHAR(30) NOT NULL,
  UNIQUE(correo )
);

-- -----------------------------------------------------
-- Table `Laboratorio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Laboratorio (
  idLaboratorio serial PRIMARY KEY,
  nombre VARCHAR(40) NOT NULL,
  estado VARCHAR(8) NOT NULL,
  UNIQUE (nombre)
);

-- -----------------------------------------------------
-- Table `Equipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Equipo (
  idEquipo serial PRIMARY KEY,
  laboratorio INT REFERENCES Laboratorio(idLaboratorio) DEFERRABLE,
  fechaRegistro DATE NOT NULL,
  disponible INT  NOT NULL,
  estado VARCHAR(8) NOT NULL
);

-- -----------------------------------------------------
-- Table `Elemento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Elemento (
  idElemento serial PRIMARY KEY,
  tipo VARCHAR(8) NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  descripcion VARCHAR(500)  NOT NULL,
  idEquipo INT REFERENCES Equipo(idEquipo) DEFERRABLE,
  disponible INT NOT NULL,
  estado VARCHAR(8) NOT NULL,
  UNIQUE(tipo,idEquipo)
);
-- -----------------------------------------------------
-- Table `Novedad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Novedad (
  idNovedad serial PRIMARY KEY,
  titulo VARCHAR(40) NOT NULL,
  detalle VARCHAR(200) NOT NULL,
  fecha DATE NOT NULL,
  usuario INT REFERENCES Usuario(idUsuario) DEFERRABLE,
  idEquipo INT REFERENCES Equipo(idEquipo) DEFERRABLE,
  idElemento INT REFERENCES Elemento(idElemento) DEFERRABLE
);