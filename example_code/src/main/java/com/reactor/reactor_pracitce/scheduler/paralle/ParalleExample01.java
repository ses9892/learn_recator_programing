package com.reactor.reactor_pracitce.scheduler.paralle;

import com.reactor.reactor_pracitce.utils.Logger;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParalleExample01 {
  public static void main(String[] args) throws InterruptedException {
    Flux.fromArray(new Integer[] { 1, 3, 5, 7, 9, 11, 13, 15 })
            .doOnNext(Logger::doOnNext)
            .subscribeOn(Schedulers.boundedElastic())
            .publishOn(Schedulers.parallel())
            .subscribe(Logger::onNext);

    Thread.sleep(100L);
  }
}
