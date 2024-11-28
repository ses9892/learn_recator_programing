package com.reactor.reactor_pracitce.scheduler.operator;

import com.reactor.reactor_pracitce.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulerOperatorExample05 {

    public static void main(String[] args) throws InterruptedException {

        //  fromArray ~ filter 까지  [ boundedElastic Thread ]
        //  map ~ subscribe 까지 [ parallel Thread ]
        Flux.fromArray(new Integer[] {1, 3, 5, 7})
                .subscribeOn(Schedulers.boundedElastic())
                .filter(data -> data > 3)
                .doOnNext(data -> Logger.doOnNext("filter", data))
                .publishOn(Schedulers.parallel())
                .map(data -> data * 10)
                .doOnNext(data -> Logger.doOnNext("map", data))
                .subscribe(Logger::onNext);

        Thread.sleep(100L);

    }
}
