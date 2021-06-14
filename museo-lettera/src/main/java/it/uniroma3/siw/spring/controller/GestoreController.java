package it.uniroma3.siw.spring.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import it.uniroma3.siw.spring.FileUploadUtil;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.CuratoreService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class GestoreController {
	
	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private CuratoreService curatoreService;
	
	@Autowired
	private OperaService operaService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping (value = "/default", method = RequestMethod.GET)
	public void discriminatoreUtente(Model model) {
		logger.debug("reached /default");
		this.getOperazioniAdmin(model);
	}
	
	@RequestMapping (value = "/admin/operazioniGestore", method = RequestMethod.GET)
	public String getOperazioniAdmin(Model model) {
		logger.debug("reached /default");
		return "admin/operazioniGestore";
	}
	
	// SEZIONE PER GESTIRE LE OPERE
	
	@RequestMapping (value = "admin/addOpera", method = RequestMethod.GET)
	public String goToAddOpera(Model model) {
		logger.debug("reached operaForm");
		model.addAttribute("artisti", artistaService.getAll());
		return "admin/operaForm";
	}
	
	@RequestMapping (value = "admin/addOpera", method = RequestMethod.POST)
	public String submitAddOpera(Model model,
		@ModelAttribute("autore") String id,
		@ModelAttribute("titolo") String titolo,
		@ModelAttribute("descrizione") String descrizione,
		@ModelAttribute("anno") String anno,
		@RequestParam("image") MultipartFile multipartFile) {
		
		logger.debug("reached operaForm");
		// Ottengo il nome del file immagine "pulito"
		String artworkName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		Opera opera = new Opera();
		opera.setAutore(artistaService.getArtista(Long.parseLong(id)));
		
		if(opera.getAutore() == null) {
			logger.debug("Autore null");
		}
		
		opera.setNome(titolo);
		opera.setDescrizione(descrizione);
		opera.setAnno(anno);
		opera.setArtwork(artworkName);
		
		// Lo salvo in locale sul server
		String directory = "artworks/" + opera.getAutore().getId();
		try {
			FileUploadUtil.saveFile(directory, artworkName, multipartFile); // eccezione non gestita
			operaService.inserisci(opera);
			logger.debug("opera salvata");
		}
		catch (IOException ioe) {
			return "admin/operazioniGestore";
		}
		
		return "admin/operazioniGestore";
	}
	
	// SEZIONE PER GESTIRE LE COLLEZIONI
	
	@RequestMapping (value = "admin/addCollezione", method = RequestMethod.GET)
	public String goToAddCollezione(Model model) {
		logger.debug("reached collezioneForm");
		model.addAttribute("curatori", curatoreService.getAll());
		return "admin/collezioneForm";
	}
	
	@RequestMapping (value = "admin/addCollezione", method = RequestMethod.POST)
	public String submitAddOpera(Model model,
		@ModelAttribute("curatore") Long id,
		@ModelAttribute("titolo") String titolo,
		@ModelAttribute("descrizione") String descrizione) {
		
		Collezione collezione = new Collezione();
		collezione.setCuratore(curatoreService.getCuratore(id));
		
		if(collezione.getId() == null) {
			logger.debug("Recieved null id");
		}
		
		collezione.setNome(titolo);
		collezione.setDescrizione(descrizione);
		
		collezioneService.inserisci(collezione);
		logger.debug("opera salvata");
		
		return "admin/operazioniGestore";
	}
	
}
