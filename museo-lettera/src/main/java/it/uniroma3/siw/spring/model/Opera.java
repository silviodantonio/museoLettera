package it.uniroma3.siw.spring.model;

import java.beans.Transient;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Opera {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String descrizione;
	@ManyToOne
	private Artista autore;
	private String anno;
	@ManyToOne
	private Collezione collezione;
	private String artwork;
	
	@Transient
    public String getArtworkPath() {
        if (artwork == null || id == null) return null;
         
        return "/artworks/" + autore.getId() + "/" + artwork;
    }
	
}
