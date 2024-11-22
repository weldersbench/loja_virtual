package br.com.loja_virtual.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/*Intercepta todas as requisicoes, tudo que vier para a API e retornar*/
public class JWTApiAutenticacaoFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		/*Estabelece a autenticacao do usuario*/
		Authentication authentication = new JWTTokenAutenticacaoService().
				getAuthentication((HttpServletRequest)request, (HttpServletResponse)response);
		
		/*Coloca o processo de autenticacao para o spring security*/
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		chain.doFilter(request, response);
		
	}

}
