package com.reactor.reactor_pracitce;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class ColdSequenceExample {

  /**
   * 콜드 시퀀스
   * 마블다이어 그램 상 subscriber 마다 새로운 시퀀스를 생성 (타임라인을 공유 하지 않는다.)
   */
  public static void main(String[] args) {
    Flux<String> coldFlux = Flux.fromIterable(Arrays.asList("RED", "GREEN", "BLUE"))
      .map(String::toLowerCase);

    coldFlux.subscribe(data -> log.info("Subscriber 1: {}", data));

    log.info("--------------------------------");
    
    coldFlux.subscribe(data -> log.info("Subscriber 2: {}", data));


  }
}
