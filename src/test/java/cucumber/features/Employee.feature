Feature: Employee API

    Background: 
        Given The Base URL in This Feature is "https://whitesmokehouse.com/webhook"
    
    Scenario: Create Employee
        When Send a "POST" Request to "/employee/add" with the following body:
        """
        {
            "email": "Valorant0000000000016@Test.com",
            "password": "test99",
            "full_name": "fifi noela",
            "department": "IT",
            "title": "QA"
        }
        """
        Then The Response Status Code should be 200
        And The Response Schema should be valid against "RegisterSchema.json"

    Scenario: Login Employee
        When Send a "POST" Request to "/employee/login" with the following body:
        """
        {
            "email": "Valorant0000000000016@Test.com",
            "password": "test99"
        }
        """
        Then The Response Status Code should be 200
        And The Response Schema should be valid against "LoginSchema.json"
        And Save Token to local variable

    Scenario: Update Employee
        Given Make Sure Token is Not Empty
        When Send a "PUT" Request to "/employee/update" with the following body:
        """
        {
            "email": "Valorant0000000000016@Test.com",
            "password": "test99",
            "full_name": "fifi Update",
            "department": "IT Update",
            "title": "QA Update"
        }
        """
        Then The Response Status Code should be 200
        And Full Name in The Response Body should be "fifi Update"
        And Department in The Response Body should be "IT Update"
        And Title in The Response Body should be "QA Update"
    
    Scenario: Get Employee
        Given Make Sure Token is Not Empty
        When Send a "GET" Request to "/employee/get" with the following body:
        """
        {}
        """
        Then The Response Status Code should be 200
        And Full Name in The Response Body should be "fifi Update"
        And Department in The Response Body should be "IT Update"
        And Title in The Response Body should be "QA Update"
    
    Scenario: Delete Employee
        Given Make Sure Token is Not Empty
        When Send a "DELETE" Request to "/employee/delete" with the following body:
        """
        {
            "email": "Valorant0000000000016@Test.com",
            "password": "test99"
        }
        """
        Then The Response Status Code should be 300



