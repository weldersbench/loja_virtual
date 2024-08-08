package br.com.loja_virtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.loja_virtual.controller.AcessoController;
import br.com.loja_virtual.model.Acesso;
import br.com.loja_virtual.repository.AcessoRepository;
import br.com.loja_virtual.service.AcessoService;

@SpringBootTest(classes = LojaVirtualApplication.class)
class LojaVirtualApplicationTests {
	
	/*
	 * Como existe um controller criado, essa injeção não é necessaria
	@Autowired
	private AcessoRepository acessoRepository;
	*/
	/*
	 * Como existe um controller criado, essa injeção não é necessaria
	@Autowired
	private AcessoService acessoService;
	*/
	@Autowired
	private AcessoController acessoController;

	@Test
	public void acessoCadastro() {
		
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("Role_Teste");
		
		/*Chama o metodo criado na classe de service, passando a variavel da instancia da classe Acesso*/
		//acessoService.save(acesso); - usando o service direto
		acessoController.salvarAcesso(acesso); // usando o controller
	}

}
