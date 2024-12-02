# 🌟 Reactor Practice: Sequence Modify Operators 🌟

## 🎯 Table of Contents 
| 연산자 | 설명 |
|:--:|:--|
| [🔄 Map](#map-기본-변환의-시작-) | 각 요소를 동기적으로 변환하는 연산자 |
| [🌊 FlatMap](#flatmap-비동기-변환의-마법-) | 각 요소를 비동기적으로 변환하고 평탄화하는 연산자 |
| [📝 CollectList](#collectlist-모아서-한방에-) | 모든 요소를 리스트로 수집하는 연산자 |
| [🗺️ CollectMap](#collectmap-키-값으로-정리-️) | 요소들을 Map으로 수집하는 연산자 |
| [🔗 Concat](#concat-순서대로-이어붙이기-) | 여러 Publisher를 순차적으로 연결하는 연산자 |
| [🤝 Merge](#merge-비동기-결합의-예술-) | 여러 Publisher를 비동기적으로 병합하는 연산자 |
| [🎯 Zip](#zip-완벽한-짝맞추기-) | 여러 Publisher의 요소들을 조합하는 연산자 |
| [✨ And](#and-모두-완료될-때까지-) | 여러 작업의 완료를 기다리는 연산자 |
---

## Map: 기본 변환의 시작 🔄

### ✨ 주요 특징
- 각 요소를 1:1로 변환
- 동기적 변환 수행
- 스트림의 요소 수 유지

### 💡 사용 사례
- 데이터 형식 변환
- 값 계산 및 가공
- 객체 변환

### 📝 예시 상황
- 문자열을 대문자로 변환
- 숫자에 특정 연산 적용
- DTO를 엔티티로 변환

### 예제 소스
[Map01](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/sequenceModify/Map01.java)

[목차로 돌아가기 👆](#-table-of-contents)

---

## FlatMap: 비동기 변환의 마법 🌊

### ✨ 주요 특징
- 비동기 변환 지원
- 1:N 변환 가능
- 결과를 평탄화하여 단일 스트림으로 병합

### 💡 사용 사례
- 비동기 API 호출
- 중첩된 데이터 처리
- 병렬 처리가 필요한 변환

### 📝 예시 상황
- 외부 API 동시 호출
- 데이터베이스 비동기 조회
- 복잡한 데이터 변환

### 예제 소스
[FlatMap](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/sequenceModify/FlatMap.java)

[목차로 돌아가기 👆](#-table-of-contents)

---

## CollectList: 모아서 한방에 📝

### ✨ 주요 특징
- 모든 요소를 단일 리스트로 수집
- Flux를 Mono<List>로 변환
- 모든 요소가 방출될 때까지 대기

### 💡 사용 사례
- 배치 처리
- 전체 데이터 수집
- 리스트 형태의 응답 생성

### 📝 예시 상황
- API 응답 데이터 수집
- 전체 결과 한 번에 처리
- 데이터 일괄 저장

### 예제 소스
[CollectList](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/sequenceModify/CollectList.java)

[목차로 돌아가기 👆](#-table-of-contents)

---

## CollectMap: 키-값으로 정리 🗺️

### ✨ 주요 특징
- 요소들을 Map 형태로 수집
- 키와 값을 지정하여 변환
- 중복 키는 마지막 값으로 덮어씀

### 💡 사용 사례
- 데이터 인덱싱
- 캐시 구성
- 그룹화된 데이터 처리

### 📝 예시 상황
- ID별 사용자 매핑
- 카테고리별 상품 정리
- 코드별 데이터 정리

### 예제 소스
[CollectMap](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/sequenceModify/CollectMap.java)

[목차로 돌아가기 👆](#-table-of-contents)

---

## Concat: 순서대로 이어붙이기 🔗

### ✨ 주요 특징
- Publisher들을 순차적으로 연결
- 순서 보장
- 앞선 Publisher가 완료된 후 다음 실행

### 💡 사용 사례
- 순차적 데이터 처리
- 우선순위가 있는 데이터 연결
- 조건부 데이터 추가

### 📝 예시 상황
- 페이지네이션 데이터 연결
- 기본 데이터와 부가 데이터 연결
- 단계별 데이터 처리

### 예제 소스
[Concat](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/sequenceModify/Concat.java)

[목차로 돌아가기 👆](#-table-of-contents)

---

## Merge: 비동기 결합의 예술 🤝

### ✨ 주요 특징
- 여러 Publisher를 동시에 구독
- 도착 순서대로 데이터 처리
- 비동기적 실행

### 💡 사용 사례
- 실시간 데이터 통합
- 여러 소스의 데이터 병합
- 비동기 이벤트 처리

### 📝 예시 상황
- 여러 센서 데이터 통합
- 다중 사용자 활동 추적
- 실시간 알림 통합

### 예제 소스
[Merge01](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/sequenceModify/Merge01.java), [Merge02](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/sequenceModify/Merge02.java)

[목차로 돌아가기 👆](#-table-of-contents)

---

## Zip: 완벽한 짝맞추기 🎯

### ✨ 주요 특징
- 여러 Publisher의 요소를 조합
- 모든 Publisher의 데이터가 필요
- 가장 느린 Publisher의 속도에 맞춤

### 💡 사용 사례
- 관련 데이터 결합
- 동기화된 데이터 처리
- 복합 데이터 생성

### 📝 예시 상황
- 사용자 정보와 주문 정보 결합
- 센서 데이터 동기화
- 다중 API 응답 조합

### 예제 소스
[Zip01](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/sequenceModify/Zip01.java)

[목차로 돌아가기 👆](#-table-of-contents)

---

## And: 모두 완료될 때까지 ✨

### ✨ 주요 특징
- 여러 작업의 완료를 대기
- 개별 결과값은 무시
- 모든 작업 완료 시 알림

### 💡 사용 사례
- 병렬 작업 완료 확인
- 초기화 작업 동기화
- 다중 작업 조율

### 📝 예시 상황
- 여러 서비스 초기화
- 다중 백업 작업 완료 대기
- 병렬 처리 작업 동기화

### 예제 소스
[And01](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/sequenceModify/And01.java)

[목차로 돌아가기 👆](#-table-of-contents)

---

## 💫 마무리

시퀀스 수정 연산자들은 리액티브 스트림의 데이터를 다양한 방식으로 변환하고 조합하는 강력한 도구입니다.

> 💡 **Pro Tips:**
> - 동기 변환은 `map`, 비동기 변환은 `flatMap` 사용
> - 순서가 중요한 경우 `concat` 사용
> - 비동기 결합이 필요한 경우 `merge` 사용
> - 데이터 쌍이 필요한 경우 `zip` 사용

---
