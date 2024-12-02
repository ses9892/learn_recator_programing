package com.reactor.reactor_pracitce.operator.filtering;

import reactor.core.publisher.Flux;
import java.util.List;

public class TakeLast {
  public static void main(String[] args) {
    Flux<Integer> numbers = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    // takeLast : 마지막 인자로 전달된 갯수만큼 데이터를 뒤에서 받아옴
    numbers.takeLast(3)
           .collectList()
           .subscribe(list -> {
             System.out.println("Last 3 elements: " + list);
           });
  }
}
