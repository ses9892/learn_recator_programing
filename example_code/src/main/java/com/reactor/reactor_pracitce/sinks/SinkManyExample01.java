package com.reactor.reactor_pracitce.sinks;

import com.reactor.reactor_pracitce.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

public class SinkManyExample01 {

    public static void main(String[] args) {
        // 단 하나의 Subscriber에게만 데이터를 emit할 수 있다.
        // unicast : 단 하나의 Subscriber에게만 데이터를 emit 한다.
        // onBackpressureBuffer : 백프레셔 정책을 적용한다.
        Sinks.Many<Integer> unicastSink = Sinks.many().unicast().onBackpressureBuffer();        
        Flux<Integer> fluxView = unicastSink.asFlux();

        // emitValue : 데이터를 수동 emit 한다.
        // Sinks.EmitFailureHandler.FAIL_FAST : 데이터 emit 실패 시 즉시 예외를 발생시킨다.
        // Sinks.EmitFailureHandler.FAIL_SILENT : 데이터 emit 실패 시 무시한다.
        unicastSink.emitNext(1, FAIL_FAST);
        unicastSink.emitNext(2, FAIL_FAST);

        fluxView.subscribe(data -> Logger.onNext("Subscriber1", data));

        unicastSink.emitNext(3, FAIL_FAST);

        // TODO 주석 전, 후 비교해서 보여 줄 것.
//        fluxView.subscribe(data -> Logger.onNext("Subscriber2", data));
    }
}
