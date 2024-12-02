# Create Operator API 📥


## 🎯 Table of Contents 
| 연산자 | 설명 |
|:--:|:--|
| [🛠️ Create](#1-create-연산자) | 프로그래머가 직접 Signal을 생성하는 연산자 |
| [⏳ Defer](#2-defer-연산자) | 구독 시점에 데이터 스트림을 생성하는 연산자 |
| [🔄 FromIterable](#3-fromiterable-연산자) | 컬렉션을 리액티브 스트림으로 변환하는 연산자 |
| [⚡ Generate](#4-generate-연산자) | 상태 기반으로 값을 생성하는 연산자 |
| [🔒 Using](#5-using-연산자) | 리소스를 안전하게 관리하며 데이터를 생성하는 연산자 |
| [📦 JustOrEmpty](#6-justorempty-연산자) | null을 포함할 수 있는 단일 값을 위한 연산자 |
| [🔢 Range](#7-range-연산자) | 연속된 정수 시퀀스를 생성하는 연산자 |


## 1. Create 연산자
프로그래머가 직접 Signal을 생성하여 데이터를 전달하는 방식의 연산자입니다.

### 주요 특징
- `FluxSink`를 통한 프로그래밍 방식의 데이터 생성
- 백프레셔 지원
- 다양한 오버플로우 전략 설정 가능

### 예제

```java

Flux.create(sink -> {
sink.onRequest(n -> {
for (int i = 0; i < n; i++) {
sink.next(data);
}
});
sink.onDispose(() -> log.info("정리 작업 수행"));
})
```


### 오버플로우 전략
```java
Flux.create(sink -> {
// 데이터 생성 로직
}, FluxSink.OverflowStrategy.DROP) // DROP, LATEST, ERROR 등 선택 가능
```


## 2. Defer 연산자
구독 시점에 데이터 스트림을 생성하는 지연 생성 연산자입니다.

### 주요 특징
- 구독 시점에 새로운 데이터 스트림 생성
- 매 구독마다 새로운 데이터 제공
- 실제 필요한 시점까지 실행 지연 가능

### 예제

```java
// 구독 시점의 시간을 얻는 예제
Mono<LocalDateTime> deferredTime = Mono.defer(() ->
Mono.just(LocalDateTime.now())
);
// switchIfEmpty와 함께 사용
Mono.just(data)
.switchIfEmpty(Mono.defer(() -> fallbackOperation()))
```


## 3. FromIterable 연산자
컬렉션 데이터를 리액티브 스트림으로 변환하는 연산자입니다.

### 주요 특징
- List, Set 등 Iterable 구현체를 Flux로 변환
- 컬렉션의 요소를 순차적으로 방출

### 예제

```java
List<Coin> coins = Arrays.asList(
new Coin("Bitcoin", 100000),
new Coin("Ethereum", 5000)
);
Flux.fromIterable(coins)
.subscribe(coin -> log.info("코인: {}, 가격: {}", coin.name, coin.price));
```


## 4. Generate 연산자
상태 기반의 프로그래밍 방식으로 값을 생성하는 연산자입니다.

### 주요 특징
- 상태를 유지하면서 값을 하나씩 생성
- sink.next()를 한 번만 호출 가능
- 동기적 실행 보장

### 예제

```java
// 구구단 생성 예제
Flux.generate(
() -> Tuples.of(3, 1), // 초기 상태
(state, sink) -> {
sink.next(state.getT1() + " x " + state.getT2() + " = " +
(state.getT1() state.getT2()));
if (state.getT2() == 9) {
sink.complete();
}
return Tuples.of(state.getT1(), state.getT2() + 1);
}
)
```


## 5. Using 연산자
리소스를 안전하게 관리하면서 데이터를 생성하는 연산자입니다.

### 주요 특징
- 리소스 생성, 사용, 정리를 한번에 처리
- try-with-resources와 유사한 패턴
- 자동 리소스 정리 보장

### 예제

```java
Flux.using(
() -> Files.lines(path), // 리소스 생성
Flux::fromStream, // 스트림 생성
Stream::close // 리소스 정리
)
```


## 6. JustOrEmpty 연산자
null을 포함할 수 있는 단일 값을 위한 연산자입니다.

### 주요 특징
- null 값을 허용
- null일 경우 빈 Mono 반환
- NPE 방지

### 예제

```java
Mono.justOrEmpty(null)
.subscribe(
data -> log.info("데이터: {}", data),
error -> log.error("에러: {}", error),
() -> log.info("완료")
);
```


## 7. Range 연산자
연속된 정수 시퀀스를 생성하는 연산자입니다.

### 주요 특징
- 시작 값과 개수를 지정하여 순차적 숫자 생성
- 동기적 실행

### 예제

```java
Flux.range(1, 5)
.subscribe(num -> log.info("숫자: {}", num));
// 출력: 1, 2, 3, 4, 5
```


---
각 연산자는 특정 사용 사례에 맞게 설계되었으며, 상황에 따라 적절한 연산자를 선택하여 사용하면 됩니다. 특히 백프레셔 지원과 리소스 관리가 중요한 경우 Create와 Using 연산자를, 지연 실행이 필요한 경우 Defer 연산자를 고려하시면 좋습니다.