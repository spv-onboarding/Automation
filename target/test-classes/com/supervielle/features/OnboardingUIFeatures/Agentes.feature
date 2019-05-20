@Browser @Agentes
Feature: Ingreso a Onboarding desde Agentes

  @Regression @Agentes @AgenteSinDeal
  Scenario Outline: Validar que cuando ingreso a Agentes con un usuario sin Deal, las ofertas esten deshabilitadas
    Given me encuentro en la pagina de inicio de Agentes de <agente>
    And me logueo con las credenciales de Agente Externo <credenciales>
    And completo los datos de un prospect que tiene <oferta> para descubrir oferta en agentes
    When selecciono la opcion disponible para descubrir oferta en agentes
    Then cada una de las ofertas disponibles que tenga el prospect aparecen deshabilitadas

    Examples: 
      | oferta                        | agente    | credenciales    |
      | "Oferta+Tarjeta+Voucher+Gold" | "agentes" | "AgenteSinDeal" |

  @Regression @OfertaCrediticia @SinOferta
  Scenario Outline: Validar que la oferta del producto este deshabilitada en Agentes cuando el servicio de oferta crediticia responda con datos invalidos de Normalizacion de Tarjeta
    Given me encuentro en la pagina de inicio de Agentes de <agente>
    And me logueo con las credenciales de Agente Externo <credenciales>
    And completo los datos de un prospect que tiene <estado> para descubrir oferta en agentes
    When selecciono la opcion disponible para descubrir oferta en agentes
    And obtengo la llamada al servicio de oferta crediticia para el prospect que tiene <estado>
    Then si la response de oferta crediticia informa de que no hay oferta para el <producto> la oferta aparece deshabilitada en Agentes

    Examples: 
      | producto     | agente    | credenciales     | estado                |
      | "Mastercard" | "agentes" | "UsuarioAgentes" | "SinOfertaCrediticia" |

  @Regression @LimiteTarjetas @OfertaCrediticia
  Scenario Outline: Validar Límite Tarjetas para Paquete de un prospect desde Agentes
    Given me encuentro en la pagina de inicio de Agentes de <agente>
    And me logueo con las credenciales de Agente Externo <credenciales>
    And completo los datos de un prospect que tiene <oferta> para descubrir oferta en agentes
    When selecciono la opcion disponible para descubrir oferta en agentes
    And obtengo la llamada al servicio de oferta crediticia para el prospect que tiene <oferta>
    Then valido que el limite de tarjeta en Oferta Crediticia coincida con Oferta de Agentes

    Examples: 
      | oferta                        | agente    | credenciales     |
      | "Oferta+Tarjeta+Voucher+Gold" | "agentes" | "UsuarioAgentes" |

  @Regression @ValidacionIdentidad
  Scenario Outline: Validar que que el paso de validacion de identidad aparezca correctamente y fluya sin errores en Onboarding ingresando desde Agentes
    Given me encuentro en la pagina de inicio de Agentes de <agente>
    And me logueo con las credenciales de Agente Externo <credenciales>
    And completo los datos de un prospect que tiene <oferta> para descubrir oferta en agentes
    And selecciono la opcion disponible para descubrir oferta en agentes
    And selecciono una <ofertaDisponible> de las que tenga disponible el prospect
    And solicito tarjeta
    And completo los datos personales, de direccion e informacion laboral del prospect que tiene <oferta>
    And selecciono la opcion para aceptar los datos del formulario de alta
    And selecciono una sucursal de supervielle en el mapa
    And selecciono la direccion por defecto para la entrega de la documentacion
    And confirmo la seleccion de la sucursal de un flujo desde agentes
    Then la seccion de preguntas de seguridad carga correctamente
    And la aplicacion me redirige a la pagina de confirmacion de solicitud de tarjeta despues de responder las preguntas de seguridad para el prospect que tiene <oferta>

    Examples: 
      | producto             | oferta                        | agente    | credenciales                     | ofertaDisponible            |
      | "onboarding.voucher" | "Oferta+Tarjeta+Voucher+Gold" | "agentes" | "AgenteConValidacionDeIdentidad" | "Mastercard Gold + Voucher" |

  @Regression @Alta @BarraNavegacion
  Scenario Outline: Validar que en la barra de navegacion se active el paso correspondiente a medida que el usuario avance en Onboarding
    Given me encuentro en la pagina de inicio de Agentes de <agente>
    And me logueo con las credenciales de Agente Externo <credenciales>
    And completo los datos de un prospect que tiene <oferta> para descubrir oferta en agentes
    And selecciono la opcion disponible para descubrir oferta en agentes
    And selecciono una <ofertaDisponible> de las que tenga disponible el prospect
    And verifico que en la barra de navegacion este activo el step de Oferta
    And solicito tarjeta
    And completo los datos personales, de direccion e informacion laboral del prospect que tiene <oferta>
    And selecciono la opcion para aceptar los datos del formulario de alta
    And verifico que en la barra de navegacion este activo el step de Seleccion de Sucursal
    And selecciono una sucursal de supervielle en el mapa
    And selecciono la direccion por defecto para la entrega de la documentacion
    And confirmo la seleccion de la sucursal de un flujo desde agentes
    Then la seccion de preguntas de seguridad carga correctamente
    And verifico que en la barra de navegacion este activo el step de Validacion de Identidad
    And la aplicacion me redirige a la pagina de confirmacion de solicitud de tarjeta despues de responder las preguntas de seguridad para el prospect que tiene <oferta>
    And verifico que en la barra de navegacion este activo el step de Confirmacion de producto

    Examples: 
      | producto             | oferta                        | agente    | credenciales                     | ofertaDisponible            |
      | "onboarding.voucher" | "Oferta+Tarjeta+Voucher+Gold" | "agentes" | "AgenteConValidacionDeIdentidad" | "Mastercard Gold + Voucher" |


#12-03-2019 Feature para Barra Navegacion de Paquetes
  @Regression @Paquetes @Alta @BarraNavegacion
  Scenario Outline: Validar que en la barra de navegacion se active el paso correspondiente a medida que el usuario avance en Onboarding
    Given me encuentro en la pagina de inicio de Agentes de <agente>
    And me logueo con las credenciales de Agente Externo <credenciales>
    And completo los datos de un prospect que tiene <oferta> para descubrir oferta en agentes
    And selecciono la opcion disponible para descubrir oferta en agentes
    #HASTA ACÁ TODO IGUAL
    
    And selecciono una <ofertaDisponible> de las que tenga disponible el prospect
    And verifico que en la barra de navegacion este activo el step de Oferta
    And solicito tarjeta
    And completo los datos personales, de direccion e informacion laboral del prospect que tiene <oferta>
    And selecciono la opcion para aceptar los datos del formulario de alta
    And verifico que en la barra de navegacion este activo el step de Seleccion de Sucursal
    And selecciono una sucursal de supervielle en el mapa
    And selecciono la direccion por defecto para la entrega de la documentacion
    And confirmo la seleccion de la sucursal de un flujo desde agentes
    Then la seccion de preguntas de seguridad carga correctamente
    And verifico que en la barra de navegacion este activo el step de Validacion de Identidad
    And la aplicacion me redirige a la pagina de confirmacion de solicitud de tarjeta despues de responder las preguntas de seguridad para el prospect que tiene <oferta>
    And verifico que en la barra de navegacion este activo el step de Confirmacion de producto

    Examples: 
      | producto             | oferta                        									| agente    | credenciales                     | ofertaDisponible         				  					|
#     | "onboarding.voucher" | "OfertaIntegrada+Paquete+Liberte" 							| "agentes" | "AgenteConValidacionDeIdentidad" | "Mastercard Gold + Voucher" 									|
#     | "onboarding.voucher" | "OfertaIntegrada+Paquete+Liberte+Gold" 				| "agentes" | "AgenteConValidacionDeIdentidad" | "Mastercard Gold + Voucher" 									|
      | "onboarding.voucher" | "OfertaIntegrada+Paquete+Identite+Platinum" 		| "agentes" | "AgenteConValidacionDeIdentidad" | "Mastercard Platinum" 	|
#     | "onboarding.voucher" | "OfertaIntegrada+Paquete+Identite+Black" 			| "agentes" | "AgenteConValidacionDeIdentidad" | "Mastercard Gold + Voucher" 									|


  @Regression @Agentes @Alta @AltaVoucher
  Scenario Outline: Validar correspondencia de datos entre alta Tarjeta instantanea y Oferta crediticia durante alta de prospect ingresando desde Agentes
    Given me encuentro en la pagina de inicio de Agentes de <agente>
    And me logueo con las credenciales de Agente Externo <credenciales>
    And completo los datos de un prospect que tiene <oferta> para descubrir oferta en agentes
    And selecciono la opcion disponible para descubrir oferta en agentes
    And selecciono una <ofertaDisponible> de las que tenga disponible el prospect
    And solicito tarjeta
    And completo los datos personales, de direccion e informacion laboral del prospect que tiene <oferta>
    And selecciono la opcion para aceptar los datos del formulario de alta
    And selecciono una sucursal de supervielle en el mapa
    And selecciono la direccion por defecto para la entrega de la documentacion
    And confirmo la seleccion de la sucursal
    And confirmo la seleccion de la tarjeta
    And valido la pantalla de comercios adheridos segun el <producto> y la <oferta>
    And obtengo la llamada al servicio de oferta crediticia para el prospect que tiene <oferta>
    And obtengo la llamada al servicio de alta de <producto> para el prospect que tiene <oferta>
    Then la aplicacion me muestra una pagina con un mensaje de que mi operacion fue exitosa
    And el servicio de alta de tarjeta para prospect que tiene <oferta> corresponde con el <producto>
    And la request de alta de tarjeta tiene el valor de codigo, porcentaje, y limite correspondientes con el de oferta crediticia

    Examples: 
      | producto             | oferta                        | agente    | credenciales     | ofertaDisponible            |
      | "onboarding.voucher" | "Oferta+Tarjeta+Voucher+Gold" | "agentes" | "UsuarioAgentes" | "Mastercard Gold + Voucher" |
