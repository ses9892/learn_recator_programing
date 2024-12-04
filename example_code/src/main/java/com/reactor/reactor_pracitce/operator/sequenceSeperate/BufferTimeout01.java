package com.reactor.reactor_pracitce.operator.sequenceSeperate;

import java.time.Duration;

import com.reactor.reactor_pracitce.utils.Logger;
import com.reactor.reactor_pracitce.utils.TimeUtils;

import reactor.core.publisher.Flux;

public class BufferTimeout01 {
  public static void main(String[] args) {

    // # onNext(): [10, 11] -> 10, 11 두 개의 데이터가 버퍼에 들어간 것을 확인할 수 있다.
    // timeout 이 진행되면 진행중이던 데이터까지 버퍼에 들어간다. (데이터를 discard 하는 것이 아님)

    Flux.range(1, 20)
      .map(num -> {
        if(num < 10){
          TimeUtils.sleep(100);
        }else{
          TimeUtils.sleep(300);
        }

        return num;
      })
      .bufferTimeout(3, Duration.ofMillis(400))
      .subscribe(Logger::onNext);

  }
}
