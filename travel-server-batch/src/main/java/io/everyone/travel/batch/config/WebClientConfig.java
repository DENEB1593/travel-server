package io.everyone.travel.batch.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    // TODO API Properties

    @Bean("publicDataWebClient")
    public WebClient publicDataWebClient(
        ReactorResourceFactory reactorResourceFactory
    ) {
        return WebClient.builder()
            .baseUrl("/public/data")
            .defaultHeaders(httpHeaders -> {
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            })
            .clientConnector(
                this.customizeConnector(reactorResourceFactory)
            )
            .build();
    }

    private ClientHttpConnector customizeConnector(
        ReactorResourceFactory reactorResourceFactory
    ) {
        return new ReactorClientHttpConnector(reactorResourceFactory, httpClient -> {
            ConnectionProvider provider = ConnectionProvider.builder("public-data")
                .maxConnections(100)
                .pendingAcquireMaxCount(1000)
                .maxIdleTime(Duration.ofMillis(1000))
                .maxLifeTime(Duration.ofMillis(1000))
                .build();

            return HttpClient.create(provider)
                .metrics(true, uri -> "/")
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(
                        new ReadTimeoutHandler(1000)
                    );
                });
        });
    }

}
