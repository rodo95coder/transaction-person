package com.nttdata.bootcamp.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.nttdata.bootcamp.models.RequestTransactBootcoin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {
  private final KafkaTemplate<String, String> kafkaTemplate;
  
  @Value(value= "${kafka.topic.name}")
  private String topic;
  
  public void publishMessage(String message) {
    //id is the second parameter
    ListenableFuture<SendResult<String, String>> futureMessage = kafkaTemplate.send(this.topic,message);
    futureMessage.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

      @Override
      public void onSuccess(SendResult<String, String> result) {
        log.info("Message {} has been sent to the topic succesfully ", message);
      }

      @Override
      public void onFailure(Throwable ex) {
        log.error("There was an error while sending a message: {}", ex.getMessage());
      }
    });
  }
  
  /*public void publishMessageTransaction(RequestTransactBootcoin message) {
    //id is the second parameter
    ListenableFuture<SendResult<String, String>> futureMessage = kafkaTemplate.send(this.topic,message);
    futureMessage.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
      @Override
      public void onSuccess(SendResult<String, String> result) {
        log.info("Message {} has been sent to the topic succesfully ", message);
      }
      @Override
      public void onFailure(Throwable ex) {
        log.error("There was an error while sending a message: {}", ex.getMessage());
      }
    });
  }*/

}