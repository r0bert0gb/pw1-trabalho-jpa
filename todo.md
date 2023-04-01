# ToDo

- [ ] atributo nome de Pessoa como not null em Passageiro

- [ ] Mapear a classe passagem.

* Como � a cascade type de Passageiro na classe Passagem?
	* persist:
	Passageiro n�o ser� exclu�do se a passagem for exclu�da.
	Nem vai sofrer merge... ou vai?
	* N�o esquecer do "fetch"
	
	
	passagem de id = 1
	- Tem passageiro de id = 3
	
	- [x] posso mudar a id do passageiro com "setId"?
	Resposta: N�O!!	
	Ali�s... n�o tenho como associar uma id que existe e sem passagem
	para um passagem nula o.o
	
		- [x] Tentar mudar para um valor que de passageiro que n�o existe
		de 3 para 5 (n�o existe)
			- Resultado: "updates are not allowed"
			
		- [x] Tentar mudar para uma id que existe e n�o tem passagem
		de 3 para 1
			- Resultado: updates are not allowed

		- [x] Tentar mudar a id para 0
			- Resultado: id inv�lida por padr�o
			
		- [x] Tentar mudar para uma id que existe e POSSUI passagem
		de 3 para 4
			- Resultado: updates are not allowed
			
	passagem de id = 2
	- Tem passageiro de id = 4
	- [ ] posso mudar o passageiro atrav�s de inst�ncias com setPassageiro()?
	
		- [x] Instanciar um passageiro novo, sem persist na passagem de id 2
			Resultado: D� certo. Um novo passageiro � criado no banco
		
		- [x] Fazer um setPassageiro() com um passageiro que existe
			id da passagem usada agora � 3!
			id do passageiro original � 10
			
			passar para o passageiro de id 2
			
			Resultado: D� certo. A id pode ser alterada usando o setPassageiro()
			
		- [ ] Fazer um set de passageiro com valor nulo
			Resultado: D� certo. Passagem fica sem ID
			REQUER CONSERTO!
			
		- [ ] Fazer um set de um passageiro que j� possua uma passagem
			id da passagem usada 2
			id do passageiro original 4 (3 e 2 tb possuem passagem)
			passar para o passageiro de id 2
			
			
		
			
			
	





