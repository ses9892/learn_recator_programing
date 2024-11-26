package com.reactor.reactor_pracitce;

import java.time.Duration;

import com.reactor.reactor_pracitce.utils.Logger;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class BackpressureStrategyErrorExample {
  public static void main(String[] args) throws InterruptedException {
    Flux
        .interval(Duration.ofMillis(1L))
        .onBackpressureError()
        .doOnNext(Logger::doOnNext)
        .publishOn(Schedulers.parallel())
        .subscribe(data -> {
          try {
            Thread.sleep(5L);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          Logger.onNext(data);
        },
        error -> Logger.onError(error));
    
    Thread.sleep(2000L);
  }
}
