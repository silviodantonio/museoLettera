package it.uniroma3.siw.spring.service;

import it.uniroma3.siw.spring.repository.CollezioneRepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Collezione;

@Service
public class CollezioneService {
	
	@Autowired
	private CollezioneRepository collezioneRepository;

	@Transactional
	public List<Collezione> getAll() {
		return collezioneRepository.findAll();
	}
	
	@Transactional
	public Collezione getCollezione(Long id) {
		return collezioneRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public Collezione inserisci(Collezione collezione) {
		return collezioneRepository.save(collezione);
	}
	
	@Transactional
	public boolean alreadyExists(Collezione collezione) {
		List<Collezione> collezioni = this.collezioneRepository.findByNome(collezione.getNome());
		if (collezioni.size() > 0)
			return true;
		else 
			return false;
	}
	
	@Transactional
	public void elimina(Long id) {
		collezioneRepository.deleteById(id);
	}
}
