@NoBrowser @Servicios
Feature: API Clientes
  Servicios - Validar servicios Rest

  Scenario Outline: Validar infoBasica de Clientes responde OK con cuil valido
    Given consulto a <resource> usado para Onboarding
    When realizo el post request con el cuil <cuil>, mail <mail> y tipo de oferta <tipoOferta>
    Then el servicio responde <code>

    Examples: 
      | dni        | resource                  | cuil        | mail                      | tipoOferta      | code |
      | "28269338" | "api/clientes/infoBasica" | 20500000008 | "favio.veron@globant.com" | "voucherMaster" |  200 |
