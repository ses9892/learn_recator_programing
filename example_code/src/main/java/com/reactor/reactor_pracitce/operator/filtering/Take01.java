package com.reactor.reactor_pracitce.operator.filtering;

import java.time.Duration;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;

import reactor.core.publisher.Flux;

public class Take01 {
  // take : 첫번째 인자로 전달된 갯수만큼 데이터를 받아옴
  public static void main(String[] args) {
    Flux.range(1, 10)
    .take(3)
    .subscribe(Logger::onNext);

    Flux.range(10, 20)
    .delayElements(Duration.ofMillis(500))
    .take(Duration.ofMillis(2500))    // 2.5초 동안 데이터를 받아옴
    .subscribe(Logger::onNext);

    TimeUtils.sleep(3000);
  }
}
