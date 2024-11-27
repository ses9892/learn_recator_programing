package com.reactor.reactor_pracitce.sinks;

import com.reactor.reactor_pracitce.utils.Logger;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinkOneExample01 {
    public static void main(String[] args) {
        // emit 된 데이터 중에서 단 하나의 데이터만 Subscriber에게 전달한다. 나머지 데이터는 Drop 됨.
        Sinks.One<String> skinsOne = Sinks.one();       // Skins 를 이용해 프로그래밍적으로 Reactor value 를 emit 할 수 있게 구현
        Mono<String> mono = skinsOne.asMono();          // 이런 구현체는 Mono 또는 Flux를 받아 subscribe를 구현 할 수 있게 해준다.

        // emitValue : 데이터를 수동 emit 한다.
        // Sinks.EmitFailureHandler.FAIL_FAST : 데이터 emit 실패 시 즉시 예외를 발생시킨다.
        // Sinks.EmitFailureHandler.FAIL_SILENT : 데이터 emit 실패 시 무시한다.
        skinsOne.emitValue("Hello Reactor" , Sinks.EmitFailureHandler.FAIL_FAST);

        // 이 데이터는 emit 되지 않는다. ( Mono 이기때문 )
        skinsOne.emitValue("Hello Reactor2" , Sinks.EmitFailureHandler.FAIL_FAST);

        // subscribe : 구독자가 구독하는 시점을 지정
        mono.subscribe(data -> Logger.doOnNext("Subscribe1 : {}", data));
        mono.subscribe(data -> Logger.doOnNext("Subscribe2 : {}", data));



    }
}
