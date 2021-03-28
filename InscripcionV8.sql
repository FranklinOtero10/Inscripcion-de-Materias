create database Inscripcion; 
go 
use Inscripcion;

create table carreras(
	id_carrera					int identity primary key,
	codigo_carrera				varchar(20) not null, 
	carrera						varchar(100) not null
);

create table usuarios(
	id							int identity primary key,
	codigo						varchar(40) not null,
	contrasena					varchar(50) not null, 
	nivel						int not null,
	nombres						varchar(50) not null,
	apellidos					varchar(50) not null,
	foto						varbinary(MAX) 
);

create table grupos(
	id_grupo					int identity primary key,
	grupo						varchar(10) not null,
	horario						varchar(100) not null,
	aula 						varchar(4) not null
);

create table administradores(
	id_administrador			int identity primary key,
	telefono					varchar(15) not null, 
	email						varchar(30) not null,
	direccion					varchar(100) not null,  
	sexo						varchar(10) not null,
	cargo						varchar(20) not null, 
	idusuario					int not null, 
	constraint fkAdministradorUsuario foreign key(idusuario) references usuarios(id)
);

create table ciclos(
	id_ciclo					int identity primary key, 
	ciclo						varchar(4) not null
);

create table estudiantes(
	expediente					int identity primary key, 
	fecha_nacimiento			varchar(15) not null, 
	direccion					varchar(100) not null, 
	sexo						varchar(10) not null, 
	dui							varchar(10) not null, 
	id_carrera					int	not null, 
	idusuario					int not null, 
	constraint fkEstudianteCarrera	foreign key (id_carrera) references carreras(id_carrera), 
	constraint fkEstudianteUsuario foreign key (idusuario) references usuarios(id)
);


create table materias(
	codigo_materia				int identity primary key,
	materia						varchar(45) not null,
	uv							varchar(1) not null,
	id_ciclo					int not null,
	prerequisito				varchar(50),
	constraint fkMateriaCiclo foreign key (id_ciclo) references ciclos(id_ciclo)
);


/*

	EN INSCRIPCION ASOCIAMOS LA NOTA(ID_NOTA) QUE OBTENDRA EL ESTUDIANTE(EXPEDIENTE) EN EL GRUPO(IDGRUPO) 
	QUE ESTE INSCRIBIO(ID_INSCRIPCION) EN INSCRIPCION TENDRA UN ESTADO (CAMPO ENTERO: 1-APROBADA , 2-REPROBADA )
	PODEMOS MEDIANTE PROGRAMACION CAMBIAR EL ESTADO CON RESPECTO A LA NOTA_FINAL MEDIANTE LA REFERENCIA FORANEA
	
*/ 
create table inscripciones(
	id_inscripcion				int identity primary key, 
	id_grupo					int not null,
	id_ciclo					int,
	codigo_materia				int,
	expediente					int not null, 
	anio						varchar(4),
	constraint fkInscripcionGrupo foreign key (id_grupo) references grupos(id_grupo), 
	constraint fkInscripcionEstudiante foreign key (expediente) references estudiantes(expediente),
	constraint fkInscripcionCiclo foreign key (id_ciclo) references ciclos(id_ciclo),
	constraint fkInscripcionMateria foreign key (codigo_materia) references materias(codigo_materia)
);
