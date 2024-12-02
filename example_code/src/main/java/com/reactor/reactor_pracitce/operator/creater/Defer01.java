package com.reactor.reactor_pracitce.operator.creater;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Defer01 {

  public static void main(String[] args) {

    /**
     * defer : 구독시점에 원본 Publisher를 생성하는 연산자
     */

    log.info("start : {}", LocalDateTime.now());
    Mono<LocalDateTime> justMono = Mono.just(LocalDateTime.now());
    Mono<LocalDateTime> deferMono = Mono.defer(() -> Mono.just(LocalDateTime.now()));

    TimeUtils.sleep(1000);

    justMono.subscribe(data -> log.info("justMono1 : {}", data));
    deferMono.subscribe(data -> log.info("deferMono1 : {}", data));

    TimeUtils.sleep(2000);

    justMono.subscribe(data -> log.info("justMono2 : {}", data));
    deferMono.subscribe(data -> log.info("deferMono2 : {}", data));

    // start : 2024-12-02T17:59:09.209601600
    // justMono1 : 2024-12-02T17:59:09.213006300    
    // deferMono1 : 2024-12-02T17:59:10.258568800   --> 구독되는 시점에 데이터 Emit
    // justMono2 : 2024-12-02T17:59:09.213006300
    // deferMono2 : 2024-12-02T17:59:12.267915600   --> 구독되는 시점에 데이터 Emit

  }
}
