package com.reactor.reactor_pracitce.operator.filtering;

import reactor.core.publisher.Flux;
import java.util.Arrays;
import java.util.List;

public class TakeWhile01 {
    public static void main(String[] args) {
        // 숫자 리스트 생성
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Flux.fromIterable로 리스트를 Flux로 변환하고
        // takeWhile을 사용하여 5보다 작은 숫자만 취함
        Flux.fromIterable(numbers)
            .takeWhile(num -> num < 5)  // 5보다 작은 숫자만 !! true가 되는 동안만!
            .subscribe(
                num -> System.out.println("방출된 숫자: " + num),
                error -> System.err.println("에러 발생: " + error),
                () -> System.out.println("완료")
            );
    }
}