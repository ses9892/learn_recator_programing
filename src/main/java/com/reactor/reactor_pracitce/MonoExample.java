package com.reactor.reactor_pracitce;

import java.net.URI;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import reactor.core.publisher.Mono;

// Mono : 단일 데이터를 생성하고 Emit 하는 Publisher
public class MonoExample {
    private static final Logger log = LoggerFactory.getLogger(MonoExample.class);

    /**
     * Mono.just() : 데이터를 생성하고 발행
     * subscribe 메서드의 1번째 인자 : Mono에서 Publisher로 데이터를 받아옴
     */
    public static void monoTest1() {
        Mono.just("Hello Mono")
            .subscribe(data -> log.info("subscribe: {}", data));
    }

    /**
     * Mono.empty() : 데이터를 생성하지 않고 완료만 신호를 보냄
     * subscribe 메서드의 1번째 인자 : Mono에서 Publisher로 데이터를 받아옴 (없으므로 호출되지 않음)
     * subscribe 메서드의 2번째 인자 : Mono에서 Publisher로 에러를 받아옴 (없으므로 호출되지 않음)
     * subscribe 메서드의 3번째 인자 : Mono에서 Publisher로 완료 신호를 받아옴 (finally 블록에 해당)
     */
    public static void monoTest2() {
        Mono.empty()
            .subscribe(
                data -> log.info("subscribe: {}", data),
                error -> log.error("error: {}", error),
                () -> log.info("complete")
            );
    }

    /**
     * 웹 서버에서 데이터를 가져오는 예제
     * map 메서드 : 데이터를 변환하는 메서드
     * subscribe 메서드 : 데이터를 소비하는 메서드
     */
    public static void monoTest3() {

        URI worldTimeUri = UriComponentsBuilder.newInstance().scheme("https")
            .host("json-server-vercel-five-sandy.vercel.app")
            .path("/canvases")
            .build()
            .encode()
            .toUri();

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


            Mono.just(
                restTemplate.exchange(worldTimeUri, HttpMethod.GET, new HttpEntity<String>(headers), String.class)
                )
                .map(res -> {
                    DocumentContext documentContext = JsonPath.parse(res.getBody());
                    return  documentContext.jsonString().replaceAll("title", "TITLE");
                })
                .subscribe(
                    data -> log.info("subscribe: {}", data),
                    error -> log.error("error: {}", error),
                    () -> log.info("complete")
                );

    }

    public static void main(String[] args) {
        // monoTest1();
        monoTest3();
    }

}
