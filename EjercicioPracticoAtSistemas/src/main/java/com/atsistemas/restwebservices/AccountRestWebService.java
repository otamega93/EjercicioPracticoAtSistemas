package com.atsistemas.restwebservices;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.atsistemas.entities.Account;
import com.atsistemas.services.AccountService;

//@Service
//@Transactional(readOnly=true)
public class AccountRestWebService implements AccountService {
	
	@Transactional(readOnly=false)
	public Account create(Account account) throws RestClientException, URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForEntity(new URI("http://localhost:8080/EjercicioPracticoAtSistemas/accounts"), account, Account.class).getBody();
	}
	
	public Page<Account> findAll(Pageable pageable) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			return (Page<Account>) restTemplate.getForEntity(new URI("http://localhost:8080/EjercicioPracticoAtSistemas/accounts"), Page.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Account save(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
