package com.reactor.reactor_pracitce.operator.multicasting;

import java.time.Duration;

import com.reactor.reactor_pracitce.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
@Slf4j
public class Publish03 {

  private static Flux<String> publisher;

  // autoConnect 호출 시 최초 2명의 관중 등록 시 데이터 발생을 자동으로 시작한다.
  // publish 만 사용할때는 수동 connect 호출필요
  static {
    publisher = Flux.just("Concert part1", "Concert part2", "Concert part3")
      .delayElements(Duration.ofMillis(300))
      .publish()
      .autoConnect(2);
  }

  public static void main(String[] args) {
    publisher.subscribe(data -> log.info("# audience1: is watching {}", data));   // 관중 1 등록  

    TimeUtils.sleep(500);
    publisher.subscribe(data -> log.info("# audience2: is watching {}", data));   // 관중 2 등록

    TimeUtils.sleep(500);
    publisher.subscribe(data -> log.info("# audience3: is watching {}", data));   // 관중 3 등록

    TimeUtils.sleep(3000);
  }
}
