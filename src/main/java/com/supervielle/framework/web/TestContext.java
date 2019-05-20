package com.supervielle.framework.web;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.File;
import java.util.function.Supplier;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import com.supervielle.webdriver.WebdriverConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestContext {

    private static final ThreadLocal<WebDriver> WEBDRIVER_PER_THREAD = ThreadLocal
            .withInitial(new Supplier<WebDriver>() {

                @Override
                public WebDriver get() {
                    WebDriver driver = generateDriver();
                    driver.manage().timeouts().implicitlyWait(1, SECONDS);
                    driver.manage().window().maximize();
                    return driver;
                }

            });

    public TestContext() {
    }

    public static WebDriver get() {
        return WEBDRIVER_PER_THREAD.get();
    }

    public static WebDriver generateDriver() {
        WebDriver driver = null;
        WebdriverConfig config = ConfigFactory.create(WebdriverConfig.class);
        String browser = config.browser().toUpperCase();
        switch (browser) {
        case "FIREFOX":
            System.setProperty("webdriver.gecko.driver", config.geckoDriver());
            driver = new FirefoxDriver();
            break;
        case "CHROME":
            System.setProperty("webdriver.chrome.driver", config.chromeDriver());
            driver = new ChromeDriver();
            break;
        case "IE":
            InternetExplorerOptions IEOptions = new InternetExplorerOptions();
            IEOptions.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
            IEOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            IEOptions.setCapability("requireWindowFocus", true);
            // IEOptions.setCapability("nativeEvents", false);
            IEOptions.setCapability("ignoreZoomSetting", true);
            // IEOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,
            // true);
            File file = new File("IEDriverServer.exe");
            System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
            driver = new InternetExplorerDriver(IEOptions);
            break;
        case "WDCHROME":
//           WebDriverManager.chromedriver().setup();
             System.setProperty("wdm.proxy", "10.243.104.200:8080");
             WebDriverManager.chromedriver().version("2.41").setup();
            driver = new ChromeDriver();
            break;
        case "WDFIREFOX":
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            break;
        case "WDIE":
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
            break;
        case "WDEDGE":
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            break;
        default:
            break;
        }
        return driver;
    }

}