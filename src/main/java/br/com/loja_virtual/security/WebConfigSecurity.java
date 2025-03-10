package br.com.loja_virtual.security;

import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.loja_virtual.service.ImplementacaoUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter implements HttpSessionListener {
	
	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.
				withHttpOnlyFalse()).disable().authorizeRequests().
				antMatchers("/").permitAll().antMatchers("/index").
				permitAll().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				
		/*Redireciona ou da um retorno para o index quando deslogado*/
				.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
				
		/*Mapeia o logout do sistema*/
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				
		/*Filtra as requisicoes para login de JWT*/
				.and().addFilterAfter(new JWTLoginFilter("/login", authenticationManager()), 
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JWTApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	
	/*Irá consultar o user no banco com spring Security*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(implementacaoUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	

	/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //ignorando URL no momento de autenticar
        http
            //.csrf().disable()
            //.authorizeRequests()
            //.antMatchers(HttpMethod.GET, "/salvarAcesso", "/deleteAcesso").permitAll()
            //.antMatchers(HttpMethod.POST, "/salvarAcesso", "/deleteAcesso").permitAll()
            //.anyRequest().authenticated();
    }
   */
	
	/*
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*") // Ou especifique os domínios permitidos
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
    */
    
}
