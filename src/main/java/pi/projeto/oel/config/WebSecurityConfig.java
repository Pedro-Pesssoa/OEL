package pi.projeto.oel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/oel").permitAll()
		.antMatchers("/oel/usuario").permitAll()
		.antMatchers("/oel/{id}").permitAll()
		.antMatchers("/imagens/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/oel/login")
			.permitAll();
	}
}
