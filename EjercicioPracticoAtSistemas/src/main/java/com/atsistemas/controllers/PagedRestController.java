package com.atsistemas.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.entities.Account;
import com.atsistemas.services.AccountService;

@RestController
@RequestMapping("/test")
public class PagedRestController {
	
	@Autowired
	private AccountService accountService;
	
	public AccountService setAccountService(AccountService accountService) {
		System.out.println("Lo usa");
		return accountService;
	}
	
	//http://localhost:8080/EjercicioPracticoAtSistemas/rest/test/findAll
	@RequestMapping(value= "/findAll", method= RequestMethod.GET)
	public ResponseEntity<Page<Account>> findAll(Pageable pageable) {
		
		Page<Account> accounts = accountService.findAll(pageable);
		return new ResponseEntity<Page<Account>>(accounts, HttpStatus.OK);
		
	}

}
