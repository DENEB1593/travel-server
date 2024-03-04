package io.everyone.travel.batch.client;

import io.everyone.travel.batch.client.property.PubDataApiProperty;
import io.everyone.travel.batch.client.response.TravelAlarmResponse;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.PrematureCloseException;
import reactor.util.retry.Retry;
import reactor.util.retry.RetrySpec;

@Slf4j
@Component
public class PubDataApiClient {

    private final WebClient webClient;
    private final CircuitBreaker circuitBreaker;
    private final PubDataApiProperty apiProperty;


    public PubDataApiClient(
        @Qualifier("publicDataWebClient") WebClient webClient,
        CircuitBreakerRegistry circuitBreakerRegistry,
        PubDataApiProperty apiProperty
    ) {
        this.webClient = webClient;
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("travel-server");
        this.apiProperty = apiProperty;
    }

    public TravelAlarmResponse getTravelAlarmList() {
        return webClient.get()
            .uri(uriBuilder ->
                uriBuilder
                    .path("/TravelAlarmService2/getTravelAlarmList2")
                    .queryParam("serviceKey", apiProperty.serviceKey())
                    .queryParam("pageNo", apiProperty.pageNo())
                    .queryParam("numOfRows", apiProperty.numOfRows())
                    .build()
            )
            .attribute("method", PubDataApiClient.class.getSimpleName() + " getTravelAlarmList")
            .retrieve()
            .onStatus(
                statusCode -> statusCode.value() > HttpStatus.INTERNAL_SERVER_ERROR.value(),
                clientResponse -> clientResponse.createException()
                // .map() Custom Exception은 알아서 매핑
            )
            .bodyToMono(TravelAlarmResponse.class)
            .transformDeferred(CircuitBreakerOperator.of(this.circuitBreaker)) // circuit break
            .retryWhen(getRetryBeforeThrownInfo())
            .block();
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
