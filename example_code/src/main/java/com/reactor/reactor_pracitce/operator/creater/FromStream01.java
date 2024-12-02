package com.reactor.reactor_pracitce.operator.creater;

import java.util.Arrays;
import java.util.List;

import com.reactor.reactor_pracitce.operator.Coin;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
@Slf4j
public class FromStream01 {

  static List<Coin> coinList = Arrays.asList(
    new Coin("Bitcoin", 100000),
    new Coin("Ethereum", 5000),
    new Coin("Litecoin", 100)
  );


  public static void main(String[] args) {
    /**
     * fromStream : Stream을 통해 Flux를 생성 
     * Stream 특성상 한번 소비되면 재사용 불가능하므로 주의 필요
     */
    Flux
      .fromStream(() -> coinList.stream())
      .subscribe(coin -> {
        log.info("coin 명 : {} , 현재가 : {}", coin.name, coin.price);  
      });
  }
}
