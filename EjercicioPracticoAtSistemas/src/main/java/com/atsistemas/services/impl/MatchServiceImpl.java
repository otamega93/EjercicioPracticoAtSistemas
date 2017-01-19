package com.atsistemas.services.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atsistemas.entities.Account;
import com.atsistemas.entities.Match;
import com.atsistemas.repositories.AccountRepository;
import com.atsistemas.repositories.MatchRepository;
import com.atsistemas.services.MatchService;
import com.google.common.collect.Lists;

@Service
@Transactional(readOnly=true)
public class MatchServiceImpl implements MatchService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private MatchRepository matchRepository;

	private List<Account> accounts = new ArrayList<Account>();
	
	private List<Match> matches = new ArrayList<>();
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	public Page<Account> partnerAffinityMatcher(Long idForMatcher, Pageable pageable) {

			Account account = new Account();
			account = accountRepository.findOne(idForMatcher);
			int accountAge = calculateAge(account.getBirthDate(), LocalDate.now());
			float accountHeight = account.getHeight();
		
			accounts = Lists.newArrayList(accountRepository.findAll());
			
			//List to fill it with every match
			List<Account> matchedAccountsForAffinity = new ArrayList<>();
		
			for (Account accountPointer : accounts) {
				
				//Check if it's the same user
				if (accountPointer.getId() == account.getId()) {
					//They are the same, so continue to next iteration
					continue;
				}

				//get accountPointerHeight
				float accountPointerHeight = accountPointer.getHeight();
				
				//get Age from birthdate
				int accountPointerAge = calculateAge(accountPointer.getBirthDate(), LocalDate.now());
				
				//get the difference of age
				int differenceOfAge = Math.abs(accountAge - accountPointerAge); 
				//get the difference of height
				float differenceOfHeight = Math.abs(accountHeight - accountPointerHeight);
					//check if the age gap is less or equal than by 10 and if the difference of height is less than by 50
					if (differenceOfAge <= 10 && differenceOfHeight < 50) {
							System.out.println("La persona número " + account.getId() + " es afin a la persona " + accountPointer.getId());
							//Add matching to list so later build the json page response 
							matchedAccountsForAffinity.add(accountPointer);
					}
					else {
						System.out.println("La persona número " + account.getId() + " es NO es afin a la persona " + accountPointer.getId());
					}
			}
			
			System.out.println("matchedAccounts: " + matchedAccountsForAffinity.toString());
			//building the paged json
			PageImpl<Account> accountPages= new PageImpl<>(matchedAccountsForAffinity, pageable, accountRepository.count());
			return accountPages;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public Page<Account> partnerIdealMatcher(Long idForMatcher, Pageable pageable) {

		//Create new account object to compare with ALL the rest of accounts within the dB
		Account account = new Account();
		account = accountRepository.findOne(idForMatcher);
		int accountAge = calculateAge(account.getBirthDate(), LocalDate.now());
		float accountHeight = account.getHeight();
	
		//Find all the accounts in DB
		accounts = Lists.newArrayList(accountRepository.findAll());
		//List to fill it with every match
		List<Account> matchedAccountsForIdeal = new ArrayList<>();
	
		//Iterate the list...
		for (Account accountPointer : accounts) {
			
			//Check if it's the same user
			if (accountPointer.getId() == account.getId()) {
				//They are the same, so continue to next iteration
				continue;
			}

			//get accountPointerHeight
			float accountPointerHeight = accountPointer.getHeight();
			
			//get Age from birthdate
			int accountPointerAge = calculateAge(accountPointer.getBirthDate(), LocalDate.now());
			
			//get the difference of age and see if the result is negative
			int differenceOfAge = Math.abs(accountAge - accountPointerAge); 
			
			//get the difference of height
			float differenceOfHeight = Math.abs(accountHeight - accountPointerHeight);

			//check if the age gap is less or equal than by 5 and if the difference of height is less than by 50 and the sex is different
			if (differenceOfAge <= 5 && differenceOfHeight < 10 && !accountPointer.getSex().equals(account.getSex())) {
				
				System.out.println("La persona número " + account.getId() + " es ideal para la persona " + accountPointer.getId());
				matchedAccountsForIdeal.add(accountPointer);
				
				//found a single match, ends matching, return the single match build the paged json
				PageImpl<Account> accountPages= new PageImpl<>(matchedAccountsForIdeal, pageable, accountRepository.count());
				return accountPages;
			}
			else
				System.out.println("La persona número " + account.getId() + " es NO ideal para la persona " + accountPointer.getId());
		}
		
		//build the paged json
		PageImpl<Account> accountPages= new PageImpl<>(matchedAccountsForIdeal, pageable, accountRepository.count());
		return accountPages;
	}

	@Override
    public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

	@Override
	public List<Match> findAll() {
		matches = Lists.newArrayList(matchRepository.findAll());
		return matches;
	}

}
