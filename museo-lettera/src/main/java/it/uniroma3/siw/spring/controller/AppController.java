package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping (value="/artisti", method = RequestMethod.GET)
	public String getArtisti(Model model) {
		model.addAttribute("artisti", artistaService.getAll());
		return "artisti.html";
	}
	
	@RequestMapping (value = {"/", "/index"}, method = RequestMethod.GET)
	public String goToHome(Model model) {
		return "index.html";
	}
	
	@RequestMapping (value = "/login", method = RequestMethod.GET)
	public String accessoGestori(Model model) {
		logger.debug("reached /login");
		return "login.html";
	}
	
	@RequestMapping (value = "/default", method = RequestMethod.GET)
	public String discriminatoreUtente(Model model) {
		logger.debug("reached /default");
		return "index";
	}
}
