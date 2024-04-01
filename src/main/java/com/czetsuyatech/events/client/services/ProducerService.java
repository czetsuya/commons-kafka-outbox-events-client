package com.czetsuyatech.events.client.services;

public interface ProducerService {

  void processEvent();

  void failEvent();

  void ignoreEvent();

  void retryEvent();
}
