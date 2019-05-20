@Browser @EstadoUsuario
Feature: Estado de usuario en Onboarding

  @Regression @UsuarioNoBloqueado
  Scenario Outline: Validar que Usuario no bloqueado continua con el proceso de alta hasta la pagina de oferta
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    When valido que para el prospect que tiene <oferta> la respuesta del servicio de preguntas de seguridad contiene <mensaje>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    Then la pagina de Oferta es correctamente desplegada

    Examples: 
      | producto             | oferta                            | mensaje |
      | "onboarding.voucher" | "Oferta+Master+MultiplesTarjetas" | "OK"    |

  @Regression @UsuarioBloqueado
  Scenario Outline: Validar que para un Usuario bloqueado se muestra mensaje acorde a la situación y no podrá continuar con el proceso de alta.
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    When ingreso datos de un prospect que tiene <oferta>
    Then valido que para el prospect que tiene <oferta> la respuesta del servicio de preguntas de seguridad contiene <mensaje>
    And valido que en la pagina el mensaje de usuario bloqueado este visible <mensaje>

    Examples: 
      | producto             | oferta             | mensaje                                    												|
      | "onboarding.voucher" | "UsuarioBloqueado" | "No hemos podido validar tu identidad.Podes solicitar tu tarjeta" |
     #| "onboarding.voucher" | "UsuarioBloqueado" | "Lamentablemente tu Usuario fue bloqueado" 												| 06-02-2019: Cambió el 'mensaje'
