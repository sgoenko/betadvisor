package com.hay.betadvisor.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "team")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@OneToMany(mappedBy = "team")
	private Set<Synonym> synonyms;

	public Team() {
	}

	public Set<Synonym> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(Set<Synonym> synonyms) {
		this.synonyms = synonyms;
	}

	public void addSynonym(String name) {
		Synonym synonym = new Synonym();
		
		synonym.setName(name);
		synonym.setTeam(this);
		
		synonyms.add(synonym);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
