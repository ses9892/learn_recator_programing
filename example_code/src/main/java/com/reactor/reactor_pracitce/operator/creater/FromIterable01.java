package com.reactor.reactor_pracitce.operator.creater;

import java.util.Arrays;
import java.util.List;

import com.reactor.reactor_pracitce.operator.Coin;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
@Slf4j
public class FromIterable01 {

  static List<Coin> coinList = Arrays.asList(
    new Coin("Bitcoin", 100000),
    new Coin("Ethereum", 5000),
    new Coin("Litecoin", 100)
  );

  /**
   * fromIterable : Iterable 인터페이스를 구현한 컬렉션으로부터  Flux를 생성
   */

  public static void main(String[] args) {
    Flux
      .fromIterable(coinList)
      .subscribe(coin -> {
        log.info("coin 명 : {} , 현재가 : {}", coin.name, coin.price);  
      });
  }



}
