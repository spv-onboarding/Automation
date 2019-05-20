package com.supervielle.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.supervielle.framework.tdm.UserProvider;
import com.supervielle.framework.utils.DateTimeUtils;
import com.supervielle.framework.web.PageObjectBase;

public class OnboardingSolicitudAdicionalPage extends PageObjectBase {

    public OnboardingSolicitudAdicionalPage(WebDriver driver) {
        super(driver);
    }

    private final String NEGOCIO_ADHERIDO = "//li[contains(text(),'#VALUE')]";
    private final String NEGOCIO_ADHERIDO_TIENDA_SUPERVIELLE_VALUE = "Tienda Supervielle";
    private final String ENTENDIDO_XPATH = "//button[contains(text(),'Entendido')]";
    private final String IMPRIMIR_RESUMEN_XPATH = "//button[contains(text(),'IMPRIMIR RESUMEN')] | //a[contains(text(), 'O imprímelo desde aquí.')]";
    private String ADICIONAL_CSS = ".adicional-item-#OPTION";
    private final String CONFIRMAR_XPATH = "(//button[contains(text(),'Confirmar')][1])";
    private final int MAXIMO_ADICIONALES = 2;
    private final String MENSAJE_EXITO_XPATH = "//p[contains(text(),'Completaste exitosamente el pedido de tu Tarjeta de Crédito.')]";
    private final String CONFIRMAR_ADICIONALES_XPATH = "(//button[contains(text(),'Confirmar')])[2]";
    private final String MENSAJE_EXITO_ADICIONALES_XPATH = "//h3[contains(text(),'¡Listo!')]";

    // Datos del adicional
    private final String NOMBRE_ADICIONAL_NOMBRE = "nombre";
    private final String APELLIDO_ADICIONAL_NOMBRE = "apellido";
    private final String VINCULO_CON_TITULAR_NOMBRE = "vinculo";
    private String GENERO_CSS = "input[value='#OPTION'] ~ span.circle";
    private final String PAIS_NACIMIENTO_NAME = "paisNacimiento";
    private final String DOCUMENTO_NAME = "documento";
    private final String CUIL_NAME = "cuil";
    private final String FECHA_NACIMIENTO_ID = "input_1";
    private final String DEFAULT_FECHA_NAC = "03/10/1993";
    private final String EMAIL_NAME = "email";
    private final String CODIGO_TELEFONO_NAME = "codeTelefono";
    private final String TELEFONO_NAME = "telefono";
    private final String VOLVER_XPATH = "(//button[contains(text(),'Volver')])[1]";
    private final String CONTINUAR_XPATH = "//button[contains(text(),'Continuar')]";
    private String SELECTOR_OPTION = "//option[contains(text(),'#OPTION')]";
    private final String FEMENINO_CHECK_OPTION_DESCRIPTION = "FEMENINO";
    private final String FEMENINO_CHECK_OPTION_VALUE = "0";
    private final String MASCULINO_CHECK_OPTION_VALUE = "1";

    @FindBy(xpath = ENTENDIDO_XPATH)
    WebElement confirmProductOperationResult;

    @FindBy(xpath = IMPRIMIR_RESUMEN_XPATH)
    WebElement printSummaryButton;

    @FindBy(xpath = CONFIRMAR_XPATH)
    WebElement confirmButton;

    // Datos del adicional
    @FindBy(name = NOMBRE_ADICIONAL_NOMBRE)
    WebElement nameInput;

    @FindBy(name = APELLIDO_ADICIONAL_NOMBRE)
    WebElement surnameInput;

    @FindBy(name = VINCULO_CON_TITULAR_NOMBRE)
    WebElement linkWithHolderSelector;

    @FindBy(name = PAIS_NACIMIENTO_NAME)
    WebElement countryOfBirthSelector;

    @FindBy(name = DOCUMENTO_NAME)
    WebElement documentInput;

    @FindBy(name = CUIL_NAME)
    WebElement cuilInput;

    @FindBy(name = EMAIL_NAME)
    WebElement emailInput;

    @FindBy(name = CODIGO_TELEFONO_NAME)
    WebElement telephoneCodeInput;

    @FindBy(name = TELEFONO_NAME)
    WebElement telephoneInput;

    @FindBy(xpath = VOLVER_XPATH)
    WebElement goBackButton;

    @FindBy(xpath = CONTINUAR_XPATH)
    WebElement continueButton;

    @FindBy(id = FECHA_NACIMIENTO_ID)
    WebElement birthDateInput;

    public boolean listOfShopsAdheredToBankIsPresent() {
        getWebDriverUtils().waitSeconds(TIME_OUT_FOR_PAGE_LOAD);
        return getWebDriverUtils().waitForElementLocatedToBePresent(
                By.xpath(NEGOCIO_ADHERIDO.replace("#VALUE", NEGOCIO_ADHERIDO_TIENDA_SUPERVIELLE_VALUE)),
                TIME_AFTER_CLICK);

    }

    public void clickOnConfirmProductOperationResult() {
        getWebDriverUtils().waitSeconds(TIME_AFTER_LOAD);
        if (getWebDriverUtils().waitForElementToBePresent(confirmProductOperationResult, TIME_AFTER_CLICK)) {
            click(confirmProductOperationResult);
        }
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
    }

    public void selectAditional(int option) {
        WebElement selectOneAditional;
        if (MAXIMO_ADICIONALES > option) {
            selectOneAditional = getDriver().findElement(By.cssSelector(ADICIONAL_CSS.replace("#OPTION", "1")));
        } else {
            selectOneAditional = getDriver().findElement(By.cssSelector(ADICIONAL_CSS.replace("#OPTION", "2")));
        }
        click(selectOneAditional);
    }

    public void clickOnConfirmButton() {
        click(confirmButton);
    }

    // Datos del adicional
    public void setNameOfAnAditional(String name) {
        typeOnField(nameInput, name);
    }

    public void setSurnameOfAnAditional(String surname) {
        typeOnField(surnameInput, surname);
    }

    public void setLinkWithHolder(String option) {
        getWebDriverUtils().waitSeconds(TIME_AFTER_LOAD);
        click(linkWithHolderSelector);
        WebElement linkOption = linkWithHolderSelector
                .findElement(By.xpath(SELECTOR_OPTION.replace("#OPTION", option)));
        click(linkOption);
    }

    public void setCountryOfBirth(String country) {
        click(countryOfBirthSelector);
        WebElement countryOfBirthOption = countryOfBirthSelector
                .findElement(By.xpath(SELECTOR_OPTION.replace("#OPTION", country)));
        click(countryOfBirthOption);
    }

    public void setDNI(String dni) {
        typeOnField(documentInput, dni);
    }

    public void setCUIL(String cuil) {
        typeOnField(cuilInput, cuil);
    }

    public void setEmail(String email) {
        typeOnField(emailInput, email);
    }

    public void setTelephoneCode(String codigo) {
        typeOnField(telephoneCodeInput, codigo);
    }

    public void setTelephone(String telefono) {
        typeOnField(telephoneInput, telefono);
    }

    public void clickOnContinueButton() {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        click(continueButton);
    }

    public void selectGender(String gender) {
        WebElement genderOption;
        if (FEMENINO_CHECK_OPTION_DESCRIPTION.contains(gender.toUpperCase())) {
            genderOption = getDriver()
                    .findElement(By.cssSelector(GENERO_CSS.replace("#OPTION", FEMENINO_CHECK_OPTION_VALUE)));
        } else {
            genderOption = getDriver()
                    .findElement(By.cssSelector(GENERO_CSS.replace("#OPTION", MASCULINO_CHECK_OPTION_VALUE)));
        }
        getWebDriverUtils().waitSeconds(TIME_AFTER_LOAD);
        click(genderOption);
    }

    public void selectBirthDate(String fecha) {
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        for (int i = 0; i < 2; i++) {
            getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
            birthDateInput.clear();
            if (DateTimeUtils.dateHasValidFormat(fecha)) {
                birthDateInput.sendKeys(fecha);
            } else {
                birthDateInput.sendKeys(DEFAULT_FECHA_NAC);
            }
            cuilInput.click();
        }
    }

    public void fillAditionalProspectData(String prospect) {
        System.out.println("Cuil: " + UserProvider.getCuil(prospect));
        System.out.println("Nombre: " + UserProvider.getNombre(prospect));
        System.out.println("Apellido: " + UserProvider.getApellido(prospect));
        System.out.println("Sexo: " + UserProvider.getGenero(prospect));
        System.out.println("Codigo de Area: " + UserProvider.getCodArea(prospect));
        System.out.println("Telefono: " + UserProvider.getTelefono(prospect));

        setLinkWithHolder(UserProvider.getVinculo(prospect));
        selectGender(UserProvider.getGenero(prospect));
        setCountryOfBirth(UserProvider.getPaisNacimiento(prospect));
        setDNI(UserProvider.getDNIFromCuil(prospect));
        setCUIL(UserProvider.getCuil(prospect));
        selectBirthDate(UserProvider.getFechaNac(prospect));
        setTelephoneCode(UserProvider.getCodArea(prospect));
        setTelephone(UserProvider.getTelefono(prospect));
        setNameOfAnAditional(UserProvider.getNombre(prospect));
        setSurnameOfAnAditional(UserProvider.getApellido(prospect));
    }

    public void finalizeSolicitudeOfAditional() {
        getWebDriverUtils().waitSeconds(TIME_OUT_FOR_PAGE_LOAD);
        click(getDriver().findElement(By.xpath(CONFIRMAR_ADICIONALES_XPATH)));
    }

    public boolean successMessageIsPresent() {
        return getWebDriverUtils().waitForElementLocatedToBePresent(By.xpath(MENSAJE_EXITO_XPATH), TIME_AFTER_CLICK);
    }

    public boolean successMessageIsPresentForAditionals() {
        return getWebDriverUtils().waitForElementLocatedToBePresent(By.xpath(MENSAJE_EXITO_ADICIONALES_XPATH),
                TIME_AFTER_LOAD);
    }

    @Override
    protected By getPageLocator() {
        return By.xpath(IMPRIMIR_RESUMEN_XPATH);
    }

}
