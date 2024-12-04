package com.reactor.reactor_pracitce.operator.sequenceSeperate;

import org.reactivestreams.Subscription;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

@Slf4j
public class Window01 {
  public static void main(String[] args) {

    /**
     * window 연산자는 원본 데이터를 특정 크기의 윈도우로 나누어 발행하는 연산자이다.
     * 
     * 예시에서는 11개의 데이터를 3개씩 나누어 Flux<Flux<Integer>> 형태로 발행한다.
     */
    Flux.range(1, 11)
        .window(3)
        .flatMap(flux -> {
          log.info("==============");
          return flux;
        })
        .subscribe(new BaseSubscriber<Integer>() {

          @Override
          protected void hookOnSubscribe(Subscription subscription) {
            request(2);
          }

          @Override
          protected void hookOnNext(Integer value) {
            log.info("# onNext : {}", value);
            request(2);
          }
        });
  }
}
