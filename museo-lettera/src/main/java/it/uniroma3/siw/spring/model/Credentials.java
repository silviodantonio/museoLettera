package it.uniroma3.siw.spring.model;

import javax.persistence.*;

import lombok.Data;
@Entity
@Data
public class Credentials {
	
	public static final String DEFAULT_ROLE = "DEFAULT";
	public static final String ADMIN_ROLE = "ADMIN";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String role;
	
	// Si riferiscono ad un amministratore (null) 
	// oppure ad un curatore
	@OneToOne(cascade = CascadeType.ALL)
	private Curatore curatore;
	
}
