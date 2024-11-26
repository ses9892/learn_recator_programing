package com.reactor.reactor_pracitce;

import reactor.core.publisher.Flux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public class HelloReactor {
    private static final Logger log = LoggerFactory.getLogger(HelloReactor.class);

    public static void main(String[] args) {

        System.out.println("Hello Reactor1");

        // Flux : 데이터를 생성하고 Emit 하는 Publisher        
        // Mono : 단일 데이터를 생성하고 Emit 하는 Publisher

        Flux<String> sequence = Flux.just("Hello", "Reactor")
            .delayElements(Duration.ofSeconds(1))
            .map(data -> {
                log.info("map: {}", data);
                return data.toUpperCase();
            });
        
        sequence.subscribe(data -> log.info("subscribe: {}", data));

        log.info("프로그램 계속 실행...");
        
        // 비동기 실행을 위해 메인 스레드 대기
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Hello Reactor2");


        System.out.println("Hello Reactor3");
        System.out.println("Hello Reactor4");

        System.out.println("Hello Reactor5");

        System.out.println("Hello Reactor6");


    }
}
