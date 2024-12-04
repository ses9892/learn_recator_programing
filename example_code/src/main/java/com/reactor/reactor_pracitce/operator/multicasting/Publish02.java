package com.reactor.reactor_pracitce.operator.multicasting;

import java.time.Duration;

import com.reactor.reactor_pracitce.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
@Slf4j
public class Publish02 {

  private static ConnectableFlux<String> publisher;
  private static int checkedAudience;

  static {
    publisher = Flux.just("Concert part1", "Concert part2", "Concert part3")
      .delayElements(Duration.ofMillis(300))
      .publish();
  }

  public static void main(String[] args) {
    checkAudience();    // 관중 검사
    TimeUtils.sleep(500);
    publisher.subscribe(data -> log.info("# audience1: is watching {}", data));   // 관중 1 등록  
    checkedAudience++;

    TimeUtils.sleep(500);
    publisher.subscribe(data -> log.info("# audience2: is watching {}", data));   // 관중 2 등록
    checkedAudience++;

    checkAudience();    // 관중 검사

    TimeUtils.sleep(500);
    publisher.subscribe(data -> log.info("# audience3: is watching {}", data));   // 관중 3 등록
    checkedAudience++;

    TimeUtils.sleep(1000);
  }

  public static void checkAudience() {
    if(checkedAudience >= 2) {
      publisher.connect();
    }
  }
}
