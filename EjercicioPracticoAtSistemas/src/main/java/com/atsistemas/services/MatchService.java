package com.atsistemas.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atsistemas.entities.Account;
import com.atsistemas.entities.Match;

public interface MatchService {

	public int calculateAge(LocalDate birthDate, LocalDate currentDate);
	
	public List<Match> findAll();

	public Page<Account> partnerAffinityMatcher(Long idForMatcher, Pageable pageable);

	public Page<Account> partnerIdealMatcher(Long idForMatcher, Pageable pageable);
	
}
