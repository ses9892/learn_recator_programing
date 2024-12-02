package com.reactor.reactor_pracitce.operator.sequenceModify;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class CollectList {
  public static void main(String[] args) {
    
    // collectList() : 모든 데이터를 하나의 List로 수집
    Flux.just("..." , "---","...")
    .map(data -> {
      switch (data) {
        case "...":
          return "S";
        case "---":
          return "O";
        default:
          return "X";
      }
    })
    .collectList()
    .subscribe(list -> log.info(list.stream().collect(Collectors.joining())));
  }

}
