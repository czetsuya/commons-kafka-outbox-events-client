package com.czetsuyatech.events.client.services;

public interface ProducerService {

  void okEvent();
  void koEvent(String error);
  void ignoredEvent();
}
