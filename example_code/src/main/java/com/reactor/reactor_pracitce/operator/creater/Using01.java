package com.reactor.reactor_pracitce.operator.creater;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.reactor.reactor_pracitce.utils.Logger;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Using01 {

  public static void main(String[] args) {

    Path path = Paths.get("example_code/src/main/java/com/reactor/reactor_pracitce/operator/creater/useing_example.txt");
    
    // * @param resourcesSupplier 리소스를 생성하기 위해 구독 시 호출되는 {@link Callable}
    // * @param sourceSupplier 제공된 리소스에서 {@link 게시자}를 파생시키는 팩토리
    // * @param ResourceCleanup 완료 시 호출되는 리소스 정리 콜백
    // 리소스생성
    // 리소스에서 게시자 파생
    // 리소스 정리
    Flux.using(() -> Files.lines(path), Flux::fromStream, Stream::close)
      .subscribe(Logger::onNext);

    // 19:31:14.696 [main] INFO com.reactor.reactor_pracitce.utils.Logger -- # onNext(): 안녕하세요.
    // 19:31:14.698 [main] INFO com.reactor.reactor_pracitce.utils.Logger -- # onNext(): Using01.java 예제 코드 입니다.

  }
}
