package com.reactor.reactor_pracitce.scheduler.schedulers;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import java.util.concurrent.CountDownLatch;

public class SchedulersNewParallelExample01 {
    public static void main(String[] args) {
        var scheduler = Schedulers.newParallel("Parallel Thread", 4, false);

        // 4개의 작업을 기다리는 카운트생성
        CountDownLatch latch = new CountDownLatch(4); // 4개의 작업을 기다림
        
        Mono<Integer> flux =
                Mono
                        .just(1)
                        .publishOn(scheduler);

        flux.subscribe(data -> {
            try {
                TimeUtils.sleep(5000L);
                Logger.onNext("subscribe 1", data);
            } finally {
                latch.countDown(); // 작업 완료시 카운트 감소
            }
        });

        flux.subscribe(data -> {
            try {
                TimeUtils.sleep(4000L);
                Logger.onNext("subscribe 2", data);
            } finally {
                latch.countDown(); // 작업 완료시 카운트 감소
            }
        });

        flux.subscribe(data -> {
            try {
                TimeUtils.sleep(3000L);
                Logger.onNext("subscribe 3", data);
            } finally {
                latch.countDown(); // 작업 완료시 카운트 감소
            }
        });

        flux.subscribe(data -> {
            try {
                TimeUtils.sleep(2000L);
                Logger.onNext("subscribe 4", data);
            } finally {
                latch.countDown(); // 작업 완료시 카운트 감소
            }
        });

        try {
            // 모든 작업이 완료될 때까지 대기
            latch.await();
            // 스케줄러 종료
            scheduler.dispose();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
