package it.uniroma3.siw.spring.controller;


import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.OperaService;

@Component
public class OperaValidator implements Validator {
	@Autowired
	private OperaService operaService;
	
    private static final Logger logger = LoggerFactory.getLogger(OperaValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		
		logger.debug("Invocato opera validator");
		
		Opera opera = (Opera)o;
		int annoAttuale = Calendar.getInstance().get(Calendar.YEAR);
		int annoOpera = Integer.parseInt(opera.getAnno());
		
		logger.debug("Anno attuale " + annoAttuale);
		logger.debug("Anno opera " + annoOpera);
		
		if(annoOpera >= annoAttuale) {
			logger.debug("opera nel futuro");
			errors.reject("anno.futuro");
		}
		
		if (this.operaService.alreadyExists(opera)) {
			logger.debug("opera duplicata");
			errors.reject("titolo.duplicato");
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Opera.class.equals(aClass);
	}
}
