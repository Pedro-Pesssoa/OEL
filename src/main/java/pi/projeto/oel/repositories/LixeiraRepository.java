package pi.projeto.oel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pi.projeto.oel.models.Lixeira;

@Repository
public interface LixeiraRepository extends JpaRepository<Lixeira, Long>{

	@Query("select b from Lixeira b where b.bairro like %?1%")
	public List<Lixeira> findByBairroAndAllIgnoreCase(String bairro);
	
	@Query("select r from Lixeira r where r.rua like %?1%")
	public List<Lixeira> findByRuaAndAllIgnoreCase(String rua);
	
	@Query("select r from Lixeira r where r.tipo like %?1%")
	public List<Lixeira> findByTipoAndAllIgnoreCase(String tipo);
	
	
}
