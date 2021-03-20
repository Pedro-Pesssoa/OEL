package pi.projeto.oel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pi.projeto.oel.models.Denuncia;
import pi.projeto.oel.models.Lixeira;
import pi.projeto.oel.models.Usuario;
import pi.projeto.oel.repositories.DenunciaRepository;
import pi.projeto.oel.repositories.LixeiraRepository;
import pi.projeto.oel.repositories.UsuarioRepository;

@Controller
@RequestMapping("/oel")
public class OelController {

	@Autowired
	private LixeiraRepository lr;

	@Autowired
	private UsuarioRepository ur;
	
	@Autowired
	private DenunciaRepository dr;

	@GetMapping("/usuario")
	public String cadastrarUsuario(Usuario usuario) {

		System.out.println(usuario);
		ur.save(usuario);

		return "cadastroUsuario";
	}

	@GetMapping("/lixeira")
	public String cadastrarLixeira(Lixeira lixeira) {

		System.out.println(lixeira);
		lr.save(lixeira);

		return "cadastroLixeira";
	}
	
	@GetMapping("/denuncia")
	public String denunciaLixeira(Denuncia denuncia) {
		
		System.out.println(denuncia);
		dr.save(denuncia);
		
		return "denuncia";
		
	}

	@GetMapping
	public ModelAndView listar() {
		List<Lixeira> lixeiras = lr.findAll();
		ModelAndView mv = new ModelAndView("listLixeiras");
		mv.addObject("lixeiras", lixeiras);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Lixeira> opt = lr.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/oel");
			return md;
		}

		md.setViewName("/detalhes");
		Lixeira lixeira = opt.get();
		md.addObject("lixeira", lixeira);

		return md;
	}
}
