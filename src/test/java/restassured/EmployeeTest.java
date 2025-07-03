package restassured;


import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;


public class EmployeeTest {
    public static String token;
    public final String expected_email = "test1@test.com";
    public final String expected_full_name = "fifi noela";

    @BeforeSuite
    public void login(){
        String body = "{\r\n" +
                "  \"email\": \"" + expected_email + "\",\r\n" + 
                "  \"password\": \"test\"\r\n" + 
                "}";
       Response res = RestAssured
        .given()
        .contentType("application/json")
        .body(body)
        .log()
        .all()
        .when()
        .post("https://whitesmokehouse.com/webhook/employee/login");

        token = res.jsonPath().getString("[0].token");

        System.out.println("Token: " + token);

        Assert.assertNotNull(token, "Token should not be null");
    }
    

    @Test
    public void getCurrentEmployeeTest(){
        RestAssured
        .given()
        .header(new Header("Authorization", "Bearer " + token))
        .log()
        .all()
        .when()
        .get("https://whitesmokehouse.com/webhook/employee/get")
        .then()
        .statusCode(200)
        .body("[0].email", Matchers.containsString(expected_email))
        .and()
        .body("[0].full_name", Matchers.containsString(expected_full_name));
    }


}
