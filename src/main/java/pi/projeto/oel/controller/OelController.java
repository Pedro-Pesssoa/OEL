package pi.projeto.oel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OelController {
	
	@RequestMapping("/cadastrarUsuario")
	public String cadastrarUsuario () {
		return "cadastroUsuario";
		
	}
	@RequestMapping("/cadastrarLixeira")
	public String cadastrarLixeira () {
		return "cadastroLixeira";
		
	}
}
