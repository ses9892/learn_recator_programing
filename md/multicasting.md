# 🌟 Reactor Practice: Multicasting 🌟

## 🎯 Table of Contents 
| 연산자 | 설명 |
|:--:|:--|
| [🔄 Publish](#publish-구독자-공유의-시작-) | 하나의 소스를 여러 구독자와 공유 |
| [🌊 RefCount](#refcount-구독자-기반-실행-제어-) | 구독자 수에 따른 동적 실행 관리 |
| [🔥 AutoConnect](#autoconnect-자동-연결의-편의성-) | 첫 구독 시 자동으로 소스에 연결 |
| [🔗 Cache](#cache-결과-캐싱으로-효율성-극대화-) | 소스의 결과를 캐싱하여 재사용 |
---

## Publish: 구독자 공유의 시작 🔄

### ✨ 주요 특징
- 하나의 소스를 여러 구독자와 공유
- Cold 스트림을 Hot 스트림으로 변환
- 구독자 간 데이터 동기화 가능

### 💡 사용 사례
- 실시간 데이터 브로드캐스팅
- 여러 컴포넌트 간 데이터 공유
- 리소스 집약적인 작업 결과 공유

### 📝 예시 상황
- 주식 시세 정보 실시간 배포
- 센서 데이터의 다중 처리
- 대규모 계산 결과의 공유

[목차로 돌아가기 👆](#-table-of-contents)

---

## RefCount: 구독자 기반 실행 제어 🌊

### ✨ 주요 특징
- 구독자 수에 따라 소스 실행 관리
- 첫 구독자 등장 시 소스 실행 시작
- 마지막 구독자 해제 시 소스 실행 종료

### 💡 사용 사례
- 리소스 효율적인 멀티캐스팅
- 동적 구독 관리가 필요한 상황
- 자동 시작/종료가 필요한 장기 실행 작업

### 📝 예시 상황
- 데이터베이스 연결 풀 관리
- WebSocket 연결의 동적 관리
- 백그라운드 작업의 자동 시작/종료

[목차로 돌아가기 👆](#-table-of-contents)

---

## AutoConnect: 자동 연결의 편의성 🔥

### ✨ 주요 특징
- 첫 구독 시 자동으로 소스에 연결
- 구독자가 없어져도 연결 유지
- 간편한 Hot 스트림 생성

### 💡 사용 사례
- 지속적인 데이터 스트림 유지
- 구독자 독립적인 데이터 소스 관리
- 백그라운드 프로세스 유지

### 📝 예시 상황
- 로그 스트림 지속 모니터링
- 시스템 상태 지속 추적
- 외부 이벤트 스트림 연속 수신

[목차로 돌아가기 👆](#-table-of-contents)

---

## Cache: 결과 캐싱으로 효율성 극대화 🔗

### ✨ 주요 특징
- 소스의 결과를 메모리에 캐싱
- 후속 구독자에게 캐시된 데이터 제공
- 비용이 큰 작업의 재실행 방지

### 💡 사용 사례
- 비용이 높은 연산 결과 재사용
- API 호출 결과 캐싱
- 자주 접근되는 데이터의 빠른 제공

### 📝 예시 상황
- 데이터베이스 쿼리 결과 캐싱
- 외부 API 응답 캐싱
- 복잡한 계산 결과의 재사용

[목차로 돌아가기 👆](#-table-of-contents)

---

#### 예제 소스코드 📖
[Publish01](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/multicasting/Publish01.java)
[Publish02](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/multicasting/Publish02.java)
[Publish03](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/multicasting/Publish03.java)
[RefCount](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/multicasting/RefCount.java)
[Cache](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/multicasting/CacheExample.java)

---

## 💫 마무리

멀티캐스팅 연산자들은 리액티브 스트림의 효율성과 유연성을 크게 향상시킵니다.

> 💡 **Pro Tips:**
> - `publish()`와 `refCount()`를 함께 사용하여 동적 Hot 스트림 생성
> - `autoConnect()`로 간편한 Hot 스트림 관리
> - `cache()`를 사용하여 비용이 큰 작업의 결과 재사용
> - 적절한 연산자 선택으로 리소스 사용 최적화