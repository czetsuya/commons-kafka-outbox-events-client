package com.czetsuyatech.events.client.web.controllers;

import com.czetsuyatech.events.client.services.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {

  private final ProducerService producerService;

  @GetMapping("/uni-events/uc-1")
  public void uc1() {

    log.info("Receive uc-1 request");

    producerService.sendMessage();
  }
}
