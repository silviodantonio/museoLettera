package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Artista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private LocalDate dataMorte;
	private String luogoNascita;
	private String luogoMorte;
	private String nazionalita;
	private String bio;
	@OneToMany(mappedBy = "autore")
	private List<Opera> opere;
	
}
