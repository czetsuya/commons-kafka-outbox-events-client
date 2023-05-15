package com.czetsuyatech.events.client.services.impl;

import com.czetsuyatech.events.client.config.AppConfig;
import com.czetsuyatech.events.client.messaging.producers.IgnoredProducer;
import com.czetsuyatech.events.client.messaging.producers.KoProducer;
import com.czetsuyatech.events.client.messaging.producers.OkProducer;
import com.czetsuyatech.events.client.services.ProducerService;
import com.czetsuyatech.events.messaging.messages.UniEvent;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerServiceImpl implements ProducerService {

  private final AppConfig appConfig;
  private final OkProducer okProducer;
  private final KoProducer koProducer;
  private final IgnoredProducer ignoredProducer;

  @Transactional
  @Override
  public void okEvent() {

    log.debug("Performing UC-1 - Sending / Consuming Ok");

    UniEvent uniEvent = getEvent();

    okProducer.sendEvent(uniEvent);
  }

  @Transactional
  @Override
  public void koEvent(String error) {

    log.debug("Performing UC-2 - Failed event");

    UniEvent uniEvent = getEvent();
    uniEvent.setEntityName("KO-" + error);
    uniEvent.setDescription("UC2 - Ko");

    koProducer.sendEvent(uniEvent);
  }

  @Transactional
  @Override
  public void ignoredEvent() {

    log.debug("Performing UC-3 - Ignored event");

    UniEvent uniEvent = getEvent();
    uniEvent.setEntityName("IGNORED");
    uniEvent.setDescription("UC3 - Ignored");

    ignoredProducer.sendEvent(uniEvent);
  }

  private UniEvent getEvent() {

    return UniEvent.builder()
        .eventId(UUID.randomUUID().toString())
        .parentEventId(null)
        .eventDate(Instant.now())
        .createdBy("SYSTEM")
        .eventType("TEST")
        .eventVersion(1)
        .eventSource(appConfig.getName())
        .entityName("OK")
        .callbackTopic(null)
        .entityData("{\"name\":\"Edward Legaspi\",\"alias\":\"czetsuya\",\"age\":39}")
        .description("UC1 - Sending / Consuming Ok")
        .build();
  }
}
