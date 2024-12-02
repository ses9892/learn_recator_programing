package com.reactor.reactor_pracitce.operator.creater;

import com.reactor.reactor_pracitce.utils.Logger;

import reactor.core.publisher.Flux;

public class Range01 {

  public static void main(String[] args) {

    /**
     * range : 지정된 범위의 숫자를 발행하는 Flux를 생성
     * start : 시작 숫자
     * count : 발행할 숫자의 개수
     */
    Flux
      .range(5, 10)
      .subscribe(Logger::onNext);
  }


}
