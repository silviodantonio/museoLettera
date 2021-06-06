package it.uniroma3.siw.spring.repository;

import java.util.List;
import it.uniroma3.siw.spring.model.Artista;

import org.springframework.data.repository.CrudRepository;

public interface ArtistaRepository extends CrudRepository<Artista, Long>{

	public List<Artista> findAll();
}
