package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class AppController {
	
	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private OperaService operaService;
	
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping (value="/artisti", method = RequestMethod.GET)
	public String getArtisti(Model model) {
		model.addAttribute("artisti", artistaService.getAll());
		return "artisti";
	}
	
	@RequestMapping (value="/artisti/{id}", method = RequestMethod.GET)
	public String getArtista(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artista", artistaService.getArtista(id));
		return "artista";
	}
	
	@RequestMapping (value="/opere/{id}", method = RequestMethod.GET)
	public String getOpera(@PathVariable("id") Long id, Model model) {
		model.addAttribute("opera", operaService.getOpera(id));
		return "opera";
	}
	
	@RequestMapping (value="/collezioni", method = RequestMethod.GET)
	public String getCollezioni(Model model) {
		model.addAttribute("collezioni", collezioneService.getAll());
		return "collezioni";
	}
	
	@RequestMapping (value="/collezioni/{id}", method = RequestMethod.GET)
	public String getCollezione(@PathVariable("id") Long id, Model model) {
		model.addAttribute("collezione", collezioneService.getCollezione(id));
		return "collezione";
	}
	
	@RequestMapping (value = {"/", "/index"}, method = RequestMethod.GET)
	public String goToHome(Model model) {
		return "index";
	}
	
	@RequestMapping (value = "/login", method = RequestMethod.GET)
	public String accessoGestori(Model model) {
		logger.debug("reached /login");
		return "login";
	}
}
