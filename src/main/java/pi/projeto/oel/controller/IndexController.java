package pi.projeto.oel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String inicio() {
		System.out.println("Chamou o método index");
		return "redirect:/oel";
	}

}
