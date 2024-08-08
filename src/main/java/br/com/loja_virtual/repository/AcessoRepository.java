package br.com.loja_virtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.loja_virtual.model.Acesso;

@Repository
@Transactional
public interface AcessoRepository extends JpaRepository<Acesso, Long> {
	
	/*
	 Esconde a complexidade do acesso ao banco de dados, fornecendo os metodos CRUD e interagindo com o spring Data JPA
	 */

}
