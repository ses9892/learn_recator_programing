package com.reactor.reactor_pracitce.operator.sequenceModify;

import java.time.Duration;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;

import reactor.core.publisher.Flux;

public class Zip01 {
  public static void main(String[] args) {
    // zip 연산자를 사용하여 두 Flux를 동시에 구독하고 각각의 원소를 쌍으로 결합
    // 가장 느린 Publisher의 속도에 맞춰진다.
    // 즉, Flux.jush(4,5,6) 속도에 맞춰서 tuple로 emit된다.
    Flux.zip(
      Flux.just(1,2,3).delayElements(Duration.ofMillis(100)),
      Flux.just(4,5,6).delayElements(Duration.ofMillis(150)),
      (t, u) -> t*u           // 각각의 원소를 쌍으로 결합하는 함수 (사용하지 않는다면 tuple로 emit됨)
      
    ).subscribe(Logger::onNext);

    TimeUtils.sleep(2000);
  }
}
