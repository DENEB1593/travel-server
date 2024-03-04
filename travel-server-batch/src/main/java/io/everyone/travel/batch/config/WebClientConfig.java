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
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilderFactory;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient publicDataWebClient(
        ReactorResourceFactory reactorResourceFactory
    ) {
        return WebClient.builder()
            .baseUrl("http://apis.data.go.kr/1262000")
            .uriBuilderFactory(defaultUriFactory())
            .defaultHeaders(httpHeaders -> {
                httpHeaders.setContentType(APPLICATION_JSON);
            })
            .clientConnector(
                this.customizeConnector(reactorResourceFactory)
            )
            .build();
    }

    private UriBuilderFactory defaultUriFactory() {
        var factory = new DefaultUriBuilderFactory("http://apis.data.go.kr/1262000");
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        return factory;
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
