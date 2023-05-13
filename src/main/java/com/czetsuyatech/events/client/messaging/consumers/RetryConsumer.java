package com.czetsuyatech.events.client.messaging.consumers;

import com.czetsuyatech.events.client.messaging.constants.TopicKeys;
import com.czetsuyatech.events.config.UniAppConfig;
import com.czetsuyatech.events.mappers.EventMapper;
import com.czetsuyatech.events.messaging.consumers.UniEventRetryConsumer;
import com.czetsuyatech.events.messaging.exceptions.EventFailedException;
import com.czetsuyatech.events.messaging.exceptions.EventRetryableException;
import com.czetsuyatech.events.messaging.messages.UniEvent;
import com.czetsuyatech.events.services.UniDeadLetterService;
import com.czetsuyatech.events.services.UniInboundEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RetryConsumer extends UniEventRetryConsumer {

  private final ObjectMapper om;

  public RetryConsumer(
      UniAppConfig appConfig,
      ConsumerFactory<String, String> consumerFactory,
      UniInboundEventService uniInboundEventService,
      UniDeadLetterService uniDeadLetterService,
      EventMapper eventMapper,
      ObjectMapper om) {

    super(appConfig, consumerFactory, uniInboundEventService, uniDeadLetterService, eventMapper);

    this.om = om;
  }

  @Override
  protected String getTopicKey() {
    return TopicKeys.CONSUMER_OK;
  }

  @Override
  protected void preConsumeMessage() {

    log.debug("Before consuming the message");
  }

  @Override
  protected void handleMessage(UniEvent uniEvent) throws EventRetryableException, EventFailedException {
    log.info("Handling message={}", uniEvent);
  }

  @Override
  public ObjectMapper getObjectMapper() {
    return om;
  }
}
