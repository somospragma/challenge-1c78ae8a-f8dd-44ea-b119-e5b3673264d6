# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Diseño y Automatización de Funcionalidades en Sistema de Gestión de Créditos**.

| | |
|---|---|
| Tema | desarrollador-senior-con-sólida-experiencia-en-bdd-y-frameworks-de-automatización |
| Nivel | senior-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean con capas de dominio, aplicación e infraestructura |
| Tiempo estimado | 2 semanas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web 3.5.6
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-test n/a
- io.cucumber:cucumber-java 7.18.0
- io.cucumber:cucumber-junit 7.18.0
- io.cucumber:cucumber-spring 7.18.0
- org.postgresql:postgresql 42.7.3
- org.projectlombok:lombok 1.18.34
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.6.0
- io.github.resilience4j:resilience4j-spring-boot3 2.2.0
- io.github.resilience4j:resilience4j-micrometer 2.2.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Especificación de Funcionalidades en BDD**: Especificaciones en lenguaje de negocio para las funcionalidades del sistema.
- **Fase 2 — Automatización de Pruebas con Frameworks de Automatización**: Casos de prueba automatizados para las funcionalidades del sistema.
- **Fase 3 — Refactorización y Optimización**: Código refactorizado y optimizado para las funcionalidades del sistema.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Referencias colgando (73)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/credito/gestion/Application.java` — `io.micrometer.core`
      El import io.micrometer.core.instrument.MeterRegistry pertenece a io.micrometer.core, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/BuroRiesgosRestAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/credito/gestion/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.numeroOperacion`
      Se invoca `numeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.tipoDocumento`
      Se invoca `tipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.numeroDocumento`
      Se invoca `numeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.resultadoAntifraude`
      Se invoca `resultadoAntifraude` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.resultadoBuro`
      Se invoca `resultadoBuro` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.resultadoCore`
      Se invoca `resultadoCore` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.monto`
      Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoJpaAdapter.java` — `SolicitudCreditoJpaRepository.save`
      Se invoca `save` sobre `SolicitudCreditoJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.numeroOperacion`
      Se invoca `numeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.tipoDocumento`
      Se invoca `tipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.numeroDocumento`
      Se invoca `numeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.monto`
      Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.plazo`
      Se invoca `plazo` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.destino`
      Se invoca `destino` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.resultadoAntifraude`
      Se invoca `resultadoAntifraude` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.resultadoBuro`
      Se invoca `resultadoBuro` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.resultadoCore`
      Se invoca `resultadoCore` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.observaciones`
      Se invoca `observaciones` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.estado`
      Se invoca `estado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.fechaCreacion`
      Se invoca `fechaCreacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java` — `SolicitudCredito.numeroOperacion`
      Se invoca `numeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java` — `SolicitudCredito.tipoDocumento`
      Se invoca `tipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java` — `SolicitudCredito.numeroDocumento`
      Se invoca `numeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java` — `SolicitudCredito.monto`
      Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java` — `SolicitudCredito.plazo`
      Se invoca `plazo` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java` — `SolicitudCredito.destino`
      Se invoca `destino` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/BuroRiesgosRestAdapter.java` — `SolicitudCredito.numeroOperacion`
      Se invoca `numeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/BuroRiesgosRestAdapter.java` — `SolicitudCredito.tipoDocumento`
      Se invoca `tipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/BuroRiesgosRestAdapter.java` — `SolicitudCredito.numeroDocumento`
      Se invoca `numeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/BuroRiesgosRestAdapter.java` — `SolicitudCredito.monto`
      Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java` — `SolicitudCredito.numeroOperacion`
      Se invoca `numeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java` — `SolicitudCredito.tipoDocumento`
      Se invoca `tipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java` — `SolicitudCredito.numeroDocumento`
      Se invoca `numeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java` — `SolicitudCredito.monto`
      Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java` — `SolicitudCredito.plazo`
      Se invoca `plazo` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java` — `SolicitudCredito.tipoCredito`
      Se invoca `tipoCredito` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java` — `SolicitudCredito.tasaInteres`
      Se invoca `tasaInteres` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCreditoRequest.numeroDocumento`
      Se invoca `numeroDocumento` sobre `SolicitudCreditoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCreditoRequest.numeroOperacion`
      Se invoca `numeroOperacion` sobre `SolicitudCreditoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCreditoRequest.tipoDocumento`
      Se invoca `tipoDocumento` sobre `SolicitudCreditoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCreditoRequest.monto`
      Se invoca `monto` sobre `SolicitudCreditoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCreditoRequest.plazo`
      Se invoca `plazo` sobre `SolicitudCreditoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCreditoRequest.tipoCredito`
      Se invoca `tipoCredito` sobre `SolicitudCreditoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCreditoRequest.tasaInteres`
      Se invoca `tasaInteres` sobre `SolicitudCreditoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.numeroOperacion`
      Se invoca `numeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.estado`
      Se invoca `estado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.isEmpty`
      Se invoca `isEmpty` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.get`
      Se invoca `get` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.tipoDocumento`
      Se invoca `tipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.numeroDocumento`
      Se invoca `numeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.monto`
      Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.plazo`
      Se invoca `plazo` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.tipoCredito`
      Se invoca `tipoCredito` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.tasaInteres`
      Se invoca `tasaInteres` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.resultadoAntifraude`
      Se invoca `resultadoAntifraude` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.resultadoBuro`
      Se invoca `resultadoBuro` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.resultadoCore`
      Se invoca `resultadoCore` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.observaciones`
      Se invoca `observaciones` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/gestion/steps/SolicitudCreditoSteps.java` — `SolicitudCredito.numeroOperacion`
      Se invoca `numeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/gestion/steps/SolicitudCreditoSteps.java` — `SolicitudCredito.tipoDocumento`
      Se invoca `tipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/gestion/steps/SolicitudCreditoSteps.java` — `SolicitudCredito.numeroDocumento`
      Se invoca `numeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/gestion/steps/SolicitudCreditoSteps.java` — `SolicitudCredito.monto`
      Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/gestion/steps/SolicitudCreditoSteps.java` — `SolicitudCredito.plazoMeses`
      Se invoca `plazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/gestion/steps/SolicitudCreditoSteps.java` — `CoreBancarioService.consultarCore`
      Se invoca `consultarCore` sobre `CoreBancarioService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (26)

- `pom.xml`
- `src/main/java/com/credito/gestion/Application.java`
- `src/main/resources/application.yml`
- `src/main/java/com/credito/gestion/domain/model/SolicitudCredito.java`
- `src/main/java/com/credito/gestion/domain/port/SolicitudCreditoRepository.java`
- `src/main/java/com/credito/gestion/domain/port/AntifraudeService.java`
- `src/main/java/com/credito/gestion/domain/port/BuroRiesgosService.java`
- `src/main/java/com/credito/gestion/domain/port/CoreBancarioService.java`
- `src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java`
- `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoJpaAdapter.java`
- `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java`
- `src/main/java/com/credito/gestion/infrastructure/entity/SolicitudCreditoEntity.java`
- `src/main/java/com/credito/gestion/infrastructure/repository/SolicitudCreditoJpaRepository.java`
- `src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java`
- `src/main/java/com/credito/gestion/infrastructure/adapter/BuroRiesgosRestAdapter.java`
- `src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java`
- `src/main/java/com/credito/gestion/infrastructure/config/RestTemplateConfig.java`
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java`
- `src/main/resources/features/solicitud_credito.feature`
- `src/main/java/com/credito/gestion/infrastructure/exception/GlobalExceptionHandler.java`
- `src/main/java/com/credito/gestion/infrastructure/exception/SolicitudDuplicadaException.java`
- `src/main/java/com/credito/gestion/infrastructure/exception/TimeoutException.java`
- `src/main/java/com/credito/gestion/infrastructure/exception/CoreBancarioException.java`
- `src/test/java/com/credito/gestion/steps/SolicitudCreditoSteps.java`
- `src/test/java/com/credito/gestion/CucumberTest.java`
- `src/test/resources/cucumber.properties`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/credito/gestion`
- `src/main/java/com/credito/gestion/domain`
- `src/main/java/com/credito/gestion/domain/model`
- `src/main/java/com/credito/gestion/domain/port`
- `src/main/java/com/credito/gestion/application`
- `src/main/java/com/credito/gestion/application/usecase`
- `src/main/java/com/credito/gestion/infrastructure`
- `src/main/java/com/credito/gestion/infrastructure/adapter`
- `src/main/java/com/credito/gestion/infrastructure/config`
- `src/main/java/com/credito/gestion/infrastructure/controller`
- `src/main/resources`
- `src/test/java/com/credito/gestion`
- `src/test/resources/features`

## Verificacion

```bash
mvn clean compile
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean con capas de dominio, aplicación e infraestructura**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior
- Brecha que el reto ataca: Aplica metodologías de desarrollo basadas en comportamiento como BDD (Desarrollo Guiado por Comportamiento) y trabaja bajo herramientas de automatización como Cucumber, el framework Karate, etc.
- Mision: Candidato con experiencia sólida en backend, con capacidad de diseñar soluciones robustas en Java.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
