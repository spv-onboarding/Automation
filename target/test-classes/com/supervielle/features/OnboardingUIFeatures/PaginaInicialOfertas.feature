@Browser @PaginaInicialOfertas
Feature: Pagina Inicial de Ofertas

  @Regression
  Scenario Outline: Validar que las caracteristicas basicas de las ofertas disponibles esten presentes en la UI de Onboarding
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    Then la marca de la oferta ofrecida en la UI corresponde con <marca>
    And los textos de costos de comision aparecen en la UI
    And los bloques de costos de comision aparecen en la UI
 #   And realizo mouse hover sobre tooltip comision de Renovacion Anual
    And valido que el tooltip comision de Renovacion anual sea visible
    And realizo mouse hover sobre tooltip comision de Administracion
    And valido que el tooltip comision de Renovacion Administrativa sea visible

    Examples: 
      | oferta                                | producto             | marca        |
      | "Oferta+Voucher+Platinum+TarjetaVisa" | "onboarding.voucher" | "MASTERCARD" |
