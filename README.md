# Diseño y Automatización de Funcionalidades en Sistema de Gestión de Créditos

El sistema de gestión de créditos de una institución financiera requiere la implementación y automatización de nuevas funcionalidades utilizando BDD. El objetivo es garantizar que las funcionalidades se desarrollen de manera robusta y sean verificables a través de pruebas automatizadas. Los actores involucrados incluyen el originador de créditos, el motor antifraude, el buró de riesgos y el core bancario. Las funcionalidades deben manejar volúmenes de hasta 1 500 solicitudes por segundo en hora pico y garantizar una latencia máxima de 2 segundos por solicitud. El sistema debe ser idempotente en la recepción de solicitudes, utilizando el número de operación como clave, y manejar adecuadamente los timeouts del buró y las respuestas 5xx del core.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | desarrollador-senior-con-sólida-experiencia-en-bdd-y-frameworks-de-automatización |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 2 semanas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Especificación de Funcionalidades en BDD

**Objetivo:** Definir las funcionalidades del sistema utilizando el lenguaje de negocio y BDD.

**Tiempo estimado:** 3 días

**Instrucciones:**

- Identificar las funcionalidades clave del sistema de gestión de créditos.
- Escribir las especificaciones en lenguaje de negocio para cada funcionalidad utilizando BDD.
- Garantizar que las especificaciones sean claras, concisas y verificables.

**Entregable:** Especificaciones en lenguaje de negocio para las funcionalidades del sistema.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los diferentes actores y sus interacciones.
- Piensa en los posibles edge cases y cómo manejarlos.

</details>

### Fase 2: Automatización de Pruebas con Frameworks de Automatización

**Objetivo:** Implementar pruebas automatizadas para las funcionalidades definidas en la fase anterior.

**Tiempo estimado:** 5 días

**Instrucciones:**

- Seleccionar un framework de automatización (como Cucumber o Karate) para implementar las pruebas.
- Escribir los casos de prueba automatizados para cada funcionalidad.
- Ejecutar las pruebas y verificar que se cumplan los criterios de aceptación.

**Entregable:** Casos de prueba automatizados para las funcionalidades del sistema.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la idempotencia y los posibles modos de falla en tus pruebas.
- Piensa en cómo manejar los edge cases y las respuestas del sistema.

</details>

### Fase 3: Refactorización y Optimización

**Objetivo:** Refactorizar y optimizar el código para mejorar la robustez y el rendimiento.

**Tiempo estimado:** 4 días

**Instrucciones:**

- Analizar el código implementado en las fases anteriores.
- Identificar áreas de mejora y refactorizar el código para mayor robustez y rendimiento.
- Verificar que las funcionalidades siguen cumpliendo los criterios de aceptación después del refactor.

**Entregable:** Código refactorizado y optimizado para las funcionalidades del sistema.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la consistencia y la latencia en tus refactorizaciones.
- Piensa en cómo puedes mejorar la idempotencia y el manejo de errores.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es BDD y cómo se aplica en el desarrollo de software?
- **paraQueSirve**: ¿Para qué sirve utilizar BDD en el desarrollo de funcionalidades?
- **comoSeUsa**: ¿Cómo se escriben especificaciones en lenguaje de negocio utilizando BDD?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar pruebas automatizadas y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica refactorizar y optimizar el código en términos de robustez y rendimiento?

## Criterios de Evaluacion

- Definir funcionalidades utilizando BDD.
- Implementar pruebas automatizadas para las funcionalidades.
- Refactorizar y optimizar el código para mejorar la robustez y el rendimiento.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
