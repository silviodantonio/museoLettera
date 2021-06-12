package it.uniroma3.siw.spring.repository;

import java.util.List;
import it.uniroma3.siw.spring.model.Collezione;

import org.springframework.data.repository.CrudRepository;

public interface CollezioneRepository extends CrudRepository<Collezione, Long>{
	
	public List<Collezione> findAll();
}
