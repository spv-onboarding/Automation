@Browser @Alta
Feature: Alta de un prospect en Onboarding

  @Regression @AltaTipoProducto @DEMO200
  Scenario Outline: Validar alta de prospect entrando a Onboarding desde distintos tipos de producto
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    And solicito tarjeta
    And verifico que en la barra de navegacion este activo el step de Oferta
    And completo los datos personales, de direccion e informacion laboral del prospect que tiene <oferta>
    And selecciono la opcion para aceptar los datos del formulario de alta
    And verifico que en la barra de navegacion este activo el step de Seleccion de Sucursal
    And selecciono una sucursal de supervielle en el mapa
    And selecciono la direccion por defecto para la entrega de la documentacion
    And confirmo la seleccion de la sucursal
    And verifico que en la barra de navegacion este activo el step de Confirmacion de producto
    And acepto los terminos y condiciones
    And confirmo la seleccion de la tarjeta
    And obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <oferta>
    And obtengo la llamada al servicio de alta Persona para el prospect que tiene <oferta>
    And obtengo la llamada al servicio de alta de <producto> para el prospect que tiene <oferta>
    And valido la pantalla de comercios adheridos segun el <producto> y la <oferta>
    Then la aplicacion me muestra una pagina con un mensaje de que mi operacion fue exitosa
    And la request del servicio alta Persona para el prospect que tiene <oferta> tiene parametros validos
    And la response del servicio alta Persona para el prospect describe una operacion exitosa
    And el servicio de alta de tarjeta para prospect que tiene <oferta> corresponde con el <producto>
    And el servicio de alta de <producto> responde con codigo de exito

    Examples: 
      | producto             | oferta                            |
      | "onboarding.voucher" | "OfertaMaster+Voucher"            |
 #    | "onboarding.tarjeta" | "Oferta+Tarjeta+Voucher+Platinum" |

  @DeprecadoEnProduccion @AltaAdicionalesTipoProducto
  Scenario Outline: Validar alta de prospect con seleccion de adicionales entrando a Onboarding desde distintos tipos de producto
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    And solicito tarjeta
    And completo los datos personales, de direccion e informacion laboral del prospect que tiene <oferta>
    And selecciono la opcion para aceptar los datos del formulario de alta
    And selecciono una sucursal de supervielle en el mapa
    And selecciono la direccion por defecto para la entrega de la documentacion
    And confirmo la seleccion de la sucursal
    And acepto los terminos y condiciones
    And confirmo la seleccion de la tarjeta
    And valido la pantalla de comercios adheridos segun el <producto> y la <oferta>
    And hago click en la opcion Entendido en la pagina de seleccion de adicionales
    When verifico que el prospect que tiene <oferta> no tenga registrado al <adicional> en la base de datos
    And selecciono <cantidad> adicionales
    And selecciono la opcion confirmar adicionales
    And cargo los datos de un <adicional> en los datos personales
    And hago click en continuar operacion
    And hago click en la opcion disponible para confirmar y finalizar la solicitud de adicionales
    And obtengo la llamada al servicio alta de adicional para el prospect que tiene <oferta>
    Then la aplicacion me muestra un mensaje de solicitud de adicionales exitosa
    And el servicio de alta de adicional responde con codigo de exito

    Examples: 
      | producto             | oferta                            | cantidad | adicional    |
      | "onboarding.voucher" | "OfertaMaster+Voucher"            |        1 | "AdicionalA" |
      | "onboarding.tarjeta" | "Oferta+Tarjeta+Voucher+Platinum" |        1 | "AdicionalA" |

  @DeprecadoEnProduccion @AltaAdicionalesMasterCard
  Scenario Outline: Validar alta de prospect con seleccion de adicional de MasterCard
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    And solicito tarjeta
    And completo los datos personales, de direccion e informacion laboral del prospect que tiene <oferta>
    And selecciono la opcion para aceptar los datos del formulario de alta
    And selecciono una sucursal de supervielle en el mapa
    And selecciono la direccion por defecto para la entrega de la documentacion
    And confirmo la seleccion de la sucursal
    And acepto los terminos y condiciones
    And confirmo la seleccion de la tarjeta
    And valido la pantalla de comercios adheridos segun el <producto> y la <oferta>
    And obtengo la llamada al servicio de alta de <producto> para el prospect que tiene <oferta>
    And hago click en la opcion Entendido en la pagina de seleccion de adicionales
    When verifico que el prospect que tiene <oferta> no tenga registrado al <adicional> en la base de datos
    And selecciono <cantidad> adicionales
    And selecciono la opcion confirmar adicionales
    And cargo los datos de un <adicional> en los datos personales
    And hago click en continuar operacion
    And hago click en la opcion disponible para confirmar y finalizar la solicitud de adicionales
    And obtengo la llamada al servicio alta de adicional para el prospect que tiene <oferta>
    Then la aplicacion me muestra un mensaje de solicitud de adicionales exitosa
    And el servicio de alta de adicional responde con codigo de exito
    And la request del servicio de alta adicional para el <adicional> tiene parametros validos con respecto al <producto> y la <marca>

    Examples: 
      | producto             | oferta                 | cantidad | adicional    | marca  |
      | "onboarding.voucher" | "OfertaMaster+Voucher" |        1 | "AdicionalA" | "Visa" |

  @Regression @AltaVoucherComerciosAdheridos
  Scenario Outline: Validar que se muestra una lista de locales adheridos al banco despues de un alta para voucher
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    And solicito tarjeta
    And completo los datos personales, de direccion e informacion laboral del prospect que tiene <oferta>
    And selecciono la opcion para aceptar los datos del formulario de alta
    And selecciono una sucursal de supervielle en el mapa
    And selecciono la direccion por defecto para la entrega de la documentacion
    And confirmo la seleccion de la sucursal
    And acepto los terminos y condiciones
    And confirmo la seleccion de la tarjeta
    Then valido la pantalla de comercios adheridos segun el <producto> y la <oferta>
    And la aplicacion me muestra una pagina con un mensaje de que mi operacion fue exitosa

    Examples: 
      | producto             | oferta                 |
      | "onboarding.voucher" | "OfertaMaster+Voucher" |

  @Regression @AltaUsuarioSinLlamarANosis
  Scenario Outline: Validar que para un usuario dado de alta no se ejecuta el servicio que obtiene los antecedentes comerciales
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    When ingreso datos de un prospect <prospect>
    Then valido que en la pagina el mensaje de proceso de Tarjeta de Credito activo este visible <mensaje>
    And valido que la llamada al servicio que obtiene los antecedentes comerciales no se ejecute

    Examples: 
      | producto             | prospect               | mensaje                                            |
      | "onboarding.voucher" | "OfertaMaster+Voucher" | "ya tenes un proceso de Tarjeta de Crédito activo" |

  @Regression @ResumenAlta
  Scenario Outline: Validar resumen de alta para un prospect
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    And solicito tarjeta
    And completo los datos personales, de direccion e informacion laboral del prospect que tiene <oferta>
    And selecciono la opcion para aceptar los datos del formulario de alta
    And selecciono una sucursal de supervielle en el mapa
    And selecciono la direccion por defecto para la entrega de la documentacion
    When confirmo la seleccion de la sucursal
    Then valido que el precio mensual, productos seleccionados y terminos y condiciones sean visibles
    And valido que al hacer click en el link de Terminos y Condiciones el popup sea visible y luego lo cancelo
    And valido que el sistema abra pop up y solicite que se marque el checkbox de Terminos y Condiciones cuando Solicito tarjeta
    And valido que al aceptar los Terminos y Condiciones desde el pop up rediriga a la confirmacion de la tarjeta
    And hago click en la opcion Entendido en la pagina de seleccion de adicionales
    And la aplicacion me muestra una pagina con un mensaje de que mi operacion fue exitosa

    Examples: 
      | producto             | oferta                 |
      | "onboarding.voucher" | "OfertaMaster+Voucher" |
