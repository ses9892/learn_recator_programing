package com.reactor.reactor_pracitce.sinks;

import com.reactor.reactor_pracitce.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkManyExample02 {

    public static void main(String[] args) {
        // multicast : 여러 Subscriber에게 데이터를 emit 한다. + Hot Sequence 방식이다.
        Sinks.Many<Integer> multicastSink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Integer> flux = multicastSink.asFlux();

        multicastSink.emitNext(1 , Sinks.EmitFailureHandler.FAIL_FAST);
        multicastSink.emitNext(2 , Sinks.EmitFailureHandler.FAIL_FAST);

        flux.subscribe(data -> Logger.onNext("Subscriber 1 : {}" , data));

        // 1구독자가 받은 데이터는 emit 되지 않는다. ( Hot Sequence 이기때문 )
        flux.subscribe(data -> Logger.onNext("Subscriber 2 : {}" , data));

        // 3은받지.. 1,2 구독자 둘다..
        multicastSink.emitNext(3 , Sinks.EmitFailureHandler.FAIL_FAST);

    }
}
