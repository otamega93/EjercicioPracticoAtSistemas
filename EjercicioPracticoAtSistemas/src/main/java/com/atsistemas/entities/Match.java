package com.atsistemas.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Matches")
public class Match implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String idealMatches;
	
	private String affinityMatches;

	public Match(Long id, String name, String idealMatches, String affinityMatches) {
		super();
		this.id = id;
		this.name = name;
		this.idealMatches = idealMatches;
		this.affinityMatches = affinityMatches;
	}

	public Match() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getIdealMatches() {
		return idealMatches;
	}

	public void setIdealMatches(String idealMatches) {
		this.idealMatches = idealMatches;
	}

	public String getAffinityMatches() {
		return affinityMatches;
	}

	public void setAffinityMatches(String affinityMatches) {
		this.affinityMatches = affinityMatches;
	}

	@Override
	public String toString() {
		return "Match [id=" + id + ", name=" + name + ", idealMatches=" + idealMatches + ", affinityMatches="
				+ affinityMatches + "]";
	}
	
}
