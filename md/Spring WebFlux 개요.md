# Spring WebFlux란? 🌐
---
<p align="center">
  <img src="/md/images/image.png" width="100" alt="Spring WebFlux 아키텍처">
</p>

Spring WebFlux는 **Spring 5**부터 지원하는 리액티브 웹 프레임워크입니다.  
비동기 **Non-Blocking I/O** 방식을 기반으로 적은 수의 쓰레드를 사용하여 효율적으로 동작합니다.

---

## 1. Spring WebFlux의 특징 ✨

### 🔹 비동기 Non-Blocking I/O
- 적은 수의 스레드로 **대규모 요청 처리**를 가능하게 함.
- 시스템 자원을 효율적으로 사용하여 **높은 동시성 처리** 제공.

### 🔹 Reactor 기반 리액티브 스트림
- **Reactive Streams**의 구현체인 **Reactor**를 활용.
- **비동기 로직**을 구성하고 **리액티브 스트림**을 제공.
- Reactor 외에도 **RxJava** 같은 다른 리액티브 확장 라이브러리 적용 가능.

### 🔹 학습 비용
- Spring WebFlux 자체는 간단하지만, **Reactor**에 대한 학습이 필요.
- Reactor의 개념(Flux, Mono, Backpressure 등)을 이해해야 효과적으로 사용할 수 있음.

---

## 2. Spring MVC와 Spring WebFlux의 기술 스택 비교 🔄
> <p align="center">
  <img src="/md/images/image-1.png" width="700" height="400" alt="Spring WebFlux 아키텍처">
</p>

| 프레임워크 | 기술 스택 | I/O 방식 |
|------------|-----------|-----------|
| Spring MVC | **Servlet** | **블로킹 I/O** |
| Spring WebFlux | **Reactive Streams** | **Non-Blocking I/O** |

---

## 3. Spring MVC와 Spring WebFlux의 밴다이어그램 🖼️
> <p align="center">
  <img src="/md/images/image-2.png" width="700" height="400" alt="Spring WebFlux 아키텍처">
</p>

| 구분 | 설명 |
|------|------|
| 공통점 | - Spring Framework 생태계를 공유하며 의존성 관리 및 부가 기능 활용 가능<br>- Spring Boot, Spring Security 등의 기능 사용 가능<br>- Spring의 핵심 기능인 DI, AOP 등을 동일하게 활용 |
| 차이점 | - 요청/응답 처리 방식: MVC는 동기식, WebFlux는 비동기식<br>- 내부 아키텍처: MVC는 Servlet 기반, WebFlux는 Reactive Streams 기반<br>- 스레드 모델: MVC는 Thread per Request, WebFlux는 Event Loop 방식 |

---

## 4. Spring WebFlux의 Non-Blocking 프로세스 🔄
> <p align="center">
  ```mermaid
  graph LR
    A[클라이언트 요청] --> B[이벤트 루프]
    B --> C[작업 큐]
    C --> D[비동기 작업 처리]
    D --> E[콜백 실행]
    E --> F[응답 반환]
    F --> B
  ```
</p>

Spring WebFlux는 이벤트 루프 기반의 Non-Blocking 모델을 사용하여 요청 처리 과정을 최적화합니다:
1. 요청을 비동기적으로 처리.
2. 데이터 준비 상태를 감지하고 콜백 방식으로 응답.
3. 작업이 완료될 때까지 스레드가 대기하지 않음.

---

## 5. Spring WebFlux를 사용하기 적합한 시스템 ✅

Spring WebFlux는 다음과 같은 요구사항을 가진 시스템에 적합합니다:

### 📈 **대량의 요청 트래픽 처리**
- **Blocking I/O** 방식으로 처리하기 어려운 **높은 트래픽** 상황에서 효과적.

### 🛠️ **마이크로서비스 기반 시스템**
- 마이크로서비스 간 **비동기 통신**을 통해 서비스 간 결합도를 낮춤.

### 🔄 **스트리밍 또는 실시간 시스템**
- **데이터 스트리밍**, **실시간 업데이트**가 필요한 애플리케이션에 적합.

### 🌐 **네트워크 접속이 느린 클라이언트 처리**
- 클라이언트의 요청 지연을 효율적으로 처리하여 사용자 경험을 향상.

---

> **📚 참고 자료**  
> - [Spring WebFlux 공식 문서](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)  
> - [Reactive Streams Specification](https://www.reactive-streams.org/)
