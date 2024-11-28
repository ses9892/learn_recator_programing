package com.reactor.reactor_pracitce.scheduler.operator;

import com.reactor.reactor_pracitce.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulerOperatorExample03 {
    public static void main(String[] args) throws InterruptedException {

        // publishOn : 해당 함수 아래부터 선언된 sequence 부터는 지정한 Thread 에서 진행이 되도록 만든다.
        // 또다른 publishOn을 기준으로 각각 다른 Thread에서 진행
        Flux.fromArray(new Integer[] {1, 3, 5, 7})
                .doOnNext(data -> Logger.doOnNext("fromArray", data))
                .publishOn(Schedulers.parallel())
                .filter(data -> data > 3)                                   // A Thread
                .doOnNext(data -> Logger.doOnNext("filter", data))  // A Thread
                .publishOn(Schedulers.parallel())
                .map(data -> data * 10)                                     // B Thread
                .doOnNext(data -> Logger.doOnNext("map", data))     // B Thread
                .subscribe(Logger::onNext);

        Thread.sleep(100L);
    }
}
