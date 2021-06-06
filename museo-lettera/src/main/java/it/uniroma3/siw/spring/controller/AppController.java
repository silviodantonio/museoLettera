package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.service.ArtistaService;

@Controller
public class AppController {
	
	@Autowired
	private ArtistaService artistaService;

	@RequestMapping (value="/artisti", method = RequestMethod.GET)
	public String getArtisti(Model model) {
		model.addAttribute("artisti", artistaService.getAll());
		return "artisti.html";
	}
	
	@RequestMapping (value = {"/", "/index"}, method = RequestMethod.GET)
	public String goToHome(Model model) {
		return "index.html";
	}
	
	@RequestMapping (value = "/accesso-gestori", method = RequestMethod.GET)
	public String accessoGestori(Model model) {
		return "login.html";
	}
}
