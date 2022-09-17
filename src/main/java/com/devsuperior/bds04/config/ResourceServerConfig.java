package com.devsuperior.bds04.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	
	private static final String [] PUBLIC = {"/h2-console/**","/oauth/token"};
	
	private static final String [] EVENTS_AND_CITIES ={"/events/**", "/cities/**"}; 
			
	private static final String [] ADMIM_OR_CLIENT = {"/events/**", "/cities/**"};
	
	private static final String [] EVENTS = {"/events/**"};

	private static final String [] ADMINISTRATOR = {"/users/**"}; 

	@Autowired
	private Environment environment; 

	@Autowired
	private JwtTokenStore tokenStore;

	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

		resources.tokenStore(tokenStore);
	}


	@Override
	public void configure(HttpSecurity http) throws Exception {

		//config para liberar acesso ao h2-console
		if (Arrays.asList(environment.getActiveProfiles()).contains("test")==true) {
			http.headers().frameOptions().disable();
		}

		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()		
		.antMatchers(HttpMethod.GET, EVENTS_AND_CITIES).permitAll()
		.antMatchers(HttpMethod.POST, EVENTS_AND_CITIES).hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, EVENTS).hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, EVENTS).hasRole("CLIENT")
		.antMatchers(ADMINISTRATOR).hasRole("ADMIN")
		//.anyRequest().authenticated(); // para acessar qualquer outra rota não espeficicada tem que estar logado
		.anyRequest().hasRole("ADMIN");
	}	

}