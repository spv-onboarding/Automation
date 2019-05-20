package com.supervielle.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.supervielle.framework.web.PageObjectBase;

public class OnboardingConfirmacionPage extends PageObjectBase {

    public OnboardingConfirmacionPage(WebDriver driver) {
        super(driver);
    }

    private final String PASO_BARRA_NAV_XPATH = "//div[contains(@class,'active')]//span[contains(text(),'CONFIRMACIÓN')]";
    private final String SOLICITAR_XPATH = "//button[contains(text(),'SOLICITAR')]";
    private final String ACEPTAR_TERMINOS_Y_CONDICIONES_CSS = ".checkbox-terminos";
    private final String TARJETA_TITULO_CSS = ".subtitle";
    private final String COSTO_MENSUAL_TARJETA_TEXT_CSS = "[ng-if*='CostoPromocinal']";
    private final String LINK_ACEPTO_TERMINOS_Y_CONDICIONES_CSS = ".aceptarTerminos";
    private final String POPUP_ACEPTO_TERMINOS_Y_CONDICIONES_CSS = ".modal-content";
    private final String POPUP_ACEPTAR_TERMINOS_BOTON_CSS = ".modal-footer .btn-principal";
    private final String POPUP_CANCELAR_TERMINOS_BOTON_CSS = ".modal-footer .btn-default";

    @FindBy(xpath = SOLICITAR_XPATH)
    protected WebElement requestProductButton;

    @FindBy(css = ACEPTAR_TERMINOS_Y_CONDICIONES_CSS)
    protected WebElement acceptTermsAndConditionsCheckBox;

    @FindBy(css = TARJETA_TITULO_CSS)
    protected WebElement creditCardSubtitle;

    @FindBy(css = COSTO_MENSUAL_TARJETA_TEXT_CSS)
    protected WebElement creditCardCost;

    @FindBy(css = LINK_ACEPTO_TERMINOS_Y_CONDICIONES_CSS)
    protected WebElement termsAndConditionsLink;

    @FindBy(css = POPUP_ACEPTAR_TERMINOS_BOTON_CSS)
    protected WebElement acceptTermsAndConditionsButton;

    @FindBy(css = POPUP_CANCELAR_TERMINOS_BOTON_CSS)
    protected WebElement cancelTermsAndConditionsButton;

    public boolean stepIsActiveInNavBar(){
        return getWebDriverUtils().waitForElementToBeVisible(By.xpath(PASO_BARRA_NAV_XPATH));
    }
    
    public OnboardingSolicitudAdicionalPage confirmAndContinueToProductSolicitude() {
        getWebDriverUtils().waitSeconds(TIME_AFTER_LOAD);
        clickOnApplyButton();
        getWebDriverUtils().waitSeconds(TIME_OUT_FOR_PAGE_LOAD);
        return new OnboardingSolicitudAdicionalPage(getDriver());
    }

    public void clickOnApplyButton() {
        click(requestProductButton);
    }

    public void clickOnAcceptTermsAndConditions() {
        click(acceptTermsAndConditionsCheckBox);
    }

    @Override
    protected By getPageLocator() {
        return By.xpath(SOLICITAR_XPATH);
    }

    public boolean validateCreditCardMensualCostTextsIsDisplayed() {
        return creditCardCost.isDisplayed();
    }

    public boolean validateCreditCardNameTextsIsDisplayed() {
        return creditCardSubtitle.isDisplayed();
    }

    public void clickOnAcceptTermsAndConditionsLink() {
        click(termsAndConditionsLink);
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
    }

    public boolean validateTermsAndConditionsPopUpIsDisplayed() {
        return getWebDriverUtils().isElementPresent(getDriver(), By.cssSelector(POPUP_ACEPTO_TERMINOS_Y_CONDICIONES_CSS));
    }

    public OnboardingSolicitudAdicionalPage clickOnPopUpAcceptTermsAndConditions() {
        click(acceptTermsAndConditionsButton);
        return new OnboardingSolicitudAdicionalPage(getDriver());
    }

    public void clickOnPopUpCancelTermsAndConditions() {
        click(cancelTermsAndConditionsButton);
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
    }

    public boolean validateTermsAndConditionsLinkIsDisplayed(){
        return termsAndConditionsLink.isDisplayed();
    }

}
