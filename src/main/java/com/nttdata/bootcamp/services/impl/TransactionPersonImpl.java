package com.nttdata.bootcamp.services.impl;

import java.math.BigDecimal;
import java.util.function.Function;

import com.nttdata.bootcamp.exceptions.TypeTransactionException;
import com.nttdata.bootcamp.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcamp.models.TransactionPerson;
import com.nttdata.bootcamp.models.products.*;
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
	
	private WebClient customerServiceClient = WebClient.builder().baseUrl("http://localhost:8024").build();

	private Function<Mono<SavingAccount>, Mono<SavingAccount>>
			updateSavingAccount = (objeto) -> customerServiceClient.patch()
			.uri("/savingAccount/update")
			.body(objeto, SavingAccount.class)
			.retrieve()
			.bodyToMono(SavingAccount.class);

	@Override
	public Flux<TransactionPerson> findAll() {
		return tprepo.findAll();
	}

	@Override
	public Mono<TransactionPerson> findById(String id) {
		return tprepo.findById(id);
	}

	@Override
	public Mono<TransactionPerson> saveSavingAccount(TransactionPerson transactionPerson) {
		Mono<SavingAccount> savingAccount = customerServiceClient.get()
				.uri("/savingAccount/findById/"+transactionPerson.getIdProduct())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(SavingAccount.class)
				.flatMap(p->{
					switch (transactionPerson.getTypeTransaction()){
						case Constants.WITHDRAWAL:
							p.withdrawal(new BigDecimal(transactionPerson.getAmount()));
							break;
						case Constants.DEPOSIT:
							p.deposit(new BigDecimal(transactionPerson.getAmount()));
							break;
						default:
							return Mono.error( new TypeTransactionException("The type transaction is incorret"));
					}
					return Mono.just(p);
				});
		
		return updateSavingAccount.apply(savingAccount).flatMap(p->{
			return tprepo.save(transactionPerson);
		});
	}

	@Override
	public Mono<TransactionPerson> saveBusinessCredit(TransactionPerson transactionPerson) {
		Mono<SavingAccount> savingAccount = customerServiceClient.get()
				.uri("/savingAccount/findById/"+transactionPerson.getIdProduct())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(SavingAccount.class)
				.flatMap(p->{
					switch (transactionPerson.getTypeTransaction()){
						case Constants.WITHDRAWAL:
							p.withdrawal(new BigDecimal(transactionPerson.getAmount()));
							break;
						case Constants.DEPOSIT:
							p.deposit(new BigDecimal(transactionPerson.getAmount()));
							break;
						default:
							return Mono.error( new TypeTransactionException("The type transaction is incorret"));
					}
					return Mono.just(p);
				});
		
		return updateSavingAccount.apply(savingAccount).flatMap(p->{
			return tprepo.save(transactionPerson);
		});
	}

	@Override
	public Mono<TransactionPerson> saveCurrentAccount(TransactionPerson transactionPerson) {
		Mono<SavingAccount> savingAccount = customerServiceClient.get()
				.uri("/savingAccount/findById/"+transactionPerson.getIdProduct())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(SavingAccount.class)
				.flatMap(p->{
					switch (transactionPerson.getTypeTransaction()){
						case Constants.WITHDRAWAL:
							p.withdrawal(new BigDecimal(transactionPerson.getAmount()));
							break;
						case Constants.DEPOSIT:
							p.deposit(new BigDecimal(transactionPerson.getAmount()));
							break;
						default:
							return Mono.error( new TypeTransactionException("The type transaction is incorret"));
					}
					return Mono.just(p);
				});
		
		return updateSavingAccount.apply(savingAccount).flatMap(p->{
			return tprepo.save(transactionPerson);
		});
	}

	@Override
	public Mono<TransactionPerson> saveFixedtermaccount(TransactionPerson transactionPerson) {
		Mono<SavingAccount> savingAccount = customerServiceClient.get()
				.uri("/savingAccount/findById/"+transactionPerson.getIdProduct())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(SavingAccount.class)
				.flatMap(p->{
					switch (transactionPerson.getTypeTransaction()){
						case Constants.WITHDRAWAL:
							p.withdrawal(new BigDecimal(transactionPerson.getAmount()));
							break;
						case Constants.DEPOSIT:
							p.deposit(new BigDecimal(transactionPerson.getAmount()));
							break;
						default:
							return Mono.error( new TypeTransactionException("The type transaction is incorret"));
					}
					return Mono.just(p);
				});
		
		return updateSavingAccount.apply(savingAccount).flatMap(p->{
			return tprepo.save(transactionPerson);
		});
	}

	@Override
	public Mono<TransactionPerson> savePersonalCredit(TransactionPerson transactionPerson) {
		Mono<SavingAccount> savingAccount = customerServiceClient.get()
				.uri("/savingAccount/findById/"+transactionPerson.getIdProduct())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(SavingAccount.class)
				.flatMap(p->{
					switch (transactionPerson.getTypeTransaction()){
						case Constants.WITHDRAWAL:
							p.withdrawal(new BigDecimal(transactionPerson.getAmount()));
							break;
						case Constants.DEPOSIT:
							p.deposit(new BigDecimal(transactionPerson.getAmount()));
							break;
						default:
							return Mono.error( new TypeTransactionException("The type transaction is incorret"));
					}
					return Mono.just(p);
				});
		
		return updateSavingAccount.apply(savingAccount).flatMap(p->{
			return tprepo.save(transactionPerson);
		});
	}
	
	@Override
	public Mono<Void> delete(TransactionPerson transactionPerson) {
		return tprepo.delete(transactionPerson);
	}
	
}
