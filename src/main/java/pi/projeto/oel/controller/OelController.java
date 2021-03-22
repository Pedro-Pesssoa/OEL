package pi.projeto.oel.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String cadastrarUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "verifique se todos os campos, estão respondidos");
			return "cadastroUsuario";
		}

		System.out.println(usuario);
		ur.save(usuario);

		return "cadastroUsuario";
	}

	@GetMapping("/lixeira")
	public String cadastrarLixeira(@Valid Lixeira lixeira, BindingResult result) {
		
		if (result.hasErrors()) {
			return "cadastroLixeira";
		}

		System.out.println(lixeira);
		lr.save(lixeira);

		return "cadastroLixeira";
	}   
	  
	@GetMapping("/pesquisa")
	public ModelAndView filtroBairro(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView mv = new ModelAndView("listLixeiras");
		mv.addObject("lixeiras", lr.findByBairro(nomepesquisa));
		mv.addObject("lixeira", new Lixeira());
		return mv;
	}
	
	@GetMapping("/pesquisa2") 
	public ModelAndView filtroRua(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView mv = new ModelAndView("listLixeiras");
		mv.addObject("lixeiras", lr.findByRua(nomepesquisa));
		mv.addObject("lixeira", new Lixeira());
		return mv;
	}
	 
	@PostMapping("/{idlixeira}")  
	public String denunciaLixeira(@PathVariable Long idlixeira, Denuncia denuncia) {
		
		
		System.out.println("id lixeira"+ idlixeira);
		System.out.println(denuncia);
		
		Optional<Lixeira> opt = lr.findById(idlixeira);
		
		if (opt.isEmpty()) {
			return "redirect:/oel";
		}
		
		Lixeira lixeira = opt.get();
		denuncia.setLixeira(lixeira);
		dr.save(denuncia);
		
		return "redirect:/{idlixeira}";
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
