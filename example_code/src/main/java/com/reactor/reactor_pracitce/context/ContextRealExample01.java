package com.reactor.reactor_pracitce.context;

import com.reactor.reactor_pracitce.context.vo.Book;

import com.reactor.reactor_pracitce.utils.Logger;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;
/**
 * Context 활용 예제
 *  - 직교성을 가지는 정보를 표현할 때 주로 사용된다.
 */
public class ContextRealExample01 {
      public static final String HEADER_NAME_AUTH_TOKEN = "authToken";
    public static void main(String[] args) {
        Mono<String> mono =
                postBook(Mono.just(
                                    new Book("abcd-1111-3533-2809"
                                            , "Reactor's Bible"
                                            ,"Kevin"))
                )
                .contextWrite(Context.of(HEADER_NAME_AUTH_TOKEN, "eyJhbGciOiJIUzUxMiJ9.eyJzdWI"));

        mono.subscribe(Logger::onNext);

    }

    private static Mono<String> postBook(Mono<Book> book) {
      // Mono.zip : 여러 Mono를 하나의 Mono로 조합 tuple 형태로 반환
        return Mono.zip(book, Mono.deferContextual(ctx -> Mono.just(ctx.get(HEADER_NAME_AUTH_TOKEN))))
                .flatMap(tuple -> Mono.just(tuple))  // 외부 API 서버로 HTTP POST request를 전송한다고 가정 map으로 안하고 flatMap 으로 해야 이런 과정을 거칠 수 있음
                .flatMap(tuple -> {
                    String response = "POST the book(" + tuple.getT1().getBookName() +
                            "," + tuple.getT1().getAuthor() + ") with token: " +
                            tuple.getT2();
                    return Mono.just(response); // HTTP response를 수신했다고 가정
                });
    }
}
