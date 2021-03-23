package pi.projeto.oel.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Denuncia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@NotBlank
	public String motivo;
	@NotBlank
	public String argumento;
	
	@ManyToOne
	private Lixeira lixeira;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getArgumento() {
		return argumento;
	}

	public void setArgumento(String argumento) {
		this.argumento = argumento;
	}

	public Lixeira getLixeira() {
		return lixeira;
	}

	public void setLixeira(Lixeira lixeira) {
		this.lixeira = lixeira;
	}

	@Override
	public String toString() {
		return "Denuncia [id=" + id + ", motivo=" + motivo + ", argumento=" + argumento + ", lixeira=" + lixeira + "]";
	}
	
	
}
