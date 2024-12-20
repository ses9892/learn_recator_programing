# 🌟 Reactor Practice: Sequence Seperate 🌟

## 🎯 Table of Contents 
| 연산자 | 설명 |
|:--:|:--|
| [🔄 Window](#window-윈도우-크기-지정-) | 데이터 스트림을 작은 윈도우로 분할하는 연산자 |
| [🌊 Buffer](#buffer-버퍼-크기-지정-) | 데이터를 List로 모아서 처리하는 연산자 |
| [📝 GroupBy](#groupby-그룹-지정-) | 키 기준으로 데이터를 그룹화하는 연산자 |
---

## Window: 윈도우 크기 지정 🔄

### ✨ 주요 특징
- 데이터 스트림을 작은 윈도우로 분할
- 각 윈도우는 새로운 Flux로 생성
- 크기, 시간, 조건 기반 분할 가능
- 원본 시퀀스를 더 작은 시퀀스로 나눔

### 💡 사용 사례 
- 실시간 데이터 배치 처리
- 시계열 데이터 분석
- 스트리밍 데이터 청크 처리
- 대용량 데이터의 실시간 처리

### 📝 예시 상황
- IoT 센서 데이터의 실시간 분석
- 주식 시장 데이터의 시간별 분석
- 로그 데이터의 청크 단위 처리

```java
Flux.range(1, 10)
    .window(3)  // 3개씩 윈도우 생성
    .flatMap(windowFlux -> windowFlux.collectList())
    .subscribe(System.out::println);
// 출력: [1, 2, 3], [4, 5, 6], [7, 8, 9], [10]
```

[목차로 돌아가기 👆](#-table-of-contents)

---

## Buffer: 버퍼 크기 지정 🌊

### ✨ 주요 특징
- 데이터를 List로 모아서 처리
- 지정된 크기만큼 데이터 수집
- Window와 달리 직접 List 반환
- 메모리 효율적인 처리 가능

### 💡 사용 사례
- 대용량 데이터 일괄 처리
- 네트워크 요청 최적화
- 메모리 사용량 제어
- 배치 처리 작업

### 📝 예시 상황
- 대량 이메일 발송 처리
- 데이터베이스 배치 입력
- API 요청 일괄 처리

```java
Flux.range(1, 10)
    .buffer(3)  // 3개씩 버퍼링
    .subscribe(System.out::println);
// 출력: [1, 2, 3], [4, 5, 6], [7, 8, 9], [10]
```

[목차로 돌아가기 👆](#-table-of-contents)

---

## GroupBy: 그룹 지정 📝

### ✨ 주요 특징
- 키 기준으로 데이터 그룹화
- 각 그룹은 별도의 Flux로 생성
- 동적 그룹 생성 가능
- 데이터 분류와 집계에 효과적

### 💡 사용 사례
- 데이터 분류 및 집계
- 카테고리별 처리
- 사용자별 데이터 구분
- 통계 데이터 생성

### 📝 예시 상황
- 사용자 활동 로그 분석
- 상품 카테고리별 통계
- 지역별 판매 데이터 집계

```java
Flux.just(1, 2, 3, 4, 5, 6)
    .groupBy(n -> n % 2 == 0 ? "짝수" : "홀수")
    .flatMap(group -> group.collectList()
        .map(list -> group.key() + ": " + list))
    .subscribe(System.out::println);
// 출력: 
// 홀수: [1, 3, 5]
// 짝수: [2, 4, 6]
```

[목차로 돌아가기 👆](#-table-of-contents)

---

#### 예제 소스코드 📖
[Window](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/sequenceSeperate/Window01.java)
[Buffer](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/sequenceSeperate/Buffer01.java)
[BufferTimeout](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/sequenceSeperate/BufferTimeout01.java)
[GroupBy](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/sequenceSeperate/GroupBy01.java)

---

## 💫 마무리

시퀀스 분리 연산자들은 데이터 스트림을 효과적으로 분할하고 그룹화하는 데 사용됩니다.

> 💡 **Pro Tips:**
> - 실시간 처리는 `window` 사용
> - 배치 처리는 `buffer` 사용
> - 데이터 분류는 `groupBy` 사용
> - 메모리 사용량을 고려하여 적절한 크기 설정
> - 각 연산자의 특성을 고려하여 적절히 선택

---