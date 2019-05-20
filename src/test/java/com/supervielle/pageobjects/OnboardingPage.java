package com.supervielle.pageobjects;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.supervielle.backend.data.ClientDao;
import com.supervielle.backend.data.EmailDao;
import com.supervielle.framework.tdm.UserProvider;
import com.supervielle.framework.utils.NumberUtils;
import com.supervielle.framework.web.PageObjectBase;

public class OnboardingPage extends PageObjectBase {

    private final String BANNER_ID = "banners-carousel";
    private final String HACETE_CLIENTE_XPATH = "//ul[contains(@class,'navbar-nav')]/li/a[@href='#//abrir-cuenta']";
    private final String NOMBRE_NAME = "name";
    private final String APELLIDO_NAME = "lastName";
    private final String CUIL_NAME = "cuil";
    private final String GENERO_CHECKBOX = "input[value='#TEXT'] ~ span.circle";
    private final String MAIL_NAME = "mail";
    private final String CODAREA_ID = "codigoTelefono";
    private final String CELULAR_ID = "celularTelefono";
    private final String CONTINUAR_BUTTON_CSS = "#step-data button.btn-white-red";
    private final String VERIFICAR_PAIS_DE_NACIMIENTO_CSS = "input[name='pregunta184' ][value='1'] ~ span.circle";
 // private final String VERIFICAR_TARJETA_CSS = "input[name='pregunta193' ][value='1'] ~ span.circle";
    private final String VERIFICAR_TARJETA_CSS = "input[name='pregunta268' ][value='1'] ~ span.circle";
    private final String VERIFICAR_BANCO_CAJA_CSS = "input[name='pregunta185'][value='3'] ~ span.circle";
    private final String VERIFICAR_CODIGO_NAME = "emailCodeValidation";
    private final String CONTINUAR_PAIS_NACIMIENTO_XPATH = "//button[@index-slide='0' and contains(text(),'Continuar')]";
    private final String CONTINUAR_CAJA_SEGURIDAD_XPATH = "//button[@index-slide='2' and contains(text(),'Continuar')]";
    private final String CONTINUAR_TARJETA_XPATH = "//button[@index-slide='1' and contains(text(),'Continuar')]";
    private final String VALIDAR_CODIGO_ID = "email-validation-code";
    private final String MSJ_ERROR_TELEFONO = ".telefono-celular .msj-error-field";
    private final String MSJ_USUARIO_BLOQUEADO = "#blocked-user .step-subtitle-normal";
    private final String GENERO_CONTAINER = "#sexo-container span.circle";
    private final String MSJ_USUARIO_SIN_OFERTA = "#nooffer-validation .nooffer-paragraph";
    private final String MSJ_USUARIO_ALTA_EXISTENTE = "#blocked-user .step-subtitle-normal";
    
    @FindBy(xpath = HACETE_CLIENTE_XPATH)
    private WebElement haceteSocioLink;

    @FindBy(name = NOMBRE_NAME)
    private WebElement nameInput;

    @FindBy(name = APELLIDO_NAME)
    private WebElement surnameInput;

    @FindBy(name = CUIL_NAME)
    private WebElement cuilInput;

    @FindBy(css = GENERO_CHECKBOX)
    private WebElement genderCheckbox;

    @FindBy(name = MAIL_NAME)
    private WebElement emailInput;

    @FindBy(id = CODAREA_ID)
    private WebElement areaCodeInput;

    @FindBy(id = CELULAR_ID)
    private WebElement telephoneInput;

    @FindBy(css = CONTINUAR_BUTTON_CSS)
    private WebElement continueButton;

    @FindBy(css = VERIFICAR_PAIS_DE_NACIMIENTO_CSS)
    private WebElement countryOfBirthCheckbox;

    @FindBy(css = VERIFICAR_TARJETA_CSS)
    private WebElement cardCheckbox;

    @FindBy(css = VERIFICAR_BANCO_CAJA_CSS)
    private WebElement bankSafeBoxCheckbox;

    @FindBy(name = VERIFICAR_CODIGO_NAME)
    private WebElement validateCodeInput;

    @FindBy(xpath = CONTINUAR_PAIS_NACIMIENTO_XPATH)
    private WebElement continueButtonFromCountryOfBirth;

    @FindBy(xpath = CONTINUAR_CAJA_SEGURIDAD_XPATH)
    private WebElement continueButtonFromSafeBox;

    @FindBy(xpath = CONTINUAR_TARJETA_XPATH)
    private WebElement continueButtonFromCard;

    @FindBy(id = VALIDAR_CODIGO_ID)
    private WebElement validateCodeButton;

    @FindBy(css = MSJ_ERROR_TELEFONO)
    private WebElement telephoneErrorMessage;

    @FindBy(css = MSJ_USUARIO_BLOQUEADO)
    private WebElement blockedUserMessage;

    @FindBy(css = GENERO_CONTAINER)
    private WebElement genderCheckboxContainer;

    @FindBy(css = MSJ_USUARIO_SIN_OFERTA)
    private WebElement userWithoutOfferingAccessMessage;

    @FindBy(css = MSJ_USUARIO_ALTA_EXISTENTE)
    private WebElement activeCreditCardProcessMessage;

    public OnboardingPage(WebDriver driver) {
        super(driver);
    }

    public void fillProspectData(String prospect) throws EncryptedDocumentException {
        EmailDao.setEmailToNull(UserProvider.getCuil(prospect), UserProvider.getEmail(prospect));
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        ClientDao.cleanClientRegistrationForGivenCuil(Long.parseLong(UserProvider.getCuil(prospect)));
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        waitForLoadingElementToDissapear();
        System.out.println("Cuil: " + UserProvider.getCuil(prospect));
        System.out.println("Nombre: " + UserProvider.getNombre(prospect));
        System.out.println("Apellido: " + UserProvider.getApellido(prospect));
        System.out.println("Sexo: " + UserProvider.getGenero(prospect));
        System.out.println("Email: " + UserProvider.getEmail(prospect));
        System.out.println("Codigo de Area: " + UserProvider.getCodArea(prospect));
        System.out.println("Telefono: " + UserProvider.getTelefono(prospect));

        typeOnNameInput(UserProvider.getNombre(prospect));
        typeOnLastNameInput(UserProvider.getApellido(prospect));
        selectGender(UserProvider.getGenero(prospect));
        typeOnCuilInput(UserProvider.getCuil(prospect));
        typeOnMailInput(UserProvider.getEmail(prospect));
        typeOnAreaCodeInput(UserProvider.getCodArea(prospect));
        typeOnTelephoneInput(UserProvider.getTelefono(prospect));
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        clickOnContinueButton();
        getWebDriverUtils().waitSeconds(TIME_AFTER_SERVICES_CALL);
    }

    public void typeOnNameInput(String name) {
        typeOnField(nameInput, name);
    }

    public void typeOnLastNameInput(String lastName) {
        typeOnField(surnameInput, lastName);
    }

    public void typeOnCuilInput(String cuil) {
        typeOnField(cuilInput, cuil);
    }

    public void typeOnMailInput(String email) {
        typeOnField(emailInput, email);
    }

    public void typeOnAreaCodeInput(String areaCode) {
        typeOnField(areaCodeInput, areaCode);
    }

    public void typeOnTelephoneInput(String telephone) {
        typeOnField(telephoneInput, telephone);
    }

    public void selectGender(String provider) {
        String genero = GENERO_CHECKBOX.replace("#TEXT", provider);
        click(getDriver().findElement(By.cssSelector(genero)));
    }

    public boolean firstStepOfSecurityQuestionsHasLoaded() {
        return getWebDriverUtils().waitForElementToBePresent(countryOfBirthCheckbox, TIME_AFTER_LOAD);
    }

    public void selectCountryOfBirth() {
        getWebDriverUtils().waitSeconds(TIME_AFTER_LOAD);
        click(countryOfBirthCheckbox);
    }

    public void clickOnContinueButton() {
        click(continueButton);
    }

    public void selectCard() {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        click(cardCheckbox);
    }

    public void selectSafeBoxOfBank() {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        click(bankSafeBoxCheckbox);
    }

    public void fillValidationCodeFromEmail(String cuil) {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        Integer cliId = ClientDao.getCliIdFromCuil(cuil);
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        String codigo = EmailDao.getToken(cliId);
        getWebDriverUtils().waitSeconds(TIME_AFTER_LOAD);
        System.out.println("Codigo usado: " + codigo);
        typeOnField(validateCodeInput, codigo);
    }

    public void clickOnContinueButtonFromCountryOfBirth() {
        click(continueButtonFromCountryOfBirth);
    }

    public void clickOnContinueButtonFromSafeBox() {
        click(continueButtonFromSafeBox);
    }

    public void clickButtonFromCard() {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        click(continueButtonFromCard);
    }

    public OnboardingOfertaPage clickOnValidateCodeButton() {
        click(validateCodeButton);
        getWebDriverUtils().waitSeconds(TIME_AFTER_LOAD);
        return new OnboardingOfertaPage(getDriver());
    }

    public String getTextFromCuilInputElement() {
        getWebDriverUtils().waitForElementToBePresent(cuilInput, TIME_AFTER_LOAD);
        return cuilInput.getAttribute("value").replaceAll("-", "");
    }

    public boolean isGenderOptionSelected(String gender) {
        WebElement generoOption = getDriver().findElement(By.cssSelector(GENERO_CHECKBOX.replace("#TEXT", gender)));
        return generoOption.isSelected();
    }

    @Override
    protected By getPageLocator() {
        return By.xpath(HACETE_CLIENTE_XPATH);
    }

    public void clickOnOptionForClientRegistration() {
        getWebDriverUtils().waitSeconds(TIME_AFTER_LOAD);
        waitForLoadingElementToDissapear();
        getWebDriverUtils().waitForElementLocatedToBePresent(By.id(BANNER_ID), TIME_OUT_FOR_PAGE_LOAD);
        click(haceteSocioLink);
        getWebDriverUtils().waitSeconds(TIME_AFTER_LOAD);
    }

    public void fillAreaCode(int digit) {
        areaCodeInput.clear();
        typeOnField(areaCodeInput, NumberUtils.generateRandomNumericString(digit));
    }

    public int getLengthOfAreaCode() {
        return areaCodeInput.getAttribute("value").length();
    }

    public boolean isErrorMessageDisplayed() {
        getWebDriverUtils().waitForElementLocatedToBePresent(By.cssSelector(MSJ_ERROR_TELEFONO), TIME_AFTER_CLICK);
        return telephoneErrorMessage.isDisplayed();
    }

    public void fillTelephoneNumber(int digit) {
        telephoneInput.clear();
        typeOnField(telephoneInput, NumberUtils.generateRandomNumericString(digit));
    }

    public int getLengthOfTelephoneNumber() {
        return telephoneInput.getAttribute("value").length();
    }

    public boolean prospectDataLoadViewIsLoaded() {
        getWebDriverUtils().waitForElementToBePresent(cuilInput, TIME_AFTER_LOAD);
        return cuilInput.isDisplayed();
    }

    public String getMessageForBlockedUser() {
        getWebDriverUtils().waitSeconds(TIME_OUT_FOR_PAGE_LOAD);
        blockedUserMessage.isDisplayed();
        return blockedUserMessage.getText();
    }

    public boolean personalDataFieldsAreDisplayed() {
        emailInput.isDisplayed();
        cuilInput.isDisplayed();
        nameInput.isDisplayed();
        surnameInput.isDisplayed();
        telephoneInput.isDisplayed();
        areaCodeInput.isDisplayed();
        return getDriver().findElements(By.cssSelector(GENERO_CONTAINER)).size() == 2;
    }

    public String getMessageForUserWithoutOfferingAccess() {
        getWebDriverUtils().waitSeconds(TIME_OUT_FOR_PAGE_LOAD);
        userWithoutOfferingAccessMessage.isDisplayed();
        return userWithoutOfferingAccessMessage.getText();
    }

    public String getActiveCreditCardProcessMessage() {
        getWebDriverUtils().waitForElementToBePresent(activeCreditCardProcessMessage, 2);
        activeCreditCardProcessMessage.isDisplayed();
        return activeCreditCardProcessMessage.getText();
    }

}
