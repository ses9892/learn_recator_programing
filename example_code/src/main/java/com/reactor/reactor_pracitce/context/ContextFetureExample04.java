package com.reactor.reactor_pracitce.context;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context의 특징
 *  - inner Sequence 내부에서는 외부 Context에 저장된 데이터를 읽을 수 있다.
 *  - inner Sequence 내부에서 Context에 저장된 데이터는 inner Sequence 외부에서 읽을 수 없다.
 */
public class ContextFetureExample04 {
  public static void main(String[] args) {
      String key1 = "id";
      Mono.just("Kevin")
          .transformDeferredContextual((stringMono, contextView) -> contextView.get("job"))
          // flatMap : 데이터를 변환하고 새로운 Mono를 반환
          // 일반 Map API를 쓸 경우 평탄화가 안되어 코드가 복잡해짐 + 길어짐
          .flatMap(name -> Mono.deferContextual(ctx ->
                  Mono.just(ctx.get(key1) + ", " + name)
                      .transformDeferredContextual((mono, innerCtx) ->
                              mono.map(data -> data + ", " + innerCtx.get("job"))
                      )
                      .contextWrite(context -> context.put("job", "Software Engineer"))     // 해당 선언은 외부 Context에서 사용할 수는없음 즉 , flatMap 내부에서만 사용 가능
              )
          )
          .publishOn(Schedulers.parallel())
          .contextWrite(context -> context.put(key1, "itVillage"))
          .subscribe(Logger::onNext);

      TimeUtils.sleep(100L);
  }
}