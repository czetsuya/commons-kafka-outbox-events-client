package com.czetsuyatech.events.client.services.impl;

import com.czetsuyatech.events.client.config.AppConfig;
import com.czetsuyatech.events.client.services.ProducerService;
import com.czetsuyatech.events.messaging.messages.UniEvent;
import com.czetsuyatech.events.messaging.producers.UniEventProducer;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerServiceImpl implements ProducerService {

  private final UniEventProducer uniEventProducer;
  private final AppConfig appConfig;

  @Transactional(propagation = Propagation.REQUIRED)
  @Override
  public void sendMessageUc1() {

    log.debug("Performing UC-1 - Sending / Consuming Ok");

    UniEvent uniEvent = UniEvent.builder()
        .eventId(UUID.randomUUID().toString())
        .parentEventId(null)
        .eventDate(Instant.now())
        .createdBy("SYSTEM")
        .eventType("TEST")
        .eventVersion(1)
        .eventSource(appConfig.getName())
        .entityName("TEST")
        .callbackTopic(null)
        .entityData("{\"name\":\"Edward Legaspi\",\"alias\":\"czetsuya\",\"age\":39}")
        .description("UC1 - Sending / Consuming Ok")
        .build();

    uniEventProducer.sendEvent("Unified.Kafka.Events.in", null, uniEvent);
  }
}
