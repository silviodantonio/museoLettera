package it.uniroma3.siw.spring.repository;
import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OperaRepository extends CrudRepository<Opera, Long>{
		
	public List<Opera> findByAutoreAndNome(Artista autore, String nome);
	
	public List<Opera> findByCollezione(Collezione collezione);
}
