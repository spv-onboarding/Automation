@Browser @CalculoNSEDeNosis
Feature: Obtencion de Nivel Socio Economico

  @Regression
  Scenario Outline: Verificar en Datos Analiticos el valor Categoria NSE el mismo debe ser el devuelto por Sacnosis
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    When obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <oferta>
    Then valido que el valor de NSE en servicio que obtiene los antecedentes comerciales exista en al tabla de CCR_NivelesSocioEconomicos
    And valido que el valor de la categoria de NSE en servicio que obtiene los antecedentes comerciales sea el mismo que en tabla Datos Analiticos para mi <oferta>

    Examples: 
      | producto             | oferta                         |
      | "onboarding.voucher" | "UsuarioRelacionDeDependencia4" |
