package com.reactor.reactor_pracitce.scheduler.schedulers;

import com.reactor.reactor_pracitce.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.immediate()을 적용하기 전.
 * 2개의 parallel 쓰레드가 할당된다.
 */
public class SchedulersImmediateExample01 {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[] {1, 3, 5, 7})
                .publishOn(Schedulers.parallel())
                .filter(data -> data > 3)
                .doOnNext(data -> Logger.doOnNext("filter", data))
                .publishOn(Schedulers.parallel())
                .map(data -> data * 10)
                .doOnNext(data -> Logger.doOnNext("map", data))
                .subscribe(Logger::onNext);

        Thread.sleep(200L);
    }
}
