package com.atsistemas.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atsistemas.entities.Account;

public interface AccountService {

	public Account save(Account account);
	
	public List<Account> findAll();
	
	public boolean delete(Long id);

	public Page<Account> findAll(Pageable pageable);
	
}