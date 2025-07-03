// package restassured;


// import org.hamcrest.Matchers;
// import org.testng.Assert;
// import org.testng.annotations.BeforeSuite;
// import org.testng.annotations.Test;

// import io.restassured.RestAssured;
// import io.restassured.http.Header;
// import io.restassured.response.Response;


// public class AddEmployeeTest {
//     private String token;
//     private String email;
//     private String password;

//     @Test
//     public void AddNewEmployeeTest(){
//         Response res = RestAssured
//                 .given()
//                 .contentType("application/json")
//                 .log()
//                 .all()
//                 .when()
//                 .get("https://whitesmokehouse.com/webhook/employee/get")
//                 .then()
//                 .statusCode(200)
//                 .body("[0].email", Matchers.containsString(expected_email))
//                 .and()
//                 .body("[0].full_name", Matchers.containsString(expected_full_name));
//     }


// }
