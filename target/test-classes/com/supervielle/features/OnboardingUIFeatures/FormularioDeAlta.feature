@Browser @FormularioAlta
Feature: Formulario de Alta

  @Regression @AutocompletadoDatosPersonales
  Scenario Outline: Validar precarga de datos personales en formulario de alta
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    Then el bloque de datos del cliente corresponde a los obtenidos del servicio que obtiene los antecedentes comerciales para el prospect
    Then la seccion de datos personales aparece precargada con los datos validos
    And en datos personales el campo telefono aparece autocompletado segun el prospect cargado que tiene <oferta>
    And la pagina no me permite el acceso a la carga de domicilio
    And la pagina no me permite el acceso a la carga de informacion laboral

    Examples: 
      | producto             | oferta                            |
      | "onboarding.voucher" | "Oferta+Voucher+Gold+TarjetaVisa" |

  @Regression @DatosPersonalesValidos
  Scenario Outline: Validar acceso a carga de datos de domicilio luego de ingresar datos personales validos en formulario de alta
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    And completo los datos personales del prospect que tiene <oferta>
    And cuando confirmo mis datos personales y avanzo a la seccion de domicilio
    Then la pagina me permite el acceso a la carga de domicilio
    And la pagina no me permite el acceso a la carga de informacion laboral

    Examples: 
      | producto             | oferta                            |
      | "onboarding.voucher" | "Oferta+Voucher+Gold+TarjetaVisa" |

  @Regression @DatosPersonalesNoValidos
  Scenario Outline: Validar el bloqueo de acceso a carga de datos de domicilio luego de ingresar datos personales no validos en formulario de alta
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    And completo los datos personales del prospect que tiene <oferta>
    And borro los datos del campo de numero de celular
    And cuando confirmo mis datos personales y avanzo a la seccion de domicilio
    Then la pagina me muestra un mensaje de error de numero de telefono no valido
    And la pagina no me permite el acceso a la carga de domicilio
    And la pagina no me permite el acceso a la carga de informacion laboral

    Examples: 
      | producto             | oferta                            |
      | "onboarding.voucher" | "Oferta+Voucher+Gold+TarjetaVisa" |

  @Regression @AutocompletadoDatosDomicilio
  Scenario Outline: Validar precarga de datos de domicilio en formulario de alta
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    And completo los datos personales del prospect que tiene <oferta>
    Then para mi oferta <oferta> valido que la direccion este precargada si vive en una provincia operativa tomados del servicio que obtiene los antecedentes comerciales
    And la pagina no me permite el acceso a la carga de informacion laboral

    Examples: 
      | producto             | oferta                            |
      | "onboarding.voucher" | "Oferta+Voucher+Gold+TarjetaVisa" |

  @Regression @DireccionValida
  Scenario Outline: Validar acceso a carga informacion laboral luego de ingresar una direccion valida en formulario de alta
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    And completo los datos personales del prospect que tiene <oferta>
    And completo los datos de direccion valida del prospect que tiene <oferta>
    And hago click en el boton continuar para pasar a la seccion de informacion laboral
    Then la pagina me permite el acceso a la carga de informacion laboral

    Examples: 
      | producto             | oferta                            |
      | "onboarding.voucher" | "Oferta+Voucher+Gold+TarjetaVisa" |

  @Regression @DireccionNoValida
  Scenario Outline: Validar mensaje de error luego de ingresar una direccion no valida en formulario de alta
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    And completo los datos personales del prospect que tiene <oferta>
    And completo los datos de direccion valida del prospect que tiene <oferta>
    Then cada vez que ingrese una direccion no valida de <calle> y haga click en el boton continuar, la pagina me muestra un mensaje de error de direccion no valida, negandome el acceso a la carga de informacion laboral

    Examples: 
      | producto             | oferta                            | calle        |
      | "onboarding.voucher" | "Oferta+Voucher+Gold+TarjetaVisa" | "!,?/A..A21" |

  @Regression @AutocompletadoInformacionLaboral
  Scenario Outline: Validar precarga de datos de informacion laboral en formulario de alta
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    And completo los datos personales del prospect que tiene <oferta>
    And completo los datos de direccion valida del prospect que tiene <oferta>
    Then valido que la relacion laboral este precargada con <relacionLaboral>

    Examples: 
      | producto             | oferta                         | relacionLaboral        |
      | "onboarding.voucher" | "UsuarioRelacionDeDependencia" | "RELACION_DEPENDENCIA" |

  @Regression @InformacionLaboralCompleta
  Scenario Outline: Validar el acceso a la seleccion de sucursales luego de cargar la informacion laboral en formulario de alta
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    And completo los datos personales del prospect que tiene <oferta>
    And completo los datos de direccion valida del prospect que tiene <oferta>
    And valido que la relacion laboral este precargada con <relacionLaboral>
    And selecciono una opcion negativa en la condicion PEP
    And selecciono la opcion para aceptar los datos del formulario de alta
    Then la pagina me permite el acceso a la seleccion de sucursal

    Examples: 
      | producto             | oferta                         | relacionLaboral        |
      | "onboarding.voucher" | "UsuarioRelacionDeDependencia" | "RELACION_DEPENDENCIA" |

  @Regression @InformacionLaboralIncompleta
  Scenario Outline: Validar el bloqueo de acceso a la seleccion de sucursales luego de saltear la seleccion de una opcion PEP en formulario de alta
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    And completo los datos personales del prospect que tiene <oferta>
    And completo los datos de direccion valida del prospect que tiene <oferta>
    And valido que la relacion laboral este precargada con <relacionLaboral>
    And selecciono la opcion para aceptar los datos del formulario de alta
    Then la pagina me muestra un mensaje de error de que tengo seleccionar una opcion de PEP
    And la pagina no me permite el acceso a la seleccion de sucursal

    Examples: 
      | producto             | oferta                         | relacionLaboral        |
      | "onboarding.voucher" | "UsuarioRelacionDeDependencia" | "RELACION_DEPENDENCIA" |

  @Regression @DatosConyuge
  Scenario Outline: Validar correspondencia de datos de conguye en la base datos
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And valido que los campos de datos personales estan visibles
    And ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    And completo los datos personales del prospect que tiene <oferta>
    And selecciono para mi <oferta> estado civil casado y completo nombre <nombreConyuge> apellido <apellidoConyuge> pais nacimiento <paisNacimientoConyuge> y DNI <dni>
    And completo los datos de direccion valida del prospect que tiene <oferta>
    And completo la informacion laboral en el formulario de alta
    And selecciono la opcion para aceptar los datos del formulario de alta
    Then valido para mi <oferta> que nombre <nombreConyuge> apellido <apellidoConyuge> y DNI <dni> de los campos de conyuge aparezcan en la base de datos

    Examples: 
      | producto             | oferta                            | nombreConyuge | apellidoConyuge | dni      | paisNacimientoConyuge  |
      | "onboarding.voucher" | "Oferta+Voucher+Gold+TarjetaVisa" | "Mariana"     | "Ramirez"       | 30562987 | "ARGENTINA, REPUBLICA" |

  @Regression @DatosFiliatorios
  Scenario Outline: Validar correspondencia de datos filiatorios para un prospect en la base de datos
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    And ingreso datos de un prospect que tiene <oferta>
    And respondo las preguntas de seguridad en base al prospect que tiene <oferta>
    And ingreso codigo de seguridad del email del prospect que tiene <oferta>
    When solicito tarjeta
    And completo los datos personales, de direccion e informacion laboral del prospect que tiene <oferta>
    Then valido para mi <oferta> aparezca en datos filiatorios de la base de datos despues de aceptar los datos en el formulario de alta
    And valido que mis datos <oferta> de Domicilio en base de datos

    Examples: 
      | producto             | oferta                            |
      | "onboarding.voucher" | "Oferta+Voucher+Gold+TarjetaVisa" |
