package com.reactor.reactor_pracitce.scheduler.schedulers;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.immediate()을 적용 후,
 * 현재 쓰레드가 할당된다.
 *
 * 왜쓰냐?
 * 일반적인 프로덕션용으로 사용하지는 않는다.
 * 사용한다면? 불필요한 쓰레드 전환을 피하여 성능최적화
 * 테스트 , 디버깅 목적으로 코드 실행용도
 */
public class SchedulersImmediateExample02 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[] {1, 3, 5, 7})
                .publishOn(Schedulers.parallel())
                .filter(data -> data > 3)
                .doOnNext(data -> Logger.doOnNext("filter", data))
                .publishOn(Schedulers.immediate())      //  immediate  적용하여 현재작업중인 : parallel 쓰레드를 계속 할당
                .map(data -> data * 10)
                .doOnNext(data -> Logger.doOnNext("map", data))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(200L);
    }
}
