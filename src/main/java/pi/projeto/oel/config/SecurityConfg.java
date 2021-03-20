package pi.projeto.oel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pi.projeto.oel.service.CustomUserDetailService;

@Configuration
public class SecurityConfg {

	@Autowired
	private CustomUserDetailService detailService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(detailService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
