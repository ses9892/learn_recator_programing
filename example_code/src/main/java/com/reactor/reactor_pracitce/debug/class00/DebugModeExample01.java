package com.reactor.reactor_pracitce.debug.class00;

import com.reactor.reactor_pracitce.utils.Logger;

import reactor.core.publisher.Flux;

/**
 * Non-Debug mode
 */
public class DebugModeExample01 {
  public static void main(String[] args) {
    Flux.just(2,4,6,8)
      .zipWith(Flux.just(1,2,3,0) , (a,b) -> a/b)
      .subscribe(Logger::onNext , Logger::onError);
      
  }
}
