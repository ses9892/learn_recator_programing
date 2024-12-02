package com.reactor.reactor_pracitce.operator.filtering;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
/**
 * next : 첫번째 데이터를 받아옴
 */
public class Next {
    public static void main(String[] args) {
        // 예제 1: 기본적인 next() 사용
        List<String> fruits = Arrays.asList("apple", "banana", "orange");
        Flux.fromIterable(fruits)
            .next()
            .subscribe(fruit -> System.out.println("첫 번째 과일: " + fruit));

        // 예제 2: empty Flux에서 next() 사용
        Flux.empty()
            .next()
            .subscribe(
                item -> System.out.println("아이템: " + item),
                error -> System.err.println("에러: " + error),
                () -> System.out.println("완료 (비어있음)")
            );

        // 예제 3: next()와 defaultIfEmpty 조합
        Flux.empty()
            .next()
            .defaultIfEmpty("기본값")
            .subscribe(value -> System.out.println("값: " + value));

        // 예제 4: 비동기 처리와 next()
        Mono<String> firstElement = Flux.fromIterable(fruits)
            .filter(fruit -> fruit.startsWith("b"))
            .next();
        
        firstElement.subscribe(
            fruit -> System.out.println("'b'로 시작하는 첫 과일: " + fruit)
        );
    }
}