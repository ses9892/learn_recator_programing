package com.reactor.reactor_pracitce.operator.filtering;

import com.reactor.reactor_pracitce.utils.Logger;

import reactor.core.publisher.Flux;

public class Filter01 {
  public static void main(String[] args) {
    Flux.range(1, 10)
    .filter(i -> i % 2 != 0)    // sequence 순서대로 처리 과정에서 filter 연산자를 만나면 해당 데이터를 걸러냄
    .subscribe(Logger::onNext);
  }
}
