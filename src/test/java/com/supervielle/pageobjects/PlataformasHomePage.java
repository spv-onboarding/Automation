package com.supervielle.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.supervielle.framework.web.PageObjectBase;

public class PlataformasHomePage extends PageObjectBase {

    private final String DESCUBRIR_XPATH = "//button[contains(text(),'DESCUBRIR')]";
    private final String TIPO_DOCUMENTO_NAME = "documentos";
    private final String NUMERO_DOCUMENTO_NAME = "numero";
    private final String NACIONALIDAD_NAME = "nacionalidad";
    private final String GENERO_XPATH = "//div[contains(text(),'#OPTION')]";

    @FindBy(name = TIPO_DOCUMENTO_NAME)
    protected WebElement documentTypeSelect;

    @FindBy(name = NUMERO_DOCUMENTO_NAME)
    protected WebElement documentNumberInput;

    @FindBy(name = NACIONALIDAD_NAME)
    protected WebElement nationalitySelect;

    @FindBy(xpath = DESCUBRIR_XPATH)
    protected WebElement discoverButton;

    public PlataformasHomePage(WebDriver driver) {
        super(driver);
        isLoaded();
    }

    @Override
    protected By getPageLocator() {
        return By.xpath(DESCUBRIR_XPATH);
    }

    public OnboardingPage clickOnDiscoverButton() {
        click(discoverButton);
        getWebDriverUtils().waitSeconds(TIME_AFTER_LOAD);
        return new OnboardingPage(getDriver());
    }

    public void selectDocumentType(String documentType) {
        getWebDriverUtils().waitSeconds(TIME_AFTER_LOAD);
        click(documentTypeSelect);
        selectOptionFromDropdown(documentTypeSelect, documentType);
    }

    public void writeOnDocumentInput(String documentNumber) {
        typeOnField(documentNumberInput, documentNumber);
    }

    public void selectNationality(String nationality) {
        click(nationalitySelect);
        selectOptionFromDropdown(nationalitySelect, nationality);
    }

    public void selectGender(String gender) {
        WebElement genderRadioButton = getDriver().findElement(By.xpath(GENERO_XPATH.replace("#OPTION", gender)));
        getWebDriverUtils().clickWithActionsBuilder(getDriver(), genderRadioButton);
    }
}
