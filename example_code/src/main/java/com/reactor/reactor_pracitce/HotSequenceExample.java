package com.reactor.reactor_pracitce;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class HotSequenceExample {

  /**
   * 핫 시퀀스
   * 마블다이어 그램 상 subscriber 들이 공유하는 타임라인을 가진다. (타임라인을 공유한다.)
   * 즉, 첫번째 subscriber 가 시퀀스를 구독하면 두번째 subscriber 도 동일한 타임라인의 시퀀스를 구독할 수 있다.
   */
  public static void main(String[] args) {
    // Hot Sequence
    Flux<String> concertFlux = Flux.fromStream(Stream.of("Singer A", "Singer B", "Singer C", "Singer D", "Singer E"))
      .delayElements(Duration.ofSeconds(1))
      .share();

    concertFlux.subscribe(data -> log.info("Subscriber 1: {}", data));

    try {
      Thread.sleep(2500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    concertFlux.subscribe(data -> log.info("Subscriber 2: {}", data));

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  } 
}
