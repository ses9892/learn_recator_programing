package com.reactor.reactor_pracitce.sinks;

import com.reactor.reactor_pracitce.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

public class SinkManyExample04 {
    public static void main(String[] args) {
        // 구독 이후, emit된 데이터 중에서 최신 데이터 2개만 replay 한다. + 2개만 하고 끝은 아님 구독시점에 타임라인 시퀀스만 적용할뿐
        Sinks.Many<Integer> replaySink = Sinks.many().replay().limit(2);
        Flux<Integer> fluxView = replaySink.asFlux();

        replaySink.emitNext(1, FAIL_FAST);
        replaySink.emitNext(2, FAIL_FAST);
        replaySink.emitNext(3, FAIL_FAST);

        fluxView.subscribe(data -> Logger.onNext("Subscriber1", data));

        replaySink.emitNext(4, FAIL_FAST);

        fluxView.subscribe(data -> Logger.onNext("Subscriber2", data));
    }
}
