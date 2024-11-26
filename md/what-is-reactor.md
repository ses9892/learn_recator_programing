# 리액터(Reactor)란? ⚡

Reactor는 **리액티브 프로그래밍**을 지원하기 위한 강력한 **리액티브 라이브러리**입니다.  
**Reactive Streams 스펙**을 구현한 라이브러리 중 하나로, 비동기 데이터 흐름을 다루기 위한 도구를 제공합니다.

---

## 1. Reactor의 역할 🔄

Reactor는 **Spring 에코 시스템**에서 중요한 역할을 합니다:
- **Reactive Stack의 기반**으로 동작.
- Spring WebFlux와 같은 **리액티브 프레임워크의 핵심 구성 요소**로 포함.

Reactor는 **비동기 데이터 흐름 처리**에 적합하며, **고성능**, **높은 동시성**을 요구하는 애플리케이션 개발을 지원합니다.

---

## 2. Reactor의 주요 특징 ✨

### 🌀 **Reactive Streams 표준 구현**
- Reactor는 **Reactive Streams 사양**을 충실히 따르는 구현체입니다.
- **Publisher**, **Subscriber**, **Subscription**, **Processor**와 같은 핵심 요소를 제공합니다.

### 🧵 **Non-Blocking & Backpressure 지원**
- **Non-Blocking I/O** 기반으로 작동하여 효율적으로 리소스를 사용.
- **Backpressure**를 지원해 데이터 생산자와 소비자 간 속도 불일치를 제어.

### 🔧 **Mono와 Flux**
Reactor의 핵심 데이터 구조인 **Mono**와 **Flux**를 사용하여 데이터를 스트림 형태로 처리:
- **Mono**: 0~1개의 데이터 값을 처리.
- **Flux**: 0~N개의 데이터 스트림을 처리.

간단하게 예제코드를 살펴보면 아래와 같습니다.

```java
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

  // Mono: 0~1개의 데이터를 처리
  Mono<String> monoExample = Mono.just("Hello, Reactor!");
  monoExample.subscribe(data -> System.out.println("Mono Data: " + data));

  // Flux: 0~N개의 데이터를 처리
  Flux<String> fluxExample = Flux.just("Spring", "Reactor", "WebFlux");
  fluxExample.subscribe(data -> System.out.println("Flux Data: " + data));

  // Flux: 데이터 처리 중 추가 변환
  Flux<Integer> transformedFlux = Flux.just(1, 2, 3, 4, 5)
                                      .map(num -> num * num); // 데이터를 제곱
  transformedFlux.subscribe(data -> System.out.println("Transformed Data: " + data));

  // Flux: 에러 처리 예제
  Flux<String> fluxWithError = Flux.just("A", "B", "C")
                                    .concatWith(Flux.error(new RuntimeException("An error occurred!")))
                                    .onErrorResume(error -> {
                                        System.out.println("Error: " + error.getMessage());
                                        return Flux.just("Recovered"); // 에러 발생 시 대체 스트림 제공
                                    });
  fluxWithError.subscribe(data -> System.out.println("Flux with Error Handling: " + data));

```


### 🚀 **고성능 및 경량성**
- 낮은 메모리 오버헤드와 높은 처리량을 제공.
- 대규모 트래픽 처리와 실시간 시스템 구축에 적합.

---

## 3. Reactor의 주요 사용 사례 🌟

### 📡 **Spring WebFlux와의 통합**
- Reactor는 Spring WebFlux에서 **리액티브 흐름 처리**의 기반으로 동작.
- 요청-응답 흐름을 비동기적으로 처리하여 **높은 동시성**을 지원.

### 🛠️ **데이터 스트리밍**
- 실시간 데이터 스트림 처리, 예: **소셜 미디어 피드**, **라이브 데이터 대시보드**.

### 🔄 **마이크로서비스 간 통신**
- 비동기 메시징을 사용한 마이크로서비스 간 **느슨한 결합** 및 효율적 통신.

### 🌍 **네트워크 요청 처리**
- 네트워크 지연 시간이 큰 환경에서 효과적(예: API 게이트웨이, 클라이언트 요청 대기).

---

## 4. Reactor를 학습해야 하는 이유 🤔

리액티브 프로그래밍은 점점 더 중요해지고 있습니다.  
Reactor는 이를 구현하는 데 가장 널리 사용되는 라이브러리로, 아래와 같은 이유로 학습할 가치가 있습니다:

- **Spring WebFlux 및 리액티브 스택 이해 필수**: Reactor는 Spring의 리액티브 기술을 지원하는 핵심 라이브러리입니다.
- **비동기 시스템 설계**: 고성능 애플리케이션을 설계하는 데 필수적인 도구입니다.
- **확장성과 유연성**: Flux와 Mono를 통해 다양한 리액티브 프로그래밍 패턴을 구현할 수 있습니다.

---

> **📚 참고 자료**  
> - [Reactor 공식 문서](https://projectreactor.io/)  
> - [Reactive Streams Specification](https://www.reactive-streams.org/)  
> - [Spring WebFlux와 Reactor 관계 설명](https://spring.io/projects/spring-webflux)
