package scenario;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UpdateUser {
    private String email_Update = "Valoran01@test.com";
    private String email_fix = "Valorant01Update.com";
    private String update_fullName = "Test Employee Updated";
    private String expected_department = "IT";
    private String expected_title = "QA";
    private String tokenLogin;
    /*
        * Test Case 002 : Update Employee
        1. Hit the update endpoint with valid employee data
        2. Hit the getEmployee endpoint to verify the employee was updated
        3. Hit the Login endpoint with the updated employee's credentials
        4. Hit the search endpoint to verify the employee was updated
     */
    @BeforeClass
     public void login(){
        String body = "{\r\n" +
                        "  \"email\": \"" + email_fix + "\",\r\n" +
                        "  \"password\": \"Mine\"\r\n" +
                        "}";
        Response res = RestAssured
            .given()
            .contentType("application/json")
            .body(body)
            .log()
            .all()
            .when()
            .post("https://whitesmokehouse.com/webhook/employee/login");

        tokenLogin = res.jsonPath().getString("[0].token");

        System.out.println("Token: " + tokenLogin);

        Assert.assertNotNull(tokenLogin, "Token should not be null");
     }
    @Test()
    public void update() {
        String body = "{\r\n" + //
                        "    \"email\": \"" + email_Update + "\",\r\n" + //
                        "    \"password\": \"Mine\",\r\n" + //
                        "    \"full_name\": \"" + update_fullName + "\",\r\n" + //
                        "    \"department\": \""+ expected_department + "\",\r\n" + //
                        "    \"title\": \""+ expected_title +"\"\r\n" + //
                        "}";

        //Send a POST request to update the employee
        Response responseUpdateEmployee = RestAssured
            .given()
            .contentType("application/json")
            .header("Authorization", "Bearer " + tokenLogin)
            .body(body)
            .log()
            .all()
            .when()
            .put("https://whitesmokehouse.com/webhook/employee/update");

        System.out.println("Response Update Employee: " + responseUpdateEmployee.asPrettyString());

        //Validate the response
        Assert.assertEquals(responseUpdateEmployee.statusCode(), 200, "Status code should be 200" + responseUpdateEmployee.statusCode());
        Assert.assertNotNull(responseUpdateEmployee.jsonPath().get("[0].id"), "Response body should not be null");
        Assert.assertEquals(responseUpdateEmployee.jsonPath().get("[0].email"), email_Update, "Email should match the expected email" + " But got: " + responseUpdateEmployee.jsonPath().get("[0].email"));
        Assert.assertEquals(responseUpdateEmployee.jsonPath().get("[0].full_name"), update_fullName, "Full name should match the expected full name" + " But got: " + responseUpdateEmployee.jsonPath().get("[0].full_name"));
        Assert.assertEquals(responseUpdateEmployee.jsonPath().get("[0].department"), expected_department, "Department should match the expected department" + " But got: " + responseUpdateEmployee.jsonPath().get("[0].department"));
        Assert.assertEquals(responseUpdateEmployee.jsonPath().get("[0].title"), expected_title, "Title should match the expected title" + " But got: " + responseUpdateEmployee.jsonPath().get("[0].title"));
    
        //Hit the getEmployee endpoint to verify the employee was updated
        Response responseGetEmployee = RestAssured
            .given()
            .header("Authorization", "Bearer " + tokenLogin)
            .header("Content-Type", "application/json")
            .log()
            .all()
            .when()
            .get("https://whitesmokehouse.com/webhook/employee/get");

        System.out.println("Response Get Employee: " + responseGetEmployee.asPrettyString());
        Assert.assertEquals(responseGetEmployee.statusCode(), 200, "Status code should be 200" + responseGetEmployee.statusCode());
        Assert.assertEquals(responseGetEmployee.jsonPath().get("[0].email"), email_Update, "Email should match the expected email");
        Assert.assertEquals(responseGetEmployee.jsonPath().get("[0].full_name"), update_fullName, "Full name should match the expected full name");
        Assert.assertEquals(responseGetEmployee.jsonPath().get("[0].department"), expected_department, "Department should match the expected department");
        Assert.assertEquals(responseGetEmployee.jsonPath().get("[0].title"), expected_title, "Title should match the expected title");

        //hit search endpoint to verify the employee was updated
        Response responseSearchEmployee = RestAssured
            .given()
            .header("Authorization", "Bearer " + tokenLogin)
            .header("Content-Type", "application/json")
            .log()
            .all()
            .when()
            .get("https://whitesmokehouse.com/webhook/41a9698d-d8b0-42df-9ddc-89c0a1a1aa79/employee/search/" + update_fullName);
        System.out.println("Response Search Employee: " + responseSearchEmployee.asPrettyString());
        Assert.assertEquals(responseSearchEmployee.statusCode(), 200, "Status code should be 200" + responseSearchEmployee.statusCode());
        Assert.assertEquals(responseSearchEmployee.jsonPath().get("[0].result[0].full_name"), update_fullName, "Full name should match the expected full name");
    }
    
}
