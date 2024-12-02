# Context in Reactive Programming 🌐

## Context란? 🧩
- **정의**: 비동기 작업 간에 상태 또는 데이터를 전달할 수 있도록 설계된 객체.
- **목적**:
  - 전역 상태를 대체
  - 명시적이고 안전한 데이터 전달
  - 요청 간 정보 공유를 위한 효율적인 방식 제공
- **사용 사례**:
  - 인증/인가 정보 전달
  - 로깅 및 추적 데이터 전달
  - 기본 설정 값 또는 환경 변수 전달

---

## Context의 특징 🛠️
1. **불변성**
   - Context 객체는 불변(immutable)으로 설계됨.
   - 새 데이터를 추가하거나 제거할 경우 기존 Context는 변경되지 않고, 새로운 Context가 생성됨.

2. **Thread-Local 대체**
   - Context는 Thread-Local 변수와 유사한 역할을 하지만, 비동기/논블로킹 환경에서도 안전하게 사용할 수 있음.
   - Thread-Local은 특정 스레드에 종속되지만, Context는 리액티브 체인 전체에 걸쳐 전달 가능.

3. **Key-Value 기반**
   - 데이터를 저장할 때 **Key-Value** 구조를 사용.
   - 키를 통해 Context에 저장된 값을 조회할 수 있음.

---

## Context 사용 방법 🧑‍💻

### 1. Context 생성
```java
Context context = Context.of("key1", "value1", "key2", "value2");
```

### 2. Context와 Reactor 연동
- Context는 리액티브 체인에서 사용 가능하며, `Mono` 또는 `Flux`와 통합 가능.
```java
Mono.deferContextual(ctx -> {
    String value = ctx.get("key1");
    return Mono.just("Retrieved value: " + value);
})
.contextWrite(Context.of("key1", "value1"))
.subscribe(System.out::println);

// 출력: Retrieved value: value1
```

#### `contextWrite()`의 역할
- Context 데이터를 설정하거나 업데이트.
- 리액티브 체인의 특정 시점에서 Context를 변경 가능.
- ##### 단! DownStream ➡️ Upstream으로 전파되기 때문에 코드 상 subscribe 이전에 적어서 사용해야 함.
```java
Mono.deferContextual(ctx -> {
    String value = ctx.get("key1");
    return Mono.just("Retrieved value: " + value);
})
.contextWrite(Context.of("key1", "value1"))       // context는 DownStream ➡️ Upstream으로 전파됨.
.subscribe(System.out::println);

// 출력: Retrieved value: value1
```

---

## Context API 주요 메서드 📚

| 메서드                   | 설명                                                                 |
|--------------------------|----------------------------------------------------------------------|
| `get(key)`               | 주어진 키에 해당하는 값을 반환. 없을 경우 예외 발생.                 |
| `getOrDefault(key, def)` | 키가 존재하면 값을 반환하고, 없으면 기본값 반환.                     |
| `of(key, value...)`      | Key-Value 쌍을 기반으로 Context 객체 생성.                          |
| `put(key, value)`        | 새로운 Key-Value 추가. 기존 Context를 변경하지 않고 새로운 Context 생성. |

---

## Context 활용 사례 ✨

### 1. 인증 및 권한 정보 전달
```java
Mono.deferContextual(ctx -> {
    String token = ctx.getOrDefault("authToken", "default-token");
    return Mono.just("Using token: " + token);
})
.contextWrite(Context.of("authToken", "Bearer abc123"))
.subscribe(System.out::println);
// 출력: Using token: Bearer abc123
 ```

### 2. 로깅 및 트랜잭션 추적
```java
Flux.range(1, 5)
    .flatMap(num -> Mono.deferContextual(ctx -> {
        String traceId = ctx.get("traceId");
        return Mono.just("TraceId: " + traceId + ", Number: " + num);
    }))
    .contextWrite(Context.of("traceId", "1234-5678"))
    .subscribe(System.out::println);

// 출력 예시:
// TraceId: 1234-5678, Number: 1
// TraceId: 1234-5678, Number: 2
// ...
```

### 3. 기본 설정 및 환경 변수 전달
```java
Mono.deferContextual(ctx -> {
    String env = ctx.getOrDefault("env", "production");
    return Mono.just("Running in environment: " + env);
})
.contextWrite(Context.of("env", "development"))
.subscribe(System.out::println);

// 출력: Running in environment: development
```

---

## Context의 주의사항 ⚠️

### 1. 불필요한 데이터 남용
- `Context`에 과도한 데이터를 저장하면 성능 저하 초래 가능.
- 필요한 정보만 최소한으로 저장.

### 2. Context 키 중복
- 동일한 키가 여러 번 사용되면 최신 값으로 덮어씀.
- 키 네이밍에 주의 필요.

### 3. 비동기 전환 시 Context 전파 누락
- `contextWrite()` 이후에 새로운 스케줄러로 작업이 전환되면 Context가 손실될 수 있음.
- Reactor의 Context Propagation 메커니즘을 올바르게 이해하고 사용.

---

## 예제 소스 📝

### 1. Context API 기본 예제
- [ContextAPIExample03.java](../example_code/src/main/java/com/reactor/reactor_pracitce/context/ContextAPIExample03.java)

### 2. Context 특징 예제
- [ContextFeatureExample01.java](../example_code/src/main/java/com/reactor/reactor_pracitce/context/ContextFetureExample01.java)
- [ContextFeatureExample02.java](../example_code/src/main/java/com/reactor/reactor_pracitce/context/ContextFetureExample02.java)
- [ContextFeatureExample03.java](../example_code/src/main/java/com/reactor/reactor_pracitce/context/ContextFetureExample03.java)
- [ContextFeatureExample04.java](../example_code/src/main/java/com/reactor/reactor_pracitce/context/ContextFetureExample04.java)

### 3. Context 실제 활용 예제
- [ContextRealExample01.java](../example_code/src/main/java/com/reactor/reactor_pracitce/context/ContextRealExample01.java)
- [Book.java](../example_code/src/main/java/com/reactor/reactor_pracitce/context/vo/Book.java)

### 4. Context 소개 예제
- [ContextIntroduceExample01.java](../example_code/src/main/java/com/reactor/reactor_pracitce/context/ContextIntroduceExample01.java)

---

## 참고자료 📚

### 공식 문서
- [Reactor Reference Guide - Context](https://projectreactor.io/docs/core/release/reference/#context)
- [Reactor API Documentation](https://projectreactor.io/docs/core/release/api/)

### 유용한 온라인 자료
- [Project Reactor Context Explained](https://www.baeldung.com/reactor-context)
- [Spring WebFlux and Reactor Context Propagation](https://spring.io/blog/2020/08/31/reactor-context-and-spring-webflux)
