package com.reactor.reactor_pracitce;


import java.time.Duration;

import com.reactor.reactor_pracitce.utils.Logger;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class BackpressureStrategyDrop {
  public static void main(String[] args) throws InterruptedException {
    // 1밀리초 간격으로 데이터를 생성하는 Flux를 생성
    Flux
        .interval(Duration.ofMillis(1L))
        // Backpressure 전략으로 DROP을 사용
        // Downstream이 처리할 수 없는 데이터는 버리고, 버려진 데이터를 로깅
        .onBackpressureDrop(dropped -> Logger.info("## dropped : {}" , dropped))
        // 병렬 스케줄러에서 실행되도록 설정
        .publishOn(Schedulers.parallel())
        // 구독자 설정
        .subscribe(
            // 데이터를 처리하는 로직
            data -> {
              try {
                // 데이터 처리에 5밀리초가 걸리는 것을 시뮬레이션
                Thread.sleep(5L);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              // 처리된 데이터를 로깅
              Logger.onNext(data);
            },
            // 에러 발생시 처리하는 로직
            error -> Logger.onError(error));
    
    // 프로그램이 바로 종료되지 않도록 2초간 대기
    Thread.sleep(2000L);
  }
}
