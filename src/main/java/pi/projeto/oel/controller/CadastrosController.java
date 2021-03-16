package pi.projeto.oel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pi.projeto.oel.models.Lixeira;
import pi.projeto.oel.models.Usuario;
import pi.projeto.oel.repositories.LixeiraRepository;
import pi.projeto.oel.repositories.UsuarioRepository;

@Controller
public class CadastrosController {
	
	
	@Autowired
	private LixeiraRepository lr;
	
	@Autowired
	private UsuarioRepository ur;
	
	@RequestMapping("/usuario")
	public String cadastrarUsuario (Usuario usuario) {
		
		ur.save(usuario);
		
		return "cadastroUsuario";
		
	}
	@RequestMapping("/lixeira")
	public String cadastrarLixeira (Lixeira lixeira) {
		
		lr.save(lixeira);
		
		return "cadastroLixeira";
		
	}
	
	
}
