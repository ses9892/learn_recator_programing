package com.reactor.reactor_pracitce.operator.filtering;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

/**
 * filterWhen : 조건에 맞는 데이터만 필터링하는 연산자
 *              조건 판단은 비동기적으로 처리됨
 * 
 * filter : 조건에 맞는 데이터만 필터링하는 연산자
 *          조건 판단은 동기적으로 처리됨
 * 
 * 사용용도 : 비동기적으로 처리되는 조건에 따라 데이터를 필터링할 때 사용
 *            데이터베이스 조회나 외부 API 호출과 같은 비동기 작업이 필요한 경우
 */
public class FilterWhen01 {

  
  public static void main(String[] args) {
    Flux<Tuple2<Integer, String>> flux = Flux.just(
      Tuples.of(1, "one"),
      Tuples.of(2, "two"),
      Tuples.of(3, "three"),
      Tuples.of(4, "four")
    );

    flux.filterWhen(tuple -> 
      Mono.just(tuple.getT1() % 2 == 0)
        .publishOn(Schedulers.parallel())
    )
    .subscribe(Logger::onNext);

    TimeUtils.sleep(1000);
  }
}
