package com.czetsuyatech.events.client.web.controllers;

import com.czetsuyatech.events.client.services.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UseCaseController {

  private final ProducerService producerService;

  @GetMapping("/uni-events/ok-event")
  public void okEvent() {

    log.info("Receive uc-1 request");

    producerService.okEvent();
  }

  @GetMapping("/uni-events/ko-retry-event")
  public void koRetryEvent() {

    log.info("Receive uc-2 request - retry");

    producerService.koEvent("RETRY");
  }

  @GetMapping("/uni-events/ko-failed-event")
  public void koFailedEvent() {

    log.info("Receive uc-2 request - failed");

    producerService.koEvent("FAILED");
  }

  @GetMapping("/uni-events/ignored-event")
  public void ignoredEvent() {

    log.info("Receive uc-3 request");

    producerService.ignoredEvent();
  }
}
