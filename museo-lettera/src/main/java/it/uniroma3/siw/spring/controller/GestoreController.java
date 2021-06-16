package it.uniroma3.siw.spring.controller;

import java.io.IOException;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import it.uniroma3.siw.spring.FileUploadUtil;
import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.repository.CollezioneRepository;
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
	
	@Autowired
	private OperaValidator operaValidator;
	
	@Autowired
	private CollezioneValidator collezioneValidator;
	
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
		model.addAttribute("opera", new Opera());
		model.addAttribute("artista", new Artista());
		model.addAttribute("collezioni", collezioneService.getAll());
		return "admin/operaForm";
	}
	
	@RequestMapping (value = "admin/addOpera", method = RequestMethod.POST)
	public String submitAddOpera(Model model,
		@ModelAttribute("collezione") String collezione,
		@ModelAttribute("artista") Artista artista,
		@ModelAttribute("opera") Opera opera,
		@RequestParam("image") MultipartFile multipartFile,
		BindingResult bindingResult) {
		
		this.operaValidator.validate(opera, bindingResult);
		
		if(!bindingResult.hasErrors()) {

			opera.setAutore(artista);
			
			if (this.operaService.alreadyExists(opera)) {
				logger.debug("opera duplicata");
				bindingResult.reject("titolo.duplicato");
				model.addAttribute("artisti", artistaService.getAll());
				return "admin/operaForm.html";
			}
			else {
				
				opera.setCollezione(collezioneService.getCollezione(Long.parseLong(collezione)));
			
				//fornisco il path di salvataggio delle immagini
				String directory = "artworks/" + artista.getId();
				
				// Ottengo il nome del file immagine "pulito"
				String artworkName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
							
				try {
					FileUploadUtil.saveFile(directory, artworkName, multipartFile);
					opera.setArtwork(artworkName);
					operaService.inserisci(opera);
					logger.debug("opera salvata");
				}
				
				catch (IOException ioe) {
					return "admin/operazioniGestore.html";
				}
				
				return "admin/operazioniGestore.html";
			}
		}
		
		model.addAttribute("collezioni", collezioneService.getAll());
		model.addAttribute("artisti", artistaService.getAll());
		return "admin/operaForm.html";
	}
	
	// SEZIONE PER GESTIRE LE COLLEZIONI
	
	@RequestMapping (value = "admin/addCollezione", method = RequestMethod.GET)
	public String goToAddCollezione(Model model) {
		logger.debug("reached collezioneForm");
		model.addAttribute("curatori", curatoreService.getAll());
		model.addAttribute("collezione", new Collezione());
		return "admin/collezioneForm";
	}
	
	@RequestMapping (value = "admin/addCollezione", method = RequestMethod.POST)
	public String submitAddOpera(Model model,
		@ModelAttribute("curatore") String id,
		@ModelAttribute("collezione") Collezione collezione,
		BindingResult bindingResult) {
		
		this.collezioneValidator.validate(collezione, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			collezione.setCuratore(curatoreService.getCuratore(Long.parseLong(id)));
			collezioneService.inserisci(collezione);
			logger.debug("opera salvata");
			return "admin/operazioniGestore.html";
		}
		
		model.addAttribute("curatori", curatoreService.getAll());
		return "admin/collezioneForm.html";
	}
	
	@RequestMapping (value = "admin/deleteCollezione", method = RequestMethod.GET)
	public String goToDeleteCollezione(Model model) {
		logger.debug("reached deleteCollezione");
		model.addAttribute("collezioni", collezioneService.getAll());
		return "admin/deleteCollezione.html";
	}
	
	@RequestMapping (value = "admin/deleteCollezione/{id}", method = RequestMethod.GET)
	public String goToDeleteCollezione(@PathVariable("id") String id, Model model) {
		collezioneService.elimina(Long.parseLong(id));
		return "admin/deleteCollezione.html";
	}
	
}
