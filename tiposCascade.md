# Tipos de Cascade

Maneira de definir constraints via EclipseLink(JPA)

## CascadeType = ALL

Toda alteração na entidade que contém, é repassada para as outras

## CascadeType = PERSIST

Apenas persiste, não remove as entidades relacionadas

## CascadeType = DETACH

Faz detach nas entidades relacionadas quando a que contém sofre detach

## CascadeType = MERGE

Entidades sofre merge quando o dono sofre merge

## orphanRemoval = true / false

remoção de órfãos

***

passageiro e telefones

passageiro pode existir sem telefones

telefones não podem existir sem passageiros

Se adiciona telefone no passageiro, tem que adicionar nos telefones, tb

se exclui telefone do psgro, tem que excluir telefone tb

se atualiza, tem que atualizar




