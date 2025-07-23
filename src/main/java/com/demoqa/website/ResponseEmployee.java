package com.demoqa.website;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
@Data
public class ResponseEmployee {
    /*
     {
        "id": 697,
        "email": "Valoran01@test.com",
        "password_hash": "fedc0e3f18d0ff5df1579ad5ce33ba5d",
        "full_name": "Test Employee Updated",
        "department": "IT",
        "title": "QA",
        "create_at": "2025-07-08T03:03:42.213Z",
        "update_at": "2025-07-08T03:03:42.213Z"
    }
     */


    // Implementation Deserialization
    @JsonProperty("id")
    public int id;

    @JsonProperty("email")
    public String email;

    @JsonProperty("password_hash")
    public String passwordHash;

    @JsonProperty("full_name")
    public String fullName;

    @JsonProperty("department")
    public String department;

    @JsonProperty("title")
    public String title;

    @JsonProperty("create_at")
    public String createAt;

    @JsonProperty("update_at")
    public String updateAt;

    public ResponseEmployee(){
        
    }
}
