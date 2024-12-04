package com.reactor.reactor_pracitce.operator.multicasting;

import java.time.Duration;

import com.reactor.reactor_pracitce.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
@Slf4j
public class Publish01 {
  public static void main(String[] args) {
    ConnectableFlux<Integer> flux = Flux.range(1, 5)
      .delayElements(Duration.ofMillis(300))
      .publish();    // 데이터 발생 시작 전에 구독자 등록 가능
      // publish 호출 시 데이터의 emit은 Hot Sequence 이다.

      TimeUtils.sleep(500);

      flux.subscribe(data -> log.info("# subscriber1: {}", data));

      TimeUtils.sleep(200);

      flux.subscribe(data -> log.info("# subscriber2: {}", data));

      flux.connect();     // connect 호출 시 데이터 발생 시작

      TimeUtils.sleep(1000);

      flux.subscribe(data -> log.info("# subscriber3: {}", data));

      TimeUtils.sleep(2000);

  }
}
