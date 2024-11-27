package com.reactor.reactor_pracitce.sinks;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

@Slf4j
public class SinksExample01 {
    public static void main(String[] args) throws InterruptedException {
        int tasks = 6;
        // Create 는 sinks 작업을 직접 프로그래밍언어로 실행 하기 위함
        Flux.create(
                (FluxSink<String> sink) -> {
                    IntStream
                            .range(1, tasks)
                            .forEach(n -> sink.next(doTask(n)));
                })
                .subscribeOn(Schedulers.boundedElastic())           //subscribeOn : 업스트림 연산이 실행될 쓰레드를 지정
                                                                    // Schedulers.boundedElastic() : I/O 작업처럼 blocking 작업을 처리하기에 적합한 스케줄러
                .doOnNext(n -> log.info("# create() : {}", n)) 
                .publishOn(Schedulers.parallel())              // publishOn : 다운스트림 연산이 실행될 쓰레드를 지정
                                                                // Schedulers.parallel() : 비동기 작업을 처리하기에 적합한 스케줄러
                .map(result -> result + " success")
                .doOnNext(n -> log.info("# map() : {} " ,n))    
                .publishOn(Schedulers.parallel())               // publishOn : 다운스트림 연산이 실행될 쓰레드를 지정
                .subscribe(data -> log.info("# onNext : {}" ,data));    // subscribe : 구독자가 구독하는 시점을 지정

        Thread.sleep(500L);
    }

    public static String doTask(int task) {
        return "task" + task + " result";
    }
}
