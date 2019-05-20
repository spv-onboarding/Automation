$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("AltaProspect.feature");
formatter.feature({
  "line": 2,
  "name": "Alta de un prospect en Onboarding",
  "description": "",
  "id": "alta-de-un-prospect-en-onboarding",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Browser"
    },
    {
      "line": 1,
      "name": "@Alta"
    }
  ]
});
formatter.scenarioOutline({
  "line": 5,
  "name": "Validar alta de prospect entrando a Onboarding desde distintos tipos de producto",
  "description": "",
  "id": "alta-de-un-prospect-en-onboarding;validar-alta-de-prospect-entrando-a-onboarding-desde-distintos-tipos-de-producto",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 4,
      "name": "@Regression"
    },
    {
      "line": 4,
      "name": "@AltaTipoProducto"
    },
    {
      "line": 4,
      "name": "@DEMO200"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "me encuentro en la pagina de inicio de Onboarding de \u003cproducto\u003e",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "selecciono la opcion disponible para hacerme cliente",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "ingreso datos de un prospect que tiene \u003coferta\u003e",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "respondo las preguntas de seguridad en base al prospect que tiene \u003coferta\u003e",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "ingreso codigo de seguridad del email del prospect que tiene \u003coferta\u003e",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "solicito tarjeta",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "verifico que en la barra de navegacion este activo el step de Oferta",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "completo los datos personales, de direccion e informacion laboral del prospect que tiene \u003coferta\u003e",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "selecciono la opcion para aceptar los datos del formulario de alta",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "verifico que en la barra de navegacion este activo el step de Seleccion de Sucursal",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "selecciono una sucursal de supervielle en el mapa",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "selecciono la direccion por defecto para la entrega de la documentacion",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "confirmo la seleccion de la sucursal",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "verifico que en la barra de navegacion este activo el step de Confirmacion de producto",
  "keyword": "And "
});
formatter.step({
  "line": 20,
  "name": "acepto los terminos y condiciones",
  "keyword": "And "
});
formatter.step({
  "line": 21,
  "name": "confirmo la seleccion de la tarjeta",
  "keyword": "And "
});
formatter.step({
  "line": 22,
  "name": "obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene \u003coferta\u003e",
  "keyword": "And "
});
formatter.step({
  "line": 23,
  "name": "obtengo la llamada al servicio de alta Persona para el prospect que tiene \u003coferta\u003e",
  "keyword": "And "
});
formatter.step({
  "line": 24,
  "name": "obtengo la llamada al servicio de alta de \u003cproducto\u003e para el prospect que tiene \u003coferta\u003e",
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "valido la pantalla de comercios adheridos segun el \u003cproducto\u003e y la \u003coferta\u003e",
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "la aplicacion me muestra una pagina con un mensaje de que mi operacion fue exitosa",
  "keyword": "Then "
});
formatter.step({
  "line": 27,
  "name": "la request del servicio alta Persona para el prospect que tiene \u003coferta\u003e tiene parametros validos",
  "keyword": "And "
});
formatter.step({
  "line": 28,
  "name": "la response del servicio alta Persona para el prospect describe una operacion exitosa",
  "keyword": "And "
});
formatter.step({
  "line": 29,
  "name": "el servicio de alta de tarjeta para prospect que tiene \u003coferta\u003e corresponde con el \u003cproducto\u003e",
  "keyword": "And "
});
formatter.step({
  "line": 30,
  "name": "el servicio de alta de \u003cproducto\u003e responde con codigo de exito",
  "keyword": "And "
});
formatter.examples({
  "line": 32,
  "name": "",
  "description": "",
  "id": "alta-de-un-prospect-en-onboarding;validar-alta-de-prospect-entrando-a-onboarding-desde-distintos-tipos-de-producto;",
  "rows": [
    {
      "cells": [
        "producto",
        "oferta"
      ],
      "line": 33,
      "id": "alta-de-un-prospect-en-onboarding;validar-alta-de-prospect-entrando-a-onboarding-desde-distintos-tipos-de-producto;;1"
    },
    {
      "cells": [
        "\"onboarding.voucher\"",
        "\"OfertaMaster+Voucher\""
      ],
      "line": 34,
      "id": "alta-de-un-prospect-en-onboarding;validar-alta-de-prospect-entrando-a-onboarding-desde-distintos-tipos-de-producto;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 5865489900,
  "status": "passed"
});
formatter.scenario({
  "line": 34,
  "name": "Validar alta de prospect entrando a Onboarding desde distintos tipos de producto",
  "description": "",
  "id": "alta-de-un-prospect-en-onboarding;validar-alta-de-prospect-entrando-a-onboarding-desde-distintos-tipos-de-producto;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 4,
      "name": "@Regression"
    },
    {
      "line": 1,
      "name": "@Browser"
    },
    {
      "line": 4,
      "name": "@DEMO200"
    },
    {
      "line": 1,
      "name": "@Alta"
    },
    {
      "line": 4,
      "name": "@AltaTipoProducto"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "me encuentro en la pagina de inicio de Onboarding de \"onboarding.voucher\"",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "selecciono la opcion disponible para hacerme cliente",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "ingreso datos de un prospect que tiene \"OfertaMaster+Voucher\"",
  "matchedColumns": [
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "respondo las preguntas de seguridad en base al prospect que tiene \"OfertaMaster+Voucher\"",
  "matchedColumns": [
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "ingreso codigo de seguridad del email del prospect que tiene \"OfertaMaster+Voucher\"",
  "matchedColumns": [
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "solicito tarjeta",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "verifico que en la barra de navegacion este activo el step de Oferta",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "completo los datos personales, de direccion e informacion laboral del prospect que tiene \"OfertaMaster+Voucher\"",
  "matchedColumns": [
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "selecciono la opcion para aceptar los datos del formulario de alta",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "verifico que en la barra de navegacion este activo el step de Seleccion de Sucursal",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "selecciono una sucursal de supervielle en el mapa",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "selecciono la direccion por defecto para la entrega de la documentacion",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "confirmo la seleccion de la sucursal",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "verifico que en la barra de navegacion este activo el step de Confirmacion de producto",
  "keyword": "And "
});
formatter.step({
  "line": 20,
  "name": "acepto los terminos y condiciones",
  "keyword": "And "
});
formatter.step({
  "line": 21,
  "name": "confirmo la seleccion de la tarjeta",
  "keyword": "And "
});
formatter.step({
  "line": 22,
  "name": "obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene \"OfertaMaster+Voucher\"",
  "matchedColumns": [
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 23,
  "name": "obtengo la llamada al servicio de alta Persona para el prospect que tiene \"OfertaMaster+Voucher\"",
  "matchedColumns": [
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 24,
  "name": "obtengo la llamada al servicio de alta de \"onboarding.voucher\" para el prospect que tiene \"OfertaMaster+Voucher\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "valido la pantalla de comercios adheridos segun el \"onboarding.voucher\" y la \"OfertaMaster+Voucher\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "la aplicacion me muestra una pagina con un mensaje de que mi operacion fue exitosa",
  "keyword": "Then "
});
formatter.step({
  "line": 27,
  "name": "la request del servicio alta Persona para el prospect que tiene \"OfertaMaster+Voucher\" tiene parametros validos",
  "matchedColumns": [
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 28,
  "name": "la response del servicio alta Persona para el prospect describe una operacion exitosa",
  "keyword": "And "
});
formatter.step({
  "line": 29,
  "name": "el servicio de alta de tarjeta para prospect que tiene \"OfertaMaster+Voucher\" corresponde con el \"onboarding.voucher\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 30,
  "name": "el servicio de alta de \"onboarding.voucher\" responde con codigo de exito",
  "matchedColumns": [
    0
  ],
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "onboarding.voucher",
      "offset": 54
    }
  ],
  "location": "OnboardingUIDefinitions.initializeOnboardingHomePage(String)"
});
formatter.result({
  "duration": 2925516600,
  "status": "passed"
});
formatter.match({
  "location": "OnboardingUIDefinitions.gotToClientRegistrationPage()"
});
formatter.result({
  "duration": 30648624300,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "OfertaMaster+Voucher",
      "offset": 40
    }
  ],
  "location": "OnboardingUIDefinitions.fillProspectDataOnOnboardingPersonalDataForm(String)"
});
formatter.result({
  "duration": 33370942400,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "OfertaMaster+Voucher",
      "offset": 67
    }
  ],
  "location": "OnboardingUIDefinitions.answerSecurityQuestions(String)"
});
formatter.result({
  "duration": 25984308100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "OfertaMaster+Voucher",
      "offset": 62
    }
  ],
  "location": "OnboardingUIDefinitions.fillValidationCode(String)"
});
formatter.result({
  "duration": 40940534100,
  "status": "passed"
});
formatter.match({
  "location": "OnboardingUIDefinitions.clickOnButtonToRequestCard()"
});
formatter.result({
  "duration": 15971733900,
  "status": "passed"
});
formatter.match({
  "location": "OnboardingUIDefinitions.validateActiveStepInOfferPage()"
});
formatter.result({
  "duration": 225722499,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "OfertaMaster+Voucher",
      "offset": 90
    }
  ],
  "location": "OnboardingUIDefinitions.fillHolderDataInOfferingPage(String)"
});
formatter.result({
  "duration": 93215993399,
  "status": "passed"
});
formatter.match({
  "location": "OnboardingUIDefinitions.clickOnAcceptOption()"
});
formatter.result({
  "duration": 5396172901,
  "status": "passed"
});
formatter.match({
  "location": "OnboardingUIDefinitions.validateActiveStepInBranchSelectionPage()"
});
formatter.result({
  "duration": 4230089901,
  "status": "passed"
});
formatter.match({
  "location": "OnboardingUIDefinitions.selectASupervielleBranchOnMap()"
});
formatter.result({
  "duration": 67778138800,
  "status": "passed"
});
formatter.match({
  "location": "OnboardingUIDefinitions.selectDocumentationAddressByDefault()"
});
formatter.result({
  "duration": 5221634400,
  "status": "passed"
});
formatter.match({
  "location": "OnboardingUIDefinitions.confirmSupervielleBranchSelection()"
});
