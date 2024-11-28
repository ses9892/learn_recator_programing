# 🌟 WebFlux Sinks - 개요 및 주요 개념

WebFlux Sinks는 프로그래밍 방식으로 데이터를 리액티브 스트림에 푸시할 수 있는 메커니즘을 제공합니다.  
외부 이벤트나 수동 트리거가 데이터를 생성하는 시나리오에서 특히 유용합니다.

---

## ✨ WebFlux Sinks의 주요 특징

1. **⚡ Backpressure 지원**  
   - Sinks는 다운스트림 구독자의 수요를 존중하며, 리소스를 효율적으로 활용합니다.

2. **🔒 스레드 안전성**  
   - 동시 접근 및 데이터 방출이 안전하게 관리되어 멀티스레드 환경에서 적합합니다.

3. **🎛️ 유연한 데이터 방출**  
   - 여러 전략(`unicast`, `multicast`, `replay`)을 통해 이벤트가 구독자에게 전달되는 방식을 제어할 수 있습니다.

---

## 🛠️ WebFlux Sinks의 유형

1. **🟩 UnicastProcessor**  
   - 단일 구독자만 데이터를 수신합니다.  
   - 구독자가 연결된 이후부터 데이터가 처리되며, 스트림이 완료될 때까지 지속됩니다.  
   - **🔷 Hot Sequence 지원**: 데이터가 생성되는 즉시 구독자에게 전달되며, 새로운 구독자는 이전 데이터를 받을 수 없습니다.

2. **🟦 MulticastProcessor**  
   - 여러 구독자가 동일한 이벤트를 수신합니다.  
   - 모든 구독자가 동시에 동일한 데이터를 받도록 보장합니다.  
   - **🔷 Hot Sequence 지원**: 모든 구독자가 연결된 시점부터 데이터를 수신하며, 이전에 생성된 데이터는 전달되지 않습니다.

3. **🟨 ReplayProcessor**  
   - 새 구독자에게 모든 방출된 이벤트를 다시 재생합니다.  
   - 캐싱이 필요하거나 나중에 구독자가 생기는 시나리오에 적합합니다.  
   - **🔷 Cold Sequence 지원**: 모든 구독자가 이전에 생성된 데이터를 받을 수 있습니다. 새로운 구독자도 이미 방출된 데이터를 재생하여 수신합니다.

---

### 🔥 Hot Sequence vs ❄️ Cold Sequence

- **Hot Sequence**  
  - 데이터 생성이 독립적으로 발생하며 구독자가 존재하지 않아도 데이터를 방출합니다.  
  - 구독자는 연결된 시점부터 데이터 스트림을 수신합니다.  
  - `UnicastProcessor`, `MulticastProcessor`는 Hot Sequence로 동작합니다.

- **Cold Sequence**  
  - 데이터 방출은 구독자가 연결된 이후에만 시작됩니다.  
  - 모든 구독자는 동일한 데이터 스트림을 처음부터 받을 수 있습니다.  
  - `ReplayProcessor`는 Cold Sequence로 동작하며, 이전 데이터를 재생합니다.


---

## 📚 WebFlux Sinks의 사용 사례

- **🚀 이벤트 기반 애플리케이션**  
  사용자 상호작용 또는 외부 시스템에서 발생하는 이벤트를 푸시합니다.

- **🌉 비동기-동기 API 간 브릿징**  
  기존의 동기식 API를 리액티브 파이프라인에 통합합니다.

- **🔄 동적 데이터 소스 처리**  
  예측할 수 없는 데이터 소스(예: 센서 데이터, 사용자 생성 이벤트)를 다룹니다.

---

## ✅ WebFlux Sinks 사용 시 베스트 프랙티스

1. **📌 적절한 Sink 선택**  
   - 단일 구독자를 위한 `unicast`, 여러 구독자를 위한 `multicast`를 적절히 선택합니다.

2. **⚙️ Backpressure 적절히 처리**  
   - `buffer`, `drop`, `error`와 같은 Backpressure 전략이 애플리케이션 요구사항에 맞게 설정되었는지 확인합니다.

3. **❗ 에러 핸들링**  
   - 다운스트림 구독자를 위한 강력한 에러 핸들링 메커니즘을 구현합니다.

4. **🧹 리소스 관리**  
   - Sinks의 적절한 정리를 통해 메모리 누수 또는 스트림 문제를 방지합니다.

---
# Sink 사용에 대한 참고 사항
## 🎯 Sink.One과 Sink.Many

1. **Sink.One**  
   - **특징**:  
     - 하나의 값을 발행하거나 완료 신호를 보낼 수 있는 Sink입니다.
     - 단일 값의 전달을 보장하며, 그 이후에 스트림을 종료합니다.  
   - **사용 사례**:  
     - 단일 이벤트(예: 데이터베이스 조회 결과, 단일 메시지 전달) 처리에 적합합니다.  
   - **예**: API 호출의 결과를 리액티브 방식으로 제공.

2. **Sink.Many**  
   - **특징**:  
     - 여러 값을 발행하거나 완료 신호를 보낼 수 있는 Sink입니다.
     - `Multicast`, `Unicast`, `Replay` 등 다양한 전략으로 값을 전달할 수 있습니다.  
   - **사용 사례**:  
     - 다수의 이벤트 스트림을 처리해야 하는 경우(예: 실시간 데이터 처리, 채팅 메시지 브로드캐스트 등).  
   - **예**: 채팅 애플리케이션에서 실시간 메시지를 여러 사용자에게 전송.

---

### 🛠️ Sink.One vs Sink.Many 차이점

| 특징               | Sink.One                         | Sink.Many                          |
|--------------------|----------------------------------|------------------------------------|
| 발행 가능한 데이터 | 단일 값                          | 다수의 값                          |
| 사용 전략          | 단순 데이터 응답                 | 다양한 데이터 전달 전략 사용 가능  |
| 주요 활용 시나리오 | 단일 이벤트 응답 (예: API 결과)   | 스트림 기반 이벤트 처리            |

--- 

## 소스 단위 예제
```java
import reactor.core.publisher.Sinks;

public class SinkEmitExample {

    public static void main(String[] args) {
        // Sink.Many를 사용하여 여러 값을 발행할 수 있는 Sink 생성
        Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();

        // Sink를 Flux로 변환하여 구독 가능
        sink.asFlux().subscribe(
            data -> System.out.println("Subscriber received: " + data),  // 데이터 수신 시 처리
            error -> System.err.println("Error occurred: " + error),     // 에러 발생 시 처리
            () -> System.out.println("Stream completed.")                // 스트림 종료 시 처리
        );

        // 1. emitValue를 사용하여 데이터를 방출
        //    성공적으로 데이터를 방출하면 true를 반환, 실패하면 false를 반환
        boolean success = sink.tryEmitNext("Hello, WebFlux").isSuccess();
        System.out.println("Emit status: " + (success ? "Success" : "Failure"));

        // 2. Sinks.EmitFailureHandler.FAIL_FAST 사용
        //    데이터 방출 실패 시 즉시 예외를 발생
        try {
            sink.emitNext("Emit with FAIL_FAST", Sinks.EmitFailureHandler.FAIL_FAST);
            System.out.println("Emit with FAIL_FAST succeeded");
        } catch (Exception e) {
            System.err.println("Emit with FAIL_FAST failed: " + e.getMessage());
        }

        // 3. Sinks.EmitFailureHandler.FAIL_SILENT 사용
        //    데이터 방출 실패 시 아무런 예외도 발생하지 않음 (실패를 조용히 무시)
        sink.emitNext("Emit with FAIL_SILENT", Sinks.EmitFailureHandler.FAIL_SILENT);
        System.out.println("Emit with FAIL_SILENT executed (no exception thrown)");

        // 스트림을 완료
        sink.tryEmitComplete();
    }
}
```

```java
Subscriber received: Hello, WebFlux
Emit status: Success
Emit with FAIL_FAST succeeded
Emit with FAIL_SILENT executed (no exception thrown)
Stream completed.
```
<details>
<summary style="font-weight: bold;">💡 예제소스코드 보기</summary>

- <a href="/example_code/src/main/java/com/reactor/reactor_pracitce/sinks/SinkOneExample01.java">SinkOneExample01.java</a>  
- <a href="/example_code/src/main/java/com/reactor/reactor_pracitce/sinks/SinkOneExample02.java">SinkOneExample02.java</a>  
- <a href="/example_code/src/main/java/com/reactor/reactor_pracitce/sinks/SinkManyExample01.java">SinkManyExample01.java</a>  
- <a href="/example_code/src/main/java/com/reactor/reactor_pracitce/sinks/SinkManyExample02.java">SinkManyExample02.java</a>  
- <a href="/example_code/src/main/java/com/reactor/reactor_pracitce/sinks/SinkManyExample03.java">SinkManyExample03.java</a>  
- <a href="/example_code/src/main/java/com/reactor/reactor_pracitce/sinks/SinkManyExample04.java">SinkManyExample04.java</a>  
- <a href="/example_code/src/main/java/com/reactor/reactor_pracitce/sinks/SinkManyExample05.java">SinkManyExample05.java</a>  

</details>

---

>**📚 참고 자료**  
>
> - [Spring WebFlux 공식 문서](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)  
> - [Reactor Project: Sinks](https://projectreactor.io/docs/core/release/reference/#sinks)

