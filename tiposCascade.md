# Tipos de Cascade

Maneira de definir constraints via EclipseLink(JPA)

## CascadeType = ALL

Toda altera��o na entidade que cont�m, � repassada para as outras

## CascadeType = PERSIST

Apenas persiste, n�o remove as entidades relacionadas

## CascadeType = DETACH

Faz detach nas entidades relacionadas quando a que cont�m sofre detach

## CascadeType = MERGE

Entidades sofre merge quando o dono sofre merge

## orphanRemoval = true / false

remo��o de �rf�os

***

passageiro e telefones

passageiro pode existir sem telefones

telefones n�o podem existir sem passageiros

Se adiciona telefone no passageiro, tem que adicionar nos telefones, tb

se exclui telefone do psgro, tem que excluir telefone tb

se atualiza, tem que atualizar




