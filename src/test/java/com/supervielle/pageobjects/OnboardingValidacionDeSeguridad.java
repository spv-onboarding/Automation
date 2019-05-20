package com.supervielle.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.supervielle.backend.data.ClientDao;
import com.supervielle.backend.data.EmailDao;
import com.supervielle.framework.web.PageObjectBase;

public class OnboardingValidacionDeSeguridad extends PageObjectBase {

    private final String PASO_BARRA_NAV_XPATH = "//div[contains(@class,'active')]//span[contains(text(),'VALIDACIÓN')]//span[contains(text(),'DE IDENTIDAD')]";
    private final String CONTINUAR_BUTTON_CSS = "#step-data button.btn-white-red";
    private final String VERIFICAR_PAIS_DE_NACIMIENTO_CSS = "input[name='pregunta184' ][value='1'] ~ span.circle";
    private final String VERIFICAR_TARJETA_CSS = "input[name='pregunta193' ][value='1'] ~ span.circle";
    private final String VERIFICAR_BANCO_CAJA_CSS = "input[name='pregunta185'][value='3'] ~ span.circle";
    private final String VERIFICAR_CODIGO_NAME = "emailCodeValidation";
    private final String CONTINUAR_PAIS_NACIMIENTO_XPATH = "//button[@index-slide='0' and contains(text(),'Continuar')]";
    private final String CONTINUAR_CAJA_SEGURIDAD_XPATH = "//button[@index-slide='2' and contains(text(),'Continuar')]";
    private final String CONTINUAR_TARJETA_XPATH = "//button[@index-slide='1' and contains(text(),'Continuar')]";

    @FindBy(css = CONTINUAR_BUTTON_CSS)
    private WebElement continueButton;

    @FindBy(css = VERIFICAR_PAIS_DE_NACIMIENTO_CSS)
    private WebElement countryOfBirthCheckbox;

    @FindBy(css = VERIFICAR_TARJETA_CSS)
    private WebElement cardCheckbox;

    @FindBy(css = VERIFICAR_BANCO_CAJA_CSS)
    private WebElement bankSafeBoxCheckbox;

    @FindBy(xpath = CONTINUAR_PAIS_NACIMIENTO_XPATH)
    private WebElement continueButtonFromCountryOfBirth;

    @FindBy(xpath = CONTINUAR_CAJA_SEGURIDAD_XPATH)
    private WebElement continueButtonFromSafeBox;

    @FindBy(xpath = CONTINUAR_TARJETA_XPATH)
    private WebElement continueButtonFromCard;

    @FindBy(name = VERIFICAR_CODIGO_NAME)
    private WebElement validateCodeInput;

    
    public OnboardingValidacionDeSeguridad(WebDriver driver) {
        super(driver);
    }

    public boolean stepIsActiveInNavBar(){
        return getWebDriverUtils().waitForElementToBeVisible(By.xpath(PASO_BARRA_NAV_XPATH));
    }
    
    public boolean firstStepOfSecurityQuestionsHasLoaded() {
        return getWebDriverUtils().waitForElementToBePresent(countryOfBirthCheckbox, TIME_AFTER_LOAD);
    }

    public void selectCountryOfBirth() {
        getWebDriverUtils().waitSeconds(TIME_OUT_FOR_PAGE_LOAD);
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

    public OnboardingConfirmacionPage clickOnContinueButtonFromSafeBox() {
        click(continueButtonFromSafeBox);
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        return new OnboardingConfirmacionPage(getDriver());
    }

    public void clickButtonFromCard() {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        click(continueButtonFromCard);
    }

    @Override
    protected By getPageLocator() {
        return By.xpath(VERIFICAR_PAIS_DE_NACIMIENTO_CSS);
    }

}
