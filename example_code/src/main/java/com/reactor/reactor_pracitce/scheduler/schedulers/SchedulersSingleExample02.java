package com.reactor.reactor_pracitce.scheduler.schedulers;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.single()을 적용 후,
 * 첫번째 Schedulers.single()에서 할당 된 쓰레드를 재사용 한다.
 */
public class SchedulersSingleExample02 {
    public static void main(String[] args) {

        doTask("task1")
                .subscribe(Logger::onNext);

        doTask("task2")
                .subscribe(Logger::onNext);


        TimeUtils.sleep(200L);
    }

    /**
     * 작업 플로우 생성
     * newSingle() 스케줄러를 사용하여 작업 플로우 생성 , 호출될때마다 single() 과는 다르게 새로운 
     * 단일 쓰레드 추가 생성
     * @param taskName
     * @return
     */
    private static Flux<Integer> doTask(String taskName) {
        return Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .doOnNext(data -> Logger.doOnNext(taskName, "fromArray", data))
                .publishOn(Schedulers.newSingle("new-single", true))
                .filter(data -> data > 3)
                .doOnNext(data -> Logger.doOnNext(taskName, "filter", data))
                .map(data -> data * 10)
                .doOnNext(data -> Logger.doOnNext(taskName, "map", data));
    }
}
