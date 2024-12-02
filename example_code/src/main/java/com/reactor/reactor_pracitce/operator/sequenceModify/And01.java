package com.reactor.reactor_pracitce.operator.sequenceModify;

import java.time.Duration;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;

import reactor.core.publisher.Mono;

public class And01 {

  // when : 여러 Mono를 동시에 실행하고 모든 작업이 완료되면 완료 이벤트를 발생시키는 연산자
  // then : 업스트림 데이터는 모두 무시하고 단지 완료 이벤트만 발생시키는 연산자
  public static void main(String[] args) {
      Mono<Void> task1 = Mono.just("데이터 백업")
          .delayElement(Duration.ofMillis(100))
          .doOnNext(Logger::onNext)
          .then();
          
      Mono<Void> task2 = Mono.just("캐시 초기화")
          .delayElement(Duration.ofMillis(150))
          .doOnNext(Logger::onNext)
          .then();
          
      Mono.when(task1, task2)
          .doOnSuccess(v -> Logger.onNext("모든 작업 완료"))
          .subscribe(Logger::onNext);
          
      TimeUtils.sleep(1000);
  }
}
