package com.reactor.reactor_pracitce.scheduler.operator;

import com.reactor.reactor_pracitce.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulerOperatorExample06 {

    public static void main(String[] args) {

        // fromArray() -> doOnNext() [ boundedElastic Thread ]
        // filter() -> doOnNext -> map() -> doOnNext [ parallel Thread ]
        Flux.fromArray(new Integer[] {1, 3, 5, 7})
                .doOnNext(data -> Logger.doOnNext("fromArray", data))
                .publishOn(Schedulers.parallel())
                .filter(data -> data > 3)
                .doOnNext(data -> Logger.doOnNext("filter", data))
                .subscribeOn(Schedulers.boundedElastic())
                .map(data -> data * 10)
                .doOnNext(data -> Logger.doOnNext("map", data))
                .subscribe(Logger::onNext);
    }
}
