package com.nttdata.bootcamp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nttdata.bootcamp.models.TransactionPerson;
import com.nttdata.bootcamp.models.products.SavingAccount;
import com.nttdata.bootcamp.repositories.ITransactionPersonRepo;
import com.nttdata.bootcamp.services.ITransactionPersonService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class TransactionPersonImpl implements ITransactionPersonService {
	
	@Autowired
	ITransactionPersonRepo tprepo;
	
	@Autowired
	private RestTemplate clientRest;

	@Override
	public Flux<TransactionPerson> findAll() {
		return tprepo.findAll();
	}

	@Override
	public Mono<TransactionPerson> findById(String id) {
		return tprepo.findById(id);
	}

	@Override
	public Mono<TransactionPerson> save(TransactionPerson transactionPerson) {
		SavingAccount savingAccount =clientRest.getForObject("http://localhost:8024/savingAccount/findById/"+transactionPerson.getIdProduct(), SavingAccount.class);
		System.out.println(savingAccount);
		int newamount = Integer.parseInt(transactionPerson.getAmount())  + Integer.parseInt(savingAccount.getAccountingBalance());
		savingAccount.setAccountingBalance(String.valueOf(newamount));
		SavingAccount samodicated = clientRest.patchForObject("http://localhost:8024/savingAccount/update", savingAccount,SavingAccount.class);
		log.info("a product was modificated"+samodicated.getId());
		return tprepo.save(transactionPerson);
	}

	@Override
	public Mono<Void> delete(TransactionPerson transactionPerson) {
		return tprepo.delete(transactionPerson);
	}
	
}
