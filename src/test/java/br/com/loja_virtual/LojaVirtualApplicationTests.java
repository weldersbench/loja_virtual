package br.com.loja_virtual;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.loja_virtual.controller.AcessoController;
import br.com.loja_virtual.model.Acesso;
import br.com.loja_virtual.repository.AcessoRepository;
import br.com.loja_virtual.service.AcessoService;
import junit.framework.TestCase;

@SpringBootTest(classes = LojaVirtualApplication.class)
class LojaVirtualApplicationTests extends TestCase{
	
	/*
	 * Como existe um controller criado, essa injeção não é necessaria
	 */
	@Autowired
	private AcessoRepository acessoRepository;
	
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
		
		acesso.setDescricao("te amo giovana");
		
		/*Chama o metodo criado na classe de service, passando a variavel da instancia da classe Acesso*/
		//acessoService.save(acesso); - usando o service direto
		
		/*É usado a mesma variavel para guardar o returno do acesso entity*/
		acesso = acessoController.salvarAcesso(acesso).getBody(); // usando o controller
		
		/*Validando se o ID está sendo salvo corretamento no banco*/
		assertEquals(true, acesso.getId() > 0);
		/*Validando o incremento sequencial de ID*/
		//assertEquals(true, acesso.getId() > 14 && acesso.getId() <= 15);
		/*Validando a mensagem gravada, ignorando Maiusculas e Minusculas*/
		assertTrue("Te Amo Giovana".equalsIgnoreCase(acesso.getDescricao()));
		/*Validando a mensagem gravada, transformando tudo em Minusculas*/
		assertEquals("Te Amo Giovana".toLowerCase(), acesso.getDescricao().toLowerCase());
		
		
		/**TESTE DE CARREGAMENTO**/
		/*Instancia um novo acesso, atribui a persistencia da busca com o repositorio, e retorna o que encontrou*/
		Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();
		/*Esperado e Atual*/
		assertEquals(acesso.getId(), acesso2.getId());
		
		/**TESTE DE DELETE**/
		
		acessoRepository.deleteById(acesso2.getId());
		acessoRepository.flush();
		
		acesso = acessoRepository.findById(acesso2.getId()).orElse(null);
		
		assertEquals(true, acesso == null);
		
		/**TESTE DE QUERY**/
		
		/*Instancio um novo acesso, como se fosse zerar o que já estava instanciado*/
		acesso = new Acesso();
		
		acesso.setDescricao("ALUNO");
		
		acesso = acessoController.salvarAcesso(acesso).getBody();
		
		List<Acesso> acessos = acessoRepository.buscarAcessoDesc("aluno".trim().toUpperCase());
		
		assertEquals(1, acessos.size());
		
		/*É necessario deletar, para o teste passar, porque senão o tamanho size fica com 2 ou mais na busca.*/
		acessoRepository.deleteById(acesso.getId());
		
	}

}
