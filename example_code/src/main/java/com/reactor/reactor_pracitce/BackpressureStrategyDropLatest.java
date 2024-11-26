package com.reactor.reactor_pracitce;

import java.time.Duration;

import com.reactor.reactor_pracitce.utils.Logger;

import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class BackpressureStrategyDropLatest {
  public static void main(String[] args) throws InterruptedException {

    Flux
        .interval(Duration.ofMillis(300L))
        .doOnNext(data -> Logger.info("### emited by original Flux : {}", data))
        .onBackpressureBuffer(2,
            dropped -> Logger.info("## OverFlow & dropped : {}", dropped),
            BufferOverflowStrategy.DROP_OLDEST)
        .doOnNext(data -> Logger.info("## emmited by Buffer : {}", data))
        .publishOn(Schedulers.parallel(), false, 1)
        .subscribe(data -> {
          try {
            Thread.sleep(1000L);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          Logger.onNext(data);
        },
            error -> Logger.onError(error));

    Thread.sleep(3000L);
  }
}
