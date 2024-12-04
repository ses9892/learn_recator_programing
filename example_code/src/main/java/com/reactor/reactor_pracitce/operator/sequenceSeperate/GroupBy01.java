package com.reactor.reactor_pracitce.operator.sequenceSeperate;

import reactor.core.publisher.Flux;

public class GroupBy01 {
    // 학생 정보를 담는 내부 클래스
    static class Student {
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', age=" + age + "}";
        }
    }

    public static void main(String[] args) {
        // 5명의 학생 데이터를 가진 Flux 생성
        Flux<Student> studentFlux = Flux.just(
            new Student("Alice", 20),
            new Student("Bob", 21),
            new Student("Charlie", 20),
            new Student("David", 22),
            new Student("Eve", 21)
        );

        studentFlux
            // Student::getAge를 기준으로 그룹화
            // 결과적으로 GroupedFlux<Integer, Student> 타입이 생성됨
            // 20세 그룹, 21세 그룹, 22세 그룹으로 분리
            .groupBy(Student::getAge)
            
            // 각 그룹별로 처리하기 위한 flatMap
            // group 파라미터는 GroupedFlux<Integer, Student> 타입
            // group.key()는 나이값(20, 21, 22)을 반환
            .flatMap(group -> {
                System.out.println("Age Group: " + group.key());
                
                // 각 그룹의 Student들을 List로 수집
                // Mono<List<Student>> 타입 반환
                return group.collectList()
                    // List<Student>를 문자열로 변환
                    .map(students -> {
                        // 현재 처리중인 그룹의 전체 학생 목록 출력
                        System.out.println("students : " + students);
                        // 최종 결과 문자열 생성 및 반환
                        return "Students of age " + group.key() + ": " + students;
                    });
            })
            // 구독하여 최종 결과 출력
            // 각 그룹별로 처리된 문자열이 출력됨
            .subscribe(System.out::println);
    }
}