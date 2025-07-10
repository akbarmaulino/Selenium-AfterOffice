package cucumber.definitions;

import java.util.List;

import org.testng.Assert;

import com.demoqa.website.ResponseEmployee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;


public class EmployeeDefinition {
    public static String baseUrl;
    public static Response res;
    public static String token;



    @Given("The Base URL in This Feature is {string}")
    public void theBaseURLInThisFeatureIs(String baseUrl) {
        EmployeeDefinition.baseUrl = baseUrl;
    }

    @When("Make Sure Token is Not Empty")
    public void makeSureTokenIsNotEmpty() {
        assert EmployeeDefinition.token != null : "Token null";
    }

    @When("Send a {string} Request to {string} with the following body:")
    public void sendRequest(String method, String endpoint, String body) {
        System.out.println("Add Employee Test");

        res = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", EmployeeDefinition.token != null ? "Bearer " + EmployeeDefinition.token : "")
                .body(body)
                .when()
                .redirects().follow(true)
                .request(method, EmployeeDefinition.baseUrl + endpoint);
    }

    @Then("The Response Status Code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        Assert.assertEquals(res.getStatusCode(), statusCode, "Status code does not match");
    }

    @And("The Response Schema should be valid against {string}")
    public void theResponseBodyShouldContain(String expectedBody) {
        res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(expectedBody));
    }

    @And("Save Token to local variable")
    public void saveTokenToVariable() {
        EmployeeDefinition.token = res.jsonPath().getString("[0].token");
    }

    @And("Full Name in The Response Body should be {string}")
    public void fullNameShouldBe(String expectedFullName) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseEmployee> addEmployeeResponse = objectMapper.readValue(res.body().asString(), new TypeReference<List<ResponseEmployee>>() {});
        assert addEmployeeResponse.get(0).getFullName().equals(expectedFullName) : "fullname not expected";
    }

    @And("Department in The Response Body should be {string}")
    public void departmentShouldBe(String expectedDepartment) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseEmployee> addEmployeeResponse = objectMapper.readValue(res.body().asString(), new TypeReference<List<ResponseEmployee>>() {});
        assert addEmployeeResponse.get(0).getDepartment().equals(expectedDepartment) : "Department not expected";

    }

    @And("Title in The Response Body should be {string}")
    public void titleShouldBe(String expectedTitle) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseEmployee> addEmployeeResponse = objectMapper.readValue(res.body().asString(), new TypeReference<List<ResponseEmployee>>() {});
        assert addEmployeeResponse.get(0).getTitle().equals(expectedTitle) : "title not expected";
    }
}
