package com.reactor.reactor_pracitce.scheduler.operator;

import com.reactor.reactor_pracitce.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulerOperatorExample02 {
    public static void main(String[] args) throws InterruptedException {

        // publishOn : 해당 함수 아래부터 선언된 sequence 부터는 지정한 Thread 에서 진행이 되도록 만든다.
        Flux.fromArray(new Integer[] {1, 3, 5, 7})
                .doOnNext(data -> Logger.doOnNext("fromArray", data))
                .publishOn(Schedulers.parallel())       // 아래부터 parallel Thread에서 진행
                .filter(data -> data > 3)
                .doOnNext(data -> Logger.doOnNext("filter", data))
                .map(data -> data * 10)
                .doOnNext(data -> Logger.doOnNext("map", data))
                .subscribe(Logger::onNext);

        Thread.sleep(100L);
    }
}
