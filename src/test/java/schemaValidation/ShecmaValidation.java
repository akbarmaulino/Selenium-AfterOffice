package schemaValidation;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demoqa.website.RequestLogin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class ShecmaValidation {
    private String email = "Valoran03@test.com";
    private String password = "Mine";
    private String tokenLogin;

    @Test
    public void LoginEmployee() throws JsonProcessingException{
        String body = "{\r\n" +
                        "  \"email\": \"" + email + "\",\r\n" +
                        "  \"password\": \"" + password + "\"\r\n" +
                        "}";


        RequestLogin requestLogin = new RequestLogin(email, password);
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonLogin = objectMapper.writeValueAsString(requestLogin);
        System.out.println("JSON String : " + jsonLogin);
                        
        Response res = RestAssured
            .given()
            .contentType("application/json")
            .body(body)
            .log()
            .all()
            .when()
            .post("https://whitesmokehouse.com/webhook/employee/login");

        try {
            res.then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("LoginSchema.json"));
            System.out.println("✅ JSON Schema VALID!");
        } catch (AssertionError e) {
            System.out.println("❌ JSON Schema TIDAK VALID!");
            System.out.println("Error: " + e.getMessage());
            throw e;
        }

        tokenLogin = res.jsonPath().getString("[0].token");

        System.out.println("Response : " + res.asPrettyString());

        System.out.println("Token: " + tokenLogin);
        Assert.assertNotNull(tokenLogin, "Token should not be null");
     }

    
}
