package pi.projeto.oel.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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

	private static String caminhoImg = "/Users/laura/Desktop/workspace/OEL/src/main/resources/static/imgLixo/";

	@Autowired
	private LixeiraRepository lr;

	@Autowired
	private UsuarioRepository ur;

	@Autowired
	private DenunciaRepository dr;

	@GetMapping("/lixeira")
	public String formLixeira(Lixeira lixeira) {

		return "/oel/cadastroLixeira";

	}

	@GetMapping("/usuario")
	public String formUsuario(Usuario usuario) {

		return "/oel/cadastroUsuario";
	}

	@PostMapping("/usuario")
	public String cadastrarUsuario(@Valid Usuario usuario, BindingResult result) {
		System.out.println("daleee " + usuario);

		if (result.hasErrors()) {

			System.out.println("erro");
			return formUsuario(usuario);
		}

		System.out.println(usuario);
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		ur.save(usuario);

		return "/oel/cadastroUsuario";
	}

	@PostMapping("/lixeira")
	public String cadastrarLixeira(@Valid Lixeira lixeira, BindingResult result,
			@RequestParam("file") MultipartFile arquivo) {

		if (result.hasErrors()) {
			System.out.println("erro");
			return formLixeira(lixeira);
		}

		System.out.println(lixeira);
		lr.save(lixeira);

		try {
			if (!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths
						.get(caminhoImg + String.valueOf(lixeira.getId()) + " - " + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);

				lixeira.setNomeImg(String.valueOf(lixeira.getId()) + " - " + arquivo.getOriginalFilename());
				lr.save(lixeira);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "/oel/cadastroLixeira";
	}

	@GetMapping("/pesquisa")
	public ModelAndView filtroBairro(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView mv = new ModelAndView("/oel/listLixeiras");
		mv.addObject("lixeiras", lr.findByBairroAndAllIgnoreCase(nomepesquisa));
		mv.addObject("lixeira", new Lixeira());
		return mv;
	}

	@GetMapping("/pesquisa2")
	public ModelAndView filtroRua(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView mv = new ModelAndView("/oel/listLixeiras");
		mv.addObject("lixeiras", lr.findByRuaAndAllIgnoreCase(nomepesquisa));
		mv.addObject("lixeira", new Lixeira());
		return mv;
	}

	@GetMapping("/pesquisa3")
	public ModelAndView filtroTipo(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView mv = new ModelAndView("/oel/listLixeiras");
		mv.addObject("lixeiras", lr.findByTipoAndAllIgnoreCase(nomepesquisa));
		mv.addObject("lixeira", new Lixeira());
		return mv;
	}

	@GetMapping
	public ModelAndView listar() {
		List<Lixeira> lixeiras = lr.findAll();
		ModelAndView mv = new ModelAndView("/oel/listLixeiras");
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

		md.setViewName("/oel/detalhes");
		Lixeira lixeira = opt.get();
		md.addObject("lixeira", lixeira);

		return md;
	}

	@GetMapping("/{idLixeira}/fazerDenuncia")
	public ModelAndView fazerDenuncia(@PathVariable Long idLixeira, Denuncia denuncia) {
		ModelAndView md = new ModelAndView();

		Optional<Lixeira> optLixeira = lr.findById(idLixeira);

		if (optLixeira.isEmpty()) {
			md.setViewName("redirect:/oel");
			return md;
		}

		Lixeira lixeira = optLixeira.get();

		md.setViewName("/oel/denuncia");
		md.addObject("lixeira", lixeira);

		return md;

	}

	@PostMapping("/{idLixeira}/fazerDenuncia")
	public ModelAndView realizarDenuncia(@PathVariable Long idLixeira, @Valid Denuncia denuncia) {
		ModelAndView md = new ModelAndView();
		Optional<Lixeira> optLixeira = lr.findById(idLixeira);

		if (optLixeira.isEmpty()) {
			md.setViewName("redirect:/oel");
			return md;
		}

		Lixeira lixeira = optLixeira.get();

		md.setViewName("redirect:/oel/{idLixeira}");
		md.addObject("lixeira", lixeira);
		md.addObject("denuncia", denuncia);

		denuncia.setLixeira(lixeira);

		dr.save(denuncia);

		return md;

	}
	
	@GetMapping("/listDenuncia")
	public ModelAndView listaDenunica() {
		
		ModelAndView mv = new ModelAndView("oel/listDenuncia");
				
		List<Denuncia> listDenuncia = dr.findAll();
		mv.addObject("listDenuncia", listDenuncia);
		return mv;
	}

	@GetMapping("/{id}/remover")
	public String apagarLixeira(@PathVariable Long id) {
		
		Optional<Lixeira> opt = lr.findById(id);
		
		if (!opt.isEmpty()) {
			Lixeira lixeira = opt.get();
			
			Optional<Denuncia> denuncias = dr.findByLixeira(lixeira);
			
			dr.deleteAll();
			lr.delete(lixeira);
		}
		
		return "redirect:/oel";
	}

	@GetMapping("/{idLixeira}/mostrarImg/{imagem}")
	@ResponseBody
	public byte[] retornarImg(@PathVariable("imagem") String imagem) {
		File imagemArquivo = new File(caminhoImg + imagem);
		if (imagem != null || imagem.trim().length() > 0) {
			try {
				return Files.readAllBytes(imagemArquivo.toPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
