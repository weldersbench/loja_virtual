package br.com.loja_virtual;

public class ExceptionNotValue extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ExceptionNotValue(String msgErro) {
		/*Chama a superClasse Exception e armazena na melhoria a msg de erro*/
		super(msgErro);
	}

}
