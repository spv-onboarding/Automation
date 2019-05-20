@Browser @IdentificacionRelacionLaboral
Feature: Identificacion de Relacion Laboral

  @Regression @OBIdentificacionRelacionLaboral
  Scenario Outline: Validar que en la tabla CLI-Datoslaborales el campo RelacionLaboralID coincida la relacion laboral del CUIL ingresado desde Onboarding
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    When ingreso datos de un prospect que tiene <oferta>
    Then valido que relacion laboral para mi <oferta> sea <relacionLaboralID>

    Examples: 
      | producto             | oferta                          | relacionLaboralID |
      | "onboarding.voucher" | "UsuarioRelacionDeDependencia2" |                 1 |
      | "onboarding.voucher" | "UsuarioMonotributo"						 |                 2 |
      | "onboarding.voucher" | "UsuarioJubilado"               |                 4 |
      | "onboarding.voucher" | "UsuarioAutonomo"               |                 6 |


  @Regression @AgentesIdentificacionRelacionLaboral
  Scenario Outline: Validar que en la tabla CLI-Datoslaborales el campo RelacionLaboralID coincida la relacion laboral del CUIL ingresado desde Agentes
    Given me encuentro en la pagina de inicio de Agentes de <producto>
    When me logueo con las credenciales de Agente Externo <credenciales>
    And completo los datos de un prospect que tiene <oferta> para descubrir oferta en agentes
    And selecciono la opcion disponible para descubrir oferta en agentes
    Then valido que relacion laboral para mi <oferta> sea <relacionLaboralID>

    Examples: 
      | producto  | credenciales     | relacionLaboralID | oferta                          |
      | "agentes" | "UsuarioAgentes" |                 1 | "UsuarioRelacionDeDependencia2" |
      #      | "agentes" | "UsuarioAgentes" |                 2 | "UsuarioMonotributo"           |
      | "agentes" | "UsuarioAgentes" |                 4 | "UsuarioJubilado"               |
      | "agentes" | "UsuarioAgentes" |                 6 | "UsuarioAutonomo"               |
