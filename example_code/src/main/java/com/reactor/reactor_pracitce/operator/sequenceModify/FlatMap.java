package com.reactor.reactor_pracitce.operator.sequenceModify;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.Arrays;
import java.util.List;
public class FlatMap {
    public static void main(String[] args) {
        List<Tuple2<String, Integer>> userList = Arrays.asList(
            Tuples.of("Alice", 25),
            Tuples.of("Bob", 30),
            Tuples.of("Charlie", 35)
        );

        // flatMap을 사용한 비동기 변환
        Flux.fromIterable(userList)
            // 비동기 작업, 중첩된 스트림 처리 !!!순서가 보장되지 않음!!!
            .<UserDetail>flatMap(tuple -> 
                Flux.just(tuple)
                    .publishOn(Schedulers.parallel())       // 병렬 처리
                    .map(t -> new UserDetail(t.getT1(), t.getT2()))
                    .map(FlatMap::enrichUserData)
            )
            .subscribe(user -> 
                System.out.println("처리된 사용자: " + user.toString())
            );
    }

    static class UserDetail {
        private String name;
        private int age;
        private String category;

        public UserDetail(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        @Override
        public String toString() {
            return String.format("User{name='%s', age=%d, category='%s'}", 
                name, age, category);
        }
    }

    private static UserDetail enrichUserData(UserDetail user) {
        user.setCategory(user.age < 30 ? "Junior" : "Senior");
        return user;
    }
}