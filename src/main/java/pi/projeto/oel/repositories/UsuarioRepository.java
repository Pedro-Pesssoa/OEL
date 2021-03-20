package pi.projeto.oel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pi.projeto.oel.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByEmail(String email);

}
