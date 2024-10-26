package br.com.loja_virtual.security;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
/*Criar a autenticação e retornar também a autenticação JWT*/
public class JWTTokenAutenticacaoService {
	
	/*Token de validade de 11 dias*/
	private static final long EXPIRATION_TIME = 959990000;
	/*Chave de senha para juntar com o JWT*/
	private static final String SECRET = "";
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
		
		/*BODY da requisição - Usado para ver no postman, para testes*/
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}

}
