package it.uniroma3.siw.spring.model;

import java.util.*;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Collezione {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String descrizione;
	@ManyToOne
	private Curatore curatore;
	@OneToMany(mappedBy = "collezione")
	private List<Opera> opere;
	
}
