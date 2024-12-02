# Scheduler 📘

## Thread에 대한 이해 🧵

### Thread의 기본 개념
- **정의**: 프로세스 내에서 실행되는 가장 작은 실행 단위
- **특징**: 독립적인 실행 경로와 스택을 가짐
- **동시성**: 여러 작업을 동시에 처리 가능

### Thread의 종류
1. **User Thread (사용자 스레드)**
   - 애플리케이션 레벨에서 생성/관리
   - 개발자가 직접 제어 가능

2. **Daemon Thread (데몬 스레드)**
   - 백그라운드에서 실행
   > **데몬 스레드**는 백그라운드에서 동작하며, 주요 애플리케이션의 동작에 영향을 미치지 않고 보조 작업을 수행합니다.
   - 자동종료
   > 데몬 스레드는 **메인 스레드**가 종료될 때 자동으로 종료됩니다. 즉, 명시적으로 종료 시키지 않아도 됩니다.

   ![Thread_Type](/md/images/Thread-Type.png)

   
## Scheduler 기본 개념 ⚙️

### 정의와 목적
- **정의**: Reactor의 비동기 실행 흐름을 제어하는 핵심 컴포넌트
- **목적**: 작업의 실행 컨텍스트와 타이밍을 효율적으로 관리
- **역할**: **리액티브 스트림의 처리 위치**와 방식을 결정

### 핵심 특징 🎯
1. **비동기 처리 최적화**
   - 논블로킹 작업 실행
   - 효율적인 리소스 활용
   - 높은 처리량 보장

2. **스레드 관리 시스템**
   - 자동화된 스레드 풀 관리
   - 작업 부하의 균등한 분산
   - 스레드 생명주기 자동화

3. **컨텍스트 관리**
   - 유연한 실행 컨텍스트 전환
   - 상황별 최적 실행 환경 제공
   - 효율적인 리소스 스케줄링

## Scheduler 타입 가이드 �

### 1. Schedulers.immediate() ⚡
- **언제 사용하나요?**
  - 간단하고 빠른 작업이 필요할 때
  - 스레드 전환이 필요 없는 경우
  - 최소한의 오버헤드가 필요한 경우

- **장점**
  - 즉각적인 실행
  - 추가 리소스 사용 없음
  - 간단한 구현

```java
// 간단한 숫자 출력 예시
Flux.just(1, 2, 3)
    .subscribeOn(Schedulers.immediate())
    .subscribe(num -> System.out.println("처리된 숫자: " + num));
```

### 2. Schedulers.single() 🎯
- **언제 사용하나요?**
  - 순서가 중요한 작업을 처리할 때
  - 단일 스레드로 충분한 경우
  - 동시성 문제를 피하고 싶을 때

- **장점**
  - 순차적 실행 보장
  - 예측 가능한 동작
  - 리소스 효율적 사용

```java
// 순차적 데이터 처리 예시
Flux.range(1, 5)
    .publishOn(Schedulers.single())
    .map(num -> "처리 순서: " + num)
    .subscribe(System.out::println);
```

### 3. Schedulers.boundedElastic() 🌊
- **언제 사용하나요?**
  - 데이터베이스 작업
  - 파일 입출력
  - API 호출
  - 블로킹 작업이 필요한 경우

- **장점**
  - 효율적인 리소스 관리
  - 작업 대기열 제공
  - 블로킹 작업에 최적화

```java
// API 호출 예시
Flux.fromIterable(apiUrls)
    .flatMap(url -> Mono.fromCallable(() -> callExternalApi(url))
                        .subscribeOn(Schedulers.boundedElastic()))
    .subscribe(response -> System.out.println("API 응답: " + response));
```

### 4. Schedulers.parallel() 💪
- **언제 사용하나요?**
  - 대량 데이터 처리
  - 복잡한 수학 계산
  - CPU를 많이 사용하는 작업

- **장점**
  - 최대 CPU 활용
  - 병렬 처리 최적화
  - 높은 처리량

```java
// 대량 데이터 병렬 처리 예시
Flux.range(1, 1000)
    .parallel()
    .runOn(Schedulers.parallel())
    .map(num -> complexCalculation(num))
    .subscribe(result -> System.out.println("계산 결과: " + result));
```

## Scheduler 선택 가이드 🤔

| Scheduler 타입 | 최적의 사용 사례 | 주의사항 |
|---------------|----------------|---------|
| immediate() | 간단한 동기 작업 | 블로킹 작업 피하기 |
| single() | 순차적 처리 필요 작업 | 긴 작업은 피하기 |
| boundedElastic() | I/O 작업, 블로킹 작업 | 스레드 수 제한 확인 |
| parallel() | CPU 집약적 작업 | 메모리 사용량 주의 |�
---
## 모범 사례 및 주의사항 ⚠️

### 1. 스레드 안전성 확보
- 공유 상태 접근 시 동기화 메커니즘 사용
- 불변 객체 활용
- 부작용이 있는 연산 주의

### 2. 리소스 관리
- 적절한 스케줄러 선택
- 리소스 해제 보장
- 메모리 누수 방지

### 3. 성능 최적화
- 불필요한 스케줄러 전환 최소화
- 적절한 배압(Backpressure) 전략 사용
- 모니터링 및 튜닝

### 4. 디버깅 팁
- 로깅을 통한 스레드 추적
- 스케줄러 동작 검증
- 성능 병목 지점 식별

---

## 예제소스 📖

### Scheduler Operator 예제
- [SchedulerOperatorExample01](../example_code/src/main/java/com/reactor/reactor_pracitce/scheduler/operator/SchedulerOperatorExample01.java) - 기본 Flux 연산자 체인 예제
- [SchedulerOperatorExample02](../example_code/src/main/java/com/reactor/reactor_pracitce/scheduler/operator/SchedulerOperatorExample02.java) - publishOn을 사용한 스레드 전환 예제
- [SchedulerOperatorExample03](../example_code/src/main/java/com/reactor/reactor_pracitce/scheduler/operator/SchedulerOperatorExample03.java) - 다중 publishOn 사용 예제
- [SchedulerOperatorExample04](../example_code/src/main/java/com/reactor/reactor_pracitce/scheduler/operator/SchedulerOperatorExample04.java) - subscribeOn 사용 예제
- [SchedulerOperatorExample05](../example_code/src/main/java/com/reactor/reactor_pracitce/scheduler/operator/SchedulerOperatorExample05.java) - subscribeOn과 publishOn 조합 예제
- [SchedulerOperatorExample06](../example_code/src/main/java/com/reactor/reactor_pracitce/scheduler/operator/SchedulerOperatorExample06.java) - publishOn과 subscribeOn 순서 예제

### Parallel 처리 예제
- [ParalleExample01](../example_code/src/main/java/com/reactor/reactor_pracitce/scheduler/paralle/ParalleExample01.java) - 기본 parallel 스케줄링 예제
- [ParalleExample02](../example_code/src/main/java/com/reactor/reactor_pracitce/scheduler/paralle/ParalleExample02.java) - parallel() 연산자 사용 예제
- [ParalleExample03](../example_code/src/main/java/com/reactor/reactor_pracitce/scheduler/paralle/ParalleExample03.java) - parallel(n) 파라미터 지정 예제

### Schedulers 타입별 예제
- [SchedulersImmediateExample01](../example_code/src/main/java/com/reactor/reactor_pracitce/scheduler/schedulers/SchedulersImmediateExample01.java) - immediate() 적용 전 예제
- [SchedulersImmediateExample02](../example_code/src/main/java/com/reactor/reactor_pracitce/scheduler/schedulers/SchedulersImmediateExample02.java) - immediate() 적용 후 예제
- [SchedulersNewBoundedElasticExample01](../example_code/src/main/java/com/reactor/reactor_pracitce/scheduler/schedulers/SchedulersNewBoundedElasticExample01.java) - newBoundedElastic 스케줄러 예제
- [SchedulersNewParallelExample01](../example_code/src/main/java/com/reactor/reactor_pracitce/scheduler/schedulers/SchedulersNewParallelExample01.java) - newParallel 스케줄러 예제
- [SchedulersSingleExample02](../example_code/src/main/java/com/reactor/reactor_pracitce/scheduler/schedulers/SchedulersSingleExample02.java) - single/newSingle 스케줄러 예제

---

## 참고자료 📚

### 공식 문서
- [Project Reactor Reference Guide](https://projectreactor.io/docs/core/release/reference/)
- [Reactor Core Documentation](https://projectreactor.io/docs/core/release/api/)

### 추천 도서
- "Hands-On Reactive Programming in Spring 5.0" - Oleh Dokuka
- "Reactive Programming with Java and Spring" - Josh Long

### 유용한 온라인 자료
- [Reactor 3 Reference Guide](https://projectreactor.io/docs/core/release/reference/docs/index.html)
- [Baeldung의 Project Reactor 가이드](https://www.baeldung.com/reactor-core)

### 커뮤니티 리소스
- [Stack Overflow - reactor 태그](https://stackoverflow.com/questions/tagged/project-reactor)
- [Reddit r/java](https://www.reddit.com/r/java/)
- [Spring 커뮤니티 포럼](https://community.spring.io/)