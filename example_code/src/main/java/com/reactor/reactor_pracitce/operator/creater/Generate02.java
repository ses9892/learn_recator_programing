package com.reactor.reactor_pracitce.operator.creater;

import com.reactor.reactor_pracitce.utils.Logger;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

public class Generate02 {
  public static void main(String[] args) {
    final int dan = 3;

    // 프로그래밍 방식으로 신호를 하나씩 생성하여 {@link Flux}를 생성합니다.
    // * @param <T> 방출된 값 유형
    // * @param <S> 구독자별 사용자 정의 상태 유형
    // Tuple을 수동으로 생성하여 상태를 저장합니다.
    Flux.generate(() -> Tuples.of(dan , 1), (state , sink) -> {
      sink.next(state.getT1() + "*" + state.getT2() + "=" + (state.getT1() * state.getT2()));
      if(state.getT2() == 9) {
        sink.complete();
      }
      return Tuples.of(state.getT1(), state.getT2() + 1);
    }).subscribe(Logger::onNext);

  }
}
