package com.supervielle.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.supervielle.framework.web.PageObjectBase;
import org.openqa.selenium.WebElement;

public class AgentesOfertasPage extends PageObjectBase {

    private final String AGENTES_OFERTAS_LOCATOR_XPATH = "//button[contains(text(),'INGRESAR NUEVO PROSPECTO')]";
    private final String DESCRIPTION_ARROW_XPATH = "//div[@class='bloque-descripcion-arrow']";
    private final String AGENTES_OFERTA_OPTION_XPATH = "//h4[contains(@class,'bloque-descripcion-title') and contains(text(),'#OFFER')]"
            + "#EXTRAOFFER" + "/../.." + DESCRIPTION_ARROW_XPATH;
    private final String EXTRA_OFFER = "/span[contains(text(),'#VALUE')]/..";
    private final String CREDIT_CARD_LIMIT_TEXT_CSS = "[ng-repeat*='oferta'] .bloque-descripcion-text > b";

    public AgentesOfertasPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageLocator() {
        return By.xpath(AGENTES_OFERTAS_LOCATOR_XPATH);
    }

    public OnboardingOfertaPage selectOfferingFromList(String text) {
        clickOnOfferOptionByText(text);
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        closeFirstTabInBrowser(tabs);
        return new OnboardingOfertaPage(getDriver());
    }
    
    public void closeFirstTabInBrowser(ArrayList<String> tabs){
        getDriver().switchTo().window(tabs.get(0));
        getDriver().close();
        getDriver().switchTo().window(tabs.get(1));
    }

    public int getSizeOfOfferOptionsDisplayed() {
        return getDriver().findElements(By.xpath(DESCRIPTION_ARROW_XPATH)).size();
    }

    public void clickOnOfferOptionByText(String text){
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        String[] offering = text.split("\\+");
        String offeringOption;
        if (offering.length > 1) {
            offeringOption = AGENTES_OFERTA_OPTION_XPATH.replace("#EXTRAOFFER", EXTRA_OFFER);
            offeringOption = offeringOption.replace("#VALUE", offering[1]);
        } else {
            offeringOption = AGENTES_OFERTA_OPTION_XPATH.replace("#EXTRAOFFER", "");
                                                                 //*[contains(text(), 'Identité Platinum')]
        }
        offeringOption = offeringOption.replace("#OFFER", offering[0]);
        System.out.println("MENSAJE offeringOption");
        System.out.println(offeringOption);
        System.out.println("MENSAJE offeringOption");
        click(getDriver().findElement(By.xpath(offeringOption)));
        waitForLoadingElementToDissapear();
    }
    
 //   private final String AGENTES_OFERTA_OPTION_XPATH = "//h4[contains(@class,'bloque-descripcion-title') and contains(text(),'#OFFER')]"
 //           + "#EXTRAOFFER" + "/../.." + DESCRIPTION_ARROW_XPATH;

 //   private final String EXTRA_OFFER = "/span[contains(text(),'#VALUE')]/..";
    
    // public void SeleccionoPlan (String Plan){ 	Driver.findElement(By.Xpath("//*[contains(text(), '"+PLAN+"')]"))  }
    public void clickOnOfferOptionByPosition(int position) {
        click(getDriver().findElements(By.xpath(DESCRIPTION_ARROW_XPATH)).get(position));
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
    }

    public boolean offerOptionOpensOnboardingtabAfterClicking(int position) {
        clickOnOfferOptionByPosition(position);
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        return tabs.size() > 1;
    }
    
    public boolean offerOptionOpensOnboardingtabAfterClicking(String text) {
        clickOnOfferOptionByText(text);
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        return tabs.size() > 1;
    }

    public String getFirstCreditCardLimitText() {
        List<WebElement> limits = getDriver().findElements(By.cssSelector(CREDIT_CARD_LIMIT_TEXT_CSS));
        return limits.get(0).getText();
    }

}
