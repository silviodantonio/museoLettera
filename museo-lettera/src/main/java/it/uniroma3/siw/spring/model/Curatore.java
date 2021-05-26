package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Curatore {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private String luogoNascita;
	private String email;
	private String telefono;
	private String matricola;
	@OneToMany(mappedBy = "curatore")
	private List<Collezione> collezioni;
	
}
