package br.com.eventoapp.eventoapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping(name = "/")
	public String index() {
		return "index";
	}
}
