package br.com.loja_virtual.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.loja_virtual.model.Usuario;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter{
	
	/*Configurando o gerenciador de autenticacao*/
	public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
		
		/*Obriga a autenticacao da url*/
		super(new AntPathRequestMatcher(url));
		
		/*Gerenciador de autenticacao*/
		setAuthenticationManager(authenticationManager);
	}

	
	/*Retorna o usuario ao processar a autenticacao*/
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		/*Obtem o usuario*/
		Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
		
		/*Retorna o usuario com o login e senha*/
		return getAuthenticationManager().
				authenticate(new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		try {
			new JWTTokenAutenticacaoService().addAuthentication(response, authResult.getName());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
