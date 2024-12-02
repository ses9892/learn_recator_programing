package com.reactor.reactor_pracitce.operator.creater;

import com.reactor.reactor_pracitce.utils.Logger;

import reactor.core.publisher.Flux;

public class Generate01 {
  public static void main(String[] args) {

    // 프로그래밍 방식으로 신호를 하나씩 생성하여 {@link Flux}를 생성합니다.
    // * @param <T> 방출된 값 유형
    // * @param <S> 구독자별 사용자 정의 상태 유형
    Flux.generate(() -> 0, (state, sink) -> {
      sink.next(state);
      if(state == 10) {
        sink.complete();
      }
      return ++state;
    }).subscribe(Logger::onNext);
  }
}
