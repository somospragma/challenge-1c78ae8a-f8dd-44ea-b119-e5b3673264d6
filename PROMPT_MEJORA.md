# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/credito/gestion/Application.java` — `io.micrometer.core`: El import io.micrometer.core.instrument.MeterRegistry pertenece a io.micrometer.core, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/credito/gestion/infrastructure/adapter/BuroRiesgosRestAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/credito/gestion/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.numeroOperacion`: Se invoca `numeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.tipoDocumento`: Se invoca `tipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.numeroDocumento`: Se invoca `numeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.resultadoAntifraude`: Se invoca `resultadoAntifraude` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.resultadoBuro`: Se invoca `resultadoBuro` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.resultadoCore`: Se invoca `resultadoCore` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.monto`: Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoJpaAdapter.java` — `SolicitudCreditoJpaRepository.save`: Se invoca `save` sobre `SolicitudCreditoJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.numeroOperacion`: Se invoca `numeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.tipoDocumento`: Se invoca `tipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.numeroDocumento`: Se invoca `numeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.monto`: Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.plazo`: Se invoca `plazo` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.destino`: Se invoca `destino` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.resultadoAntifraude`: Se invoca `resultadoAntifraude` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.resultadoBuro`: Se invoca `resultadoBuro` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.resultadoCore`: Se invoca `resultadoCore` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.observaciones`: Se invoca `observaciones` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.estado`: Se invoca `estado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java` — `SolicitudCredito.fechaCreacion`: Se invoca `fechaCreacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java` — `SolicitudCredito.numeroOperacion`: Se invoca `numeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java` — `SolicitudCredito.tipoDocumento`: Se invoca `tipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java` — `SolicitudCredito.numeroDocumento`: Se invoca `numeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java` — `SolicitudCredito.monto`: Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java` — `SolicitudCredito.plazo`: Se invoca `plazo` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java` — `SolicitudCredito.destino`: Se invoca `destino` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/BuroRiesgosRestAdapter.java` — `SolicitudCredito.numeroOperacion`: Se invoca `numeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/BuroRiesgosRestAdapter.java` — `SolicitudCredito.tipoDocumento`: Se invoca `tipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/BuroRiesgosRestAdapter.java` — `SolicitudCredito.numeroDocumento`: Se invoca `numeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/BuroRiesgosRestAdapter.java` — `SolicitudCredito.monto`: Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java` — `SolicitudCredito.numeroOperacion`: Se invoca `numeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java` — `SolicitudCredito.tipoDocumento`: Se invoca `tipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java` — `SolicitudCredito.numeroDocumento`: Se invoca `numeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java` — `SolicitudCredito.monto`: Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java` — `SolicitudCredito.plazo`: Se invoca `plazo` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java` — `SolicitudCredito.tipoCredito`: Se invoca `tipoCredito` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java` — `SolicitudCredito.tasaInteres`: Se invoca `tasaInteres` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCreditoRequest.numeroDocumento`: Se invoca `numeroDocumento` sobre `SolicitudCreditoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCreditoRequest.numeroOperacion`: Se invoca `numeroOperacion` sobre `SolicitudCreditoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCreditoRequest.tipoDocumento`: Se invoca `tipoDocumento` sobre `SolicitudCreditoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCreditoRequest.monto`: Se invoca `monto` sobre `SolicitudCreditoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCreditoRequest.plazo`: Se invoca `plazo` sobre `SolicitudCreditoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCreditoRequest.tipoCredito`: Se invoca `tipoCredito` sobre `SolicitudCreditoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCreditoRequest.tasaInteres`: Se invoca `tasaInteres` sobre `SolicitudCreditoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.numeroOperacion`: Se invoca `numeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.estado`: Se invoca `estado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.isEmpty`: Se invoca `isEmpty` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.get`: Se invoca `get` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.tipoDocumento`: Se invoca `tipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.numeroDocumento`: Se invoca `numeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.monto`: Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.plazo`: Se invoca `plazo` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.tipoCredito`: Se invoca `tipoCredito` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.tasaInteres`: Se invoca `tasaInteres` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.resultadoAntifraude`: Se invoca `resultadoAntifraude` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.resultadoBuro`: Se invoca `resultadoBuro` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.resultadoCore`: Se invoca `resultadoCore` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java` — `SolicitudCredito.observaciones`: Se invoca `observaciones` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/gestion/steps/SolicitudCreditoSteps.java` — `SolicitudCredito.numeroOperacion`: Se invoca `numeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/gestion/steps/SolicitudCreditoSteps.java` — `SolicitudCredito.tipoDocumento`: Se invoca `tipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/gestion/steps/SolicitudCreditoSteps.java` — `SolicitudCredito.numeroDocumento`: Se invoca `numeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/gestion/steps/SolicitudCreditoSteps.java` — `SolicitudCredito.monto`: Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/gestion/steps/SolicitudCreditoSteps.java` — `SolicitudCredito.plazoMeses`: Se invoca `plazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/gestion/steps/SolicitudCreditoSteps.java` — `CoreBancarioService.consultarCore`: Se invoca `consultarCore` sobre `CoreBancarioService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior

### Brecha de conocimiento
Aplica metodologías de desarrollo basadas en comportamiento como BDD (Desarrollo Guiado por Comportamiento) y trabaja bajo herramientas de automatización como Cucumber, el framework Karate, etc.

### Misión / candidato
Candidato con experiencia sólida en backend, con capacidad de diseñar soluciones robustas en Java.

### Reto
- Tema: desarrollador-senior-con-sólida-experiencia-en-bdd-y-frameworks-de-automatización
- Seniority: senior-l2
- Tipo: practical
- Título: Diseño y Automatización de Funcionalidades en Sistema de Gestión de Créditos
- Tiempo estimado: 2 semanas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Especificación de Funcionalidades en BDD — objetivo: Definir las funcionalidades del sistema utilizando el lenguaje de negocio y BDD. — entregable (NO resolver): Especificaciones en lenguaje de negocio para las funcionalidades del sistema.
- Fase 2: Automatización de Pruebas con Frameworks de Automatización — objetivo: Implementar pruebas automatizadas para las funcionalidades definidas en la fase anterior. — entregable (NO resolver): Casos de prueba automatizados para las funcionalidades del sistema.
- Fase 3: Refactorización y Optimización — objetivo: Refactorizar y optimizar el código para mejorar la robustez y el rendimiento. — entregable (NO resolver): Código refactorizado y optimizado para las funcionalidades del sistema.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.credito</groupId>
    <artifactId>gestion</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>gestion</name>
    <description>Sistema de gestión de créditos con BDD y pruebas automatizadas</description>

    <properties>
        <java.version>21</java.version>
        <cucumber.version>7.18.0</cucumber.version>
        <resilience4j.version>2.2.0</resilience4j.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- Cucumber -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-spring</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- PostgreSQL -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
            <version>42.7.3</version>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.34</version>
            <scope>provided</scope>
        </dependency>

        <!-- OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.6.0</version>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-micrometer</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/credito/gestion/Application.java ===
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

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: gestion-creditos
  
  datasource:
    url: jdbc:postgresql://localhost:5432/creditos_db
    username: creditos_user
    password: creditos_pass
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 20
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect
        jdbc:
          batch_size: 50
  
  mvc:
    async:
      request-timeout: 5000

server:
  port: 8080
  servlet:
    context-path: /api/creditos

resilience4j:
  circuitbreaker:
    configs:
      default:
        registerHealthIndicator: true
        slidingWindowType: COUNT_BASED
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
    instances:
      buroCircuitBreaker:
        baseConfig: default
        waitDurationInOpenState: 10s
      coreCircuitBreaker:
        baseConfig: default
        waitDurationInOpenState: 15s
  
  retry:
    configs:
      default:
        maxAttempts: 3
        waitDuration: 1s
        enableExponentialBackoff: true
        exponentialBackoffMultiplier: 2
        retryExceptions:
          - org.springframework.web.client.ResourceAccessException
          - java.util.concurrent.TimeoutException
          - com.credito.gestion.infrastructure.exception.TimeoutException
    instances:
      buroRetry:
        baseConfig: default

logging:
  level:
    org.springframework: INFO
    com.credito.gestion: DEBUG
    io.github.resilience4j: INFO
    org.hibernate: ERROR

app:
  external-services:
    buro-riesgos:
      url: http://localhost:8081/api/buro
      timeout: 1500
    core-bancario:
      url: http://localhost:8082/api/core
      timeout: 2000
    antifraude:
      url: http://localhost:8083/api/antifraude
      timeout: 1000

// === ARCHIVO: src/main/java/com/credito/gestion/domain/model/SolicitudCredito.java ===
package com.credito.gestion.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record SolicitudCredito(
    UUID numeroOperacion,
    String tipoDocumento,
    String numeroDocumento,
    String nombreCompleto,
    BigDecimal montoSolicitado,
    Integer plazoMeses,
    String estado,
    LocalDate fechaSolicitud,
    LocalDate fechaRespuesta,
    String resultadoAntifraude,
    String resultadoBuro,
    String resultadoCore,
    String observaciones
) {
    public SolicitudCredito {
        if (numeroOperacion == null) {
            throw new IllegalArgumentException("El número de operación no puede ser nulo");
        }
        if (tipoDocumento == null || tipoDocumento.isBlank()) {
            throw new IllegalArgumentException("El tipo de documento no puede ser nulo o vacío");
        }
        if (numeroDocumento == null || numeroDocumento.isBlank()) {
            throw new IllegalArgumentException("El número de documento no puede ser nulo o vacío");
        }
        if (nombreCompleto == null || nombreCompleto.isBlank()) {
            throw new IllegalArgumentException("El nombre completo no puede ser nulo o vacío");
        }
        if (montoSolicitado == null || montoSolicitado.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto solicitado debe ser mayor que cero");
        }
        if (plazoMeses == null || plazoMeses <= 0) {
            throw new IllegalArgumentException("El plazo en meses debe ser mayor que cero");
        }
        if (estado == null || estado.isBlank()) {
            estado = "PENDIENTE";
        }
        if (fechaSolicitud == null) {
            fechaSolicitud = LocalDate.now();
        }
    }

    public SolicitudCredito conResultadoAntifraude(String resultado) {
        return new SolicitudCredito(
            this.numeroOperacion,
            this.tipoDocumento,
            this.numeroDocumento,
            this.nombreCompleto,
            this.montoSolicitado,
            this.plazoMeses,
            this.estado,
            this.fechaSolicitud,
            this.fechaRespuesta,
            resultado,
            this.resultadoBuro,
            this.resultadoCore,
            this.observaciones
        );
    }

    public SolicitudCredito conResultadoBuro(String resultado) {
        return new SolicitudCredito(
            this.numeroOperacion,
            this.tipoDocumento,
            this.numeroDocumento,
            this.nombreCompleto,
            this.montoSolicitado,
            this.plazoMeses,
            this.estado,
            this.fechaSolicitud,
            this.fechaRespuesta,
            this.resultadoAntifraude,
            resultado,
            this.resultadoCore,
            this.observaciones
        );
    }

    public SolicitudCredito conResultadoCore(String resultado) {
        return new SolicitudCredito(
            this.numeroOperacion,
            this.tipoDocumento,
            this.numeroDocumento,
            this.nombreCompleto,
            this.montoSolicitado,
            this.plazoMeses,
            "APROBADA".equals(resultado) ? "APROBADA" : "RECHAZADA",
            this.fechaSolicitud,
            LocalDate.now(),
            this.resultadoAntifraude,
            this.resultadoBuro,
            resultado,
            this.observaciones
        );
    }

    public SolicitudCredito conObservaciones(String observaciones) {
        return new SolicitudCredito(
            this.numeroOperacion,
            this.tipoDocumento,
            this.numeroDocumento,
            this.nombreCompleto,
            this.montoSolicitado,
            this.plazoMeses,
            this.estado,
            this.fechaSolicitud,
            this.fechaRespuesta,
            this.resultadoAntifraude,
            this.resultadoBuro,
            this.resultadoCore,
            observaciones
        );
    }
}

// === ARCHIVO: src/main/java/com/credito/gestion/domain/port/SolicitudCreditoRepository.java ===
package com.credito.gestion.domain.port;

import com.credito.gestion.domain.model.SolicitudCredito;
import java.util.Optional;
import java.util.UUID;

public interface SolicitudCreditoRepository {
    SolicitudCredito save(SolicitudCredito solicitud);
    
    Optional<SolicitudCredito> findByNumeroOperacion(UUID numeroOperacion);
    
    Optional<SolicitudCredito> findByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento);
}

// === ARCHIVO: src/main/java/com/credito/gestion/domain/port/AntifraudeService.java ===
package com.credito.gestion.domain.port;

import com.credito.gestion.domain.model.SolicitudCredito;
import java.util.concurrent.CompletableFuture;

public interface AntifraudeService {
    CompletableFuture<String> evaluarRiesgo(SolicitudCredito solicitud);
}

// === ARCHIVO: src/main/java/com/credito/gestion/domain/port/BuroRiesgosService.java ===
package com.credito.gestion.domain.port;

import com.credito.gestion.domain.model.SolicitudCredito;
import java.util.concurrent.CompletableFuture;

/**
 * Puerto de salida para interactuar con el buró de riesgos.
 * Define la abstracción que el dominio usa para consultar información crediticia.
 * La implementación concreta reside en la capa de infraestructura.
 */
public interface BuroRiesgosService {

    /**
     * Consulta el buró de riesgos para obtener el historial crediticio del cliente.
     * Retorna el resultado de la consulta: APROBADO, RECHAZADO o REVISION.
     *
     * @param solicitud la solicitud de crédito con los datos del cliente
     * @return CompletableFuture con el resultado de la consulta al buró
     */
    CompletableFuture<String> consultarBuro(SolicitudCredito solicitud);

    /**
     * Verifica si el cliente tiene alertas activas en el buró de riesgos.
     *
     * @param tipoDocumento tipo de documento del cliente
     * @param numeroDocumento número de documento del cliente
     * @return CompletableFuture con true si hay alertas, false en caso contrario
     */
    CompletableFuture<Boolean> verificarAlertas(String tipoDocumento, String numeroDocumento);

    /**
     * Obtiene el score crediticio del cliente del buró.
     *
     * @param tipoDocumento tipo de documento del cliente
     * @param numeroDocumento número de documento del cliente
     * @return CompletableFuture con el score crediticio (0-1000)
     */
    CompletableFuture<Integer> obtenerScore(String tipoDocumento, String numeroDocumento);
}

// === ARCHIVO: src/main/java/com/credito/gestion/domain/port/CoreBancarioService.java ===
package com.credito.gestion.domain.port;

import com.credito.gestion.domain.model.SolicitudCredito;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Puerto de salida para interactuar con el core bancario.
 * Define la abstracción que el dominio usa para registrar y confirmar
 * las operaciones de crédito en el sistema central de la institución.
 */
public interface CoreBancarioService {

    /**
     * Registra la operación de crédito en el core bancario.
     *
     * @param numeroOperacion identificador único de la operación
     * @param monto monto aprobado del crédito
     * @param solicitud datos completos de la solicitud
     * @return CompletableFuture con el resultado del registro
     */
    CompletableFuture<String> registrarOperacion(UUID numeroOperacion, BigDecimal monto, SolicitudCredito solicitud);

    /**
     * Confirma la operación de crédito en el core bancario.
     * Se invoca después de que todos los servicios de validación aprobaran.
     *
     * @param numeroOperacion identificador único de la operación
     * @return CompletableFuture con la confirmación del core
     */
    CompletableFuture<String> confirmarOperacion(UUID numeroOperacion);

    /**
     * Consulta el estado de una operación previamente registrada.
     *
     * @param numeroOperacion identificador único de la operación
     * @return CompletableFuture con el estado actual de la operación
     */
    CompletableFuture<String> consultarEstado(UUID numeroOperacion);

    /**
     * Cancela una operación de crédito en el core bancario.
     * Se invoca cuando falla alguna validación posterior al registro.
     *
     * @param numeroOperacion identificador único de la operación
     * @param motivo motivo de la cancelación
     * @return CompletableFuture con el resultado de la cancelación
     */
    CompletableFuture<String> cancelarOperacion(UUID numeroOperacion, String motivo);
}

// === ARCHIVO: src/main/java/com/credito/gestion/application/usecase/ProcesarSolicitudCreditoUseCase.java ===
package com.credito.gestion.application.usecase;


import com.credito.gestion.infrastructure.exception.SolicitudDuplicadaException;
import com.credito.gestion.domain.model.SolicitudCredito;
import com.credito.gestion.domain.port.AntifraudeService;
import com.credito.gestion.domain.port.BuroRiesgosService;
import com.credito.gestion.domain.port.CoreBancarioService;
import com.credito.gestion.domain.port.SolicitudCreditoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Caso de uso principal para el procesamiento de solicitudes de crédito.
 * Orchestrates las interacciones con los servicios externos: antifraude,
 * buró de riesgos y core bancario.
 * 
 * Flujo principal:
 * 1. Verificar idempotencia mediante número de operación
 * 2. Evaluar riesgo de fraude
 * 3. Consultar buró de riesgos
 * 4. Registrar en core bancario
 * 5. Confirmar operación si todas las validaciones pasan
 */
@Service
public class ProcesarSolicitudCreditoUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ProcesarSolicitudCreditoUseCase.class);
    private static final String RESULTADO_APROBADO = "APROBADO";
    private static final String RESULTADO_RECHAZADO = "RECHAZADO";
    private static final String RESULTADO_REVISION = "REVISION";

    private final SolicitudCreditoRepository solicitudRepository;
    private final AntifraudeService antifraudeService;
    private final BuroRiesgosService buroRiesgosService;
    private final CoreBancarioService coreBancarioService;

    public ProcesarSolicitudCreditoUseCase(
            SolicitudCreditoRepository solicitudRepository,
            AntifraudeService antifraudeService,
            BuroRiesgosService buroRiesgosService,
            CoreBancarioService coreBancarioService) {
        this.solicitudRepository = solicitudRepository;
        this.antifraudeService = antifraudeService;
        this.buroRiesgosService = buroRiesgosService;
        this.coreBancarioService = coreBancarioService;
    }

    /**
     * Procesa una solicitud de crédito ejecutando el flujo completo de validación.
     *
     * @param solicitud la solicitud de crédito a procesar
     * @return la solicitud procesada con los resultados de cada validación
     */
    public SolicitudCredito ejecutar(SolicitudCredito solicitud) {
        logger.info("Iniciando procesamiento de solicitud: numeroOperacion={}", 
                solicitud.numeroOperacion());

        UUID numeroOperacion = solicitud.numeroOperacion();

        verificarIdempotencia(numeroOperacion, solicitud.tipoDocumento(), solicitud.numeroDocumento());

        SolicitudCredito solicitudConAntifraude = evaluarAntifraude(solicitud);

        if (RESULTADO_RECHAZADO.equals(solicitudConAntifraude.resultadoAntifraude())) {
            logger.warn("Solicitud rechazada por antifraude: numeroOperacion={}", numeroOperacion);
            return guardarSolicitud(solicitudConAntifraude);
        }

        SolicitudCredito solicitudConBuro = consultarBuroRiesgos(solicitudConAntifraude);

        if (RESULTADO_RECHAZADO.equals(solicitudConBuro.resultadoBuro())) {
            logger.warn("Solicitud rechazada por buró de riesgos: numeroOperacion={}", numeroOperacion);
            return guardarSolicitud(solicitudConBuro);
        }

        SolicitudCredito solicitudConCore = registrarEnCore(solicitudConBuro);

        if (RESULTADO_RECHAZADO.equals(solicitudConCore.resultadoCore())) {
            logger.error("Error al registrar en core bancario: numeroOperacion={}", numeroOperacion);
            return guardarSolicitud(solicitudConCore);
        }

        SolicitudCredito solicitudFinal = confirmarEnCore(solicitudConCore);
        logger.info("Solicitud procesada exitosamente: numeroOperacion={}, resultado={}", 
                numeroOperacion, solicitudFinal.resultadoCore());

        return guardarSolicitud(solicitudFinal);
    }

    private void verificarIdempotencia(UUID numeroOperacion, String tipoDocumento, String numeroDocumento) {
        solicitudRepository.findByNumeroOperacion(numeroOperacion)
            .ifPresent(s -> {
                logger.info("Solicitud duplicada detectada por numeroOperacion: {}", numeroOperacion);
                throw new com.credito.gestion.infrastructure.exception.SolicitudDuplicadaException(
                    "Ya existe una solicitud con el número de operación: " + numeroOperacion);
            });

        solicitudRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento)
            .ifPresent(s -> {
                logger.info("Solicitud duplicada detectada por documento: tipo={}, numero={}", 
                        tipoDocumento, numeroDocumento);
                throw new com.credito.gestion.infrastructure.exception.SolicitudDuplicadaException(
                    "Ya existe una solicitud para el documento: " + tipoDocumento + " " + numeroDocumento);
            });
    }

    private SolicitudCredito evaluarAntifraude(SolicitudCredito solicitud) {
        try {
            logger.info("Evaluando antifraude para solicitud: {}", solicitud.numeroOperacion());
            String resultado = antifraudeService.evaluarRiesgo(solicitud).get();
            logger.info("Resultado antifraude: numeroOperacion={}, resultado={}", 
                    solicitud.numeroOperacion(), resultado);
            return solicitud.conResultadoAntifraude(resultado);
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error en servicio antifraude: numeroOperacion={}, error={}", 
                    solicitud.numeroOperacion(), e.getMessage());
            return solicitud.conResultadoAntifraude(RESULTADO_REVISION)
                    .conObservaciones("Error en evaluación antifraude: " + e.getMessage());
        }
    }

    private SolicitudCredito consultarBuroRiesgos(SolicitudCredito solicitud) {
        try {
            logger.info("Consultando buró de riesgos para solicitud: {}", solicitud.numeroOperacion());
            CompletableFuture<String> consultaBuro = buroRiesgosService.consultarBuro(solicitud);
            CompletableFuture<Integer> scoreFuture = buroRiesgosService.obtenerScore(
                    solicitud.tipoDocumento(), solicitud.numeroDocumento());

            String resultadoBuro = consultaBuro.get();
            Integer score = scoreFuture.get();

            logger.info("Resultado buró: numeroOperacion={}, resultado={}, score={}", 
                    solicitud.numeroOperacion(), resultadoBuro, score);

            String observaciones = String.format("Score crediticio: %d", score);
            return solicitud.conResultadoBuro(resultadoBuro).conObservaciones(observaciones);
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error en servicio de buró de riesgos: numeroOperacion={}, error={}", 
                    solicitud.numeroOperacion(), e.getMessage());
            return solicitud.conResultadoBuro(RESULTADO_REVISION)
                    .conObservaciones("Timeout al consultar buró de riesgos: " + e.getMessage());
        }
    }

    private SolicitudCredito registrarEnCore(SolicitudCredito solicitud) {
        try {
            logger.info("Registrando en core bancario: numeroOperacion={}, monto={}", 
                    solicitud.numeroOperacion(), solicitud.monto());
            String resultado = coreBancarioService.registrarOperacion(
                    solicitud.numeroOperacion(), 
                    solicitud.monto(), 
                    solicitud).get();
            logger.info("Resultado registro core: numeroOperacion={}, resultado={}", 
                    solicitud.numeroOperacion(), resultado);
            return solicitud.conResultadoCore(resultado);
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error al registrar en core bancario: numeroOperacion={}, error={}", 
                    solicitud.numeroOperacion(), e.getMessage());
            return solicitud.conResultadoCore(RESULTADO_RECHAZADO)
                    .conObservaciones("Error al registrar en core: " + e.getMessage());
        }
    }

    private SolicitudCredito confirmarEnCore(SolicitudCredito solicitud) {
        try {
            logger.info("Confirmando en core bancario: numeroOperacion={}", solicitud.numeroOperacion());
            String resultado = coreBancarioService.confirmarOperacion(solicitud.numeroOperacion()).get();
            logger.info("Resultado confirmación core: numeroOperacion={}, resultado={}", 
                    solicitud.numeroOperacion(), resultado);
            return solicitud.conResultadoCore(resultado);
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error al confirmar en core bancario: numeroOperacion={}, error={}", 
                    solicitud.numeroOperacion(), e.getMessage());
            try {
                coreBancarioService.cancelarOperacion(
                        solicitud.numeroOperacion(), 
                        "Error en confirmación: " + e.getMessage()).get();
            } catch (Exception cancelError) {
                logger.error("Error al intentar cancelar operación: numeroOperacion={}, error={}",
                        solicitud.numeroOperacion(), cancelError.getMessage());
            }
            return solicitud.conResultadoCore(RESULTADO_RECHAZADO)
                    .conObservaciones("Error al confirmar en core: " + e.getMessage());
        }
    }

    private SolicitudCredito guardarSolicitud(SolicitudCredito solicitud) {
        logger.info("Guardando solicitud: numeroOperacion={}", solicitud.numeroOperacion());
        return solicitudRepository.save(solicitud);
    }
}

// === ARCHIVO: src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoJpaAdapter.java ===
package com.credito.gestion.infrastructure.adapter;

import com.credito.gestion.domain.model.SolicitudCredito;
import com.credito.gestion.domain.port.SolicitudCreditoRepository;
import com.credito.gestion.infrastructure.entity.SolicitudCreditoEntity;
import com.credito.gestion.infrastructure.repository.SolicitudCreditoJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class SolicitudCreditoJpaAdapter implements SolicitudCreditoRepository {

    private final SolicitudCreditoJpaRepository jpaRepository;
    private final SolicitudCreditoMapper mapper;

    public SolicitudCreditoJpaAdapter(SolicitudCreditoJpaRepository jpaRepository,
                                       SolicitudCreditoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public SolicitudCredito save(SolicitudCredito solicitud) {
        SolicitudCreditoEntity entidad = mapper.toEntity(solicitud);
        SolicitudCreditoEntity guardada = jpaRepository.save(entidad);
        return mapper.toDomain(guardada);
    }

    @Override
    public Optional<SolicitudCredito> findByNumeroOperacion(UUID numeroOperacion) {
        return jpaRepository.findByNumeroOperacion(numeroOperacion)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<SolicitudCredito> findByTipoDocumentoAndNumeroDocumento(
            String tipoDocumento, String numeroDocumento) {
        return jpaRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento)
                .map(mapper::toDomain);
    }
}

// === ARCHIVO: src/main/java/com/credito/gestion/infrastructure/adapter/SolicitudCreditoMapper.java ===
package com.credito.gestion.infrastructure.adapter;

import com.credito.gestion.domain.model.SolicitudCredito;
import com.credito.gestion.infrastructure.entity.SolicitudCreditoEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class SolicitudCreditoMapper {

    public SolicitudCredito toDomain(SolicitudCreditoEntity entity) {
        return new SolicitudCredito(
                entity.getNumeroOperacion(),
                entity.getTipoDocumento(),
                entity.getNumeroDocumento(),
                entity.getMonto(),
                entity.getPlazo(),
                entity.getDestino(),
                entity.getResultadoAntifraude(),
                entity.getResultadoBuro(),
                entity.getResultadoCore(),
                entity.getObservaciones(),
                entity.getEstado(),
                entity.getFechaCreacion(),
                entity.getFechaActualizacion()
        );
    }

    public SolicitudCreditoEntity toDomain(SolicitudCredito solicitud) {
        SolicitudCreditoEntity entity = new SolicitudCreditoEntity();
        entity.setNumeroOperacion(solicitud.numeroOperacion());
        entity.setTipoDocumento(solicitud.tipoDocumento());
        entity.setNumeroDocumento(solicitud.numeroDocumento());
        entity.setMonto(solicitud.monto());
        entity.setPlazo(solicitud.plazo());
        entity.setDestino(solicitud.destino());
        entity.setResultadoAntifraude(solicitud.resultadoAntifraude());
        entity.setResultadoBuro(solicitud.resultadoBuro());
        entity.setResultadoCore(solicitud.resultadoCore());
        entity.setObservaciones(solicitud.observaciones());
        entity.setEstado(solicitud.estado());
        if (solicitud.fechaCreacion() != null) {
            entity.setFechaCreacion(solicitud.fechaCreacion());
        } else {
            entity.setFechaCreacion(LocalDateTime.now());
        }
        entity.setFechaActualizacion(LocalDateTime.now());
        return entity;
    }

    public SolicitudCreditoEntity toEntity(SolicitudCredito solicitud) {
        SolicitudCreditoEntity entity = new SolicitudCreditoEntity();
        if (solicitud.numeroOperacion() != null) {
            entity.setNumeroOperacion(solicitud.numeroOperacion());
        } else {
            entity.setNumeroOperacion(UUID.randomUUID());
        }
        entity.setTipoDocumento(solicitud.tipoDocumento());
        entity.setNumeroDocumento(solicitud.numeroDocumento());
        entity.setMonto(solicitud.monto());
        entity.setPlazo(solicitud.plazo());
        entity.setDestino(solicitud.destino());
        entity.setResultadoAntifraude(solicitud.resultadoAntifraude());
        entity.setResultadoBuro(solicitud.resultadoBuro());
        entity.setResultadoCore(solicitud.resultadoCore());
        entity.setObservaciones(solicitud.observaciones());
        entity.setEstado(solicitud.estado() != null ? solicitud.estado() : "PENDIENTE");
        LocalDateTime ahora = LocalDateTime.now();
        entity.setFechaCreacion(ahora);
        entity.setFechaActualizacion(ahora);
        return entity;
    }
}

// === ARCHIVO: src/main/java/com/credito/gestion/infrastructure/entity/SolicitudCreditoEntity.java ===
package com.credito.gestion.infrastructure.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "solicitudes_credito", indexes = {
    @Index(name = "idx_numero_operacion", columnList = "numero_operacion"),
    @Index(name = "idx_documento", columnList = "tipo_documento, numero_documento")
})
public class SolicitudCreditoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "numero_operacion", nullable = false, unique = true)
    private UUID numeroOperacion;

    @Column(name = "tipo_documento", nullable = false, length = 10)
    private String tipoDocumento;

    @Column(name = "numero_documento", nullable = false, length = 30)
    private String numeroDocumento;

    @Column(name = "monto", nullable = false, precision = 19, scale = 4)
    private BigDecimal monto;

    @Column(name = "plazo", nullable = false)
    private Integer plazo;

    @Column(name = "destino", nullable = false, length = 100)
    private String destino;

    @Column(name = "resultado_antifraude", length = 50)
    private String resultadoAntifraude;

    @Column(name = "resultado_buro", length = 50)
    private String resultadoBuro;

    @Column(name = "resultado_core", length = 50)
    private String resultadoCore;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    @Column(name = "estado", nullable = false, length = 30)
    private String estado;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getNumeroOperacion() {
        return numeroOperacion;
    }

    public void setNumeroOperacion(UUID numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getResultadoAntifraude() {
        return resultadoAntifraude;
    }

    public void setResultadoAntifraude(String resultadoAntifraude) {
        this.resultadoAntifraude = resultadoAntifraude;
    }

    public String getResultadoBuro() {
        return resultadoBuro;
    }

    public void setResultadoBuro(String resultadoBuro) {
        this.resultadoBuro = resultadoBuro;
    }

    public String getResultadoCore() {
        return resultadoCore;
    }

    public void setResultadoCore(String resultadoCore) {
        this.resultadoCore = resultadoCore;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}

// === ARCHIVO: src/main/java/com/credito/gestion/infrastructure/repository/SolicitudCreditoJpaRepository.java ===
package com.credito.gestion.infrastructure.repository;

import com.credito.gestion.infrastructure.entity.SolicitudCreditoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SolicitudCreditoJpaRepository extends JpaRepository<SolicitudCreditoEntity, UUID> {

    Optional<SolicitudCreditoEntity> findByNumeroOperacion(UUID numeroOperacion);

    Optional<SolicitudCreditoEntity> findByTipoDocumentoAndNumeroDocumento(
            String tipoDocumento, String numeroDocumento);
}

// === ARCHIVO: src/main/java/com/credito/gestion/infrastructure/adapter/AntifraudeRestAdapter.java ===
package com.credito.gestion.infrastructure.adapter;

import com.credito.gestion.domain.model.SolicitudCredito;
import com.credito.gestion.domain.port.AntifraudeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class AntifraudeRestAdapter implements AntifraudeService {

    private static final Logger log = LoggerFactory.getLogger(AntifraudeRestAdapter.class);
    private static final String ANTIFRAUDE_URL = "http://localhost:8081/api/antifraude/evaluar";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public AntifraudeRestAdapter(@Qualifier("restTemplate") RestTemplate restTemplate,
                                  ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    @Async("taskExecutor")
    @CircuitBreaker(name = "antifraude", fallbackMethod = "evaluarRiesgoFallback")
    public CompletableFuture<String> evaluarRiesgo(SolicitudCredito solicitud) {
        try {
            log.info("Evaluando riesgo antifraude para operacion: {}", solicitud.numeroOperacion());
            
            Map<String, Object> payload = new HashMap<>();
            payload.put("numeroOperacion", solicitud.numeroOperacion().toString());
            payload.put("tipoDocumento", solicitud.tipoDocumento());
            payload.put("numeroDocumento", solicitud.numeroDocumento());
            payload.put("monto", solicitud.monto().toString());
            payload.put("plazo", solicitud.plazo());
            payload.put("destino", solicitud.destino());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

            Map<String, Object> response = restTemplate.postForObject(
                    ANTIFRAUDE_URL, request, Map.class);

            String resultado = (String) response.get("resultado");
            log.info("Resultado antifraude para operacion {}: {}", solicitud.numeroOperacion(), resultado);
            
            return CompletableFuture.completedFuture(resultado != null ? resultado : "APROBADO");
            
        } catch (Exception e) {
            log.error("Error en evaluacion antifraude para operacion {}: {}", 
                    solicitud.numeroOperacion(), e.getMessage());
            return CompletableFuture.completedFuture("ERROR");
        }
    }

    private CompletableFuture<String> evaluarRiesgoFallback(SolicitudCredito solicitud, Throwable t) {
        log.warn("Fallback antifraude activado para operacion {}: {}", 
                solicitud.numeroOperacion(), t.getMessage());
        return CompletableFuture.completedFuture("PENDIENTE_REVISION");
    }
}

// === ARCHIVO: src/main/java/com/credito/gestion/infrastructure/adapter/BuroRiesgosRestAdapter.java ===
package com.credito.gestion.infrastructure.adapter;

import com.credito.gestion.domain.model.SolicitudCredito;
import com.credito.gestion.domain.port.BuroRiesgosService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class BuroRiesgosRestAdapter implements BuroRiesgosService {

    private static final Logger log = LoggerFactory.getLogger(BuroRiesgosRestAdapter.class);
    private static final String BURO_URL = "http://localhost:8082/api/buro/consultar";
    private static final int TIMEOUT_SECONDS = 2;

    private final RestTemplate restTemplate;

    public BuroRiesgosRestAdapter(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Retry(name = "buro")
    @CircuitBreaker(name = "buro", fallbackMethod = "consultarBuroFallback")
    public CompletableFuture<String> consultarBuro(SolicitudCredito solicitud) {
        try {
            log.info("Consultando buró de riesgos para operacion: {}", solicitud.numeroOperacion());
            
            Map<String, Object> payload = new HashMap<>();
            payload.put("numeroOperacion", solicitud.numeroOperacion().toString());
            payload.put("tipoDocumento", solicitud.tipoDocumento());
            payload.put("numeroDocumento", solicitud.numeroDocumento());
            payload.put("monto", solicitud.monto().toString());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

            Map<String, Object> response = restTemplate.postForObject(
                    BURO_URL, request, Map.class);

            String resultado = (String) response.get("resultado");
            String calificacion = (String) response.get("calificacion");
            
            log.info("Resultado buró para operacion {}: resultado={}, calificacion={}", 
                    solicitud.numeroOperacion(), resultado, calificacion);
            
            String resultadoFinal = resultado != null ? resultado : "SIN_INFORMACION";
            return CompletableFuture.completedFuture(resultadoFinal);
            
        } catch (Exception e) {
            log.error("Error en consulta buró para operacion {}: {}", 
                    solicitud.numeroOperacion(), e.getMessage());
            return CompletableFuture.completedFuture("ERROR_CONSULTA");
        }
    }

    private CompletableFuture<String> consultarBuroFallback(SolicitudCredito solicitud, Throwable t) {
        log.warn("Fallback buró activado para operacion {}: {}", 
                solicitud.numeroOperacion(), t.getMessage());
        return CompletableFuture.completedFuture("SIN_RESPUESTA");
    }
}

// === ARCHIVO: src/main/java/com/credito/gestion/infrastructure/adapter/CoreBancarioRestAdapter.java ===
package com.credito.gestion.infrastructure.adapter;

import com.credito.gestion.domain.model.SolicitudCredito;
import com.credito.gestion.domain.port.CoreBancarioService;
import com.credito.gestion.infrastructure.exception.CoreBancarioException;
import com.credito.gestion.infrastructure.exception.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class CoreBancarioRestAdapter implements CoreBancarioService {

    private static final Logger log = LoggerFactory.getLogger(CoreBancarioRestAdapter.class);
    private static final String CORE_BANCARIO_URL = "http://localhost:8081/api/core";

    private final RestTemplate restTemplate;
    private final io.github.resilience4j.circuitbreaker.CircuitBreaker circuitBreaker;

    public CoreBancarioRestAdapter(
            @Qualifier("coreRestTemplate") RestTemplate restTemplate,
            @Qualifier("coreCircuitBreaker") io.github.resilience4j.circuitbreaker.CircuitBreaker circuitBreaker) {
        this.restTemplate = restTemplate;
        this.circuitBreaker = circuitBreaker;
    }

    @Override
    @CircuitBreaker(name = "coreBancario", fallbackMethod = "registrarEnCoreFallback")
    public CompletableFuture<String> registrarSolicitud(SolicitudCredito solicitud) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("X-Idempotency-Key", solicitud.numeroOperacion().toString());

                Map<String, Object> payload = new HashMap<>();
                payload.put("numeroOperacion", solicitud.numeroOperacion().toString());
                payload.put("tipoDocumento", solicitud.tipoDocumento());
                payload.put("numeroDocumento", solicitud.numeroDocumento());
                payload.put("monto", solicitud.monto().toString());
                payload.put("plazo", solicitud.plazo().toString());
                payload.put("tipoCredito", solicitud.tipoCredito());
                payload.put("tasaInteres", solicitud.tasaInteres().toString());

                HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

                ResponseEntity<String> response = restTemplate.exchange(
                        CORE_BANCARIO_URL + "/solicitudes",
                        HttpMethod.POST,
                        request,
                        String.class
                );

                if (response.getStatusCode().is2xxSuccessful()) {
                    log.info("Solicitud {} registrada exitosamente en core bancario", solicitud.numeroOperacion());
                    return response.getBody() != null ? response.getBody() : "APROBADA";
                } else {
                    throw new CoreBancarioException("Respuesta no exitosa del core bancario: " + response.getStatusCode());
                }

            } catch (HttpServerErrorException e) {
                log.error("Error 5xx del core bancario para solicitud {}: {}", solicitud.numeroOperacion(), e.getMessage());
                throw new CoreBancarioException("Error del core bancario: " + e.getStatusCode());
            } catch (HttpClientErrorException e) {
                log.error("Error 4xx del core bancario para solicitud {}: {}", solicitud.numeroOperacion(), e.getMessage());
                throw new CoreBancarioException("Error de cliente del core bancario: " + e.getStatusCode());
            } catch (org.springframework.web.client.ResourceAccessException e) {
                log.error("Timeout conectando con core bancario para solicitud {}", solicitud.numeroOperacion());
                throw new TimeoutException("Timeout al conectar con el core bancario");
            }
        });
    }

    private CompletableFuture<String> registrarEnCoreFallback(SolicitudCredito solicitud, Throwable t) {
        log.warn("Fallback ejecutado para solicitud {} debido a: {}", solicitud.numeroOperacion(), t.getMessage());
        if (t instanceof TimeoutException) {
            throw (TimeoutException) t;
        }
        if (t instanceof CoreBancarioException) {
            throw (CoreBancarioException) t;
        }
        throw new CoreBancarioException("Servicio de core bancario no disponible temporalmente");
    }
}

// === ARCHIVO: src/main/java/com/credito/gestion/infrastructure/config/RestTemplateConfig.java ===
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

// === ARCHIVO: src/main/java/com/credito/gestion/infrastructure/controller/SolicitudCreditoController.java ===
package com.credito.gestion.infrastructure.controller;

import com.credito.gestion.domain.model.SolicitudCredito;
import com.credito.gestion.domain.port.SolicitudCreditoRepository;
import com.credito.gestion.application.usecase.ProcesarSolicitudCreditoUseCase;
import com.credito.gestion.infrastructure.exception.SolicitudDuplicadaException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/solicitudes")
@Tag(name = "Solicitudes de Crédito", description = "Endpoints para la gestión de solicitudes de crédito")
public class SolicitudCreditoController {

    private static final Logger log = LoggerFactory.getLogger(SolicitudCreditoController.class);

    private final SolicitudCreditoRepository repository;
    private final ProcesarSolicitudCreditoUseCase procesarUseCase;

    public SolicitudCreditoController(
            SolicitudCreditoRepository repository,
            ProcesarSolicitudCreditoUseCase procesarUseCase) {
        this.repository = repository;
        this.procesarUseCase = procesarUseCase;
    }

    @PostMapping
    @Operation(summary = "Crear solicitud de crédito", description = "Registra una nueva solicitud de crédito en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Solicitud creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "409", description = "Solicitud duplicada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<?> crearSolicitud(
            @Parameter(description = "Datos de la solicitud de crédito") @Valid @RequestBody SolicitudCreditoRequest request) {

        log.info("Recibida solicitud de crédito para documento: {}", request.numeroDocumento());

        if (request.numeroOperacion() == null || request.numeroOperacion().isBlank()) {
            request = new SolicitudCreditoRequest(
                    UUID.randomUUID().toString(),
                    request.tipoDocumento(),
                    request.numeroDocumento(),
                    request.monto(),
                    request.plazo(),
                    request.tipoCredito(),
                    request.tasaInteres()
            );
        }

        UUID numeroOperacion = UUID.fromString(request.numeroOperacion());

        var existente = repository.findByNumeroOperacion(numeroOperacion);
        if (existente.isPresent()) {
            log.warn("Solicitud duplicada detectada para numeroOperacion: {}", numeroOperacion);
            throw new SolicitudDuplicadaException("Ya existe una solicitud con el número de operación: " + numeroOperacion);
        }

        SolicitudCredito solicitud = new SolicitudCredito(
                numeroOperacion,
                request.tipoDocumento(),
                request.numeroDocumento(),
                request.monto(),
                request.plazo(),
                request.tipoCredito(),
                request.tasaInteres(),
                "PENDIENTE",
                null,
                null,
                null,
                null,
                java.time.LocalDateTime.now()
        );

        SolicitudCredito guardada = repository.save(solicitud);
        log.info("Solicitud {} guardada exitosamente", guardada.numeroOperacion());

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "numeroOperacion", guardada.numeroOperacion().toString(),
                "estado", guardada.estado(),
                "mensaje", "Solicitud de crédito creada exitosamente"
        ));
    }

    @GetMapping("/{numeroOperacion}")
    @Operation(summary = "Consultar solicitud", description = "Obtiene los detalles de una solicitud por número de operación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    public ResponseEntity<?> consultarSolicitud(
            @Parameter(description = "Número de operación de la solicitud") @PathVariable String numeroOperacion) {

        UUID operacion = UUID.fromString(numeroOperacion);
        var solicitud = repository.findByNumeroOperacion(operacion);

        if (solicitud.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Solicitud no encontrada"));
        }

        SolicitudCredito s = solicitud.get();
        return ResponseEntity.ok(Map.of(
                "numeroOperacion", s.numeroOperacion().toString(),
                "tipoDocumento", s.tipoDocumento(),
                "numeroDocumento", s.numeroDocumento(),
                "monto", s.monto().toString(),
                "plazo", s.plazo().toString(),
                "tipoCredito", s.tipoCredito(),
                "tasaInteres", s.tasaInteres().toString(),
                "estado", s.estado(),
                "resultadoAntifraude", s.resultadoAntifraude() != null ? s.resultadoAntifraude() : "N/A",
                "resultadoBuro", s.resultadoBuro() != null ? s.resultadoBuro() : "N/A",
                "resultadoCore", s.resultadoCore() != null ? s.resultadoCore() : "N/A",
                "observaciones", s.observaciones() != null ? s.observaciones() : ""
        ));
    }

    @PostMapping("/{numeroOperacion}/procesar")
    @Operation(summary = "Procesar solicitud", description = "Inicia el procesamiento de una solicitud de crédito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Procesamiento iniciado"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    public ResponseEntity<?> procesarSolicitud(
            @Parameter(description = "Número de operación de la solicitud") @PathVariable String numeroOperacion) {

        UUID operacion = UUID.fromString(numeroOperacion);
        var solicitud = repository.findByNumeroOperacion(operacion);

        if (solicitud.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Solicitud no encontrada"));
        }

        try {
            SolicitudCredito result = procesarUseCase.ejecutar(solicitud.get());
            return ResponseEntity.ok(Map.of(
                    "numeroOperacion", result.numeroOperacion().toString(),
                    "estado", result.estado(),
                    "resultadoAntifraude", result.resultadoAntifraude() != null ? result.resultadoAntifraude() : "N/A",
                    "resultadoBuro", result.resultadoBuro() != null ? result.resultadoBuro() : "N/A",
                    "resultadoCore", result.resultadoCore() != null ? result.resultadoCore() : "N/A"
            ));
        } catch (Exception e) {
            log.error("Error al procesar solicitud {}: {}", numeroOperacion, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al procesar solicitud: " + e.getMessage()));
        }
    }

    public record SolicitudCreditoRequest(
            String numeroOperacion,
            String tipoDocumento,
            String numeroDocumento,
            String monto,
            Integer plazo,
            String tipoCredito,
            String tasaInteres
    ) {}
}

// === ARCHIVO: src/main/resources/features/solicitud_credito.feature ===
Feature: Gestión de Solicitudes de Crédito

  Como originador de créditos
  Quiero procesar solicitudes de crédito de manera idempotente
  Para garantizar la consistencia y evitar duplicados en el sistema

  Background:
    Given el sistema está disponible para procesar solicitudes
    And el número de operación no existe en el sistema

  @smoke
  Scenario: Procesamiento exitoso de solicitud de crédito
    When se recibe una solicitud de crédito con los siguientes datos:
      | tipoDocumento | numeroDocumento | nombreCompleto | montoSolicitado | plazoMeses |
      | CC            | 12345678        | Juan Pérez     | 50000000        | 36         |
    Then el sistema debe crear la solicitud con estado "EN_PROCESO"
    And el sistema debe invocar al servicio de antifraude
    And el sistema debe invocar al servicio de buró de riesgos
    And el sistema debe invocar al servicio del core bancario
    And el sistema debe retornar el resultado final con el estado de aprobación

  @smoke
  Scenario: Solicitud duplicada - mismo número de operación
    Given existe una solicitud con número de operación "OP-2024-00001"
    When se recibe una nueva solicitud con el mismo número de operación "OP-2024-00001"
    Then el sistema debe retornar la solicitud existente sin crear una nueva
    And el sistema debe responder con código de estado 200

  @smoke
  Scenario: Solicitud duplicada - mismo tipo y número de documento
    Given existe una solicitud con tipo documento "CC" y número "12345678" en estado "APROBADA"
    When se recibe una nueva solicitud con tipo documento "CC" y número "12345678"
    Then el sistema debe rechazar la solicitud con mensaje de error
    And el sistema debe responder con código de estado 409

  @integration
  Scenario: Timeout en servicio de buró de riesgos
    Given el servicio de buró de riesgos no responde dentro del tiempo esperado
    When se procesa una solicitud de crédito
    Then el sistema debe aplicar la estrategia de fallback configurada
    And el sistema debe continuar el flujo sin bloquearse
    And debe registrar el evento de timeout para auditoría

  @integration
  Scenario: Error 5xx del core bancario
    Given el servicio del core bancario retorna un error 500
    When se procesa una solicitud de crédito
    Then el sistema debe manejar el error gracefully
    And el sistema debe reintentar según la política configurada
    And debe registrar el evento de error para auditoría

  @integration
  Scenario: Evaluación de antifraude rechaza solicitud
    Given el servicio de antifraude retorna "RECHAZADO" por riesgo alto
    When se procesa una solicitud de crédito
    Then el sistema debe rechazar la solicitud automáticamente
    And el estado final debe ser "RECHAZADA_POR_ANTIFRAUDE"
    And no debe invocar a los servicios de buró ni core

  @performance
  Scenario: Procesamiento de alto volumen
    Given el sistema recibe 100 solicitudes simultáneas
    When se procesan todas las solicitudes en paralelo
    Then el sistema debe completar todas las solicitudes
    And el tiempo promedio por solicitud debe ser menor a 2 segundos
    And no debe haber pérdida de solicitudes

  @idempotency
  Scenario: Reenvío de solicitud con mismo número de operación
    Given existe una solicitud con número de operación "OP-2024-00002" en estado "APROBADA"
    When se recibe nuevamente la solicitud con número de operación "OP-2024-00002"
    Then el sistema debe retornar la solicitud existente
    And no debe ejecutar las validaciones nuevamente
    And debe responder con código de estado 200

// === ARCHIVO: src/main/java/com/credito/gestion/infrastructure/exception/GlobalExceptionHandler.java ===
package com.credito.gestion.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SolicitudDuplicadaException.class)
    public ResponseEntity<ErrorResponse> handleSolicitudDuplicada(
            SolicitudDuplicadaException ex, WebRequest request) {
        logger.warn("Solicitud duplicada detectada: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Conflicto - Solicitud Duplicada")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .numeroOperacion(ex.getNumeroOperacion())
                .build();
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ErrorResponse> handleTimeout(
            TimeoutException ex, WebRequest request) {
        logger.error("Timeout en servicio externo: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.GATEWAY_TIMEOUT.value())
                .error("Gateway Timeout")
                .message("El servicio externo no respondió a tiempo: " + ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .servicio(ex.getServicio())
                .build();
        
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(errorResponse);
    }

    @ExceptionHandler(CoreBancarioException.class)
    public ResponseEntity<ErrorResponse> handleCoreBancarioException(
            CoreBancarioException ex, WebRequest request) {
        logger.error("Error del core bancario: {}", ex.getMessage());
        
        HttpStatus status = ex.getStatusCode() != null ? 
                HttpStatus.valueOf(ex.getStatusCode()) : HttpStatus.BAD_GATEWAY;
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error("Error en Core Bancario")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .servicio("core-bancario")
                .codigoError(ex.getCodigoError())
                .build();
        
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        logger.warn("Error de validación: {}", ex.getMessage());
        
        Map<String, String> errores = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null ? 
                                error.getDefaultMessage() : "Valor inválido",
                        (existing, replacement) -> existing
                ));
        
        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Error de Validación")
                .message("Los datos proporcionados no son válidos")
                .path(request.getDescription(false).replace("uri=", ""))
                .errores(errores)
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {
        logger.warn("Argumento ilegal: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Solicitud Inválida")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        logger.error("Error interno del servidor: ", ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Error Interno del Servidor")
                .message("Ha ocurrido un error inesperado. Por favor, contacte al administrador.")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    public static class ErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;
        private String numeroOperacion;
        private String servicio;
        private String codigoError;

        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }

        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public String getNumeroOperacion() { return numeroOperacion; }
        public void setNumeroOperacion(String numeroOperacion) { this.numeroOperacion = numeroOperacion; }
        public String getServicio() { return servicio; }
        public void setServicio(String servicio) { this.servicio = servicio; }
        public String getCodigoError() { return codigoError; }
        public void setCodigoError(String codigoError) { this.codigoError = codigoError; }

        public static class ErrorResponseBuilder {
            private LocalDateTime timestamp;
            private int status;
            private String error;
            private String message;
            private String path;
            private String numeroOperacion;
            private String servicio;
            private String codigoError;

            public ErrorResponseBuilder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp; return this; }
            public ErrorResponseBuilder status(int status) {
                this.status = status; return this; }
            public ErrorResponseBuilder error(String error) {
                this.error = error; return this; }
            public ErrorResponseBuilder message(String message) {
                this.message = message; return this; }
            public ErrorResponseBuilder path(String path) {
                this.path = path; return this; }
            public ErrorResponseBuilder numeroOperacion(String numeroOperacion) {
                this.numeroOperacion = numeroOperacion; return this; }
            public ErrorResponseBuilder servicio(String servicio) {
                this.servicio = servicio; return this; }
            public ErrorResponseBuilder codigoError(String codigoError) {
                this.codigoError = codigoError; return this; }
            public ErrorResponse build() {
                ErrorResponse response = new ErrorResponse();
                response.timestamp = this.timestamp;
                response.status = this.status;
                response.error = this.error;
                response.message = this.message;
                response.path = this.path;
                response.numeroOperacion = this.numeroOperacion;
                response.servicio = this.servicio;
                response.codigoError = this.codigoError;
                return response;
            }
        }
    }

    public static class ValidationErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;
        private Map<String, String> errores;

        public static ValidationErrorResponseBuilder builder() {
            return new ValidationErrorResponseBuilder();
        }

        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public Map<String, String> getErrores() { return errores; }
        public void setErrores(Map<String, String> errores) { this.errores = errores; }

        public static class ValidationErrorResponseBuilder {
            private LocalDateTime timestamp;
            private int status;
            private String error;
            private String message;
            private String path;
            private Map<String, String> errores;

            public ValidationErrorResponseBuilder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp; return this; }
            public ValidationErrorResponseBuilder status(int status) {
                this.status = status; return this; }
            public ValidationErrorResponseBuilder error(String error) {
                this.error = error; return this; }
            public ValidationErrorResponseBuilder message(String message) {
                this.message = message; return this; }
            public ValidationErrorResponseBuilder path(String path) {
                this.path = path; return this; }
            public ValidationErrorResponseBuilder errores(Map<String, String> errores) {
                this.errores = errores; return this; }
            public ValidationErrorResponse build() {
                ValidationErrorResponse response = new ValidationErrorResponse();
                response.timestamp = this.timestamp;
                response.status = this.status;
                response.error = this.error;
                response.message = this.message;
                response.path = this.path;
                response.errores = this.errores;
                return response;
            }
        }
    }
}

// === ARCHIVO: src/main/java/com/credito/gestion/infrastructure/exception/SolicitudDuplicadaException.java ===
package com.credito.gestion.infrastructure.exception;

public class SolicitudDuplicadaException extends RuntimeException {

    private final String numeroOperacion;
    private final String tipoDocumento;
    private final String numeroDocumento;

    public SolicitudDuplicadaException(String numeroOperacion) {
        super(String.format("Ya existe una solicitud con el número de operación: %s", numeroOperacion));
        this.numeroOperacion = numeroOperacion;
        this.tipoDocumento = null;
        this.numeroDocumento = null;
    }

    public SolicitudDuplicadaException(String tipoDocumento, String numeroDocumento) {
        super(String.format("Ya existe una solicitud aprobada para el documento: %s %s", 
                tipoDocumento, numeroDocumento));
        this.numeroOperacion = null;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }

    public SolicitudDuplicadaException(String numeroOperacion, String tipoDocumento, String numeroDocumento) {
        super(String.format("Solicitud duplicada - Número de operación: %s, Documento: %s %s", 
                numeroOperacion, tipoDocumento, numeroDocumento));
        this.numeroOperacion = numeroOperacion;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    @Override
    public String toString() {
        return "SolicitudDuplicadaException{" +
                "numeroOperacion='" + numeroOperacion + '\'' +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                "} " + super.toString();
    }
}

// === ARCHIVO: src/main/java/com/credito/gestion/infrastructure/exception/TimeoutException.java ===
package com.credito.gestion.infrastructure.exception;

public class TimeoutException extends RuntimeException {

    private final String servicio;
    private final String numeroOperacion;
    private final long tiempoEspera;

    public TimeoutException(String mensaje) {
        super(mensaje);
        this.servicio = null;
        this.numeroOperacion = null;
        this.tiempoEspera = 0L;
    }

    public TimeoutException(String mensaje, String servicio) {
        super(mensaje);
        this.servicio = servicio;
        this.numeroOperacion = null;
        this.tiempoEspera = 0L;
    }

    public TimeoutException(String mensaje, String servicio, String numeroOperacion) {
        super(mensaje);
        this.servicio = servicio;
        this.numeroOperacion = numeroOperacion;
        this.tiempoEspera = 0L;
    }

    public TimeoutException(String mensaje, String servicio, String numeroOperacion, long tiempoEspera) {
        super(mensaje);
        this.servicio = servicio;
        this.numeroOperacion = numeroOperacion;
        this.tiempoEspera = tiempoEspera;
    }

    public TimeoutException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.servicio = null;
        this.numeroOperacion = null;
        this.tiempoEspera = 0L;
    }

    public TimeoutException(String mensaje, String servicio, Throwable causa) {
        super(mensaje, causa);
        this.servicio = servicio;
        this.numeroOperacion = null;
        this.tiempoEspera = 0L;
    }

    public TimeoutException(String mensaje, String servicio, String numeroOperacion, Throwable causa) {
        super(mensaje, causa);
        this.servicio = servicio;
        this.numeroOperacion = numeroOperacion;
        this.tiempoEspera = 0L;
    }

    public TimeoutException(String mensaje, String servicio, String numeroOperacion, long tiempoEspera, Throwable causa) {
        super(mensaje, causa);
        this.servicio = servicio;
        this.numeroOperacion = numeroOperacion;
        this.tiempoEspera = tiempoEspera;
    }

    public String getServicio() {
        return servicio;
    }

    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    public long getTiempoEspera() {
        return tiempoEspera;
    }

    public boolean tieneNumeroOperacion() {
        return numeroOperacion != null && !numeroOperacion.isBlank();
    }

    public boolean tieneServicio() {
        return servicio != null && !servicio.isBlank();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TimeoutException: ");
        sb.append(getMessage());
        if (tieneServicio()) {
            sb.append(" | Servicio: ").append(servicio);
        }
        if (tieneNumeroOperacion()) {
            sb.append(" | NumeroOperacion: ").append(numeroOperacion);
        }
        if (tiempoEspera > 0) {
            sb.append(" | TiempoEspera: ").append(tiempoEspera).append("ms");
        }
        return sb.toString();
    }
}

// === ARCHIVO: src/main/java/com/credito/gestion/infrastructure/exception/CoreBancarioException.java ===
package com.credito.gestion.infrastructure.exception;

public class CoreBancarioException extends RuntimeException {

    private final String codigoError;
    private final int codigoHttp;
    private final String numeroOperacion;
    private final boolean esReintentable;

    public CoreBancarioException(String mensaje) {
        super(mensaje);
        this.codigoError = null;
        this.codigoHttp = 500;
        this.numeroOperacion = null;
        this.esReintentable = false;
    }

    public CoreBancarioException(String mensaje, String codigoError) {
        super(mensaje);
        this.codigoError = codigoError;
        this.codigoHttp = 500;
        this.numeroOperacion = null;
        this.esReintentable = false;
    }

    public CoreBancarioException(String mensaje, String codigoError, int codigoHttp) {
        super(mensaje);
        this.codigoError = codigoError;
        this.codigoHttp = codigoHttp;
        this.numeroOperacion = null;
        this.esReintentable = esError5xx(codigoHttp);
    }

    public CoreBancarioException(String mensaje, String codigoError, int codigoHttp, String numeroOperacion) {
        super(mensaje);
        this.codigoError = codigoError;
        this.codigoHttp = codigoHttp;
        this.numeroOperacion = numeroOperacion;
        this.esReintentable = esError5xx(codigoHttp);
    }

    public CoreBancarioException(String mensaje, String codigoError, int codigoHttp, String numeroOperacion, boolean esReintentable) {
        super(mensaje);
        this.codigoError = codigoError;
        this.codigoHttp = codigoHttp;
        this.numeroOperacion = numeroOperacion;
        this.esReintentable = esReintentable;
    }

    public CoreBancarioException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = null;
        this.codigoHttp = 500;
        this.numeroOperacion = null;
        this.esReintentable = false;
    }

    public CoreBancarioException(String mensaje, String codigoError, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.codigoHttp = 500;
        this.numeroOperacion = null;
        this.esReintentable = false;
    }

    public CoreBancarioException(String mensaje, String codigoError, int codigoHttp, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.codigoHttp = codigoHttp;
        this.numeroOperacion = null;
        this.esReintentable = esError5xx(codigoHttp);
    }

    public CoreBancarioException(String mensaje, String codigoError, int codigoHttp, String numeroOperacion, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.codigoHttp = codigoHttp;
        this.numeroOperacion = numeroOperacion;
        this.esReintentable = esError5xx(codigoHttp);
    }

    public CoreBancarioException(String mensaje, String codigoError, int codigoHttp, String numeroOperacion, boolean esReintentable, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.codigoHttp = codigoHttp;
        this.numeroOperacion = numeroOperacion;
        this.esReintentable = esReintentable;
    }

    private static boolean esError5xx(int codigoHttp) {
        return codigoHttp >= 500 && codigoHttp < 600;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public int getCodigoHttp() {
        return codigoHttp;
    }

    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    public boolean isEsReintentable() {
        return esReintentable;
    }

    public boolean tieneNumeroOperacion() {
        return numeroOperacion != null && !numeroOperacion.isBlank();
    }

    public boolean tieneCodigoError() {
        return codigoError != null && !codigoError.isBlank();
    }

    public boolean esErrorServidor() {
        return esError5xx(codigoHttp);
    }

    public boolean esErrorCliente() {
        return codigoHttp >= 400 && codigoHttp < 500;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CoreBancarioException: ");
        sb.append(getMessage());
        if (tieneCodigoError()) {
            sb.append(" | CodigoError: ").append(codigoError);
        }
        sb.append(" | CodigoHTTP: ").append(codigoHttp);
        if (tieneNumeroOperacion()) {
            sb.append(" | NumeroOperacion: ").append(numeroOperacion);
        }
        sb.append(" | Reintentable: ").append(esReintentable);
        return sb.toString();
    }
}

// === ARCHIVO: src/test/java/com/credito/gestion/steps/SolicitudCreditoSteps.java ===
package com.credito.gestion.steps;

import com.credito.gestion.Application;
import com.credito.gestion.domain.model.SolicitudCredito;
import com.credito.gestion.domain.port.AntifraudeService;
import com.credito.gestion.domain.port.BuroRiesgosService;
import com.credito.gestion.domain.port.CoreBancarioService;
import com.credito.gestion.domain.port.SolicitudCreditoRepository;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = Application.class)
public class SolicitudCreditoSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SolicitudCreditoRepository solicitudCreditoRepository;

    @Autowired
    private AntifraudeService antifraudeService;

    @Autowired
    private BuroRiesgosService buroRiesgosService;

    @Autowired
    private CoreBancarioService coreBancarioService;

    private SolicitudCredito solicitudEnProceso;
    private ResponseEntity<String> respuestaHttp;
    private String numeroOperacionGenerado;
    private String resultadoAntifraude;
    private String resultadoBuro;
    private String resultadoCore;

    @Dado("que el sistema está operativo y disponible")
    public void queElSistemaEstaOperativo() {
        assertNotNull(restTemplate, "RestTemplate no debería ser nulo");
        assertNotNull(solicitudCreditoRepository, "Repositorio no debería ser nulo");
    }

    @Dado("una solicitud de crédito con los siguientes datos:")
    public void unaSolicitudConLosDatos(Map<String, String> datosSolicitud) {
        numeroOperacionGenerado = datosSolicitud.getOrDefault("numeroOperacion", UUID.randomUUID().toString());
        String tipoDocumento = datosSolicitud.get("tipoDocumento");
        String numeroDocumento = datosSolicitud.get("numeroDocumento");
        String monto = datosSolicitud.get("monto");
        String plazo = datosSolicitud.get("plazoMeses");

        solicitudEnProceso = new SolicitudCredito(
            UUID.fromString(numeroOperacionGenerado),
            tipoDocumento,
            numeroDocumento,
            new BigDecimal(monto),
            Integer.parseInt(plazo),
            "ORIGINADOR_CREDITOS",
            null, null, null, null
        );
    }

    @Cuando("el originador envía la solicitud de crédito al sistema")
    public void elOriginadorEnvíaLaSolicitud() {
        String jsonSolicitud = String.format("""
            {
                "numeroOperacion": "%s",
                "tipoDocumento": "%s",
                "numeroDocumento": "%s",
                "monto": %s,
                "plazoMeses": %d,
                "canalOrigen": "ORIGINADOR_CREDITOS"
            }
            """,
            solicitudEnProceso.numeroOperacion(),
            solicitudEnProceso.tipoDocumento(),
            solicitudEnProceso.numeroDocumento(),
            solicitudEnProceso.monto(),
            solicitudEnProceso.plazoMeses()
        );

        respuestaHttp = restTemplate.postForEntity(
            "/api/solicitudes-credito",
            jsonSolicitud,
            String.class
        );
    }

    @Entonces("el sistema debe responder con código de estado {int}")
    public void elSistemaRespondeCon(int codigoEsperado) {
        assertNotNull(respuestaHttp, "La respuesta no debería ser nula");
        assertEquals(codigoEsperado, respuestaHttp.getStatusCode().value(),
            "El código de estado debería ser " + codigoEsperado);
    }

    @Y("el sistema debe guardar la solicitud en la base de datos")
    public void elSistemaDebeGuardarLaSolicitud() {
        var solicitudGuardada = solicitudCreditoRepository.findByNumeroOperacion(
            solicitudEnProceso.numeroOperacion()
        );
        assertTrue(solicitudGuardada.isPresent(), "La solicitud debería estar guardada");
    }

    @Dado("que existe una solicitud de crédito previamente procesada")
    public void existeSolicitudPreviamenteProcesada() {
        solicitudEnProceso = new SolicitudCredito(
            UUID.randomUUID(),
            "CC",
            "12345678",
            new BigDecimal("5000000"),
            12,
            "ORIGINADOR_CREDITOS",
            "APROBADO",
            "APROBADO",
            "APROBADO",
            null
        );
        solicitudCreditoRepository.save(solicitudEnProceso);
    }

    @Cuando("el originador envía nuevamente la solicitud con el mismo número de operación")
    public void elOriginadorEnvíaNuevamenteLaSolicitud() {
        String jsonSolicitud = String.format("""
            {
                "numeroOperacion": "%s",
                "tipoDocumento": "%s",
                "numeroDocumento": "%s",
                "monto": %s,
                "plazoMeses": %d,
                "canalOrigen": "ORIGINADOR_CREDITOS"
            }
            """,
            solicitudEnProceso.numeroOperacion(),
            solicitudEnProceso.tipoDocumento(),
            solicitudEnProceso.numeroDocumento(),
            solicitudEnProceso.monto(),
            solicitudEnProceso.plazoMeses()
        );

        respuestaHttp = restTemplate.postForEntity(
            "/api/solicitudes-credito",
            jsonSolicitud,
            String.class
        );
    }

    @Entonces("el sistema debe retornar el resultado del procesamiento anterior")
    public void elSistemaRetornaResultadoAnterior() {
        assertEquals(HttpStatus.CONFLICT.value(), respuestaHttp.getStatusCode().value(),
            "Debería retornarse conflicto por solicitud duplicada");
    }

    @Cuando("el sistema evalúa el riesgo con el servicio de antifraude")
    public void elSistemaEvaluaRiesgoAntifraude() {
        CompletableFuture<String> resultadoFuture = antifraudeService.evaluarRiesgo(solicitudEnProceso);
        resultadoAntifraude = resultadoFuture.join();
    }

    @Entonces("el resultado del antifraude debe ser {string}")
    public void elResultadoDebeSer(String resultadoEsperado) {
        assertNotNull(resultadoAntifraude, "El resultado del antifraude no debería ser nulo");
        assertEquals(resultadoEsperado, resultadoAntifraude,
            "El resultado del antifraude debería ser " + resultadoEsperado);
    }

    @Cuando("el sistema consulta el buró de riesgos")
    public void elSistemaConsultaBuroRiesgos() {
        CompletableFuture<String> resultadoFuture = buroRiesgosService.consultarBuro(solicitudEnProceso);
        resultadoBuro = resultadoFuture.join();
    }

    @Entonces("el resultado del buró debe ser {string}")
    public void elResultadoBuroDebeSer(String resultadoEsperado) {
        assertNotNull(resultadoBuro, "El resultado del buró no debería ser nulo");
        assertEquals(resultadoEsperado, resultadoBuro,
            "El resultado del buró debería ser " + resultadoEsperado);
    }

    @Cuando("el sistema consulta el core bancario")
    public void elSistemaConsultaCoreBancario() {
        CompletableFuture<String> resultadoFuture = coreBancarioService.consultarCore(solicitudEnProceso);
        resultadoCore = resultadoFuture.join();
    }

    @Entonces("el resultado del core debe ser {string}")
    public void elResultadoCoreDebeSer(String resultadoEsperado) {
        assertNotNull(resultadoCore, "El resultado del core no debería ser nulo");
        assertEquals(resultadoEsperado, resultadoCore,
            "El resultado del core debería ser " + resultadoEsperado);
    }

    @Dado("que el monto solicitado supera el límite permitido de {int}")
    public void queElMontoSuperaElLimite(int limite) {
        solicitudEnProceso = new SolicitudCredito(
            UUID.randomUUID(),
            "CC",
            "99999999",
            new BigDecimal(limite + 1),
            12,
            "ORIGINADOR_CREDITOS",
            null, null, null, null
        );
    }

    @Entonces("el sistema debe rechazar la solicitud por monto excedido")
    public void elSistemaRechazaPorMontoExcedido() {
        assertTrue(solicitudEnProceso.monto().compareTo(new BigDecimal(10000000)) > 0,
            "El monto debería exceder el límite");
    }

    @Dado("que el plazo solicitado supera el límite máximo de {int} meses")
    public void queElPlazoSuperaElLimite(int limite) {
        solicitudEnProceso = new SolicitudCredito(
            UUID.randomUUID(),
            "CC",
            "88888888",
            new BigDecimal("5000000"),
            limite + 1,
            "ORIGINADOR_CREDITOS",
            null, null, null, null
        );
    }

    @Entonces("el sistema debe rechazar la solicitud por plazo excedido")
    public void elSistemaRechazaPorPlazoExcedido() {
        assertTrue(solicitudEnProceso.plazoMeses() > 60,
            "El plazo debería exceder el límite máximo");
    }
}

// === ARCHIVO: src/test/java/com/credito/gestion/CucumberTest.java ===
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

// === ARCHIVO: src/test/resources/cucumber.properties ===
cucumber.publish.enabled=true
cucumber.publish.quiet=true
cucumber.snippet.type=underscore
cucumber.features=src/main/resources/features
cucumber.glue=com.credito.gestion.steps
cucumber.filter.tags=@solicitudCredito
cucumber.plugin=pretty,json:target/cucumber-reports/cucumber.json,html:target/cucumber-reports/cucumber.html
cucumber.strict=true
cucumber.dry-run=false
cucumber.monochrome=false
```
