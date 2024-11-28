package com.reactor.reactor_pracitce.context;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ContextFetureExample03 {
        public static void main(String[] args) {
        String key1 = "id";

        // contextWrite 는 DownStream -> UpStream 방향으로 데이터를 전달하기 때문에 Subscriber 선언 바로 전에 호출 해야함!
        //  context.put() context 데이터를 UPDATE 하기 때문에 중복 선언 시 마지막에 선언한 값으로 덮어씌어짐
        // DownStream -> UpStream 구조로 인해 "itWorld" 가 최종 출력됨
        Mono.deferContextual(ctx ->
                Mono.just("ID: " + " " + ctx.get(key1))
        )
        .publishOn(Schedulers.parallel())
        .contextWrite(context -> context.put(key1, "itWorld"))
        .contextWrite(context -> context.put(key1, "itVillage"))
        .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
