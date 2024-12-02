package com.reactor.reactor_pracitce.operator.sequenceModify;

import java.time.Duration;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;

import reactor.core.publisher.Flux;
/*
 * merge의 특징
    여러 Flux를 동시에 구독하여 먼저 도착하는 데이터부터 처리합니다
    각 Flux의 발행 시점에 따라 결과가 인터리빙(interleaving)됩니다
    원본 Flux들의 순서나 타이밍을 보장하지 않습니다
 */
public class Merge01 {
  public static void main(String[] args) {
    // 두 개의 Flux를 merge로 결합
    Flux.merge(
        // 첫 번째 Flux: 100ms 간격으로 A, B, C 발행
        Flux.just("A", "B", "C")
            .delayElements(Duration.ofMillis(100)), // 각 원소 사이에 100ms 지연

        // 두 번째 Flux: 150ms 간격으로 D, E, F 발행
        Flux.just("D", "E", "F")
            .delayElements(Duration.ofMillis(150)) // 각 원소 사이에 150ms 지연
    )
        .subscribe(Logger::onNext); // 결과를 로그로 출력

    // 비동기 실행 결과를 확인하기 위해 메인 스레드를 2초간 대기
    TimeUtils.sleep(2000);
  }
}
