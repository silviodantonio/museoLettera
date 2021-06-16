package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.service.CollezioneService;

@Component
public class CollezioneValidator implements Validator {
	@Autowired
	private CollezioneService collezioneService;
	
    private static final Logger logger = LoggerFactory.getLogger(CollezioneValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		
		logger.debug("Invocato opera validator");
		
		Collezione collezione = (Collezione)o;
		
		if (this.collezioneService.alreadyExists(collezione)) {
			logger.debug("collezione duplicata");
			errors.reject("collezione.duplicato");
		}
		
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Collezione.class.equals(aClass);
	}
}
