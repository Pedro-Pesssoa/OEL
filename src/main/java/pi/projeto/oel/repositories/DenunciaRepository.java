package pi.projeto.oel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pi.projeto.oel.models.Denuncia;
import pi.projeto.oel.models.Lixeira;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
	Optional<Denuncia> findByLixeira(Lixeira lixeira);
	
}
