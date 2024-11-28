package com.reactor.reactor_pracitce.scheduler.paralle;

import com.reactor.reactor_pracitce.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParalleExample03 {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1,2,3,4,5,6,7,8,9})    // fromArray : Operator
                .parallel(3)                      // Operator를 병렬 처리 선언
                .runOn(Schedulers.parallel())               // runOn가 같이 사용해야 병렬처리 가능
                .subscribe(Logger::onNext);

        Thread.sleep(100L);
    }
}
