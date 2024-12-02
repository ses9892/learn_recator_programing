package com.reactor.reactor_pracitce.operator.creater;

import java.time.Duration;
import java.time.LocalDateTime;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Defer02 {

  public static void main(String[] args) {
    log.info("start : {}", LocalDateTime.now());

    /**
     * switchIfEmpty : Upstream Publisher가 비어있을 경우 대체 Publisher를 반환하는 연산자
     * switchIfEmpty(Mono.defer(() -> sayDefault()))의 경우:
     * defer를 사용하면 실제 구독이 발생하고 데이터가 비어있을 때만 sayDefault()가 실행됩니다
     * 지연 평가(lazy evaluation)가 이루어집니다
     */

    Mono.just("Hello")
      .delayElement(Duration.ofSeconds(3))
      // .switchIfEmpty(sayDefault())  // 데이터가 없을 경우 sayDefault() 실행
      .switchIfEmpty(Mono.defer(() -> sayDefault()))  // 데이터가 없을 경우 구독시점에 sayDefault() 실행
      .subscribe(Logger::onNext);

      TimeUtils.sleep(3500);
    
  }

  public static Mono<String> sayDefault() {
    log.info("# Say Hi");
    return Mono.just("Hi");
  }
}
