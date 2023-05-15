package com.czetsuyatech.events.client.messaging.producers;

import com.czetsuyatech.events.client.messaging.constants.TopicKeys;
import com.czetsuyatech.events.config.UniAppConfig;
import com.czetsuyatech.events.messaging.producers.AbstractUniEventProducer;
import com.czetsuyatech.events.messaging.producers.UniEventProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KoProducer extends AbstractUniEventProducer {

  public KoProducer(UniAppConfig appConfig,
      UniEventProducer uniEventProducer) {
    super(appConfig, uniEventProducer);

  }

  @Override
  protected String getTopicKey() {
    return TopicKeys.TOPIC_KO;
  }
}
