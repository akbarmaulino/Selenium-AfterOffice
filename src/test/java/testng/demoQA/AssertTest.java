package testNG.demoQA;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.testng.Assert;
import org.testng.annotations.Test;

import testNG.data.Prodcut;

public class AssertTest {
    @Test
    public void main() {
        Prodcut actual = new Prodcut("Laptop", "High performance laptop", 1500.00);
        Prodcut expected = new Prodcut("Laptop", "High performance laptop", 1500.00);
        Assert.assertEquals(actual, expected, "The actual product does not match the expected product");


        List<String> actualList = new ArrayList<String>();
        actualList.add("Apple");
        actualList.add("Banana");
        actualList.add("Cherry");
        Predicate<String> expectedList = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.equals("Apple");
            }
        };

        Assert.assertListContains(actualList, expectedList, "Apple kosong ada di list actual");

        // // Test case 1: Assert True
        // boolean condition1 = true;
        // assert condition1 : "Test case 1 failed: Condition is not true";
        // System.out.println("Test case 1 passed: Condition is true");
        // // Test case 2: Assert False
        // boolean condition2 = false;
        // try {
        //     assert condition2 : "Test case 2 failed: Condition is not false";
        // } catch (AssertionError e) {
        //     System.out.println(e.getMessage());
        // }
        // // Test case 3: Assert Equals
        // int expectedValue = 5;
        // int actualValue = 5;
        // assert expectedValue == actualValue : "Test case 3 failed: Expected " + expectedValue + " but got " + actualValue;
        // System.out.println("Test case 3 passed: Values are equal");
        // // Test case 4: Assert Not Equals
        // int unexpectedValue = 10;
        // int anotherValue = 5;
        // assert unexpectedValue != anotherValue : "Test case 4 failed: Values should not be equal, but they are: " + unexpectedValue + " and " + anotherValue;
    
        // // Test case 5: Assert Null
        // String nullValue = null;
        // assert nullValue == null : "Test case 5 failed: Value is not null";
        // System.out.println("Test case 5 passed: Value is null");
        // // Test case 6: Assert Not Null
        // String notNullValue = "Hello";
        // assert notNullValue != null : "Test case 6 failed: Value is null";
        // System.out.println("Test case 6 passed: Value is not null");
    
    }
}
