# Como funciona persist e merge

Material para Hibernate, mas funciona com o EclipseLink

[guia de iniciantes sobre estados de entidades](https://vladmihalcea.com/a-beginners-guide-to-jpa-hibernate-entity-state-transitions/)

[como persist e merge funcionam](https://vladmihalcea.com/jpa-persist-and-merge/)


## Os estados de uma entidade

### Novo / Transiente (New / Transient)

* Objeto rec�m criado
* **Nunca** foi associado com uma sess�o Hibernate(Contexto de Persist�ncia)
* N�o est� mapeado para **nenhum** registro / linha de **nenhuma** tabela

***

### Persistente / Gerenciado (Persistent / Managed)

* Uma entidade persistida foi associada com um registro no banco de dados
* Est� sendo gerenciada pelo *Contexto de Persist�ncia* atual
* Qualquer mudan�a nos atributos do objeto ser� detectada e propagada para sua respectiva coluna. (Na *flush-time* da *Sess�o*)

***

### Detached (desacoplado, n�o conectado, etc)

Quando o *Contexto de Persist�ncia* � fechado:

* Entidades se tornam "detached".
* altera��es nas entidades n�o s�o mais rastreadas e **nenhuma** sincroniza��o com o banco acontece.

Para associar uma entidade "detached" para uma sess�o ativa do *Hibernate / EclipseLink*, podemos usar as seguintes op��es:

#### Reattaching

Exclusivo do Hibernate

#### Merging

Copia o estado da entidade "detached" para uma entidade de inst�ncia (registro destino).

Se a entidade que sofre o *merge* n�o tem equivalente na *Sess�o*, uma ser� *fetched* do banco.

> A inst�ncia do objeto "detached" vai permanecer *detached* mesmo **depois** da opera��o de *merge*!

***

### Removed

A especificica��o JPA demanda que apenas entidades *managed* podem ser removidas (Hibernate pode pode deletar entidades *detached*

uma entidade removida � agendada para delete na declara��o *DELETE* durante o *flush-time* da *Sess�o*.

***


## Merge e Persist

### Persist

Deve usado apenas com entidades novas. Da perspectiva JPA, uma entidade � "nova"
quando nunca foi associada com um registro no banco. N�o h� registro referente �
entidade em quest�o.

### Merge

Merge � requerido apenas para entidades detached. Entidades novas devem usar
persist. � poss�vel persistir uma entidade nova com merge em estrat�gias Identity
e sequence, mas elas geram uma consulta extra no banco.



