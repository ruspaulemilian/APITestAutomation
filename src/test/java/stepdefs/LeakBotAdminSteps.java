package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class LeakBotAdminSteps {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(LeakBotAdminSteps.class);


    public Response response;
    public ValidatableResponse json;
    public RequestSpecification request;
    private String ENDPOINT_MAIN_APPLICATION = "https://test.leakbot.io/v1.0";
    private String ENDPOINT_UPDATE_USER = ENDPOINT_MAIN_APPLICATION + "/User/Account/Update";


    @Given("^The application is up and running$")
    public void verifyApplicationIsUp() {
        request = given().request();
        response = request.when().get(ENDPOINT_MAIN_APPLICATION);
        json =response.then().statusCode(200);
    }

    @When("I want to modify the user details using details from the json file '([^']*)'")
    public void modifyUserDetails(String file, Integer statusCode) throws URISyntaxException, IOException {
        URI uri = LeakBotAdminSteps.class.getResource(file).toURI();
        final String json = IOUtils.toString(uri, (Charset) Charset.availableCharsets());
        LOG.debug("TCR REQUEST POST {}", json);
        response = given().body(file).with().contentType("application/json").
                when().put(ENDPOINT_UPDATE_USER);
    }

    @Then("The status code is (\\d+)")
    public void verify_status_code(int statusCode) {
        json = response.then().statusCode(statusCode);
    }

    @Then("Response includes the following$")
    public void response_equals(Map<String, String> responseFields) {
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if (StringUtils.isNumeric(field.getValue())) {
                json.body(field.getKey(), equalTo(Long.parseLong(field.getValue())));
            } else {
                json.body(field.getKey(), equalTo(field.getValue()));
            }
        }
    }


}
