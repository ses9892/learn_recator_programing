package com.reactor.reactor_pracitce.operator.creater;

import com.reactor.reactor_pracitce.utils.Logger;

import reactor.core.publisher.Mono;

public class JustOrEmpty01 {
  public static void main(String[] args) {

    // justOrEmpty : just의 확장 Operator
    // emit 값이 null이면 NPE 발생하지 않고 빈 Mono를 반환하며 onComplete Signal만 발생
    Mono
      .justOrEmpty(null)
      .subscribe(Logger::onNext , Logger::onError , Logger::onComplete); 

    // output : 
    // # onComplete()
  }
}
