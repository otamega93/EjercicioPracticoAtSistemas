package com.atsistemas;

import java.text.ParseException;
import java.time.LocalDate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atsistemas.entities.Account;
import com.atsistemas.services.AccountService;
import com.atsistemas.services.MatchService;

public class AppTestStart {

	public static void main(String[] args) throws ParseException {

		System.out.println("Entering the main method.");
		
		ApplicationContext context = new AnnotationConfigApplicationContext("com.atsistemas");
		
		AccountService accountService = context.getBean(AccountService.class);
		
		MatchService matchService = context.getBean(MatchService.class);
		
		//Create first account to test
		Account account = new Account();
		account.setName("matcher");
		account.setPassword("1234");
		
		account.setBirthDate(LocalDate.of(1995, 9, 23));
		
		account.setHeight(155f);
		account.setSex("M");
		
//		accountService.save(account);
		
		//Create second account to test
		Account account1 = new Account();
		account1.setName("carla");
		account1.setPassword("1234");
		

		account1.setBirthDate(LocalDate.of(1996, 05, 04));
		
		account1.setHeight(150f);
		account1.setSex("f");
		
//		accountService.save(account1);
		
		for (Account accounts : accountService.findAll()) {
			System.out.println(accounts);
		}
		
		System.out.println(matchService.calculateAge(account.getBirthDate(), LocalDate.now()));
		
		System.out.println("Begin of test:");
		matchService.partnerAffinityMatcher(1L, null);
		System.out.println("Separator");
		matchService.partnerIdealMatcher(1L, null);
		System.out.println("Separator");
		for (Account accounts : accountService.findAll()) {
			System.out.println(accounts);
		}
		
		System.out.println(matchService.findAll());
		System.out.println("End.");		
		
	}

}
