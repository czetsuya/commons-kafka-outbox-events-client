package com.czetsuyatech.events.client;

import com.czetsuyatech.events.client.messaging.consumers.IgnoredConsumer;
import com.czetsuyatech.events.client.messaging.consumers.FailedConsumer;
import com.czetsuyatech.events.client.messaging.consumers.ProcessedConsumer;
import com.czetsuyatech.events.client.messaging.consumers.RetriedConsumer;
import com.czetsuyatech.events.client.messaging.producers.RetriedProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

  private final ProcessedConsumer processedConsumer;
  private final FailedConsumer failedConsumer;
  private final IgnoredConsumer ignoredConsumer;
  private final RetriedConsumer retriedConsumer;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {

    processedConsumer.start();
    failedConsumer.start();
    ignoredConsumer.start();
    retriedConsumer.start();
  }
}
