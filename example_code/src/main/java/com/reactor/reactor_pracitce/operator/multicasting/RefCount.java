package com.reactor.reactor_pracitce.operator.multicasting;

import java.time.Duration;

import com.reactor.reactor_pracitce.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

@Slf4j
public class RefCount {

  // refCount : 최초 N 명의 구독자 등록 시 데이터 발생을 시작하고, 구독자가 0명이 되면 데이터 발생을 중지한다.
  // 구독자가 취소되고 테이터 발생 중지 후 재구독을 하였을때는 타임라인 처음부터 다시시작한다.
  public static void main(String[] args) {
   Flux<Long> publisher = Flux
    .interval(Duration.ofMillis(500))
    .publish().refCount(3);

    Disposable disposable1 = publisher.subscribe(data -> log.info("# subscriber1: {}", data));
    Disposable disposable2 = publisher.subscribe(data -> log.info("# subscriber2: {}", data));
    Disposable disposable3 = publisher.subscribe(data -> log.info("# subscriber3: {}", data));

    TimeUtils.sleep(2100);

    disposable1.dispose();
    disposable2.dispose();

    TimeUtils.sleep(5000);
    
    // 이상태에서도 타임라인은 유지된 상태로 hot sequence 이다.
    publisher.subscribe(data -> log.info("# subscriber1: {}", data));   
    publisher.subscribe(data -> log.info("# subscriber2: {}", data));

    TimeUtils.sleep(10000);

  }
}
