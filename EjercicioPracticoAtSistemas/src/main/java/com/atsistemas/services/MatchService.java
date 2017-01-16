package com.atsistemas.services;

import java.time.LocalDate;
import java.util.List;

import com.atsistemas.entities.Match;

public interface MatchService {

	public boolean partnerIdealMatcher(Long id);
	
	public boolean partnerAffinityMatcher(Long id);

	public int calculateAge(LocalDate birthDate, LocalDate currentDate);
	
	public List<Match> findAll();
	
}
