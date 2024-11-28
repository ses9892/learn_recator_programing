package com.reactor.reactor_pracitce.context;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ContextIntroduceExample01 {
    public static void main(String[] args) {
        String key = "message";


        // deferContextual : Context 기반으로 데이터 스트림을 생성
        Mono<String> mono = Mono.deferContextual(ctx ->
                        // ctx : 현재 컨텍스트 정보
                        Mono.just("Hello" + " " + ctx.get(key)).doOnNext(Logger::doOnNext)
                )
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel())
                .transformDeferredContextual((mono2, ctx) -> {
                    return mono2.map(data -> data + " " + ctx.get(key));
                            // .filter(data -> data.equals("Hello Reactor"));       // 추가로 더 이용해도됨
                })
                .contextWrite(context -> context.put(key, "Reactor"));


        mono.subscribe(data -> Logger.onNext(data));

        TimeUtils.sleep(100L);
    }
}
