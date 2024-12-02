package com.reactor.reactor_pracitce.operator.sequenceModify;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Merge02 {
public static void main(String[] args) {
    String[] useStates = { "A", "B", "C", "D", "E", "F" };

    // merge 연산자를 사용하여 여러 Mono를 동시에 실행 (Mono에 지연시간을 걸어놔서 순서가 보장되지 않음)
    Flux.merge(getMeltDownRecoveryMessage(useStates))
        .subscribe(Logger::onNext);

    // 비동기 실행 결과를 기다리기 위해 메인 스레드 대기
    TimeUtils.sleep(2000);
}

public static List<Mono<String>> getMeltDownRecoveryMessage(String[] useStates) {
    Random random = new Random();
    ArrayList<Mono<String>> messages = new ArrayList<>();

    for (String useState : useStates) {
        messages.add(
            Mono.just(useState)
                .map(data -> data + " Done")
                .delayElement(Duration.ofMillis(random.nextInt(500))) // 0~500ms 랜덤 지연
        );
    }
    return messages;
}
}
