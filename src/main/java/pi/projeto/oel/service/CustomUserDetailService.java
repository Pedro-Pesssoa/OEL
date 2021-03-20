package pi.projeto.oel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pi.projeto.oel.models.Usuario;
import pi.projeto.oel.repositories.UsuarioRepository;

@Service
public class CustomUserDetailService implements UserDetailsService
{

	@Autowired
	private UsuarioRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = ur.findByEmail(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario não foi encontrado");
		}
		
		return usuario;
	}

}
