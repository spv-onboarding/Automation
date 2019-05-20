package com.supervielle.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.supervielle.backend.data.ClientDao;
import com.supervielle.backend.data.EmailDao;
import com.supervielle.framework.tdm.UserProvider;
import com.supervielle.framework.web.PageObjectBase;

public class AgentesHomePage extends PageObjectBase {

    public AgentesHomePage(WebDriver driver) {
        super(driver);
    }

    private final String AGENTES_PAGE_LOCATOR = ".agente-main-container";
    private final String AGENTE_EXTERNO_XPATH = "//button[contains(text(),'AGENTE EXTERNO')]";
    private final String NOMBRE_USUARIO_NAME = "usuario";
    private final String PASSWORD_NAME = "password";
    private final String BOTON_INGRESAR_CSS = "button.btn-principal";
    private final String DROPDOWN_DOCUMENTOS_NAME = "select[name='documentos']";
    private final String NRO_DOCUMENTO_NAME = "numero";
    private final String GENERO_CHECKBOX_CSS = "input[value='#TEXT'] ~ span.circle";
    private final String NACIONALIDAD_NAME = "nacionalidad";
    private final String DESCUBRIR_BOTTON = "//button[contains(text(),'DESCUBRIR OFERTAS')]";

    @FindBy(xpath = AGENTE_EXTERNO_XPATH)
    protected WebElement externalAgentButton;

    @FindBy(name = NOMBRE_USUARIO_NAME)
    protected WebElement userNameInput;

    @FindBy(name = PASSWORD_NAME)
    protected WebElement passwordInput;

    @FindBy(css = BOTON_INGRESAR_CSS)
    protected WebElement enterButton;

    @FindBy(css = DROPDOWN_DOCUMENTOS_NAME)
    protected WebElement documentsDropdown;

    @FindBy(name = NRO_DOCUMENTO_NAME)
    protected WebElement documentInput;

    @FindBy(name = NACIONALIDAD_NAME)
    protected WebElement nationalityDropdown;

    @FindBy(xpath = DESCUBRIR_BOTTON)
    protected WebElement discoverButton;


    @Override
    protected By getPageLocator() {return By.cssSelector(AGENTES_PAGE_LOCATOR);}

    public void clickOnExternalAgent() {
        waitForLoadingElementToDissapear();
        click(externalAgentButton);
    }

    public void loginToAgentPage(String credentials){
        typeOnField(userNameInput, UserProvider.getUser(credentials));
        typeOnField(passwordInput, UserProvider.getPassword(credentials));
        click(enterButton);
        waitForLoadingElementToDissapear();
    }

    public void fillProspectData(String prospect){
        EmailDao.setEmailToNull(UserProvider.getCuil(prospect),UserProvider.getEmail(prospect));
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        ClientDao.cleanClientRegistrationForGivenCuil(Long.parseLong(UserProvider.getCuil(prospect)));
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        getWebDriverUtils().waitForElementToBeVisible(By.xpath(DESCUBRIR_BOTTON));
        selectCuilDocumentType();
        typeOnField(documentInput, UserProvider.getCuil(prospect));
        selectGender(UserProvider.getGenero(prospect));
    }
    
    public AgentesOfertasPage clickOnOptionToDiscoverOfferingContent(){
        click(discoverButton);
        waitForLoadingElementToDissapear();
        getWebDriverUtils().waitSeconds(TIME_AFTER_LOAD);
        return new AgentesOfertasPage(getDriver());
    }
    

    public void selectCuilDocumentType(){
        waitForLoadingElementToDissapear();
        click(documentsDropdown);
//      selectOptionFromDropdown(documentsDropdown, "C.U.I.L.");	11-03-2019. El Servicio ConsultaCodificadora había cambiado. 
        selectOptionFromDropdown(documentsDropdown, "C.U.I.T.");
    }

    public void selectGender(String provider) {
        String genero = GENERO_CHECKBOX_CSS.replace("#TEXT", provider);
        click(getDriver().findElement(By.cssSelector(genero)));
    }


}
