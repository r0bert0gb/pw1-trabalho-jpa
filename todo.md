# ToDo

- [ ] atributo nome de Pessoa como not null em Passageiro

- [ ] Mapear a classe passagem.

* Como é a cascade type de Passageiro na classe Passagem?
	* persist:
	Passageiro não será excluído se a passagem for excluída.
	Nem vai sofrer merge... ou vai?
	* Não esquecer do "fetch"
	
	
	passagem de id = 1
	- Tem passageiro de id = 3
	
	- [x] posso mudar a id do passageiro com "setId"?
	Resposta: NÃO!!	
	Aliás... não tenho como associar uma id que existe e sem passagem
	para um passagem nula o.o
	
		- [x] Tentar mudar para um valor que de passageiro que não existe
		de 3 para 5 (não existe)
			- Resultado: "updates are not allowed"
			
		- [x] Tentar mudar para uma id que existe e não tem passagem
		de 3 para 1
			- Resultado: updates are not allowed

		- [x] Tentar mudar a id para 0
			- Resultado: id inválida por padrão
			
		- [x] Tentar mudar para uma id que existe e POSSUI passagem
		de 3 para 4
			- Resultado: updates are not allowed
			
	passagem de id = 2
	- Tem passageiro de id = 4
	- [ ] posso mudar o passageiro através de instâncias com setPassageiro()?
	
		- [x] Instanciar um passageiro novo, sem persist na passagem de id 2
			Resultado: Dá certo. Um novo passageiro é criado no banco
		
		- [x] Fazer um setPassageiro() com um passageiro que existe
			id da passagem usada agora é 3!
			id do passageiro original é 10
			
			passar para o passageiro de id 2
			
			Resultado: Dá certo. A id pode ser alterada usando o setPassageiro()
			
		- [ ] Fazer um set de passageiro com valor nulo
			Resultado: Dá certo. Passagem fica sem ID
			REQUER CONSERTO!
			
		- [ ] Fazer um set de um passageiro que já possua uma passagem
			id da passagem usada 2
			id do passageiro original 4 (3 e 2 tb possuem passagem)
			passar para o passageiro de id 2
			
			
		
			
			
	





