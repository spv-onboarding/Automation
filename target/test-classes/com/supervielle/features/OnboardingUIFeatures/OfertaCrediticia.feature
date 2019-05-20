@Browser @OfertaCrediticia
Feature: Llamado a OfertaCreditica durante el flujo de alta en Onboarding

  @Regression @AltaVoucher
  Scenario Outline: Validar correspondencia de datos entre alta Tarjeta instantanea y Oferta crediticia durante alta de prospect
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
    And obtengo la llamada al servicio de oferta crediticia para el prospect que tiene <oferta>
    And obtengo la llamada al servicio de alta de <producto> para el prospect que tiene <oferta>
    Then la aplicacion me muestra una pagina con un mensaje de que mi operacion fue exitosa
    And el servicio de alta de tarjeta para prospect que tiene <oferta> corresponde con el <producto>
    And la request de alta de tarjeta tiene el valor de codigo, porcentaje, y limite correspondientes con el de oferta crediticia

    Examples: 
      | producto             | oferta                  |
      | "onboarding.voucher" | "OfertaMaster+Voucher2" |

  @Regression @AceptacionCrediticia @AceptacionCrediticiaRechazada
  Scenario Outline: Validar que la respuesta del servicio de Aceptacion Crediticia sea valida para un prospect sin ofertas
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    When ingreso datos de un prospect que tiene <estado>
    When obtengo la llamada al servicio de aceptacion crediticia para el prospect que tiene <estado>
    And la respuesta del servicio de aceptacion crediticia es <respuesta>
    And el codigo del servicio de aceptacion crediticia es <codigo>
    And la pagina muestra el mensaje <mensaje> correspondiente a prospect sin oferta

    Examples: 
      | producto             | estado                          | respuesta             | codigo | mensaje                                                              |
      | "onboarding.voucher" | "AceptacionCrediticiaRechazada" | "Solicitud Rechazada" | "4"    | "Lamentablemente no tenemos una oferta para vos mediante este canal" |

  @Regression @SinOferta
  Scenario Outline: Validar mensaje de error durante ingreso de datos de prospect cuando el servicio de oferta crediticia responda con datos invalidos de Normalizacion de Tarjeta
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <estado>
    And obtengo la llamada al servicio de oferta crediticia para el prospect que tiene <estado>
    And si la response de oferta crediticia informa de que no hay ofertas para el cliente la aplicacion devuelve un <mensaje>

    Examples: 
      | producto             | estado                | mensaje                                                              |
      | "onboarding.voucher" | "SinOfertaCrediticia" | "Lamentablemente no tenemos una oferta para vos mediante este canal" |

  @Regression @GeneracionOfertaCrediticia
  Scenario Outline: Validar que el servicio de oferta crediticia devuelva un resultado para un prospect <oferta>
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    When obtengo la llamada al servicio de oferta crediticia para el prospect que tiene <oferta>
    And obtengo la llamada al servicio de consulta scoring Supervielle para el prospect que tiene <oferta>
    And obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <oferta>
    Then request de llamada a oferta crediticia tiene un valor de CanalVentaID valido <canalVentaID>
    And request de llamada a oferta crediticia tiene un valor de SegmentoID valido <segmentoID>
    And valido el request de OfertaCrediticia donde PoliticaID tenga el valor <politicaID>
    And request de llamada a oferta crediticia tiene un valor de solicitadoDescubierto <solicitadoDescubierto>, solicitadoVisaLimite <solicitadoVisaLimite> y solicitadoMasterLimite <solicitadoMasterLimite>
    And request de llamada a oferta crediticia tiene un valor de solicitadoPrestamoCapital <solicitadoPrestamoCapital>, solicitadoPrestamoPlazo <solicitadoPrestamoPlazo> y solicitadoPrestamoTasa <solicitadoPrestamoTasa>
    And request de llamada a oferta crediticia tiene un valor de empresaID <empresaID>, acuerdoActual <acuerdoActual> y paqueteActual <paqueteActual>
    And request de llamada a oferta crediticia tiene un valor de sucursalID <sucursalID>, rentaConyuge <rentaConyuge>
    And valido en response de Consulta scoring Supervielle que el score de sea mayor a 0 y menor a 999
    And valido que Edad en la respuesta del servicio que obtiene los antecedentes comerciales sea mayor de edad y no nulo

    Examples: 
      | producto             | oferta                            | canalVentaID | segmentoID | solicitadoDescubierto | solicitadoVisaLimite | solicitadoMasterLimite | solicitadoPrestamoCapital | solicitadoPrestamoPlazo | solicitadoPrestamoTasa | politicaID | empresaID | acuerdoActual | paqueteActual | sucursalID | rentaConyuge |
      #      | "onboarding.voucher" | "UsuarioMonotributo"              | "3"          | "1"        | "0"                   | "0"                  | "0"                    | "0"                       | "0"                     | "0"                    | "0"        | "1"       | "0"           | "0"           | "100"      | "0"          |
      | "onboarding.voucher" | "UsuarioRelacionDeDependencia3"   | "3"          | "1"        | "0"                   | "0"                  | "0"                    | "0"                       | "0"                     | "0"                    | "0"        | "1"       | "0"           | "0"           | "100"      | "0"          |
      | "onboarding.voucher" | "UsuarioJubilado2"                | "3"          | "1"        | "0"                   | "0"                  | "0"                    | "0"                       | "0"                     | "0"                    | "0"        | "1"       | "0"           | "0"           | "100"      | "0"          |
      | "onboarding.voucher" | "UsuarioRelacionLaboralCompuesta" | "3"          | "1"        | "0"                   | "0"                  | "0"                    | "0"                       | "0"                     | "0"                    | "0"        | "1"       | "0"           | "0"           | "100"      | "0"          |

  @Regression @LimiteTarjetas @OfertaCrediticia
  Scenario Outline: Validar que el limite de tarjeta de Oferta Crediticia sea el mismo que aparece en la pagina de Oferta
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When obtengo la llamada al servicio de oferta crediticia para el prospect que tiene <oferta>
    Then valido que el limite de la tarjeta obtenida de Oferta Crediticia sea el mismo que aparece en la pagina de Oferta
    And valido que los tags de PaqueteUtilizado y paqueteID de Oferta Crediticia sean los mismos

    Examples: 
      | producto             | oferta                  |
      | "onboarding.voucher" | "OfertaMaster+Voucher2" |
