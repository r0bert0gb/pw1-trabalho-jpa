# Como funciona persist e merge

Material para Hibernate, mas funciona com o EclipseLink

[guia de iniciantes sobre estados de entidades](https://vladmihalcea.com/a-beginners-guide-to-jpa-hibernate-entity-state-transitions/)

[como persist e merge funcionam](https://vladmihalcea.com/jpa-persist-and-merge/)


## Os estados de uma entidade

### Novo / Transiente (New / Transient)

* Objeto recém criado
* **Nunca** foi associado com uma sessão Hibernate(Contexto de Persistência)
* Não está mapeado para **nenhum** registro / linha de **nenhuma** tabela

***

### Persistente / Gerenciado (Persistent / Managed)

* Uma entidade persistida foi associada com um registro no banco de dados
* Está sendo gerenciada pelo *Contexto de Persistência* atual
* Qualquer mudança nos atributos do objeto será detectada e propagada para sua respectiva coluna. (Na *flush-time* da *Sessão*)

***

### Detached (desacoplado, não conectado, etc)

Quando o *Contexto de Persistência* é fechado:

* Entidades se tornam "detached".
* alterações nas entidades não são mais rastreadas e **nenhuma** sincronização com o banco acontece.

Para associar uma entidade "detached" para uma sessão ativa do *Hibernate / EclipseLink*, podemos usar as seguintes opções:

#### Reattaching

Exclusivo do Hibernate

#### Merging

Copia o estado da entidade "detached" para uma entidade de instância (registro destino).

Se a entidade que sofre o *merge* não tem equivalente na *Sessão*, uma será *fetched* do banco.

> A instância do objeto "detached" vai permanecer *detached* mesmo **depois** da operação de *merge*!

***

### Removed

A especificicação JPA demanda que apenas entidades *managed* podem ser removidas (Hibernate pode pode deletar entidades *detached*

uma entidade removida é agendada para delete na declaração *DELETE* durante o *flush-time* da *Sessão*.

***


## Merge e Persist

### Persist

Deve usado apenas com entidades novas. Da perspectiva JPA, uma entidade é "nova"
quando nunca foi associada com um registro no banco. Não há registro referente à
entidade em questão.

### Merge

Merge é requerido apenas para entidades detached. Entidades novas devem usar
persist. É possível persistir uma entidade nova com merge em estratégias Identity
e sequence, mas elas geram uma consulta extra no banco.



