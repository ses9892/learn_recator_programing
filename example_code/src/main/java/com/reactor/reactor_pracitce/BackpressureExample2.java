package com.reactor.reactor_pracitce;

import org.reactivestreams.Subscription;

import com.reactor.reactor_pracitce.utils.Logger;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackpressureExample2 {
  public static int count = 0;

  public static void main(String[] args) {
    Flux.range(1, 5)
        .doOnNext(Logger::doOnNext) // Upstream 에서 emit 되는 데이터를 출력
        .doOnRequest(Logger::doOnRequest) // Subscriber 가 요청한 데이터 개수를 출력
        .subscribe(
            new BaseSubscriber<Integer>() { // BaseSubscriber 를 상속받아 구현
              @Override
              protected void hookOnSubscribe(Subscription subscription) {
                // Publisher와 Subscriber가 연결될 때 호출되는 메서드
                // subscription 파라미터를 통해 데이터 요청량을 조절할 수 있음
                // request(n)을 호출하여 Publisher에게 데이터를 요청
                // 여기서는 데이터를 1개씩만 요청하도록 설정 (백프레셔 적용)
                request(2);
              }

              @Override
              protected void hookOnNext(Integer value) {
                // Publisher로부터 데이터를 전달받을 때마다 호출되는 메서드
                // value 파라미터로 전달받은 데이터를 처리
                count++;
                // 전달받은 데이터를 로그로 출력
                Logger.onNext(value);
                if (count == 2) {
                  try {
                    Thread.sleep(30000);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                  // request(2);
                  count = 0;
                }
              }

              @Override
              protected void hookOnComplete() {
                System.out.println("모든 데이터 처리 완료!");
              }
            });
  }
}
