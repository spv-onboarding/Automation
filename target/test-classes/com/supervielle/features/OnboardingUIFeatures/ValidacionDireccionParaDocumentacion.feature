@Browser @DireccionParaDocumentacion
Feature: Validacion de Direccion

  @Regression @DireccionValidaDocumentacion
  Scenario Outline: Validar acceso a confirmacion de solicitud de tarjeta luego de ingresar direccion valida en seleccion de sucursal
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    And completo los datos personales, de direccion e informacion laboral del prospect que tiene <oferta>
    And selecciono la opcion para aceptar los datos del formulario de alta
    And selecciono una sucursal de supervielle en el mapa
    And selecciono la opcion Otra Direccion para recibir la documentacion
    And completo los datos de una direccion alternativa valida para el prospect que tiene <oferta>
    And confirmo la seleccion de la sucursal
    Then la aplicacion me redirige a la pagina de confirmacion de solicitud de tarjeta

    Examples: 
      | producto             | oferta                |
      | "onboarding.voucher" | "UsuarioMonotributoD" |

  @Regression @DireccionNoValidaDocumentacion
  Scenario Outline: Validar mensaje de error luego de ingresar una direccion no valida en seleccion de sucursal
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    And completo los datos personales, de direccion e informacion laboral del prospect que tiene <oferta>
    And selecciono la opcion para aceptar los datos del formulario de alta
    And selecciono una sucursal de supervielle en el mapa
    And selecciono la opcion Otra Direccion para recibir la documentacion
    And completo los datos de una direccion alternativa valida para el prospect que tiene <oferta>
    Then cada vez que ingrese una direccion no valida de <calle> y haga click en el boton continuar, la pagina me muestra un mensaje de error de direccion alternativa no valida, negandome el acceso a la pagina de confirmacion de solicitud de tarjeta

    Examples: 
      | producto             | oferta										| calle					|
      | "onboarding.voucher" | "UsuarioMonotributoD"		| "!,?/#"				|
#     | "onboarding.voucher" | "UsuarioMonotributoD"		| "!,?/A..A21"	| Los '..' son válidos o inválidos?
      