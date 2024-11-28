package com.reactor.reactor_pracitce.context;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
/**
 * Context의 특징
 *  - Context는 체인의 맨 아래에서부터 위로 전파된다.
 *      - 따라서 Operator 체인에서 Context read 읽는 동작이 Context write 동작 밑에 있을 경우에는 write된 값을 read할 수 없다.
 */
public class ContextFetureExample02 {
    public static void main(String[] args) {
        final String key1 = "id";
        final String key2 = "name";

        // contextWrite 는 DownStream -> UpStream 방향으로 데이터를 전달하기 때문에 Subscriber 선언 바로 전에 호출 해야함!
        Mono
            .deferContextual(ctx ->
                    Mono.just(ctx.get(key1))
            )
            .publishOn(Schedulers.parallel())
            .contextWrite(context -> context.put(key2, "Kevin"))      // 해당 선언 부분은 transformDeferredContextual 에서는 읽을 수 없음
            .transformDeferredContextual((mono, ctx) ->
                    mono.map(data -> data + ", " + ctx.getOrDefault(key2, "Tom"))
            )
            .contextWrite(context -> context.put(key1, "itVillage"))  // 해당 선언 부분은 정상
            .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
