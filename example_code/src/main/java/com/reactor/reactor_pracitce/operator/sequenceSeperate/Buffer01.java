package com.reactor.reactor_pracitce.operator.sequenceSeperate;

import reactor.core.publisher.Flux;

public class Buffer01 {
  public static void main(String[] args) {
    Flux.range(1, 95)
        .buffer(10)
        .subscribe(System.out::println);
  }
}
