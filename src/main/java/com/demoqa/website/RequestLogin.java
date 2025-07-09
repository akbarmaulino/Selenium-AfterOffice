package com.demoqa.website;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestLogin {
    /*
    "{\r\n" +
        "  \"email\": \"" + expected_email + "\",\r\n" +
        "  \"password\": \"" + expected_password + "\"\r\n" +
        "}";
     */
    
     @JsonProperty("email")
     public String email;

     @JsonProperty("password")
     public String password;

     public RequestLogin(String email, String password){
        this.email = email;
        this.password = password;
        
     }
}
