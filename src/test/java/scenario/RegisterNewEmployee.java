package scenario;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RegisterNewEmployee {
    private final String regis_Email = "Valoran01@test.com";
    private final String expected_full_name = "Test Employee";
    private final String expected_department = "IT";
    private final String expected_title = "QA";
    public static String token;

    /* List Of Test APIs
        1.testGetEmployee
        2.testLogin
        3.testDeleteEmployee
        4.testUpdateEmployee
        5.registerEmployee
        6.searchEmployee
    */ 

    /* Scenario : RestAssured E2E Test
        * Test Case 001 : Register a new employee
        1. Hit the register endpoint with valid employee data
        2. Hit the Login endpoint with the new employee's credentials
        3. Hit the getEmployee endpoint to verify the employee was created
     */

    @Test()
    public void registerEmployee() {
        String body = "{\r\n" + //
                        "    \"email\": \"" + regis_Email + "\",\r\n" + //
                        "    \"password\": \"Mine\",\r\n" + //
                        "    \"full_name\": \"" + expected_full_name + "\",\r\n" + //
                        "    \"department\": \""+ expected_department + "\",\r\n" + //
                        "    \"title\": \""+ expected_title +"\"\r\n" + //
                        "}";
        //Send a POST request to register a new employee
        Response responseCreateEmployee = RestAssured
            .given()
            .contentType("application/json")
            .body(body)
            .log()
            .all()
            .when()
            .post("https://whitesmokehouse.com/webhook/employee/add");

            System.out.println("Response Create Employee: " + responseCreateEmployee.asPrettyString());

            //Validate the response
            Assert.assertEquals(responseCreateEmployee.statusCode(), 200, "Status code should be 200" + responseCreateEmployee.statusCode());

            //Validate the response
            Assert.assertNotNull(responseCreateEmployee.jsonPath().get("[0].id"), "Response body should not be null");
            Assert.assertEquals(responseCreateEmployee.jsonPath().get("[0].email"), regis_Email, "Email should match the expected email" + " But got: " + responseCreateEmployee.jsonPath().get("[0].email"));

            //Hit the login endpoint with the new employee's credentials
            String loginBody = "{\r\n" +
                                "  \"email\": \"" + regis_Email + "\",\r\n" +
                                "  \"password\": \"Mine\"\r\n" +
                                "}";
            Response responseLogin = RestAssured
                .given()
                .contentType("application/json")
                .body(loginBody)
                .log()
                .all()
                .when()
                .post("https://whitesmokehouse.com/webhook/employee/login");

            System.out.println("Response Login: " + responseLogin.asPrettyString());
            token = responseLogin.jsonPath().getString("[0].token");


            //Hit the getEmployee endpoint to verify the employee was created

            Response responseGetEmployee = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .log()
                .all()
                .when()
                .get("https://whitesmokehouse.com/webhook/employee/get");

            System.out.println("Response Get Employee: " + responseGetEmployee.asPrettyString());

            Assert.assertEquals(responseGetEmployee.statusCode(), 200, "Status code should be 200" + responseGetEmployee.statusCode());

            Assert.assertEquals(responseCreateEmployee.jsonPath().get("[0].email"),regis_Email, "Email should match the expected email");
            Assert.assertEquals(responseCreateEmployee.jsonPath().get("[0].full_name"), expected_full_name, "Full name should match the expected full name");
            Assert.assertEquals(responseCreateEmployee.jsonPath().get("[0].department"), expected_department, "Department should match the expected department");
            Assert.assertEquals(responseCreateEmployee.jsonPath().get("[0].title"), expected_title, "Title should match the expected title");
    }
    /*
        * Test Case 003 : Delete Employee
        1. Hit the delete endpoint with the employee's ID
        2. Hit the getEmployee endpoint to verify the employee was deleted
        3. Hit the login endpoint with the deleted employee's credentials
     */

    @Test(dependsOnMethods = "registerEmployee")
    public void deleteEmployee() {
        //Hit the delete endpoint with the employee's ID
        Response responseDeleteEmployee = RestAssured
            .given()
            .header("Authorization", "Bearer " + token)
            .header("Content-Type", "application/json")
            .log()
            .all()
            .when()
            .delete("https://whitesmokehouse.com/webhook/employee/delete");

        System.out.println("Response Delete Employee: " + responseDeleteEmployee.asPrettyString());
        //Validate the response
        Assert.assertEquals(responseDeleteEmployee.statusCode(), 200, "Status code should be 200" + responseDeleteEmployee.statusCode());
    }

}