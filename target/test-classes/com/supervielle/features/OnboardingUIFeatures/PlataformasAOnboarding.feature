@Browser @Plataformas
Feature: Redireccionamiento a Onboarding desde Plataformas

  @Smoke
  Scenario Outline: Verificar redireccionamiento desde Plataformas a Onboarding con datos autocompletados
    Given me encuentro en la pagina de inicio de plataformas
    And selecciono un <tipoDocumento>
    And ingreso el <numeroDocumento>
    And selecciono la <nacionalidad>
    And selecciono el <genero>
    When selecciono la opcion descubrir
    Then la aplicacion me redirecciona al inicio de Onboarding con los datos autocompletados de <numeroDocumento> y <genero>

    Examples: 
      | tipoDocumento | numeroDocumento | nacionalidad           | genero      |
      | "C.U.I.L."    | "20340000006"   | "ARGENTINA, REPUBLICA" | "Masculino" |
      | "D.N.I."      | "34000000"      | "ARGENTINA, REPUBLICA" | "Femenino"  |
