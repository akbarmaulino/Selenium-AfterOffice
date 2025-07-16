package converter;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.demoqa.website.RequestLogin;
import com.demoqa.website.ResponseEmployee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class AddEmployeeWithDeserialization {
    private final String expected_email = "test00000001@test.com";
    private final String expected_password = "test99";
    private String token;


    @Test
    public void AddNewEmployeeTest() {
        System.out.println("Add Employee Test");
        String body = "{\r\n" +
                    "  \"email\": \"" + expected_email + "\",\r\n" +
                    "  \"password\": \"" + expected_password + "\",\r\n" +
                    "  \"full_name\": \"fifi noela\",\r\n" +
                    "  \"department\": \"IT\",\r\n" +
                    "  \"title\": \"QA\"\r\n" +
                    "}";
        Response res = RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .log()
                .all()
                .when()
                .post("https://whitesmokehouse.com/webhook/employee/add");
        // System.out.println(res.prettyPrint());
        // String email = res.jsonPath().getString("[0].email");
        // String password = res.jsonPath().getString("[0].password_hash");
        // Assert.assertEquals(email, expected_email, "Email should match");
        // Assert.assertNotNull(password, "Password should not be null");
        // Assert.assertNotNull(res.jsonPath().getString("[0].id"), "ID should not be null");

        ResponseEmployee[] resAdd = res.as(ResponseEmployee[].class);
        System.out.println("Email Deserialization : " + resAdd[0].email);
        System.out.println("Id Deserialization : " + resAdd[0].id);

        Assert.assertEquals(res.getStatusCode(), 200,"Status Code is Wrong Not 200");
        Assert.assertEquals(resAdd[0].email, expected_email);
        Assert.assertNotNull(resAdd[0].passwordHash);
        Assert.assertNotNull(resAdd[0].id,"ID Should Not be Null");
    }

    @Test(dependsOnMethods = "AddNewEmployeeTest")
    public void LoginEmployeeTest() throws JsonProcessingException {

        RequestLogin requestLogin = new RequestLogin(expected_email, expected_password);
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonLogin = objectMapper.writeValueAsString(requestLogin);
        System.out.println("JSON String : " + jsonLogin);

        System.out.println("Login Employee Test");
        String body = "{\r\n" +
                      "  \"email\": \"" + expected_email + "\",\r\n" +
                      "  \"password\": \"" + expected_password + "\"\r\n" +
                      "}";

        Response res = RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .log()
                .all()
                .when()
                .post("https://whitesmokehouse.com/webhook/employee/login");

        // DEBUG: Cek isi response lengkap
        System.out.println("Raw login response: " + res.getBody().asString());

        // Ambil token dan bersihkan tanda [ ]
        token = res.jsonPath().getString("[0].token");
        Assert.assertNotNull(token, "Token should not be null");
    }

    @Test(dependsOnMethods = "LoginEmployeeTest")
    public void deleteEmployeeTest() {
        System.out.println("Delete Employee Test");
        Response res = RestAssured
                .given()
                .header(new Header("Authorization", "Bearer " + token))
                .log()
                .all()
                .when()
                .delete("https://whitesmokehouse.com/webhook/employee/delete");

        System.out.println("Delete Response: " + res.getBody().asString());
        Assert.assertEquals(res.getStatusCode(), 200, "Status code should be 200 (OK)");

    }
    
}