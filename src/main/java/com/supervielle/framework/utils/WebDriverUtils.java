package com.supervielle.framework.utils;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.supervielle.framework.tdm.EnvironmentProvider;

public class WebDriverUtils {
    protected static final int TIME_OUT_FOR_ELEMENT = 5;
    protected static final int TIME_OUT_FOR_WAIT = 180;
    private static final String ENVIRONMENT = AutomationProperties.getString("environment").replaceAll(" ", "");
    private static WebDriverWait driverWait;

    public static String getEnvironment() {
        return ENVIRONMENT;
    }

    public static void initializePageInstance(WebDriver driver, String app)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        driver.get(EnvironmentProvider.getUrl(app + "." + getEnvironment()));
        System.out.println("---");
        System.out.println("ENVIRONMENT: " + EnvironmentProvider.getUrl(getEnvironment()));
    }

    public void initializeDriverWait(WebDriver driver) {
        driverWait = new WebDriverWait(driver, TIME_OUT_FOR_WAIT);
    }

    public boolean waitForElementToBeVisible(By by) {
        try {
            driverWait.until((ExpectedConditions.visibilityOfElementLocated(by)));
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean waitForElementToBeClickable(WebElement element) {
        try {
            driverWait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean waitForElementToBePresent(WebElement element, int seconds) {
        try {
            return waitOn(element, seconds).until((Function<? super WebElement, Boolean>) WebElement::isDisplayed);
        } catch (TimeoutException toe) {
            return false;
        }
    }

    public boolean waitForElementLocatedToBePresent(By by, int sec) {
        try {
            driverWait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return false;

    }

    public void waitSeconds(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected <K> FluentWait<K> waitOn(K object, int timeOutSeconds) {
        return new FluentWait<>(object).ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class).withTimeout(timeOutSeconds, SECONDS)
                .pollingEvery(1, SECONDS);
    }

    public boolean clickWithActionsBuilder(WebDriver webDriver, WebElement element) {
        try {
            Actions builder = new Actions(webDriver);
            builder.moveToElement(element, 5, 5).click(element);
            builder.perform();
            return true;
        } catch (TimeoutException toe) {
            return false;
        }
    }

    public void waitForElementLocatedToDisappear(WebDriver driver, By byLocator, int seconds) {
        if (driver.findElement(byLocator).isDisplayed()) {
            try {
                driverWait.until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return !d.findElement(byLocator).isDisplayed();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isElementPresent(WebDriver driver, By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void mouseOver(WebDriver driver, WebElement element) {
        Actions builder = new Actions(driver);
        Action action = builder.moveToElement(element).build();
        action.perform();
    }
}
