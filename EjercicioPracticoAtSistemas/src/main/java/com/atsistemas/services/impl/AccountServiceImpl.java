package com.atsistemas.services.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atsistemas.entities.Account;
import com.atsistemas.repositories.AccountRepository;
import com.atsistemas.services.AccountService;
import com.google.common.collect.Lists;

@Service
@Transactional(readOnly=true)
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	List<Account> accounts = new ArrayList<>();
	
	@Override
	@Transactional(readOnly=false)
	public Account save(Account account) {
		if (null != account) {
			account.setPassword(passwordEncoder.encode((account.getPassword())));
			accountRepository.save(account);
			return account;
		}
		else
			return null;
	}

	@Override
	public Page<Account> findAll(Pageable pageable) {
		accounts = Lists.newArrayList(accountRepository.findAll(pageable));
		PageImpl<Account> accountPages= new PageImpl<>(accounts, pageable, accountRepository.count());
		return accountPages;
	}

	@Override
	@Transactional(readOnly=false)
	public boolean delete(Long id) {
		if (null!= id) {
			accountRepository.delete(id);
			return true;
		}
		else {
			return false;
		}	
	}

	@Override
	public List<Account> findAll() {
		accounts = Lists.newArrayList(accountRepository.findAll());
		return accounts;
	}

}
