package com.nttdata.bootcamp.services;

import com.nttdata.bootcamp.models.TransactionPerson;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionPersonService {
	public Flux<TransactionPerson> findAll();
	public Mono<TransactionPerson> findById(String id);
	public Mono<TransactionPerson> saveSavingAccount(TransactionPerson transactionPerson);
	public Mono<TransactionPerson> saveBusinessCredit(TransactionPerson transactionPerson);
	public Mono<TransactionPerson> saveCurrentAccount(TransactionPerson transactionPerson);
	public Mono<TransactionPerson> saveFixedtermaccount(TransactionPerson transactionPerson);
	public Mono<TransactionPerson> savePersonalCredit(TransactionPerson transactionPerson);
	public Mono<Void> delete(TransactionPerson transactionPerson);
}
