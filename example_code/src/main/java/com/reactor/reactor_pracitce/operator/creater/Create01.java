package com.reactor.reactor_pracitce.operator.creater;

import java.util.Arrays;
import java.util.List;

import org.reactivestreams.Subscription;

import com.reactor.reactor_pracitce.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

@Slf4j
public class Create01 {

  static int COUNT = -1;
  static int SIZE = 0;

  final static List<Integer> DATA = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

  public static void main(String[] args) {

    // 1. 데이터 생성 SInk를 통해
    // 2. 데이터 처리 BaseSubscriber를 통해
    // 3. 완료 처리 onDispose
    // 4. 완료 처리 onComplete
    log.info("START");
    Flux.create(sink -> {
      sink.onRequest(n -> {
        try {
          TimeUtils.sleep(1000);
          for (int i = 0; i < n; i++) {
            if (COUNT >= 9) {
              sink.complete();
            } else {
              COUNT++;
              sink.next(DATA.get(COUNT));
            }
          }
        } catch (Exception e) {
        }
      });

      sink.onDispose(() -> log.info("onDispose"));
    }).subscribe(new BaseSubscriber<>() {
      
      @Override
      protected void hookOnSubscribe(Subscription subscription) {
        log.info("hookOnSubscribe");
        request(2);
      }

      @Override
      protected void hookOnNext(Object value) {
        SIZE++;
        log.info("hookOnNext : {}", value);
        if(SIZE == 2){
          request(2);
          SIZE = 0;
        }
      }

      @Override
      protected void hookOnComplete() {
        log.info("hookOnComplete");
      }
    });

    TimeUtils.sleep(10000);
  }
}
