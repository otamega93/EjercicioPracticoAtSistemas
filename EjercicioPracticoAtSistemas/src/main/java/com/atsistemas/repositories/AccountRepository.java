package com.atsistemas.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.entities.Account;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Long>{

	public Account findByUsername(String username);
}
