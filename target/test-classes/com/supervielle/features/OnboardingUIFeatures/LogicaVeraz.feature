@Browser @Veraz
Feature: Logica Veraz

  @Regression @GrupoBanco0
  Scenario Outline: Validar servicio de Veraz para Grupo Banco <= 0
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    When ingreso datos de un prospect que tiene <estado>
    And respondo las preguntas de seguridad en base al prospect que tiene <estado>
    And ingreso codigo de seguridad del email del prospect que tiene <estado>
    And obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <estado>
    Then la respuesta del servicio que obtiene los antecedentes comerciales tiene el valor CI en el tag Dato-Prefijo
    And la respuesta del servicio que obtiene los antecedentes comerciales tiene la tag Deudas dentro del tag UltimoInforme
    And la respuesta del servicio de informacion crediticia y solvencia economica para el prospect que tiene <estado> devuelve un valor menor o igual a cero en algun grupo de banco

    Examples: 
      | producto             | estado          |
      | "onboarding.voucher" | "GrupoBanco<=0" |

  @Regression @ScoreVeraz0
  Scenario Outline: Validar servicio de Veraz para un prospect con score veraz = 0
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    When ingreso datos de un prospect que tiene <estado>
    And respondo las preguntas de seguridad en base al prospect que tiene <estado>
    And ingreso codigo de seguridad del email del prospect que tiene <estado>
    And obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <estado>
    Then la respuesta del servicio de informacion crediticia y solvencia economica para el prospect que tiene <estado> devuelve un valor de score_veraz = 0

    Examples: 
      | producto             | estado        |
      | "onboarding.voucher" | "ScoreVeraz0" |

  @Regression @ProspectSinDeudas
  Scenario Outline: Validar servicio de Veraz para un prospect Sin Deudas
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    When ingreso datos de un prospect que tiene <estado>
    And respondo las preguntas de seguridad en base al prospect que tiene <estado>
    And ingreso codigo de seguridad del email del prospect que tiene <estado>
    And obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <estado>
    Then la respuesta del servicio que obtiene los antecedentes comerciales tiene el valor CI en el tag Dato-Prefijo
    And la respuesta del servicio que obtiene los antecedentes comerciales no contiene datos de deudas

    Examples: 
      | producto             | estado           |
      | "onboarding.voucher" | "VerazSinDeudas" |

  @Regression
  Scenario Outline: Validar que el servicio de Veraz no contenga el valor CI en Prefijo
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    When ingreso datos de un prospect que tiene <estado>
    And respondo las preguntas de seguridad en base al prospect que tiene <estado>
    And ingreso codigo de seguridad del email del prospect que tiene <estado>
    And obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <estado>
    Then la respuesta del servicio que obtiene los antecedentes comerciales no tiene el valor CI en el tag Dato-Prefijo

    Examples: 
      | producto             | estado                 |
      | "onboarding.voucher" | "SacnosisSinPrefijoCI" |

  @Regression @AceptacionCrediticia @CasoValido
  Scenario Outline: Validar que la respuesta del servicio de Aceptacion Crediticia sea valida
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    When ingreso datos de un prospect que tiene <estado>
    And respondo las preguntas de seguridad en base al prospect que tiene <estado>
    And ingreso codigo de seguridad del email del prospect que tiene <estado>
    And obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <estado>
    And verifico que el mayor ingreso de la respuesta del servicio de informacion crediticia y solvencia economica para el prospect que tiene <estado> no supera el limite de grupos
    When obtengo la llamada al servicio de aceptacion crediticia para el prospect que tiene <estado>
    Then la request del servicio de aceptacion crediticia tiene el elemento politica igual a <politica>
    And el ingreso del servicio de aceptacion crediticia es igual al ingreso calculado de la respuesta de veraz
    And el tipoScore del servicio de aceptacion crediticia es igual a <tipoScore>
    And la respuesta del servicio de aceptacion crediticia es <respuesta>
    And el codigo del servicio de aceptacion crediticia es <codigo>

    Examples: 
      | producto             | estado                               | politica | tipoScore | respuesta            | codigo |
      | "onboarding.voucher" | "OfertaMaster+Voucher+VisaSignature" |        1 | "1"       | "Solicitud Aprobada" | "1"    |

  @Regression @Politica @CasoValido @OfertaCrediticia @ELQUEFALTA
  Scenario Outline: Verificar que se utilice la politica correcta en oferta crediticia
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    When ingreso datos de un prospect que tiene <estado>
    And respondo las preguntas de seguridad en base al prospect que tiene <estado>
    And ingreso codigo de seguridad del email del prospect que tiene <estado>
    And obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <estado>
    And obtengo la llamada al servicio de informacion crediticia y solvencia economica para el prospect que tiene <estado>
    And verifico que el mayor ingreso de la respuesta del servicio de informacion crediticia y solvencia economica para el prospect que tiene <estado> no supera el limite de grupos
    And obtengo la llamada al servicio de oferta crediticia para el prospect que tiene <estado>
    Then la respuesta del servicio que obtiene los antecedentes comerciales tiene el valor CI en el tag Dato-Prefijo
    And la respuesta del servicio que obtiene los antecedentes comerciales tiene la tag Deudas dentro del tag UltimoInforme
    Then la request de la llamada a oferta crediticia tiene un valor de PoliticaID valido

    Examples: 
      | producto             | estado            |
      | "onboarding.voucher" | "PoliticaGeneral" |
      | "onboarding.voucher" | "PoliticaEspejo"  |

  @Regression @Veraz @ValidarPreguntasSeguridad
  Scenario Outline: Verificar el estado y resultado de las preguntas de seguridad de Veraz para confirmacion de la validez de las mismas
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    When ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And obtengo la respuesta del servicio Consulta Cuestionario Veraz para el prospect que tiene <oferta>
    Then valido el estado <estado> y resultado <resultado> en Consulta Cuestionario Veraz

    Examples: 
      | producto             | oferta                               | estado | resultado |
      | "onboarding.voucher" | "OfertaMaster+Voucher+VisaSignature" |      1 | "OK"      |
