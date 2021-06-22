package pi.projeto.oel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/oel").permitAll()
		.antMatchers("/oel/usuario").permitAll()
		.antMatchers("/oel/{id}").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/oel/login")
			.defaultSuccessUrl("/oel", true)
			.permitAll()
		.and()
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/oel/login")
			.permitAll();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/imagens/**", "/oel/{idLixeira}/mostrarImg/{imagem}");
	}
}
