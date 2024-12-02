package com.reactor.reactor_pracitce.operator.creater;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Create02 {
  static int start = 1;
  static int end = 4;

  public static void main(String[] args) {

    Flux.create(sink -> {
      sink.onRequest(n -> {
        log.info("onRequest : {}", n);
        TimeUtils.sleep(500);

        for (int i = start; i <= end; i++) {
          sink.next(i);
        }
        start += 4;
        end += 4;
      });

      sink.onDispose(() -> log.info("onDispose"));
    }, FluxSink.OverflowStrategy.DROP)                    // 버퍼 오버플로우 전략 (버퍼 최대 사이즈는 2개로 지정했으며, 버퍼 초과 이후 emit된 데이터는 버림)
    .subscribeOn(Schedulers.boundedElastic())
    .publishOn(Schedulers.parallel() , 2)       // second param : request 요청 갯수
    .subscribe(Logger::onNext);

    TimeUtils.sleep(3000);
  }
}
