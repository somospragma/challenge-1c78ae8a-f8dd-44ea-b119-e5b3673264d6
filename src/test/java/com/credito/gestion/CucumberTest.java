package com.credito.gestion;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/main/resources/features",
    glue = {"com.credito.gestion.steps", "com.credito.gestion.infrastructure.config"},
    plugin = {
        "pretty",
        "json:target/cucumber-reports/cucumber.json",
        "html:target/cucumber-reports/cucumber.html",
        "junit:target/cucumber-reports/cucumber.xml"
    },
    tags = "@solicitudCredito or @idempotencia or @validacion",
    strict = true,
    dryRun = false
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
    "spring.datasource.driverClassName=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.jpa.show-sql=true",
    "server.port=0",
    "spring.cloud.discovery.enabled=false",
    "resilience4j.circuitbreaker.configs.default.slidingWindowSize=10",
    "resilience4j.circuitbreaker.configs.default.permittedNumberOfCallsInHalfOpenState=3",
    "resilience4j.circuitbreaker.configs.default.waitDurationInOpenState=5s",
    "resilience4j.circuitbreaker.configs.default.failureRateThreshold=50",
    "resilience4j.retry.configs.default.maxAttempts=3",
    "resilience4j.retry.configs.default.waitDuration=500ms",
    "external.services.antifraude.url=http://localhost:8081",
    "external.services.buro-riesgos.url=http://localhost:8082",
    "external.services.core-bancario.url=http://localhost:8083",
    "external.services.timeout=2000"
})
public class CucumberTest {
}