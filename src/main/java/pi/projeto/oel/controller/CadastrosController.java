package pi.projeto.oel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pi.projeto.oel.models.Lixeira;
import pi.projeto.oel.models.Usuario;
import pi.projeto.oel.repositories.LixeiraRepository;
import pi.projeto.oel.repositories.UsuarioRepository;

@Controller
@RequestMapping("/oel")
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
	
	@GetMapping
	public ModelAndView listar() {
		List<Lixeira> lixeiras = lr.findAll();
		ModelAndView mv = new ModelAndView("listLixeiras");
		mv.addObject("lixeiras", lixeiras);
		return mv;
	}
}
