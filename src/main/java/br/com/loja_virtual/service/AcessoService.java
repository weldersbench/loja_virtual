package br.com.loja_virtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.loja_virtual.model.Acesso;
import br.com.loja_virtual.repository.AcessoRepository;

@Service
public class AcessoService {
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	/*Criado um metodo de salvar no banco de dados*/
	public Acesso save(Acesso acesso) {
		
		/*Qualquer validações necessaria*/
		
		/*Retorna para a variavel do repository, para persistir o acesso no banco e salvar*/
		return acessoRepository.save(acesso);
	}
	
	
	public void delete(Acesso acesso) {
		acessoRepository.deleteById(acesso.getId());;
	}
	
	public void deleteById(Long id) {
		acessoRepository.deleteById(id);
	}
	
	public Acesso buscarAcesso(Long id) {
		return acessoRepository.findById(id).get();
	}
	
	public List<Acesso> buscarPorDesc(String dec){
		return acessoRepository.buscarAcessoDesc(dec);
	}

}
