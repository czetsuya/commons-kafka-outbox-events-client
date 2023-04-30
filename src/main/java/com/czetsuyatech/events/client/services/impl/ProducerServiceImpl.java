package com.czetsuyatech.events.client.services.impl;

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

  @Transactional(propagation = Propagation.REQUIRED)
  @Override
  public void sendMessage() {

    log.debug("Performing uc-1 with null partitionKey");

    UniEvent uniEvent = UniEvent.builder()
        .eventId(UUID.randomUUID().toString())
        .parentEventId(null)
        .eventDate(Instant.now())
        .createdBy("SYSTEM")
        .eventType("TEST")
        .eventVersion(1)
        .eventSource("")
        .entityName("TEST")
        .callbackTopic(null)
        .entityData("{\"name\":\"Edward Legaspi\",\"salary\":300000,\"age\":39}")
        .description("Hello world")
        .build();

    uniEventProducer.sendEvent("uc-1", null, uniEvent);
  }
}
