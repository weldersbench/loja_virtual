package br.com.loja_virtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.loja_virtual.model.Acesso;

@Repository
@Transactional
public interface AcessoRepository extends JpaRepository<Acesso, Long> {
	
	/*
	 Esconde a complexidade do acesso ao banco de dados, fornecendo os metodos CRUD e interagindo com o spring Data JPA
	 */
	
	/**METODO DE QUERY**/
	/*Retorna uma lista de acessos e (recebe uma string com o nome do acesso)*/
	
	@Query("select a from Acesso a where upper(trim(a.descricao)) like %?1%")
	List<Acesso> buscarAcessoDesc(String desc);

}
