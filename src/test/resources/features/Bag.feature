Feature: Adicionar na sacola

	Scenario Outline: Inserindo coisas na sacola
		Given A sacola encontra-se vazia
		When Inserir <quantidade1> "<item1>" na sacola
		And Inserir <quantidade2> "<item2>" na sacola
		Then A sacola deve conter <quantidade1> "<item1>"
		And A sacola deve conter <quantidade2> "<item2>"
		
		Examples:
			| quantidade1 | item1  | quantidade2 | item2   |
			|     1       | batata |    0        | banana  |
			|     2       | tomate |    2        | limao   |