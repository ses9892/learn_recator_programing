package com.reactor.reactor_pracitce.scheduler.paralle;

import com.reactor.reactor_pracitce.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParalleExample02 {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,30,31,33})
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(Logger::onNext);

        Thread.sleep(100L);
    }
}
