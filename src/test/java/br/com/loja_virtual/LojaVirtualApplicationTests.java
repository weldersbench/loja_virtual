package br.com.loja_virtual;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@Autowired
	private WebApplicationContext wac;
	
	@Test
	public void testeRestApiCadastroAcesso() throws JsonProcessingException, Exception {
		/*Os 2 objetos vão ser responsaveis pelos os testes, fazer requisão, retorna e etc*/
		/*Umas das formas mais facies para trabalhar com Mock, é com a classe Builder*/
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		/*Tras todas as informações da aplicação que está no objeto builder*/
		MockMvc mockMvc = builder.build();
		
		/**NOVO OBJETO, PARA SALVAR E CARREGAR DADOS**/
		Acesso acesso = new Acesso();
		/*Conteudo que vai ser salvo no banco*/
		acesso.setDescricao("ROLE_COMPRADOR");
		
		/**NOVO OJETO, PARA USAR AS CLASSES DE JSON**/
		ObjectMapper objectMapper = new ObjectMapper();
		
		/**RETORNO DA API, AONDE O TESTE ACONTECE**/
		
		ResultActions retornoApi = mockMvc
						.perform(MockMvcRequestBuilders.post("/salvarAcesso") // Passa os dados dentro dele "Perform"
						.content(objectMapper.writeValueAsString(acesso)) // É o objeto que vai ser salvo
						.accept(MediaType.APPLICATION_JSON) // Qual é o tipo do conteudo
						.contentType(MediaType.APPLICATION_JSON)); // E o que é, "JSON"
		
		System.out.println("RETORNO DA API: " + retornoApi.andReturn().getResponse().getContentAsString());
		
		/**CONVERTER O RETORNO DA API PARA UM OBJETO DE ACESSO**/
		
		Acesso objetoRetorno = objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);
		
		assertEquals(acesso.getDescricao(), objetoRetorno.getDescricao());
				
	}
	
	@Test
	public void testeRestApiDeleteAcesso() throws JsonProcessingException, Exception {
		/*Os 2 objetos vão ser responsaveis pelos os testes, fazer requisão, retorna e etc*/
		/*Umas das formas mais facies para trabalhar com Mock, é com a classe Builder*/
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		/*Tras todas as informações da aplicação que está no objeto builder*/
		MockMvc mockMvc = builder.build();
		
		/**NOVO OBJETO, PARA SALVAR E CARREGAR DADOS**/
		Acesso acesso = new Acesso();
		/*Conteudo que vai ser salvo no banco*/
		acesso.setDescricao("ROLE_DELETADO");
		
		acesso = acessoRepository.save(acesso);
		
		/**NOVO OJETO, PARA USAR AS CLASSES DE JSON**/
		ObjectMapper objectMapper = new ObjectMapper();
		
		/**RETORNO DA API, AONDE O TESTE ACONTECE**/
		
		ResultActions retornoApi = mockMvc
						.perform(MockMvcRequestBuilders.post("/deleteAcesso") // Passa os dados dentro dele "Perform"
						.content(objectMapper.writeValueAsString(acesso)) // É o objeto que vai ser salvo
						.accept(MediaType.APPLICATION_JSON) // Qual é o tipo do conteudo
						.contentType(MediaType.APPLICATION_JSON)); // E o que é, "JSON"
		
		System.out.println("RETORNO DA API: " + retornoApi.andReturn().getResponse().getContentAsString());
		System.out.println("RETORNO DO STATUS: " + retornoApi.andReturn().getResponse().getStatus());
		
				
	}
	
	
	@Test
	public void testeRestApiDeleteAcessoById() throws JsonProcessingException, Exception {
		/*Os 2 objetos vão ser responsaveis pelos os testes, fazer requisão, retorna e etc*/
		/*Umas das formas mais facies para trabalhar com Mock, é com a classe Builder*/
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		/*Tras todas as informações da aplicação que está no objeto builder*/
		MockMvc mockMvc = builder.build();
		
		/**NOVO OBJETO, PARA SALVAR E CARREGAR DADOS**/
		Acesso acesso = new Acesso();
		/*Conteudo que vai ser salvo no banco*/
		acesso.setDescricao("ROLE_DELETADO_BY_ID");
		
		acesso = acessoRepository.save(acesso);
		
		/**NOVO OJETO, PARA USAR AS CLASSES DE JSON**/
		ObjectMapper objectMapper = new ObjectMapper();
		
		/**RETORNO DA API, AONDE O TESTE ACONTECE**/
		
		ResultActions retornoApi = mockMvc
						.perform(MockMvcRequestBuilders.delete("/deleteAcessoById/" + acesso.getId()) // Passa os dados dentro dele "Perform"
						.content(objectMapper.writeValueAsString(acesso)) // É o objeto que vai ser salvo
						.accept(MediaType.APPLICATION_JSON) // Qual é o tipo do conteudo
						.contentType(MediaType.APPLICATION_JSON)); // E o que é, "JSON"
		
		System.out.println("RETORNO DA API: " + retornoApi.andReturn().getResponse().getContentAsString());
		System.out.println("RETORNO DO STATUS: " + retornoApi.andReturn().getResponse().getStatus());
		
				
	}
	
	@Test
	public void testeRestApiObterAcessoPorId() throws JsonProcessingException, Exception {
		/*Os 2 objetos vão ser responsaveis pelos os testes, fazer requisão, retorna e etc*/
		/*Umas das formas mais facies para trabalhar com Mock, é com a classe Builder*/
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		/*Tras todas as informações da aplicação que está no objeto builder*/
		MockMvc mockMvc = builder.build();
		
		/**NOVO OBJETO, PARA SALVAR E CARREGAR DADOS**/
		Acesso acesso = new Acesso();
		/*Conteudo que vai ser salvo no banco*/
		acesso.setDescricao("ROLE_BUSCAR_BY_ID");
		
		acesso = acessoRepository.save(acesso);
		
		/**NOVO OJETO, PARA USAR AS CLASSES DE JSON**/
		ObjectMapper objectMapper = new ObjectMapper();
		
		/**ENVIA E RETORNO DA API, AONDE O TESTE ACONTECE**/
		ResultActions retornoApi = mockMvc
						.perform(MockMvcRequestBuilders.get("/obterAcesso/" + acesso.getId()) // Passa os dados dentro dele "Perform"
						.content(objectMapper.writeValueAsString(acesso)) // É o objeto que vai ser salvo
						.accept(MediaType.APPLICATION_JSON) // Qual é o tipo do conteudo
						.contentType(MediaType.APPLICATION_JSON)); // E o que é, "JSON"
		
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		
		/**READVALUE Retorna um objeto generico. - Devido isso é preciso passar a classe que estamos acessando.**/
		
		Acesso objetoRetorno = objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);
		
		assertEquals(acesso.getDescricao(), objetoRetorno.getDescricao());
		assertEquals(acesso.getId(), objetoRetorno.getId());
		
				
	}
	
	
	@Test
	public void testeRestApiBuscarPorDesc() throws JsonProcessingException, Exception {
		/*Os 2 objetos vão ser responsaveis pelos os testes, fazer requisão, retorna e etc*/
		/*Umas das formas mais facies para trabalhar com Mock, é com a classe Builder*/
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		/*Tras todas as informações da aplicação que está no objeto builder*/
		MockMvc mockMvc = builder.build();
		
		/**NOVO OBJETO, PARA SALVAR E CARREGAR DADOS**/
		Acesso acesso = new Acesso();
		/*Conteudo que vai ser salvo no banco*/
		acesso.setDescricao("ROLE_BUSCAR_POR_LISTA_ACESSO");
		
		acesso = acessoRepository.save(acesso);
		
		/**NOVO OJETO, PARA USAR AS CLASSES DE JSON**/
		ObjectMapper objectMapper = new ObjectMapper();
		
		/**ENVIA E RETORNO DA API, AONDE O TESTE ACONTECE**/
		ResultActions retornoApi = mockMvc
						.perform(MockMvcRequestBuilders.get("/buscarPorDescricao/TA_ACESSO") // Passa os dados dentro dele "Perform"
						.content(objectMapper.writeValueAsString(acesso)) // É o objeto que vai ser salvo
						.accept(MediaType.APPLICATION_JSON) // Qual é o tipo do conteudo
						.contentType(MediaType.APPLICATION_JSON)); // E o que é, "JSON"
		
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		
		/**CONVERTER O RETORNO DA API PARA UM OBJETO DE ACESSO**/
		
		
		List<Acesso> retornoApiList = objectMapper
				        .readValue(retornoApi.andReturn().getResponse().getContentAsString(), 
						new TypeReference<List<Acesso>>() {});
		
		assertEquals(1, retornoApiList.size());
		assertEquals(acesso.getDescricao(), retornoApiList.get(0).getDescricao());
				
		acessoRepository.deleteById(acesso.getId());	
	}

	@Test
	public void acessoCadastro() {
		
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("macedao");
		
		/*Chama o metodo criado na classe de service, passando a variavel da instancia da classe Acesso*/
		//acessoService.save(acesso); - usando o service direto
		
		/*É usado a mesma variavel para guardar o returno do acesso entity*/
		acesso = acessoController.salvarAcesso(acesso).getBody(); // usando o controller
		
		/*Validando se o ID está sendo salvo corretamento no banco*/
		assertEquals(true, acesso.getId() > 0);
		/*Validando o incremento sequencial de ID*/
		//assertEquals(true, acesso.getId() > 14 && acesso.getId() <= 15);
		/*Validando a mensagem gravada, ignorando Maiusculas e Minusculas*/
		assertTrue("".equalsIgnoreCase(acesso.getDescricao()));
		/*Validando a mensagem gravada, transformando tudo em Minusculas*/
		assertEquals("".toLowerCase(), acesso.getDescricao().toLowerCase());
		
		
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
