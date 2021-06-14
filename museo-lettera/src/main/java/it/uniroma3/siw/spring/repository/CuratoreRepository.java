package it.uniroma3.siw.spring.repository;

import java.util.List;
import it.uniroma3.siw.spring.model.Curatore;

import org.springframework.data.repository.CrudRepository;

public interface CuratoreRepository extends CrudRepository<Curatore, Long>{

	public List<Curatore> findAll();
}
