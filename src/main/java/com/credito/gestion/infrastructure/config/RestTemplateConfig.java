package com.credito.gestion.infrastructure.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(5);
    private static final Duration BURO_TIMEOUT = Duration.ofSeconds(3);
    private static final Duration CORE_TIMEOUT = Duration.ofSeconds(4);

    @Bean
    public RestTemplate defaultRestTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(DEFAULT_TIMEOUT)
                .setReadTimeout(DEFAULT_TIMEOUT)
                .connectTimeout(Duration.ofSeconds(5))
                .readTimeout(Duration.ofSeconds(5))
                .build();
    }

    @Bean
    @Qualifier("buroRestTemplate")
    public RestTemplate buroRestTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(BURO_TIMEOUT)
                .setReadTimeout(BURO_TIMEOUT)
                .connectTimeout(Duration.ofSeconds(3))
                .readTimeout(Duration.ofSeconds(3))
                .build();
    }

    @Bean
    @Qualifier("coreRestTemplate")
    public RestTemplate coreRestTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(CORE_TIMEOUT)
                .setReadTimeout(CORE_TIMEOUT)
                .connectTimeout(Duration.ofSeconds(4))
                .readTimeout(Duration.ofSeconds(4))
                .build();
    }
}