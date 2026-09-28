package com.credito.gestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import io.micrometer.core.instrument.MeterRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties
@ComponentScan(basePackages = {"com.credito.gestion"})
public class Application {
    
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final RetryRegistry retryRegistry;
    private final MeterRegistry meterRegistry;

    public Application(CircuitBreakerRegistry circuitBreakerRegistry,
                      RetryRegistry retryRegistry,
                      MeterRegistry meterRegistry) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
        this.retryRegistry = retryRegistry;
        this.meterRegistry = meterRegistry;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    public Executor taskExecutor() {
        return Executors.newFixedThreadPool(10);
    }
    
    @Bean
    public CircuitBreaker buroCircuitBreaker() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("buroCircuitBreaker");
        circuitBreaker.getEventPublisher()
                .onStateTransition(event -> meterRegistry.gauge("circuit_breaker_state", 
                        event.getStateTransition().getToState().getOrder()));
        return circuitBreaker;
    }
    
    @Bean
    public Retry buroRetry() {
        Retry retry = retryRegistry.retry("buroRetry");
        retry.getEventPublisher()
                .onRetry(event -> meterRegistry.counter("retry_attempts").increment());
        return retry;
    }
    
    @Bean
    public CircuitBreaker coreCircuitBreaker() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("coreCircuitBreaker");
        circuitBreaker.getEventPublisher()
                .onStateTransition(event -> meterRegistry.gauge("core_circuit_breaker_state", 
                        event.getStateTransition().getToState().getOrder()));
        return circuitBreaker;
    }
}