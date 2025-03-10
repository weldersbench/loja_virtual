package br.com.loja_virtual.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.loja_virtual.ApplicationContextLoad;
import br.com.loja_virtual.model.Usuario;
import br.com.loja_virtual.repository.UsuarioRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
@Component
/*Criar a autenticação e retornar também a autenticação JWT*/
public class JWTTokenAutenticacaoService {
	
	/*Token de validade de 11 dias*/
	private static final long EXPIRATION_TIME = 959990000;
	/*Chave de senha para juntar com o JWT*/
	private static final String SECRET = "ss/-*-*sds565dsd-s/d-s*dsds";
	/*O prefixo do tipo do Token*/
	private static final String TOKEN_PREFIX = "Bearer";
	/*Cabeçalho da requisição*/
	private static final String HEADER_STRING = "Authorization";
	
	/*Gera o token e da a resposta para o cliente com o JWT*/
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {
		
		String JWT = Jwts.builder(). /*Chama o gerenciador de token*/
				setSubject(username) /*Adiciona o usuario*/
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) /*Tempo de expiração*/
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); /*Cria uma assinatura codificada*/
		
		/*Monta o Token Bearer*/
		String token = TOKEN_PREFIX + " " + JWT;
		/*Dá a resposta para a tela e para o cliente, sendo: outra API, navegador, aplicativo, javascript, outra chamada java*/
		response.addHeader(HEADER_STRING, token);
		response.addHeader("Content-Type", "application/json");
		
		libaracaoCors(response);
		
		/*BODY da requisição - Usado para ver no postman, para testes*/
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}
	
	/*Retorna o usuario validado com o token ou caso nao seja valido retorna null*/
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String token = request.getHeader(HEADER_STRING);
		try {
			
			if(token != null) {
				/*Retira o "Bearer" do inicio do token, devido ele precisar vim limpo*/
				String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();
				
				
				/*Faz a validação do token do usuario na requisicao e obtem o USER*/
				String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(tokenLimpo).getBody().getSubject();
				
				if(user != null) {
					
					/*Ajuda a consultar o usuario no banco de dados*/
					Usuario usuario = ApplicationContextLoad.getApplicationContext().
							getBean(UsuarioRepository.class).
							findUserByLogin(user);
					
					if(usuario != null) {
						return new UsernamePasswordAuthenticationToken(
								usuario.getLogin(), 
								usuario.getSenha(), 
								usuario.getAuthorities());
					}
				}
				
			}
		
		}catch (SignatureException e) {
			response.getWriter().write("Token Invalido!");
			//response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Token inválido
			
		}catch (ExpiredJwtException e) {
			response.getWriter().write("Token Expirado!");
			
		}finally {
			libaracaoCors(response);
		}
		
		libaracaoCors(response);
		
		return null;
		
	}
	
	/*Esse erro de cors, não ocorre quando usado para testes de API, apenas com requisicoes WEB*/
	private void libaracaoCors(HttpServletResponse response) {
		
		if(response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		if(response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}
		
		if(response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		
		if(response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
		
	}

}
