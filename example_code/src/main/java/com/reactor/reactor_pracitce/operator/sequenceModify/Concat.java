package com.reactor.reactor_pracitce.operator.sequenceModify;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.Arrays;
import java.util.List;

public class Concat {
    public static void main(String[] args) {
        // 첫 번째 사용자 리스트 (일반 사용자)
        List<Tuple2<String, Integer>> regularUsers = Arrays.asList(
            Tuples.of("Alice", 25),
            Tuples.of("Bob", 30)
        );

        // 두 번째 사용자 리스트 (VIP 사용자)
        List<Tuple2<String, Integer>> vipUsers = Arrays.asList(
            Tuples.of("Charlie", 35),
            Tuples.of("David", 40)
        );

        // 첫 번째 Flux 생성 (일반 사용자)
        Flux<UserInfo> regularFlux = Flux.fromIterable(regularUsers)
            .map(tuple -> new UserInfo(tuple.getT1(), tuple.getT2(), "Regular"));

        // 두 번째 Flux 생성 (VIP 사용자)
        Flux<UserInfo> vipFlux = Flux.fromIterable(vipUsers)
            .map(tuple -> new UserInfo(tuple.getT1(), tuple.getT2(), "VIP"));

        // 예제 1: 기본 concat 사용
        System.out.println("=== 기본 concat 예제 ===");

        // Flux , Mono 를 param으로 받아 두개를 연결한다.
        Flux.concat(regularFlux, vipFlux)
            .subscribe(user -> System.out.println(user.toString()));

        // 예제 2: concatWith 사용
        // concatWith 는 두개의 Flux 를 연결한다. (기존에 제공된 data + 추가될 Mono,Flux Data)
        System.out.println("\n=== concatWith 예제 ===");
        regularFlux
            .concatWith(vipFlux)
            .subscribe(user -> System.out.println(user.toString()));

        // 예제 3: 조건부 concat
        System.out.println("\n=== 조건부 concat 예제 ===");
        Flux<UserInfo> newUsers = Flux.just(new UserInfo("Eve", 28, "New"));
        
        Flux.concat(
            regularFlux,
            vipFlux,
            // 조건에 따라 새로운 Flux 추가
            newUsers.filter(user -> user.age > 30)
        ).subscribe(user -> System.out.println(user.toString()));
    }

    // 사용자 정보를 담는 클래스
    static class UserInfo {
        private String name;
        private int age;
        private String type;

        public UserInfo(String name, int age, String type) {
            this.name = name;
            this.age = age;
            this.type = type;
        }

        @Override
        public String toString() {
            return String.format("UserInfo{name='%s', age=%d, type='%s'}", 
                name, age, type);
        }
    }
}