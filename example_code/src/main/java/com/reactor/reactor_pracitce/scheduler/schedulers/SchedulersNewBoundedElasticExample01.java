package com.reactor.reactor_pracitce.scheduler.schedulers;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class SchedulersNewBoundedElasticExample01 {

    public static void main(String[] args) {
        // 2개의 쓰레드를 생성하고, 2개의 쓰레드를 사용하는 최대 큐 사이즈를 2로 설정
        // 쓰레드 이름은 "I/O-Thread"
        // 쓰레드는 기본적으로 큐를 가지고 있고 queuedTaskCap으로 queue 사이즈를 설정
        Scheduler scheduler = Schedulers.newBoundedElastic(2, 2, "I/O-Thread");
        Mono<Integer> mono =
                Mono
                        .just(1)
                        .subscribeOn(scheduler);

        Logger.info("# Start");

        mono.subscribe(data -> {
            Logger.onNext("subscribe 1 doing", data);
            TimeUtils.sleep(3000L);
            Logger.onNext("subscribe 1 done", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 2 doing", data);
            TimeUtils.sleep(3000L);
            Logger.onNext("subscribe 2 done", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 3 doing", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 4 doing", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 5 doing", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 6 doing", data);
        });

        // Queue가 꽉차서 Error 가난다.
        // Exceptions$ReactorRejectedExecutionException
//        mono.subscribe(data -> {
//            Logger.onNext("subscribe 7 doing", data);
//        });
    }
}
