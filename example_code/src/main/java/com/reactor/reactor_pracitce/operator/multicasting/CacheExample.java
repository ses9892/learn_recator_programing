package com.reactor.reactor_pracitce.operator.multicasting;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class CacheExample {
    public static void main(String[] args) {
        // 데이터를 생성하고 캐시하는 Flux
        Flux<Integer> cachedFlux = Flux.range(1, 3)
            .doOnSubscribe(s -> log.info("신규 구독 발생"))
            .doOnNext(n -> log.info("새로운 데이터 생성: {}", n))
            .cache();  // 데이터를 캐시

        // 첫 번째 구독
        log.info("첫 번째 구독 시작");
        cachedFlux.subscribe(
            data -> log.info("Subscriber 1: {}", data)
        );

        // 두 번째 구독
        log.info("두 번째 구독 시작");
        cachedFlux.subscribe(
            data -> log.info("Subscriber 2: {}", data)
        );
    }
}