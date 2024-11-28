package com.reactor.reactor_pracitce.scheduler.operator;

import com.reactor.reactor_pracitce.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulerOperatorExample04 {
    public static void main(String[] args) throws InterruptedException {
        
        // subscribeOn : UPStream 에서 Operation 진행 부터 별도 지정 Thread 에서 진행하도록 한다.
        // 아래 코드는 subscribeOn 으로 인해
        // fromArray 부터 subscribe 까지 boundedElastic Thread에서 연산이 된다.
        Flux.fromArray(new Integer[] {1, 3, 5, 7})
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(data -> Logger.doOnNext("fromArray", data))
                .filter(data -> data > 3)
                .doOnNext(data -> Logger.doOnNext("filter", data))
                .map(data -> data * 10)
                .doOnNext(data -> Logger.doOnNext("map", data))
                .subscribe(Logger::onNext);
        
        Thread.sleep(100L);

    }
}
