package pi.projeto.oel.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Lixeira {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	private String tipo;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate data;
	
	
	private String latitude;
	
	
	private String logitude;
	
	
	private String complemento;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLogitude() {
		return logitude;
	}

	public void setLogitude(String logitude) {
		this.logitude = logitude;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Lixeira [id=" + id + ", tipo=" + tipo + ", data=" + data + ", latitude=" + latitude + ", logitude="
				+ logitude + ", complemento=" + complemento + "]";
	}

	
	
	
}
