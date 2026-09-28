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