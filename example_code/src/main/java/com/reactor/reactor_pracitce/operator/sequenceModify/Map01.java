package com.reactor.reactor_pracitce.operator.sequenceModify;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.Arrays;
import java.util.List;

public class Map01 {
    public static void main(String[] args) {
        // Tuple2 리스트 생성 (사용자 이름, 나이)
        List<Tuple2<String, Integer>> userList = Arrays.asList(
            Tuples.of("Alice", 25),
            Tuples.of("Bob", 30),
            Tuples.of("Charlie", 35)
        );

        // 예제 1: 기본 map 변환
        System.out.println("=== 기본 map 변환 ===");
        Flux.fromIterable(userList)
            .map(tuple -> "이름: " + tuple.getT1() + ", 나이: " + tuple.getT2())
            .subscribe(System.out::println);

        // 예제 2: User 객체로 변환
        System.out.println("\n=== User 객체로 변환 ===");
        Flux.fromIterable(userList)
            .map(tuple -> new User(tuple.getT1(), tuple.getT2()))
            .subscribe(user -> System.out.println(user.toString()));

        // 예제 3: 나이 계산 변환
        System.out.println("\n=== 나이 계산 변환 ===");
        Flux.fromIterable(userList)
            .map(tuple -> Tuples.of(tuple.getT1(), calculateAgeCategory(tuple.getT2())))
            .subscribe(result -> System.out.println(
                result.getT1() + "님은 " + result.getT2() + "입니다."
            ));
    }

    // User 클래스 정의
    static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{name='" + name + "', age=" + age + "}";
        }
    }

    // 나이 카테고리 계산 메소드
    private static String calculateAgeCategory(int age) {
        if (age < 30) return "청년층";
        else if (age < 40) return "중년층";
        else return "장년층";
    }
}