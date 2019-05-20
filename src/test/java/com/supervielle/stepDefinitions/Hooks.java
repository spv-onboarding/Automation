package com.supervielle.stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import com.supervielle.framework.web.TestContext;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

    protected static WebDriver driver;

    @Before
    public void tearUp(Scenario scenario) {
	if (scenario.getSourceTagNames().contains("@NoBrowser")) {
	    System.out.println("Inicializando tests de servicios.");
	} else {
	    driver = TestContext.generateDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	    driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
	}

    }

    @After
    public void tearDown(Scenario scenario) {
	if (scenario.getSourceTagNames().contains("@NoBrowser")) {
	    System.out.println("Finalizando tests de servicios");
	} else {
	    if (driver != null) {
	    	takeScreenShot(scenario);	
			driver.manage().deleteAllCookies();
			driver.quit();
			driver = null;
	    }
	}
    }

	protected static void takeScreenShot(Scenario scenario)
	{
		if(scenario.isFailed()){
		String screenShotName = scenario.getName().replaceAll("\\s","").replaceAll("[^\\p{ASCII}]", "") + System.currentTimeMillis();
		File sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{				
			File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/"+ screenShotName + ".png");
		    File dir = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots");
		    if (!dir.exists()) dir.mkdirs();
			Files.copy(sourcePath, destinationPath);
			Reporter.addScreenCaptureFromPath(destinationPath.toString());
		}
		catch(IOException ex){
			// TODO Auto-generated catch block
			Reporter.addStepLog("No se pudo capturar la pantalla");
		}
		}
	}    
    
    public static WebDriver getwebDriver() {
	return driver;
    }
}
