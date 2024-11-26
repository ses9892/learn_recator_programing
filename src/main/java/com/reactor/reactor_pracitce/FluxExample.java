package com.reactor.reactor_pracitce;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class FluxExample {

  /**
   * Flux.just() : 데이터를 생성하고 발행 "여러 개의 데이터를 발행"
   * map() : 데이터를 변환
   * subscribe() : 데이터를 소비
   */
  public static void fluxTest1() {
    Flux.just(6, 9, 13) // Upstream
        .map(num -> num % 2) // Operator
        .subscribe(data -> log.info("omit even number: {}", data)); // Downstream
  }

  /**
   * fromArray() : 배열을 생성하고 발행
   * chain 형태로 데이터를 처리
   * filter() : 데이터를 필터링
   * map() : 데이터를 변환
   */
  public static void fluxTest2() {
    Flux.fromArray(new Integer[] { 3, 6, 7, 9 })
        .filter(num -> num > 6)
        .map(num -> num * 2)
        .subscribe(data -> log.info("data: {}", data));
  }

  /**
   * concatWith() : 두 개의 Mono를 연결 ( 문자열이 Concat이 아니라 데이터가 연결됨 총 2개가 됨)
   */
  public static void fluxTest3() {
    Flux<Object> flux = Mono.justOrEmpty(null)
        .concatWith(Mono.justOrEmpty("jobs"));
    flux.subscribe(data -> log.info("data: {}", data));
  }

  /**
   * concat() : 여러 개의 Mono를 연결
   * collectList() : 데이터를 수집하고 리스트로 반환  (list Object emit)
   * collectList 가 없으면 데이터가 하나씩 출력됨 (each emit)
   */
  public static void fluxTest4() {
    Flux.concat(
      Mono.just("Hello"),
      Mono.just("Flux"),
      Mono.just("World")
    )
      .collectList()          
      .subscribe(plannerList -> log.info("List : {}" , plannerList));
    
  }

  public static void main(String[] args) {
    // fluxTest1();
    // fluxTest2();
    // fluxTest3();
    fluxTest4();
  }
}
