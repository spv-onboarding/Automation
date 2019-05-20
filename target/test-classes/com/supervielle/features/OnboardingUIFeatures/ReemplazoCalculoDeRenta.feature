@Browser @RolPorNosis
Feature: Reemplazo de sistema calculo de Renta

  @RolSinActividadLaboral @Regression
  Scenario Outline: Validar rol por Nosis para un prospect que no tenga una actividad laboral declarada
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    When obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <oferta>
    And valido el response de Nosis no tenga Item <item> y clave <clave>
    Then la pagina muestra el mensaje <mensaje> correspondiente a prospect sin oferta

    Examples: 
      | producto             | oferta                         | mensaje                                                              | item | clave |
      | "onboarding.voucher" | "SinActividadLaboralDeclarada" | "Lamentablemente no tenemos una oferta para vos mediante este canal" | "14" | "NSE" |

  @RolProvinciaNoOperativa @Regression
  Scenario Outline: Validar rol por Nosis para un prospect que tenga una Provincia no operativa
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    When obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <oferta>
    And valido el response de Nosis tenga Item <item> y clave <clave>
    And valido el response del servicio que obtiene los antecedentes comerciales el tag SinActividad sea <sinActividad>
    And valido el response del servicio que obtiene los antecedentes comerciales informe el valor de NSE
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    And completo los datos personales del prospect que tiene <oferta>
    And completo los datos de direccion valida del prospect que tiene <oferta>
    Then valido que en dropdown de provincia no contenga a la provincia del response del servicio que obtiene los antecedentes comerciales
    And valido que el dropdown de la UI de Provincia sean los mismos que en la tabla Provincias Operativas de la base de datos
    And completo la informacion laboral en el formulario de alta
    And selecciono la opcion para aceptar los datos del formulario de alta
    And selecciono una sucursal de supervielle en el mapa
    And selecciono la direccion por defecto para la entrega de la documentacion
    And confirmo la seleccion de la sucursal
    And acepto los terminos y condiciones
    And confirmo la seleccion de la tarjeta
    And valido la pantalla de comercios adheridos segun el <producto> y la <oferta>
    Then la aplicacion me muestra una pagina con un mensaje de que mi operacion fue exitosa

    Examples: 
      | producto             | oferta                        | item | clave | sinActividad |
      | "onboarding.voucher" | "UsuarioProvinciaNoOperativa" | "14" | "NSE" | "NO"         |

  @RolMonotributo @Regression
  Scenario Outline: Validar rol por Nosis para un prospect que sea monotributista
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    When obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <oferta>
    And valido el response de Nosis tenga Item <item> y clave <clave>
    And valido el response del servicio que obtiene los antecedentes comerciales el tag SinActividad sea <sinActividad>
    And valido el response del servicio que obtiene los antecedentes comerciales el tag Aut_Monotrib sea <monotributo>
    And valido el response del servicio que obtiene los antecedentes comerciales el tag compromisoOtrosBancos no sea vacio
    And obtengo la llamada al servicio de oferta crediticia para el prospect que tiene <oferta>
    And la request de la llamada a oferta crediticia tiene un valor de ActividadID valido <actividadID>
    And valido para mi oferta <oferta> el request de OfertaCrediticia los valores de ActividadID, RentaTitular, Compromiso otros bancos, PoliticaID, Edad y ProvinciaID coincida con los datos de CLI_DatosAnaliticos
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    And valido que fecha nacimiento, dni y genero esten precargados tomados del servicio que obtiene los antecedentes comerciales
    And completo los datos personales del prospect que tiene <oferta>
    And para mi oferta <oferta> valido que la direccion este precargada si vive en una provincia operativa tomados del servicio que obtiene los antecedentes comerciales
    And valido que la relacion laboral este precargada con <relacionLaboral>
    And selecciono una opcion negativa en la condicion PEP
    And selecciono la opcion para aceptar los datos del formulario de alta
    And selecciono una sucursal de supervielle en el mapa
    And selecciono la direccion por defecto para la entrega de la documentacion
    And confirmo la seleccion de la sucursal
    And acepto los terminos y condiciones
    And confirmo la seleccion de la tarjeta
    And valido la pantalla de comercios adheridos segun el <producto> y la <oferta>
    Then la aplicacion me muestra una pagina con un mensaje de que mi operacion fue exitosa

    Examples: 
      | producto             | oferta               | item | clave | sinActividad | monotributo | actividadID | relacionLaboral        |
      | "onboarding.voucher" | "UsuarioMonotributo" | "14" | "NSE" | "NO"         | "Si"        | "1"         | "RELACION_MONOTRIBUTO" |

  @RolJubilado @Regression
  Scenario Outline: Validar rol por Nosis para un prospect que sea jubilado
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    When obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <oferta>
    And valido el response de Nosis tenga Item <item> y clave <clave>
    And valido el response del servicio que obtiene los antecedentes comerciales el tag SinActividad sea <sinActividad>
    And valido el response del servicio que obtiene los antecedentes comerciales el tag Aut_Monotrib sea <monotributo>
    And valido el response del servicio que obtiene los antecedentes comerciales el tag jubilado sea <jubilado>
    And valido el response del servicio que obtiene los antecedentes comerciales el tag compromisoOtrosBancos no sea vacio
    And obtengo la llamada al servicio de oferta crediticia para el prospect que tiene <oferta>
    And la request de la llamada a oferta crediticia tiene un valor de ActividadID valido <actividadID>
    And valido para mi oferta <oferta> el request de OfertaCrediticia los valores de ActividadID, RentaTitular, Compromiso otros bancos, PoliticaID, Edad y ProvinciaID coincida con los datos de CLI_DatosAnaliticos
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    And valido que fecha nacimiento, dni y genero esten precargados tomados del servicio que obtiene los antecedentes comerciales
    And completo los datos personales del prospect que tiene <oferta>
    And para mi oferta <oferta> valido que la direccion este precargada si vive en una provincia operativa tomados del servicio que obtiene los antecedentes comerciales
    And valido que la relacion laboral este precargada con <relacionLaboral>
    And selecciono una opcion negativa en la condicion PEP
    And selecciono la opcion para aceptar los datos del formulario de alta
    And selecciono una sucursal de supervielle en el mapa
    And selecciono la direccion por defecto para la entrega de la documentacion
    And confirmo la seleccion de la sucursal
    And acepto los terminos y condiciones
    And confirmo la seleccion de la tarjeta
    And valido la pantalla de comercios adheridos segun el <producto> y la <oferta>
    Then la aplicacion me muestra una pagina con un mensaje de que mi operacion fue exitosa

    Examples: 
      | producto             | oferta             | item | clave | sinActividad | monotributo | actividadID | relacionLaboral                | jubilado |
      | "onboarding.voucher" | "UsuarioJubilado3" | "14" | "NSE" | "NO"         | "Si"        | "1"         | "RELACION_JUBILADO_PENSIONADO" | "si"     |

  @RolRelacionDependencia @Regression
  Scenario Outline: Validar rol por Nosis para un prospect que tenga relacion de dependencia
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    When obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <oferta>
    And valido el response de Nosis tenga Item <item> y clave <clave>
    And valido el response del servicio que obtiene los antecedentes comerciales el tag SinActividad sea <sinActividad>
    And valido el response del servicio que obtiene los antecedentes comerciales el tag relacion de dependencia sea <relacionDependencia>
    And valido el response del servicio que obtiene los antecedentes comerciales el tag compromisoOtrosBancos no sea vacio
    And obtengo la llamada al servicio de oferta crediticia para el prospect que tiene <oferta>
    And la request de la llamada a oferta crediticia tiene un valor de ActividadID valido <actividadID>
    And valido para mi oferta <oferta> el request de OfertaCrediticia los valores de ActividadID, RentaTitular, Compromiso otros bancos, PoliticaID, Edad y ProvinciaID coincida con los datos de CLI_DatosAnaliticos
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    And valido que fecha nacimiento, dni y genero esten precargados tomados del servicio que obtiene los antecedentes comerciales
    And completo los datos personales del prospect que tiene <oferta>
    And para mi oferta <oferta> valido que la direccion este precargada si vive en una provincia operativa tomados del servicio que obtiene los antecedentes comerciales
    And valido que la relacion laboral este precargada con <relacionLaboral>
    And selecciono una opcion negativa en la condicion PEP
    And selecciono la opcion para aceptar los datos del formulario de alta
    And selecciono una sucursal de supervielle en el mapa
    And selecciono la direccion por defecto para la entrega de la documentacion
    And confirmo la seleccion de la sucursal
    And acepto los terminos y condiciones
    And confirmo la seleccion de la tarjeta
    And valido la pantalla de comercios adheridos segun el <producto> y la <oferta>
    Then la aplicacion me muestra una pagina con un mensaje de que mi operacion fue exitosa

    Examples: 
      | producto             | oferta                          | item | clave | sinActividad | actividadID | relacionLaboral        | relacionDependencia |
      | "onboarding.voucher" | "UsuarioRelacionDeDependencia5" | "14" | "NSE" | "NO"         | "1"         | "RELACION_DEPENDENCIA" | "si"                |

  @RolRelacionLaboralCompuesta @Regression
  Scenario Outline: Validar rol por Nosis para un prospect que tenga relacion de dependencia y sea monotributista
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    When obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <oferta>
    And valido el response de Nosis tenga Item <item> y clave <clave>
    And valido el response del servicio que obtiene los antecedentes comerciales el tag SinActividad sea <sinActividad>
    And valido el response del servicio que obtiene los antecedentes comerciales el tag relacion de dependencia sea <relacionDependencia>
    And valido el response del servicio que obtiene los antecedentes comerciales el tag relacion de dependencia sea <relacionMonotributo>
    And valido el response del servicio que obtiene los antecedentes comerciales el tag compromisoOtrosBancos no sea vacio
    And obtengo la llamada al servicio de oferta crediticia para el prospect que tiene <oferta>
    And la request de la llamada a oferta crediticia tiene un valor de ActividadID valido <actividadID>
    And valido para mi oferta <oferta> el request de OfertaCrediticia los valores de ActividadID, RentaTitular, Compromiso otros bancos, PoliticaID, Edad y ProvinciaID coincida con los datos de CLI_DatosAnaliticos
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    And valido que fecha nacimiento, dni y genero esten precargados tomados del servicio que obtiene los antecedentes comerciales
    And completo los datos personales del prospect que tiene <oferta>
    And para mi oferta <oferta> valido que la direccion este precargada si vive en una provincia operativa tomados del servicio que obtiene los antecedentes comerciales
    And valido que la relacion laboral este precargada con <relacionLaboral>
    And selecciono una opcion negativa en la condicion PEP
    And selecciono la opcion para aceptar los datos del formulario de alta
    And selecciono una sucursal de supervielle en el mapa
    And selecciono la direccion por defecto para la entrega de la documentacion
    And confirmo la seleccion de la sucursal
    And acepto los terminos y condiciones
    And confirmo la seleccion de la tarjeta
    And valido la pantalla de comercios adheridos segun el <producto> y la <oferta>
    Then la aplicacion me muestra una pagina con un mensaje de que mi operacion fue exitosa

    Examples: 
      | producto             | oferta                             | item | clave | sinActividad | actividadID | relacionLaboral        | relacionDependencia | relacionMonotributo |
      | "onboarding.voucher" | "UsuarioRelacionLaboralCompuesta2" | "14" | "NSE" | "NO"         | "1"         | "RELACION_DEPENDENCIA" | "si"                | "si"                |
