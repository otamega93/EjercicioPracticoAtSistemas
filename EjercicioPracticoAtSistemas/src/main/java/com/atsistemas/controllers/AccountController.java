package com.atsistemas.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.atsistemas.entities.Account;
import com.atsistemas.services.AccountService;
import com.atsistemas.services.MatchService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	public MatchService matchService;
	
	@Autowired
	public AccountService accountService;
	
	// http://localhost:8080/EjercicioPracticoAtSistemas/rest/accounts/1/partnerAffinityMatcher
	@RequestMapping(value="/{id}/partnerAffinityMatcher", method= RequestMethod.GET, produces="application/json", consumes="application/json")
	public ResponseEntity<Page<Account>> partnerAffinityMatcher(@PathVariable Long id, Pageable pageable) {
		
		Page<Account> accounts = matchService.partnerAffinityMatcher(id, pageable);
		return new ResponseEntity<Page<Account>>(accounts, HttpStatus.OK);
		
	}
	
	// http://localhost:8080/EjercicioPracticoAtSistemas/rest/accounts/1/partnerIdealMatcher
	@RequestMapping(value="/{id}/partnerIdealMatcher", method= RequestMethod.GET, produces="application/json", consumes="application/json")
	public ResponseEntity<Page<Account>> partnerIdealMatcher(@PathVariable Long id, Pageable pageable) {
		
		Page<Account> accounts = matchService.partnerIdealMatcher(id, pageable);
		return new ResponseEntity<Page<Account>>(accounts, HttpStatus.OK);
		
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<Page<Account>> findAll(Pageable pageable) {
		
		Page<Account> accounts = accountService.findAll(pageable);
		return new ResponseEntity<Page<Account>>(accounts, HttpStatus.OK);
		
	}
	
	@RequestMapping(value= "", method= RequestMethod.POST)
	public ResponseEntity<Account> create(@RequestBody Account account) {
		
		if (null != account) 
			return new ResponseEntity<Account>(accountService.save(account), HttpStatus.OK);
		
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
	
}
