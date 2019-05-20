package com.supervielle.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.supervielle.framework.web.PageObjectBase;

public class OnboardingSeleccionDeSucursalPage extends PageObjectBase {

    public OnboardingSeleccionDeSucursalPage(WebDriver driver) {
        super(driver);
    }

    private final String PASO_BARRA_NAV_XPATH = "//div[contains(@class,'active')]//span[contains(text(),'SUCURSAL')]";
    private final String BUSCADOR_SUCURSALES_ID = "mapSearchInput";
    private final String MAPA_SUCURSALES_ID = "map";
    private final String ICONOS_SUCURSALES_SUPERVIELLE_XPATH = "//div/img[@src='dist/app/assets/img/iconos/icono_banco_map.png']";
    private final String SELECCIONAR_SUCURSAL_XPATH = "//button[contains(text(),'Seleccionar Sucursal')]";
    private final String OPCIONES_DIRECCION_DOCUMENTACION_CSS = "label > span.circle";
    private final String CONTINUAR_XPATH = "//button[contains(text(),'Continuar')]";
    private final int PRIMER_SUCURSAL = 0;
    private final int OTRA_DIRECCION_INDEX = 1;
    private final String PROVINCIA_NAME = "provincias";
    private final String CALLE_XPATH = "//div[contains(@class,'receptionDoc-calle')]/input";
    private final String NUMERO_CALLE_XPATH = "//div[contains(@class,'receptionDoc-num')]/input";
    private final String CODIGO_POSTAL_XPATH = "//div[contains(@class,'receptionDoc-cp')]/input";
    private final String DIRECCION_INVALIDA_XPATH = "//span[text()='Ingrese una calle válida']";

    @FindBy(id = BUSCADOR_SUCURSALES_ID)
    protected WebElement searchInputForBankBranches;

    @FindBy(id = MAPA_SUCURSALES_ID)
    protected WebElement mapOfBranches;

    @FindBy(xpath = SELECCIONAR_SUCURSAL_XPATH)
    protected WebElement selectBankBranchButton;

    @FindBy(xpath = CONTINUAR_XPATH)
    protected WebElement continueButton;

    @FindBy(name = PROVINCIA_NAME)
    protected WebElement provinceSelector;

    @FindBy(xpath = CALLE_XPATH)
    protected WebElement streetInput;

    @FindBy(xpath = CODIGO_POSTAL_XPATH)
    protected WebElement zipCodeInput;

    @FindBy(xpath = NUMERO_CALLE_XPATH)
    protected WebElement streetNumberInput;
    
    public boolean stepIsActiveInNavBar(){
        return getWebDriverUtils().waitForElementToBeVisible(By.xpath(PASO_BARRA_NAV_XPATH));
    }

    public void clickOnSupervielleBranch() throws InterruptedException {
        getWebDriverUtils().waitSeconds(TIME_AFTER_LOAD);
        List<WebElement> supervielleBranch = getDriver().findElements(By.xpath(ICONOS_SUCURSALES_SUPERVIELLE_XPATH));
        for (int i = 0; i < supervielleBranch.size(); i++) {
            if (supervielleBranch.get(i).isDisplayed()) {
                int tries = 0;
                while (tries < 3 && !getWebDriverUtils().waitForElementToBePresent(selectBankBranchButton,
                        TIME_OUT_MINIMUN)) {
                    getWebDriverUtils().clickWithActionsBuilder(getDriver(), supervielleBranch.get(i));
                    tries++;
                }
                if (getWebDriverUtils().waitForElementToBePresent(selectBankBranchButton, TIME_OUT_MINIMUN)) {
                    break;
                }
            }
        }
    }

    public void clickOnSelectBranchButton() {
        getWebDriverUtils().waitSeconds(TIME_OUT_FOR_PAGE_LOAD);
        click(selectBankBranchButton);
    }

    public void selectDocumentationAddressByDefault() {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        WebElement documentationAddressOption = getDriver()
                .findElements(By.cssSelector(OPCIONES_DIRECCION_DOCUMENTACION_CSS)).get(PRIMER_SUCURSAL);
        click(documentationAddressOption);
    }

    public void clickOnSetAlternativeDocumentationAddress() {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        WebElement documentationAddressOption = getDriver()
                .findElements(By.cssSelector(OPCIONES_DIRECCION_DOCUMENTACION_CSS)).get(OTRA_DIRECCION_INDEX);
        click(documentationAddressOption);
    }

    public OnboardingConfirmacionPage clickOnContinueButton() {
        getWebDriverUtils().clickWithActionsBuilder(getDriver(), (continueButton));
        getWebDriverUtils().waitSeconds(TIME_AFTER_LOAD);
        return new OnboardingConfirmacionPage(getDriver());
    }

    public OnboardingValidacionDeSeguridad clickOnContinueButtonOpeningTheIdentityValidationPage() {
        getWebDriverUtils().clickWithActionsBuilder(getDriver(), (continueButton));
        getWebDriverUtils().waitSeconds(TIME_AFTER_LOAD);
        return new OnboardingValidacionDeSeguridad(getDriver());
    }
    
    @Override
    protected By getPageLocator() {
        return By.id(BUSCADOR_SUCURSALES_ID);
    }

    public void selectProvince(String province) {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        click(provinceSelector);
        selectOptionFromDropdown(provinceSelector, province);
    }

    public void writeOnStreetInput(String street) {
        typeOnField(streetInput, street);
    }

    public void writeOnStreetNumberInput(String num) {
        typeOnField(streetNumberInput, num);
    }

    public void writeOnZipCode(String zipCode) {
        typeOnField(zipCodeInput, zipCode);
        typeOnField(zipCodeInput, zipCode);
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
    }

    public boolean errorMessageForInvalidDirectionIsPresent() {
        return getWebDriverUtils().waitForElementToBeVisible(By.xpath(DIRECCION_INVALIDA_XPATH));
    }
}
