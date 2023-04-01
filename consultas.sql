drop database testesjpa;
--
create database testesjpa;
--
use testesjpa;

select id_passagem as id, valor from tb_passagem psgm
inner join tb_passageiro psgro on psgm.id_passageiro= psgro.id_passageiro
where psgro.id_passageiro = 1;

-- Nome, cpf, telefone e assento do passageiro de id = 1
select
	nome as Nome
	, cpf as CPF
	, fone.telefone as Telefone
	, psgm.poltrona as Assento
from passageiro psgro

inner join
	passageiro_telefone fone on fone.id_passageiro = psgro.id_passageiro
inner join
	passagem psgm on psgm.id_passageiro = psgro.id_passageiro

where psgro.id_passageiro = 1;

select psgro.id_passageiro, nome from passageiro psgro
inner join passagem psgm on psgm.id_passageiro = psgro.id_passageiro
where psgm.id_passageiro = 3;

insert into passagem (id_passageiro) values (4) where id_passagem = 4;

drop database testesjpa;

create database testesjpa;