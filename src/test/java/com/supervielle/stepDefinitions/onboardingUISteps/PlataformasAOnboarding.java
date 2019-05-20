package com.supervielle.stepDefinitions.onboardingUISteps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.supervielle.framework.utils.AutomationProperties;
import com.supervielle.framework.utils.WebDriverUtils;
import com.supervielle.pageobjects.OnboardingPage;
import com.supervielle.pageobjects.PlataformasHomePage;
import com.supervielle.stepDefinitions.Hooks;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PlataformasAOnboarding {

    protected WebDriver driver;
    PlataformasHomePage platformHomePage;
    OnboardingPage onboardingPage;

    public PlataformasAOnboarding() {
        this.driver = Hooks.getwebDriver();
    }

    @Given("^me encuentro en la pagina de inicio de plataformas$")
    public void me_encuentro_en_la_pagina_de_inicio_de_platformas() throws Throwable {
        WebDriverUtils.initializePageInstance(driver, AutomationProperties.getString("app.plataformas"));
        platformHomePage = new PlataformasHomePage(driver);
    }

    @And("^selecciono un \"([^\"]*)\"$")
    public void selecciono_un(String tipoDocumento) throws Throwable {
        platformHomePage.selectDocumentType(tipoDocumento);
    }

    @And("^ingreso el \"([^\"]*)\"$")
    public void ingreso_el(String numeroDocumento) throws Throwable {
        platformHomePage.writeOnDocumentInput(numeroDocumento);
    }

    @And("^selecciono la \"([^\"]*)\"$")
    public void selecciono_la(String nacionalidad) throws Throwable {
        platformHomePage.selectNationality(nacionalidad);
    }

    @And("^selecciono el \"([^\"]*)\"$")
    public void selecciono_el(String genero) throws Throwable {
        platformHomePage.selectGender(genero);
    }

    @When("^selecciono la opcion descubrir$")
    public void selecciono_la_opcion_descubrir() throws Throwable {
        onboardingPage = platformHomePage.clickOnDiscoverButton();
    }

    @Then("^la aplicacion me redirecciona al inicio de Onboarding con los datos autocompletados de \"([^\"]*)\" y \"([^\"]*)\"$")
    public void la_aplicacion_me_redirecciona_al_inicio_de_Onboarding_con_los_datos_autocompletados_de(
            String nroDocumento, String genero) throws Throwable {
        Assert.assertTrue("La pagina de inicio de OB cargo no ha cargado correctamente", onboardingPage.isLoaded());
        Assert.assertTrue("Dato de cuil no ha sido autocompletado correctamente",
                onboardingPage.getTextFromCuilInputElement().contains(nroDocumento));
        Assert.assertTrue("El genero cargado previamente no fue autocompletado correctamente",
                onboardingPage.isGenderOptionSelected(genero));
    }
}
