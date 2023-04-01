/*
 * um para um na rela��o Passageiro - Pessoa
 *
 * Significa que:
 * - n�o pode haver Passagem sem id
 * - n�o pode haver passageiro com mais de uma passagem
 */


-- -- PASSAGEIRO -- --
-- tabela que o eclipselink est� criando:
create table passageiro (
	id_passageiro bigint auto_increment not null
	, cpf varchar(20)
	, endereco varchar(255)
	, nome varchar(50) -- n�o t� pegando o not null da classe Pessoa
	
	, PRIMARY KEY (id_passageiro)
);

-- como eu quero que seja
create table passageiro (
	id_passageiro bigint auto_increment NOT NULL
	, cpf varchar(20)
	, endereco varchar(255)
	, nome varchar(50) NOT NULL
	
	, PRIMARY KEY (id_passageiro)
);

-- -- PASSAGEM -- --
CREATE TABLE passagem (
	id_passagem BIGINT AUTO_INCREMENT NOT NULL
	, numero INTEGER
	, poltrona INTEGER
	, situacao varchar(15)
	, valor float(7, 2)
	, id_passageiro BIGINT -- TEM QUE SER UNIQUE e not null
	
	, PRIMARY KEY (id_passagem)
);

ALTER TABLE passagem
	ADD CONSTRAINT FK_passagem_id_passageiro
	FOREIGN KEY (id_passageiro)
	REFERENCES passageiro (id_passageiro)
);

-- como eu quero
CREATE TABLE passagem (
	id_passagem BIGINT AUTO_INCREMENT NOT NULL
	, numero INTEGER
	, poltrona INTEGER
	, situacao varchar(15)
	, valor float(7, 2)
	, id_passageiro BIGINT UNIQUE NOT NULL
	
	, PRIMARY KEY (id_passagem)
);



