package io.everyone.travel.batch.client;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.PrematureCloseException;
import reactor.util.retry.Retry;
import reactor.util.retry.RetrySpec;

import java.util.Objects;

@Slf4j
public class PubDataClient {

    private final WebClient webClient;
    private final CircuitBreaker circuitBreaker;


    public PubDataClient(
        @Qualifier("publicDataWebClient") WebClient webClient,
        CircuitBreakerRegistry circuitBreakerRegistry
    ) {
        this.webClient = webClient;
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("travel-server");
    }

    public void getPublicData() {
        Mono<String> response = webClient.get()
            .uri("/list")
            .attribute("SOME_KEY", "SOME_VALUE")
            .retrieve()
            .onStatus(
                statusCode -> statusCode.value() > HttpStatus.INTERNAL_SERVER_ERROR.value(),
                clientResponse -> clientResponse.createException()
                // .map() Custom Exception은 알아서 매핑
            )
            .bodyToMono(String.class)
            .transformDeferred(CircuitBreakerOperator.of(this.circuitBreaker)) // circuit break
            .doOnSuccess(it -> log.info("success log: {}", it))
            .onErrorMap(throwable -> throwable)
            .retryWhen(getRetryBeforeThrownInfo());
    }

    private static RetrySpec getRetryBeforeThrownInfo() {
        return Retry.max(2)
            .filter(throwable -> isPrematureCloseException(throwable))
            .doBeforeRetry(before -> log.info("retry before thrown info", before.failure()));
    }


    private static boolean isPrematureCloseException(Throwable throwable) {
        // Connection prematurely closed BEFORE response 오류 인 경우
        return getCauseRecursive(throwable) instanceof PrematureCloseException;
    }

    private static Throwable getCauseRecursive(Throwable throwable) {
        if (throwable.getCause() != null) {
            return getCauseRecursive(throwable.getCause());
        }
        return throwable;
    }
}
