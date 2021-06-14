package it.uniroma3.siw.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.repository.CuratoreRepository;

@Service
public class CuratoreService {
	
	@Autowired
	private CuratoreRepository curatoreRepository;
	
	@Transactional
	public List<Curatore> getAll() {
		return (List<Curatore>) curatoreRepository.findAll();
	}
	
	@Transactional
	public Curatore getCuratore(Long id) {
		return curatoreRepository.findById(id).orElse(null);
	}
	
}
