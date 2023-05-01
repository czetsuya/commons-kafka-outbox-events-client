package com.czetsuyatech.events.client;

import com.czetsuyatech.events.client.messaging.consumers.OkConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

  private final OkConsumer okConsumer;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {

    okConsumer.startConsumer();
  }
}
