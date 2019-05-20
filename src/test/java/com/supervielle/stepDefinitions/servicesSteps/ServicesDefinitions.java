package com.supervielle.stepDefinitions.servicesSteps;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;

import com.supervielle.backend.SupervielleServicesUtils;
import com.supervielle.framework.utils.ServicesUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ServicesDefinitions {

    String baseHost;
    String resourceURL;
    Response response;
    SupervielleServicesUtils serviciosSupervielle;

    @Given("^consulto a \"([^\"]*)\" usado para Onboarding$")
    public void setBaseHostAndResourceURL(String resource)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        baseHost = ServicesUtils.getEndpoint(ServicesUtils.ONBOARDING_APPLICATION);
        resourceURL = resource;

    }

    @When("^realizo el post request con el cuil (\\d+), mail \"([^\"]*)\" y tipo de oferta \"([^\"]*)\"$")
    public void doPostWithData(long cuil, String mail, String tipoOferta) {
        RestAssured.useRelaxedHTTPSValidation();
        serviciosSupervielle = new SupervielleServicesUtils();
        Map<String, Object> map = serviciosSupervielle.getClientInfo(cuil, mail, tipoOferta);
        response = serviciosSupervielle.requestPost(map, baseHost, resourceURL);
    }

    @Then("^el servicio responde (\\d+)$")
    public void verifyServiceResponse(String status) {
        Assert.assertTrue("El servicio " + baseHost + resourceURL + " NO MUESTRA RESULTADOS ",
                serviciosSupervielle.validateStatus(response, status));
    }

}
