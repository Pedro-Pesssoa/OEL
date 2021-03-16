package pi.projeto.oel.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pi.projeto.oel.models.Lixeira;
import pi.projeto.oel.repositories.LixeiraRepository;

@Controller
public class OelController {
	
	
	@Autowired
	private LixeiraRepository lr;
	
	@RequestMapping("/cadastrarUsuario")
	public String cadastrarUsuario () {
		return "cadastroUsuario";
		
	}
	@RequestMapping("/lixeira")
	public String cadastrarLixeira (Lixeira lixeira) {
		
		lr.save(lixeira);
		
		return "cadastroLixeira";
		
	}
	
	
}
