package it.uniroma3.siw.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.repository.OperaRepository;

@Service
public class OperaService {

	@Autowired
	private OperaRepository operaRepository; 
	
	@Transactional
	public Opera inserisci(Opera opera) {
		return operaRepository.save(opera);
	}
	
	@Transactional
	public boolean alreadyExists(Opera opera) {
		List<Opera> opere = this.operaRepository.findByAutoreAndNome(opera.getAutore(), opera.getNome());
		if (opere.size() > 0)
			return true;
		else 
			return false;
	}
	
	@Transactional
	public Opera getOpera(Long id) {
		return operaRepository.findById(id).orElse(null);
	}
	
	@Transactional 
	public List<Opera> getOpereByCollezione(Collezione collezione){
		return operaRepository.findByCollezione(collezione);
	}
	
}
