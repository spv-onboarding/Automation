package com.supervielle.framework.web;

import java.text.Normalizer;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import com.supervielle.framework.utils.WebDriverUtils;

/**
 * Abstract class that every Page Object must inherit.
 *
 * @author Juan Krzemien
 */
public abstract class PageObjectBase {

    // Constants
    protected static final int TIME_AFTER_LOAD = 15;
    protected static final int TIME_AFTER_SERVICES_CALL = 10;
    protected static final int TIME_AFTER_CLICK = 5;
    protected static final int TIME_OUT_MINIMUN = 1;
    protected static final int TIME_OUT_FOR_PAGE_LOAD = 30;
    protected static final String HIDDEN_LOADING_COVER_XPATH = "//div[contains(@class,'loading ng-hide')]";

    private static final String ASCII_REGEX = "[^\\p{ASCII}]";
    private WebDriverUtils webDriverUtils = new WebDriverUtils();

    public static String lastElement;

    private static WebDriver webDriver;

    protected PageObjectBase(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver object is null");
        } else {
            webDriver = driver;
            PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 10), this);
            webDriverUtils.initializeDriverWait(webDriver);
            if (isAlertPresent()) {
                webDriver.switchTo().alert().accept();
                System.out.println("ALERT TRUE DISMISS");
            }
        }
    }

    protected abstract By getPageLocator();

    public boolean isLoaded() {
        return webDriverUtils.waitForElementToBeVisible(getPageLocator());
    }

    // Never allowed to be public!
    protected static WebDriver getDriver() {
        return webDriver;
    }

    protected void typeOnField(WebElement element, String text) {
        webDriverUtils.waitForElementToBeClickable(element);

        System.out.println("Limpiando texto y enviando nuevo texto: " + text);
        element.clear();
        String val = text;
        for (int i = 0; i < val.length(); i++) {
            char c = val.charAt(i);
            String s = new StringBuilder().append(c).toString();
            element.sendKeys(s);
        }
        System.out.println("Un texto fue escrito en " + element.toString());
    }

    protected String getNormalizedText(WebElement element) {
        webDriverUtils.waitForElementToBeClickable(element);
        String text = element.getText();
        System.out.println("Getting: " + text);
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll(ASCII_REGEX, StringUtils.EMPTY);
        System.out.println("Normalized: " + text);
        return text;
    }

    public void dispose() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    protected void scrollDownToElement(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView();", webElement);
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void selectOptionFromDropdown(WebElement webElement, String text) {
        Select dropdown = new Select(webElement);
        dropdown.selectByVisibleText(text);
        getWebDriverUtils().waitSeconds(TIME_AFTER_CLICK);
        //boolean selectedOptionText = dropdown.getFirstSelectedOption().getAttribute("label").equalsIgnoreCase(text);
        boolean selectedOptionText = dropdown.getFirstSelectedOption().getAttribute("label").contains(text);
        if (!selectedOptionText) {
            throw new InvalidElementStateException(
                    String.format("The option with text " + text + " was not correctly selected in the dropdown", text));
//            		String.format("The option with text <{0}> was not correctly selected in the dropdown", text));
        }
    }

    public String getUrl() {
        return getDriver().getCurrentUrl();
    }

    public static void setLastElement(String text) {
        lastElement = text;
    }

    protected void click(WebElement element) {
        System.out.println("Tratando de hacer click en " + element.toString());
        if ((webDriverUtils.waitForElementToBeClickable(element))) {
            element.click();
            System.out.println("Se hizo click en " + element.toString());
        }

    }

    protected String normalText(WebElement element) {
        String text = element.getText();
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll(ASCII_REGEX, "");
        return text;
    }

    public WebDriverUtils getWebDriverUtils() {
        return this.webDriverUtils;
    }

    public boolean isAlertPresent() {
        try {
            webDriver.switchTo().alert();
            System.out.println("HAY ALERT");
            return true;
        } catch (NoAlertPresentException Ex) {
            System.out.println("NO HAY ALERT");
            return false;
        }
    }

    public void waitForLoadingElementToDissapear() {
        webDriverUtils.waitForElementLocatedToBePresent(By.xpath(HIDDEN_LOADING_COVER_XPATH), TIME_OUT_FOR_PAGE_LOAD);
    }

}
