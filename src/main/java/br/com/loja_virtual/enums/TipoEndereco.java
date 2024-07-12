package br.com.loja_virtual.enums;

public enum TipoEndereco {
	
	COBRANCA("Cobrança"),
	ENTREGA("Entrega");
	
	/*
	 Enum exige que coloque um campo de descrição. É criado o atributo "descrição"
	 */
	private String descricao;
	
	// É preciso criar um construtor tbm, para atribuir a variavel descrição.
	private TipoEndereco(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	/*
	 É adicionado o toString, para o java não se perder quando for mostrar o enum, e no retorno é apontado para o que queremos que mostre.
	 */
	@Override
	public String toString() {
		return this.descricao;
	}

}
