package pi.projeto.oel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class LoginController {
	
	@GetMapping("/oel/login")
	public String login () {
		return "/oel/login";
	}
	
	
}
