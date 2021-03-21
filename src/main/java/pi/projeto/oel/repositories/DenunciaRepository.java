package pi.projeto.oel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pi.projeto.oel.models.Denuncia;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {

}
