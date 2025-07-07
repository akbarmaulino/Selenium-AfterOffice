package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class AddEmployeeTest {
    private final String expected_email = "test00000000@test.com";
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

        System.out.println(res.prettyPrint());

        String email = res.jsonPath().getString("[0].email");
        String password = res.jsonPath().getString("[0].password_hash");
        Assert.assertEquals(email, expected_email, "Email should match");
        Assert.assertNotNull(password, "Password should not be null");
    }

    @Test(dependsOnMethods = "AddNewEmployeeTest")
    public void LoginEmployeeTest() {
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
