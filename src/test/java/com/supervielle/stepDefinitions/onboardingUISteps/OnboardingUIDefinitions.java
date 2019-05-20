package com.supervielle.stepDefinitions.onboardingUISteps;

import static com.supervielle.backend.data.ClientDao.getCliIdFromCuil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.supervielle.backend.data.CalculoRentaDao;
import com.supervielle.backend.data.ClientDao;
import com.supervielle.backend.data.EmailDao;
import com.supervielle.backend.domain.DatoAnalitico;
import com.supervielle.backend.domain.DatoFiliatorio;
import com.supervielle.backend.domain.Domicilio;
import com.supervielle.backend.domain.EMPLOYMENT_RELATIONSHIP_TYPE;
import com.supervielle.backend.domain.Persona;
import com.supervielle.backend.domain.RegistrationServicesConstants;
import com.supervielle.backend.domain.RegistrationServicesConstants.BRAND_CODE;
import com.supervielle.backend.domain.RegistrationServicesConstants.CIVIL_STATUS_CODE;
import com.supervielle.backend.domain.RegistrationServicesConstants.DOCUMENT_TYPE;
import com.supervielle.backend.domain.RegistrationServicesConstants.ENTITY_NUMBER;
import com.supervielle.backend.domain.RegistrationServicesConstants.OPERATION_CODE;
import com.supervielle.backend.domain.RegistrationServicesConstants.PRODUCT_TYPE;
import com.supervielle.backend.domain.RegistrationServicesConstants.REGISTRATION_TYPE;
import com.supervielle.backend.domain.RegistrationServicesConstants.TRIBU_DOC_TYPE;
import com.supervielle.backend.domain.aceptacion_crediticia.AceptacionCrediticiaUtils;
import com.supervielle.backend.domain.aceptacion_crediticia.EstadoGeneral;
import com.supervielle.backend.domain.alta_adicional.AltaAdicionalUtils;
import com.supervielle.backend.domain.alta_adicional.ResponseAltaAdicional;
import com.supervielle.backend.domain.alta_adicional.request.Complementarios;
import com.supervielle.backend.domain.alta_adicional.request.Filiatorios;
import com.supervielle.backend.domain.alta_adicional.request.Principal;
import com.supervielle.backend.domain.alta_adicional.request.RequestAltaAdicional;
import com.supervielle.backend.domain.alta_persona.AltaPersonaUtils;
import com.supervielle.backend.domain.alta_persona.AltaPersonaUtils.EducationaLevel;
import com.supervielle.backend.domain.alta_persona.ResponseAltaPersona;
import com.supervielle.backend.domain.alta_persona.request.RequestAltaPersona;
import com.supervielle.backend.domain.alta_producto.AltaProductoUtils;
import com.supervielle.backend.domain.alta_producto.alta_tarjeta.AltaTarjetaUtils;
import com.supervielle.backend.domain.alta_producto.alta_tarjeta.ResponseAltaTitular;
import com.supervielle.backend.domain.alta_producto.alta_voucher.AltaVoucherUtils;
import com.supervielle.backend.domain.alta_producto.alta_voucher.ResponseAltaInstantaneaMaster;
import com.supervielle.backend.domain.alta_producto.alta_voucher.request.RegistroAltaBATCH;
import com.supervielle.backend.domain.alta_producto.alta_voucher.request.RequestAltaInstantaneaMaster;
import com.supervielle.backend.domain.calculo_renta.CalculoRentaUtils;
import com.supervielle.backend.domain.consulta_scoring_supervielle.ConsultaScoringSupervielleUtils;
import com.supervielle.backend.domain.cuestionario_veraz.ConsultaCuestionarioVerazUtils;
import com.supervielle.backend.domain.oferta_crediticia.OfertaCrediticiaUtils;
import com.supervielle.backend.domain.oferta_crediticia.ResponseOfertaCrediticia;
import com.supervielle.backend.domain.oferta_crediticia.Tarjeta;
import com.supervielle.backend.domain.preguntas_seguridad.PreguntasSeguridadUtils;
import com.supervielle.backend.domain.preguntas_seguridad.ResponsePreguntasSeguridad;
import com.supervielle.backend.domain.sacnosis.Dato;
import com.supervielle.backend.domain.sacnosis.SacnosisUtils;
import com.supervielle.backend.domain.veraz.VerazUtils;
import com.supervielle.constants.ClientRegistrationFormConstants;
import com.supervielle.framework.tdm.UserProvider;
import com.supervielle.framework.utils.AutomationProperties;
import com.supervielle.framework.utils.DateTimeUtils;
import com.supervielle.framework.utils.TextUtils;
import com.supervielle.framework.utils.WebDriverUtils;
import com.supervielle.pageobjects.AgentesHomePage;
import com.supervielle.pageobjects.AgentesOfertasPage;
import com.supervielle.pageobjects.OnboardingConfirmacionPage;
import com.supervielle.pageobjects.OnboardingOfertaPage;
import com.supervielle.pageobjects.OnboardingPage;
import com.supervielle.pageobjects.OnboardingSeleccionDeSucursalPage;
import com.supervielle.pageobjects.OnboardingSolicitudAdicionalPage;
import com.supervielle.pageobjects.OnboardingValidacionDeSeguridad;
import com.supervielle.stepDefinitions.Hooks;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OnboardingUIDefinitions {

    private WebDriver driver;
    AgentesOfertasPage agentesOfertasPage;
    OnboardingPage onboardingPage;
    OnboardingOfertaPage onboardingOfertaPage;
    OnboardingSeleccionDeSucursalPage onboardingSeleccionDeSucursalPage;
    OnboardingValidacionDeSeguridad onboardingValidacionDeSeguridad;
    OnboardingConfirmacionPage onboardingConfirmacionPage;
    OnboardingSolicitudAdicionalPage onboardingSolicitudAdicionalPage;
    String verazResponse;
    String SACNOSISresponse;
    String aceptCrediticiaResponse;
    String aceptCrediticiaRequest;
    EstadoGeneral generalStatus;
    String ofertaCrediticiaRequest;
    String ofertaCrediticiaResponse;
    String altaPersonaRequest;
    String altaPersonaResponse;
    String altaProductoRequest;
    String altaProductoResponse;
    String altaAdicionalRequest;
    String altaAdicionalResponse;
    String consultaScoringSupervielle;
    String consultaCuestionarioVerazResponse;
    AgentesHomePage agentesHomePage;
    double maxIncome;
    String dateFormatFromProspectFile = "dd/MM/yyyy";
    List<String> altaPersonaRequests;
    List<String> altaPersonaResponses;
    static final String TAG_AGENTES = "Agentes";
    static final int TIME_FOR_DATABASE_REFRESH = 10;

    public OnboardingUIDefinitions() {
        this.driver = Hooks.getwebDriver();
    }

    // *******************BASE UI DEFINITIONS***************************//
    public void selectCountryOfBirth() {
        onboardingPage.selectCountryOfBirth();
        onboardingPage.clickOnContinueButtonFromCountryOfBirth();
    }

    public void selectCountryOfBirthOnIdentityValidationPage() {
        onboardingValidacionDeSeguridad.selectCountryOfBirth();
        onboardingValidacionDeSeguridad.clickOnContinueButtonFromCountryOfBirth();
    }

    public void selectCard() {
        onboardingPage.selectCard();
        onboardingPage.clickButtonFromCard();
    }

    public void selectCardOnIdentityValidationPage() {
        onboardingValidacionDeSeguridad.selectCard();
        onboardingValidacionDeSeguridad.clickButtonFromCard();
    }

    public void selectSafeBox() {
        onboardingPage.selectSafeBoxOfBank();
        onboardingPage.clickOnContinueButtonFromSafeBox();
    }

    public void selectSafeBoxOnIdentityValidationPage() {
        onboardingValidacionDeSeguridad.selectSafeBoxOfBank();
        onboardingConfirmacionPage = onboardingValidacionDeSeguridad.clickOnContinueButtonFromSafeBox();
    }

    public void fillValidationCodeForClient(String offering) {
        onboardingPage.fillValidationCodeFromEmail(UserProvider.getCuil(offering));
        onboardingOfertaPage = onboardingPage.clickOnValidateCodeButton();
    }

    /*---- ALTA PROSPECT FEATURE ---*/
    @Given("^me encuentro en la pagina de inicio de Onboarding de \"([^\"]*)\"$")
    public void initializeOnboardingHomePage(String product)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        WebDriverUtils.initializePageInstance(driver, AutomationProperties.getString(product));
        onboardingPage = new OnboardingPage(driver);
    }

    @Given("^selecciono la opcion disponible para hacerme cliente$")
    public void gotToClientRegistrationPage() {
        onboardingPage.clickOnOptionForClientRegistration();
    }

    @Given("^ingreso datos de un prospect que tiene \"([^\"]*)\"$")
    public void fillProspectDataOnOnboardingPersonalDataForm(String offeringContent) throws Exception {
        onboardingPage.fillProspectData(offeringContent);
    }

    @Given("^ingreso datos de un prospect \"([^\"]*)\"$")
    public void fillProspectDataOnOnboarding(String prospect) {
        onboardingPage.typeOnNameInput(UserProvider.getNombre(prospect));
        onboardingPage.typeOnLastNameInput(UserProvider.getApellido(prospect));
        onboardingPage.selectGender(UserProvider.getGenero(prospect));
        onboardingPage.typeOnAreaCodeInput(UserProvider.getCodArea(prospect));
        onboardingPage.typeOnTelephoneInput(UserProvider.getTelefono(prospect));
        onboardingPage.typeOnMailInput("automationTestingDadoAlta@globant.com");
        onboardingPage.typeOnCuilInput(UserProvider.getCuil(prospect));
        onboardingPage.clickOnContinueButton();
    }

    @Then("^valido que la llamada al servicio que obtiene los antecedentes comerciales no se ejecute$")
    public void validateBusinessBackgroundServiceIsNotExecuted() {
        Long cuil = ClientDao.getClientCuilFromAddedUserProcess();
        Assert.assertFalse("Se ejecuto la llamada al servicio que obtiene los antecedentes comerciales para el CUIL"
                + cuil + "y no debería", ClientDao.validateIfSacnosisExecuted("consultaSACNOSIS", cuil.toString()));
    }

    @Then("^la seccion de preguntas de seguridad carga correctamente$")
    public void verifySecurityQuestionsSectionHasLoadedCorrectly() {
        onboardingValidacionDeSeguridad.firstStepOfSecurityQuestionsHasLoaded();
    }

    @Given("^respondo las preguntas de seguridad en base al prospect que tiene \"([^\"]*)\"$")
    public void answerSecurityQuestions(String offeringContent) {
        selectCountryOfBirth();
        selectCard();
//      selectSafeBox();
    }

    public void answerSecurityQuestionsOnIdentityValidationPage(String offeringContent) {
        selectCountryOfBirthOnIdentityValidationPage();
        selectCardOnIdentityValidationPage();
        selectSafeBoxOnIdentityValidationPage();
    }

    @Given("^ingreso codigo de seguridad del email del prospect que tiene \"([^\"]*)\"$")
    public void fillValidationCode(String offeringContent) {
        fillValidationCodeForClient(offeringContent);
    }

    @Then("^verifico que en la barra de navegacion este activo el step de Oferta$")
    public void validateActiveStepInOfferPage() {
        Assert.assertTrue(
                "El step activo en la barra de navegacion no corresponde con el paso en el que se encuentra el usuario, Oferta",
                onboardingOfertaPage.stepIsActiveInNavBar());
    }

    @When("^solicito tarjeta$")
    public void clickOnButtonToRequestCard() {
        onboardingOfertaPage.clickOnRequestYourCardButton();
    }

    @When("^completo los datos personales, de direccion e informacion laboral del prospect que tiene \"([^\"]*)\"$")
    public void fillHolderDataInOfferingPage(String oferta) {
        fillHolderPersonalDataInOfferingPage(oferta);
        fillAddressDataForHolderInOfferingPage(oferta);
        fillWorkingDataOfHolderInOfferingPage();
    }

    @When("^completo los datos personales del prospect que tiene \"([^\"]*)\"$")
    public void fillHolderPersonalDataInOfferingPage(String offeringContent) {
        onboardingOfertaPage.selectCountryOfBirth(UserProvider.getPaisNacimiento(offeringContent));
        onboardingOfertaPage.selectBirthPlace(UserProvider.getLugarNacimiento(offeringContent));
    //  onboardingOfertaPage.writeOnEmailInput(UserProvider.getEmail(offeringContent));
        onboardingOfertaPage.selectEducationalLevel(UserProvider.getNivelEstudio(offeringContent));
    }

    @When("^completo los datos de direccion valida del prospect que tiene \"([^\"]*)\"$")
    public void fillAddressDataForHolderInOfferingPage(String offeringContent) {
        onboardingOfertaPage.clickOnContinueToAdressButton();
        onboardingOfertaPage.writeOnStreetAddressInput(UserProvider.getDireccion(offeringContent));
        onboardingOfertaPage.writeOnStreetLevel(UserProvider.getNumeroCalle(offeringContent));
        onboardingOfertaPage.selectProvince(UserProvider.getLugarNacimiento(offeringContent));
        onboardingOfertaPage.writeOnZipCodeInput(UserProvider.getCodigoPostal(offeringContent));
    }

    @When("^completo la informacion laboral en el formulario de alta$")
    public void fillWorkingDataOfHolderInOfferingPage() {
        onboardingOfertaPage.clickOnContinueToWorkingInfoButton();
        onboardingOfertaPage.selectEmploymentRelationship("Relación de Dependencia");
        onboardingOfertaPage.selectPEP("false");
    }

    @When("^selecciono la opcion para aceptar los datos del formulario de alta$")
    public void clickOnAcceptOption() {
        onboardingSeleccionDeSucursalPage = onboardingOfertaPage.clickOnAccept();
        Assert.assertNotNull(onboardingSeleccionDeSucursalPage);
    }

    @Then("^verifico que en la barra de navegacion este activo el step de Seleccion de Sucursal$")
    public void validateActiveStepInBranchSelectionPage() {
        Assert.assertTrue(
                "El step activo en la barra de navegacion no corresponde con el paso en el que se encuentra el usuario, Seleccion de Sucursal",
                onboardingSeleccionDeSucursalPage.stepIsActiveInNavBar());
    }

    @When("^selecciono una sucursal de supervielle en el mapa$")
    public void selectASupervielleBranchOnMap() throws InterruptedException {
        onboardingSeleccionDeSucursalPage.clickOnSupervielleBranch();
        onboardingSeleccionDeSucursalPage.clickOnSelectBranchButton();
    }

    @When("^selecciono la direccion por defecto para la entrega de la documentacion$")
    public void selectDocumentationAddressByDefault() {
        onboardingSeleccionDeSucursalPage.selectDocumentationAddressByDefault();
    }

    @When("^confirmo la seleccion de la sucursal$")
    public void confirmSupervielleBranchSelection() {
        onboardingConfirmacionPage = onboardingSeleccionDeSucursalPage.clickOnContinueButton();
    }

    @Then("^verifico que en la barra de navegacion este activo el step de Confirmacion de producto$")
    public void validateActiveStepInConfirmationPage() {
        Assert.assertTrue(
                "El step activo en la barra de navegacion no corresponde con el paso en el que se encuentra el usuario, Confirmacion de producto",
                onboardingConfirmacionPage.stepIsActiveInNavBar());
    }

    @When("^confirmo la seleccion de la sucursal de un flujo desde agentes$")
    public void confirmSupervielleBranchSelectionForAFlowFromAgentes() {
        onboardingValidacionDeSeguridad = onboardingSeleccionDeSucursalPage
                .clickOnContinueButtonOpeningTheIdentityValidationPage();
    }

    @Then("^verifico que en la barra de navegacion este activo el step de Validacion de Identidad$")
    public void validateActiveStepInIdentityValidationPage() {
        Assert.assertTrue(
                "El step activo en la barra de navegacion no corresponde con el paso en el que se encuentra el usuario, Validacion de Identidad",
                onboardingValidacionDeSeguridad.stepIsActiveInNavBar());
    }

    @Then("^la aplicacion me redirige a la pagina de confirmacion de solicitud de tarjeta despues de responder las preguntas de seguridad para el prospect que tiene \"([^\"]*)\"$")
    public void validateConfirmationPagehasLoadedAfterAnswerSecurityQuestions(String offering) {
        answerSecurityQuestionsOnIdentityValidationPage(offering);
        Assert.assertTrue("La pagina de confirmacion de solicitud de tarjeta no ha cargado correctamente",
                onboardingConfirmacionPage.isLoaded());
    }

    @Then("^valido que el precio mensual, productos seleccionados y terminos y condiciones sean visibles$")
    public void validateSummaryElements() {
        Assert.assertTrue("Precio mensual a pagar no está visible",
                onboardingConfirmacionPage.validateCreditCardMensualCostTextsIsDisplayed());
        Assert.assertTrue("Nombre de tarjeta no está visible",
                onboardingConfirmacionPage.validateCreditCardNameTextsIsDisplayed());
        Assert.assertTrue("Precio mensual a pagar no está visible",
                onboardingConfirmacionPage.validateTermsAndConditionsLinkIsDisplayed());
    }

    @Then("^valido que el sistema abra pop up y solicite que se marque el checkbox de Terminos y Condiciones cuando Solicito tarjeta$")
    public void validateTermsAndContiniosPopUp() {
        onboardingConfirmacionPage.clickOnApplyButton();
        Assert.assertTrue("Pop up de Términos y Condiciones no fue desplegado",
                onboardingConfirmacionPage.validateTermsAndConditionsPopUpIsDisplayed());
    }

    @Then("^valido que al aceptar los Terminos y Condiciones desde el pop up rediriga a la confirmacion de la tarjeta$")
    public void validateAcceptContinueTermsAndConditionsPopUp() {
        onboardingSolicitudAdicionalPage = onboardingConfirmacionPage.clickOnPopUpAcceptTermsAndConditions();
    }

    @Then("^valido que al hacer click en el link de Terminos y Condiciones el popup sea visible y luego lo cancelo")
    public void validateLinkAcceptContinueTermsAndConditionsPopUp() {
        onboardingConfirmacionPage.clickOnAcceptTermsAndConditionsLink();
        Assert.assertTrue("Pop up de Términos y Condiciones no fue desplegado",
                onboardingConfirmacionPage.validateTermsAndConditionsPopUpIsDisplayed());
        onboardingConfirmacionPage.clickOnPopUpCancelTermsAndConditions();
        Assert.assertFalse("Pop up de Términos y Condiciones está visible",
                onboardingConfirmacionPage.validateTermsAndConditionsPopUpIsDisplayed());
    }

    @When("^acepto los terminos y condiciones$")
    public void acceptTermsAndConditionsForProductRequest() {
        onboardingConfirmacionPage.clickOnAcceptTermsAndConditions();
    }

    @When("^confirmo la seleccion de la tarjeta$")
    public void confirmCardSelection() {
        onboardingSolicitudAdicionalPage = onboardingConfirmacionPage.confirmAndContinueToProductSolicitude();
    }

    @When("^valido la pantalla de comercios adheridos segun el \"([^\"]*)\" y la \"([^\"]*)\"$")
    public void validateShopsAdheredToBankList(String product, String offer) {
        if (AltaProductoUtils.PRODUCTO_ONBOARDING_VOUCHER.equalsIgnoreCase(product)) {
            if (UserProvider.getOferta(offer).contains(AltaProductoUtils.ONBOARDING_VOUCHER_OFFER)) {
                Assert.assertTrue("La lista de comercios adheridos no cargo correctamente",
                        onboardingSolicitudAdicionalPage.listOfShopsAdheredToBankIsPresent());
                clickOnConfirmProductOperationResult();
            }
        }
        onboardingSolicitudAdicionalPage.getWebDriverUtils().waitSeconds(TIME_FOR_DATABASE_REFRESH);

    }

    @Then("^la aplicacion me muestra una pagina con un mensaje de que mi operacion fue exitosa$")
    public void verifySuccessMessageInUIAfterCardConfirmation() {
        Assert.assertTrue("La pagina de seleccion de adicionales no cargo correctamente",
                onboardingSolicitudAdicionalPage.isLoaded());
        Assert.assertTrue("La pagina no muestra mensaje de exito",
                onboardingSolicitudAdicionalPage.successMessageIsPresent());
    }

    @When("^obtengo la llamada al servicio de alta Persona para el prospect que tiene \"([^\"]*)\"$")
    public void getPersonRegistrationServiceCall(String offeringContent) {
        altaPersonaRequest = AltaPersonaUtils.getRequestFromPersonRegistrationService(offeringContent);
        altaPersonaResponse = AltaPersonaUtils.getResponseFromPersonRegistrationService(offeringContent);
        TextUtils.addRequestToReport(altaPersonaRequest, "AltaPersonaRequest");
        TextUtils.addResponseToReport(altaPersonaResponse, "AltaPersonaResponse");
    }

    @When("^obtengo la llamada al servicio de alta de \"([^\"]*)\" para el prospect que tiene \"([^\"]*)\"$")
    public void getProductRegistrationServiceCall(String product, String offeringContent) throws InterruptedException {
        driver.wait(TIME_FOR_DATABASE_REFRESH);
        altaProductoRequest = AltaProductoUtils.getRequestFromProductRegistrationService(offeringContent, product);
        altaProductoResponse = AltaProductoUtils.getResponseFromProductRegistrationService(offeringContent, product);
        TextUtils.addRequestToReport(altaProductoRequest, "AltaProductoRequest");
        TextUtils.addResponseToReport(altaProductoResponse, "AltaProductoResponse");
    }

    @Then("^la request del servicio alta Persona para el prospect que tiene \"([^\"]*)\" tiene parametros validos$")
    public void verifyIfPersonRegistrationRequestHasValidParameters(String prospect) {
        Dato sacnosisDato = SacnosisUtils.getFirstDatoFromResponseSACNOSIS(SACNOSISresponse);
        RequestAltaPersona altaPersonaRequestDTO = AltaPersonaUtils
                .getDTOFromPersonRegistrationRequest(altaPersonaRequest);

        Assert.assertTrue("No se encontro la llamada al servicio de alta Persona",
                StringUtils.isNotBlank(altaPersonaRequest));

        Assert.assertEquals(
                "La request del servicio altaPersona no tiene el valor esperado en el parametro Aplicacion Externa",
                AltaPersonaUtils.APLICACION_EXTERNA, altaPersonaRequestDTO.getData().getAplicacionExterna());

        Assert.assertEquals("La request del servicio altaPersona no tiene el valor esperado en el Documento",
                UserProvider.getDNIFromCuil(prospect),
                altaPersonaRequestDTO.getData().getPersonas().getPersona().getIdentificador().getNumDoc());

        Assert.assertEquals(
                "La request del servicio altaPersona no tiene el valor esperado en el parametro TipoPersona",
                AltaPersonaUtils.CODIGO_PERSONA_FISICA,
                altaPersonaRequestDTO.getData().getPersonas().getPersona().getInfoPersona().getTipoPersona());

        Assert.assertEquals(
                "La request del servicio altaPersona no tiene el valor esperado en el parametro Canal De Distribucion",
                AltaPersonaUtils.CANAL_DISTRIBUCION,
                altaPersonaRequestDTO.getData().getPersonas().getPersona().getInfoPersona().getCanalDistribucion());

        Assert.assertEquals(
                "La request del servicio altaPersona no tiene el valor esperado en el parametro Tipo De Alta",
                AltaPersonaUtils.TIPO_ALTA,
                altaPersonaRequestDTO.getData().getPersonas().getPersona().getInfoPersona().getTipoAlta());

        Assert.assertEquals("La request del servicio altaPersona no tiene el valor esperado en el parametro Categoria",
                AltaPersonaUtils.CATEGORIA,
                altaPersonaRequestDTO.getData().getPersonas().getPersona().getInfoPersona().getCategoria());

        // Assert.assertEquals("La request del servicio altaPersona no tiene el
        // valor esperado en el parametro Nombre",
        // sacnosisDato.getNombre(),
        // altaPersonaRequestDTO.getData().getPersonas().getPersona().getInfoPersona().getNombre());
        //
        // Assert.assertEquals("La request del servicio altaPersona no tiene el
        // valor esperado en el parametro Apellido",
        // sacnosisDato.getApellido(),
        // altaPersonaRequestDTO.getData().getPersonas().getPersona().getInfoPersona().getApellido1());

        Assert.assertEquals("La request del servicio altaPersona no tiene el valor esperado en el parametro Sexo",
                UserProvider.getGenero(prospect),
                altaPersonaRequestDTO.getData().getPersonas().getPersona().getInfoPersona().getSexo());

        Assert.assertEquals(
                "La request del servicio altaPersona no tiene el valor esperado en el parametro codTitularidad",
                AltaPersonaUtils.COD_TITULARIDAD,
                altaPersonaRequestDTO.getData().getPersonas().getPersona().getInfoPersona().getCodTitularidad());

        String nivelEstudioID = EducationaLevel.valueOf(UserProvider.getNivelEstudio(prospect)).getNivel();
        Assert.assertEquals(
                "La request del servicio altaPersona no tiene el valor esperado en el parametro NivelEstudio",
                nivelEstudioID, altaPersonaRequestDTO.getData().getPersonas().getPersona().getInfoPersona()
                        .getInfAdicional().getNivelEstudio());

        Assert.assertEquals("La request del servicio altaPersona no tiene el valor esperado en el parametro Calle",
                UserProvider.getDireccion(prospect).toUpperCase(), altaPersonaRequestDTO.getData().getPersonas()
                        .getPersona().getDomicilios().getDomicilio().getCalle().toUpperCase());

        Assert.assertEquals(
                "La request del servicio altaPersona no tiene el valor esperado en el parametro Codigo Postal",
                UserProvider.getCodigoPostal(prospect),
                altaPersonaRequestDTO.getData().getPersonas().getPersona().getDomicilios().getDomicilio().getCp());

        String numTelef = UserProvider.getCodArea(prospect) + "15" + UserProvider.getTelefono(prospect);
        Assert.assertTrue(
                "La request del servicio altaPersona no tiene el valor esperado en el parametro Numero de Telefono",
                altaPersonaRequestDTO.getData().getPersonas().getPersona().getDomicilios().getDomicilio().getTelefono()
                        .replace(" ", "").contains(numTelef));

    }

    @Then("^la response del servicio alta Persona para el prospect describe una operacion exitosa$")
    public void verifyIfPersonRegistrationResponseDescribesASuccessfulOperation() {
        ResponseAltaPersona responseAltaPersonaDTO = AltaPersonaUtils
                .getDTOFromPersonRegistrationResponse(altaPersonaResponse);
        Assert.assertEquals("La response del servicio altaPersona tiene estado OK", AltaPersonaUtils.ESTADO_OK,
                responseAltaPersonaDTO.getData().getRow().getEstado().getEstado());
        Assert.assertEquals("La response del servicio altaPersona tiene describe La persona con estado OK",
                AltaPersonaUtils.ESTADO_OK,
                responseAltaPersonaDTO.getData().getRow().getPersonas().getPersona().getEstado().getEstado());
    }

    @Then("^el servicio de alta de tarjeta para prospect que tiene \"([^\"]*)\" corresponde con el \"([^\"]*)\"$")
    public void verifyIfTheCardRegistrationServiceMatchesWithTheCorrespodingTypeOfProduct(String prospect,
            String product) {
        Assert.assertTrue("No se encontro la llamada al servicio de alta Producto para " + product,
                StringUtils.isNotBlank(altaProductoResponse));
    }

    @Then("^el servicio de alta de \"([^\"]*)\" responde con codigo de exito$")
    public void verifyIfProductRegistrationResponseDescribesASuccessfulOperation(String producto) {
        switch (producto) {
        case AltaProductoUtils.PRODUCTO_ONBOARDING_VOUCHER:
            ResponseAltaInstantaneaMaster responseAltaInstantaneaMaster = AltaVoucherUtils
                    .getDTOFromVoucherRegistrationResponse(altaProductoResponse);
            Assert.assertTrue("La descripcion de la response de alta de Producto no reporto exito",
                    responseAltaInstantaneaMaster.getData().getDescripcion()
                            .contains(AltaVoucherUtils.DESCRIPCION_EXITOSA));
            Assert.assertTrue("La descripcion de la solicitud response de alta de Producto no reporto exito",
                    responseAltaInstantaneaMaster.getData().getSolicitud().getDescripcion()
                            .contains(AltaVoucherUtils.DESCRIPCION_EXITOSA));
            break;
        default:
            ResponseAltaTitular responseAltaTitular = AltaTarjetaUtils
                    .getDTOFromTarjetaRegistrationResponse(altaProductoResponse);
            Assert.assertEquals("La response del servicio alta Titular no tiene estado OK", AltaTarjetaUtils.ESTADO_OK,
                    responseAltaTitular.getData().getRow().getEstado().getEstado());
            Assert.assertTrue("La response del servicio alta Titular no reporto una resolucion exitosa",
                    responseAltaTitular.getData().getRow().getCodigoResolucion().getDescripcion()
                            .contains(AltaTarjetaUtils.DESCRIPCION_EXITOSA));
            break;
        }
    }
    // *******************END BASE UI DEFINITIONS***************************//

    /*---- CAMPOS ONBOARDING FEATURE ---*/
    // Verificar validacion del campo telefónico con datos validos

    @When("^ingreso numero telefonico valido con (\\d+) y para numero de celular (\\d+)$")
    public void fillTelephoneNumber(int digitsForArea, int digitsForTelephone) {
        onboardingPage.fillAreaCode(digitsForArea);
        onboardingPage.fillTelephoneNumber(digitsForTelephone);
    }

    @When("^valido cantidad maxima de digitos del codigo de area (\\d+) y de numero de celular (\\d+)$")
    public void verifyAmountOfDigitsForTelephone(int maxAreaCode, int maxTelephoneCode) {
        Assert.assertEquals("Cantidad de digitos de código de área no validos", maxAreaCode,
                onboardingPage.getLengthOfAreaCode());
        Assert.assertEquals("Cantidad de digitos de número de celular no validos", maxTelephoneCode,
                onboardingPage.getLengthOfTelephoneNumber());
    }

    @When("^valido que la suma de los digitos de codigo de area y numero de celular sea (\\d+)$")
    public void verifySumOfTelephoneDigits(int digits) {
        int sum = onboardingPage.getLengthOfTelephoneNumber() + onboardingPage.getLengthOfAreaCode();
        Assert.assertEquals("la suma total de digitos de area y numero no es valida", digits, sum);
    }

    @Then("la pagina no muestra ningun mensaje de error$")
    public void verifyHomePageDoesNotShowErrorMessage() {
        onboardingPage.clickOnContinueButton();
        Assert.assertFalse("Mensaje número de teléfono inválido es visible", onboardingPage.isErrorMessageDisplayed());
    }

    // Verificar validacion del campo telefónico con datos NO validos
    @When("^ingreso numero telefonico erroneo con (\\d+) y con (\\d+)$")
    public void fillInvalidTelephoneNumber(int digitsForArea, int digitsForTelephone) {
        fillTelephoneNumber(digitsForArea, digitsForTelephone);
    }

    @Then("^la pagina me muestra un mensaje de error de validacion para el numero de telefono$")
    public void verifyHomePageShowsErrorMessageForInvalidTelephoneNumber() {
        onboardingPage.clickOnContinueButton();
        Assert.assertTrue("Mensaje número de teléfono inválido no es visible",
                onboardingPage.isErrorMessageDisplayed());
    }

    /* AUTOCOMPLETADO DE DATOS EN EL CAMPO NUMERO TELEFONICO */
    @Then("^en datos personales el campo telefono aparece autocompletado segun el prospect cargado que tiene \"([^\"]*)\"$")
    public void verifyAutoFillingOfTheTelephoneFieldWithPrefilledData(String oferta) {
        Assert.assertEquals(UserProvider.getCodArea(oferta), onboardingOfertaPage.getTextOfTelephoneCode());
        Assert.assertEquals(UserProvider.getTelefono(oferta), onboardingOfertaPage.getTextOfTelephone());
    }

    @When("^valido que para el prospect que tiene \"([^\"]*)\" la respuesta del servicio de preguntas de seguridad contiene \"([^\"]*)\"$")
    public void verifyMessageInSecurityQuestionsService(String cuil, String message) {
        ResponsePreguntasSeguridad response = PreguntasSeguridadUtils.getResponseFromSecurityQuestionsService(cuil);
        Assert.assertTrue("El mensaje obtenido no es el esperado", TextUtils.normalize(response.getMessage()).contains(TextUtils.normalize(message)));
    }

    @Then("^valido que en la pagina el mensaje de usuario bloqueado este visible \"([^\"]*)\"$")
    public void verifyPageShowsErrorMesageForABlockedUser(String message) {
        onboardingPage.getMessageForBlockedUser().contains(message);
    }

    @Then("^valido que en la pagina el mensaje de proceso de Tarjeta de Credito activo este visible \"([^\"]*)\"$")
    public void verifyPageShowsErrorMesageForActiveCreditCardProcess(String message) {
        onboardingPage.getActiveCreditCardProcessMessage().contains(message);
    }

    @Then("^la pagina de Oferta es correctamente desplegada$")
    public void verifyOfferingPageHasLoaded() {
        Assert.assertTrue("La página de Oferta no fue desplegafa correctamente", onboardingOfertaPage.isLoaded());
    }

    @Given("^valido que los campos de datos personales estan visibles$")
    public void verifyDataFieldsAreDisplayedInUI() {
        Assert.assertTrue("Los checboxes no estan visibles", onboardingPage.personalDataFieldsAreDisplayed());
    }

    @When("^selecciono para mi \"([^\"]*)\" estado civil casado y completo nombre \"([^\"]*)\" apellido \"([^\"]*)\" pais nacimiento \"([^\"]*)\" y DNI (\\d+)$")
    public Integer selectMarriedAsCivilStatusAndFillDataOfSpouse(String offeringContent, String name, String surname,
            String country, Integer dni) {

        Integer cliId = getCliIdFromCuil(UserProvider.getCuil(offeringContent));
        ClientDao.removeConyuge(cliId);
        onboardingOfertaPage.selectCivilStatus("Casado/a");
        onboardingOfertaPage.writeOnSpouseName(name, surname);
        onboardingOfertaPage.writeOnSpouseDNI(dni.toString());
        onboardingOfertaPage.selectCountryOfBirthForSpouse(country);
        onboardingOfertaPage.selectDocumentTypeForSpouse("D.N.I.");
        return cliId;
    }

    @When("^cuando confirmo mis datos personales y avanzo a la seccion de domicilio$")
    public void continueToAddressFillingSection() {
        onboardingOfertaPage.clickOnContinueToAdressButton();
    }

    @Then("^valido para mi \"([^\"]*)\" que nombre \"([^\"]*)\" apellido \"([^\"]*)\" y DNI (\\d+) de los campos de conyuge aparezcan en la base de datos$")
    public void verifySpouseDataInDatabase(String offeringContent, String name, String surname, Long dni) {
        Integer cliId = getCliIdFromCuil(UserProvider.getCuil(offeringContent));
        onboardingSeleccionDeSucursalPage.getWebDriverUtils().waitSeconds(TIME_FOR_DATABASE_REFRESH);
        Persona conyuge = ClientDao.getConyugeRow(cliId);

        Assert.assertTrue("Apellido de conyuge esperado no es correcto con el de la Base de Datos",
                conyuge.getApellido().equalsIgnoreCase(surname));
        Assert.assertTrue("Nombre de conyuge esperado no es correcto con el de la Base de Datos",
                conyuge.getNombre().equalsIgnoreCase(name));
        Assert.assertTrue("Número de DNI de conyuge esperado no es correcto con el de la Base de Datos",
                conyuge.getDni().equals(dni));
    }

    @Then("^valido para mi \"([^\"]*)\" aparezca en datos filiatorios de la base de datos despues de aceptar los datos en el formulario de alta$")
    public void validateFiliatoryDataWithoutCitizenshipInBD(String offeringContent) {
        Integer cliId = getCliIdFromCuil(UserProvider.getCuil(offeringContent));
        ClientDao.removeDatosFiliatorios(cliId);

        clickOnAcceptOption();
        onboardingSeleccionDeSucursalPage.getWebDriverUtils().waitSeconds(TIME_FOR_DATABASE_REFRESH);
        DatoFiliatorio datos = ClientDao.getDatosFiliatorios(cliId);
        Assert.assertTrue("Doble ciudadanía no es correcto", datos.getDobleCiudadania().equals(0));
        Assert.assertNull("Documento doble ciudadanía no es nulo", datos.getDocumentoDobleCiudadania());
        Assert.assertNull("País doble ciudadanía no es nulo", datos.getPaisDobleCiudadania());
        Assert.assertNotNull("Estado civil es nulo", datos.getEstadoCivil());
        Assert.assertTrue("País de residencia no es correcto",
                (UserProvider.getPaisNacimiento(offeringContent)).contains(datos.getPaisResidencia()));
        Assert.assertTrue("Residente no es correcto", datos.getResidente().equals(1));
        Assert.assertTrue("Doble ciudadanía no es correcto", datos.getDobleCiudadania().equals(0));
        Assert.assertTrue("Lugar de nacimiento no es correcto",
                datos.getLugarNacimiento().equalsIgnoreCase(UserProvider.getLugarNacimiento(offeringContent)));
        Assert.assertTrue("País nacimiento no es correcto",
                UserProvider.getPaisNacimiento(offeringContent).contains(datos.getPaisNacimiento()));
    }

    @Then("^valido que mis datos \"([^\"]*)\" de Domicilio en base de datos$")
    public void validateAddressDataInDatabase(String offeringContent) {
        Integer cliId = getCliIdFromCuil(UserProvider.getCuil(offeringContent));
        Domicilio domicilio = ClientDao.getDomicilio(cliId);

        Assert.assertTrue("Nombre de calle no es correcta",
                domicilio.getCalle().equalsIgnoreCase(UserProvider.getDireccion(offeringContent)));
        Assert.assertNotNull("Altura de calle es nula", domicilio.getAltura());
        Assert.assertTrue("Codigo postal no es correcto",
                domicilio.getCodigoPostal().toString().equals(UserProvider.getCodigoPostal(offeringContent)));
        Assert.assertNotNull("Piso es nula", domicilio.getPiso());
        Assert.assertTrue("Localidad no es correcta",
                domicilio.getLocalidad().contains(UserProvider.getLocalidad(offeringContent)));
        Assert.assertTrue("Provincia no es correcta",
                domicilio.getProvincia().contains(UserProvider.getProvincia(offeringContent)));
    }

    /* VALIDACION DE DIRECCION */
    @When("^hago click en el boton continuar para pasar a la seccion de informacion laboral$")
    public void clickOnContinueToWorkingInfoButton() {
        onboardingOfertaPage.clickOnContinueToWorkingInfoButton();
    }

    @Then("^la pagina me permite el acceso a la carga de informacion laboral$")
    public void employmentRelationshipInfoSectionHasLoaded() {
        Assert.assertTrue("La seccion de carga de informacion laboral no cargo correctamente",
                onboardingOfertaPage.employmentRelationshipInfoHasLoaded());
    }

    public void fillStreetAddressWithInvalidData(String street) {
        onboardingOfertaPage.writeOnStreetAddressInput(street);
    }

    @Then("^cada vez que ingrese una direccion no valida de \"([^\"]*)\" y haga click en el boton continuar, la pagina me muestra un mensaje de error de direccion no valida, negandome el acceso a la carga de informacion laboral$")
    public void loadInvalidAddressAndVerifyOnboardingBehaviourForInvalidAddress(String streetExamples) {
        String streets[] = streetExamples.split("A");
        for (String street : streets) {
            TextUtils.reportLog("Filling street field with value : " + street);
            fillStreetAddressWithInvalidData(street);
            clickOnContinueToWorkingInfoButton();
            verifyErrorMessageForInvalidAddressIsDisplayedOnUI();
            employmentRelationshipInfoHasNotLoaded();
        }

    }

    public void verifyErrorMessageForInvalidAddressIsDisplayedOnUI() {
        Assert.assertTrue("No aparecio mensaje de error alguno para la direccion no valida",
                onboardingOfertaPage.errorMessageForInvalidDirectionIsDisplayed());
    }

    public void employmentRelationshipInfoHasNotLoaded() {
        Assert.assertFalse(
                "La seccion de carga de informacion laboral cargo correctamente a pesar de la direccion no valida",
                onboardingOfertaPage.employmentRelationshipInfoHasLoaded());
    }

    // Validacion de direccion para entrega de documentacion
    @When("^selecciono la opcion Otra Direccion para recibir la documentacion$")
    public void selectAlternativeDocumentationAddress() {
        onboardingSeleccionDeSucursalPage.clickOnSetAlternativeDocumentationAddress();
    }

    @When("^completo los datos de una direccion alternativa valida para el prospect que tiene \"([^\"]*)\"$")
    public void fillDataForAlternativeDocumentationAddress(String offeringContent) {
        onboardingSeleccionDeSucursalPage.selectProvince(UserProvider.getLugarNacimiento(offeringContent));
        onboardingSeleccionDeSucursalPage.writeOnZipCode(UserProvider.getCodigoPostal(offeringContent));
        onboardingSeleccionDeSucursalPage.writeOnStreetInput(UserProvider.getDireccion(offeringContent));
        onboardingSeleccionDeSucursalPage.writeOnStreetNumberInput(UserProvider.getNumeroCalle(offeringContent));
    }

    @Then("^la aplicacion me redirige a la pagina de confirmacion de solicitud de tarjeta$")
    public void verifyConfirmationProductPageHasLoaded() {
        Assert.assertTrue("La pagina de confirmacion de solicitud de tarjeta no ha cargado correctamente",
                onboardingConfirmacionPage.isLoaded());
    }

    public void fillAlternativeDocumentationAddressWithInvalidStreetData(String street) {
        onboardingSeleccionDeSucursalPage.writeOnStreetInput(street);
    }

    @Then("^cada vez que ingrese una direccion no valida de \"([^\"]*)\" y haga click en el boton continuar, la pagina me muestra un mensaje de error de direccion alternativa no valida, negandome el acceso a la pagina de confirmacion de solicitud de tarjeta$")
    public void fillInvalidStreetDataAndVerifyOnboardingBehaviourForInvalidAlternativeAddress(String streetExamples) {
        String streets[] = streetExamples.split("A");
        for (String street : streets) {
            TextUtils.reportLog("Filling street field with value : " + street);
            fillAlternativeDocumentationAddressWithInvalidStreetData(street);
            confirmSupervielleBranchSelection();
            verifyErrorMessageForInvalidAlternativeAddressIsDisplayedOnUI();
            verifyProductConfirmationPageHasNotLoaded();
        }
    }

    public void verifyErrorMessageForInvalidAlternativeAddressIsDisplayedOnUI() {
        Assert.assertTrue("No aparecio mensaje de error alguno para la direccion no valida",
                onboardingSeleccionDeSucursalPage.errorMessageForInvalidDirectionIsPresent());
    }

    public void verifyProductConfirmationPageHasNotLoaded() {
        Assert.assertTrue("La pagina de confirmacion de producto cargo correctamente a pesar de los datos no validos",
                onboardingSeleccionDeSucursalPage.isLoaded());
    }

    @Then("^la pagina muestra el mensaje \"([^\"]*)\" correspondiente a prospect sin oferta$")
    public void verifyErrorMessageForNoOfferingAvailableIsDisplayedOnUI(String message) {
        onboardingPage.getMessageForUserWithoutOfferingAccess().contains(message);
    }

    @Then("^valido que el dropdown de la UI de Provincia sean los mismos que en la tabla Provincias Operativas de la base de datos$")
    public void validateOperativeProvincesInUIMatchWithTheDatabase() {
        Assert.assertTrue("Lista de provincia de UI no es la misma que en base de datos",
                onboardingOfertaPage.validateProvinceListFromDBAndUI());
    }

    /* LOGICA VERAZ */
    @When("^obtengo la respuesta del servicio que obtiene los antecedentes comerciales para el prospect que tiene \"([^\"]*)\"$")
    public void getResponseFromBusinessBackgroundService(String status) {
        SACNOSISresponse = SacnosisUtils.getResponseFromCommercialBackgroundService(status);
        TextUtils.addResponseToReport(SACNOSISresponse, "SACNOSISresponse");
    }

    @Then("^valido que Edad en la respuesta del servicio que obtiene los antecedentes comerciales sea mayor de edad y no nulo$")
    public void validateAgeIsForAdultFromSacnosis() {
        Assert.assertTrue("La response que obtiene los antecedentes comerciales para el valor <Edad> no es correcto",
                SacnosisUtils.getValueInAgeFromSACNOSISResponse(SACNOSISresponse) > 18);
    }

    @Then("^la respuesta del servicio que obtiene los antecedentes comerciales tiene el valor CI en el tag Dato-Prefijo$")
    public void validateCommercialBackgroundServiceHasCIValueInTags() {
        Assert.assertTrue(
                "La response que obtiene los antecedentes comerciales no contiene el valor CI en Dato>>Prefijo",
                SacnosisUtils.SACNOSISResponseHasGivenValueInPrefijo(SACNOSISresponse, "CI"));
    }

    @Then("^la respuesta del servicio que obtiene los antecedentes comerciales tiene la tag Deudas dentro del tag UltimoInforme$")
    public void validateDeudasTagPositionInBusinessBackgroundService() {
        Assert.assertTrue("La response no contiene <Deudas> en <UltimoInforme>",
                SacnosisUtils.SACNOSISResponseHasDeudas(SACNOSISresponse));
    }

    @Then("^la respuesta del servicio de informacion crediticia y solvencia economica para el prospect que tiene \"([^\"]*)\" devuelve un valor menor o igual a cero en algun grupo de banco$")
    public void verifyVerazServiceResponseHasValueZeroInSomeOfTheBankGroupsTags(String offeringContent) {
        getResponseFromVerazService(offeringContent);
        Assert.assertTrue("Ninguno de los grupos de bancos devolvio valor menor o igual a cero",
                VerazUtils.verazResponseHasValue0(verazResponse));
    }

    @Then("^la respuesta del servicio de informacion crediticia y solvencia economica para el prospect que tiene \"([^\"]*)\" devuelve un valor de score_veraz = 0$")
    public void verifyResponseFromVerazServiceHasValueEqualsOrLessThanZeroInSomeBankGroups(String status) {
        getResponseFromVerazService(status);
        Assert.assertTrue("Ninguno de los grupos de bancos devolvio valor menor o igual a cero",
                VerazUtils.verazResponseHasScoreVeraz0(verazResponse));
    }

    @Then("^la respuesta del servicio que obtiene los antecedentes comerciales no contiene datos de deudas$")
    public void verifyServiceForCommercialBackgroundHasNoDebts() {

        Assert.assertFalse("La response contiene datos de <Deudas> en <UltimoInforme>",
                SacnosisUtils.SACNOSISResponseHasDeudas(SACNOSISresponse));
    }

    @Then("^la respuesta del servicio que obtiene los antecedentes comerciales no tiene el valor CI en el tag Dato-Prefijo$")
    public void validateCommercialBackgroundServiceHasNoCIValueInTags() {
        Assert.assertFalse("La response que obtiene los antecedentes comerciales contiene el valor CI en Dato>>Prefijo",
                SacnosisUtils.SACNOSISResponseHasGivenValueInPrefijo(SACNOSISresponse, "CI"));
    }

    @When("^verifico que el mayor ingreso de la respuesta del servicio de informacion crediticia y solvencia economica para el prospect que tiene \"([^\"]*)\" no supera el limite de grupos$")
    public void verifyMaximumIncome(String estado) {
        getResponseFromVerazService(estado);
        maxIncome = CalculoRentaUtils.getMaximumIncome(SACNOSISresponse, verazResponse);
        Assert.assertTrue("El mayor ingreso supera los ", maxIncome < CalculoRentaDao.getLimiteGruposBancos());
    }

    /* ACEPTACION CREDITICIA */
    @When("^obtengo la llamada al servicio de aceptacion crediticia para el prospect que tiene \"([^\"]*)\"$")
    public void getCreditAcceptationServiceCall(String status) {
        aceptCrediticiaRequest = AceptacionCrediticiaUtils.getRequestFromCreditAcceptationService(status);
        aceptCrediticiaResponse = AceptacionCrediticiaUtils.getResponseFromCreditAcceptationService(status);
        TextUtils.addRequestToReport(aceptCrediticiaRequest, "AceptacionCrediticiaRequest");
        TextUtils.addResponseToReport(aceptCrediticiaResponse, "AceptacionCrediticiaResponse");
        generalStatus = AceptacionCrediticiaUtils.getEstadoGeneralFromAceptCrediticiaResponse(aceptCrediticiaResponse);
    }

    @Then("^la request del servicio de aceptacion crediticia tiene el elemento politica igual a (\\d+)$")
    public void verifyPoliticaIdValueFromCreditAcceptationRequest(int policy) {
        Assert.assertEquals("Politica tiene un valor distinto a " + policy, policy,
                AceptacionCrediticiaUtils.getPoliticaValueFromRequest(aceptCrediticiaRequest));
    }

    @Then("^el ingreso del servicio de aceptacion crediticia es igual al ingreso calculado de la respuesta de veraz$")
    public void verifyIncomeFromVerazIsEqualsToIncomeFromCreditAcceptationRequest() {
        Assert.assertEquals("El ingreso de la aceptacion crediticia no coincide con el ingreso obtenido por veraz",
                maxIncome, AceptacionCrediticiaUtils.getIngresoFromRequest(aceptCrediticiaRequest), 1);
    }

    @Then("^el score del servicio de aceptacion crediticia es igual al score obtenido de la respuesta de veraz$")
    public void verifyScoreFromVerazIsEqualsToScoreFromCreditAcceptationRequest() {
        Assert.assertEquals(
                "El valor del score de la aceptacion crediticia no coincide con el score obtenido por veraz",
                VerazUtils.getScoreFromResponse(verazResponse),
                AceptacionCrediticiaUtils.getScoreValueFromRequest(aceptCrediticiaRequest));
    }

    @Then("^el tipoScore del servicio de aceptacion crediticia es igual a \"([^\"]*)\"$")
    public void verifyScoreTypeFromVerazIsEqualsToScoreTypeFromCreditAcceptationRequest(String scoreType) {
        Assert.assertEquals(
                "El valor del tipo de score de la aceptacion crediticia no coincide con el score obtenido por veraz",
                scoreType, AceptacionCrediticiaUtils.getScoreTypeFromRequest(aceptCrediticiaRequest));
    }

    @Then("^la respuesta del servicio de aceptacion crediticia es \"([^\"]*)\"$")
    public void verifyRespuestaFromCreditAcceptationResponseIsEqualsTo(String result) {
        Assert.assertEquals("La respuesta de la aceptacion crediticia fue distinta fue distinta de " + result, result,
                generalStatus.getDescripcion());
    }

    @Then("^el codigo del servicio de aceptacion crediticia es \"([^\"]*)\"$")
    public void verifyCodigoFromCreditAcceptationResponseIsEqualsTo(String code) {
        Assert.assertEquals("La respuesta de la aceptacion crediticia fue distinta fue distinta de " + code, code,
                generalStatus.getCodigo());
    }

    @When("^valido el response de Nosis no tenga Item \"([^\"]*)\" y clave \"([^\"]*)\"$")
    public void verifyResponseFromCommercialBackgroundServiceHasNoItemElement(String itemNumber, String key) {
        Assert.assertTrue("Para el CUIL brindado la response SI contiene el Item: " + itemNumber,
                SacnosisUtils.SACNOSISResponseHasNotGivenValueInItemAndClave(SACNOSISresponse, itemNumber, key));
    }

    @When("^valido el response de Nosis tenga Item \"([^\"]*)\" y clave \"([^\"]*)\"$")
    public void verifyResponseFromCommercialBackgroundServiceHasItemElement(String itemNumber, String key) {
        Assert.assertFalse("Para el CUIL brindado la response no contiene el Item: " + itemNumber,
                SacnosisUtils.SACNOSISResponseHasNotGivenValueInItemAndClave(SACNOSISresponse, itemNumber, key));
    }

    @When("^valido el response del servicio que obtiene los antecedentes comerciales el tag SinActividad sea \"([^\"]*)\"$")
    public void verifySinActividadTagFromCommercialBackgroundServiceIsEqualsTo(String activity) {
        Assert.assertTrue("El tag <SinActividad> no contiene el parametro: " + activity,
                SacnosisUtils.SACNOSISResponseHasGivenValueInSinActividad(SACNOSISresponse, activity));
    }

    @Then("^valido que en dropdown de provincia no contenga a la provincia del response del servicio que obtiene los antecedentes comerciales$")
    public void validateProvinceDropdown() {
        String fiscalDomicile = SacnosisUtils.getProvinciaDomicilioFiscal(SACNOSISresponse);
        Assert.assertTrue("La provincia del response está presente en el dropdown y no debería",
                onboardingOfertaPage.validateProvinceElementsFromDropdown(fiscalDomicile));
    }

    @Then("^la request de la llamada a oferta crediticia tiene un valor de PoliticaID valido")
    public void validatePoliticaIDFromCreditOfferingService() {
        Assert.assertEquals("El valor de PoliticaID de la aceptacion crediticia no coincide con el valor esperado",
                CalculoRentaUtils.getPoliticaIdFromResponse(SACNOSISresponse, verazResponse),
                OfertaCrediticiaUtils.getPoliticaIDFromRequest(ofertaCrediticiaRequest));
    }

    @When("^obtengo la llamada al servicio de oferta crediticia para el prospect que tiene \"([^\"]*)\"$")
    public void getCreditOfferingServiceCall(String status) {
        ofertaCrediticiaRequest = OfertaCrediticiaUtils.getRequestFromCreditAcceptationService(status);
        ofertaCrediticiaResponse = OfertaCrediticiaUtils.getResponseFromCreditAcceptationService(status);
        TextUtils.addRequestToReport(ofertaCrediticiaRequest, "OfertaCrediticiaRequest");
        TextUtils.addResponseToReport(ofertaCrediticiaResponse, "OfertaCrediticiaResponse");
    }

    @When("^obtengo la llamada al servicio de informacion crediticia y solvencia economica para el prospect que tiene \"([^\"]*)\"$")
    public void getResponseFromVerazService(String status) {
        verazResponse = VerazUtils.getResponseFromVerazService(status);
        TextUtils.addResponseToReport(verazResponse, "VerazResponse");
    }

    @When("^valido el response del servicio que obtiene los antecedentes comerciales informe el valor de NSE$")
    public void verifyNSEValueFromServiceForCommercialBackgroundIsNotNull() {
        Assert.assertNotNull("El valor de NSE es nulo y no debería",
                SacnosisUtils.getCategoryFromNSE(SACNOSISresponse));
    }

    /* ALTA DE ADICIONALES */
    @When("^hago click en la opcion Entendido en la pagina de seleccion de adicionales$")
    public void clickOnConfirmProductOperationResult() {
        onboardingSolicitudAdicionalPage.clickOnConfirmProductOperationResult();
    }

    @When("^selecciono (\\d+) adicionales$")
    public void selectAmountOfAditionals(int aditionals) {
        onboardingSolicitudAdicionalPage.selectAditional(aditionals);
    }

    @When("^verifico que el prospect que tiene \"([^\"]*)\" no tenga registrado al \"([^\"]*)\" en la base de datos$")
    public void cleanDataForAditionalRegistration(String prospectOfferingContent, String prospectOfAditional) {
        ClientDao.cleanDataForAditionalRegistration(
                getCliIdFromCuil((UserProvider.getCuil(prospectOfferingContent))),
                UserProvider.getDNIFromCuil(prospectOfAditional));
    }

    @When("^selecciono la opcion confirmar adicionales$")
    public void confirmAmountOfAditionals() {
        onboardingSolicitudAdicionalPage.clickOnConfirmButton();
    }

    @When("^cargo los datos de un \"([^\"]*)\" en los datos personales$")
    public void fillProspectDataOfAditional(String prospectAdicional) {
        onboardingSolicitudAdicionalPage.fillAditionalProspectData(prospectAdicional);
    }

    @When("^hago click en continuar operacion$")
    public void continueOperationInAditionalsPage() {
        onboardingSolicitudAdicionalPage.clickOnContinueButton();
    }

    @When("^hago click en la opcion disponible para confirmar y finalizar la solicitud de adicionales$")
    public void confirmRequestOfAditionals() {
        onboardingSolicitudAdicionalPage.finalizeSolicitudeOfAditional();
    }

    @When("^obtengo la llamada al servicio alta de adicional para el prospect que tiene \"([^\"]*)\"$")
    public void getProductRegistrationServiceResponseOfAditional(String prospect) {
        altaAdicionalRequest = AltaAdicionalUtils.getRequestFromRegistrationOfAditionalService(prospect);
        altaAdicionalResponse = AltaAdicionalUtils.getResponseFromRegistrationOfAditionalService(prospect);
        TextUtils.addRequestToReport(altaAdicionalRequest, "AltaAdicionalRequest");
        TextUtils.addResponseToReport(altaAdicionalResponse, "AltaAdicionalResponse");
    }

    @Then("^la aplicacion me muestra un mensaje de solicitud de adicionales exitosa$")
    public void verifyMessageOfSuccessOperationForAditionalRequestIsDisplayedOnUI() {
        Assert.assertTrue("La pagina no muestra mensaje de solicitud de adicionales exitosa",
                onboardingSolicitudAdicionalPage.successMessageIsPresentForAditionals());
    }

    @Then("^el servicio de alta de adicional responde con codigo de exito$")
    public void verifyResponseFromServiceForRegistrationOfAditionalWasSuccesful() {
        ResponseAltaAdicional responseAltaAdicional = AltaAdicionalUtils
                .getDTOFromRegistrationOfAditionalResponse(altaAdicionalResponse);
        Assert.assertEquals("La response del servicio alta Adicional no tiene estado OK", AltaTarjetaUtils.ESTADO_OK,
                responseAltaAdicional.getData().getRow().getEstado().getEstado());
        Assert.assertTrue("La response del servicio alta Adicional no reporto una resolucion exitosa",
                responseAltaAdicional.getData().getRow().getCodigoResolucion().getDescripcion()
                        .contains(AltaTarjetaUtils.DESCRIPCION_EXITOSA));
    }

    @Then("^la request del servicio de alta adicional para el \"([^\"]*)\" tiene parametros validos con respecto al \"([^\"]*)\" y la \"([^\"]*)\"$")
    public void verifyIfRegistrationOfAditionalRequestHasValidParameters(String prospect, String product,
            String brand) {
        RequestAltaAdicional requestOfAditionalRequest = AltaAdicionalUtils
                .getDTOFromRegistrationOfAditionalRequest(altaAdicionalRequest);

        Assert.assertTrue("No se encontro la request del servicio de alta adicional",
                StringUtils.isNotBlank(altaAdicionalRequest));

        Assert.assertEquals("El codigo de operacion no corresponde con el codigo de alta adicional",
                OPERATION_CODE.ALTA_ADICIONAL.getOperationCode(),
                requestOfAditionalRequest.getData().getTitularAdicional().getCodOperacion());

        verifyPrincipalSectionFromRegistrationOfAditionalRequest(requestOfAditionalRequest, prospect, product, brand);

        try {
            verifyFiliatoriosSectionFromRegistrationOfAditionalRequest(requestOfAditionalRequest, prospect);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        verifyComplementariosSectionFromRegistrationOfAditionalRequest(requestOfAditionalRequest, product);
    }

    public void verifyPrincipalSectionFromRegistrationOfAditionalRequest(RequestAltaAdicional requestOfAditionalRequest,
            String prospect, String product, String brand) {
        Principal principal = requestOfAditionalRequest.getData().getTitularAdicional().getPrincipal();

        Assert.assertEquals("El codigo de marca no corresponde con la marca del producto de la oferta",
                BRAND_CODE.getBrandCodeByDescription(brand), principal.getCodMarca());

        Assert.assertEquals("El numero de entidad no corresponde con la marca del producto de la oferta",
                ENTITY_NUMBER.getEntityNumberDescription(brand), principal.getCodMarca());

        String registrationType = (product.contains(PRODUCT_TYPE.VOUCHER.getProductType()))
                ? REGISTRATION_TYPE.VOUCHER.getRegistrationType() : REGISTRATION_TYPE.BATCH.getRegistrationType();
        Assert.assertEquals("El tipo de alta no corresponde con el tipo de producto", registrationType,
                principal.getTipoAlta());

        if (product.contains(PRODUCT_TYPE.VOUCHER.getProductType())) {
            Assert.assertEquals("Cuenta Ban Total no corresponde con la marca del producto de la oferta",
                    RegistrationServicesConstants.BAN_TOTAL_ACCOUNT_VOUCHER, principal.getCuentaBanTotal());

            Assert.assertEquals(
                    "El numero de solicitud de tarjeta no corresponde con la marca del producto de la oferta",
                    RegistrationServicesConstants.BAN_TOTAL_ACCOUNT_VOUCHER, principal.getNroSolicitud());
        }

        Assert.assertNotNull("El codigo de tipo de documento no concuerda con los existentes",
                DOCUMENT_TYPE.valueOf(principal.getNroDoc()));
        if (DOCUMENT_TYPE.valueOf(principal.getNroDoc()) != null) {
            switch (DOCUMENT_TYPE.valueOf(principal.getNroDoc()).getDocType()) {
            case "DNI":
                Assert.assertEquals(
                        "El numero de documento del servicio de alta de adicional no coincide con el numero del prospect cargado previamente",
                        UserProvider.getDNIFromCuil(prospect), principal.getNroDoc());
                break;
            default:
                // Agregar datos en prospect de los otros tipos de coumento
                break;
            }
        }

        Assert.assertNotNull("El codigo de tipo de doc tributario no concuerda con los existentes",
                TRIBU_DOC_TYPE.valueOf(principal.getNroDoc()));
        if (TRIBU_DOC_TYPE.valueOf(principal.getNroDoc()) != null) {
            switch (TRIBU_DOC_TYPE.valueOf(principal.getNroDoc()).getTribuDocType()) {
            case "CUIL":
                Assert.assertEquals(
                        "El numero de documento del servicio de alta de adicional no coincide con el numero del prospect cargado previamente",
                        UserProvider.getCuil(prospect), principal.getNumDocTribu());
                break;
            default:
                // Agregar datos en prospect de CUIT
                break;
            }
        }

        Assert.assertEquals("El valor de porcenLCompra no coincide con la constante esperada",
                RegistrationServicesConstants.ADITIONAL_SERVICE_PERCENTAGE, principal.getPorcenLCompra());

        Assert.assertEquals("El valor de porcentLCCuotas no coincide con la constante esperada",
                RegistrationServicesConstants.ADITIONAL_SERVICE_PERCENTAGE, principal.getPorcenLCCuotas());

        Assert.assertEquals("El valor de porcentLAdel no coincide con la constante esperada",
                RegistrationServicesConstants.ADITIONAL_SERVICE_PERCENTAGE, principal.getPorcenLAdel());

    }

    public void verifyFiliatoriosSectionFromRegistrationOfAditionalRequest(
            RequestAltaAdicional requestOfAditionalRequest, String prospect) throws ParseException {

        Filiatorios filiatorios = requestOfAditionalRequest.getData().getTitularAdicional().getFiliatorios();

        Assert.assertEquals(
                "El nombre de cliente en la request del servicio de alta adicional no es igual al nombre usado en la solicitud de adicional",
                UserProvider.getNombre(prospect) + " " + UserProvider.getApellido(prospect),
                filiatorios.getNomCliente());

        Assert.assertEquals("El codigo de nacionalidad no coincide con el valor constante usado",
                RegistrationServicesConstants.ADITIONAL_SERVICE_COD_NACIONALIDAD, filiatorios.getCodNacionalidad());

        Assert.assertEquals(
                "El nombre embozado en la request del servicio de alta adicional no es igual al nombre usado en la solicitud de adicional",
                UserProvider.getApellido(prospect) + " " + UserProvider.getNombre(prospect),
                DateTimeUtils.parseDateToGivenFormat(filiatorios.getFechaNacimiento(), dateFormatFromProspectFile,
                        RegistrationServicesConstants.ADITIONAL_SERVICE_DATE_FORMAT));

        Assert.assertNotNull("El codigo de estado civil no coincide con los existentes",
                CIVIL_STATUS_CODE.valueOf(filiatorios.getCodEstadoCivil()));

        Assert.assertEquals(
                "La fecha de nacimiento en la request del servicio de alta adicional no es igual a la fecha usada en la solicitud de adicional",
                UserProvider.getFechaNac(prospect), filiatorios.getFechaNacimiento());

        Assert.assertEquals(
                "El genero en la request del servicio de alta adicional no es igual al ingresado en la solicitud de adicional",
                UserProvider.getGenero(prospect), filiatorios.getSexo());
    }

    public void verifyComplementariosSectionFromRegistrationOfAditionalRequest(
            RequestAltaAdicional requestOfAditionalRequest, String product) {

        Complementarios complementarios = requestOfAditionalRequest.getData().getTitularAdicional()
                .getComplementarios();

        SimpleDateFormat format = new SimpleDateFormat(RegistrationServicesConstants.ADITIONAL_SERVICE_DATE_FORMAT);
        Assert.assertEquals(
                "la fecha de solicitud en la request del servicio de alta adicional no es igual a la fecha actual, en que se realizo alta de adicional",
                format.format(new Date()), complementarios.getFechaSolicitud());

        String account = "";
        if (product.contains(PRODUCT_TYPE.VOUCHER.getProductType())) {
            account = RegistrationServicesConstants.BAN_TOTAL_ACCOUNT_VOUCHER;
        } else {
            ResponseAltaInstantaneaMaster responseAltaInstantaneaMaster = AltaVoucherUtils
                    .getDTOFromVoucherRegistrationResponse(altaProductoResponse);
            account = responseAltaInstantaneaMaster.getData().getSolicitud().getCuenta();
        }
        Assert.assertEquals(
                "La cuenta de la request del servicio de alta adicional no corresponde con la cuenta del prospect adicional",
                account);
    }

    /* FIN ALTA DE ADICIONALES */

    @When("^valido el response del servicio que obtiene los antecedentes comerciales el tag Aut_Monotrib sea \"([^\"]*)\"$")
    public void verifyAut_MonotribTagFromCommercialBackgroundServiceIsEqualsTo(String monotax) {
        Assert.assertTrue("El tag <Aut_Monotrib> no contiene el parametro: " + monotax,
                SacnosisUtils.getValueInAutMonotribFromSACNOSISResponse(SACNOSISresponse).equalsIgnoreCase(monotax));
    }

    @When("^valido el response del servicio que obtiene los antecedentes comerciales el tag jubilado sea \"([^\"]*)\"$")
    public void verifyJubiladoTagFromCommercialBackgroundServiceIsEqualsTo(String jubilado) {
        Assert.assertTrue("El tag <Jubilado> no contiene el parametro: " + jubilado,
                SacnosisUtils.getValueInJubiladoFromSACNOSISResponse(SACNOSISresponse).equalsIgnoreCase(jubilado));
    }

    @When("^valido el response del servicio que obtiene los antecedentes comerciales el tag relacion de dependencia sea \"([^\"]*)\"$")
    public void verifyRelacionDependenciaTagFromCommercialBackgroundServiceIsEqualsTo(String employmentRelationship) {
        Assert.assertTrue("El tag <RelDependencia> no contiene el parametro: " + employmentRelationship,
                SacnosisUtils.getValueInRelacionDependenciaFromSACNOSISResponse(SACNOSISresponse)
                        .equalsIgnoreCase(employmentRelationship));
    }

    @When("^valido el response del servicio que obtiene los antecedentes comerciales el tag compromisoOtrosBancos no sea vacio$")
    public void verifyCompromisoOtrosBancosTagIsnotNull() {
        Assert.assertNotNull("El tag <compromisoOtrosBancos> se encuentra vacío",
                SacnosisUtils.SACNOSISResponseGetValueInItem(SACNOSISresponse, "15"));
    }

    @When("^la request de la llamada a oferta crediticia tiene un valor de ActividadID valido \"([^\"]*)\"$")
    public void validateActividadIDFromCreditOfferingServiceRequest(String activityID) {
        Assert.assertTrue("El valor de ActividadID de la aceptacion crediticia no coincide con el valor esperado",
                OfertaCrediticiaUtils.getActividadIdFromRequest(ofertaCrediticiaRequest).equalsIgnoreCase(activityID));

    }

    @When("^valido para mi oferta \"([^\"]*)\" el request de OfertaCrediticia los valores de ActividadID, RentaTitular, Compromiso otros bancos, PoliticaID, Edad y ProvinciaID coincida con los datos de CLI_DatosAnaliticos$")
    public void verifyDataFromCreditOfferingServiceMatchesWithAnalitycsDataTableInDatabase(String offeringContent) {
        Integer cliId = getCliIdFromCuil(UserProvider.getCuil(offeringContent));
        DatoAnalitico datosAnaliticos = ClientDao.getRowFromDatoAnalitico(cliId);

        Assert.assertTrue("ActividadID no coincide con el valor esperado", datosAnaliticos.getActividadID().toString()
                .equals(OfertaCrediticiaUtils.getActividadIdFromRequest(ofertaCrediticiaRequest)));
        Assert.assertTrue("Parámetro CompromisoConOtrosBancos no coincide con el valor esperado",
                datosAnaliticos.getCompromisoOtrosBancos().toString()
                        .contains(OfertaCrediticiaUtils.getCompromisoOtrosBancosFromRequest(ofertaCrediticiaRequest)));
        Assert.assertTrue("Parámetro CompromisoConOtrosBancos no coincide con el valor esperado",
                datosAnaliticos.getRentaTitular().toString()
                        .equals(OfertaCrediticiaUtils.getRentaTitularFromRequest(ofertaCrediticiaRequest)));
        Assert.assertTrue("Parámetro Edad no coincide con el valor esperado", datosAnaliticos.getEdad().toString()
                .equals(OfertaCrediticiaUtils.getEdadFromRequest(ofertaCrediticiaRequest)));
        Assert.assertTrue("Parámetro ProvinciaID no coincide con el valor esperado", datosAnaliticos.getProvinciaId()
                .toString().equals(OfertaCrediticiaUtils.getProvinciaIDFromRequest(ofertaCrediticiaRequest)));
    }

    @Then("^valido el request de OfertaCrediticia donde PoliticaID tenga el valor \"([^\"]*)\"$")
    public void verifyPoliticaIdFromCreditOffering(String politicaId) {
        /* Se necesita mock que datos correctos. Aparece 10 y tiene que ser 0 */
        Assert.assertEquals("Parámetro CompromisoConOtrosBancos no coincide con el valor esperado", politicaId,
                OfertaCrediticiaUtils.getPoliticaIDFromRequest(ofertaCrediticiaRequest));
    }

    @When("^valido que fecha nacimiento, dni y genero esten precargados tomados del servicio que obtiene los antecedentes comerciales$")
    public void verifyPrefillingOfPersonalDataFromCommercialBackgroundServiceResponse() {
        Date fechaNac = SacnosisUtils.getValueInFechaNacimientoFromSACNOSISResponse(SACNOSISresponse);
        Assert.assertTrue("Fecha nacimiento de UI no corresponde con Nosis",
                onboardingOfertaPage.convertSacnosisBirthDateToUIFormatAndValidate(fechaNac));
        Assert.assertTrue("CUIL obtenido de UI no corresponde con Nosis", onboardingOfertaPage.getCUIL()
                .equals(SacnosisUtils.getValueInCUILFromSACNOSISResponse(SACNOSISresponse)));
        Assert.assertTrue("Genero obtenido de UI no corresponde con Nosis", onboardingOfertaPage.getGender()
                .contains(SacnosisUtils.getValueInGeneroFromSACNOSISResponse(SACNOSISresponse)));

    }

    @When("^para mi oferta \"([^\"]*)\" valido que la direccion este precargada si vive en una provincia operativa tomados del servicio que obtiene los antecedentes comerciales$")
    public void verifyPrefillingOfAddressDataFromCommercialBackgroundServiceResponse(String offeringContent) {
        String province = SacnosisUtils.getProvinciaDomicilioFiscal(SACNOSISresponse);
        if (onboardingOfertaPage.validateIfProvinceIsOperative(province)) {
            onboardingOfertaPage.clickOnContinueToAdressButton();
            onboardingOfertaPage.formatAddress();
            if (!onboardingOfertaPage.getSelectedProvinceTextFromDropDown().equalsIgnoreCase("capital federal")) {
                onboardingOfertaPage.formatLocalityInput();
            }
            Assert.assertEquals(onboardingOfertaPage.getZipCode(), (SacnosisUtils.getFiscalZipCode(SACNOSISresponse)));
            String expectedAdress = SacnosisUtils.getFiscalDomicile(SACNOSISresponse).replace(".", "");
            Assert.assertTrue("Domicilio no está precargado",
                    expectedAdress.toLowerCase().contains(onboardingOfertaPage.getAddress().toLowerCase()));
        } else {
            fillAddressDataForHolderInOfferingPage(offeringContent);
        }
    }

    @When("^valido que la relacion laboral este precargada con \"([^\"]*)\"$")
    public void validateEmploymentRelationship(EMPLOYMENT_RELATIONSHIP_TYPE relacionLaboral) {
        onboardingOfertaPage.clickOnContinueToWorkingInfoButton();
        Assert.assertTrue("Relación laboral no es correcta con Sacnosis",
                onboardingOfertaPage.validateIfEmploymentRelationshipIsSelected(relacionLaboral));
    }

    @When("^selecciono una opcion negativa en la condicion PEP$")
    public void selectFalseValueInPEPOption() {
        onboardingOfertaPage.selectPEP("false");
    }

    @Then("^valido que el limite de la tarjeta obtenida de Oferta Crediticia sea el mismo que aparece en la pagina de Oferta$")
    public void validateCreditCardLimit() {
        String creditCardtype = onboardingOfertaPage.getCreditCardTypeText().toLowerCase();
        String limit = onboardingOfertaPage.getCreditCardLimitText();
        if (creditCardtype.contains("visa")) {
            Assert.assertTrue("Límite de tarjeta Visa no es correcto",
                    limit.contains(OfertaCrediticiaUtils.getLimiteTarjetaVisaFromResponse(ofertaCrediticiaResponse)));
        } else {
            Assert.assertTrue("Límite de tarjeta Mastercard no es correcto",
                    limit.contains(OfertaCrediticiaUtils.getLimiteTarjetaMasterFromResponse(ofertaCrediticiaResponse)));
        }
    }

    @Then("^valido que los tags de PaqueteUtilizado y paqueteID de Oferta Crediticia sean los mismos$")
    public void validatePackageIdFromOfferingResponse() {
        Assert.assertEquals("Los paquete ID de Oferta Crediticia no son correctos",
                OfertaCrediticiaUtils.getPaqueteUtilizadoFromResponse(ofertaCrediticiaResponse),
                OfertaCrediticiaUtils.getPaqueteIdFromResponse(ofertaCrediticiaResponse));
    }

    /* OfertaCrediticia: letra y coeficiente */
    @Then("^la request de alta de tarjeta tiene el valor de codigo, porcentaje, y limite correspondientes con el de oferta crediticia$")
    public void verifyDataFromProductRegistrationRequestMatchesDataFromCreditOfferingResponse() {
        ResponseOfertaCrediticia responseOfertaCrediticia = OfertaCrediticiaUtils
                .getDTOFromCreditAcceptationResponse(ofertaCrediticiaResponse);
        RequestAltaInstantaneaMaster responseAltaInstantaneaMaster = AltaVoucherUtils
                .getDTOFromVoucherRegistrationRequest(altaProductoRequest);

        Tarjeta tarjeta = responseOfertaCrediticia.getData().getRow().getResultado().getNormalizacionTarjetas()
                .getMaster().getTarjeta();
        RegistroAltaBATCH registroAltaBATCH = responseAltaInstantaneaMaster.getData().getPrincipal()
                .getRegistroAltaBATCH();

        Assert.assertEquals(
                "El codigo de la request de alta producto coincide con el de la response de alta crediticia",
                tarjeta.getCodigo(), registroAltaBATCH.getLimiteCompra());

        Assert.assertEquals("El porcentaje de la request de alta producto corresponde con response de alta crediticia",
                Double.parseDouble(tarjeta.getPorcentaje()) / 100.00,
                Double.parseDouble(registroAltaBATCH.getCoeficienteLimiteCompra()), 2);

        Assert.assertEquals(
                "El limite de la request de alta producto coincide con el de la response de alta crediticia",
                tarjeta.getLimite(), registroAltaBATCH.getLimiteCredito());
    }

    @Then("^si la response de oferta crediticia informa de que no hay ofertas para el cliente la aplicacion devuelve un \"([^\"]*)\"$")
    public void validateNullOrEmptyParamsFromOfertaCrediticiaResponse(String message) {
        ResponseOfertaCrediticia responseOfertaCrediticia = OfertaCrediticiaUtils
                .getDTOFromCreditAcceptationResponse(ofertaCrediticiaResponse);
        Tarjeta tarjeta = responseOfertaCrediticia.getData().getRow().getResultado().getNormalizacionTarjetas()
                .getMaster().getTarjeta();
        if (tarjeta == null || tarjeta.getLimite().equals("0")) {
            verifyErrorMessageForNoOfferingAvailableIsDisplayedOnUI(message);
        }
    }

    @Then("^request de llamada a oferta crediticia tiene un valor de CanalVentaID valido \"([^\"]*)\"$")
    public void validate_canal_venta_id_from_OfertaCrediticia(String canalVentaId) {
        Assert.assertTrue("El valor de ActividadID de aceptacion crediticia no coincide con el valor esperado",
                OfertaCrediticiaUtils.getCanalVentaIDFromRequest(ofertaCrediticiaRequest)
                        .equalsIgnoreCase(canalVentaId));
    }

    @Then("^request de llamada a oferta crediticia tiene un valor de SegmentoID valido \"([^\"]*)\"$")
    public void validate_value_canal_segmento_id_from_oferta_crediticia(String segmentoId) {
        // Falta mock de cuils con SegmentoID = 1
        Assert.assertTrue("El valor de SegmentoID de aceptacion crediticia no coincide con el valor esperado",
                OfertaCrediticiaUtils.getSegmentoIDFromRequest(ofertaCrediticiaRequest).equalsIgnoreCase(segmentoId));
    }

    @Then("^request de llamada a oferta crediticia tiene un valor de solicitadoDescubierto \"([^\"]*)\", solicitadoVisaLimite \"([^\"]*)\" y solicitadoMasterLimite \"([^\"]*)\"$")
    public void validate_value_descubierto_visa_master_descubierto_from_oferta_crediticia(String solicitadoDescubierto,
            String solicitadoVisaLimite, String solicitadoMasterLimite) {
        Assert.assertTrue(
                "El valor de <solicitadoDescubierto> de aceptacion crediticia no coincide con el valor esperado",
                OfertaCrediticiaUtils.getSolicitadoDescubiertoFromRequest(ofertaCrediticiaRequest)
                        .equalsIgnoreCase(solicitadoDescubierto));
        Assert.assertTrue(
                "El valor de <solicitadoVisaLimite> de aceptacion crediticia no coincide con el valor esperado",
                OfertaCrediticiaUtils.getSolicitadoVisaLimiteFromRequest(ofertaCrediticiaRequest)
                        .equalsIgnoreCase(solicitadoVisaLimite));
        Assert.assertTrue(
                "El valor de <solicitadoMasterLimite> de aceptacion crediticia no coincide con el valor esperado",
                OfertaCrediticiaUtils.getSolicitadoMasterLimiteFromRequest(ofertaCrediticiaRequest)
                        .equalsIgnoreCase(solicitadoMasterLimite));
    }

    @Then("^request de llamada a oferta crediticia tiene un valor de solicitadoPrestamoCapital \"([^\"]*)\", solicitadoPrestamoPlazo \"([^\"]*)\" y solicitadoPrestamoTasa \"([^\"]*)\"$")
    public void validate_value_capital_plazo_tasa_from_oferta_crediticia(String solicitadoPrestamoCapital,
            String solicitadoPrestamoPlazo, String solicitadoPrestamoTasa) {
        Assert.assertTrue(
                "El valor de <solicitadoPrestamoCapital> de aceptacion crediticia no coincide con el valor esperado",
                OfertaCrediticiaUtils.getSolicitadoPrestamoCapitalFromRequest(ofertaCrediticiaRequest)
                        .equalsIgnoreCase(solicitadoPrestamoCapital));
        Assert.assertTrue(
                "El valor de <solicitadoPrestamoPlazo> de aceptacion crediticia no coincide con el valor esperado",
                OfertaCrediticiaUtils.getSolicitadoPrestamoPlazoFromRequest(ofertaCrediticiaRequest)
                        .equalsIgnoreCase(solicitadoPrestamoPlazo));
        Assert.assertTrue(
                "El valor de <solicitadoPrestamoTasa> de aceptacion crediticia no coincide con el valor esperado",
                OfertaCrediticiaUtils.getSolicitadoPrestamoTasaFromRequest(ofertaCrediticiaRequest)
                        .equalsIgnoreCase(solicitadoPrestamoTasa));
    }

    @Then("^request de llamada a oferta crediticia tiene un valor de empresaID \"([^\"]*)\", acuerdoActual \"([^\"]*)\" y paqueteActual \"([^\"]*)\"$")
    public void validate_empresaId_acuerdoActual_paqueteActual_from_oferta_crediticia(String empresaID,
            String acuerdoActual, String paqueteActual) {
        Assert.assertTrue("El valor de <empresaID> de aceptacion crediticia no coincide con el valor esperado",
                OfertaCrediticiaUtils.getEmpresaIdFromRequest(ofertaCrediticiaRequest).equalsIgnoreCase(empresaID));
        Assert.assertTrue("El valor de <acuerdoActual> de aceptacion crediticia no coincide con el valor esperado",
                OfertaCrediticiaUtils.getAcuerdoActualFromRequest(ofertaCrediticiaRequest)
                        .equalsIgnoreCase(acuerdoActual));
        Assert.assertTrue("El valor de <paqueteActual> de aceptacion crediticia no coincide con el valor esperado",
                OfertaCrediticiaUtils.getPaqueteActualFromRequest(ofertaCrediticiaRequest)
                        .equalsIgnoreCase(paqueteActual));
    }

    @Then("^request de llamada a oferta crediticia tiene un valor de sucursalID \"([^\"]*)\", rentaConyuge \"([^\"]*)\"$")
    public void validate_sucursalID_paqueteActual_rentaConyuge_from_oferta_crediticia(String sucursalID,
            String rentaConyuge) {
        Assert.assertTrue("El valor de <sucursalID> de aceptacion crediticia no coincide con el valor esperado",
                OfertaCrediticiaUtils.getSucursalIDFromRequest(ofertaCrediticiaRequest).equalsIgnoreCase(sucursalID));
        Assert.assertTrue("El valor de <rentaConyuge> de aceptacion crediticia no coincide con el valor esperado",
                OfertaCrediticiaUtils.getRentaConyugeFromRequest(ofertaCrediticiaRequest)
                        .equalsIgnoreCase(rentaConyuge));
    }

    /* CONSULTA SCORING SUPERVIELLE */

    @When("^obtengo la llamada al servicio de consulta scoring Supervielle para el prospect que tiene \"([^\"]*)\"$")
    public void getSupervielleScoringConsult(String status) {
        consultaScoringSupervielle = ConsultaScoringSupervielleUtils.getResponseFromCreditAcceptationService(status);
        TextUtils.addRequestToReport(consultaScoringSupervielle, "OfertaCrediticiaRequest");
    }

    @When("^valido en response de Consulta scoring Supervielle que el score de sea mayor a 0 y menor a 999$")
    public void validate_score_from_scoring_Supervielle() {
        /* Fallará por que necesita mock nuevo */
        double scoreFromResponse = ConsultaScoringSupervielleUtils.getScoreFromResponse(consultaScoringSupervielle);
        Assert.assertTrue("El Score obtenido del response no es correcto",
                scoreFromResponse > 0 && scoreFromResponse <= 999);
    }

    /* CONSULTA SCORING SUPERVIELLE */

    @Then("^valido que relacion laboral para mi \"([^\"]*)\" sea (\\d+)$")
    public void validate_employment_relationship(String offeringContent, Integer expectedRelLab) {
        Integer cliId = getCliIdFromCuil(UserProvider.getCuil(offeringContent));
        Integer relacionLaboral = ClientDao.getRowFromRelacionLaboral(cliId);
        Assert.assertEquals("Relación laboral no es correcta", expectedRelLab, relacionLaboral);
    }

    @Then("^valido que el valor de NSE en servicio que obtiene los antecedentes comerciales exista en al tabla de CCR_NivelesSocioEconomicos$")
    public void validate_NSEValue_on_nivelesSocioEconomicos_Table() {
        String sacnosisCategory = SacnosisUtils.getCategoryFromNSE(SACNOSISresponse);
        Assert.assertNotNull("Nivel Socio Economico no existe en Base de Datos",
                CalculoRentaDao.getNivelSocioEconomico(sacnosisCategory).getCategoria());
    }

    @Then("^valido que el valor de la categoria de NSE en servicio que obtiene los antecedentes comerciales sea el mismo que en tabla Datos Analiticos para mi \"([^\"]*)\"$")
    public void validate_NSECategoryValue_on_DatosAnaliticos_Table(String prospect) {
        Integer cliId = getCliIdFromCuil(UserProvider.getCuil(prospect));
        String sacnosisCategory = SacnosisUtils.getCategoryFromNSE(SACNOSISresponse);
        Integer categoryEntry = CalculoRentaDao.getNivelSocioEconomico(sacnosisCategory).getIngresoDeterminado()
                .intValue();
        DatoAnalitico analyticalData = ClientDao.getRowFromDatoAnalitico(cliId);
        Assert.assertEquals("Categoria de NSE no es correcta", sacnosisCategory, analyticalData.getCategoriaNSE());
        Assert.assertEquals("Valor de categoría de NSE no es correcta", categoryEntry,
                analyticalData.getRentaTitular());
    }

    @Then("^valido el estado (\\d+) y resultado \"([^\"]*)\" en Consulta Cuestionario Veraz$")
    public void validate_questionaryVeraz(Integer state, String result) {
        Assert.assertEquals("Estado del response Consulta Cuestionario Veraz no es correcto", state,
                ConsultaCuestionarioVerazUtils.getStateFromResponse(consultaCuestionarioVerazResponse));
        Assert.assertEquals("Resultado del response Consulta Cuestionario Veraz no es el esperado", result,
                ConsultaCuestionarioVerazUtils.getResultFromResponse(consultaCuestionarioVerazResponse));
    }

    @When("^obtengo la respuesta del servicio Consulta Cuestionario Veraz para el prospect que tiene \"([^\"]*)\"$")
    public void getResponseFromConsultaCuestionarioVeraz(String oferta) {
        consultaCuestionarioVerazResponse = ConsultaCuestionarioVerazUtils
                .getResponseFromValidarCuestionarioVeraz(oferta);
        TextUtils.addResponseToReport(consultaCuestionarioVerazResponse, "ConsultaCuestionarioVerazResponse");
    }

    /* AGENTES */
    @Given("^me encuentro en la pagina de inicio de Agentes de \"([^\"]*)\"$")
    public void initializeAgentesHomePage(String product)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        WebDriverUtils.initializePageInstance(driver, AutomationProperties.getString(product));
        agentesHomePage = new AgentesHomePage(driver);
    }

    @When("^me logueo con las credenciales de Agente Externo \"([^\"]*)\"$")
    public void fillAgentsCredentials(String credentials) {
        agentesHomePage.clickOnExternalAgent();
        agentesHomePage.loginToAgentPage(credentials);
    }

    @When("^completo los datos de un prospect que tiene \"([^\"]*)\" para descubrir oferta en agentes$")
    public void fillProspectData(String offeringContent) {
        agentesHomePage.fillProspectData(offeringContent);
    }

    @When("^selecciono la opcion disponible para descubrir oferta en agentes$")
    public void discoverOfferingContentFromAgentes() {
        agentesOfertasPage = agentesHomePage.clickOnOptionToDiscoverOfferingContent();
    }

    @When("^selecciono una \"([^\"]*)\" de las que tenga disponible el prospect$")
    public void selectOfferingFromAgentes(String offering) {
        onboardingOfertaPage = agentesOfertasPage.selectOfferingFromList(offering);
    }

    @Then("^cada una de las ofertas disponibles que tenga el prospect aparecen deshabilitadas$")
    public void validateOfferOptionsAreDisabled() {
        int offerOptions = agentesOfertasPage.getSizeOfOfferOptionsDisplayed();
        for (int i = 0; i < offerOptions; i++) {
            Assert.assertFalse(
                    "Alguna de las ofertas disponibles no estaba deshabilitada y abrio una pestaña de onboarding",
                    agentesOfertasPage.offerOptionOpensOnboardingtabAfterClicking(i));
        }
    }

    @Then("^si la response de oferta crediticia informa de que no hay oferta para el \"([^\"]*)\" la oferta aparece deshabilitada en Agentes$")
    public void validateOfferIsDisabledWhenParamsFromOfertaCrediticiaResponseAreNull(String product) {
        ResponseOfertaCrediticia responseOfertaCrediticia = OfertaCrediticiaUtils
                .getDTOFromCreditAcceptationResponse(ofertaCrediticiaResponse);
        Tarjeta tarjeta = responseOfertaCrediticia.getData().getRow().getResultado().getNormalizacionTarjetas()
                .getMaster().getTarjeta();
        if (tarjeta == null || tarjeta.getLimite().equals("0")) {
            agentesOfertasPage.offerOptionOpensOnboardingtabAfterClicking(product);
        }
    }

    @Then("^valido que el limite de tarjeta en Oferta Crediticia coincida con Oferta de Agentes$")
    public void validateCreditCardLimits() {
        String limit = agentesOfertasPage.getFirstCreditCardLimitText();
        Assert.assertTrue("Límite de tarjeta no es correcto", limit
                .contains(OfertaCrediticiaUtils.getLimiteTarjetaVisaFromResponse(ofertaCrediticiaResponse))
                || limit.contains(OfertaCrediticiaUtils.getLimiteTarjetaMasterFromResponse(ofertaCrediticiaResponse)));
    }
    /* AGENTES */

    /* FORMULARIO DE ALTA */

    @Then("^el bloque de datos del cliente corresponde a los obtenidos del servicio que obtiene los antecedentes comerciales para el prospect$")
    public void verifyClientDataInOferringStepMatchesdataFromService() throws ParseException {
        Dato sacnosisDato = SacnosisUtils.getFirstDatoFromResponseSACNOSIS(SACNOSISresponse);
        Assert.assertTrue("El nombre del cliente no se cargo correctamente en la UI", onboardingOfertaPage
                .clientNameIsDisplayedOnUI(sacnosisDato.getNombre() + " " + sacnosisDato.getApellido()));
        verifyPrefillingOfPersonalDataFromCommercialBackgroundServiceResponse();
    }

    @Then("^la seccion de datos personales aparece precargada con los datos validos$")
    public void verifyAutoFillingOfPersonalDataWithDefaultData() {
        Assert.assertEquals(ClientRegistrationFormConstants.PAIS_NACIMIENTO_DEFAULT_VALUE,
                onboardingOfertaPage.getTextFromCountryOfBirth());
        Assert.assertEquals(ClientRegistrationFormConstants.LUGAR_NACIMIENTO_DEFAULT_VALUE,
                onboardingOfertaPage.getTextFromPlaceOfBirth());
        Assert.assertEquals(ClientRegistrationFormConstants.ESTADO_CIVIL_DEFAULT_VALUE,
                onboardingOfertaPage.getTextFromCivilStatusSelector());
    }

    @Then("^la pagina no me permite el acceso a la carga de domicilio$")
    public void AddressInfoSectionHasNotLoaded() {
        Assert.assertFalse(
                "La seccion de carga de domicilio cargo correctamente a pesar de no haber avanzado desde datos personales",
                onboardingOfertaPage.addressInfoHasLoaded());
    }

    @Then("^la pagina no me permite el acceso a la carga de informacion laboral$")
    public void employmentRelationshipInfoSectionHasNotLoaded() {
        Assert.assertFalse(
                "La seccion de carga de informacion laboral cargo correctamente a pesar de no haber avanzado desde domicilio",
                onboardingOfertaPage.employmentRelationshipInfoHasLoaded());
    }

    @Then("^la pagina me permite el acceso a la carga de domicilio$")
    public void AddressInfoSectionHasLoaded() {
        Assert.assertTrue("La seccion de carga de domicilio no cargo correctamente",
                onboardingOfertaPage.addressInfoHasLoaded());
    }

    @When("^borro los datos del campo de numero de celular$")
    public void clearDataFromTelephoneNumberField() {
        onboardingOfertaPage.clearDataFromTelephoneNumberInput();
    }

    @Then("^la pagina me permite el acceso a la seleccion de sucursal$")
    public void verifyBankBranchesSelectionPageHasLoaded() {
        Assert.assertTrue("La pagina de seleccion de sucursal cargo correctamente a pesar de los datos no validos",
                onboardingSeleccionDeSucursalPage.isLoaded());
    }

    @Then("^la pagina me muestra un mensaje de error de numero de telefono no valido$")
    public void verifyErrorMessageForInvalidTelephoneNumberIsDisplayedOnUI() {
        Assert.assertTrue("No aparecio mensaje de error alguno para la direccion no valida",
                onboardingOfertaPage.errorMessageForInvalidTelephoneNumberIsDisplayed());
    }

    @Then("^la pagina me muestra un mensaje de error de que tengo seleccionar una opcion de PEP$")
    public void verifyErrorMessageForNoPEPOptionSelectedIsDisplayedOnUI() {
        Assert.assertTrue("No aparecio mensaje de error alguno para la direccion no valida",
                onboardingOfertaPage.errorMessageForNoPEPOptionSelectedIsDisplayed());
    }

    @Then("^la pagina no me permite el acceso a la seleccion de sucursal$")
    public void verifyBankBranchessSelectionPageHasNotLoaded() {
        Assert.assertTrue("La pagina de seleccion de sucursal cargo correctamente a pesar de los datos no validos",
                onboardingOfertaPage.isLoaded());
    }
    /* FIN DE FORMULARIO DE ALTA */

    /* INICIO PAGINA INICIAL DE OFERTAS */
    @Then("^la marca de la oferta ofrecida en la UI corresponde con \"([^\"]*)\"$")
    public void validateBranchOfProductInUIIsCorrect(String brand) {
        Assert.assertTrue("La marca de tarjeta en la UI no aparece o no es la esperada",
                onboardingOfertaPage.productBrandDisplayedInUIIsCorrect(brand));
    }

    @Then("^los textos de costos de comision aparecen en la UI$")
    public void validateBlocksOfTextsForCostsInUI() {
        Assert.assertTrue("Los elementos de textos descriptivos de costos no aparecen en la UI",
                onboardingOfertaPage.blocksOfTextsForCostsAreDisplayedInUI());
    }

    @Then("^los bloques de costos de comision aparecen en la UI$")
    public void validateBlocksOfCostsInUI() {
        Assert.assertTrue("Los elementos de costos respectivos no aparecen en la UI",
                onboardingOfertaPage.blocksOfCostsAreDisplayedInUI());
    }

    @When("^realizo mouse hover sobre tooltip comision de Renovacion Anual$")
    public void mouseHoverTooltipAnualRenovation() {
        onboardingOfertaPage.mouseOverTooltipAnualRenovation();
    }

    @Then("^valido que el tooltip comision de Renovacion anual sea visible$")
    public void validateMouseHoverTooltipAnualRenovation() {
        Assert.assertTrue("Tooltip de Renovación Anual no está visible",
                onboardingOfertaPage.validateTooltipAnualRenovationIsDisplayed());
    }

    @When("^realizo mouse hover sobre tooltip comision de Administracion$")
    public void mouseHoverTooltipAdministrationRenovation() {
        onboardingOfertaPage.mouseOverTooltipAdministrationRenovation();
    }

    @Then("^valido que el tooltip comision de Renovacion Administrativa sea visible$")
    public void validateMouseHoverTooltipAdministrationRenovation() {
        Assert.assertTrue("Tooltip comisión de Renovación Admisnitrativa no está visible",
                onboardingOfertaPage.validateTooltipAdministrationRenovationIsDisplayed());
    }
    /* FIN PAGINA INICIAL DE OFERTAS */
}
