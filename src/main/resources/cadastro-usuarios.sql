--- Tabela
CREATE TABLE usuarios (
	id serial PRIMARY KEY NOT NULL,	
	usuario varchar(120) NOT NULL,
	senha char(10) NOT NULL
	
);

CREATE TABLE cadastros (
	id serial PRIMARY KEY NOT NULL,	
	nome varchar(120) NOT NULL,
	cpf char(11) UNIQUE NOT NULL,
	endereco varchar(150) NULL,
	telefone varchar(11) NULL,
	id_usuario integer NOT null,
	FOREIGN KEY (id_usuario) REFERENCES usuarios(id) 	
);

--- Consultar
SELECT * FROM usuarios;
SELECT * FROM cadastros;


--- Excluir
DROP TABLE cadastros;
DROP TABLE usuarios; 
