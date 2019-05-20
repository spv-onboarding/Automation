package com.supervielle.pageobjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.supervielle.backend.data.ClientDao;
import com.supervielle.backend.domain.EMPLOYMENT_RELATIONSHIP_TYPE;
import com.supervielle.backend.domain.sacnosis.SacnosisUtils;
import com.supervielle.framework.web.PageObjectBase;

public class OnboardingOfertaPage extends PageObjectBase {

    public OnboardingOfertaPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger LOG = Logger.getLogger(SacnosisUtils.class.getName());

    private final String PASO_BARRA_NAV_XPATH = "//div[contains(@class,'active')]//span[contains(text(),'OFERTA')]";
    private final String PRODUCT_BRAND_XPATH = "//p[contains(text(),'#BRAND')]";
    private final String PRODUCT_COST_TEXT_MANTENIMIENTO_XPATH = "(//div[contains(@class,'bloque-administracion')])[1]";
    private final String PRODUCT_COST_TEXT_RENOVACION_XPATH = "(//div[contains(@class,'bloque-administracion')])[2]";
    private final String PRODUCT_COSTS_BLOCK_MANTENIMIENTO_XPATH = "(//div[contains(@class,'bloque-costo') and not(contains(@class,'contenedor-bloque-costos'))])[1]";
    private final String PRODUCT_COSTS_BLOCK_RENOVACION_XPATH = "(//div[contains(@class,'bloque-costo') and not(contains(@class,'contenedor-bloque-costos'))])[2]";
    private final String SOLICITA_TU_TARJETA_XPATH = "//button[contains(text(),'Solicitar Tarjeta')]";
    private final String INFO_BASICA_CLIENTE_XPATH = "//div[contains(@class,'bloque-info-basica-cliente')]";
    private final String NOMBRE_CLIENTE_XPATH = INFO_BASICA_CLIENTE_XPATH + "//h3[contains(text(),'#VALUE')]";
    private final String PAIS_NACIMIENTO_NAME = "filiatoriaPaisNacimiento";
    private final String LUGAR_NACIMIENTO_NAME = "filiatoriaLugarNacimiento";
    private final String ESTADO_CIVIL_NAME = "filiatoriaEstadoCivil";
    private final String EMAIL_NAME = "filiatoriaEmail";
    private final String NIVEL_ESTUDIOS_NAME = "filiatoriaNivelEstudio";
    private final String CALLE_NAME = "domicilioCalle";
    private final String PROVINCIA_NAME = "domicilioProvincia";
    private final String CODIGO_POSTAL_NAME = "domicilioCP";
    private final String LOCALIDAD_NAME = "domicilioLocalidad";
    private final String RELACION_LABORAL_NAME = "relacionLaboralCliente";
    private String SELECTOR_OPTION = "//option[contains(text(),'#OPTION')]";
    private String PEP_CSS = "input[name = 'laboralSujetoObligado'][value='#OPTION'] ~ span.circle";
    private final String CONTINUAR_A_DATOS_DIRECCION_XPATH = "//*[@id='collapseOne']//button[contains(text(),'Continuar')]";
    private final String CONTINUAR_A_INFO_LABORAL_XPATH = "//div[@id='collapseAddress']//button[contains(text(),'Continuar')]";
    private final String ACEPTAR_XPATH = "//button[contains(text(),'Aceptar')]";
    private final String CODIGO_TELEFONO_NAME = "filiatoriaCodeTelefonoCelular";
    private final String TELEFONO_NAME = "filiatoriaTelefonoCelular";
    private final String TEXTO_OFERTA_CSS = "[ng-repeat*='paquete'] h1";
    private final String CONYUGE_NAME = "conyugeNombre";
    private final String CONYUGE_APELLIDO_NAME = "conyugeApellido";
    private final String CONYUGE_PAIS_NACIMIENTO_NAME = "conyugePaisNacimiento";
    private final String CONYUGE_TIPO_DOCUMENTO_NAME = "conyugeTipoDNI";
    private final String CONYUGE_DOCUMENTO_NAME = "conyugeDNI";
    private final String NUMERO_TELEFONO_INVALIDO_CSS = "#celPhoneNumber > span";
    private final String DIRECCION_INVALIDA_XPATH = "//span[text()='Ingrese una calle válida ']";
    private final String LISTA_PROVINCIAS_HABILITADAS = "select[name='domicilioProvincia'] > option[label]";
    private final String HEADER_FECHA_NACIMIENTO_CSS = ".col-md-5 span";
    private final String HEADER_CUIL_XPATH = "//label[contains(text(),'CUIL')]/parent::*//span";
    private final String HEADER_GENERO_XPATH = "//label[contains(text(),'Sexo')]/parent::*//span";
    private final String LISTA_RELACION_LABORAL_CSS = "select[name='relacionLaboralCliente'] > option[label]";
    private final String ALTURA_CALLE_NAME = "domicilioAltura";
    private final String PEP_ERROR_MESSAGE_XPATH = "//form[@name='laborDataForm']//p[contains(text(),'Se debe seleccionar una opción')]";
    private final String TIPO_TARJETA_TEXT_CSS = "div[data-imagen*='card']~p[ng-bind-html='producto.descripcion']";
    private final String LIMITE_TARJETA_TEXT_CSS = "div[data-imagen*='card']~p[ng-bind-html='producto.descripcion']>b";
    private final String TOOLTIP_COMISION_ADMINISTRACION_CSS = "[tooltip*='tooltip.administracion']";
    private final String TOOLTIP_COMISION_ANUAL_CSS = "[tooltip*='tooltip.renovacion']";
    private final String TOOLTIP_COMISION_ADMINISTRACION_TEXT_CSS = "[tooltip*='tooltip.administracion'] .tooltip-custom-inner";
    private final String TOOLTIP_COMISION_ANUAL_TEXT_CSS = "[tooltip*='tooltip.renovacion'] .tooltip-custom-inner";

    @FindBy(xpath = SOLICITA_TU_TARJETA_XPATH)
    protected WebElement requestYourCardButton;

    @FindBy(name = PAIS_NACIMIENTO_NAME)
    protected WebElement countryOfBirthSelector;

    @FindBy(name = LUGAR_NACIMIENTO_NAME)
    protected WebElement birthPlaceSelector;

    @FindBy(name = ESTADO_CIVIL_NAME)
    protected WebElement civilStatusSelector;

    @FindBy(name = EMAIL_NAME)
    protected WebElement emailInput;

    @FindBy(name = NIVEL_ESTUDIOS_NAME)
    protected WebElement educationalLevelSelector;

    @FindBy(xpath = CONTINUAR_A_DATOS_DIRECCION_XPATH)
    protected WebElement continueToAdressButton;

    @FindBy(name = CALLE_NAME)
    protected WebElement streetAddressInput;

    @FindBy(name = CODIGO_POSTAL_NAME)
    protected WebElement zipCodeInput;

    @FindBy(name = PROVINCIA_NAME)
    protected WebElement provinceSelector;

    @FindBy(xpath = CONTINUAR_A_INFO_LABORAL_XPATH)
    protected WebElement continueToWorkingInfoButton;

    @FindBy(name = RELACION_LABORAL_NAME)
    protected WebElement employmentRelationshipSelector;

    @FindBy(xpath = ACEPTAR_XPATH)
    protected WebElement acceptButton;

    @FindBy(name = CODIGO_TELEFONO_NAME)
    protected WebElement telephoneCodeInput;

    @FindBy(name = TELEFONO_NAME)
    protected WebElement telephoneInput;

    @FindBy(name = TEXTO_OFERTA_CSS)
    protected WebElement offerText;

    @FindBy(name = CONYUGE_NAME)
    protected WebElement spouseNameInput;

    @FindBy(name = CONYUGE_APELLIDO_NAME)
    protected WebElement spouseSurnameInput;

    @FindBy(name = CONYUGE_PAIS_NACIMIENTO_NAME)
    protected WebElement countryOfForSpouseBirthDropdown;

    @FindBy(name = CONYUGE_TIPO_DOCUMENTO_NAME)
    protected WebElement spouseDocumentTypeDropdown;

    @FindBy(name = CONYUGE_DOCUMENTO_NAME)
    protected WebElement spouseDocumentInput;

    @FindBy(css = HEADER_FECHA_NACIMIENTO_CSS)
    protected WebElement birthDateLabel;

    @FindBy(xpath = HEADER_CUIL_XPATH)
    protected WebElement CUILLabel;

    @FindBy(xpath = HEADER_GENERO_XPATH)
    protected WebElement genderLabel;

    @FindBy(name = ALTURA_CALLE_NAME)
    protected WebElement streetLevelInput;

    @FindBy(name = LOCALIDAD_NAME)
    protected WebElement locationInput;

    @FindBy(css = TIPO_TARJETA_TEXT_CSS)
    protected WebElement creditcardTypeText;

    @FindBy(css = LIMITE_TARJETA_TEXT_CSS)
    protected WebElement creditcardLimitText;

    public boolean stepIsActiveInNavBar() {
        return getWebDriverUtils().waitForElementToBeVisible(By.xpath(PASO_BARRA_NAV_XPATH));
    }

    @FindBy(css = TOOLTIP_COMISION_ADMINISTRACION_CSS)
    protected WebElement tooltipAdministrationCommission;

    @FindBy(css = TOOLTIP_COMISION_ANUAL_CSS)
    protected WebElement tooltipAnualCommission;

    public void clickOnRequestYourCardButton() {
        getWebDriverUtils().waitSeconds(TIME_AFTER_LOAD);
        waitForLoadingElementToDissapear();
        click(requestYourCardButton);
    }

    public boolean clientNameIsDisplayedOnUI(String clientName) {
        return getWebDriverUtils()
                .waitForElementToBeVisible(By.xpath(NOMBRE_CLIENTE_XPATH.replace("#VALUE", clientName)));
    }

    public void selectCountryOfBirth(String countryOfBirth) {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        waitForLoadingElementToDissapear();
        click(countryOfBirthSelector);
        selectOptionFromDropdown(countryOfBirthSelector, countryOfBirth);
    }

    public void selectBirthPlace(String birthPlace) {
        click(birthPlaceSelector);
        selectOptionFromDropdown(birthPlaceSelector, birthPlace);
    }

    public void writeOnEmailInput(String email) {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        if (getWebDriverUtils().waitForElementToBePresent(emailInput, TIME_AFTER_CLICK)) {
            typeOnField(emailInput, email);
        } else {
            System.out.println("no entro");
        }
    }

    public void selectEducationalLevel(String educationalLevel) {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        click(educationalLevelSelector);
        WebElement nivelEstudiosOption = educationalLevelSelector
                .findElement(By.xpath(SELECTOR_OPTION.replace("#OPTION", educationalLevel)));
        click(nivelEstudiosOption);
    }

    public void clickOnContinueToAdressButton() {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        click(continueToAdressButton);
    }

    public void clickOnContinueToWorkingInfoButton() {
        getWebDriverUtils().waitSeconds(TIME_AFTER_LOAD);
        click(continueToWorkingInfoButton);
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
    }

    public void writeOnStreetAddressInput(String street) {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        typeOnField(streetAddressInput, street);
    }

    public void writeOnStreetLevel(String street) {
        typeOnField(streetLevelInput, street);
    }

    public void selectProvince(String province) {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        click(provinceSelector);
        selectOptionFromDropdown(provinceSelector, province);
    }

    public void writeOnZipCodeInput(String zipCode) {
        typeOnField(zipCodeInput, zipCode);
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        typeOnField(zipCodeInput, zipCode);
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
    }

    public void selectEmploymentRelationship(String employmentRel) {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        click(employmentRelationshipSelector);
        selectOptionFromDropdown(employmentRelationshipSelector, employmentRel);
    }

    public void selectPEP(String option) {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        String PEPOption;
        if ("false".equalsIgnoreCase(option)) {
            PEPOption = PEP_CSS.replace("#OPTION", "false");
        } else {
            PEPOption = PEP_CSS.replace("#OPTION", "true");
        }
        click(getDriver().findElement(By.cssSelector(PEPOption)));
    }

    public OnboardingSeleccionDeSucursalPage clickOnAccept() {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        click(acceptButton);
        return new OnboardingSeleccionDeSucursalPage(getDriver());
    }

    public void selectCivilStatus(String civilStatus) {
        selectOptionFromDropdown(civilStatusSelector, civilStatus);
    }

    public void selectDocumentTypeForSpouse(String documentType) {
        click(spouseDocumentTypeDropdown);
        selectOptionFromDropdown(spouseDocumentTypeDropdown, documentType);
    }

    public void selectCountryOfBirthForSpouse(String country) {
        click(countryOfForSpouseBirthDropdown);
        selectOptionFromDropdown(countryOfForSpouseBirthDropdown, country);
    }

    public void writeOnSpouseName(String name, String surname) {
        typeOnField(spouseNameInput, name);
        typeOnField(spouseSurnameInput, surname);
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
    }

    public void writeOnSpouseDNI(String dni) {
        typeOnField(spouseDocumentInput, dni);
    }

    @Override
    protected By getPageLocator() {
        return By.xpath(SOLICITA_TU_TARJETA_XPATH);
    }

    public String getTextFromCountryOfBirth() {
        Select dropdown = new Select(countryOfBirthSelector);
        return dropdown.getFirstSelectedOption().getAttribute("label");
    }

    public String getTextFromPlaceOfBirth() {
        Select dropdown = new Select(birthPlaceSelector);
        return dropdown.getFirstSelectedOption().getAttribute("label");
    }

    public String getTextFromCivilStatusSelector() {
        Select dropdown = new Select(civilStatusSelector);
        return dropdown.getFirstSelectedOption().getAttribute("label");
    }

    public String getTextOfTelephoneCode() {
        return telephoneCodeInput.getAttribute("value").replaceAll(" ", "");
    }

    public String getTextOfTelephone() {
        return telephoneInput.getAttribute("value").replaceAll(" ", "");
    }

    public String getTextOfStreetAddressInput() {
        return streetAddressInput.getAttribute("value").replaceAll(" ", "");
    }

    public boolean addressInfoHasLoaded() {
        return getWebDriverUtils().waitForElementToBePresent(streetAddressInput, TIME_AFTER_CLICK);
    }

    public boolean employmentRelationshipInfoHasLoaded() {
        return getWebDriverUtils().waitForElementToBeVisible(By.xpath(ACEPTAR_XPATH));
    }

    public boolean errorMessageForInvalidTelephoneNumberIsDisplayed() {
        return getWebDriverUtils().waitForElementToBeVisible(By.cssSelector(NUMERO_TELEFONO_INVALIDO_CSS));
    }

    public boolean errorMessageForInvalidDirectionIsDisplayed() {
        return getWebDriverUtils().waitForElementToBeVisible(By.xpath(DIRECCION_INVALIDA_XPATH));
    }

    public boolean errorMessageForNoPEPOptionSelectedIsDisplayed() {
        return getWebDriverUtils().waitForElementToBeVisible(By.xpath(PEP_ERROR_MESSAGE_XPATH));
    }

    public boolean validateProvinceElementsFromDropdown(String provinceName) {
        List<String> listFromProvinciasOperativas = getUIListFromOperativeProvinces();
        return listFromProvinciasOperativas.stream().noneMatch(x -> x.equalsIgnoreCase(provinceName));
    }

    public List<String> getUIListFromOperativeProvinces() {
        List<WebElement> elements = getDriver().findElements(By.cssSelector(LISTA_PROVINCIAS_HABILITADAS));
        List<String> listaProvincias = elements.stream().map(x -> x.getText()).collect(Collectors.toList());
        return listaProvincias;
    }

    public boolean validateProvinceListFromDBAndUI() {
        List<String> provinciasOperativas = ClientDao.getOperativeProvinces();
        List<String> listFromProvinciasOperativas = getUIListFromOperativeProvinces();
        return CollectionUtils.isEqualCollection(provinciasOperativas, listFromProvinciasOperativas);
    }

    public String getBirthDate() {
        return birthDateLabel.getText();
    }

    public boolean convertSacnosisBirthDateToUIFormatAndValidate(Date NosisDate) {

        String birthDateUI = getBirthDate();
        SimpleDateFormat dateWithFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateUI = null;
        try {
            dateUI = dateWithFormat.parse(birthDateUI);
        } catch (ParseException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return dateUI.equals(NosisDate);
    }

    public String getGender() {
        return genderLabel.getText();
    }

    public String getCUIL() {
        String date = CUILLabel.getText();
        return date.replaceAll("-", "");
    }

    public boolean validateIfProvinceIsOperative(String province) {
        List<String> operativeProvinces = ClientDao.getOperativeProvinces();
        return operativeProvinces.stream().anyMatch(x -> x.equalsIgnoreCase(province));
    }

    public String getAddress() {
        return streetAddressInput.getAttribute("value");
    }

    public boolean validateIfEmploymentRelationshipIsSelected(EMPLOYMENT_RELATIONSHIP_TYPE employmentRelationship) {
        List<WebElement> employmentRelationshipList = getDriver()
                .findElements(By.cssSelector(LISTA_RELACION_LABORAL_CSS));
        Optional<WebElement> employmentRelationshipElement = employmentRelationshipList.stream()
                .filter(x -> x.getText().equalsIgnoreCase(employmentRelationship.getName())).findFirst();

        if (employmentRelationshipElement.isPresent()) {
            return employmentRelationshipElement.get().getAttribute("selected").contains("true");
        }
        return false;
    }

    public String getZipCode() {
        return zipCodeInput.getAttribute("value");
    }

    public void formatLocalityInput() {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        locationInput.click();
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        locationInput.sendKeys(Keys.ARROW_DOWN);
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        locationInput.sendKeys(Keys.ENTER);
    }

    public String getSelectedProvinceTextFromDropDown() {
        Select comboBox = new Select(provinceSelector);
        return comboBox.getFirstSelectedOption().getText();
    }

    public void formatAddress() {
        String address = getAddress().replace(".", "");
        streetAddressInput.clear();
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        writeOnStreetAddressInput(address);
    }

    public void clearDataFromTelephoneNumberInput() {
        telephoneInput.clear();
    }

    public String getCreditCardTypeText() {
        return creditcardTypeText.getText();
    }

    public String getCreditCardLimitText() {
        return creditcardLimitText.getText();
    }

    public boolean productBrandDisplayedInUIIsCorrect(String brand) {
        return getWebDriverUtils().waitForElementToBeVisible(By.xpath(PRODUCT_BRAND_XPATH.replace("#BRAND", brand)));
    }

    public boolean blocksOfTextsForCostsAreDisplayedInUI() {
        return getWebDriverUtils().waitForElementLocatedToBePresent(By.xpath(PRODUCT_COST_TEXT_MANTENIMIENTO_XPATH),
                TIME_AFTER_CLICK)
                && getWebDriverUtils().waitForElementLocatedToBePresent(By.xpath(PRODUCT_COST_TEXT_RENOVACION_XPATH),
                        TIME_AFTER_CLICK);
    }

    public boolean blocksOfCostsAreDisplayedInUI() {
        return getWebDriverUtils().waitForElementLocatedToBePresent(By.xpath(PRODUCT_COSTS_BLOCK_MANTENIMIENTO_XPATH),
                TIME_AFTER_CLICK)
                && getWebDriverUtils().waitForElementLocatedToBePresent(By.xpath(PRODUCT_COSTS_BLOCK_RENOVACION_XPATH),
                        TIME_AFTER_CLICK);
    }

    public void mouseOverTooltipAnualRenovation() {
        scrollIntoView(creditcardLimitText);
        getWebDriverUtils().mouseOver(getDriver(), tooltipAnualCommission);
    }

    public boolean validateTooltipAnualRenovationIsDisplayed() {
        return getWebDriverUtils().isElementPresent(getDriver(), By.cssSelector(TOOLTIP_COMISION_ANUAL_TEXT_CSS));
    }

    public void mouseOverTooltipAdministrationRenovation() {
        getWebDriverUtils().mouseOver(getDriver(), tooltipAdministrationCommission);
    }

    public boolean validateTooltipAdministrationRenovationIsDisplayed() {
        return getWebDriverUtils().isElementPresent(getDriver(),
                By.cssSelector(TOOLTIP_COMISION_ADMINISTRACION_TEXT_CSS));
    }

}
