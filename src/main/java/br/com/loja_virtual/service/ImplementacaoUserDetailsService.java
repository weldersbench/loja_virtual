package br.com.loja_virtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.loja_virtual.model.Usuario;
import br.com.loja_virtual.repository.UsuarioRepository;

@Service
public class ImplementacaoUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findUserByLogin(username); /*Recebe o login pra consulta*/
		
		/**Se não existir um usuario cadastrado**/
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario não foi encontrado");
		}
		
		return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities()); /*Retorna o objeto*/
	}

}
