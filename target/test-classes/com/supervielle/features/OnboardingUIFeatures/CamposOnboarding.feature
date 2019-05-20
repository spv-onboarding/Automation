@Browser @Campos
Feature: Campos de datos en la pagina de inicio de Onboarding

  @Regression
  Scenario Outline: Verificar validacion del campo telefónico con datos validos
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    When ingreso numero telefonico valido con <cantAreaDigitos> y para numero de celular <cantDigitoCelular>
    And valido cantidad maxima de digitos del codigo de area <maxCodArea> y de numero de celular <maxNumCelular>
    And valido que la suma de los digitos de codigo de area y numero de celular sea <sumaDigitos>
    Then la pagina no muestra ningun mensaje de error

    Examples: 
      | producto             | maxCodArea | maxNumCelular | cantAreaDigitos | cantDigitoCelular | sumaDigitos |
      | "onboarding.voucher" |          4 |             6 |               4 |                 6 |          10 |
      | "onboarding.voucher" |          4 |             6 |              10 |                10 |          10 |
      | "onboarding.tarjeta" |          4 |             6 |               4 |                 6 |          10 |

  @Regression
  Scenario Outline: Verificar validacion del campo telefónico con datos NO validos
    Given me encuentro en la pagina de inicio de Onboarding de <producto>
    And selecciono la opcion disponible para hacerme cliente
    When ingreso numero telefonico erroneo con <cantAreaDigitos> y con <cantDigitoCelular>
    Then la pagina me muestra un mensaje de error de validacion para el numero de telefono

    Examples: 
      | producto             | cantAreaDigitos | cantDigitoCelular | sumaDigitos |
      | "onboarding.voucher" |               2 |                 4 |          10 |
      | "onboarding.voucher" |               2 |                 3 |          10 |
      | "onboarding.tarjeta" |               2 |                 4 |          10 |
