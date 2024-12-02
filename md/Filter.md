# 🌟 Reactor Practice: Filtering Operators 🌟

## 🎯 Table of Contents 
| 연산자 | 설명 |
|:--:|:--|
| [🎯 Filter](#filter-필터링의-기본) | 동기적으로 조건에 맞는 데이터를 필터링하는 연산자 |
| [🚀 FilterWhen](#filterwhen-비동기-필터링의-파워) | 비동기적으로 조건을 평가하여 필터링하는 연산자 |
| [🍏 Next](#next-첫-번째가-중요해) | 스트림의 첫 번째 데이터를 가져오는 연산자 |
| [⏭️ Skip](#skip-건너뛰기의-미학) | 처음 몇 개의 데이터를 건너뛰는 연산자 |
| [🎯 Take](#take-원하는-만큼만-깔끔하게) | 처음 몇 개의 데이터만 선택하는 연산자 |
| [⏳ TakeLast](#takelast-마지막이-결정적이야) | 마지막 몇 개의 데이터만 선택하는 연산자 |
| [🕒 TakeWhile](#takewhile-조건부-수집의-예술) | 조건이 참인 동안 데이터를 선택하는 연산자 |

---

## Filter: 필터링의 기본 🎯

### ✨ 주요 특징
- 동기적으로 조건을 평가하여 데이터를 필터링
- Predicate를 사용하여 조건 정의
- 스트림의 각 요소에 대해 조건을 검사

### 💡 사용 사례
- 짝수/홀수 필터링
- 특정 조건을 만족하는 객체만 선택
- 유효한 데이터만 추출

### 📝 예시 상황
- 양수만 필터링
- 특정 가격 이상의 상품만 선택
- 활성 사용자만 필터링

### 예제 소스
[Filter01](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/filtering/Filter01.java)


[목차로 돌아가기 👆](#-table-of-contents)


---

## FilterWhen: 비동기 필터링의 파워 🚀

### ✨ 주요 특징
- 비동기적으로 조건을 평가
- Mono<Boolean>을 반환하는 조건 사용
- 외부 서비스 호출이나 DB 조회와 함께 사용 가능

### 💡 사용 사례
- 데이터베이스 조회 결과에 따른 필터링
- 외부 API 응답에 기반한 필터링
- 비동기 유효성 검사

### 📝 예시 상황
- 사용자 권한 확인 후 필터링
- 실시간 재고 확인 후 상품 필터링
- 외부 서비스의 응답에 따른 데이터 필터링

### 예제 소스
[FilterWhen](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/filtering/FilterWhen01.java)


[목차로 돌아가기 👆](#-table-of-contents)

---

## Next: 첫 번째가 중요해 🍏

### ✨ 주요 특징
- Flux에서 첫 번째 요소만 선택
- Mono로 결과 반환
- 빈 스트림의 경우 빈 Mono 반환

### 💡 사용 사례
- 첫 번째 매칭 항목만 필요한 경우
- 샘플 데이터 추출
- 스트림의 시작 요소 확인

### 📝 예시 상황
- 첫 번째 로그 엔트리 확인
- 가장 최근 알림 조회
- 첫 번째 매칭 사용자 찾기

### 예제 소스
[Next](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/filtering/Next.java)

[목차로 돌아가기 👆](#-table-of-contents)

---

## Take: 원하는 만큼만 깔끔하게 🎯

### ✨ 주요 특징
- 지정된 수만큼의 요소만 선택
- 시간 기반 제한 가능
- 백프레셔 지원

### 💡 사용 사례
- 페이지네이션
- 데이터 샘플링
- 스트림 제한

### 📝 예시 상황
- 상위 N개 결과 조회
- 처음 5초 동안의 이벤트만 처리
- 최근 10개의 로그 항목 조회

[목차로 돌아가기 👆](#-table-of-contents)

### 예제 소스
[Take](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/filtering/Take01.java)

---

## TakeLast: 마지막이 결정적이야 ⏳

### ✨ 주요 특징
- 마지막 N개의 요소만 선택
- 전체 스트림이 완료될 때까지 대기
- 메모리에 선택할 요소 수만큼 버퍼링

### 💡 사용 사례
- 최근 항목 조회
- 마지막 N개의 로그 확인
- 최신 업데이트 확인

### 📝 예시 상황
- 마지막 3개의 거래 내역 조회
- 최근 5개의 시스템 알림 표시
- 최신 댓글 목록 표시

### 예제 소스
[TakeLast](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/filtering/TakeLast.java)

[목차로 돌아가기 👆](#-table-of-contents)

---

## TakeWhile: 조건부 수집의 예술 🕒

### ✨ 주요 특징
- 조건이 참인 동안만 요소를 선택
- 조건이 거짓이 되면 즉시 완료
- 순차적 평가

### 💡 사용 사례
- 임계값까지의 데이터 수집
- 특정 조건을 만족하는 연속된 데이터 처리
- 스트림 조기 종료

### 📝 예시 상황
- 오류가 발생하기 전까지의 데이터 수집
- 특정 값 이하의 연속된 측정값 처리
- 유효한 세션 동안의 이벤트 처리

[목차로 돌아가기 👆](#-table-of-contents)

### 예제 소스
[TakeWhile](/example_code/src/main/java/com/reactor/reactor_pracitce/operator/filtering/TakeWhile01.java)

---

## 💫 마무리

각 필터링 연산자는 특정 사용 사례에 최적화되어 있습니다. 상황에 따라 적절한 연산자를 선택하여 사용하면 효율적인 데이터 처리가 가능합니다.

> 💡 **Pro Tips:**
> - 비동기 처리가 필요한 경우 `filterWhen` 사용
> - 단순한 조건 필터링에는 `filter` 사용
> - 제한된 데이터만 필요한 경우 `take` 계열 연산자 사용

---