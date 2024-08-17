package br.com.loja_virtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.loja_virtual.model.Acesso;
import br.com.loja_virtual.service.AcessoService;

@Controller
@RestController
public class AcessoController {
	
	@Autowired
	private AcessoService acessoService;
	
	@ResponseBody /*Poder dar um retorno da API*/
	@PostMapping("/salvarAcesso") /*Mapeia a URL para receber o JSON*/
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) { /*Recebe o JSON e converte para objeto*/
		
		/*Chama o service para persistir os dados*/
		Acesso acessosalvo = acessoService.save(acesso);
		
		return new ResponseEntity<Acesso>(acessosalvo, HttpStatus.OK); /*Retorna para o objeto Acesso, e o status como OK*/
	}
	
	
	@ResponseBody /*Poder dar um retorno da API*/
	@PostMapping("/deleteAcesso") /*Mapeia a URL para receber o JSON*/
	public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) { /*Recebe o JSON e converte para objeto*/
		
		/*Chama o service para persistir os dados*/
		acessoService.delete(acesso);
		
		return new ResponseEntity("Acesso deletado", HttpStatus.OK); /*Retorna para o objeto Acesso, e o status como OK*/
	}

}
