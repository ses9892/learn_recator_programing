package com.reactor.reactor_pracitce;


import org.reactivestreams.Subscription;

import com.reactor.reactor_pracitce.utils.Logger;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;


public class BackpressureExample1 {
  public static void main(String[] args) {
    Flux.range(1, 5)
      .doOnNext(Logger::doOnNext)   // Upstream 에서 emit 되는 데이터를 출력
      .doOnRequest(Logger::doOnRequest) // Subscriber 가 요청한 데이터 개수를 출력
      .subscribe(
        new BaseSubscriber<Integer>() {   // BaseSubscriber 를 상속받아 구현
          @Override
          protected void hookOnSubscribe(Subscription subscription) {   
            // Publisher와 Subscriber가 연결될 때 호출되는 메서드
            // subscription 파라미터를 통해 데이터 요청량을 조절할 수 있음
            // request(n)을 호출하여 Publisher에게 데이터를 요청
            // 여기서는 데이터를 1개씩만 요청하도록 설정 (백프레셔 적용)
            request(1);
          }

          @Override
          protected void hookOnNext(Integer value) {
            // Publisher로부터 데이터를 전달받을 때마다 호출되는 메서드
            // value 파라미터로 전달받은 데이터를 처리
            try {
              // 데이터 처리에 시간이 걸리는 것을 시뮬레이션하기 위해 2초간 대기
              Thread.sleep(2000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            // 전달받은 데이터를 로그로 출력
            Logger.onNext(value);
            // 다음 데이터를 1개 요청 (백프레셔 적용)
            // 이렇게 하면 처리 속도에 맞춰 데이터를 요청하게 됨
            request(1);
          }
        }
      );
  }
}
