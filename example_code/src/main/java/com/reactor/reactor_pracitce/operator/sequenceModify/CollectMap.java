package com.reactor.reactor_pracitce.operator.sequenceModify;

import java.util.concurrent.ConcurrentHashMap;

import com.reactor.reactor_pracitce.utils.Logger;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class CollectMap {
  public static void main(String[] args) {

    // collectMap() : 데이터를 Map으로 수집
    // key, value 지정 가능
    // 키가 중복되면 마지막 값으로 덮어쓴다.
    Flux.just("Apple", "Banana", "Cherry", "Date")
    .collectMap(
        fruit -> fruit.charAt(0),    // key: 첫 글자
        fruit -> fruit.length(),      // value: 글자 길이
        () -> new ConcurrentHashMap<>()   // 3번째 파라미터로는 Map 인스턴스 제공 가능
    )
    .subscribe(map -> Logger.onNext(map));
  } 
}
