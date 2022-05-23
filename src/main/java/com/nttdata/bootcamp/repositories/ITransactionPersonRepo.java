package com.nttdata.bootcamp.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.models.TransactionPerson;

public interface ITransactionPersonRepo extends ReactiveMongoRepository<TransactionPerson, String> {

}
